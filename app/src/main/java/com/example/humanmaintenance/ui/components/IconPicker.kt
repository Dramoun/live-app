package com.example.humanmaintenance.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun IconPicker(
  label: String,
  selected: AppIconType,
  onSelected: (AppIconType) -> Unit
) {
  Column {

    Text(label)

    Spacer(modifier = Modifier.height(8.dp))

    FlowRow(
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      AppIconType.entries.forEach { iconType ->

        val isSelected = iconType == selected

        Box(
          modifier = Modifier
            .padding(2.dp)
            .border(
              width = if (isSelected) 2.dp else 0.dp,
              color = MaterialTheme.colorScheme.primary,
              shape = CircleShape
            )
            .padding(2.dp)
            .clickable { onSelected(iconType) }
        ) {
          AppIcon(
            style = iconType.toStyle(),
            size = 40.dp,
            hasBorder = !isSelected
          )
        }
      }
    }
  }
}