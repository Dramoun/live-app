package com.example.humanmaintenance.db.mappers

import androidx.compose.ui.graphics.Color
import com.example.humanmaintenance.db.entities.NoteEntity
import com.example.humanmaintenance.db.entities.NoteGroupEntity
import com.example.humanmaintenance.db.pojos.NoteGroupWithNotesEntity
import com.example.humanmaintenance.ui.components.AppIconType
import com.example.humanmaintenance.ui.map.NoteData
import com.example.humanmaintenance.ui.map.NoteGroupData
import java.time.LocalDateTime

fun NoteGroupWithNotesEntity.toData(): NoteGroupData = NoteGroupData(
  id = group.id,
  title = group.title,
  icon = AppIconType.valueOf(group.icon),
  color = Color(group.color.toULong()),
  notes = notes.map { it.toData() },
  createdAt = LocalDateTime.parse(group.createdAt),
  updatedAt = LocalDateTime.parse(group.updatedAt)
)

fun NoteEntity.toData(): NoteData = NoteData(
  id = id,
  text = text,
  createdAt = LocalDateTime.parse(createdAt),
  updatedAt = LocalDateTime.parse(updatedAt)
)

fun NoteGroupData.toEntity(): NoteGroupEntity = NoteGroupEntity(
  id = id,
  title = title,
  icon = icon.name,
  createdAt = createdAt.toString(),
  updatedAt = updatedAt.toString(),
  color = color.value.toLong()
)

fun NoteData.toEntity(groupId: String): NoteEntity = NoteEntity(
  id = id,
  groupId = groupId,
  text = text,
  createdAt = createdAt.toString(),
  updatedAt = updatedAt.toString()
)