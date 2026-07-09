package com.example.humanmaintenance.db.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.humanmaintenance.db.entities.TodoItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert(item: TodoItemEntity)

  @Update
  suspend fun update(item: TodoItemEntity)

  @Delete
  suspend fun delete(item: TodoItemEntity)

  @Query("SELECT * FROM todo_items WHERE id = :id")
  suspend fun getById(id: String): TodoItemEntity?

  @Query("SELECT * FROM todo_items")
  fun getAll(): Flow<List<TodoItemEntity>>
}