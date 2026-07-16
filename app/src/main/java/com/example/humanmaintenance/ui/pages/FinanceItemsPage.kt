package com.example.humanmaintenance.ui.pages

import android.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.humanmaintenance.ui.components.AppIconType
import com.example.humanmaintenance.ui.components.AppIcons
import com.example.humanmaintenance.ui.components.CircleCheckBox
import com.example.humanmaintenance.ui.components.DayTitle
import com.example.humanmaintenance.ui.components.MonthTitle
import com.example.humanmaintenance.ui.components.WeekTitle
import com.example.humanmaintenance.ui.components.YearTitle
import com.example.humanmaintenance.ui.items.FinanceItem
import com.example.humanmaintenance.ui.components.toStyle
import com.example.humanmaintenance.ui.map.FinanceItemData
import com.example.humanmaintenance.ui.map.FinanceViewMode
import com.example.humanmaintenance.ui.map.Recurrence
import java.time.DayOfWeek
import java.time.LocalDate
import kotlin.collections.forEach

private val Recurrence.sortRank: Int
  get() = when (this) {
    Recurrence.YEARLY -> 0
    Recurrence.MONTHLY -> 1
    Recurrence.WEEKLY -> 2
    Recurrence.ONE_TIME -> 3
  }

@Composable
fun FinanceItemsPage(
  modifier: Modifier = Modifier,
  date: LocalDate,
  onDateChange: (LocalDate) -> Unit = {},
  financeItems: List<FinanceItemData>,
  onItemClick: (FinanceItemData) -> Unit = {},
  viewMode: FinanceViewMode
) {
  var yearFilter by remember { mutableStateOf(true) }
  var monthFilter by remember { mutableStateOf(true) }
  var weekFilter by remember { mutableStateOf(true) }
  var oneFilter by remember { mutableStateOf(true) }

  val displayItems = when (viewMode) {
    FinanceViewMode.DAY -> financeItems.filter {
      it.overlaps(date, date)
    }

    FinanceViewMode.WEEK -> {
      val start = date.with(DayOfWeek.MONDAY)
      financeItems.filter {
        it.overlaps(start, start.plusDays(6))
      }
    }

    FinanceViewMode.MONTH -> {
      val start = date.withDayOfMonth(1)
      financeItems.filter {
        it.overlaps(start, start.withDayOfMonth(start.lengthOfMonth()))
      }
    }

    FinanceViewMode.YEAR -> {
      val start = date.withDayOfYear(1)
      financeItems.filter {
        it.overlaps(start, start.withDayOfYear(start.lengthOfYear()))
      }
    }
  }.asSequence()
    .filter { yearFilter || it.recurrence != Recurrence.YEARLY }
    .filter { monthFilter || it.recurrence != Recurrence.MONTHLY }
    .filter { weekFilter || it.recurrence != Recurrence.WEEKLY }
    .filter { oneFilter || it.recurrence != Recurrence.ONE_TIME }
    .sortedBy { it.recurrence.sortRank }
    .toList()

  Column(
    modifier = modifier
      .fillMaxSize()
      .padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(12.dp)
  ) {
    when (viewMode) {
      FinanceViewMode.DAY -> {
        DayTitle(
          date = date,
          onDateChange = onDateChange,
          modifier = Modifier.clickable { onDateChange(LocalDate.now()) }
        )
      }

      FinanceViewMode.WEEK -> {
        WeekTitle(
          date = date,
          onDateChange = onDateChange,
          modifier = Modifier.clickable { onDateChange(LocalDate.now()) }
        )
      }

      FinanceViewMode.MONTH -> {
        MonthTitle(
          date = date,
          onDateChange = onDateChange,
          modifier = Modifier.clickable { onDateChange(LocalDate.now()) }
        )
      }

      FinanceViewMode.YEAR -> {
        YearTitle(
          date = date,
          onDateChange = onDateChange,
          modifier = Modifier.clickable { onDateChange(LocalDate.now()) }
        )
      }
    }

    Column(
      modifier = Modifier
        .weight(1f)
        .fillMaxWidth()
        .verticalScroll(rememberScrollState()),
      verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

      displayItems.forEach { item ->
        FinanceItem(
          icon = item.icon.toStyle(),
          header = item.header,
          category = item.category,
          priority = item.priority,
          recurrence = item.recurrence,
          amount = item.amount,
          modifier = Modifier.clickable { onItemClick(item) }
        )
      }
    }

    Row(
      modifier = Modifier
        .padding(start = 106.dp, bottom = 12.dp, end = 12.dp),
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      CircleCheckBox(
        state = yearFilter,
        style = AppIconType.RECURRENCE_YEARLY.toStyle(),
        size = 35.dp,
        onStateChange = { yearFilter = it }
      )
      CircleCheckBox(
        state = monthFilter,
        style = AppIconType.RECURRENCE_MONTHLY.toStyle(),
        size = 35.dp,
        onStateChange = { monthFilter = it }
      )
      CircleCheckBox(
        state = weekFilter,
        style = AppIconType.RECURRENCE_WEEKLY.toStyle(),
        size = 35.dp,
        onStateChange = { weekFilter = it }
      )
      CircleCheckBox(
        state = oneFilter,
        style = AppIconType.RECURRENCE_ONCE.toStyle(),
        size = 35.dp,
        onStateChange = { oneFilter = it }
      )
    }
  }
}

fun FinanceItemData.overlaps(start: LocalDate, end: LocalDate): Boolean {
  return initialDate <= end &&
      (endDate == null || endDate >= start)
}
