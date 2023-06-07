package com.caminaapps.bookworm

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.hasMessage
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.isTrue
import assertk.fail
import com.caminaapps.bookworm.util.AsyncResult
import com.caminaapps.bookworm.util.asAsyncResult
import com.caminaapps.bookworm.util.dataOrNull
import com.caminaapps.bookworm.util.exceptionOrNull
import com.caminaapps.bookworm.util.isFailure
import com.caminaapps.bookworm.util.isLoading
import com.caminaapps.bookworm.util.isSuccess
import com.caminaapps.bookworm.util.onFailure
import com.caminaapps.bookworm.util.onLoading
import com.caminaapps.bookworm.util.onSuccess
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test

class AsyncResultTest {

    @Test
    fun asAsyncResult_emits_failure_when_exception_gets_thrown() = runTest {
        val message = "test done"

        flow {
            emit(1)
            throw IllegalArgumentException(message)
        }.asAsyncResult().test {
            skipItems(2)
            val item = awaitItem()
            awaitComplete()

            assertThat(item).isInstanceOf(AsyncResult.Failure::class)
            assertThat(item.isFailure).isTrue()
            assertThat(item.exceptionOrNull).isNotNull().hasMessage(message)
            assertThat(item.dataOrNull).isNull()
        }
    }

    @Test
    fun asAsyncResult_emits_loading_when_start_collecting() = runTest {
        emptyFlow<Nothing>().asAsyncResult().test {
            val item = awaitItem()
            awaitComplete()

            assertThat(item).isInstanceOf(AsyncResult.Loading::class)
            assertThat(item.isLoading).isTrue()
            assertThat(item.exceptionOrNull).isNull()
        }
    }

    @Test
    fun asAsyncResult_emits_success_when_value_gets_emitted() = runTest {
        flow {
            emit(1)
        }.asAsyncResult().test {
            skipItems(1)
            val item = awaitItem()
            awaitComplete()

            assertThat(item).isInstanceOf(AsyncResult.Success::class)
            assertThat(item.isSuccess).isTrue()
            assertThat(item.dataOrNull).isNotNull().isEqualTo(1)
            assertThat(item.exceptionOrNull).isNull()
        }
    }

    @Test
    fun onFailure_executes_given_action_in_failure_case() {
        var gotExecuted = false
        val failureResult = AsyncResult.Failure(IllegalArgumentException())
        failureResult.onFailure {
            gotExecuted = true
        }
        assertThat(gotExecuted).isTrue()
    }

    @Test
    fun onFailure_gets_not_executed_in_success_case() {
        val successResult = AsyncResult.Success(true)
        successResult.onFailure {
            fail("onFailure should not be executed in success case")
        }
    }

    @Test
    fun onFailure_gets_not_executed_in_loading_case() {
        val loadingResult = AsyncResult.Loading
        loadingResult.onFailure {
            fail("onFailure should not be executed in loading case")
        }
    }

    @Test
    fun onSuccess_executes_given_action_in_success_case() {
        var gotExecuted = false
        val successResult = AsyncResult.Success(true)
        successResult.onSuccess {
            gotExecuted = true
        }
        assertThat(gotExecuted).isTrue()
    }

    @Test
    fun onSuccess_gets_not_executed_in_failure_case() {
        val failureResult = AsyncResult.Failure()
        failureResult.onSuccess {
            fail("onSuccess should not be executed in failure case")
        }
    }

    @Test
    fun onSuccess_gets_not_executed_in_loading_case() {
        val loadingResult = AsyncResult.Loading
        loadingResult.onSuccess {
            fail("onSuccess should not be executed in loading case")
        }
    }

    @Test
    fun onLoading_gets_not_executed_in_success_case() {
        val successResult = AsyncResult.Success(true)
        successResult.onLoading {
            fail("onLoading should not be executed in failure case")
        }
    }

    @Test
    fun onLoading_gets_not_executed_in_failure_case() {
        val failureResult = AsyncResult.Failure()
        failureResult.onLoading {
            fail("onLoading should not be executed in failure case")
        }
    }

    @Test
    fun onLoading_gets_executed_in_loading_case() {
        var gotExecuted = false
        val loadingResult = AsyncResult.Loading
        loadingResult.onLoading {
            gotExecuted = true
        }
        assertThat(gotExecuted).isTrue()
    }
}
