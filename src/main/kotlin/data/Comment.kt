package data

/**
 * [count] — количество комментариев;
 *
 * [canPost] — информация о том, может ли текущий пользователь комментировать запись (1 — может, 0 — не может);
 *
 * [groupsCanPost] — информация о том, могут ли сообщества комментировать запись;
 *
 * [canClose] — может ли текущий пользователь закрыть комментарии к записи;
 *
 * [canOpen] — может ли текущий пользователь открыть комментарии к записи.
 */

data class Comment(
    val count: UInt,
    val canPost: Boolean,
    val groupsCanPost: Boolean,
    val canClose: Boolean,
    val canOpen: Boolean
)
