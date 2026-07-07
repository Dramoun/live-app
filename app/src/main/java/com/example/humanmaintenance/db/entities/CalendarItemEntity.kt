package com.example.humanmaintenance.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "calendar_items")
data class CalendarItemEntity(
  @PrimaryKey val id: String,
  val title: String,
  val description: String?,
  val type: String,
  val tags: String,
  val date: String,
  val start: String,
  val end: String?,
  val color: Long,
  val icon: String
)