import data.Post
import service.WallService

fun main() {

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
    service.showPost(2u)
    service.update(postUpdate)
    service.showPost(2u)

}