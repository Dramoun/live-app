package com.example.humanmaintenance.ui.components

import android.text.Layout
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.humanmaintenance.ui.map.AppPage
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
  page: AppPage,
  onMenuClick: () -> Unit
) {
  val today = remember { LocalDate.now() }
  val formatter = remember { DateTimeFormatter.ofPattern("EEE d MMM") }

  CenterAlignedTopAppBar(
    title = {
      Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
      ){
        if (page == AppPage.FINANCE_ITEMS) {
          Text(
            text = "${today.format(formatter)} (${today.monthValue}) ${today.year}",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.align(Alignment.CenterHorizontally)
          )
        }
        Text(page.label)
      }
    },
    navigationIcon = {
      IconButton(onClick = onMenuClick) {
        AppIcon(AppIcons.Menu())
      }
    },
    // TODO: no feature currently here but button is ready
    actions = {
      IconButton(onClick = { }) {
        AppIcon(AppIcons.More())
      }
    }
  )
}