package com.example.humanmaintenance.db.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.humanmaintenance.db.entities.CalendarItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CalendarDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert(item: CalendarItemEntity)

  @Update
  suspend fun update(item: CalendarItemEntity)

  @Delete
  suspend fun delete(item: CalendarItemEntity)

  @Query("SELECT * FROM calendar_items WHERE id = :id")
  suspend fun getById(id: String): CalendarItemEntity?

  @Query("SELECT * FROM calendar_items ORDER BY date, start")
  fun getAll(): Flow<List<CalendarItemEntity>>
}