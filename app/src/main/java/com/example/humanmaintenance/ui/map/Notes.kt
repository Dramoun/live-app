package com.example.humanmaintenance.ui.map

import androidx.compose.ui.graphics.Color
import com.example.humanmaintenance.ui.components.AppIconType
import java.time.LocalDateTime
import java.util.UUID


data class NoteData(
  val text: String,
  val createdAt: LocalDateTime = LocalDateTime.now(),
  val updatedAt: LocalDateTime = LocalDateTime.now(),
  val id: String = UUID.randomUUID().toString()
)

data class NoteGroupData(
  val title: String,
  val icon: AppIconType = AppIconType.OTHER,
  val color: Color,
  val notes: List<NoteData> = emptyList(),
  val id: String = UUID.randomUUID().toString(),
  val createdAt: LocalDateTime = LocalDateTime.now(),
  val updatedAt: LocalDateTime = LocalDateTime.now()
)