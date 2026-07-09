package com.example.humanmaintenance.db.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.humanmaintenance.db.repositories.TodoRepository
import com.example.humanmaintenance.ui.map.TodoItemData
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TodoViewModel(
  private val repository: TodoRepository
) : ViewModel() {

  val todoItems: StateFlow<List<TodoItemData>> =
    repository.allItems.stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5000),
      initialValue = emptyList()
    )

  fun addItem(item: TodoItemData) {
    viewModelScope.launch {
      repository.insert(item)
    }
  }

  fun updateItem(item: TodoItemData) {
    viewModelScope.launch {
      repository.update(item)
    }
  }

  fun deleteItem(item: TodoItemData) {
    viewModelScope.launch {
      repository.delete(item)
    }
  }

  fun pushTodoItem(id: String) {
    viewModelScope.launch {
      repository.pushItem(id)
    }
  }

  fun toggleComplete(id: String) {
    viewModelScope.launch {
      repository.toggleComplete(id)
    }
  }
}

class TodoViewModelFactory(
  private val repository: TodoRepository
) : ViewModelProvider.Factory {
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    @Suppress("UNCHECKED_CAST")
    return TodoViewModel(repository) as T
  }
}