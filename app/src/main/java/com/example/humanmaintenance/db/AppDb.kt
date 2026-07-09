package com.example.humanmaintenance.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.humanmaintenance.db.daos.CalendarDao
import com.example.humanmaintenance.db.daos.FinanceDao
import com.example.humanmaintenance.db.entities.CalendarItemEntity
import com.example.humanmaintenance.db.entities.FinanceItemEntity

@Database(
  entities = [
    CalendarItemEntity::class,
    FinanceItemEntity::class
             ],
  version = 2,
  exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
  abstract fun calendarDao(): CalendarDao
  abstract fun financeDao(): FinanceDao
}