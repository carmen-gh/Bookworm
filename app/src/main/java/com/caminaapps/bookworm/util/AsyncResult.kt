package com.caminaapps.bookworm.util

import com.caminaapps.bookworm.util.AsyncResult.Failure
import com.caminaapps.bookworm.util.AsyncResult.Loading
import com.caminaapps.bookworm.util.AsyncResult.Success
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

/**
 * A discriminated union that encapsulates a loading state, a successful outcome with data of
 * type [T] or a error outcome with an arbitrary [Throwable] exception.
 * Main difference to Kotlin.Result class is the additional loading state. This can be helpful for
 * example when trigger a long running operation and you need the information somewhere else that
 * this is already triggered and still in loading.
 */
sealed interface AsyncResult<out T> {
    object Loading : AsyncResult<Nothing>
    data class Success<T>(val data: T) : AsyncResult<T>
    data class Failure(val exception: Throwable? = null) : AsyncResult<Nothing>
}

/**
 * Returns a flow contains the encapsulate result as a [AsyncResult] to each value of the original flow,
 * onStart the [loading][AsyncResult.Loading] state is automatically emitted.
 * It catches exceptions in the flow and emits a [failure][AsyncResult.Failure] with the encapsulated exception.
 * This operator is *transparent* to exceptions that occur in downstream flow and does not catch
 * exceptions that are thrown to cancel the flow
 */
fun <T> Flow<T>.asAsyncResult(): Flow<AsyncResult<T>> {
    return this
        .map<T, AsyncResult<T>> { Success(it) }
        .onStart { emit(Loading) }
        .catch { emit(Failure(it)) }
}

/**
 * Returns the encapsulated value if this instance represents [Success][AsyncResult.Success] or `null`
 * if it is [Failure][AsyncResult.Failure] or [Loading][AsyncResult.Loading].
 */
val <T> AsyncResult<T>.dataOrNull: T?
    get() = (this as? Success)?.data

/**
 * Returns the encapsulated [Throwable] exception if this instance represents [Failure][AsyncResult.Failure] or `null`
 * if it is [Success][isSuccess] or [Loading][AsyncResult.Loading].
 *
 */
val <T> AsyncResult<T>.exceptionOrNull: Throwable?
    get() = (this as? Failure)?.exception

val <T> AsyncResult<T>.isLoading: Boolean
    get() = this is Loading

val <T> AsyncResult<T>.isSuccess: Boolean
    get() = this is Success

val <T> AsyncResult<T>.isFailure: Boolean
    get() = this is Failure

/**
 * Performs the given [action] if this instance represents [Loading][AsyncResult.Loading].
 * Returns the original `AsyncResult` unchanged.
 */
inline fun <T> AsyncResult<T>.onLoading(action: () -> Unit): AsyncResult<T> {
    if (isLoading) {
        action()
    }
    return this
}

/**
 * Performs the given [action] on the encapsulated value if this instance represents [Success][AsyncResult.Success].
 * Returns the original `AsyncResult` unchanged.
 */
inline fun <T> AsyncResult<T>.onSuccess(action: (data: T) -> Unit): AsyncResult<T> {
    (this as? Success)?.let {
        action(it.data)
    }
    return this
}

/**
 * Performs the given [action] on the encapsulated throwable if this instance represents [Failure][AsyncResult.Failure].
 * Returns the original `AsyncResult` unchanged.
 */
inline fun <T> AsyncResult<T>.onFailure(action: (exception: Throwable?) -> Unit): AsyncResult<T> {
    (this as? Failure)?.let {
        action(it.exception)
    }
    return this
}

/**
 * Returns a flow that invokes the given action if the upstream emits an item of type [AsyncResult.Success]
 */
fun <T> Flow<AsyncResult<T>>.onSuccess(action: suspend (T) -> Unit): Flow<AsyncResult<T>> {
    return this.onEach { result ->
        (result as? Success)?.let {
            action(it.data)
        }
    }
}

/**
 * Returns a flow that invokes the given action if the upstream emits an item of type [AsyncResult.Failure]
 */
fun <T> Flow<AsyncResult<T>>.onFailure(action: suspend (Throwable?) -> Unit): Flow<AsyncResult<T>> {
    return this.onEach { result ->
        (result as? Failure)?.let {
            action(it.exception)
        }
    }
}

/**
 * Returns a flow that invokes the given action if the upstream emits an item of type [AsyncResult.Loading]
 */
fun <T> Flow<AsyncResult<T>>.onLoading(action: suspend () -> Unit): Flow<AsyncResult<T>> {
    return this.onEach { result ->
        if (result.isLoading) {
            action()
        }
    }
}
