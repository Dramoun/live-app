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
fun DateTitleLarge(
  date: LocalDate,
  modifier: Modifier = Modifier
) {
  val dateFormatter = DateTimeFormatter.ofPattern("d MMM (M) yyyy")
  val dayFormatter = DateTimeFormatter.ofPattern("EEEE")

  val dateText = date.format(dateFormatter)
  val percent = (date.dayOfMonth * 100) / date.lengthOfMonth()
  val dateResult = "$dateText $percent%"

  val dayText = date.format(dayFormatter)
  val dayNumber = date.dayOfWeek.value
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

// TODO: refactor into
//  1. TodayDateTitle
//  2. MonthDateTitle
//  3. YearDateTitle (HARD MAYBE ON THIS ONE, save for later later)
//  also these should handle on clicks, like title on click, left onClick, right onClick
//  and also add the missing buttons as mentioned above