package com.caminaapps.bookworm.core.data.database

import androidx.room.TypeConverter
import kotlinx.datetime.LocalDate

object Converters {

    @TypeConverter
    fun intToLocalDate(value: Int?): LocalDate? = value?.let(LocalDate::fromEpochDays)

    @TypeConverter
    fun localDateToInt(localDate: LocalDate?): Int? = localDate?.toEpochDays()
}
