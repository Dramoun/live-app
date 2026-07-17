package com.example.humanmaintenance.db.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = "note_groups")
data class NoteGroupEntity(
  @PrimaryKey val id: String,
  val title: String,
  val icon: String,
  val color: Long,
  val createdAt: String,
  val updatedAt: String
)

@Entity(
  tableName = "notes",
  foreignKeys = [
    ForeignKey(
      entity = NoteGroupEntity::class,
      parentColumns = ["id"],
      childColumns = ["groupId"],
      onDelete = ForeignKey.CASCADE
    )
  ],
  indices = [Index("groupId")]
)
data class NoteEntity(
  @PrimaryKey val id: String,
  val groupId: String,
  val text: String,
  val createdAt: String,
  val updatedAt: String
)