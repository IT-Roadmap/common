package ru.boringowl.common.response

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
inline fun requireOrResponse(condition: Boolean, exception: () -> ResponseException) {
    contract {
        returns() implies condition
    }
    if (!condition) {
        throw exception()
    }
}
