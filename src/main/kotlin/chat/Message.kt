package chat

import attachments.Attachment
import java.util.*

data class Message(
    val id: Int = 1,
    var idChat: Int = 0,
    val idUserSender: Int,
    val idUserRecipient: Int,
    var textMessage: String,
    val date: Date,
    val attachments: Array<Attachment> = emptyArray(),
    var markRead: Boolean = false
) {
    var delete = false
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Message

        if (!attachments.contentEquals(other.attachments)) return false

        return true
    }

    override fun hashCode(): Int {
        return attachments.contentHashCode()
    }
}
