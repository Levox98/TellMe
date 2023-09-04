package com.tellme.data_questions.data.mappers

import androidx.room.TypeConverter
import java.util.Date

class DateTypeConverter {
    @TypeConverter
    fun toLong(date: Date): Long = date.time

    @TypeConverter
    fun fromLong(time: Long): Date = Date(time)
}