package com.example.humanmaintenance.ui.items

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.humanmaintenance.ui.components.AppIcon
import com.example.humanmaintenance.ui.components.toStyle
import com.example.humanmaintenance.ui.map.CalendarItemData
import com.example.humanmaintenance.ui.theme.AppColors

@Composable
fun CalendarItem(
  item: CalendarItemData,
  hourHeight: Dp,
  startFraction: Float,
  durationHours: Float,
  onItemClick: (CalendarItemData) -> Unit = {},
  gutterWidth: Dp,
  modifier: Modifier = Modifier
) {
  Box(
    modifier = modifier
      .padding(start = gutterWidth, end = 8.dp)
      .offset(y = hourHeight * startFraction)
      .fillMaxWidth()
      .height(hourHeight * durationHours)
      .background(item.color.copy(alpha = 0.25f), RoundedCornerShape(8.dp))
      .border(1.dp, item.color, RoundedCornerShape(8.dp))
      .clickable { onItemClick(item) }
      .padding(6.dp)
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
      AppIcon(
        style = item.icon.toStyle(),
        size = 20.dp,
        hasBorder = false
      )

      Column(
        modifier = Modifier.weight(1f)
      ) {
        Text(
          text = item.title,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis,
          fontSize = 12.sp,
          color = AppColors.TextPrimary
        )

        Row(
          horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          Text(
            text = item.type.label,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 10.sp,
            color = AppColors.TextSecondary
          )

          if (item.tags.isNotEmpty()) {
            Text(
              text = "•",
              fontSize = 10.sp,
              color = AppColors.TextSecondary
            )
          }

          item.tags.forEach { tag ->
            Text(
              text = tag.label,
              maxLines = 1,
              overflow = TextOverflow.Ellipsis,
              fontSize = 10.sp,
              color = AppColors.TextSecondary
            )
          }
        }
      }
    }
  }
}