package com.example.notesapp.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

//A single entity contains all this features(properties) of the Notes table

@Parcelize
@Entity(tableName = "Notes")            //Notes is the name of the table(DB)
class Notes (

    @PrimaryKey(autoGenerate = true)        //auto generates the primary key
     var id : Int? = null,
    var title: String,
    var subTitle: String,
    var notes: String,
    var date: String,
    var priority: String

        ) : Parcelable