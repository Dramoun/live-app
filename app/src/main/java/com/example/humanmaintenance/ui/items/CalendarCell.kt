package com.example.humanmaintenance.ui.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.humanmaintenance.ui.theme.AppColors

@Composable
fun CalendarCell(
  modifier: Modifier = Modifier,
  dayOfMonth: Int,
  numOfEvents: Int,
  isCurrentMonth: Boolean,
  isToday: Boolean,
) {
  val textColor = when {
    isToday -> AppColors.TextPrimary
    isCurrentMonth -> AppColors.TextPrimary
    else -> AppColors.TextSecondary
  }

  Box(
    modifier = modifier
      .background(
        if (isToday) AppColors.Blue.copy(alpha = 0.4f) else AppColors.SurfaceVariant.copy(alpha = if (isCurrentMonth) 1f else 0.4f)
      ),
    contentAlignment = Alignment.Center
  ) {
    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.spacedBy(0.dp)
    ) {
      Text(
        text = dayOfMonth.toString(),
        color = textColor,
        fontWeight = if (isToday) FontWeight.Bold else FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 14.sp
      )

      if (numOfEvents > 0) {
        Text(
          text = numOfEvents.toString(),
          color = AppColors.TextSecondary,
          fontSize = 9.sp,
          lineHeight = 9.sp,
          fontWeight = FontWeight.Bold
        )
      }
    }
  }
}