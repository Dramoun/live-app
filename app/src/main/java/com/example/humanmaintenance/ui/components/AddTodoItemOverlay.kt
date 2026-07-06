package com.example.humanmaintenance.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.humanmaintenance.ui.map.Effort
import com.example.humanmaintenance.ui.map.TodoItemData
import com.example.humanmaintenance.ui.map.TodoPriority
import java.time.LocalDate

@Composable
fun AddTodoItemOverlay(
  updateItem: TodoItemData?,
  date: LocalDate = LocalDate.now(),
  onDismiss: () -> Unit,
  onAdd: (TodoItemData) -> Unit
) {
  var title by remember { mutableStateOf(updateItem?.title ?: "") }
  var description by remember { mutableStateOf(updateItem?.description ?: "") }
  var priority by remember { mutableStateOf(updateItem?.priorityBase ?: TodoPriority.NORMAL) }
  var effort by remember { mutableStateOf(updateItem?.effort ?: Effort.STANDARD) }

  Dialog(onDismissRequest = onDismiss) {
    Card(
      modifier = Modifier
        .fillMaxWidth(0.9f)
        .fillMaxHeight(0.85f)
    ) {
      Column(
        modifier = Modifier
          .padding(16.dp)
          .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
      ) {
        Text(if (updateItem != null) "Update Task" else "Add Task")

        OutlinedTextField(
          value = title,
          onValueChange = { title = it },
          label = { Text("Title") },
          modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
          value = description,
          onValueChange = { description = it },
          label = { Text("Description") },
          modifier = Modifier.fillMaxWidth()
        )

        ChipSelector(
          label = "Priority",
          entries = TodoPriority.entries.toList(),
          selected = priority,
          onSelected = { priority = it },
          text = { it.label }
        )

        ChipSelector(
          label = "Effort",
          entries = Effort.entries.toList(),
          selected = effort,
          onSelected = { effort = it },
          text = { it.label }
        )

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
          OutlinedButton(
            onClick = onDismiss,
            modifier = Modifier.weight(1f)
          ) {
            Text("Close")
          }

          Button(
            onClick = {
              val item = updateItem?.copy(
                title = title,
                description = description.ifBlank { null },
                priorityBase = priority,
                effort = effort
              ) ?: TodoItemData(
                title = title,
                description = description.ifBlank { null },
                priorityBase = priority,
                priorityActual = priority,
                effort = effort,
                date = date,
                pushedCount = 0,
                completed = false
              )
              onAdd(item)
            },
            enabled = title.isNotBlank(),
            modifier = Modifier.weight(1f)
          ) {
            Text("Save")
          }
        }
      }
    }
  }
}