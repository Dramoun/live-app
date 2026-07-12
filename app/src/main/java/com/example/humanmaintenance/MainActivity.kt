package com.example.humanmaintenance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.humanmaintenance.db.AppDatabase
import com.example.humanmaintenance.db.migrations.MIGRATION_1_2
import com.example.humanmaintenance.db.migrations.MIGRATION_2_3
import com.example.humanmaintenance.db.migrations.MIGRATION_3_4
import com.example.humanmaintenance.db.repositories.CalendarRepository
import com.example.humanmaintenance.db.repositories.FinanceRepository
import com.example.humanmaintenance.db.repositories.TodoRepository
import com.example.humanmaintenance.ui.components.AddFloatingActionButton
import com.example.humanmaintenance.ui.overlays.AddSheet
import com.example.humanmaintenance.ui.navigation.AppDrawer
import com.example.humanmaintenance.ui.theme.HumanMaintenanceTheme
import com.example.humanmaintenance.ui.navigation.AppTopBar
import com.example.humanmaintenance.ui.map.AppPage
import com.example.humanmaintenance.ui.map.CalendarItemData
import com.example.humanmaintenance.db.viewmodels.CalendarViewModel
import com.example.humanmaintenance.db.viewmodels.CalendarViewModelFactory
import com.example.humanmaintenance.ui.map.FinanceItemData
import com.example.humanmaintenance.db.viewmodels.FinanceViewModel
import com.example.humanmaintenance.db.viewmodels.FinanceViewModelFactory
import com.example.humanmaintenance.db.viewmodels.TodoViewModel
import com.example.humanmaintenance.db.viewmodels.TodoViewModelFactory
import com.example.humanmaintenance.ui.map.TodoItemData
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
    ).addMigrations(MIGRATION_1_2)
      .addMigrations(MIGRATION_2_3)
      .addMigrations(MIGRATION_3_4)
      .build()

    val calendarRepository = CalendarRepository(db.calendarDao())
    val financeRepository = FinanceRepository(db.financeDao())
    val todoRepository = TodoRepository(db.todoDao())

    setContent {
      HumanMaintenanceTheme {
        val calendarViewModel: CalendarViewModel = viewModel(
          factory = CalendarViewModelFactory(calendarRepository)
        )
        val financeViewModel: FinanceViewModel = viewModel(
          factory = FinanceViewModelFactory(financeRepository)
        )
        val todoViewModel: TodoViewModel = viewModel(
          factory = TodoViewModelFactory(todoRepository)
        )
        App(
          calendarViewModel = calendarViewModel,
          financeViewModel = financeViewModel,
          todoViewModel = todoViewModel
        )
      }
    }
  }
}

@Composable
fun App(
  calendarViewModel: CalendarViewModel,
  financeViewModel: FinanceViewModel,
  todoViewModel: TodoViewModel
) {
  val financeItems by financeViewModel.financeItems.collectAsState()
  // TODO: use this somehow to prepare filtered data per month
  //  should then be much more manageble to look up data
  val calendarItems by calendarViewModel.calendarItems.collectAsState()
  val todoItems by todoViewModel.todoItems.collectAsState()
  var currentPage by remember { mutableStateOf(AppPage.FINANCE_ITEMS) }
  var showAddSheet by remember { mutableStateOf(false) }
  var editingFinanceItem by remember { mutableStateOf<FinanceItemData?>(null) }
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
        onFinanceItemClick = { item ->
          editingFinanceItem = item
          showAddSheet = true
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
          todoViewModel.pushTodoItem(id)
        },
        onSwitchTodoComplete = { id ->
          todoViewModel.toggleComplete(id)
        },
        modifier = Modifier.padding(innerPadding)
      )

      if (showAddSheet) {
        AddSheet(
          currentPage = currentPage,
          updateFinanceItem = editingFinanceItem,
          updateCalendarItem = editingCalendarItem,
          updateTodoItem = editingTodoItem,
          date = date,
          onDismiss = {
            showAddSheet = false
            editingFinanceItem = null
            editingCalendarItem = null
            editingTodoItem = null
          },
          onAddFinance = { item: FinanceItemData ->
            financeViewModel.addItem(item)
            showAddSheet = false
            editingFinanceItem = null
          },
          onFinanceDelete = { item: FinanceItemData ->
            financeViewModel.deleteItem(item)
            showAddSheet = false
            editingFinanceItem = null
          },
          onAddCalendar = { item: CalendarItemData ->
            calendarViewModel.addItem(item)
            showAddSheet = false
            editingCalendarItem = null
          },
          onAddTodo = { item: TodoItemData ->
            todoViewModel.addItem(item)
            showAddSheet = false
            editingTodoItem = null
          },
          onCalendarDelete = { item: CalendarItemData ->
            calendarViewModel.deleteItem(item)
            showAddSheet = false
            editingCalendarItem = null
          },
          onTodoDelete = { item: TodoItemData ->
            todoViewModel.deleteItem(item)
            showAddSheet = false
            editingTodoItem = null
          }
        )
      }
    }
  }
}