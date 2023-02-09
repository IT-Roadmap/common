package ru.boringowl.common.domain.model

import kotlinx.serialization.Serializable

@Serializable
sealed class Notification {
    abstract val receiverAddress: String
    abstract val receiverName: String
}

@Serializable
class CodeNotification(
    override val receiverAddress: String,
    override val receiverName: String,
    val type: NotificationType,
    val code: String
): Notification()

@Serializable
enum class NotificationType {
    RESET_PASSWORD,
    VERIFY_EMAIL,
    SIGN_UP
}
