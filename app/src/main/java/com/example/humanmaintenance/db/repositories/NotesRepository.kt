package com.example.humanmaintenance.db.repositories

import com.example.humanmaintenance.db.daos.NoteDao
import com.example.humanmaintenance.db.daos.NoteGroupDao
import com.example.humanmaintenance.db.mappers.toData
import com.example.humanmaintenance.db.mappers.toEntity
import com.example.humanmaintenance.ui.map.NoteData
import com.example.humanmaintenance.ui.map.NoteGroupData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NoteGroupRepository(
  private val groupDao: NoteGroupDao,
  private val noteDao: NoteDao
) {

  val allGroups: Flow<List<NoteGroupData>> =
    groupDao.getAllWithNotes().map { list -> list.map { it.toData() } }

  suspend fun insertGroup(group: NoteGroupData) = groupDao.insert(group.toEntity())

  suspend fun updateGroup(group: NoteGroupData) = groupDao.update(group.toEntity())

  suspend fun deleteGroup(group: NoteGroupData) = groupDao.delete(group.toEntity())

  suspend fun addNote(groupId: String, note: NoteData) = noteDao.insert(note.toEntity(groupId))

  suspend fun updateNote(groupId: String, note: NoteData) = noteDao.update(note.toEntity(groupId))

  suspend fun deleteNote(groupId: String, note: NoteData) = noteDao.delete(note.toEntity(groupId))
}