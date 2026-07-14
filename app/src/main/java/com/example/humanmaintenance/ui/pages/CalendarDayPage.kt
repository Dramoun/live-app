package com.example.humanmaintenance.ui.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.humanmaintenance.ui.components.DayTitle
import com.example.humanmaintenance.ui.items.CalendarItem
import com.example.humanmaintenance.ui.map.CalendarItemData
import java.time.LocalDate


@Composable
fun CalendarDayPage(
  modifier: Modifier = Modifier,
  date: LocalDate,
  onDateChange: (LocalDate) -> Unit = {},
  calendarItems: List<CalendarItemData> = emptyList(),
  onItemClick: (CalendarItemData) -> Unit = {}
) {
  val hourHeight = 120

  val dayItems = calendarItems
    .filter { it.date == date }
    .sortedBy { it.start }

  Column(
    modifier = modifier.fillMaxSize().padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(12.dp)
  ) {
    DayTitle(
      date = date,
      onDateChange = onDateChange,
      modifier = Modifier.clickable { onDateChange(LocalDate.now())}
    )

    val scrollState = rememberScrollState()

    val density = LocalDensity.current
    val hourHeightPx = with(density) { hourHeight.dp.toPx() }

    LaunchedEffect(date) {
      val firstItem = dayItems.firstOrNull()

      val targetPx = if (firstItem != null) {
        (firstItem.start.hour + firstItem.start.minute / 60f) * hourHeightPx
      } else {
        (24 * hourHeightPx) / 2
      }

      scrollState.animateScrollTo(targetPx.toInt())
    }

    Box(
      modifier = Modifier
        .verticalScroll(scrollState)
        .height((24 * hourHeight).dp) // 24 * 120 = 2880
        .fillMaxSize()
    ) {
      TimeBackground(hourHeight.dp)
      EventLayer(items = dayItems, hourHeight = hourHeight.dp, onItemClick = onItemClick)
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
  modifier: Modifier = Modifier,
  items: List<CalendarItemData>,
  hourHeight: Dp,
  onItemClick: (CalendarItemData) -> Unit = {},
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

      CalendarItem(
        item = item,
        hourHeight = hourHeight,
        startFraction = startFraction,
        durationHours = durationHours,
        onItemClick = onItemClick,
      )
    }
  }
}