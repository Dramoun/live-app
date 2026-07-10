package com.example.humanmaintenance.ui.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.humanmaintenance.ui.map.AppPage
import kotlinx.coroutines.launch

@Composable
fun AppDrawer(
  currentPage: AppPage,
  onPageSelected: (AppPage) -> Unit,
  content: @Composable (onMenuClick: () -> Unit) -> Unit
) {
  val drawerState = rememberDrawerState(DrawerValue.Closed)
  val scope = rememberCoroutineScope()

  ModalNavigationDrawer(
    drawerState = drawerState,
    drawerContent = {
      ModalDrawerSheet {
        Column(
          modifier = Modifier.padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
        ) {
          Spacer(Modifier.height(12.dp))
          Text("Pages", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleLarge)
          HorizontalDivider()

          Text("Finance", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleMedium)
          NavigationDrawerItem(
            label = { Text("Finance") },
            selected = currentPage == AppPage.FINANCE_ITEMS,
            onClick = {
              onPageSelected(AppPage.FINANCE_ITEMS)
              scope.launch {
                drawerState.close()
              }
            }
          )

          Text("Finance", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleMedium)
          NavigationDrawerItem(
            label = { Text("Calendar day") },
            selected = currentPage == AppPage.CALENDAR,
            onClick = {
              onPageSelected(AppPage.CALENDAR)
              scope.launch {
                drawerState.close()
              }
            }
          )

          Text("other", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleMedium)
          NavigationDrawerItem(
            label = { Text("Todo") },
            selected = currentPage == AppPage.TODO,
            onClick = {
              onPageSelected(AppPage.TODO)
              scope.launch {
                drawerState.close()
              }
            }
          )
        }
      }
    }
  ) {
    content {
      scope.launch {
        drawerState.open()
      }
    }
  }
}