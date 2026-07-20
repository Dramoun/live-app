package com.example.humanmaintenance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.room.Room
import com.example.humanmaintenance.appPreprocess.AppViewModels
import com.example.humanmaintenance.db.AppDatabase
import com.example.humanmaintenance.db.migrations.MIGRATION_1_2
import com.example.humanmaintenance.db.migrations.MIGRATION_2_3
import com.example.humanmaintenance.db.migrations.MIGRATION_3_4
import com.example.humanmaintenance.db.migrations.MIGRATION_4_5
import com.example.humanmaintenance.db.migrations.MIGRATION_5_6
import com.example.humanmaintenance.ui.components.AddFloatingActionButton
import com.example.humanmaintenance.ui.overlays.AddSheet
import com.example.humanmaintenance.ui.navigation.AppDrawer
import com.example.humanmaintenance.ui.theme.HumanMaintenanceTheme
import com.example.humanmaintenance.ui.navigation.AppTopBar
import com.example.humanmaintenance.ui.map.AppPage
import com.example.humanmaintenance.appPreprocess.EditingItem
import com.example.humanmaintenance.appPreprocess.rememberAppUiState
import com.example.humanmaintenance.appPreprocess.rememberAppViewModels
import com.example.humanmaintenance.ui.navigation.BackToGroupsButton
import com.example.humanmaintenance.ui.pages.MainScreen
import com.example.humanmaintenance.ui.theme.AppColors


class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()

    val db = Room.databaseBuilder(
      applicationContext,
      AppDatabase::class.java,
      "human_maintenance.db"
    ).addMigrations(MIGRATION_1_2)
      .addMigrations(MIGRATION_2_3)
      .addMigrations(MIGRATION_3_4)
      .addMigrations(MIGRATION_4_5)
      .addMigrations(MIGRATION_5_6)
      .build()

    setContent {
      HumanMaintenanceTheme {
        App(rememberAppViewModels(db))
      }
    }
  }
}

@Composable
fun App(vms: AppViewModels) {
  val ui = rememberAppUiState()
  val financeItems by vms.finance.financeItems.collectAsState()
  val noteGroups by vms.noteGroups.noteGroups.collectAsState()
  val calendarItems by vms.calendar.calendarItems.collectAsState()
  val todoItems by vms.todo.todoItems.collectAsState()

  AppDrawer(currentPage = ui.currentPage, onPageSelected = { ui.currentPage = it }) { onMenuClick ->
    Scaffold(
      containerColor = AppColors.Background,
      contentColor = AppColors.TextPrimary,
      floatingActionButton = { AddFloatingActionButton(onClick = ui::openAdd) },
      topBar = {
        AppTopBar(
          page = ui.currentPage,
          noteGroups.find { it.id == ui.selectedNoteGroupId }?.title,
          onMenuClick = onMenuClick
        )
      }
    ) { innerPadding ->
      Box(Modifier.padding(innerPadding)) {
        MainScreen(
          calendarItems = calendarItems,
          financeItems = financeItems,
          todoItems = todoItems,
          currentPage = ui.currentPage,
          date = ui.date,
          onDateChange = { ui.date = it },
          onFinanceItemClick = { ui.openEdit(EditingItem.Finance(it)) },
          onCalendarItemClick = { ui.openEdit(EditingItem.Calendar(it)) },
          onTodoItemClick = { ui.openEdit(EditingItem.Todo(it)) },
          onPushTodoItem = { vms.todo.pushTodoItem(it) },
          onSwitchTodoComplete = { vms.todo.toggleComplete(it) },
          onPageSelected = { ui.currentPage = it },
          noteGroups = noteGroups,
          selectedNoteGroupId = ui.selectedNoteGroupId,
          onNoteGroupClick = { ui.openEdit(EditingItem.NoteGroup(it)) },
          onNoteGroupSelect = { id ->
            ui.selectedNoteGroupId = id
            ui.currentPage = AppPage.NOTES
          },
          onNoteClick = { note ->
            ui.openEdit(EditingItem.Note(ui.selectedNoteGroupId!!, note))
          },
          modifier = Modifier
        )

        if (ui.currentPage == AppPage.NOTES) {
          BackToGroupsButton(
            modifier = Modifier.align(Alignment.BottomStart),
            onClick = { ui.currentPage = AppPage.NOTE_GROUPS }
          )
        }

        if (ui.showAddSheet) {
          AddSheet(
            currentPage = ui.currentPage,
            editingItem = ui.editingItem,
            selectedNoteGroupId = ui.selectedNoteGroupId,
            date = ui.date,
            onDismiss = ui::closeSheet,
            onSave = { item, isUpdate ->
              when (item) {
                is EditingItem.Finance -> if (isUpdate) vms.finance.updateItem(item.item) else vms.finance.addItem(item.item)
                is EditingItem.Calendar -> if (isUpdate) vms.calendar.updateItem(item.item) else vms.calendar.addItem(item.item)
                is EditingItem.Todo -> if (isUpdate) vms.todo.updateItem(item.item) else vms.todo.addItem(item.item)
                is EditingItem.NoteGroup -> if (isUpdate) vms.noteGroups.updateGroup(item.item) else vms.noteGroups.addGroup(item.item)
                is EditingItem.Note -> if (isUpdate) vms.noteGroups.updateNote(item.groupId, item.item) else vms.noteGroups.addNote(item.groupId, item.item)
                EditingItem.None -> {}
              }
              ui.closeSheet()
            },
            onDelete = { item ->
              when (item) {
                is EditingItem.Finance -> vms.finance.deleteItem(item.item)
                is EditingItem.Calendar -> vms.calendar.deleteItem(item.item)
                is EditingItem.Todo -> vms.todo.deleteItem(item.item)
                is EditingItem.NoteGroup -> vms.noteGroups.deleteGroup(item.item)
                is EditingItem.Note -> vms.noteGroups.deleteNote(item.groupId, item.item)
                EditingItem.None -> {}
              }
              ui.closeSheet()
            }
          )
        }
      }
    }
  }
}