package com.example.humanmaintenance.ui.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.humanmaintenance.ui.items.FinanceItem
import com.example.humanmaintenance.ui.components.toStyle
import com.example.humanmaintenance.ui.map.FinanceItemData
import kotlin.collections.forEach

@Composable
fun FinanceItemsPage(
  items: List<FinanceItemData>,
  onItemClick: (FinanceItemData) -> Unit = {},
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier.fillMaxSize().padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(12.dp)
  ) {
    items.forEach { item ->
      FinanceItem(
        icon = item.icon.toStyle(),
        header = item.header,
        category = item.category,
        priority = item.priority,
        recurrence = item.recurrence,
        amount = item.amount,
        modifier = Modifier.clickable{onItemClick(item)}
      )
    }
  }
}
