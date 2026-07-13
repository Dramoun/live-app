package com.example.humanmaintenance.ui.components

import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.humanmaintenance.ui.map.AppPage

@Composable
fun AddFloatingActionButton(
    onClick: () -> Unit
) {
    FloatingActionButton(
        onClick = onClick,
        containerColor = Color.Transparent,
    ) {
        AppIcon(
            style = AppIcons.Add(),
            hasBorder = false
        )
    }
}