package com.example.humanmaintenance.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePickerDialog
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun <T> ChipSelector(
  label: String,
  entries: List<T>,
  selected: T,
  onSelected: (T) -> Unit,
  text: (T) -> String
) {
  Column {
    Text(label)

    FlowRow {
      entries.forEach { item ->
        val isSelected = item == selected

        AssistChip(
          onClick = { onSelected(item) },
          label = { Text(text(item)) },
          colors = AssistChipDefaults.assistChipColors(
            containerColor =
              if (isSelected) MaterialTheme.colorScheme.primaryContainer
              else MaterialTheme.colorScheme.surface
          )
        )
      }
    }
  }
}

@Composable
fun <T> ChipSelectorMulti(
  label: String,
  entries: List<T>,
  selected: Set<T>,
  onSelectionChange: (Set<T>) -> Unit,
  text: (T) -> String
) {
  Column {
    Text(label)

    FlowRow {
      entries.forEach { item ->
        val isSelected = item in selected

        AssistChip(
          onClick = {
            val newSet =
              if (isSelected) selected - item
              else selected + item

            onSelectionChange(newSet)
          },
          label = { Text(text(item)) },
          colors = AssistChipDefaults.assistChipColors(
            containerColor =
              if (isSelected) MaterialTheme.colorScheme.primaryContainer
              else MaterialTheme.colorScheme.surface
          )
        )
      }
    }
  }
}
