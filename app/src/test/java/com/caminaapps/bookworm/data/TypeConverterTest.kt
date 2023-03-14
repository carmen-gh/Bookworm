package com.caminaapps.bookworm.data

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.caminaapps.bookworm.core.data.database.Converters
import kotlinx.datetime.LocalDate
import org.junit.Test

class TypeConverterTest {

    @Test
    fun test_room_converter_to_localDate() {
        assertThat(Converters.intToLocalDate(19427))
            .isEqualTo(LocalDate.fromEpochDays(19427))
    }

    @Test
    fun test_room_converter_from_localDate() {
        val date = LocalDate.fromEpochDays(19427)
        assertThat(Converters.localDateToInt(date)).isEqualTo(19427)
    }
}
