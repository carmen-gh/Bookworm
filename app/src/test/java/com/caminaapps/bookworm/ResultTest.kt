package com.caminaapps.bookworm

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.hasMessage
import assertk.assertions.isInstanceOf
import assertk.assertions.isNotNull
import com.caminaapps.bookworm.util.Result
import com.caminaapps.bookworm.util.asResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ResultTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun result_catches_errors() = runTest {
        val message = "test done"

        flow {
            emit(1)
            throw IllegalArgumentException(message)
        }
            .asResult()
            .test {
                assertThat(awaitItem()).isInstanceOf(Result.Loading::class)
                assertThat(awaitItem()).isInstanceOf(Result.Success(1)::class)
                val error = awaitItem() as Result.Error
                assertThat(error.exception).isNotNull().hasMessage(message)
                awaitComplete()
            }
    }
}
