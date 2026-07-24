package com.example.humanmaintenance.ui.map

import java.text.NumberFormat
import java.util.Locale

enum class AppPage(val label: String) {
  CALENDAR_DAY("Calendar Day"),
  CALENDAR_MONTH("Calendar Month"),
  FINANCE_ITEMS_DAY("Daily Finance"),
  FINANCE_ITEMS_WEEK("Weekly Finance"),
  FINANCE_ITEMS_MONTH("Monthly Finance"),
  FINANCE_OVERVIEW_MONTH("Monthly Finance Overview"),
  TODO("Todo"),
  NOTE_GROUPS("Note Groups"),
  NOTES("Notes")
}

fun formatMoney(amount: Int): String {
  return NumberFormat
    .getNumberInstance(Locale("cs", "CZ"))
    .format(amount)
}