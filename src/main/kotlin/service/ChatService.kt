package service

import chat.Chat
import chat.IChat
import chat.Message

object ChatService : IChat<Chat, Message> {

    private val chats = mutableListOf<Chat>()
    private var idChat: Int = 0

    override fun add(elementChat: Chat): Chat {
        chats += elementChat.copy(id = ++idChat)
        return chats.last().apply {
            ++countMessage
        }
    }

    override fun createMessage(elementMessage: Message): Boolean {
        chats.find {
            (it.idUserSender == elementMessage.idUserSender && it.idUserRecipient == elementMessage.idUserRecipient)
                    || (it.idUserSender == elementMessage.idUserRecipient && it.idUserRecipient == elementMessage.idUserSender)
        }
            ?.apply {
                this.messages.plusAssign(
                    elementMessage.copy(
                        idChat = id,
                        id = lastMessage!!.id + 1
                    )
                )
                lastMessage = messages.last()
                ++countMessage
            } ?: throw MessageException("chat with these users does not exist")
        return true
    }

    override fun deleteChat(chatId: Int): Boolean {
        chats.find { it.id == chatId && !it.delete }?.apply { this.delete = true }
            ?: throw ChatNotIdException("Chat with this id: $chatId does not exist")
        return true
    }

    override fun deleteMessage(idChat: Int, messageId: Int): Boolean {
        chats.find { it.id == idChat }?.apply { countMessage-- }?.messages?.find { it.id == messageId }
            ?.apply {
                delete = true
                markRead = true
            }
            ?: throw MessageException("elements with given parameters cannot be deleted")
        chats.find { it.id == idChat && it.countMessage == 0 }?.apply {
            this.delete = true
            this.markRead = true
        }
        chats.lastElementRemovalCheck(idChat)
        return true
    }

    override fun getChats(idUser: Int): List<Chat> {
        val chat = chats.filter { (it.idUserRecipient == idUser || it.idUserSender == idUser) && !it.delete }
        if (chat.isNotEmpty()) return chat
        else throw MessageException("elements with given parameters cannot be deleted")
    }

    override fun getMessage(idUser: Int, idChat: Int, countMessage: Int): List<Message> {
        return chats.readMessage(idUser, idChat, countMessage)
    }

    override fun editMessage(elementMessage: Message): Boolean {
        chats.find {
            it.idUserSender == elementMessage.idUserSender && it.idUserRecipient == elementMessage.idUserRecipient
        }?.apply {
            messages.find { it.id == elementMessage.id }?.apply { textMessage = elementMessage.textMessage }
                ?: throw MessageException("element with given id not found")
        } ?: throw ChatException("chat with these users does not exist")
        return true
    }

    fun getUnreadChatsCount(): Int {
        return chats.count { !it.markRead }
    }

    /**
     * [getReadChatsCount], [clearAllChat], [chatMarkDelete] - Test Methods
     */
    fun getReadChatsCount(idChat: Int): Int {
        return chats.find { it.id == idChat }!!.messages.count { it.markRead }
    }

    fun clearAllChat() {
        chats.clear()
        idChat = 0
    }

    fun chatMarkDelete(idChat: Int): Chat {
        return chats.find { it.id == idChat && it.delete } ?: throw ChatException("chat not deleted")
    }
}

private fun List<Chat>.readMessage(idUser: Int, idChat: Int, countMessage: Int): List<Message> {
    val messageRead =
        this.find { it -> it.id == idChat && !it.delete && countMessage <= it.messages.count { it.idUserRecipient == idUser && !it.delete } }
            ?.messages?.filter { it.idChat == idChat && it.idUserRecipient == idUser && !it.delete }?.take(countMessage)
            ?.onEach { it.markRead = true }
            ?: throw MessageException("element with given index not found")
    this.find { it -> it.id == idChat && it.messages.all { it.markRead } }?.apply { markRead = true }
    return messageRead
}

private fun List<Chat>.lastElementRemovalCheck(idChat: Int) {
    val elem =
        this.find { it.id == idChat }?.messages?.filter { it.idChat == idChat }
            ?.sortedByDescending { it.id }
            ?.find { !it.delete }
    this.find { it.lastMessage?.delete == true }.apply { this?.lastMessage = elem }
}

class MessageException(message: String) : RuntimeException(message)
class ChatException(message: String) : RuntimeException(message)
class ChatNotIdException(message: String) : RuntimeException(message)