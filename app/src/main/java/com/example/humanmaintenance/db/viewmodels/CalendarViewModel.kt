package com.example.humanmaintenance.db.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.humanmaintenance.db.repositories.CalendarRepository
import com.example.humanmaintenance.ui.map.CalendarItemData
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CalendarViewModel(
  private val repository: CalendarRepository
) : ViewModel() {

  val calendarItems: StateFlow<List<CalendarItemData>> =
    repository.allItems.stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5000),
      initialValue = emptyList()
    )

  fun addItem(item: CalendarItemData) {
    viewModelScope.launch {
      repository.insert(item)
    }
  }

  fun updateItem(item: CalendarItemData) {
    viewModelScope.launch {
      repository.update(item)
    }
  }

  fun deleteItem(item: CalendarItemData) {
    viewModelScope.launch {
      repository.delete(item)
    }
  }
}

class CalendarViewModelFactory(
  private val repository: CalendarRepository
) : ViewModelProvider.Factory {
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    @Suppress("UNCHECKED_CAST")
    return CalendarViewModel(repository) as T
  }
}