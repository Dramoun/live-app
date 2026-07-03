package com.example.humanmaintenance.ui.pages

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.humanmaintenance.ui.map.AppPage
import com.example.humanmaintenance.ui.map.CalendarItemData
import com.example.humanmaintenance.ui.map.FinanceItemData
import java.time.LocalDate

@Composable
fun MainScreen(
  calendarItems: List<CalendarItemData>,
  financeItems: List<FinanceItemData>,
  currentPage: AppPage,
  date: LocalDate,
  onDateChange: (LocalDate) -> Unit = {},
  onCalendarItemClick: (CalendarItemData) -> Unit = {},
  modifier: Modifier = Modifier
) {
  when (currentPage) {
    AppPage.CALENDAR ->
      CalendarDayPage(
        date = date,
        onDateChange = onDateChange,
        calendarItems = calendarItems,
        onItemClick = onCalendarItemClick,
        modifier = modifier)

    AppPage.FINANCE_ITEMS ->
      FinanceItemsPage(
        items = financeItems,
        modifier = modifier
      )
  }
}