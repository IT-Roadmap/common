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
    val result = action()
    val code = if (result is BaseResponse) result.code else HttpStatus.OK
    emit(status(code).body(result))
}.catch {
    val response = if (it is ResponseException)
        status(it.code).body(MessageResponse(it.message ?: "No message"))
    else
        internalServerError().build<Unit>()
    emit(response)
}.first()
