package com.miqdad.noteapp.data.locale.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.miqdad.noteapp.data.locale.entity.Notes

@Dao
interface NotesDao {

    @Insert
    suspend fun addNote(notes : Notes)

    @Query("SELECT * FROM tb_notes ORDER BY id ASC")
    fun getAllNote() : LiveData<List<Notes>>

    @Query("SELECT * FROM tb_notes WHERE title LIKE  :query")
    fun searchNoteByQuery(query: String): LiveData<List<Notes>>

    @Query("SELECT * FROM tb_notes ORDER BY CASE WHEN priority LIKE 'H%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'L%' THEN 3 END")
    fun sortByHighPriority() : LiveData<List<Notes>>

    @Query("SELECT * FROM tb_notes ORDER BY CASE WHEN priority LIKE 'H%' THEN 3 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'L%' THEN 1 END")
    fun sortByLowPriority() : LiveData<List<Notes>>

    @Query("DELETE FROM tb_notes")
    suspend fun deleteAllData()

    @Delete
    suspend fun deleteNote(notes: Notes)

    @Update
    suspend fun updateNote(notes: Notes)

}