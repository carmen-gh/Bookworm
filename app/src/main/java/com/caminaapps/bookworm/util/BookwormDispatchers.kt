package com.caminaapps.bookworm.util

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val bookwormDispatcher: BookwormDispatchers)

enum class BookwormDispatchers {
    IO
}
