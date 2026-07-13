package com.example.humanmaintenance.ui.pages

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.humanmaintenance.ui.map.AppPage
import com.example.humanmaintenance.ui.map.CalendarItemData
import com.example.humanmaintenance.ui.map.FinanceItemData
import com.example.humanmaintenance.ui.map.TodoItemData
import java.time.LocalDate

@Composable
fun MainScreen(
  modifier: Modifier = Modifier,
  calendarItems: List<CalendarItemData>,
  financeItems: List<FinanceItemData>,
  todoItems: List<TodoItemData>,
  currentPage: AppPage,
  date: LocalDate,
  onDateChange: (LocalDate) -> Unit = {},
  onFinanceItemClick: (FinanceItemData) -> Unit = {},
  onCalendarItemClick: (CalendarItemData) -> Unit = {},
  onTodoItemClick: (TodoItemData) -> Unit = {},
  onPushTodoItem: (id: String) -> Unit = {},
  onSwitchTodoComplete: (id: String) -> Unit = {},
  onPageSelected: (AppPage) -> Unit,
) {
  when (currentPage) {
    AppPage.CALENDAR_DAY ->
      CalendarDayPage(
        date = date,
        onDateChange = onDateChange,
        calendarItems = calendarItems,
        onItemClick = onCalendarItemClick,
        modifier = modifier
      )

    AppPage.CALENDAR_MONTH ->
      CalendarMonthPage(
        date = date,
        onDateChange = onDateChange,
        calendarItems = calendarItems,
        onPageSelected = onPageSelected,
        modifier = modifier
      )

    AppPage.FINANCE_ITEMS ->
      FinanceItemsPage(
        date = date,
        financeItems = financeItems,
        onDateChange = onDateChange,
        onItemClick = onFinanceItemClick,
        modifier = modifier
      )

    AppPage.TODO ->
      TodoPage(
        date = date,
        onDateChange = onDateChange,
        todoItems = todoItems,
        onItemClick = onTodoItemClick,
        onPushItem = onPushTodoItem,
        onSwitchComplete = onSwitchTodoComplete,
        modifier = modifier
      )
  }
}