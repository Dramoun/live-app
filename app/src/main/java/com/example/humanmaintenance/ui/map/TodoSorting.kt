package com.example.humanmaintenance.ui.map

private const val PUSHES_PER_ESCALATION = 2

private val urgencyOrder = listOf(TodoPriority.LOW, TodoPriority.NORMAL, TodoPriority.HIGH)

fun effectivePriority(item: TodoItemData): TodoPriority {
  val baseIndex = urgencyOrder.indexOf(item.priorityBase)
  val escalatedIndex = baseIndex + (item.pushedCount / PUSHES_PER_ESCALATION)
  val cappedIndex = escalatedIndex.coerceAtMost(urgencyOrder.lastIndex)
  return urgencyOrder[cappedIndex]
}

fun priorityDisplayLabel(item: TodoItemData): String {
  val effective = effectivePriority(item)
  return if (effective != item.priorityBase) "${effective.label} !" else effective.label
}

/**
 * Distributes items across effort buckets round-robin so difficulty
 * doesn't cluster (e.g. QUICK, STANDARD, LONG, QUICK, STANDARD, LONG...).
 * Doesn't need to be perfect alternation, just avoid long same-effort runs.
 */
private fun effortBalanced(items: List<TodoItemData>): List<TodoItemData> {
  val buckets = Effort.entries.associateWith { effort ->
    items.filter { it.effort == effort }.toMutableList()
  }

  val result = mutableListOf<TodoItemData>()
  var remaining = items.size

  while (remaining > 0) {
    for (effort in Effort.entries) {
      val bucket = buckets.getValue(effort)
      if (bucket.isNotEmpty()) {
        result.add(bucket.removeAt(0))
        remaining--
      }
    }
  }

  return result
}

/**
 * Applies the V1 spec ordering:
 * 1. Incomplete before completed
 * 2. Grouped by effective priority: High -> Normal -> Low
 * 3. Effort-balanced within each priority group
 */
fun sortedTodoItems(items: List<TodoItemData>): List<TodoItemData> {
  val (completed, incomplete) = items.partition { it.completed }

  fun sortGroup(group: List<TodoItemData>): List<TodoItemData> {
    val byPriority = group.groupBy { effectivePriority(it) }
    return listOf(TodoPriority.HIGH, TodoPriority.NORMAL, TodoPriority.LOW)
      .flatMap { priority -> effortBalanced(byPriority[priority].orEmpty()) }
  }

  return sortGroup(incomplete) + sortGroup(completed)
}