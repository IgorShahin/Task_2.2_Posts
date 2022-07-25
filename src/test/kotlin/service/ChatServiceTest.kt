package service

import chat.Chat
import chat.Message
import org.junit.Assert.*
import org.junit.Test
import java.util.*

class ChatServiceTest {

    @Test
    fun add() {
        ChatService.clearAllChat()

        val message = Message(
            textMessage = "Тест сообщение",
            idUserSender = 1,
            idUserRecipient = 2,
            date = Date()
        )

        val chat = Chat(
            lastMessage = message,
            date = Date()
        )
        val service = ChatService
        val result = service.add(chat).id
        assertEquals(1, result)
    }

    @Test(expected = MessageException::class)
    fun createMessageShouldThrowUserSender() {
        ChatService.clearAllChat()

        val message = Message(
            textMessage = "Тест сообщение",
            idUserSender = 1,
            idUserRecipient = 2,
            date = Date()
        )

        val newMessage = Message(
            textMessage = "Тест сообщение 2",
            idUserSender = 3,
            idUserRecipient = 1,
            date = Date()
        )

        val chat = Chat(
            lastMessage = message,
            date = Date()
        )
        val service = ChatService
        service.add(chat)
        service.createMessage(newMessage)
    }

    @Test(expected = MessageException::class)
    fun createMessageShouldThrowUserRecipient() {
        ChatService.clearAllChat()

        val message = Message(
            textMessage = "Тест сообщение",
            idUserSender = 1,
            idUserRecipient = 2,
            date = Date()
        )

        val newMessage = Message(
            textMessage = "Тест сообщение 2",
            idUserSender = 1,
            idUserRecipient = 3,
            date = Date()
        )

        val chat = Chat(
            lastMessage = message,
            date = Date()
        )
        val service = ChatService
        service.add(chat)
        service.createMessage(newMessage)
    }

    @Test
    fun createMessageShouldNoThrow() {
        ChatService.clearAllChat()

        val message = Message(
            textMessage = "Тест сообщение",
            idUserSender = 1,
            idUserRecipient = 2,
            date = Date()
        )

        val newMessage = Message(
            textMessage = "Тест сообщение 2",
            idUserSender = 2,
            idUserRecipient = 1,
            date = Date()
        )

        val chat = Chat(
            lastMessage = message,
            date = Date()
        )
        val service = ChatService
        service.add(chat)
        service.createMessage(message)
        val result = service.createMessage(newMessage)
        assertTrue(result)
    }

    @Test(expected = ChatNotIdException::class)
    fun deleteChatShouldThrow() {
        ChatService.clearAllChat()

        val message = Message(
            textMessage = "Тест сообщение",
            idUserSender = 1,
            idUserRecipient = 2,
            date = Date()
        )

        val chat = Chat(
            lastMessage = message,
            date = Date()
        )
        val service = ChatService
        service.add(chat)
        service.deleteChat(2)
    }

    @Test(expected = ChatNotIdException::class)
    fun deleteChatShouldThrowMarkedDeleted() {
        ChatService.clearAllChat()

        val message = Message(
            textMessage = "Тест сообщение",
            idUserSender = 1,
            idUserRecipient = 2,
            date = Date()
        )

        val chat = Chat(
            lastMessage = message,
            date = Date()
        )
        val service = ChatService
        service.add(chat)
        service.deleteChat(1)
        service.deleteChat(1)
    }

    @Test
    fun deleteChatShouldNoThrow() {
        ChatService.clearAllChat()

        val message = Message(
            textMessage = "Тест сообщение",
            idUserSender = 1,
            idUserRecipient = 2,
            date = Date()
        )

        val chat = Chat(
            lastMessage = message,
            date = Date()
        )
        val service = ChatService
        service.add(chat)
        val result = service.deleteChat(1)
        assertTrue(result)
    }

    @Test(expected = MessageException::class)
    fun deleteMessageShouldThrow() {
        ChatService.clearAllChat()

        val message = Message(
            textMessage = "Тест сообщение",
            idUserSender = 1,
            idUserRecipient = 2,
            date = Date()
        )

        val chat = Chat(
            lastMessage = message,
            date = Date()
        )
        val service = ChatService
        service.add(chat)
        service.createMessage(message)
        service.deleteMessage(1, 3)
    }

    @Test(expected = MessageException::class)
    fun deleteMessageShouldThrowIdChat() {
        ChatService.clearAllChat()

        val message = Message(
            textMessage = "Тест сообщение",
            idUserSender = 1,
            idUserRecipient = 2,
            date = Date()
        )

        val chat = Chat(
            lastMessage = message,
            date = Date()
        )
        val service = ChatService
        service.add(chat)
        service.createMessage(message)
        service.deleteMessage(2, 1)
    }

    @Test
    fun deleteMessageShouldNoThrow() {
        ChatService.clearAllChat()

        val message = Message(
            textMessage = "Тест сообщение",
            idUserSender = 1,
            idUserRecipient = 2,
            date = Date()
        )

        val chat = Chat(
            lastMessage = message,
            date = Date()
        )
        val service = ChatService
        service.add(chat)
        service.createMessage(message)
        val result = service.deleteMessage(1, 1)
        assertTrue(result)
    }

    @Test
    fun deleteMessageLast() {
        ChatService.clearAllChat()

        val message = Message(
            textMessage = "Тест сообщение",
            idUserSender = 1,
            idUserRecipient = 2,
            date = Date()
        )

        val chat = Chat(
            lastMessage = message,
            date = Date()
        )
        val service = ChatService
        service.add(chat)
        service.deleteMessage(1, 1)
        val result = service.chatMarkDelete(1).delete
        assertEquals(true, result)
    }

    @Test(expected = MessageException::class)
    fun getChatsShouldThrow() {
        ChatService.clearAllChat()

        val message = Message(
            textMessage = "Тест сообщение",
            idUserSender = 1,
            idUserRecipient = 2,
            date = Date()
        )

        val chat = Chat(
            lastMessage = message,
            date = Date()
        )
        val service = ChatService
        service.add(chat)
        service.getChats(3)
    }

    @Test(expected = MessageException::class)
    fun getChatsShouldThrowMarkedDeleted() {
        ChatService.clearAllChat()

        val message = Message(
            textMessage = "Тест сообщение",
            idUserSender = 1,
            idUserRecipient = 2,
            date = Date()
        )

        val chat = Chat(
            lastMessage = message,
            date = Date()
        )

        val service = ChatService
        service.add(chat)
        service.deleteChat(1)
        service.getChats(1)
    }

    @Test
    fun getChatsShouldNoThrow() {
        ChatService.clearAllChat()

        val message = Message(
            textMessage = "Тест сообщение",
            idUserSender = 1,
            idUserRecipient = 2,
            date = Date()
        )

        val chat = Chat(
            lastMessage = message,
            date = Date()
        )

        val chatResultTest = Chat(
            lastMessage = message,
            countMessage = 1,
            date = Date()
        )
        val listTest = arrayListOf(chatResultTest)
        val service = ChatService
        service.add(chat)
        service.getChats(2)
        val result = service.getChats(1)
        assertEquals(listTest, result)
    }


    @Test(expected = MessageException::class)
    fun getMessageShouldThrowUser() {
        ChatService.clearAllChat()

        val message = Message(
            textMessage = "Тест сообщение",
            idUserSender = 1,
            idUserRecipient = 2,
            date = Date()
        )

        val chat = Chat(
            lastMessage = message,
            date = Date()
        )

        val service = ChatService
        service.add(chat)
        service.getMessage(1, 1, 1)
    }

    @Test(expected = MessageException::class)
    fun getMessageShouldThrowChat() {
        ChatService.clearAllChat()

        val message = Message(
            textMessage = "Тест сообщение",
            idUserSender = 1,
            idUserRecipient = 2,
            date = Date()
        )

        val chat = Chat(
            lastMessage = message,
            date = Date()
        )

        val service = ChatService
        service.add(chat)
        service.getMessage(2, 2, 1)
    }

    @Test(expected = MessageException::class)
    fun getMessageShouldThrowCountMessage() {
        ChatService.clearAllChat()

        val message = Message(
            textMessage = "Тест сообщение",
            idUserSender = 1,
            idUserRecipient = 2,
            date = Date()
        )

        val chat = Chat(
            lastMessage = message,
            date = Date()
        )

        val service = ChatService
        service.add(chat)
        service.getMessage(2, 1, 2)
    }

    @Test(expected = MessageException::class)
    fun getMessageShouldThrowDeleteChat() {
        ChatService.clearAllChat()

        val message = Message(
            textMessage = "Тест сообщение",
            idUserSender = 1,
            idUserRecipient = 2,
            date = Date()
        )

        val chat = Chat(
            lastMessage = message,
            date = Date()
        )

        val service = ChatService
        service.add(chat)
        service.deleteChat(1)
        service.getMessage(2, 1, 1)
    }

    @Test(expected = MessageException::class)
    fun getMessageShouldThrowDeleteMessageUser() {
        ChatService.clearAllChat()

        val message = Message(
            textMessage = "Тест сообщение",
            idUserSender = 1,
            idUserRecipient = 2,
            date = Date()
        )

        val messageTest = Message(
            textMessage = "Тест сообщение 2",
            idUserSender = 2,
            idUserRecipient = 1,
            date = Date()
        )

        val chat = Chat(
            lastMessage = message,
            date = Date()
        )

        val service = ChatService
        service.add(chat)
        service.createMessage(messageTest)
        service.deleteMessage(1, 1)
        service.getMessage(2, 1, 1)
    }

    @Test
    fun getMessageShouldNoThrowMarkChat() {
        ChatService.clearAllChat()

        val message = Message(
            textMessage = "Тест сообщение",
            idUserSender = 1,
            idUserRecipient = 2,
            date = Date()
        )

        val message2 = Message(
            textMessage = "Тест сообщение 2",
            idUserSender = 1,
            idUserRecipient = 2,
            date = Date()
        )

        val chat = Chat(
            lastMessage = message,
            date = Date()
        )

        val service = ChatService
        service.add(chat)
        service.createMessage(message2)
        service.getMessage(2, 1, 2)
        val result = service.getReadChatsCount(1)
        assertEquals(2, result)
    }

    @Test
    fun getMessageShouldNoThrow() {
        ChatService.clearAllChat()

        val message = Message(
            textMessage = "Тест сообщение",
            idUserSender = 1,
            idUserRecipient = 2,
            date = Date()
        )

        val messageTest = Message(
            id = 1,
            idChat = 1,
            textMessage = "Тест сообщение",
            idUserSender = 1,
            idUserRecipient = 2,
            date = Date()
        )

        val chat = Chat(
            lastMessage = message,
            date = Date()
        )

        val service = ChatService
        service.add(chat)
        val listTest = arrayListOf(messageTest)
        val result = service.getMessage(2, 1, 1)
        assertEquals(listTest, result)
    }

    @Test(expected = ChatException::class)
    fun editMessageShouldThrowIdUserSender() {
        ChatService.clearAllChat()

        val message = Message(
            textMessage = "Тест сообщение",
            idUserSender = 1,
            idUserRecipient = 2,
            date = Date()
        )

        val messageUpdate = Message(
            id = 1,
            idChat = 1,
            textMessage = "Обновленное сообщение",
            idUserSender = 2,
            idUserRecipient = 2,
            date = Date()
        )

        val chat = Chat(
            lastMessage = message,
            date = Date()
        )

        val service = ChatService
        service.add(chat)
        service.editMessage(messageUpdate)
    }

    @Test(expected = ChatException::class)
    fun editMessageShouldThrowIdUserRecipient() {
        ChatService.clearAllChat()

        val message = Message(
            textMessage = "Тест сообщение",
            idUserSender = 1,
            idUserRecipient = 2,
            date = Date()
        )

        val messageUpdate = Message(
            id = 1,
            idChat = 1,
            textMessage = "Обновленное сообщение",
            idUserSender = 1,
            idUserRecipient = 1,
            date = Date()
        )

        val chat = Chat(
            lastMessage = message,
            date = Date()
        )

        val service = ChatService
        service.add(chat)
        service.editMessage(messageUpdate)
    }

    @Test(expected = MessageException::class)
    fun editMessageShouldThrowIdMessage() {
        ChatService.clearAllChat()

        val message = Message(
            textMessage = "Тест сообщение",
            idUserSender = 1,
            idUserRecipient = 2,
            date = Date()
        )

        val messageUpdate = Message(
            id = 2,
            idChat = 1,
            textMessage = "Обновленное сообщение",
            idUserSender = 1,
            idUserRecipient = 2,
            date = Date()
        )

        val chat = Chat(
            lastMessage = message,
            date = Date()
        )

        val service = ChatService
        service.add(chat)
        service.editMessage(messageUpdate)
    }

    @Test
    fun editMessageShouldNoThrow() {
        ChatService.clearAllChat()

        val message = Message(
            textMessage = "Тест сообщение",
            idUserSender = 1,
            idUserRecipient = 2,
            date = Date()
        )

        val messageUpdate = Message(
            id = 1,
            idChat = 1,
            textMessage = "Обновленное сообщение",
            idUserSender = 1,
            idUserRecipient = 2,
            date = Date()
        )

        val chat = Chat(
            lastMessage = message,
            date = Date()
        )

        val service = ChatService
        service.add(chat)
        val result = service.editMessage(messageUpdate)
        assertTrue(result)
    }

    @Test
    fun getUnreadChatsCount() {
        ChatService.clearAllChat()

        val message = Message(
            textMessage = "Тест сообщение",
            idUserSender = 1,
            idUserRecipient = 2,
            date = Date()
        )

        val message2 = Message(
            textMessage = "Тест сообщение 2",
            idUserSender = 1,
            idUserRecipient = 2,
            date = Date()
        )

        val chat = Chat(
            lastMessage = message,
            date = Date()
        )

        val chat2 = Chat(
            lastMessage = message2,
            date = Date()
        )

        val service = ChatService
        service.add(chat)
        service.add(chat2)
        service.getMessage(2,1,1)
        val result = service.getUnreadChatsCount()
        assertEquals(1, result)
    }
}