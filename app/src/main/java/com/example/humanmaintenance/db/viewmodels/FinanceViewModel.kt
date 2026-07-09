package com.example.humanmaintenance.db.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.humanmaintenance.db.repositories.FinanceRepository
import com.example.humanmaintenance.ui.map.FinanceItemData
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FinanceViewModel(
  private val repository: FinanceRepository
) : ViewModel() {

  val financeItems: StateFlow<List<FinanceItemData>> =
    repository.allItems.stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5000),
      initialValue = emptyList()
    )

  fun addItem(item: FinanceItemData) {
    viewModelScope.launch {
      repository.insert(item)
    }
  }

  fun updateItem(item: FinanceItemData) {
    viewModelScope.launch {
      repository.update(item)
    }
  }

  fun deleteItem(item: FinanceItemData) {
    viewModelScope.launch {
      repository.delete(item)
    }
  }
}

class FinanceViewModelFactory(
  private val repository: FinanceRepository
) : ViewModelProvider.Factory {
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    @Suppress("UNCHECKED_CAST")
    return FinanceViewModel(repository) as T
  }
}