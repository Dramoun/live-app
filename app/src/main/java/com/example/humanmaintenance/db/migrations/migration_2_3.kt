package com.example.humanmaintenance.db.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_2_3 = object : Migration(2, 3) {
  override fun migrate(db: SupportSQLiteDatabase) {
    db.execSQL("""
        CREATE TABLE IF NOT EXISTS todo_items (
            id TEXT NOT NULL,
            title TEXT NOT NULL,
            description TEXT,
            priorityBase TEXT NOT NULL,
            priorityActual TEXT NOT NULL,
            effort TEXT NOT NULL,
            date TEXT NOT NULL,
            pushedCount INTEGER NOT NULL,
            completed INTEGER NOT NULL,
            PRIMARY KEY(id)
        )
    """.trimIndent())
  }
}