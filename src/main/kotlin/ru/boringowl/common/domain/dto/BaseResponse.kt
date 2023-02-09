package ru.boringowl.common.domain.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.http.HttpStatus


sealed class BaseResponse(@JsonIgnore val code: HttpStatus)

open class StatusResponse(code: HttpStatus): BaseResponse(code)
class MessageResponse(val message: String): BaseResponse(HttpStatus.OK)
