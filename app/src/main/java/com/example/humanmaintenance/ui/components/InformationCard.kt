package com.example.humanmaintenance.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.humanmaintenance.ui.map.formatMoney
import com.example.humanmaintenance.ui.pages.FinanceTypeAmount
import com.example.humanmaintenance.ui.theme.AppColors

@Composable
fun InfoCard(
  modifier: Modifier = Modifier,
  title: String,
  backgroundColor: Color? = null,
  content: @Composable ColumnScope.() -> Unit,
) {
  Card(
    colors = CardDefaults.cardColors(
      containerColor = (backgroundColor ?: AppColors.SurfaceVariant)
    ),
    border = BorderStroke(
      width = 1.dp,
      color = AppColors.SurfaceVariant
    ),
    modifier = modifier
  ) {
    Column(
      modifier = Modifier.padding(10.dp)
    ) {
      Text(
        text = title,
        color = AppColors.TextPrimary
      )

      Spacer(
        modifier = Modifier.height(8.dp)
      )

      CompositionLocalProvider(
        LocalContentColor provides AppColors.TextSecondary
      ) {
        content()
      }
    }
  }
}

@Composable
fun InfoRow(
  label: String,
  value: String,
) {
  Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    Text(
      text = label
    )

    Text(
      text = value
    )
  }
}

@Composable
fun TopTypesCard(
  modifier: Modifier = Modifier,
  title: String,
  entries: List<FinanceTypeAmount>,
  backgroundColor: Color
) {
  InfoCard(
    modifier = modifier,
    title = title,
    backgroundColor = backgroundColor
  ) {
    for (i in 0 until 3) {
      val entry = entries.getOrNull(i)
      InfoRow(
        label = entry?.label ?: "—",
        value = entry?.let { formatMoney(it.amount) } ?: "—"
      )
    }
  }
}