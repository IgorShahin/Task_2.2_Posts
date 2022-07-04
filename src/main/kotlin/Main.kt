import attachments.*
import data.Comment
import data.Post
import service.WallService
import java.util.*

fun main() {

    val currentDate = Date()

    // Post1
    val videoPost1 = Video(
        id = 1,
        ownerId = 1,
        title = "Видео",
        description = "Описание видео",
        duration = 15,
        date = currentDate,
        views = 13,
        comments = 10
    )

    val videoAttachmentPost1 = VideoAttachment(videoPost1)
    val attachmentsPost1: Array<Attachment> = arrayOf(videoAttachmentPost1)

    val post1 = Post(
        ownerId = 1u,
        fromId = 1u,
        text = "Пост 1",
        attachments = attachmentsPost1,
        replyOwnerId = 1u,
        replyPostId = 1u,
        signerId = 1u,
    )

    // Post 2
    val photoPost2 = Photo(
        id = 2,
        ownerId = 2,
        albumId = 13,
        userId = 2,
        text = "Альбом Toxicity",
        date = currentDate
    )

    val audioPost2 = Audio(
        id = 2,
        ownerId = 2,
        artist = "System Of A Down",
        title = "Aerials",
        duration = 235,
        date = currentDate
    )

    val photoAttachmentPost2 = PhotoAttachment(photoPost2)
    val audioAttachmentPost2 = AudioAttachment(audioPost2)
    val attachmentsPost2: Array<Attachment> = arrayOf(photoAttachmentPost2, audioAttachmentPost2)


    val post2 = Post(
        ownerId = 2u,
        fromId = 2u,
        text = "Пост 2",
        attachments = attachmentsPost2,
        replyOwnerId = 2u,
        replyPostId = 2u,
        signerId = 2u,
    )

    // Post 3
    val post3 = Post(
        ownerId = 3u,
        fromId = 3u,
        text = "Пост 3",
        replyOwnerId = 3u,
        replyPostId = 3u,
        signerId = 3u,
    )

    val postUpdate = Post(
        id = 2u,
        ownerId = 2u,
        fromId = 2u,
        text = "Измененный пост 2",
        replyOwnerId = 2u,
        replyPostId = 2u,
        signerId = 2u,
    )

    val comment = Comment(
        fromId = 1,
        date = currentDate,
        text = "Комментарий 1 поста"
    )

    val service = WallService
    service.add(post1)
    println(service.createComment(1u, comment))
    service.getAttachment(post1)
    service.add(post2)
    service.add(post3)
    service.showPost(2u)
    service.update(postUpdate)
    service.showPost(2u)

}