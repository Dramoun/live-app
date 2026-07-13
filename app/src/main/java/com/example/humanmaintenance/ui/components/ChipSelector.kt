package com.example.humanmaintenance.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

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

    FlowRow(
      horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
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

    FlowRow (
      horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
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
