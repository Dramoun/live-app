package com.example.humanmaintenance.db.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.humanmaintenance.db.entities.NoteEntity
import com.example.humanmaintenance.db.entities.NoteGroupEntity
import com.example.humanmaintenance.db.pojos.NoteGroupWithNotesEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteGroupDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert(group: NoteGroupEntity)

  @Update
  suspend fun update(group: NoteGroupEntity)

  @Delete
  suspend fun delete(group: NoteGroupEntity)

  @Transaction
  @Query("SELECT * FROM note_groups")
  fun getAllWithNotes(): Flow<List<NoteGroupWithNotesEntity>>
}

@Dao
interface NoteDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert(note: NoteEntity)

  @Update
  suspend fun update(note: NoteEntity)

  @Delete
  suspend fun delete(note: NoteEntity)
}