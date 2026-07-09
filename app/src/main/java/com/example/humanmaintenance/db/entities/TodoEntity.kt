package com.example.humanmaintenance.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_items")
data class TodoItemEntity(
  @PrimaryKey val id: String,
  val title: String,
  val description: String?,
  val priorityBase: String,
  val priorityActual: String,
  val effort: String,
  val date: String,
  val pushedCount: Int,
  val completed: Boolean,
)