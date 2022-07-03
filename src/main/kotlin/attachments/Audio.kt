package attachments

import java.util.*

/**
 * [id] — Идентификатор видеозаписи;
 *
 * [ownerId] — Идентификатор владельца видеозаписи;
 *
 * [artist] — Исполнитель;
 *
 * [title] — Название композиции;
 *
 * [duration] — Длительность аудиозаписи в секундах;
 *
 * [date] — Дата добавления.
 */

data class Audio(
    val id: Int,
    val ownerId: Int,
    val artist: String,
    val title: String,
    val duration: Int,
    val date: Date,
)
