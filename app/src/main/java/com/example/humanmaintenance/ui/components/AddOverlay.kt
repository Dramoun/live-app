package com.example.humanmaintenance.ui.components

import androidx.compose.runtime.Composable
import com.example.humanmaintenance.ui.map.AppPage
import com.example.humanmaintenance.ui.map.CalendarItemData
import com.example.humanmaintenance.ui.map.FinanceItemData


@Composable
fun AddSheet(
  currentPage: AppPage,
  onDismiss: () -> Unit,
  onAddFinance: (FinanceItemData) -> Unit,
  onAddCalendar: (CalendarItemData) -> Unit
) {
  when (currentPage) {
    AppPage.FINANCE_ITEMS -> {
      AddFinanceItemOverlay(
        onDismiss = onDismiss,
        onAdd = onAddFinance
      )
    }

    AppPage.CALENDAR -> {
      AddCalendarItemOverlay(
        onDismiss = onDismiss,
        onAdd = onAddCalendar
      )
    }
  }
}


