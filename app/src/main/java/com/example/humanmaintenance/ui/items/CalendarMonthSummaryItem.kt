package com.example.humanmaintenance.ui.items

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.humanmaintenance.ui.components.PercentageBar
import com.example.humanmaintenance.ui.map.AppPage
import com.example.humanmaintenance.ui.map.CalendarItemData
import com.example.humanmaintenance.ui.theme.AppColors
import java.time.LocalDate


@Composable
fun CalendarMonthSummaryItem(
  modifier: Modifier = Modifier,
  date: LocalDate,
  calendarItems: List<CalendarItemData> = emptyList(),
  onPageSelected: (AppPage) -> Unit,
  onDateChange: (LocalDate) -> Unit = {},
) {
  val monthItems = calendarItems.filter {
    it.date.year == date.year &&
        it.date.month == date.month
  }

  val upcomingEvents = monthItems
    .sortedBy { it.date }
    .take(3)

  val daysInMonth = date.lengthOfMonth()

  val activeDays = monthItems
    .distinct()
    .count()

  val freeDays = daysInMonth - activeDays
  val totalEvents = monthItems.count()

  Row(
    modifier = modifier
      .fillMaxSize()
      .clip(RoundedCornerShape(16.dp))
      .background(AppColors.Surface)
      .padding(12.dp),
    horizontalArrangement = Arrangement.spacedBy(12.dp)
  ) {
    Column(
      modifier = Modifier.weight(1f),
      verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
      Text(
        text = "UPCOMING",
        color = AppColors.TextSecondary,
        style = MaterialTheme.typography.labelLarge,
        fontWeight = FontWeight.SemiBold
      )

      if (upcomingEvents.isEmpty()) {
        Text(
          text = "Nothing coming up",
          color = AppColors.TextSecondary,
          style = MaterialTheme.typography.bodySmall
        )
      } else {
        upcomingEvents.forEachIndexed { index, item ->
          CalendarUpcomingItem(
            modifier = Modifier.clickable {
              onDateChange(item.date)
              onPageSelected(AppPage.CALENDAR_DAY)
            },
            item = item
          )

          if (index < upcomingEvents.lastIndex) {
            HorizontalDivider(
              color = AppColors.SurfaceVariant,
              thickness = 1.dp
            )
          }
        }
      }
    }

    VerticalDivider(
      color = AppColors.SurfaceVariant,
      thickness = 1.dp
    )

    Column(
      modifier = Modifier.weight(1f),
      verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
      Text(
        text = "SUMMARY",
        color = AppColors.TextSecondary,
        style = MaterialTheme.typography.labelLarge,
        fontWeight = FontWeight.SemiBold
      )

      SummaryBox(
        freeDays = freeDays,
        totalDays = daysInMonth,
        totalEvents = totalEvents
      )
    }
  }
}

//TODO : add Active days X of Y
//TODO : add Total events Z
@Composable
fun SummaryBox(
  modifier: Modifier = Modifier,
  freeDays: Number,
  totalDays: Number,
  totalEvents: Number
) {
  Column(
    modifier = modifier
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 6.dp),
      verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
      Text(text = "Active days", color = AppColors.TextSecondary)

      PercentageBar(
        percentage = (freeDays.toFloat() / totalDays.toFloat())
      )
    }

    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 6.dp),
      verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
      Text(text = "Free days", color = AppColors.TextSecondary)
      Text(text = "$freeDays of $totalDays", color = AppColors.TextSecondary)
    }

    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 6.dp),
      verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
      Text(text = "Total events", color = AppColors.TextSecondary)
      Text(text = totalEvents.toString(), color = AppColors.TextSecondary)
    }
  }
}