package com.example.humanmaintenance.ui.items

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.humanmaintenance.ui.components.AppIcon
import com.example.humanmaintenance.ui.components.toStyle
import com.example.humanmaintenance.ui.map.FinanceItemData
import com.example.humanmaintenance.ui.theme.AppColors

@Composable
fun FinanceItem(
  modifier: Modifier = Modifier,
  financeItem: FinanceItemData,
  pageAmountText: String
) {
  Row(
    modifier = modifier
      .fillMaxWidth()
      .drawBehind {
        drawLine(
          color = AppColors.Surface,
          start = Offset(0f, size.height),
          end = Offset(size.width, size.height),
          strokeWidth = 1.dp.toPx()
        )
      }
      .padding(5.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(12.dp)
  ) {
    AppIcon(
      style = financeItem.icon.toStyle()
    )
    Column(
      modifier = Modifier.weight(1f)
    ) {
      Text(
        text = financeItem.header,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        color = AppColors.TextPrimary
      )
      Column(
        verticalArrangement = Arrangement.spacedBy(0.dp)
      ) {
        Row(
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          Text(
            text = financeItem.category.label,
            color = AppColors.TextSecondary,
            fontSize = 12.sp
          )
          Text(
            text = financeItem.type.label,
            color = AppColors.TextSecondary,
            fontSize = 12.sp
          )
        }
        Row(
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          Text(
            text = financeItem.priority.label,
            color = AppColors.TextSecondary,
            fontSize = 10.sp
          )
          Text(
            text = financeItem.recurrence.label,
            color = AppColors.TextSecondary,
            fontSize = 10.sp
          )
        }
      }
    }

    Column(
      horizontalAlignment = Alignment.End
    ) {
      Text(
        text = financeItem.amount.toString(),
        color = AppColors.Income
      )

      Text(
        text = pageAmountText,
        color = AppColors.TextSecondary,
        fontSize = 12.sp
      )
    }
  }
}