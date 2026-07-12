package com.example.humanmaintenance.db.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_3_4 = object : Migration(1, 2) {
  override fun migrate(db: SupportSQLiteDatabase) {
    db.execSQL("""
            ALTER TABLE finance_items
            ADD COLUMN initialDate TEXT NOT NULL DEFAULT ''
        """.trimIndent())
  }
}