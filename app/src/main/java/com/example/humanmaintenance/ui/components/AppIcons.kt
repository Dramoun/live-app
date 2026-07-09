package com.example.humanmaintenance.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.humanmaintenance.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.humanmaintenance.ui.theme.AppColors

data class IconStyle(
  val icon: androidx.compose.ui.graphics.painter.Painter,
  val contentDescription: String,
  val color: Color,
  val background: Color,
)

@Composable
fun AppIcon(
  style: IconStyle,
  size: Dp = 48.dp,
  hasBorder: Boolean = true
) {
  Box(
    modifier = Modifier
      .size(size)
      .background(color = style.background, shape = CircleShape)
      .border(width = if (hasBorder) 1.5.dp else 0.dp, color = style.color.copy(alpha = 0.4f), shape = CircleShape),
    contentAlignment = Alignment.Center
  ) {
    Icon(
      painter = style.icon,
      contentDescription = style.contentDescription,
      tint = style.color,
      modifier = Modifier.size(size * 0.5f)
    )
  }
}

// Storable/persistable key for an icon. IconStyle itself can't be stored in a plain
// data class (Painter requires a @Composable context to resolve), so data classes like
// CalendarItemData should hold an AppIconType and resolve it to a style at render time
// via `iconType.toStyle()`.
enum class AppIconType {
  INCOME,
  EXPENSE,
  HOUSING,
  FOOD,
  TRANSPORT,
  SPORTS,
  GAMING,
  SUBSCRIPTIONS,
  SAVINGS,
  OTHER,
  PREVIOUS_DAY,
  NEXT_DAY
}

@Composable
fun AppIconType.toStyle(): IconStyle = when (this) {
  AppIconType.INCOME -> AppIcons.Income()
  AppIconType.EXPENSE -> AppIcons.Expense()
  AppIconType.HOUSING -> AppIcons.Housing()
  AppIconType.FOOD -> AppIcons.Food()
  AppIconType.TRANSPORT -> AppIcons.Transport()
  AppIconType.SPORTS -> AppIcons.Sports()
  AppIconType.GAMING -> AppIcons.Gaming()
  AppIconType.SUBSCRIPTIONS -> AppIcons.Subscriptions()
  AppIconType.SAVINGS -> AppIcons.Savings()
  AppIconType.OTHER -> AppIcons.Other()
  AppIconType.PREVIOUS_DAY -> AppIcons.PreviousDay()
  AppIconType.NEXT_DAY -> AppIcons.NextDay()
}

object AppIcons {
  @Composable fun Income() = IconStyle(
    icon = painterResource(R.drawable.payments_24px),
    contentDescription = "Income",
    color = Color(0xFF4ADE80),
    background = Color(0x264ADE80),
  )
  @Composable fun Expense() = IconStyle(
    icon = painterResource(R.drawable.account_balance_wallet_24px),
    contentDescription = "Expense",
    color = Color(0xFFF87171),
    background = Color(0x26F87171),
  )
  @Composable fun Housing() = IconStyle(
    icon = painterResource(R.drawable.home_24px),
    contentDescription = "Housing",
    color = Color(0xFF60A5FA),
    background = Color(0x2660A5FA),
  )
  @Composable fun Food() = IconStyle(
    icon = painterResource(R.drawable.shopping_cart_24px),
    contentDescription = "Food",
    color = Color(0xFF60A5FA),
    background = Color(0x2660A5FA),
  )
  @Composable fun Transport() = IconStyle(
    icon = painterResource(R.drawable.directions_bus_24px),
    contentDescription = "Transport",
    color = Color(0xFF60A5FA),
    background = Color(0x2660A5FA),
  )
  @Composable fun Sports() = IconStyle(
    icon = painterResource(R.drawable.fitness_center_24px),
    contentDescription = "Sports",
    color = Color(0xFFFB923C),
    background = Color(0x26FB923C),
  )
  @Composable fun Gaming() = IconStyle(
    icon = painterResource(R.drawable.sports_esports_24px),
    contentDescription = "Gaming",
    color = Color(0xFFFB923C),
    background = Color(0x26FB923C),
  )
  @Composable fun Subscriptions() = IconStyle(
    icon = painterResource(R.drawable.subscriptions_24px),
    contentDescription = "Subscriptions",
    color = Color(0xFFF87171),
    background = Color(0x26F87171),
  )
  @Composable fun Savings() = IconStyle(
    icon = painterResource(R.drawable.savings_24px),
    contentDescription = "Savings",
    color = Color(0xFFC084FC),
    background = Color(0x26C084FC),
  )
  @Composable fun Other() = IconStyle(
    icon = painterResource(R.drawable.more_horiz_24px),
    contentDescription = "Other",
    color = Color(0xFF888888),
    background = Color(0x26888888),
  )
  @Composable fun Dashboard() = IconStyle(
    icon = painterResource(R.drawable.space_dashboard_24px),
    contentDescription = "Dashboard",
    color = Color(0xFFFFFFFF),
    background = Color(0x26FFFFFF),
  )
  @Composable fun Insights() = IconStyle(
    icon = painterResource(R.drawable.pie_chart_24px),
    contentDescription = "Insights",
    color = Color(0xFFFFFFFF),
    background = Color(0x26FFFFFF),
  )
  @Composable
  fun Add() = IconStyle(
    icon = painterResource(R.drawable.add_24px),
    contentDescription = "Add",
    color = AppColors.TextPrimary,
    background = AppColors.Surface
  )
  @Composable
  fun PreviousDay() = IconStyle(
    icon = painterResource(R.drawable.trending_down_24px),
    contentDescription = "Previous day",
    color = AppColors.TextPrimary,
    background = AppColors.Surface
  )
  @Composable
  fun NextDay() = IconStyle(
    icon = painterResource(R.drawable.trending_up_24px),
    contentDescription = "Next day",
    color = AppColors.TextPrimary,
    background = AppColors.Surface
  )
  @Composable
  fun Menu() = IconStyle(
    icon = painterResource(R.drawable.menu_24px),
    contentDescription = "Menu",
    color = AppColors.TextPrimary,
    background = AppColors.Surface
  )
  @Composable
  fun More() = IconStyle(
    icon = painterResource(R.drawable.more_horiz_24px),
    contentDescription = "Menu",
    color = AppColors.TextPrimary,
    background = AppColors.Surface
  )
}