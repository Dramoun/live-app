package com.example.humanmaintenance.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.humanmaintenance.ui.map.CalendarItemData

@Composable
fun AddCalendarItemOverlay(
  onDismiss: () -> Unit,
  onAdd: (CalendarItemData) -> Unit
) {
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
        Text("Add Calendar Item")

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