package com.example.humanmaintenance.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.humanmaintenance.ui.theme.AppColors
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun DateTitleLarge(modifier: Modifier = Modifier) {
  val today = LocalDate.now()
  val dateFormatter = DateTimeFormatter.ofPattern("d MMM (M) yyyy")
  val dayFormatter = DateTimeFormatter.ofPattern("EEEE")

  val dateText = today.format(dateFormatter)
  val percent = (today.dayOfMonth * 100) / today.lengthOfMonth()
  val dateResult = "$dateText $percent%"

  val dayText = today.format(dayFormatter)
  val dayNumber = today.dayOfWeek.value
  val dayResult = "$dayText ($dayNumber)"

  Column(
    modifier = modifier.padding(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(
      text = dayResult,
      color = AppColors.TextPrimary
    )
    Text(
      text = dateResult,
      color = AppColors.TextSecondary
    )
  }
}
