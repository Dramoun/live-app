package com.example.humanmaintenance.ui.overlays

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.humanmaintenance.ui.components.AppIconType
import com.example.humanmaintenance.ui.components.ChipSelector
import com.example.humanmaintenance.ui.map.Category
import com.example.humanmaintenance.ui.map.FinanceItemData
import com.example.humanmaintenance.ui.map.Priority
import com.example.humanmaintenance.ui.map.Recurrence
import java.util.UUID


@Composable
fun AddFinanceItemOverlay(
  updateItem: FinanceItemData?,
  onDismiss: () -> Unit,
  onAdd: (FinanceItemData) -> Unit
) {
  var title by remember { mutableStateOf(updateItem?.header ?:"") }
  var amount by remember { mutableStateOf(updateItem?.amount.toString()) }

  var category by remember { mutableStateOf(updateItem?.category ?: Category.EXPENSE) }
  var priority by remember { mutableStateOf(updateItem?.priority ?: Priority.ESSENTIAL) }
  var recurrence by remember { mutableStateOf(updateItem?.recurrence ?: Recurrence.MONTHLY) }

  Dialog(onDismissRequest = onDismiss) {
    Card {
      Column(
        modifier = Modifier
          .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
      ) {
        Text(if (updateItem != null) "Update Item" else "Add Item")

        OutlinedTextField(
          value = title,
          onValueChange = { title = it },
          label = { Text("Name") },
          modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
          value = amount,
          onValueChange = { amount = it },
          label = { Text("Amount") },
          modifier = Modifier.fillMaxWidth(),
          keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
          )
        )

        ChipSelector(
          label = "Category",
          entries = Category.entries.toList(),
          selected = category,
          onSelected = { category = it },
          text = { it.label }
        )

        ChipSelector(
          label = "Priority",
          entries = Priority.entries.toList(),
          selected = priority,
          onSelected = { priority = it },
          text = { it.label }
        )

        ChipSelector(
          label = "Recurrence",
          entries = Recurrence.entries.toList(),
          selected = recurrence,
          onSelected = { recurrence = it },
          text = { it.label }
        )
      }

      Row(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        horizontalArrangement = Arrangement.End
      ) {
        TextButton(onClick = onDismiss) {
          Text("Cancel")
        }

        Button(
          onClick = {
            onAdd(
              FinanceItemData(
                header = title,
                icon = when (category) {
                  Category.INCOME -> AppIconType.INCOME
                  Category.EXPENSE -> AppIconType.EXPENSE
                },
                category = category,
                priority = priority,
                recurrence = recurrence,
                amount = amount.toIntOrNull() ?: 0,
                id = updateItem?.id ?: UUID.randomUUID().toString()
              )
            )
          }
        ) {
          Text("Save")
        }
      }
    }
  }
}
