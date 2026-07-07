package com.example.humanmaintenance.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.humanmaintenance.db.daos.CalendarDao
import com.example.humanmaintenance.db.entities.CalendarItemEntity

@Database(
  entities = [CalendarItemEntity::class],
  version = 1,
  exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
  abstract fun calendarDao(): CalendarDao
}