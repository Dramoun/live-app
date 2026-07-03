package com.example.humanmaintenance.ui.map

import androidx.compose.ui.graphics.Color
import com.example.humanmaintenance.ui.components.AppIconType
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID


enum class Type(val label: String) {
  EVENT("Event"),
  NOTE("Note"),
  TASK("Task")
}
enum class Tag(val label: String) {
  BIRTHDAY("Birthday"),
  HOLIDAY("Holiday"),
  IMPORTANT("Important")
}

data class CalendarItemData(
  val title: String,
  val description: String? = null,
  val type: Type,
  val tags: Set<Tag> = emptySet(),
  val date: LocalDate,
  val start: LocalTime,
  val end: LocalTime? = null,
  val color: Color,
  val icon: AppIconType = AppIconType.OTHER,
  val id: String = UUID.randomUUID().toString()
)