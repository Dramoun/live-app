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
  alwaysShowIcon: Boolean = true,
  onStateChange: (Boolean) -> Unit
) {
  val backgroundColor = if (state) {
    AppColors.SuccessBackGround
  } else {
    AppColors.ErrorBackGround
  }

  val iconColor = if (state || alwaysShowIcon) {
    if (state) AppColors.Success else AppColors.Error
  } else {
    backgroundColor
  }

  val checkboxIcon = IconStyle(
    icon = style.icon,
    contentDescription = style.contentDescription,
    color = iconColor,
    background = backgroundColor,
  )

  AppIcon(
    modifier = Modifier
      .clip(CircleShape)
      .clickable { onStateChange(!state) },
    style = checkboxIcon,
    size = size,
    hasBorder = state,
    showIcon = state || alwaysShowIcon
  )
}