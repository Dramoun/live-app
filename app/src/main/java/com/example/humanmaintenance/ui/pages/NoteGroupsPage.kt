package com.example.humanmaintenance.ui.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.humanmaintenance.ui.map.NoteGroupData

@Composable
fun NotesPage(
  modifier: Modifier = Modifier,
  noteGroups: List<NoteGroupData>,
  onGroupClick: (NoteGroupData) -> Unit = {}
) {
  LazyColumn(
    modifier = modifier.fillMaxSize().padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(12.dp)
  ) {
    items(noteGroups) { group ->
      Card(modifier = Modifier.fillMaxWidth().clickable { onGroupClick(group) }) {
        Column(modifier = Modifier.padding(12.dp)) {
          Text(group.title)
          Text("${group.notes.size} notes")
        }
      }
    }
  }
}