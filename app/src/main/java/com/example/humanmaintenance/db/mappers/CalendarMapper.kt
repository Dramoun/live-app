package com.example.humanmaintenance.db.mappers

import androidx.compose.ui.graphics.Color
import com.example.humanmaintenance.db.entities.CalendarItemEntity
import com.example.humanmaintenance.ui.components.AppIconType
import com.example.humanmaintenance.ui.map.CalendarItemData
import com.example.humanmaintenance.ui.map.Tag
import com.example.humanmaintenance.ui.map.Type
import java.time.LocalDate
import java.time.LocalTime


fun CalendarItemData.toEntity(): CalendarItemEntity {
  return CalendarItemEntity(
    id = id,
    title = title,
    description = description,
    type = type.name,
    tags = tags.joinToString(",") { it.name },
    date = date.toString(),
    start = start.toString(),
    end = end?.toString(),
    color = color.value.toLong(),
    icon = icon.name
  )
}


fun CalendarItemEntity.toData(): CalendarItemData {
  return CalendarItemData(
    id = id,
    title = title,
    description = description,
    type = Type.valueOf(type),
    tags = if (tags.isBlank()) {
      emptySet()
    } else {
      tags.split(",").map { Tag.valueOf(it) }.toSet()
    },
    date = LocalDate.parse(date),
    start = LocalTime.parse(start),
    end = end?.let { LocalTime.parse(it) },
    color = Color(color.toULong()),
    icon = AppIconType.valueOf(icon)
  )
}