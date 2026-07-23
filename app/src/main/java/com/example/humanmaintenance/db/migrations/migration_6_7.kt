package com.example.humanmaintenance.db.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_6_7 = object : Migration(6, 7) {
  override fun migrate(db: SupportSQLiteDatabase) {
    db.execSQL("""
            ALTER TABLE finance_items
            ADD COLUMN type TEXT NOT NULL DEFAULT 'OTHER'
        """.trimIndent())
  }
}