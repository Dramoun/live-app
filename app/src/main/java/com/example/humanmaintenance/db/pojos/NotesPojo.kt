package com.example.humanmaintenance.db.pojos

import androidx.room.Embedded
import androidx.room.Relation
import com.example.humanmaintenance.db.entities.NoteEntity
import com.example.humanmaintenance.db.entities.NoteGroupEntity

data class NoteGroupWithNotesEntity(
  @Embedded val group: NoteGroupEntity,
  @Relation(parentColumn = "id", entityColumn = "groupId")
  val notes: List<NoteEntity>
)