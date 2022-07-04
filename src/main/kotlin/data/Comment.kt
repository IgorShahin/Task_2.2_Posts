package data

import attachments.Attachment
import java.util.Date

/**
 * [id] — Идентификатор комментария;
 *
 * [fromId] — Идентификатор автора комментария;
 *
 * [date] — Дата создания комментария;
 *
 * [text] — Текст комментария;
 *
 * [donut] — Информация о VK Donut;
 *
 * [replyToUser] — Идентификатор пользователя или сообщества, в ответ которому оставлен текущий комментарий (если применимо);
 *
 * [replyToComment] — Идентификатор комментария, в ответ на который оставлен текущий (если применимо);
 *
 * [attachments] — Медиавложения комментария (фотографии, ссылки и т.п.).
 */

data class Comment(
    val id: Int = 0,
    val fromId: Int,
    val date: Date,
    val text: String,
    val donut: Any? = null,
    val replyToUser: Int? = 0,
    val replyToComment: Int? = 0,
    val attachments: Array<Attachment> = emptyArray(),
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Comment

        if (id != other.id) return false
        if (fromId != other.fromId) return false
        if (date != other.date) return false
        if (text != other.text) return false
        if (donut != other.donut) return false
        if (replyToUser != other.replyToUser) return false
        if (replyToComment != other.replyToComment) return false
        if (!attachments.contentEquals(other.attachments)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + fromId
        result = 31 * result + date.hashCode()
        result = 31 * result + text.hashCode()
        result = 31 * result + (donut?.hashCode() ?: 0)
        result = 31 * result + (replyToUser ?: 0)
        result = 31 * result + (replyToComment ?: 0)
        result = 31 * result + attachments.contentHashCode()
        return result
    }

}
