package com.example.humanmaintenance.ui.map

import com.example.humanmaintenance.ui.components.AppIconType
import java.time.LocalDate
import java.util.UUID

enum class Category(val label: String) {
  INCOME("Income"),
  EXPENSE("Expense")
}
enum class Priority(val label: String) {
  ESSENTIAL("Essential"),
  OPTIONAL("Optional"),
  LUXURY("Luxury"),
  SAVINGS("Savings"),
}

enum class Recurrence(val label: String) {
  ONE_TIME("One-time"),
  WEEKLY("Weekly"),
  MONTHLY("Monthly"),
  YEARLY("Yearly")
}

enum class FinanceViewMode {
  DAY,
  WEEK,
  MONTH,
  YEAR
}

data class FinanceItemData(
  val header: String,
  val icon: AppIconType,
  val category: Category,
  val priority: Priority,
  val recurrence: Recurrence,
  val amount: Number,
  val id: String = UUID.randomUUID().toString(),
  val initialDate: LocalDate,
  val endDate: LocalDate? = null
)