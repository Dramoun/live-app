package com.example.humanmaintenance.ui.map

import com.example.humanmaintenance.ui.components.AppIconType
import java.time.LocalDate
import java.util.UUID

enum class Category(
  val label: String,
  val types: List<FinanceType>
) {
  INCOME(
    "Income",
    IncomeType.entries
  ),

  EXPENSE(
    "Expense",
    ExpenseType.entries
  )
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

interface FinanceType {
  val label: String
}

enum class IncomeType(override val label: String) : FinanceType {
  WORK("Work"),
  BONUS("Bonus"),
  GIFT("Gift"),
  REFUND("Refund"),
  SOLD_ITEM("Sold item"),
  INVESTMENT_RETURN("Investment return"),
  INTEREST("Interest"),
  BORROWED("Borrowed"),
  OTHER("Other")
}

enum class ExpenseType(override val label: String) : FinanceType {
  FOOD("Food"),
  SPORT("Sport"),
  HOUSING("Housing"),
  TRANSPORT("Transport"),
  HEALTH("Health"),
  SUBSCRIPTION("Subscription"),
  SHOPPING("Shopping"),
  ENTERTAINMENT("Entertainment"),
  HOBBY("Hobby"),
  TRAVEL("Travel"),
  EDUCATION("Education"),
  TAX("Tax"),
  GIFT("Gift"),
  ADDICTION("Addiction"),
  OTHER("Other")
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
  val type: FinanceType,
  val priority: Priority,
  val recurrence: Recurrence,
  val amount: Number,
  val id: String = UUID.randomUUID().toString(),
  val initialDate: LocalDate,
  val endDate: LocalDate? = null
)