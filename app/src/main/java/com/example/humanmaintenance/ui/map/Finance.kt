package com.example.humanmaintenance.ui.map

import com.example.humanmaintenance.ui.components.IconStyle

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
  YEARLY("Early")
}

data class FinanceItemData(
  val header: String,
  val icon: IconStyle,
  val category: Category,
  val priority: Priority,
  val recurrence: Recurrence,
  val amount: Number
)