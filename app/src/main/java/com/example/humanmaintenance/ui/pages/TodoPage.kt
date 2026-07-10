package com.example.humanmaintenance.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import com.example.humanmaintenance.ui.components.AppIcon
import com.example.humanmaintenance.ui.components.AppIcons
import com.example.humanmaintenance.ui.components.DateTitleLarge
import com.example.humanmaintenance.ui.items.TodoItem
import com.example.humanmaintenance.ui.map.CompletedColor
import com.example.humanmaintenance.ui.map.TodoItemData
import com.example.humanmaintenance.ui.map.priorityDisplayLabel
import com.example.humanmaintenance.ui.map.sortedTodoItems
import java.time.LocalDate

@Composable
fun TodoPage(
  date: LocalDate,
  onDateChange: (LocalDate) -> Unit = {},
  todoItems: List<TodoItemData> = emptyList(),
  onItemClick: (TodoItemData) -> Unit = {},
  onPushItem: (id: String) -> Unit = {},
  onSwitchComplete: (id: String) -> Unit = {},
  modifier: Modifier = Modifier
) {
  val todayItems = sortedTodoItems(
    todoItems.filter { it.date == date }
  )

  Column(
    modifier = modifier
      .fillMaxSize()
      .padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(12.dp)
  ) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      IconButton(onClick = { onDateChange(date.minusDays(1)) }) {
        AppIcon(
          style = AppIcons.PreviousDay(),
          size = 32.dp,
          hasBorder = false
        )
      }

      DateTitleLarge(date = date, modifier = Modifier.clickable { onDateChange(LocalDate.now())})

      IconButton(onClick = { onDateChange(date.plusDays(1)) }) {
        AppIcon(
          style = AppIcons.NextDay(),
          size = 32.dp,
          hasBorder = false
        )
      }
    }

    TodoItemsLayer(
      items = todayItems,
      pushItem = onPushItem,
      switchComplete = onSwitchComplete,
      onItemClick = onItemClick,
      modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
    )
  }
}

@Composable
fun TodoItemsLayer(
  items: List<TodoItemData>,
  pushItem: (id: String) -> Unit,
  switchComplete: (id: String) -> Unit,
  onItemClick: (TodoItemData) -> Unit = {},
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(12.dp)
  ) {
    items.forEach { item ->
      TodoItem(
        data = item,
        pushItem = pushItem,
        switchComplete = switchComplete,
        onClick = { onItemClick(item) }
      )
    }
  }
}
