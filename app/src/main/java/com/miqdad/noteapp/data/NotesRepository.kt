package com.miqdad.noteapp.data

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.miqdad.noteapp.data.locale.entity.Notes
import com.miqdad.noteapp.data.locale.room.NotesDao

class NotesRepository(private val notesDao: NotesDao) {

    val getAllNotes : LiveData<List<Notes>> = notesDao.getAllNote()
    val sortByHighPriority : LiveData<List<Notes>> = notesDao.sortByHighPriority()
    val sortByLowPriority : LiveData<List<Notes>> = notesDao.sortByLowPriority()

    suspend fun insertNotes(notes: Notes){
        notesDao.addNote(notes)
    }

    fun searchNoteByQuery(query: String): LiveData<List<Notes>> {
        return notesDao.searchNoteByQuery(query)
    }

    suspend fun deleteAllData(){
        return notesDao.deleteAllData()
    }

    suspend fun deleteNote(notes: Notes){
        return notesDao.deleteNote(notes)
    }

    suspend fun updateNote(notes: Notes){
        return notesDao.updateNote(notes)
    }

}