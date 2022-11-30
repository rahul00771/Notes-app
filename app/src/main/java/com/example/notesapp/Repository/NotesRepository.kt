package com.example.notesapp.Repository

import androidx.lifecycle.LiveData
import com.example.notesapp.Dao.NotesDao
import com.example.notesapp.entity.Notes


// to access the DAO from ViewModel, we create repository
// we call the functions of Dao in this repo, in VM we will use functions from this repo

class NotesRepository(val dao: NotesDao) {

    fun getAllNotes(): LiveData<List<Notes>> {
        return dao.getNotes()
    }

    fun insertNotes(notes: Notes)
    {
        dao.insertNotes(notes)
    }

    fun updateNotes(notes: Notes)
    {
        dao.updateNotes(notes)
    }

    //fun deleteNotes(id : Int)
   // {
      //  dao.delete(id)
    //}

    fun deleteNotes(notes: Notes)
    {
        dao.delete(notes)
    }

    fun getHighNotes() : LiveData<List<Notes>> = dao.getHighNotes()
    fun getMediumNotes() : LiveData<List<Notes>> = dao.getMediumNotes()
    fun getLowNotes() : LiveData<List<Notes>> = dao.getLowNotes()



}