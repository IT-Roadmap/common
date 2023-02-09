package ru.boringowl.common.response

import org.springframework.http.HttpStatus

class ResponseException(val code: HttpStatus, message: String) : Exception(message)
