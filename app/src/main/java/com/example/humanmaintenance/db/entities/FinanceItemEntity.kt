package com.example.humanmaintenance.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "finance_items")
data class FinanceItemEntity(
  @PrimaryKey val id: String,
  val header: String,
  val icon: String,
  val category: String,
  val priority: String,
  val recurrence: String,
  val amount: Long
)