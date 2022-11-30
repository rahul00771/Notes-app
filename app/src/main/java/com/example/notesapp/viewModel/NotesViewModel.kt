package com.example.notesapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.notesapp.Database.NotesDatabase
import com.example.notesapp.Repository.NotesRepository
import com.example.notesapp.entity.Notes

class NotesViewModel(application: Application): AndroidViewModel(application) {         //extends to application context

    val repository : NotesRepository

    init {                                                                                //init block runs at th every initial stage
        val dao = NotesDatabase.getDatabaseInstance(application).myNotesDao()
        repository = NotesRepository(dao)
    }

    //accessing the dao functions not directly from the Dao, but from the repository maintaining the MVVM architecture

    fun getNotes() : LiveData<List<Notes>> {
        return repository.getAllNotes()
    }

    fun addNotes(notes: Notes)
    {
        repository.insertNotes(notes)
    }

    fun updateNotes(notes: Notes)
    {
        repository.updateNotes(notes)
    }

    /*fun deleteNotes(id : Int)
    {
        repository.deleteNotes(id)
    }*/

    fun deleteNotes(notes: Notes)
    {
        repository.deleteNotes(notes)
    }

    fun getHighNotes() : LiveData<List<Notes>> = repository.getHighNotes()
    fun getMediumNotes() : LiveData<List<Notes>> = repository.getMediumNotes()
    fun getLowNotes() : LiveData<List<Notes>> = repository.getLowNotes()


}