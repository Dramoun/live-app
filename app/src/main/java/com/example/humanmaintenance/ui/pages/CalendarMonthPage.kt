package com.example.humanmaintenance.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.humanmaintenance.ui.components.MonthTitle
import com.example.humanmaintenance.ui.items.CalendarCell
import com.example.humanmaintenance.ui.items.CalendarMonthSummaryItem
import com.example.humanmaintenance.ui.map.AppPage
import com.example.humanmaintenance.ui.map.CalendarItemData
import com.example.humanmaintenance.ui.theme.AppColors
import java.time.LocalDate


data class GridData(
  val date: LocalDate,
  val eventNum: Int,
  val isCurrentMonth: Boolean
)

@Composable
fun CalendarMonthPage(
  modifier: Modifier = Modifier,
  date: LocalDate,
  onDateChange: (LocalDate) -> Unit = {},
  calendarItems: List<CalendarItemData> = emptyList(),
  onPageSelected: (AppPage) -> Unit,
) {
  val daysOfWeek = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
  val today = LocalDate.now()

  val monthItems = calendarItems.filter {
    it.date.year == date.year && it.date.month == date.month
  }
  val eventsByDate = monthItems.groupingBy { it.date }.eachCount()

  val firstDayOfMonth = date.withDayOfMonth(1)
  val daysBack = (firstDayOfMonth.dayOfWeek.value - 1).toLong()
  val firstGridDay = firstDayOfMonth.minusDays(daysBack)

  val gridItems = (0 until 42).map { offset ->
    val day = firstGridDay.plusDays(offset.toLong())
    GridData(
      date = day,
      eventNum = eventsByDate[day] ?: 0,
      isCurrentMonth = day.month == date.month
    )
  }

  Column(
    modifier = modifier.fillMaxSize().padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(12.dp)
  ) {
    MonthTitle(
      date = date,
      onDateChange = onDateChange,
      modifier = Modifier.clickable { onDateChange(LocalDate.now())}
    )

    Column(
      modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(16.dp))
        .background(AppColors.Surface)
        .padding(12.dp),
      verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      // Weekday header
      Row(modifier = Modifier.fillMaxWidth()) {
        daysOfWeek.forEach { day ->
          Text(
            text = day,
            color = AppColors.TextSecondary,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
          )
        }
      }

      // Day grid
      repeat(6) { week ->
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
          repeat(7) { day ->
            val item = gridItems[week * 7 + day]

            CalendarCell(
              modifier = Modifier
                .weight(1f)
                .aspectRatio(1f)
                .clip(RoundedCornerShape(10.dp))
                .clickable {
                  onDateChange(item.date)
                  onPageSelected(AppPage.CALENDAR_DAY)
                },
              dayOfMonth = item.date.dayOfMonth,
              numOfEvents = item.eventNum,
              isCurrentMonth = item.isCurrentMonth,
              isToday = item.date == today,
            )
          }
        }
      }
    }

    Box(
      modifier = Modifier
        .fillMaxSize()
        .clip(RoundedCornerShape(12.dp))
        .background(AppColors.Surface)
    ) {
      CalendarMonthSummaryItem(
        date = date,
        calendarItems = calendarItems
      )
    }


    //TODO: maybe add summary display, depends if we have space!
  }
}