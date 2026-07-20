package com.example.humanmaintenance.ui.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.humanmaintenance.ui.map.NoteData
import com.example.humanmaintenance.ui.map.NoteGroupData
import com.example.humanmaintenance.ui.theme.AppColors

@Composable
fun NotesPage(
  modifier: Modifier = Modifier,
  noteGroups: List<NoteGroupData>,
  selectedGroupId: String,
  onNoteClick: (NoteData) -> Unit = {}
) {
  val selectedGroup = noteGroups.find { it.id == selectedGroupId } ?: return

  Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
    Spacer(Modifier.height(12.dp))

    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
      items(selectedGroup.notes) { note ->
        Card(
          modifier = Modifier.fillMaxWidth().clickable { onNoteClick(note) }
        ) {
          Text(
            text = note.text,
            color = AppColors.TextPrimary,
            modifier = Modifier.padding(12.dp)
          )
        }
      }
    }
  }
}