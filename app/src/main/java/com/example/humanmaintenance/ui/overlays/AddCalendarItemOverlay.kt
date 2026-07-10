package com.example.humanmaintenance.ui.overlays

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.humanmaintenance.ui.map.CalendarItemData
import com.example.humanmaintenance.ui.map.Tag
import com.example.humanmaintenance.ui.map.Type
import com.example.humanmaintenance.ui.components.AppIconType
import com.example.humanmaintenance.ui.components.ChipSelector
import com.example.humanmaintenance.ui.components.ChipSelectorMulti
import com.example.humanmaintenance.ui.components.ColorPicker
import com.example.humanmaintenance.ui.components.IconPicker
import com.example.humanmaintenance.ui.components.TimeInput
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

@Composable
fun AddCalendarItemOverlay(
  updateItem: CalendarItemData?,
  date: LocalDate = LocalDate.now(),
  onDismiss: () -> Unit,
  onAdd: (CalendarItemData) -> Unit
) {
  var title by remember { mutableStateOf(updateItem?.title ?: "") }
  var description by remember { mutableStateOf(updateItem?.description ?:"") }
  var itemDate by remember { mutableStateOf(updateItem?.date ?: date) }
  var startTime by remember { mutableStateOf(updateItem?.start ?:LocalTime.now())}
  var endTime by remember { mutableStateOf<LocalTime?>(updateItem?.end)}

  var type by remember { mutableStateOf(updateItem?.type ?: Type.EVENT) }
  var tags by remember { mutableStateOf(updateItem?.tags ?: setOf(Tag.IMPORTANT)) }
  var color by remember { mutableStateOf(updateItem?.color ?: Color(0xFF2196F3))}
  var icon by remember { mutableStateOf(updateItem?.icon ?: AppIconType.OTHER) }

  Dialog(
    onDismissRequest = onDismiss
  ) {
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
        Text(if (updateItem != null) "Update Item" else "Add Calendar Item")

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
          label = "Type",
          entries = Type.entries.toList(),
          selected = type,
          onSelected = { type = it },
          text = { it.label }
        )

        ChipSelectorMulti(
          label = "Tags",
          entries = Tag.entries,
          selected = tags,
          onSelectionChange = { tags = it },
          text = { it.label }
        )

        TimeInput(
          label = "Start Time",
          time = startTime,
          onTimeChange = {
            startTime = it ?: LocalTime.now().withSecond(0).withNano(0)
          }
        )

        TimeInput(
          label = "End Time",
          time = endTime,
          required = false,
          onTimeChange = {
            endTime = it
          }
        )

        ColorPicker(
          label = "Color",
          selected = color,
          onSelected = {
            color = it
          }
        )

        IconPicker(
          label = "Icon",
          selected = icon,
          onSelected = {
            icon = it
          }
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
              onAdd(
                CalendarItemData(
                  title = title,
                  description = description,
                  date = itemDate,
                  start = startTime,
                  end = endTime,
                  type = type,
                  tags = tags,
                  color = color,
                  icon = icon,
                  id = updateItem?.id ?: UUID.randomUUID().toString()
                )
              )
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