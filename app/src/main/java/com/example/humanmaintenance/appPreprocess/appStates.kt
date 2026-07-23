package com.example.humanmaintenance.appPreprocess

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.humanmaintenance.ui.map.AppPage
import com.example.humanmaintenance.ui.map.CalendarItemData
import com.example.humanmaintenance.ui.map.FinanceItemData
import com.example.humanmaintenance.ui.map.NoteData
import com.example.humanmaintenance.ui.map.NoteGroupData
import com.example.humanmaintenance.ui.map.TodoItemData
import java.time.LocalDate

sealed interface EditingItem {
  data class Finance(val item: FinanceItemData) : EditingItem
  data class Calendar(val item: CalendarItemData) : EditingItem
  data class Todo(val item: TodoItemData) : EditingItem
  data class NoteGroup(val item: NoteGroupData) : EditingItem
  data class Note(val groupId: String, val item: NoteData) : EditingItem
  object None : EditingItem
}

class AppUiState {
  var currentPage by mutableStateOf(AppPage.TODO)
  var date by mutableStateOf(LocalDate.now())
  var selectedNoteGroupId by mutableStateOf<String?>(null)
  var showAddSheet by mutableStateOf(false)
  var editingItem: EditingItem by mutableStateOf(EditingItem.None)

  fun openAdd() {
    editingItem = EditingItem.None
    showAddSheet = true
  }
  fun openEdit(item: EditingItem) {
    editingItem = item
    showAddSheet = true
  }
  fun closeSheet() {
    showAddSheet = false
    editingItem = EditingItem.None
  }
}

@Composable
fun rememberAppUiState() = remember { AppUiState() }
