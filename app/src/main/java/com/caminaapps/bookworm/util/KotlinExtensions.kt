package com.caminaapps.bookworm.util

/**
 * Returns a list containing all elements of the original collection replaced all elements
 * matching the given [predicate] with [newValue].
 */
inline fun <T> List<T>.replace(newValue: T, predicate: (T) -> Boolean): List<T> {
    return map { element ->
        if (predicate(element)) {
            newValue
        } else {
            element
        }
    }
}

/**
 * Returns a list containing all elements of the original collection replaced first element
 * matching the given [predicate] with [newValue].
 */
@Suppress("VariableNaming")
inline fun <T> List<T>.replaceFirst(newValue: T, predicate: (T) -> Boolean): List<T> {
    val _list = this.toMutableList()
    for ((index, value) in this.withIndex()) {
        if (predicate(value)) {
            _list[index] = newValue
            break
        }
    }
    return _list
}
