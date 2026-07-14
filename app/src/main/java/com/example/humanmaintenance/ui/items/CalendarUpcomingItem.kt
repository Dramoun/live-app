package com.example.humanmaintenance.ui.items

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.humanmaintenance.ui.components.AppIcon
import com.example.humanmaintenance.ui.components.toStyle
import com.example.humanmaintenance.ui.map.CalendarItemData
import com.example.humanmaintenance.ui.theme.AppColors
import java.time.format.DateTimeFormatter


private val upcomingDateFormatter = DateTimeFormatter.ofPattern("d MMM")
@Composable
fun CalendarUpcomingItem(
  modifier: Modifier = Modifier,
  item: CalendarItemData,
) {
  Column(
    modifier = modifier
      .fillMaxWidth()
      .padding(vertical = 6.dp),
    verticalArrangement = Arrangement.spacedBy(6.dp)
  ) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(6.dp),
      verticalAlignment = Alignment.CenterVertically,

    ) {
      AppIcon(
        style = item.icon.toStyle(),
        size = 20.dp
      )

      Text(
        text = item.title,
        color = AppColors.TextPrimary,
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.Medium,
        maxLines = 1
      )
    }

    Text(
      text = item.date.format(upcomingDateFormatter),
      color = AppColors.TextSecondary,
      style = MaterialTheme.typography.labelSmall,
    )
  }
}