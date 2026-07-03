package com.example.humanmaintenance.ui.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.humanmaintenance.ui.components.DateTitleLarge
import com.example.humanmaintenance.ui.components.FinanceItem
import com.example.humanmaintenance.ui.map.AppPage
import com.example.humanmaintenance.ui.map.FinanceItemData
import kotlin.collections.forEach

@Composable
fun FinanceItemsPage(
  items: List<FinanceItemData>,
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier.fillMaxSize().padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(12.dp)
  ) {
    items.forEach { item ->
      FinanceItem(
        icon = item.icon,
        header = item.header,
        category = item.category,
        priority = item.priority,
        recurrence = item.recurrence,
        amount = item.amount
      )
    }
  }
}
