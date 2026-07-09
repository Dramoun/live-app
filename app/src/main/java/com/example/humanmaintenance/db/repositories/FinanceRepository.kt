package com.example.humanmaintenance.db.repositories

import com.example.humanmaintenance.db.daos.FinanceDao
import com.example.humanmaintenance.db.mappers.toData
import com.example.humanmaintenance.db.mappers.toEntity
import com.example.humanmaintenance.ui.map.FinanceItemData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FinanceRepository(private val dao: FinanceDao) {

  val allItems: Flow<List<FinanceItemData>> =
    dao.getAll().map { entities -> entities.map { it.toData() } }

  suspend fun insert(item: FinanceItemData) {
    dao.insert(item.toEntity())
  }

  suspend fun update(item: FinanceItemData) {
    dao.update(item.toEntity())
  }

  suspend fun delete(item: FinanceItemData) {
    dao.delete(item.toEntity())
  }

  suspend fun getById(id: String): FinanceItemData? {
    return dao.getById(id)?.toData()
  }
}