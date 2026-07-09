package com.example.humanmaintenance.db.mappers

import com.example.humanmaintenance.db.entities.TodoItemEntity
import com.example.humanmaintenance.ui.map.Effort
import com.example.humanmaintenance.ui.map.TodoItemData
import com.example.humanmaintenance.ui.map.TodoPriority
import java.time.LocalDate


fun TodoItemData.toEntity(): TodoItemEntity {
  return TodoItemEntity(
    id = id,
    title = title,
    description = description,
    priorityBase = priorityBase.name,
    priorityActual = priorityActual.name,
    effort = effort.name,
    date = date.toString(),
    pushedCount = pushedCount,
    completed = completed
  )
}


fun TodoItemEntity.toData(): TodoItemData {
  return TodoItemData(
    id = id,
    title = title,
    description = description,
    priorityBase = TodoPriority.valueOf(priorityBase),
    priorityActual = TodoPriority.valueOf(priorityActual),
    effort = Effort.valueOf(effort),
    date = LocalDate.parse(date),
    pushedCount = pushedCount,
    completed = completed
  )
}