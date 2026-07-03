package com.example.humanmaintenance.ui.pages

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.humanmaintenance.ui.map.AppPage
import com.example.humanmaintenance.ui.map.FinanceItemData

@Composable
fun MainScreen(
  financeItems: List<FinanceItemData>,
  currentPage: AppPage,
  modifier: Modifier = Modifier
) {
  when (currentPage) {
    AppPage.CALENDAR ->
      CalendarDayPage(modifier = modifier)

    AppPage.FINANCE_ITEMS ->
      FinanceItemsPage(
        items = financeItems,
        modifier = modifier
      )
  }
}