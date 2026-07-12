package com.example.humanmaintenance.ui.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.humanmaintenance.ui.components.DayTitle
import com.example.humanmaintenance.ui.items.FinanceItem
import com.example.humanmaintenance.ui.components.toStyle
import com.example.humanmaintenance.ui.map.FinanceItemData
import java.time.LocalDate
import kotlin.collections.forEach


/** For more items then 50 like year or month views
 * import androidx.compose.foundation.lazy.LazyColumn
 * import androidx.compose.foundation.lazy.items
 *
 * LazyColumn(
 *     modifier = Modifier
 *         .weight(1f)
 *         .fillMaxWidth(),
 *     verticalArrangement = Arrangement.spacedBy(12.dp)
 * ) {
 *     items(
 *         items = dayItems,
 *         key = { it.id }   // if your FinanceItemData has a unique id
 *     ) { item ->
 *         FinanceItem(
 *             icon = item.icon.toStyle(),
 *             header = item.header,
 *             category = item.category,
 *             priority = item.priority,
 *             recurrence = item.recurrence,
 *             amount = item.amount,
 *             modifier = Modifier.clickable {
 *                 onItemClick(item)
 *             }
 *         )
 *     }
 * }
 */
@Composable
fun FinanceItemsPage(
  modifier: Modifier = Modifier,
  date: LocalDate,
  onDateChange: (LocalDate) -> Unit = {},
  financeItems: List<FinanceItemData>,
  onItemClick: (FinanceItemData) -> Unit = {},
) {
  val dayItems = financeItems.filter { it.initialDate == date }

  Column(
    modifier = modifier
      .fillMaxSize()
      .padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(12.dp)
  ) {
    DayTitle(
      date = date,
      onDateChange = onDateChange,
      modifier = Modifier.clickable { onDateChange(LocalDate.now()) }
    )

    Column(
      modifier = Modifier
        .weight(1f)
        .fillMaxWidth()
        .verticalScroll(rememberScrollState()),
      verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
      dayItems.forEach { item ->
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
  }
}