package data

/**
 * [count] — число пользователей, которым понравилась запись;
 *
 * [userLikes] — наличие отметки «Мне нравится» от текущего пользователя (true — есть, false — нет);
 *
 * [canLike] — информация о том, может ли текущий пользователь поставить отметку «Мне нравится» (true — может, false — не может);
 *
 * [canPublish] — информация о том, может ли текущий пользователь сделать репост записи (true — может, false — не может).
 */

data class Like(
    val count: UInt,
    val userLikes: Boolean,
    val canLike: Boolean,
    val canPublish: Boolean
)
