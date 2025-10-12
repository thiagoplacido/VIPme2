package com.seuapp.vipme2.data.local

import androidx.room.TypeConverter
import com.seuapp.vipme2.data.local.model.MovementType
import java.util.Date

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun toMovementType(value: String) = enumValueOf<MovementType>(value)

    @TypeConverter
    fun fromMovementType(value: MovementType) = value.name
}