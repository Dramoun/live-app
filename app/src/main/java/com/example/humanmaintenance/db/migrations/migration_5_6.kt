package com.example.humanmaintenance.db.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_5_6 = object : Migration(5, 6) {
  override fun migrate(db: SupportSQLiteDatabase) {
    db.execSQL("""
      CREATE TABLE IF NOT EXISTS note_groups (
        id TEXT NOT NULL PRIMARY KEY,
        title TEXT NOT NULL,
        icon TEXT NOT NULL,
        color INTEGER NOT NULL,
        createdAt TEXT NOT NULL,
        updatedAt TEXT NOT NULL
      )
    """.trimIndent())

    db.execSQL("""
      CREATE TABLE IF NOT EXISTS notes (
        id TEXT NOT NULL PRIMARY KEY,
        groupId TEXT NOT NULL,
        text TEXT NOT NULL,
        createdAt TEXT NOT NULL,
        updatedAt TEXT NOT NULL,
        FOREIGN KEY (groupId) REFERENCES note_groups(id) ON DELETE CASCADE
      )
    """.trimIndent())

    db.execSQL("""
      CREATE INDEX IF NOT EXISTS index_notes_groupId ON notes(groupId)
    """.trimIndent())
  }
}