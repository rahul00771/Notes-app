package com.example.notesapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

//A single entity contains all this features(properties) of the Notes table

@Entity(tableName = "Notes")            //Notes is the name of the table(DB)
class Notes (

    @PrimaryKey(autoGenerate = true)        //auto generates the primary key
     var id : Int? = null,
    var title: String,
    var subTitle: String,
    var notes: String,
    var date: String,
    var priority: String

        )