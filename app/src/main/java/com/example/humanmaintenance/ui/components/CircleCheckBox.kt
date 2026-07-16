package com.example.humanmaintenance.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.humanmaintenance.ui.theme.AppColors


@Composable
fun CircleCheckBox(
  state: Boolean,
  style: IconStyle,
  size: Dp = 48.dp,
  onStateChange: (Boolean) -> Unit
) {
  val checkboxIcon = IconStyle(
    icon = style.icon,
    contentDescription = style.contentDescription,
    color = if (state) AppColors.Success else AppColors.Error,
    background = if (state) AppColors.SuccessBackGround else AppColors.ErrorBackGround,
  )

  AppIcon(
    modifier = Modifier
      .clip(CircleShape)
      .clickable {onStateChange(!state)},
    style = checkboxIcon,
    size = size,
    hasBorder = state
  )
}