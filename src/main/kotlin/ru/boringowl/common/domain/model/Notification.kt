package ru.boringowl.common.domain.model

import kotlinx.serialization.Serializable

@Serializable
class CodeNotification(
    val receiverAddress: String,
    val receiverName: String,
    val type: CodeNotificationType,
    val code: String
)

@Serializable
class CommonNotification(
    val to: String,
    val by: String,
    val at: Long,
    val where: String,
    val body: String,
    val type: CommonNotificationType,
)

@Serializable
enum class CodeNotificationType {
    RESET_PASSWORD,
    VERIFY_EMAIL,
    SIGN_UP
}

@Serializable
enum class CommonNotificationType {
    SYSTEM, USER, COMMUNITY
}
