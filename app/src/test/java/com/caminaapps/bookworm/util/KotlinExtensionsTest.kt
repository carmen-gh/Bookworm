package com.caminaapps.bookworm.util

import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.isEqualTo
import org.junit.Test

class KotlinExtensionsTest {

    @Test
    fun replace_when_predicate_matching() {
        val list = listOf(1, 2, 3, 4, 5, 1)
        val newList = list.replace(9) { it == 1 }
        assertThat(newList).containsExactly(9, 2, 3, 4, 5, 9)
    }

    @Test
    fun replace_when_predicate_no_match() {
        val list = listOf(1, 2, 3, 4, 5, 1)
        val newList = list.replace(9) { it == 8 }
        assertThat(newList).isEqualTo(list)
    }

    @Test
    fun replaceFirst_when_predicate_matching_then_first_is_replaced() {
        val list = listOf(1, 2, 3, 4, 5, 1)
        val newList = list.replaceFirst(9) { it == 1 }
        assertThat(newList).containsExactly(9, 2, 3, 4, 5, 1)
    }

    @Test
    fun replaceFirst_when_predicate_not_matching() {
        val list = listOf(1, 2, 3, 4, 5, 1)
        val newList = list.replaceFirst(9) { it == 8 }
        assertThat(newList).isEqualTo(list)
    }
}
