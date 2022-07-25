package chat

import java.util.Date

data class Chat(
    var id: Int = 1,
    val date: Date,
    var countMessage: Int = 0,
    var lastMessage: Message?,
    val idUserSender: Int = lastMessage!!.idUserSender,
    val idUserRecipient: Int = lastMessage!!.idUserRecipient
) {
    val messages = mutableListOf(lastMessage!!.copy(idChat = id))
    var markRead = false
    var delete = false
}
