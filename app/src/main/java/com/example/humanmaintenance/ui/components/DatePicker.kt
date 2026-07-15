package com.example.humanmaintenance.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.humanmaintenance.ui.theme.AppColors
import com.example.humanmaintenance.ui.theme.AppColors.Surface
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

/**
 * Single-date field, e.g. for AddCalendarItemOverlay's `date` when
 * only one date is needed. Renders "Label" + clickable "25 1 2025" row.
 * Tapping opens a modal DatePickerDialog; tapping outside it only
 * dismisses the dialog, leaving the parent overlay untouched.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateField(
  label: String,
  date: LocalDate,
  onDateChange: (LocalDate) -> Unit,
  formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd MM yyyy")
) {
  var showPicker by remember { mutableStateOf(false) }

  Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
    Text(text = label, color = AppColors.TextSecondary)
    Text(
      text = date.format(formatter),
      color = MaterialTheme.colorScheme.primary,
      modifier = Modifier.clickable { showPicker = true }
    )
  }

  if (showPicker) {
    val state = rememberDatePickerState(
      initialSelectedDateMillis = date.toEpochMillisUtc()
    )

    DatePickerDialog(
      onDismissRequest = { showPicker = false },
      confirmButton = {
        TextButton(onClick = {
          state.selectedDateMillis?.let { onDateChange(it.toLocalDateUtc()) }
          showPicker = false
        }) { Text("OK") }
      },
      dismissButton = {
        TextButton(onClick = { showPicker = false }) { Text("Cancel") }
      }
    ) {
      DatePicker(state = state)
    }
  }
}

/**
 * Start + optional End date field. Displays two columns side by side:
 * "Start Date" / value, "End Date" / value-or-"?" placeholder.
 * A single tap on either column opens one shared DateRangePicker dialog
 * (this matches how Material3's range picker works - one calendar,
 * two selections). End stays null until the user actually taps a
 * second day; confirming with only a start day selected is valid.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateRangeField(
  startDate: LocalDate,
  endDate: LocalDate?,
  onRangeChange: (start: LocalDate, end: LocalDate?) -> Unit,
  formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd MM yyyy")
) {
  var showPicker by remember { mutableStateOf(false) }

  Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    Column(
      modifier = Modifier.weight(1f).clickable { showPicker = true },
      verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
      Text(text = "Start Date")

      Surface(
        color = Color.Black,
        shape = RoundedCornerShape(25),
        border = BorderStroke(1.dp, AppColors.SurfaceVariant)
      ) {
        Text(
          text = startDate.format(formatter),
          color = AppColors.TextPrimary,
          modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
      }
    }

    Column(
      modifier = Modifier.weight(1f).clickable { showPicker = true },
      verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
      Text(text = "End Date")

      if (endDate == null) {
        Text(
          text = "∞",
          color = MaterialTheme.colorScheme.primary
        )

      } else {
        Surface(
          color = Color.Black,
          shape = RoundedCornerShape(25),
          border = BorderStroke(1.dp, AppColors.SurfaceVariant)
        ) {
          Text(
            text = endDate.format(formatter),
            color = AppColors.TextPrimary,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
          )
        }
      }
    }
  }

  if (showPicker) {
    val state = rememberDateRangePickerState(
      initialSelectedStartDateMillis = startDate.toEpochMillisUtc(),
      initialSelectedEndDateMillis = endDate?.toEpochMillisUtc()
    )

    DatePickerDialog(
      onDismissRequest = { showPicker = false },
      confirmButton = {
        TextButton(onClick = {
          val start = state.selectedStartDateMillis?.toLocalDateUtc() ?: startDate
          val end = state.selectedEndDateMillis?.toLocalDateUtc()
          onRangeChange(start, end)
          showPicker = false
        }) { Text("OK") }
      },
      dismissButton = {
        TextButton(onClick = { showPicker = false }) { Text("Cancel") }
      }
    ) {
      DateRangePicker(
        state = state,
        showModeToggle = false,
        modifier = Modifier
          .fillMaxWidth()
          .height(500.dp)
          .padding(16.dp)
      )
    }
  }
}

internal fun LocalDate.toEpochMillisUtc(): Long =
  atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli()

internal fun Long.toLocalDateUtc(): LocalDate =
  Instant.ofEpochMilli(this).atZone(ZoneOffset.UTC).toLocalDate()
