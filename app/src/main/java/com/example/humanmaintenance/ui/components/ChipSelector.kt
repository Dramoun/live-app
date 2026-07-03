package com.example.humanmaintenance.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

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