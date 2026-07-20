package com.example.humanmaintenance.ui.navigation

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import com.example.humanmaintenance.ui.components.AppIcon
import com.example.humanmaintenance.ui.components.AppIcons
import com.example.humanmaintenance.ui.map.AppPage
import com.example.humanmaintenance.ui.theme.AppColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
  page: AppPage,
  notesGroupName: String?,
  onMenuClick: () -> Unit
) {
  CenterAlignedTopAppBar(
    title = {
      Text(
        text = if (page == AppPage.NOTES) {
          notesGroupName ?: page.label
        } else {
          page.label
        },
        color = AppColors.TextPrimary
      )},
    navigationIcon = {
      IconButton(onClick = onMenuClick) {
        AppIcon(style = AppIcons.Menu())
      }
    },
    // TODO: no feature currently here but button is ready
    actions = {
      IconButton(onClick = { }) {
        AppIcon(style = AppIcons.More())
      }
    },
    colors = TopAppBarDefaults.topAppBarColors(
      containerColor = AppColors.Background,
      scrolledContainerColor = AppColors.Background,
      navigationIconContentColor = AppColors.TextPrimary,
      titleContentColor = AppColors.TextPrimary,
      actionIconContentColor = AppColors.TextPrimary
    )
  )
}