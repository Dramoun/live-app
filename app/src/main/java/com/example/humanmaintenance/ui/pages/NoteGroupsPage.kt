package com.example.humanmaintenance.ui.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.humanmaintenance.ui.components.AppIcon
import com.example.humanmaintenance.ui.components.toStyle
import com.example.humanmaintenance.ui.map.NoteGroupData
import com.example.humanmaintenance.ui.theme.AppColors

@Composable
fun NoteGroupsPage(
  modifier: Modifier = Modifier,
  noteGroups: List<NoteGroupData>,
  onGroupClick: (NoteGroupData) -> Unit = {},
  onGroupSelect: (groupId: String) -> Unit = {}
) {
  val sortedGroups = noteGroups.sortedByDescending { it.updatedAt }

  LazyColumn(
    modifier = modifier.fillMaxSize().padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(12.dp)
  ) {
    items(sortedGroups) { group ->
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .clickable { onGroupSelect(group.id) },
        colors = CardDefaults.cardColors(
          containerColor = group.color.copy(alpha = 0.5f)
        )
      ) {
        Row(
          modifier = Modifier.fillMaxWidth().padding(12.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            AppIcon(
              backgroundColor = AppColors.Background,
              iconColor = AppColors.Success,
              style = group.icon.toStyle()
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
              Text(text = group.title, style = MaterialTheme.typography.titleMedium)
              Text(text = "${group.notes.size} notes", style = MaterialTheme.typography.bodySmall)
            }
          }

          TextButton(onClick = { onGroupClick(group) }) {
            Text("Edit")
          }
        }
      }
    }
  }
}