package com.example.humanmaintenance.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.humanmaintenance.db.daos.CalendarDao
import com.example.humanmaintenance.db.daos.FinanceDao
import com.example.humanmaintenance.db.daos.NoteDao
import com.example.humanmaintenance.db.daos.NoteGroupDao
import com.example.humanmaintenance.db.daos.TodoDao
import com.example.humanmaintenance.db.entities.CalendarItemEntity
import com.example.humanmaintenance.db.entities.FinanceItemEntity
import com.example.humanmaintenance.db.entities.NoteEntity
import com.example.humanmaintenance.db.entities.NoteGroupEntity
import com.example.humanmaintenance.db.entities.TodoItemEntity

@Database(
  entities = [
    CalendarItemEntity::class,
    FinanceItemEntity::class,
    TodoItemEntity::class,
    NoteGroupEntity::class,
    NoteEntity::class
             ],
  version = 6,
  exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
  abstract fun calendarDao(): CalendarDao
  abstract fun financeDao(): FinanceDao
  abstract fun todoDao(): TodoDao
  abstract fun noteGroupDao(): NoteGroupDao
  abstract fun noteDao(): NoteDao
}