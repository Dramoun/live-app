package com.example.humanmaintenance.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.humanmaintenance.ui.theme.AppColors
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalAdjusters

@Composable
fun DayTitle(
  modifier: Modifier = Modifier,
  date: LocalDate,
  onDateChange: (LocalDate) -> Unit = {}
) {
  val dateFormatter = DateTimeFormatter.ofPattern("d MMM (M) yyyy")
  val dayFormatter = DateTimeFormatter.ofPattern("EEEE")

  val dateText = date.format(dateFormatter)
  val percent = (date.dayOfMonth * 100) / date.lengthOfMonth()
  val dateResult = "$dateText $percent%"
  val dayText =  date.format(dayFormatter)

  val dayNumber = date.dayOfWeek.value

  Row(
    modifier = modifier.fillMaxWidth(),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    IconButton(onClick = { onDateChange(date.minusDays(1)) }) {
      AppIcon(
        style = AppIcons.PreviousDay(),
        size = 32.dp,
        hasBorder = false
      )
    }

    Column(
      modifier = Modifier.padding(16.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Row{
        Text(
          text = dayText,
          color = AppColors.TextPrimary
        )

        Text(
          text = " ($dayNumber)",
          color = AppColors.TextSecondary
        )
      }

      Text(
        text = dateResult,
        color = AppColors.TextSecondary
      )
    }

    IconButton(onClick = { onDateChange(date.plusDays(1)) }) {
      AppIcon(
        style = AppIcons.NextDay(),
        size = 32.dp,
        hasBorder = false
      )
    }
  }

}

@Composable
fun WeekTitle(
  date: LocalDate,
  onDateChange: (LocalDate) -> Unit = {},
  modifier: Modifier = Modifier
) {
  val monday = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
  val sunday = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY))

  val weekRatio = getWeekRatio(date)
  val month = date.format(DateTimeFormatter.ofPattern("MMM"))
  val year = date.format(DateTimeFormatter.ofPattern("yyyy"))

  val secondaryDate = "$weekRatio $month $year"

  Row(
    modifier = modifier.fillMaxWidth(),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    IconButton(onClick = { onDateChange(date.minusWeeks(1)) }) {
      AppIcon(
        style = AppIcons.PreviousDay(),
        size = 32.dp,
        hasBorder = false
      )
    }

    Column(
      modifier = Modifier.padding(16.dp),
      horizontalAlignment = Alignment.CenterHorizontally
      ) {
      Row(
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = "${monday.dayOfMonth}.${monday.monthValue}",
          color = AppColors.TextPrimary
        )

        Text(
          text = " → ",
          color = AppColors.TextSecondary
        )

        Text(
          text = "${sunday.dayOfMonth}.${sunday.monthValue}",
          color = AppColors.TextPrimary
        )
      }

      Text(
        text = secondaryDate,
        color = AppColors.TextSecondary
      )
    }

    IconButton(onClick = { onDateChange(date.plusWeeks(1)) }) {
      AppIcon(
        style = AppIcons.NextDay(),
        size = 32.dp,
        hasBorder = false
      )
    }
  }
}

@Composable
fun MonthTitle(
  date: LocalDate,
  onDateChange: (LocalDate) -> Unit = {},
  modifier: Modifier = Modifier
) {
  val monthLong = date.format(DateTimeFormatter.ofPattern("MMM"))
  val secondaryDate = date.format(DateTimeFormatter.ofPattern("d M yyyy"))

  Row(
    modifier = modifier.fillMaxWidth(),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    IconButton(onClick = { onDateChange(date.minusMonths(1)) }) {
      AppIcon(
        style = AppIcons.PreviousDay(),
        size = 32.dp,
        hasBorder = false
      )
    }

    BaseDateComp(
      mainDate = monthLong,
      secondaryDate = secondaryDate
    )

    IconButton(onClick = { onDateChange(date.plusMonths(1)) }) {
      AppIcon(
        style = AppIcons.NextDay(),
        size = 32.dp,
        hasBorder = false
      )
    }
  }
}

@Composable
fun YearTitle(
  date: LocalDate,
  onDateChange: (LocalDate) -> Unit = {},
  modifier: Modifier = Modifier
) {
  val yearLong = date.format(DateTimeFormatter.ofPattern("yyyy"))
  val secondaryDate = date.format(DateTimeFormatter.ofPattern("d MMM (m)"))

  Row(
    modifier = modifier.fillMaxWidth(),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    IconButton(onClick = { onDateChange(date.minusYears(1)) }) {
      AppIcon(
        style = AppIcons.PreviousDay(),
        size = 32.dp,
        hasBorder = false
      )
    }

    BaseDateComp(
      mainDate = yearLong,
      secondaryDate = secondaryDate
    )

    IconButton(onClick = { onDateChange(date.plusYears(1)) }) {
      AppIcon(
        style = AppIcons.NextDay(),
        size = 32.dp,
        hasBorder = false
      )
    }
  }
}

@Composable
fun BaseDateComp(
  mainDate: String,
  secondaryDate: String,
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier.padding(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(
      text = mainDate,
      color = AppColors.TextPrimary
    )

    Text(
      text = secondaryDate,
      color = AppColors.TextSecondary
    )
  }
}

fun getWeekRatio(
  date: LocalDate
): String {
  val firstMonday =
    date.withDayOfMonth(1)
      .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))

  val currentMonday =
    date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))

  val weekNumber =
    ChronoUnit.WEEKS.between(firstMonday, currentMonday).toInt() + 1

  val lastDay = date.withDayOfMonth(date.lengthOfMonth())
  val lastMonday =
    lastDay.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))

  val totalWeeks =
    ChronoUnit.WEEKS.between(firstMonday, lastMonday).toInt() + 1

  return "$weekNumber/$totalWeeks"
}
