package com.example.humanmaintenance.ui.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.humanmaintenance.ui.components.DayTitle
import com.example.humanmaintenance.ui.items.TodoItem
import com.example.humanmaintenance.ui.map.TodoItemData
import com.example.humanmaintenance.ui.map.sortedTodoItems
import java.time.LocalDate

@Composable
fun TodoPage(
  modifier: Modifier = Modifier,
  date: LocalDate,
  onDateChange: (LocalDate) -> Unit = {},
  todoItems: List<TodoItemData> = emptyList(),
  onItemClick: (TodoItemData) -> Unit = {},
  onPushItem: (id: String) -> Unit = {},
  onSwitchComplete: (id: String) -> Unit = {}
) {
  // TODO: filtering can be done  off component for all pages like this
  val todayItems = sortedTodoItems(
    todoItems.filter { it.date == date }
  )

  Column(
    modifier = modifier
      .fillMaxSize()
      .padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(12.dp)
  ) {
    DayTitle(
      date = date,
      onDateChange = onDateChange,
      modifier = Modifier.clickable { onDateChange(LocalDate.now())}
    )

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
  modifier: Modifier = Modifier,
  items: List<TodoItemData>,
  pushItem: (id: String) -> Unit,
  switchComplete: (id: String) -> Unit,
  onItemClick: (TodoItemData) -> Unit = {},
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
