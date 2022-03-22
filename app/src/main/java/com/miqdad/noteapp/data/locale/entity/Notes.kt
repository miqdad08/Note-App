package com.miqdad.noteapp.data.locale.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "tb_notes")
@Parcelize
data class Notes(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title : String,
    var desc: String,
    var date : String,
    var priority : Priority
):Parcelable

