package com.example.humanmaintenance.ui.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.humanmaintenance.ui.components.InfoCard
import com.example.humanmaintenance.ui.components.InfoRow
import com.example.humanmaintenance.ui.components.MonthTitle
import com.example.humanmaintenance.ui.components.TopTypesCard
import com.example.humanmaintenance.ui.map.Category
import com.example.humanmaintenance.ui.map.FinanceItemData
import com.example.humanmaintenance.ui.map.FinanceType
import com.example.humanmaintenance.ui.map.FinanceViewMode
import com.example.humanmaintenance.ui.map.Priority
import com.example.humanmaintenance.ui.map.Recurrence
import com.example.humanmaintenance.ui.map.formatMoney
import com.example.humanmaintenance.ui.theme.AppColors
import java.time.LocalDate
import kotlin.math.roundToInt

data class FinanceTypeAmount(
  val label: String,
  val amount: Int
)

data class FinanceMonthlySummary(
  val totalIncome: Int,
  val totalExpenses: Int,
  val freeCash: Int,

  val essentialTotal: Int,
  val optionalTotal: Int,
  val luxuryTotal: Int,

  val recurringYearly: Int,
  val recurringMonthly: Int,
  val recurringWeekly: Int,

  val essentialPercentage: Float,
  val optionalPercentage: Float,
  val luxuryPercentage: Float,

  val top3EssentialTypes: List<FinanceTypeAmount>,
  val top3OptionalTypes: List<FinanceTypeAmount>
)

@Composable
fun FinanceMonthlyOverview(
  modifier: Modifier = Modifier,
  date: LocalDate,
  onDateChange: (LocalDate) -> Unit = {},
  financeItems: List<FinanceItemData>
) {
  val summary = remember(date, financeItems) {
    val start = date.withDayOfMonth(1)
    val monthItems = financeItems.filter {
      it.overlaps(start, start.withDayOfMonth(start.lengthOfMonth()))
    }
    calculateMonthlySummary(monthItems)
  }

  Column(
    modifier = modifier.fillMaxSize().padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(12.dp)
  ) {
    MonthTitle(
      date = date,
      onDateChange = onDateChange,
      modifier = Modifier.clickable { onDateChange(LocalDate.now()) }
    )

    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
      InfoCard(
        modifier = Modifier.weight(1f),
        title = "Total Overview",
        backgroundColor = AppColors.GreenBackground
      ) {
        InfoRow(label = "Income", value = formatMoney(summary.totalIncome))
        InfoRow(label = "Expenses", value = formatMoney(summary.totalExpenses))
        InfoRow(label = "Free Cash", value = formatMoney(summary.freeCash))
      }

      InfoCard(
        modifier = Modifier.weight(1f),
        title = "Categories",
        backgroundColor = AppColors.PurpleBackground
      ) {
        InfoRow(label = "Essential", value = formatMoney(summary.essentialTotal))
        InfoRow(label = "Optional", value = formatMoney(summary.optionalTotal))
        InfoRow(label = "Luxury", value = formatMoney(summary.luxuryTotal))
      }
    }

    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
      InfoCard(
        modifier = Modifier.weight(1f),
        title = "Recurring Costs",
        backgroundColor = AppColors.OrangeBackground
      ) {
        InfoRow(label = "Yearly", value = formatMoney(summary.recurringYearly))
        InfoRow(label = "Monthly", value = formatMoney(summary.recurringMonthly))
        InfoRow(label = "Weekly", value = formatMoney(summary.recurringWeekly))
      }

      InfoCard(
        modifier = Modifier.weight(1f),
        title = "Percentages",
        backgroundColor = AppColors.RedBackground
      ) {
        InfoRow(label = "Essential", value = "${summary.essentialPercentage.roundToInt()} %")
        InfoRow(label = "Optional", value = "${summary.optionalPercentage.roundToInt()} %")
        InfoRow(label = "Luxury", value = "${summary.luxuryPercentage.roundToInt()} %")
      }
    }

    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
      TopTypesCard(
        modifier = Modifier.weight(1f),
        title = "Top 3 Essential",
        entries = summary.top3EssentialTypes,
        backgroundColor = AppColors.PinkBackground
      )

      TopTypesCard(
        modifier = Modifier.weight(1f),
        title = "Top 3 Optional",
        entries = summary.top3OptionalTypes,
        backgroundColor = AppColors.BlueBackground
      )
    }
  }
}

fun calculateMonthlySummary(
  items: List<FinanceItemData>
): FinanceMonthlySummary {

  var totalIncome = 0
  var totalExpenses = 0

  var essentialTotal = 0
  var optionalTotal = 0
  var luxuryTotal = 0

  var recurringTotal = 0

  val essentialByType = HashMap<FinanceType, Int>()
  val optionalByType = HashMap<FinanceType, Int>()

  items.forEach { item ->
    val amount = item.amountFor(FinanceViewMode.MONTH).roundToInt()

    when (item.category) {
      Category.INCOME -> {
        totalIncome += amount
      }

      Category.EXPENSE -> {
        totalExpenses += amount

        when (item.priority) {
          Priority.ESSENTIAL -> {
            essentialTotal += amount
            essentialByType.merge(item.type, amount, Int::plus)
          }
          Priority.OPTIONAL -> {
            optionalTotal += amount
            optionalByType.merge(item.type, amount, Int::plus)
          }
          Priority.LUXURY -> luxuryTotal += amount
          Priority.SAVINGS -> Unit // not currently tracked in this view
        }
      }
    }

    if (item.category == Category.EXPENSE && item.recurrence != Recurrence.ONE_TIME) {
      recurringTotal += amount
    }
  }

  val freeCash = totalIncome - totalExpenses

  val top3EssentialTypes = essentialByType.entries
    .sortedByDescending { it.value }
    .take(3)
    .map { FinanceTypeAmount(it.key.label, it.value) }

  val top3OptionalTypes = optionalByType.entries
    .sortedByDescending { it.value }
    .take(3)
    .map { FinanceTypeAmount(it.key.label, it.value) }

  return FinanceMonthlySummary(
    totalIncome = totalIncome,
    totalExpenses = totalExpenses,
    freeCash = freeCash,

    essentialTotal = essentialTotal,
    optionalTotal = optionalTotal,
    luxuryTotal = luxuryTotal,

    recurringYearly = recurringTotal * 12,
    recurringMonthly = recurringTotal,
    recurringWeekly = (recurringTotal * 12 / 52),

    essentialPercentage = percentage(essentialTotal, totalExpenses),
    optionalPercentage = percentage(optionalTotal, totalExpenses),
    luxuryPercentage = percentage(luxuryTotal, totalExpenses),

    top3EssentialTypes = top3EssentialTypes,
    top3OptionalTypes = top3OptionalTypes
  )
}

private fun percentage(value: Int, total: Int): Float {
  if (total == 0) return 0f
  return value.toFloat() / total * 100
}
