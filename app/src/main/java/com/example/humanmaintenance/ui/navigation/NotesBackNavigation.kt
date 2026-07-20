package com.example.humanmaintenance.ui.navigation


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.humanmaintenance.ui.components.AppIcon
import com.example.humanmaintenance.ui.components.AppIconType
import com.example.humanmaintenance.ui.components.toStyle

@Composable
fun BackToGroupsButton(
  modifier: Modifier = Modifier,
  onClick: () -> Unit
) {
  AppIcon(
    style = AppIconType.ARROW_LEFT.toStyle(),
    size = 42.dp,
    modifier = modifier
      .padding(16.dp)
      .clickable(onClick = onClick)
  )
}