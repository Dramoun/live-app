package com.example.humanmaintenance.ui.items

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import com.example.humanmaintenance.ui.components.AppIcon
import com.example.humanmaintenance.ui.components.AppIcons
import com.example.humanmaintenance.ui.map.CompletedColor
import com.example.humanmaintenance.ui.map.TodoItemData
import com.example.humanmaintenance.ui.map.priorityDisplayLabel

@Composable
fun TodoItem(
  modifier: Modifier = Modifier,
  data: TodoItemData,
  pushItem: (id: String) -> Unit,
  switchComplete: (id: String) -> Unit,
  onClick: () -> Unit = {}
) {
  val cardBackground = MaterialTheme.colorScheme.surface
  val borderWidth = 2.dp
  val cornerRadius = 12.dp

  val leftAccent = if (data.completed) CompletedColor else data.priorityActual.color
  val rightAccent = if (data.completed) CompletedColor else data.effort.color

  val leftShape = RoundedCornerShape(topStart = cornerRadius, bottomStart = cornerRadius)
  val rightShape = RoundedCornerShape(topEnd = cornerRadius, bottomEnd = cornerRadius)

  Row(
    modifier = modifier
      .fillMaxWidth()
      .height(IntrinsicSize.Min)
      .clickable(onClick = onClick)
  ) {
    // Left "card" — accent color as outer fill, inset content box hides the touching edge
    Box(
      modifier = Modifier
        .weight(0.3f)
        .fillMaxHeight()
        .clip(leftShape)
        .background(leftAccent)
    ) {
      Box(
        modifier = Modifier
          .fillMaxSize()
          .padding(start = borderWidth, top = borderWidth, bottom = borderWidth)
          .clip(leftShape)
          .background(cardBackground)
          .padding(horizontal = 6.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Checkbox(
            checked = data.completed,
            onCheckedChange = { switchComplete(data.id) },
            modifier = Modifier.scale(0.85f)
          )
          Spacer(Modifier.width(4.dp))
          Text(priorityDisplayLabel(data), color = data.priorityActual.color)
        }
      }
    }

    // Right "card" — same trick, mirrored
    Box(
      modifier = Modifier
        .weight(0.7f)
        .fillMaxHeight()
        .clip(rightShape)
        .background(rightAccent)
    ) {
      Box(
        modifier = Modifier
          .fillMaxSize()
          .padding(end = borderWidth, top = borderWidth, bottom = borderWidth)
          .clip(rightShape)
          .background(cardBackground)
          .padding(12.dp)
      ) {
        Row(
          modifier = Modifier.fillMaxSize(),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Column {
            Text(data.title)
            Text(data.effort.label, color = data.effort.color)
          }

          IconButton(onClick = { pushItem(data.id) }) {
            AppIcon(
              style = AppIcons.NextDay(),
              size = 32.dp,
              hasBorder = false
            )
          }
        }
      }
    }
  }
}
