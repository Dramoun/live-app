package com.example.humanmaintenance.db.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.humanmaintenance.db.entities.FinanceItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FinanceDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert(item: FinanceItemEntity)

  @Update
  suspend fun update(item: FinanceItemEntity)

  @Delete
  suspend fun delete(item: FinanceItemEntity)

  @Query("SELECT * FROM finance_items WHERE id = :id")
  suspend fun getById(id: String): FinanceItemEntity?

  @Query("SELECT * FROM finance_items")
  fun getAll(): Flow<List<FinanceItemEntity>>
}