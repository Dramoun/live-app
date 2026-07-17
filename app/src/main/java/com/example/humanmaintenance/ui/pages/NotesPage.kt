package com.example.humanmaintenance.ui.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import com.example.humanmaintenance.ui.components.AppIcon
import com.example.humanmaintenance.ui.components.AppIconType
import com.example.humanmaintenance.ui.components.toStyle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.humanmaintenance.ui.map.NoteData
import com.example.humanmaintenance.ui.map.NoteGroupData
import com.example.humanmaintenance.ui.overlays.AddNoteOverlay

@Composable
fun NotesPage(
  modifier: Modifier = Modifier,
  noteGroups: List<NoteGroupData>,
  onGroupClick: (NoteGroupData) -> Unit = {}, // opens the existing edit-group overlay
  onAddNote: (groupId: String, NoteData) -> Unit = { _, _ -> },
  onUpdateNote: (groupId: String, NoteData) -> Unit = { _, _ -> },
  onDeleteNote: (groupId: String, NoteData) -> Unit = { _, _ -> }
) {
  var selectedGroupId by remember { mutableStateOf<String?>(null) }
  var editingNote by remember { mutableStateOf<NoteData?>(null) }
  var showAddNote by remember { mutableStateOf(false) }

  // look up from the live list each recomposition, so edits/adds reflect immediately
  val selectedGroup = noteGroups.find { it.id == selectedGroupId }

  if (selectedGroup == null) {
    LazyColumn(
      modifier = modifier.fillMaxSize().padding(16.dp),
      verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
      items(noteGroups) { group ->
        Card(modifier = Modifier.fillMaxWidth().clickable { selectedGroupId = group.id }) {
          Row(
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Column {
              Text(group.title)
              Text("${group.notes.size} notes")
            }
            TextButton(onClick = { onGroupClick(group) }) {
              Text("Edit")
            }
          }
        }
      }
    }
  } else {
    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        AppIcon(
          style = AppIconType.ARROW_LEFT.toStyle(),
          size = 36.dp,
          modifier = Modifier.clickable { selectedGroupId = null }
        )
        Text(selectedGroup.title, style = MaterialTheme.typography.titleLarge)
        AppIcon(
          style = AppIconType.ADD.toStyle(),
          size = 36.dp,
          modifier = Modifier.clickable {
            editingNote = null
            showAddNote = true
          }
        )
      }

      Spacer(Modifier.height(12.dp))

      LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(selectedGroup.notes) { note ->
          Card(
            modifier = Modifier.fillMaxWidth().clickable {
              editingNote = note
              showAddNote = true
            }
          ) {
            Text(note.text, modifier = Modifier.padding(12.dp))
          }
        }
      }
    }
  }

  if (showAddNote) {
    AddNoteOverlay(
      updateItem = editingNote,
      onDismiss = {
        showAddNote = false
        editingNote = null
      },
      onAdd = { note ->
        selectedGroupId?.let { gid ->
          if (editingNote != null) onUpdateNote(gid, note) else onAddNote(gid, note)
        }
        showAddNote = false
        editingNote = null
      },
      onDelete = { note ->
        selectedGroupId?.let { gid -> onDeleteNote(gid, note) }
        showAddNote = false
        editingNote = null
      }
    )
  }
}