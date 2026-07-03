package com.example.humanmaintenance.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private val PresetColors = listOf(
  Color(0xFFF44336),
  Color(0xFFE91E63),
  Color(0xFF9C27B0),
  Color(0xFF673AB7),
  Color(0xFF3F51B5),
  Color(0xFF2196F3),
  Color(0xFF03A9F4),
  Color(0xFF00BCD4),
  Color(0xFF009688),
  Color(0xFF4CAF50),
  Color(0xFF8BC34A),
  Color(0xFFFFEB3B),
  Color(0xFFFF9800),
  Color(0xFFFF5722),
  Color(0xFF795548),
  Color(0xFF607D8B)
)

@Composable
fun ColorPicker(
  label: String,
  selected: Color,
  onSelected: (Color) -> Unit
) {
  Column {

    Text(label)

    Spacer(modifier = Modifier.height(8.dp))

    FlowRow(
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      PresetColors.forEach { color ->

        val isSelected = color == selected

        Box(
          modifier = Modifier
              .size(if (isSelected) 44.dp else 36.dp)
              .clip(CircleShape)
              .background(color)
              .border(
                  width = if (isSelected) 3.dp else 1.dp,
                  color = if (isSelected)
                      MaterialTheme.colorScheme.primary
                  else
                      Color.LightGray,
                  shape = CircleShape
              )
              .clickable {
                  onSelected(color)
              }
        )
      }
    }
  }
}