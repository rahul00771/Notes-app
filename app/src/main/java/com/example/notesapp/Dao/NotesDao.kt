package com.example.notesapp.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.notesapp.entity.Notes


@Dao
interface NotesDao {

    @Query("SELECT * FROM Notes")           //to get notes of type entity in the form of LiveData
    fun getNotes() : LiveData<List<Notes>>

    @Insert(onConflict = REPLACE)           //to insert a new entry(entity)
    fun insertNotes(notes: Notes)

    @Update
    fun updateNotes(notes: Notes)           //to update an entry(entity)

    @Query("DELETE FROM Notes WHERE id = :id")       //to delete an entry with id = id
    fun delete(id: Int)

    //@Delete
    //fun delete(notes: Notes)

    @Query("SELECT * FROM Notes WHERE priority = 3 ")         //to get notes of high priority
    fun getHighNotes() : LiveData<List<Notes>>

    @Query("SELECT * FROM Notes WHERE priority = 2 ")           //to get notes of medium priority
    fun getMediumNotes() : LiveData<List<Notes>>

    @Query("SELECT * FROM Notes WHERE priority = 1 ")              //to get notes of low priority
    fun getLowNotes() : LiveData<List<Notes>>


}