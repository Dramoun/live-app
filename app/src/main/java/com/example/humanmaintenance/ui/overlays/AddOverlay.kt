package com.example.humanmaintenance.ui.overlays

import androidx.compose.runtime.Composable
import com.example.humanmaintenance.appPreprocess.EditingItem
import com.example.humanmaintenance.ui.map.AppPage
import com.example.humanmaintenance.ui.map.CalendarItemData
import com.example.humanmaintenance.ui.map.FinanceItemData
import com.example.humanmaintenance.ui.map.NoteGroupData
import com.example.humanmaintenance.ui.map.TodoItemData
import java.time.LocalDate

@Composable
fun AddSheet(
  currentPage: AppPage,
  editingItem: EditingItem,
  selectedNoteGroupId: String?,
  date: LocalDate = LocalDate.now(),
  onDismiss: () -> Unit,
  onSave: (EditingItem, isUpdate: Boolean) -> Unit,
  onDelete: (EditingItem) -> Unit
) {
  when (currentPage) {
    AppPage.FINANCE_ITEMS_DAY, AppPage.FINANCE_ITEMS_WEEK, AppPage.FINANCE_ITEMS_MONTH -> {
      val existing = (editingItem as? EditingItem.Finance)?.item
      AddFinanceItemOverlay(
        updateItem = existing,
        date = date,
        onDismiss = onDismiss,
        onDelete = { onDelete(EditingItem.Finance(it)) },
        onAdd = { onSave(EditingItem.Finance(it), existing != null) }
      )
    }
    AppPage.CALENDAR_DAY -> {
      val existing = (editingItem as? EditingItem.Calendar)?.item
      AddCalendarItemOverlay(
        updateItem = existing,
        date = date,
        onDismiss = onDismiss,
        onDelete = { onDelete(EditingItem.Calendar(it)) },
        onAdd = { onSave(EditingItem.Calendar(it), existing != null) }
      )
    }
    AppPage.CALENDAR_MONTH,
    AppPage.FINANCE_OVERVIEW_MONTH -> null

    AppPage.TODO -> {
      val existing = (editingItem as? EditingItem.Todo)?.item
      AddTodoItemOverlay(
        updateItem = existing,
        date = date,
        onDismiss = onDismiss,
        onDelete = { onDelete(EditingItem.Todo(it)) },
        onAdd = { onSave(EditingItem.Todo(it), existing != null) }
      )
    }
    AppPage.NOTE_GROUPS -> {
      val existing = (editingItem as? EditingItem.NoteGroup)?.item
      AddNoteGroupOverlay(
        updateItem = existing,
        onDismiss = onDismiss,
        onDelete = { onDelete(EditingItem.NoteGroup(it)) },
        onAdd = { onSave(EditingItem.NoteGroup(it), existing != null) }
      )
    }
    AppPage.NOTES -> {
      val existingNote = (editingItem as? EditingItem.Note)?.item
      val groupId = (editingItem as? EditingItem.Note)?.groupId ?: selectedNoteGroupId
      if (groupId != null) {
        AddNoteOverlay(
          updateItem = existingNote,
          onDismiss = onDismiss,
          onDelete = { onDelete(EditingItem.Note(groupId, it)) },
          onAdd = { onSave(EditingItem.Note(groupId, it), existingNote != null) }
        )
      }
    }
  }
}