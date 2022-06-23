package data

import java.util.Date

/**
 * [id] — Идентификатор записи;
 *
 * [ownerId] — Идентификатор владельца стены, на которой размещена запись. В версиях API ниже 5.7 это поле называется to_id;
 *
 * [fromId] — Идентификатор автора записи (от чьего имени опубликована запись);
 *
 * [createdBy] — Идентификатор администратора, который опубликовал запись (возвращается только для сообществ при запросе с ключом доступа администратора). Возвращается в записях, опубликованных менее 24 часов назад;
 *
 * [date] — Время публикации записи в формате unixtime;
 *
 * [text] — Текст записи;
 *
 * [replyOwnerId] — Идентификатор владельца записи, в ответ на которую была оставлена текущая;
 *
 * [replyPostId] — Идентификатор записи, в ответ на которую была оставлена текущая;
 *
 * [friendsOnly] — true, если запись была создана с опцией «Только для друзей»;
 *
 * [comments] — Информация о комментариях к записи;
 *
 * [likes] — Информация о лайках к записи;
 *
 * [reposts] — Информация о репостах записи («Рассказать друзьям»);
 *
 * [views] — Информация о просмотрах записи (число просмотров записи);
 *
 * [postType] — Тип записи, может принимать следующие значения: post, copy, reply, postpone, suggest;
 *
 * [signerId] — Идентификатор автора, если запись была опубликована от имени сообщества и подписана пользователем;
 *
 * [canPin] — Информация о том, может ли текущий пользователь закрепить запись (true — может, false — не может);
 *
 * [canDelete] — Информация о том, может ли текущий пользователь удалить запись (true — может, false — не может);
 *
 * [canEdit] — Информация о том, может ли текущий пользователь редактировать запись (true — может, false — не может);
 *
 * [isPinned] — Информация о том, что запись закреплена;
 *
 * [markedAsAds] — Информация о том, содержит ли запись отметку "реклама" (true — да, false — нет);
 *
 * [isFavorite] — true, если объект добавлен в закладки у текущего пользователя;
 *
 * [postponedId] — Идентификатор отложенной записи. Это поле возвращается тогда, когда запись стояла на таймере;
 */

data class Post(
    val id: UInt = 1u,
    val ownerId: UInt,
    val fromId: UInt,
    val createdBy: UInt? = null,
    val date: Date? = null,
    val text: String,
    val replyOwnerId: UInt,
    val replyPostId: UInt,
    val friendsOnly: Boolean = true,
    val comments: Comment? = null,
    val likes: Like? = null,
    val reposts: Repost? = null,
    val views: UInt = 0u,
    val postType: String = "post",
    val signerId: UInt? = null,
    val canPin: Boolean = true,
    val canDelete: Boolean = true,
    val canEdit: Boolean = true,
    val isPinned: Boolean = true,
    val markedAsAds: Boolean = false,
    val isFavorite: Boolean = true,
    val postponedId: UInt = 1u
)
