package service

import data.Post
import org.junit.Test

import org.junit.Assert.*

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
}