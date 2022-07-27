import chat.Chat
import chat.Message
import service.ChatService
import java.util.*

fun main() {

//    val message = Message(
//        textMessage = "Первый отправитель",
//        idUserSender = 1,
//        idUserRecipient = 2,
//        date = Date()
//    )
//
//    val message2 = Message(
//        textMessage = "второй отправитель",
//        idUserSender = 2,
//        idUserRecipient = 1,
//        date = Date()
//    )
//
//    val message3 = Message(
//        textMessage = "второй отправитель",
//        idUserSender = 1,
//        idUserRecipient = 3,
//        date = Date()
//    )
//
//    val chat = Chat(
//        lastMessage = message,
//        date = Date()
//    )
//
//    val chat2 = Chat(
//        lastMessage = message2,
//        date = Date()
//    )
//
//    val service = ChatService
//    service.add(chat)
//    service.add(chat2)
////    service.createMessage(message2)
////    service.createMessage(message2)
////    service.createMessage(1, message2)
////    service.deleteMessage(idChat = 1, messageId = 2)
////    println(service.getMessage(2, 1, 2))
////    println(service.getMessage(2, 1, 2))
////    service.deleteMessage(1,1)
//    println(service.getMessage(2,1,1))
////    println(service.getMessage(2,1,1))
////    println(service.getMessage(1,2,1))
//    println(service.getUnreadChatsCount())

    val message = Message(
        textMessage = "Тест сообщение",
        idUserSender = 1,
        idUserRecipient = 2,
        date = Date()
    )

    val messageTest = Message(
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
    service.createMessage(messageTest)
//    service.deleteMessage(1, 1)
    println(service.getMessage(2, 1, 2).joinToString("\n"))
    println(service.getUnreadChatsCount())
}
