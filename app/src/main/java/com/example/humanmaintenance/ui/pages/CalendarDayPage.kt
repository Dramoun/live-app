package com.example.humanmaintenance.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.humanmaintenance.ui.components.AppIcon
import com.example.humanmaintenance.ui.components.AppIcons
import com.example.humanmaintenance.ui.components.DateTitleLarge
import com.example.humanmaintenance.ui.components.toStyle
import com.example.humanmaintenance.ui.map.CalendarItemData
import com.example.humanmaintenance.ui.theme.AppColors
import java.time.LocalDate

private val GutterWidth = 56.dp

@Composable
fun CalendarDayPage(
  date: LocalDate,
  onDateChange: (LocalDate) -> Unit = {},
  calendarItems: List<CalendarItemData> = emptyList(),
  onItemClick: (CalendarItemData) -> Unit = {},
  modifier: Modifier = Modifier
) {
  val hourHeight = 120

  val dayItems = calendarItems.filter { it.date == date }

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

      DateTitleLarge(date = date, modifier = Modifier.clickable { onDateChange(LocalDate.now())})

      IconButton(onClick = { onDateChange(date.plusDays(1)) }) {
        AppIcon(
          style = AppIcons.NextDay(),
          size = 32.dp,
          hasBorder = false
        )
      }
    }

    Box(
      modifier = Modifier
        .fillMaxSize()
    ) {
      val scrollState = rememberScrollState()

      Box(
        modifier = Modifier
          .verticalScroll(scrollState)
          .height((24 * hourHeight).dp)
          .fillMaxSize()
      ) {
        TimeBackground(hourHeight.dp)
        EventLayer(items = dayItems, hourHeight = hourHeight.dp, onItemClick = onItemClick)
      }
    }
  }
}

@Composable
fun TimeBackground(hourHeight: Dp) {
  Column {

    repeat(24) { hour ->

      Box(
        modifier = Modifier
          .fillMaxWidth()
          .height(hourHeight)
      ) {

        HorizontalDivider()

        Text(
          text = "%02d:00".format(hour),
          modifier = Modifier.padding(8.dp)
        )
      }
    }
  }
}

@Composable
fun EventLayer(
  items: List<CalendarItemData>,
  hourHeight: Dp,
  onItemClick: (CalendarItemData) -> Unit = {},
  modifier: Modifier = Modifier
) {
  Box(
    modifier = modifier
      .fillMaxWidth()
      .height(hourHeight * 24)
  ) {
    items.forEach { item ->
      val startFraction = item.start.hour + item.start.minute / 60f
      val endFraction = item.end?.let { it.hour + it.minute / 60f } ?: (startFraction + 0.5f)
      val durationHours = (endFraction - startFraction).coerceAtLeast(0.5f)

      Box(
        modifier = Modifier
          .padding(start = GutterWidth, end = 8.dp)
          .offset(y = hourHeight * startFraction)
          .fillMaxWidth()
          .height(hourHeight * durationHours)
          .background(item.color.copy(alpha = 0.25f), RoundedCornerShape(8.dp))
          .border(1.dp, item.color, RoundedCornerShape(8.dp))
          .clickable { onItemClick(item) }
          .padding(6.dp)
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          AppIcon(
            style = item.icon.toStyle(),
            size = 20.dp,
            hasBorder = false
          )

          Column(
            modifier = Modifier.weight(1f)
          ) {
            Text(
              text = item.title,
              maxLines = 1,
              overflow = TextOverflow.Ellipsis,
              fontSize = 12.sp,
              color = AppColors.TextPrimary
            )

            Row(
              horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
              Text(
                text = item.type.label,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 10.sp,
                color = AppColors.TextSecondary
              )

              if (item.tags.isNotEmpty()) {
                Text(
                  text = "•",
                  fontSize = 10.sp,
                  color = AppColors.TextSecondary
                )
              }

              item.tags.forEach { tag ->
                Text(
                  text = tag.label,
                  maxLines = 1,
                  overflow = TextOverflow.Ellipsis,
                  fontSize = 10.sp,
                  color = AppColors.TextSecondary
                )
              }
            }
          }
        }
      }
    }
  }
}