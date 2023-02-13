package ru.boringowl.common.response

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import ru.boringowl.common.domain.dto.BaseResponse
import ru.boringowl.common.domain.dto.MessageResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.*

suspend fun <T> response(
    action: suspend () -> T
) = flow<ResponseEntity<*>> {
    emit(processResult(action()))
}.catch {
    emit(processException(it))
}.first()

fun <T> response(
    action: () -> T
): ResponseEntity<*> = try {
    processResult(action())
} catch (e: Exception) {
    processException(e)
}

private fun <T> processResult(result: T): ResponseEntity<T> {
    val code = if (result is BaseResponse) result.code else HttpStatus.OK
    return status(code).body(result)
}

private fun processException(e: Throwable): ResponseEntity<out Any> {
    return if (e is ResponseException)
        status(e.code).body(MessageResponse(e.message ?: "No message"))
    else
        internalServerError().build<Unit>()
}
