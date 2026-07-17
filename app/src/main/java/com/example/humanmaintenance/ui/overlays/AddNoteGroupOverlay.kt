package com.example.humanmaintenance.ui.overlays

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.humanmaintenance.ui.components.AppIconType
import com.example.humanmaintenance.ui.components.ColorPicker
import com.example.humanmaintenance.ui.components.IconPicker
import com.example.humanmaintenance.ui.map.NoteGroupData
import java.util.UUID

@Composable
fun AddNoteGroupOverlay(
  updateItem: NoteGroupData?,
  onDismiss: () -> Unit,
  onDelete: (NoteGroupData) -> Unit,
  onAdd: (NoteGroupData) -> Unit
) {
  var title by remember { mutableStateOf(updateItem?.title ?: "") }
  var color by remember { mutableStateOf(updateItem?.color ?: Color(0xFF2196F3)) }
  var icon by remember { mutableStateOf(updateItem?.icon ?: AppIconType.OTHER) }

  Dialog(onDismissRequest = onDismiss) {
    Card {
      Column(
        modifier = Modifier
          .padding(16.dp)
          .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
      ) {
        Text(if (updateItem != null) "Update Note Group" else "Add Note Group")

        OutlinedTextField(
          value = title,
          onValueChange = { title = it },
          label = { Text("Title") },
          modifier = Modifier.fillMaxWidth()
        )

        ColorPicker(label = "Color", selected = color, onSelected = { color = it })

        IconPicker(label = "Icon", selected = icon, onSelected = { icon = it })

        OverLayFooter(
          itemData = NoteGroupData(
            title = title,
            color = color,
            icon = icon,
            notes = updateItem?.notes ?: emptyList(),
            id = updateItem?.id ?: UUID.randomUUID().toString()
          ),
          itemExists = updateItem != null,
          onDismiss = onDismiss,
          onDelete = onDelete,
          onAdd = onAdd
        )
      }
    }
  }
}