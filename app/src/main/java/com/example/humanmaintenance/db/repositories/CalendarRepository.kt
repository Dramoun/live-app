package com.example.humanmaintenance.db.repositories

import com.example.humanmaintenance.db.daos.CalendarDao
import com.example.humanmaintenance.db.mappers.toData
import com.example.humanmaintenance.db.mappers.toEntity
import com.example.humanmaintenance.ui.map.CalendarItemData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CalendarRepository(private val dao: CalendarDao) {

  val allItems: Flow<List<CalendarItemData>> =
    dao.getAll().map { entities -> entities.map { it.toData() } }

  suspend fun insert(item: CalendarItemData) {
    dao.insert(item.toEntity())
  }

  suspend fun update(item: CalendarItemData) {
    dao.update(item.toEntity())
  }

  suspend fun delete(item: CalendarItemData) {
    dao.delete(item.toEntity())
  }

  suspend fun getById(id: String): CalendarItemData? {
    return dao.getById(id)?.toData()
  }
}