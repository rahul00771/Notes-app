package com.example.notesapp.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notesapp.Dao.NotesDao
import com.example.notesapp.entity.Notes


@Database(entities = [Notes::class], version = 1)
abstract class NotesDatabase:RoomDatabase() {        //abstract

    abstract fun myNotesDao() : NotesDao            //gives the DAO

    companion object{

        @Volatile                                   //visible to other threads
        var INSTANCE: NotesDatabase? = null         //main instance

        fun getDatabaseInstance(context: Context) : NotesDatabase {         //function to return the instance of DB

            val tempInstance = INSTANCE                     //temp instance

            if (tempInstance != null) {                     //if not null, means it has DB, so return the instance
                return tempInstance
            }
            synchronized(this)                          //synchronized is used as the INSTANCE is made volatile, something related to threads
            {                                               //if it is null, then we have to initialize the instance by using databaseBuilder
                val roomDatabaseInstance =
                    Room.databaseBuilder(context, NotesDatabase::class.java, "Notes").allowMainThreadQueries().build()    //builds DB and allows on main thread
                INSTANCE = roomDatabaseInstance

                return roomDatabaseInstance                 //returning the DB instance
            }

        }

    }

}