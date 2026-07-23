package com.example.humanmaintenance.db.mappers

import androidx.compose.ui.graphics.Color
import com.example.humanmaintenance.db.entities.CalendarItemEntity
import com.example.humanmaintenance.db.entities.FinanceItemEntity
import com.example.humanmaintenance.ui.components.AppIconType
import com.example.humanmaintenance.ui.map.CalendarItemData
import com.example.humanmaintenance.ui.map.Category
import com.example.humanmaintenance.ui.map.ExpenseType
import com.example.humanmaintenance.ui.map.FinanceItemData
import com.example.humanmaintenance.ui.map.FinanceType
import com.example.humanmaintenance.ui.map.IncomeType
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
    type = type.toString(),
    priority = priority.name,
    recurrence = recurrence.name,
    amount = amount.toLong(),
    icon = icon.name,
    initialDate = initialDate.toString(),
    endDate = endDate?.toString()
  )
}


fun FinanceItemEntity.toData(): FinanceItemData {
  val category = Category.valueOf(category)

  return FinanceItemData(
    id = id,
    header = header,
    category = category,
    type = type.toFinanceType(category),
    priority = Priority.valueOf(priority),
    recurrence = Recurrence.valueOf(recurrence),
    amount = amount.toInt(),
    icon = AppIconType.valueOf(icon),
    initialDate = LocalDate.parse(initialDate),
    endDate = endDate?.let { LocalDate.parse(it) },
  )
}

fun String.toFinanceType(category: Category): FinanceType {
  return when(category) {
    Category.INCOME ->
      IncomeType.valueOf(this)

    Category.EXPENSE ->
      ExpenseType.valueOf(this)
  }
}