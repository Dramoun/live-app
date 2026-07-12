package com.example.humanmaintenance.ui.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.humanmaintenance.ui.components.AppIcon
import com.example.humanmaintenance.ui.components.AppIcons
import com.example.humanmaintenance.ui.components.DayTitleLarge
import java.time.LocalDate

@Composable
fun BaseDayPage(
  modifier: Modifier = Modifier,
  date: LocalDate,
  onDateChange: (LocalDate) -> Unit = {},
  content: @Composable () -> Unit = {}
) {
  Column(
    modifier = modifier.fillMaxSize().padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(12.dp)
  ) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      IconButton(onClick = { onDateChange(date.minusDays(1)) }) {
        AppIcon(
          style = AppIcons.PreviousDay(),
          size = 32.dp,
          hasBorder = false
        )
      }

      DayTitleLarge(date = date, modifier = Modifier.clickable { onDateChange(LocalDate.now())})

      IconButton(onClick = { onDateChange(date.plusDays(1)) }) {
        AppIcon(
          style = AppIcons.NextDay(),
          size = 32.dp,
          hasBorder = false
        )
      }
    }

    content()
  }
}