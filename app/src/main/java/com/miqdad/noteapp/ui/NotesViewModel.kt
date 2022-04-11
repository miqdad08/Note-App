package com.miqdad.noteapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Query
import com.miqdad.noteapp.data.NotesRepository
import com.miqdad.noteapp.data.locale.entity.Notes
import com.miqdad.noteapp.data.locale.room.NotesDB
import com.miqdad.noteapp.data.locale.room.NotesDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {
    private val notesDao : NotesDao = NotesDB.getDataBase(application).notesDao()
    private val notesRepository : NotesRepository = NotesRepository(notesDao)

    val sortByHighPriority : LiveData<List<Notes>> = notesRepository.sortByHighPriority
    val sortByLowPriority : LiveData<List<Notes>> = notesRepository.sortByLowPriority


    fun getAllNote() : LiveData<List<Notes>> = notesRepository.getAllNotes


    fun insertNotes(notes: Notes){
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.insertNotes(notes)
        }
    }

    fun searchNotesByQuery(query: String) : LiveData<List<Notes>>{
        return notesRepository.searchNoteByQuery(query)
    }

    fun  deleteAllNote(){
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.deleteAllData()
        }
    }

    fun deleteNote(notes: Notes){
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.deleteNote(notes)
        }
    }

    fun updateData(notes: Notes){
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.updateNote(notes)
        }
    }
}