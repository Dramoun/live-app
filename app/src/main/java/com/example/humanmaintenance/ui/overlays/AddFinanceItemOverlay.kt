package com.example.humanmaintenance.ui.overlays

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import com.example.humanmaintenance.ui.components.DateField
import com.example.humanmaintenance.ui.components.DateRangeField
import com.example.humanmaintenance.ui.map.Category
import com.example.humanmaintenance.ui.map.FinanceItemData
import com.example.humanmaintenance.ui.map.Priority
import com.example.humanmaintenance.ui.map.Recurrence
import java.time.LocalDate
import java.util.UUID


@Composable
fun AddFinanceItemOverlay(
  updateItem: FinanceItemData?,
  date: LocalDate,
  onDismiss: () -> Unit,
  onDelete: (FinanceItemData) -> Unit,
  onAdd: (FinanceItemData) -> Unit
) {
  var title by remember { mutableStateOf(updateItem?.header ?:"") }
  var amount by remember { mutableStateOf(updateItem?.amount?.toString() ?: "") }
  var initialDate by remember { mutableStateOf(updateItem?.initialDate ?: date)}
  var endDate by remember { mutableStateOf(updateItem?.endDate)}

  var category by remember { mutableStateOf(updateItem?.category ?: Category.EXPENSE) }
  var priority by remember { mutableStateOf(updateItem?.priority ?: Priority.ESSENTIAL) }
  var recurrence by remember { mutableStateOf(updateItem?.recurrence ?: Recurrence.MONTHLY) }

  val effectiveEndDate = if (recurrence == Recurrence.ONE_TIME) initialDate else endDate

  Dialog(onDismissRequest = onDismiss) {
    Card {
      Column(
        modifier = Modifier
          .padding(16.dp)
          .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
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

        if (recurrence == Recurrence.ONE_TIME) {
          DateField(
            label = "Date",
            date = initialDate,
            onDateChange = { initialDate = it }
          )
        } else {
          DateRangeField(
            startDate = initialDate,
            endDate = effectiveEndDate,
            onRangeChange = { start, end ->
              initialDate = start
              endDate = end
            }
          )
        }
      }

      OverLayFooter(
        itemData = FinanceItemData(
          header = title,
          icon = when (category) {
            Category.INCOME -> AppIconType.INCOME
            Category.EXPENSE -> AppIconType.EXPENSE
          },
          category = category,
          priority = priority,
          recurrence = recurrence,
          amount = amount.toIntOrNull() ?: 0,
          id = updateItem?.id ?: UUID.randomUUID().toString(),
          initialDate = initialDate,
          endDate = effectiveEndDate
        ),
        itemExists = updateItem != null,
        onDismiss = onDismiss,
        onDelete = onDelete,
        onAdd = onAdd
      )
    }
  }
}
