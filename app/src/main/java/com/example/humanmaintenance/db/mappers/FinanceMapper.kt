package com.example.humanmaintenance.db.mappers

import androidx.compose.ui.graphics.Color
import com.example.humanmaintenance.db.entities.CalendarItemEntity
import com.example.humanmaintenance.db.entities.FinanceItemEntity
import com.example.humanmaintenance.ui.components.AppIconType
import com.example.humanmaintenance.ui.map.CalendarItemData
import com.example.humanmaintenance.ui.map.Category
import com.example.humanmaintenance.ui.map.FinanceItemData
import com.example.humanmaintenance.ui.map.Priority
import com.example.humanmaintenance.ui.map.Recurrence
import com.example.humanmaintenance.ui.map.Tag
import com.example.humanmaintenance.ui.map.Type
import java.time.LocalDate
import java.time.LocalTime


fun FinanceItemData.toEntity(): FinanceItemEntity {
  return FinanceItemEntity(
    id = id,
    header = header,
    category = category.name,
    priority = priority.name,
    recurrence = recurrence.name,
    amount = amount.toLong(),
    icon = icon.name
  )
}


fun FinanceItemEntity.toData(): FinanceItemData {
  return FinanceItemData(
    id = id,
    header = header,
    category = Category.valueOf(category),
    priority = Priority.valueOf(priority),
    recurrence = Recurrence.valueOf(recurrence),
    amount = amount.toInt(),
    icon = AppIconType.valueOf(icon)
  )
}