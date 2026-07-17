package com.example.humanmaintenance.db.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.humanmaintenance.db.repositories.NoteGroupRepository
import com.example.humanmaintenance.ui.map.NoteData
import com.example.humanmaintenance.ui.map.NoteGroupData
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NoteGroupViewModel(
  private val repository: NoteGroupRepository
) : ViewModel() {

  val noteGroups: StateFlow<List<NoteGroupData>> =
    repository.allGroups.stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5000),
      initialValue = emptyList()
    )

  fun addGroup(group: NoteGroupData) {
    viewModelScope.launch { repository.insertGroup(group) }
  }

  fun updateGroup(group: NoteGroupData) {
    viewModelScope.launch { repository.updateGroup(group) }
  }

  fun deleteGroup(group: NoteGroupData) {
    viewModelScope.launch { repository.deleteGroup(group) }
  }

  fun addNote(groupId: String, note: NoteData) {
    viewModelScope.launch { repository.addNote(groupId, note) }
  }

  fun updateNote(groupId: String, note: NoteData) {
    viewModelScope.launch { repository.updateNote(groupId, note) }
  }

  fun deleteNote(groupId: String, note: NoteData) {
    viewModelScope.launch { repository.deleteNote(groupId, note) }
  }
}

class NoteGroupViewModelFactory(
  private val repository: NoteGroupRepository
) : ViewModelProvider.Factory {
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    @Suppress("UNCHECKED_CAST")
    return NoteGroupViewModel(repository) as T
  }
}