package com.example.humanmaintenance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.humanmaintenance.db.AppDatabase
import com.example.humanmaintenance.db.repositories.CalendarRepository
import com.example.humanmaintenance.ui.components.AddFloatingActionButton
import com.example.humanmaintenance.ui.components.AddSheet
import com.example.humanmaintenance.ui.components.AppDrawer
import com.example.humanmaintenance.ui.theme.HumanMaintenanceTheme
import com.example.humanmaintenance.ui.components.AppTopBar
import com.example.humanmaintenance.ui.map.AppPage
import com.example.humanmaintenance.ui.map.CalendarItemData
import com.example.humanmaintenance.ui.map.CalendarViewModel
import com.example.humanmaintenance.ui.map.CalendarViewModelFactory
import com.example.humanmaintenance.ui.map.Effort
import com.example.humanmaintenance.ui.map.FinanceItemData
import com.example.humanmaintenance.ui.map.TodoItemData
import com.example.humanmaintenance.ui.map.TodoPriority
import com.example.humanmaintenance.ui.pages.MainScreen
import java.time.LocalDate


class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()

    val db = Room.databaseBuilder(
      applicationContext,
      AppDatabase::class.java,
      "human_maintenance.db"
    ).build()

    val calendarRepository = CalendarRepository(db.calendarDao())

    setContent {
      HumanMaintenanceTheme {
        val calendarViewModel: CalendarViewModel = viewModel(
          factory = CalendarViewModelFactory(calendarRepository)
        )
        App(calendarViewModel)
      }
    }
  }
}

@Composable
fun App(
  calendarViewModel: CalendarViewModel
) {
  val financeItems = remember { mutableStateListOf<FinanceItemData>() }
  val calendarItems by calendarViewModel.calendarItems.collectAsState()
  val todoItems = remember { mutableStateListOf<TodoItemData>() }
  var currentPage by remember { mutableStateOf(AppPage.FINANCE_ITEMS) }
  var showAddSheet by remember { mutableStateOf(false) }
  var editingCalendarItem by remember { mutableStateOf<CalendarItemData?>(null) }
  var editingTodoItem by remember { mutableStateOf<TodoItemData?>(null) }
  var date by remember { mutableStateOf(LocalDate.now()) }

  AppDrawer(
    currentPage = currentPage,
    onPageSelected = { page ->
      currentPage = page
    }
  ) { onMenuClick ->
    Scaffold(
      floatingActionButton = {
        AddFloatingActionButton(
          currentPage = currentPage,
          onClick = {
            editingCalendarItem = null
            editingTodoItem = null
            showAddSheet = true
          }
        )
      },
      topBar = {
        AppTopBar(
          page = currentPage,
          onMenuClick = onMenuClick
        )
      }
    ) { innerPadding ->
      MainScreen(
        calendarItems = calendarItems,
        financeItems = financeItems,
        todoItems = todoItems,
        currentPage = currentPage,
        date = date,
        onDateChange = { newDate ->
          date = newDate
        },
        onCalendarItemClick = { item ->
          editingCalendarItem = item
          showAddSheet = true
        },
        onTodoItemClick = { item ->
          editingTodoItem = item
          showAddSheet = true
        },
        onPushTodoItem = { id ->
          val index = todoItems.indexOfFirst { it.id == id }
          if (index >= 0) {
            val item = todoItems[index]
            todoItems[index] = item.copy(
              date = item.date.plusDays(1),
              pushedCount = item.pushedCount + 1
            )
          }
        },
        onSwitchTodoComplete = { id ->
          val index = todoItems.indexOfFirst { it.id == id }
          if (index >= 0) {
            val item = todoItems[index]
            todoItems[index] = item.copy(completed = !item.completed)
          }
        },
        modifier = Modifier.padding(innerPadding)
      )

      if (showAddSheet) {
        AddSheet(
          currentPage = currentPage,
          updateItem = editingCalendarItem,
          updateTodoItem = editingTodoItem,
          date = date,
          onDismiss = {
            showAddSheet = false
            editingCalendarItem = null
            editingTodoItem = null
          },
          onAddFinance = { item: FinanceItemData ->
            financeItems.add(item)
            showAddSheet = false
          },
          onAddCalendar = { item: CalendarItemData ->
            calendarViewModel.addItem(item)
            showAddSheet = false
            editingCalendarItem = null
          },
          onAddTodo = { item: TodoItemData ->
            val index = todoItems.indexOfFirst { it.id == item.id }
            if (index >= 0) {
              todoItems[index] = item
            } else {
              todoItems.add(item)
            }
            showAddSheet = false
            editingTodoItem = null
          }
        )
      }
    }
  }
}