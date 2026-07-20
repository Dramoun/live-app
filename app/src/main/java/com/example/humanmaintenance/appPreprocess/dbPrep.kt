package com.example.humanmaintenance.appPreprocess

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.humanmaintenance.db.AppDatabase
import com.example.humanmaintenance.db.repositories.CalendarRepository
import com.example.humanmaintenance.db.repositories.FinanceRepository
import com.example.humanmaintenance.db.repositories.NoteGroupRepository
import com.example.humanmaintenance.db.repositories.TodoRepository
import com.example.humanmaintenance.db.viewmodels.CalendarViewModel
import com.example.humanmaintenance.db.viewmodels.CalendarViewModelFactory
import com.example.humanmaintenance.db.viewmodels.FinanceViewModel
import com.example.humanmaintenance.db.viewmodels.FinanceViewModelFactory
import com.example.humanmaintenance.db.viewmodels.NoteGroupViewModel
import com.example.humanmaintenance.db.viewmodels.NoteGroupViewModelFactory
import com.example.humanmaintenance.db.viewmodels.TodoViewModel
import com.example.humanmaintenance.db.viewmodels.TodoViewModelFactory

class AppViewModels(
  val calendar: CalendarViewModel,
  val finance: FinanceViewModel,
  val todo: TodoViewModel,
  val noteGroups: NoteGroupViewModel
)

@Composable
fun rememberAppViewModels(db: AppDatabase): AppViewModels {
  val calendarRepo = remember { CalendarRepository(db.calendarDao()) }
  val financeRepo = remember { FinanceRepository(db.financeDao()) }
  val todoRepo = remember { TodoRepository(db.todoDao()) }
  val noteGroupRepo = remember { NoteGroupRepository(db.noteGroupDao(), db.noteDao()) }

  return AppViewModels(
    calendar = viewModel<CalendarViewModel>(factory = CalendarViewModelFactory(calendarRepo)),
    finance = viewModel<FinanceViewModel>(factory = FinanceViewModelFactory(financeRepo)),
    todo = viewModel<TodoViewModel>(factory = TodoViewModelFactory(todoRepo)),
    noteGroups = viewModel<NoteGroupViewModel>(factory = NoteGroupViewModelFactory(noteGroupRepo))
  )
}