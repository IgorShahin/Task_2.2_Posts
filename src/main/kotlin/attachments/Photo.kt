package attachments

import java.util.*

/**
 * [id] — Идентификатор видеозаписи;
 *
 * [ownerId] — Идентификатор владельца видеозаписи;
 *
 * [albumId] — Идентификатор альбома, в котором находится фотография;
 *
 * [userId] — Идентификатор пользователя, загрузившего фото (если фотография размещена в сообществе).
 * Для фотографий, размещенных от имени сообщества, user_id = 100;
 *
 * [text] — Текст описания фотографии;
 *
 * [date] — Дата добавления.
 */

data class Photo(
    val id: Int,
    val ownerId: Int,
    val albumId: Int,
    val userId: Int,
    val text: String,
    val date: Date
)