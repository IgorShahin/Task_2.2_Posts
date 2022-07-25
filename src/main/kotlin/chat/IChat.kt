package chat

interface IChat<C, M> {

    fun add(elementChat: C): C

    fun createMessage(elementMessage: M): Boolean

    fun deleteChat(chatId: Int): Boolean

    fun deleteMessage(idChat: Int, messageId: Int): Boolean

    fun editMessage(elementMessage: M): Boolean

    fun getChats(idUser: Int): List<C?>

    fun getMessage(idUser: Int, idChat: Int, countMessage: Int): List<M>
}