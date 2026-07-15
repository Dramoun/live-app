package com.example.humanmaintenance.ui.overlays

import androidx.compose.runtime.Composable
import com.example.humanmaintenance.ui.map.AppPage
import com.example.humanmaintenance.ui.map.CalendarItemData
import com.example.humanmaintenance.ui.map.FinanceItemData
import com.example.humanmaintenance.ui.map.TodoItemData
import java.time.LocalDate

@Composable
fun AddSheet(
  currentPage: AppPage,
  updateFinanceItem: FinanceItemData? = null,
  updateCalendarItem: CalendarItemData? = null,
  updateTodoItem: TodoItemData? = null,
  date: LocalDate = LocalDate.now(),
  onDismiss: () -> Unit,
  onAddFinance: (FinanceItemData) -> Unit,
  onFinanceDelete: (FinanceItemData) -> Unit,
  onAddCalendar: (CalendarItemData) -> Unit,
  onCalendarDelete: (CalendarItemData) -> Unit,
  onAddTodo: (TodoItemData) -> Unit,
  onTodoDelete: (TodoItemData) -> Unit
) {
  when (currentPage) {

    AppPage.FINANCE_ITEMS_DAY,
    AppPage.FINANCE_ITEMS_WEEK,
    AppPage.FINANCE_ITEMS_MONTH-> {
      AddFinanceItemOverlay(
        updateItem = updateFinanceItem,
        date = date,
        onDismiss = onDismiss,
        onDelete = onFinanceDelete,
        onAdd = onAddFinance
      )
    }

    AppPage.CALENDAR_DAY -> {
      AddCalendarItemOverlay(
        updateItem = updateCalendarItem,
        date = date,
        onDismiss = onDismiss,
        onDelete = onCalendarDelete,
        onAdd = onAddCalendar
      )
    }

    AppPage.CALENDAR_MONTH -> null

    AppPage.TODO -> {
      AddTodoItemOverlay(
        updateItem = updateTodoItem,
        date = date,
        onDismiss = onDismiss,
        onDelete = onTodoDelete,
        onAdd = onAddTodo
      )
    }
  }
}