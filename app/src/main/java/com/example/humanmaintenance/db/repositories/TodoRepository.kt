package com.example.humanmaintenance.db.repositories

import com.example.humanmaintenance.db.daos.TodoDao
import com.example.humanmaintenance.db.mappers.toData
import com.example.humanmaintenance.db.mappers.toEntity
import com.example.humanmaintenance.ui.map.TodoItemData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

class TodoRepository(private val dao: TodoDao) {

  val allItems: Flow<List<TodoItemData>> =
    dao.getAll().map { entities ->
      entities.map { it.toData() }
    }

  suspend fun insert(item: TodoItemData) {
    dao.insert(item.toEntity())
  }

  suspend fun update(item: TodoItemData) {
    dao.update(item.toEntity())
  }

  suspend fun delete(item: TodoItemData) {
    dao.delete(item.toEntity())
  }

  suspend fun getById(id: String): TodoItemData? {
    return dao.getById(id)?.toData()
  }

  suspend fun pushItem(id: String) {
    val item = dao.getById(id) ?: return

    dao.update(
      item.copy(
        date = LocalDate.parse(item.date)
          .plusDays(1)
          .toString(),

        pushedCount = item.pushedCount + 1
      )
    )
  }

  suspend fun toggleComplete(id: String) {
    val item = dao.getById(id) ?: return

    dao.update(
      item.copy(
        completed = !item.completed
      )
    )
  }
}