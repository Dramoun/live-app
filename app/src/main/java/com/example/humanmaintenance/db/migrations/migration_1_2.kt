package com.example.humanmaintenance.db.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
  override fun migrate(db: SupportSQLiteDatabase) {
    db.execSQL("""
            CREATE TABLE IF NOT EXISTS finance_items (
                id TEXT NOT NULL,
                header TEXT NOT NULL,
                icon TEXT NOT NULL,
                category TEXT NOT NULL,
                priority TEXT NOT NULL,
                recurrence TEXT NOT NULL,
                amount INTEGER NOT NULL,
                PRIMARY KEY(id)
            )
        """.trimIndent())
  }
}