package com.caminaapps.bookworm

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun something_isCorrect() {
        assertThat("hello").isEqualTo("hello")
        assertThat(false).isFalse()
    }

    @Test
    fun on_isCorrect() {
        assertThat(4).isEqualTo(2 + 3)
        assertThat("hello").isEqualTo("hello")
        assertThat(false).isFalse()
    }
}
