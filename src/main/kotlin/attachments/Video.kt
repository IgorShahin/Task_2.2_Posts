package attachments

import java.util.*

/**
 * [id] — Идентификатор видеозаписи;
 *
 * [ownerId] — Идентификатор владельца видеозаписи;
 *
 * [title] — Название видеозаписи;
 *
 * [description] — Текст описания видеозаписи;
 *
 * [duration] — Длительность ролика в секундах;
 *
 * [date] — Дата создания видеозаписи;
 *
 * [views] — Количество просмотров видеозаписи;
 *
 * [comments] — Количество комментариев к видеозаписи;
 *
 * [player] — URL страницы с плеером, который можно использовать для воспроизведения ролика в браузере.
 * Поддерживается flash и html5, плеер всегда масштабируется по размеру окна.
 */

data class Video(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val description: String,
    val duration: Int,
    val date: Date,
    var views: Int,
    var comments: Int,
    val player: String = "flash"
)
