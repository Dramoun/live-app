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
  IMPORTANT("Important"),
  PERSONAL("Personal"),
  PROJECT("Project"),
  HEALTH("Health"),
  FAMILY("Family"),
  FRIENDS("Friends"),

  MEDICAL("Medical"),
  EXERCISE("Exercise"),
  SPORT("Sport"),

  TRAVEL("Travel"),
  TRANSPORT("Transport"),

  BIRTHDAY("Birthday"),
  HOLIDAY("Holiday"),
  BOARDGAMES("Boardgames"),
  PARTY("Party"),
  DATE("Date"),

  ESTIMATE("Estimate"),
  RESERVED("Reserved")
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