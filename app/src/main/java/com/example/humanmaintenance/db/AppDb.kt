package com.example.humanmaintenance.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.humanmaintenance.db.daos.CalendarDao
import com.example.humanmaintenance.db.daos.FinanceDao
import com.example.humanmaintenance.db.daos.TodoDao
import com.example.humanmaintenance.db.entities.CalendarItemEntity
import com.example.humanmaintenance.db.entities.FinanceItemEntity
import com.example.humanmaintenance.db.entities.TodoItemEntity

@Database(
  entities = [
    CalendarItemEntity::class,
    FinanceItemEntity::class,
    TodoItemEntity::class
             ],
  version = 3,
  exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
  abstract fun calendarDao(): CalendarDao
  abstract fun financeDao(): FinanceDao
  abstract fun todoDao(): TodoDao
}