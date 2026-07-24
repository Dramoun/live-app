package com.example.humanmaintenance.ui.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.humanmaintenance.appPreprocess.EditingItem
import com.example.humanmaintenance.ui.map.AppPage
import com.example.humanmaintenance.ui.map.NoteGroupData
import com.example.humanmaintenance.ui.theme.AppColors
import kotlinx.coroutines.launch

@Composable
fun AppDrawer(
  currentPage: AppPage,
  topThreeNotes: List<NoteGroupData>,
  onPageSelected: (AppPage) -> Unit,
  onNoteGroupSelect: (groupId: String) -> Unit = {},
  content: @Composable (onMenuClick: () -> Unit) -> Unit
) {
  val drawerState = rememberDrawerState(DrawerValue.Closed)
  val scope = rememberCoroutineScope()

  val itemColors = NavigationDrawerItemDefaults.colors(
    selectedContainerColor = AppColors.SurfaceVariant,
    selectedTextColor = AppColors.TextPrimary,
    unselectedContainerColor = AppColors.Surface,
    unselectedTextColor = AppColors.TextSecondary,
  )

  ModalNavigationDrawer(
    drawerState = drawerState,
    drawerContent = {
      ModalDrawerSheet(
        modifier = Modifier.fillMaxWidth(0.7f),
        drawerContainerColor = AppColors.Surface,
        drawerContentColor = AppColors.TextPrimary,
      ) {
        Column(
          modifier = Modifier.padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
        ) {
          Spacer(Modifier.height(12.dp))
          Text(
            "Pages",
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.titleLarge,
            color = AppColors.TextPrimary
          )
          HorizontalDivider(color = AppColors.SurfaceVariant)

          Text(
            "Finance",
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.titleMedium,
            color = AppColors.TextPrimary
          )

          NavigationDrawerItem(
            label = { Text("Monthly Overview") },
            selected = currentPage == AppPage.FINANCE_OVERVIEW_MONTH,
            colors = itemColors,
            onClick = {
              onPageSelected(AppPage.FINANCE_OVERVIEW_MONTH)
              scope.launch {
                drawerState.close()
              }
            }
          )


          NavigationDrawerItem(
            label = { Text("Daily") },
            selected = currentPage == AppPage.FINANCE_ITEMS_DAY,
            colors = itemColors,
            onClick = {
              onPageSelected(AppPage.FINANCE_ITEMS_DAY)
              scope.launch {
                drawerState.close()
              }
            }
          )

          NavigationDrawerItem(
            label = { Text("Weekly") },
            selected = currentPage == AppPage.FINANCE_ITEMS_WEEK,
            colors = itemColors,
            onClick = {
              onPageSelected(AppPage.FINANCE_ITEMS_WEEK)
              scope.launch {
                drawerState.close()
              }
            }
          )

          NavigationDrawerItem(
            label = { Text("Monthly") },
            selected = currentPage == AppPage.FINANCE_ITEMS_MONTH,
            colors = itemColors,
            onClick = {
              onPageSelected(AppPage.FINANCE_ITEMS_MONTH)
              scope.launch {
                drawerState.close()
              }
            }
          )

          Text(
            "Calendar",
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.titleMedium,
            color = AppColors.TextPrimary
          )
          NavigationDrawerItem(
            label = { Text("Daily") },
            selected = currentPage == AppPage.CALENDAR_DAY,
            colors = itemColors,
            onClick = {
              onPageSelected(AppPage.CALENDAR_DAY)
              scope.launch {
                drawerState.close()
              }
            }
          )
          NavigationDrawerItem(
            label = { Text("Month") },
            selected = currentPage == AppPage.CALENDAR_MONTH,
            colors = itemColors,
            onClick = {
              onPageSelected(AppPage.CALENDAR_MONTH)
              scope.launch {
                drawerState.close()
              }
            }
          )

          if (topThreeNotes.isNotEmpty()) {
            Text(
              "Recent Notes",
              modifier = Modifier.padding(8.dp),
              style = MaterialTheme.typography.titleMedium,
              color = AppColors.TextPrimary
            )

            topThreeNotes.forEach { group ->
              NavigationDrawerItem(
                label = { Text(group.title) },
                selected = false,
                colors = itemColors,
                onClick = {
                  onNoteGroupSelect(group.id)
                  scope.launch { drawerState.close() }
                }
              )
            }
          }

          Text(
            "Other",
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.titleMedium,
            color = AppColors.TextPrimary
          )

          NavigationDrawerItem(
            label = { Text("Notes") },
            selected = currentPage == AppPage.NOTE_GROUPS,
            colors = itemColors,
            onClick = {
              onPageSelected(AppPage.NOTE_GROUPS)
              scope.launch {
                drawerState.close()
              }
            }
          )

          NavigationDrawerItem(
            label = { Text("Todo") },
            selected = currentPage == AppPage.TODO,
            colors = itemColors,
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