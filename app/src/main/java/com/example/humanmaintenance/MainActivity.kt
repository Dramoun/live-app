package com.example.humanmaintenance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.humanmaintenance.ui.components.AddFloatingActionButton
import com.example.humanmaintenance.ui.components.AddSheet
import com.example.humanmaintenance.ui.components.AppDrawer
import com.example.humanmaintenance.ui.theme.HumanMaintenanceTheme
import com.example.humanmaintenance.ui.components.AppTopBar
import com.example.humanmaintenance.ui.map.AppPage
import com.example.humanmaintenance.ui.map.CalendarItemData
import com.example.humanmaintenance.ui.map.FinanceItemData
import com.example.humanmaintenance.ui.pages.MainScreen


class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      HumanMaintenanceTheme {
        App()
      }
    }
  }
}

@Composable
fun App() {
  val financeItems = remember { mutableStateListOf<FinanceItemData>() }
  val calendarItems = remember { mutableStateListOf<CalendarItemData>() }
  var currentPage by remember { mutableStateOf(AppPage.FINANCE_ITEMS)}
  var showAddSheet by remember { mutableStateOf(false) }

  AppDrawer (
    currentPage = currentPage,
    onPageSelected = { page ->
      currentPage = page
    }
  ) { onMenuClick ->
    Scaffold(
      floatingActionButton = {
        AddFloatingActionButton(
          currentPage = currentPage,
          onClick = { showAddSheet = true }
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
        financeItems = financeItems,
        currentPage = currentPage,
        Modifier.padding(innerPadding)
      )

      if (showAddSheet) {
        AddSheet(
          currentPage = currentPage,
          onDismiss = {
            showAddSheet = false
          },
          onAddFinance = { item: FinanceItemData ->
            financeItems.add(item)
            showAddSheet = false
          },
          onAddCalendar = { item: CalendarItemData ->
            calendarItems.add(item)
            showAddSheet = false
          }
        )
      }
    }
  }
}
