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
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.humanmaintenance.ui.map.NoteData
import java.time.LocalDateTime
import java.util.UUID

@Composable
fun AddNoteOverlay(
  updateItem: NoteData?,
  onDismiss: () -> Unit,
  onDelete: (NoteData) -> Unit,
  onAdd: (NoteData) -> Unit
) {
  var text by remember { mutableStateOf(updateItem?.text ?: "") }

  Dialog(onDismissRequest = onDismiss) {
    Card {
      Column(
        modifier = Modifier
          .padding(16.dp)
          .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
      ) {
        Text(if (updateItem != null) "Update Note" else "Add Note")

        OutlinedTextField(
          value = text,
          onValueChange = { text = it },
          label = { Text("Note") },
          minLines = 3,
          modifier = Modifier.fillMaxWidth()
        )

        OverLayFooter(
          itemData = NoteData(
            text = text,
            createdAt = updateItem?.createdAt ?: LocalDateTime.now(),
            updatedAt = LocalDateTime.now(),
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