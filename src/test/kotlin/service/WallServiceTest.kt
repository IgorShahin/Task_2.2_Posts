package service

import attachments.*
import data.Comment
import data.Post
import org.junit.Assert.*
import org.junit.Test
import java.util.*

class WallServiceTest {

    @Test
    fun add() {
        WallService.clearAllPost()
        val post = Post(
            ownerId = 1u,
            fromId = 1u,
            text = "Пост 1",
            replyOwnerId = 1u,
            replyPostId = 1u,
            signerId = 1u,
        )
        val service = WallService
        val result = service.add(post).id
        assertEquals(1u, result)

    }

    @Test
    fun updateExisting() {
        WallService.clearAllPost()
        val post1 = Post(
            ownerId = 1u,
            fromId = 1u,
            text = "Пост 1",
            replyOwnerId = 1u,
            replyPostId = 1u,
            signerId = 1u,
        )

        val post2 = Post(
            ownerId = 2u,
            fromId = 2u,
            text = "Пост 2",
            replyOwnerId = 2u,
            replyPostId = 2u,
            signerId = 2u,
        )

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

        val service = WallService
        service.add(post1)
        service.add(post2)
        service.add(post3)

        val result = service.update(postUpdate)

        assertTrue(result)
    }

    @Test
    fun updateNoExisting() {
        WallService.clearAllPost()
        val post1 = Post(
            ownerId = 1u,
            fromId = 1u,
            text = "Пост 1",
            replyOwnerId = 1u,
            replyPostId = 1u,
            signerId = 1u,
        )

        val post2 = Post(
            ownerId = 2u,
            fromId = 2u,
            text = "Пост 2",
            replyOwnerId = 2u,
            replyPostId = 2u,
            signerId = 2u,
        )

        val post3 = Post(
            ownerId = 3u,
            fromId = 3u,
            text = "Пост 3",
            replyOwnerId = 3u,
            replyPostId = 3u,
            signerId = 3u,
        )

        val postUpdate = Post(
            id = 4u,
            ownerId = 2u,
            fromId = 2u,
            text = "Измененный пост 4",
            replyOwnerId = 2u,
            replyPostId = 2u,
            signerId = 2u,
        )

        val service = WallService
        service.add(post1)
        service.add(post2)
        service.add(post3)

        val result = service.update(postUpdate)

        assertFalse(result)
    }

    @Test
    fun getAttachmentExisting() {
        WallService.clearAllPost()
        val currentDate = Date()

        val videoPost = Video(
            id = 1,
            ownerId = 1,
            title = "Название тест",
            description = "Описание тест",
            duration = 15,
            date = currentDate,
            views = 13,
            comments = 10
        )

        val photoPost = Photo(
            id = 1,
            ownerId = 1,
            albumId = 13,
            userId = 1,
            text = "Фото тест",
            date = currentDate
        )

        val audioPost = Audio(
            id = 1,
            ownerId = 1,
            artist = "Исполнитель тест",
            title = "Название тест",
            duration = 235,
            date = currentDate
        )

        val photoAttachmentPost = PhotoAttachment(photoPost)
        val audioAttachmentPost = AudioAttachment(audioPost)
        val videoAttachmentPost = VideoAttachment(videoPost)
        val attachmentsPost: Array<Attachment> = arrayOf(photoAttachmentPost, audioAttachmentPost, videoAttachmentPost)

        val post = Post(
            ownerId = 1u,
            fromId = 1u,
            text = "Тест пост",
            attachments = attachmentsPost,
            replyOwnerId = 1u,
            replyPostId = 1u,
            signerId = 1u,
        )

        val service = WallService
        service.add(post)

        val result = service.getAttachment(post)
        assertTrue(result)
    }

    @Test
    fun getAttachmentNoExisting() {
        WallService.clearAllPost()
        val post = Post(
            ownerId = 1u,
            fromId = 1u,
            text = "Тест пост",
            replyOwnerId = 1u,
            replyPostId = 1u,
            signerId = 1u,
        )

        val service = WallService
        service.add(post)
        val result = service.getAttachment(post)
        assertFalse(result)
    }

    @Test(expected = PostNotFoundException::class)
    fun shouldThrow() {
        WallService.clearAllPost()
        val currentDate = Date()

        val post = Post(
            ownerId = 1u,
            fromId = 1u,
            text = "Пост 1",
            replyOwnerId = 1u,
            replyPostId = 1u,
            signerId = 1u,
        )

        val comment = Comment(
            fromId = 1,
            date = currentDate,
            text = "Комментарий 1 поста"
        )

        val service = WallService
        service.add(post)
        service.createComment(2u, comment)
    }

    @Test
    fun shouldNoThrow() {
        WallService.clearAllPost()
        val currentDate = Date()

        val post = Post(
            ownerId = 1u,
            fromId = 1u,
            text = "Пост 1",
            replyOwnerId = 1u,
            replyPostId = 1u,
            signerId = 1u,
        )

        val comment = Comment(
            fromId = 1,
            date = currentDate,
            text = "Комментарий 1 поста"
        )

        val service = WallService
        service.add(post)
        service.createComment(1u, comment)
    }
}