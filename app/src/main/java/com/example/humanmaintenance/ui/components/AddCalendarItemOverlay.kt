package com.example.humanmaintenance.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import java.time.LocalDate
import java.time.LocalTime

@Composable
fun AddCalendarItemOverlay(
  updateItem: CalendarItemData?,
  onDismiss: () -> Unit,
  onAdd: (CalendarItemData) -> Unit
) {
  var title by remember { mutableStateOf(updateItem?.title ?: "") }
  var description by remember { mutableStateOf(updateItem?.description ?:"") }
  var date by remember { mutableStateOf(updateItem?.date ?:LocalDate.now()) }
  var startTime by remember { mutableStateOf(updateItem?.start ?:LocalTime.now())}
  var endTime by remember { mutableStateOf<LocalTime?>(updateItem?.end)}

  var type by remember { mutableStateOf(updateItem?.type ?: Type.EVENT) }
  var tags by remember { mutableStateOf(updateItem?.tags ?: setOf(Tag.IMPORTANT)) }
  var color by remember { mutableStateOf(updateItem?.color ?: Color(0xFF2196F3))}

  Dialog(
    onDismissRequest = onDismiss
  ) {
    Card(
      modifier = Modifier.fillMaxWidth(0.9f)
    ) {
      Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
      ) {
        Text(if (updateItem != null) "Update Item" else "Add Calendar Item")

        Button(
          onClick = onDismiss,
          modifier = Modifier.fillMaxWidth()
        ) {
          Text("Close")
        }
      }
    }
  }
}