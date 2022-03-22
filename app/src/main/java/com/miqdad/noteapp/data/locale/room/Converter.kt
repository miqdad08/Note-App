package com.miqdad.noteapp.data.locale.room

import androidx.room.TypeConverter
import com.miqdad.noteapp.data.locale.entity.Priority

class Converter {
    @TypeConverter
    fun fromPriority(priority: Priority) : String{
        return priority.toString()
    }

    @TypeConverter
    fun toPriority(priority: String) : Priority{
        return Priority.valueOf(priority)
    }
}