package data

import attachments.Attachment
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
 * [postSource] — Поле возвращается только для Standalone-приложений с ключом доступа, полученным в Implicit Flow;
 *
 * [geo] — Информация о местоположении;
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
    val attachments: Array<Attachment> = emptyArray(),
    val postSource: Any? = null,
    val geo: Geo? = null,
    val signerId: UInt? = null,
    val canPin: Boolean? = null,
    val canDelete: Boolean = true,
    val canEdit: Boolean = true,
    val isPinned: Boolean = true,
    val markedAsAds: Boolean = false,
    val isFavorite: Boolean = true,
    val postponedId: UInt = 1u
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Post

        if (id != other.id) return false
        if (ownerId != other.ownerId) return false
        if (fromId != other.fromId) return false
        if (createdBy != other.createdBy) return false
        if (date != other.date) return false
        if (text != other.text) return false
        if (replyOwnerId != other.replyOwnerId) return false
        if (replyPostId != other.replyPostId) return false
        if (friendsOnly != other.friendsOnly) return false
        if (comments != other.comments) return false
        if (likes != other.likes) return false
        if (reposts != other.reposts) return false
        if (views != other.views) return false
        if (postType != other.postType) return false
        if (!attachments.contentEquals(other.attachments)) return false
        if (postSource != other.postSource) return false
        if (geo != other.geo) return false
        if (signerId != other.signerId) return false
        if (canPin != other.canPin) return false
        if (canDelete != other.canDelete) return false
        if (canEdit != other.canEdit) return false
        if (isPinned != other.isPinned) return false
        if (markedAsAds != other.markedAsAds) return false
        if (isFavorite != other.isFavorite) return false
        if (postponedId != other.postponedId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + ownerId.hashCode()
        result = 31 * result + fromId.hashCode()
        result = 31 * result + (createdBy?.hashCode() ?: 0)
        result = 31 * result + (date?.hashCode() ?: 0)
        result = 31 * result + text.hashCode()
        result = 31 * result + replyOwnerId.hashCode()
        result = 31 * result + replyPostId.hashCode()
        result = 31 * result + friendsOnly.hashCode()
        result = 31 * result + (comments?.hashCode() ?: 0)
        result = 31 * result + (likes?.hashCode() ?: 0)
        result = 31 * result + (reposts?.hashCode() ?: 0)
        result = 31 * result + views.hashCode()
        result = 31 * result + postType.hashCode()
        result = 31 * result + attachments.contentHashCode()
        result = 31 * result + (postSource?.hashCode() ?: 0)
        result = 31 * result + (geo?.hashCode() ?: 0)
        result = 31 * result + (signerId?.hashCode() ?: 0)
        result = 31 * result + canPin.hashCode()
        result = 31 * result + canDelete.hashCode()
        result = 31 * result + canEdit.hashCode()
        result = 31 * result + isPinned.hashCode()
        result = 31 * result + markedAsAds.hashCode()
        result = 31 * result + isFavorite.hashCode()
        result = 31 * result + postponedId.hashCode()
        return result
    }
}
