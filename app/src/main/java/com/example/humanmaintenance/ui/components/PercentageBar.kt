package com.example.humanmaintenance.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.humanmaintenance.ui.theme.AppColors
import kotlin.math.roundToInt

@Composable
fun PercentageBar(
  modifier: Modifier = Modifier,
  percentage: Float, // 0f..1f
  height: Dp = 10.dp,
) {
  val clamped = percentage.coerceIn(0f, 1f)

  Row(
    modifier = modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.spacedBy(8.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Box(
      modifier = Modifier
        .weight(1f)
        .height(height)
        .clip(RoundedCornerShape(50))
        .background(AppColors.Neutral.copy(alpha = 0.3f))
    ) {
      Box(
        modifier = Modifier
          .fillMaxHeight()
          .fillMaxWidth(clamped)
          .clip(RoundedCornerShape(50))
          .background(AppColors.TextPrimary)
      )
    }

    Text(
      text = "${(clamped * 100).roundToInt()}%",
      color = AppColors.TextSecondary,
      style = MaterialTheme.typography.labelSmall
    )
  }
}