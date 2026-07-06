package com.example.humanmaintenance.ui.map

import androidx.compose.ui.graphics.Color
import java.time.LocalDate
import java.util.UUID

// General colors
val CompletedColor = Color(0xFF43A047) // Green

enum class TodoPriority(
  val label: String,
  val color: Color
) {
  HIGH(
    "High",
    Color(0xFFE53935)      // Red
  ),
  NORMAL(
    "Mid",
    Color(0xFFFB8C00)      // Orange
  ),
  LOW(
    "Low",
    Color(0xFF757575)      // Gray
  )
}

enum class Effort(
  val label: String,
  val color: Color
) {
  QUICK(
    "Quick",
    Color(0xFFFDD835)      // Yellow
  ),
  STANDARD(
    "Standard",
    Color(0xFF1E88E5)      // Blue
  ),
  LONG(
    "Long",
    Color(0xFF8E24AA)      // Purple
  )
}

data class TodoItemData(
  val title: String,
  val description: String? = null,
  val priorityBase: TodoPriority,
  val priorityActual: TodoPriority,
  val effort: Effort,
  val date: LocalDate,
  val pushedCount: Int,
  val completed: Boolean,
  val id: String = UUID.randomUUID().toString()
)