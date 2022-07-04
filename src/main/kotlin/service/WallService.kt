package service

import data.Comment
import data.Post
import java.util.*

object WallService {
    private var posts = emptyArray<Post>()
    private var comments = emptyArray<Comment>()
    private var id = 1u

    fun createComment(postId: UInt, comment: Comment): Comment {
        for (post in posts) {
            if (post.id == postId) {
                comments += comment.copy(id = comment.id + 1)
                return comments.last()
            }
        }
        throw PostNotFoundException("no post with id $postId")
    }

    fun add(post: Post): Post {
        val currentDate = Date()
        posts += post.copy(id = id, date = currentDate)
        id++
        return posts.last()
    }

    fun getAttachment(post: Post): Boolean {
        for (postId in posts) {
            if (post.id == postId.id) {
                if (post.attachments.isNotEmpty()) {
                    for (attachment in post.attachments) {
                        when (attachment.type) {
                            "video" -> println(attachment.toString())
                            "audio" -> println(attachment.toString())
                            "photo" -> println(attachment.toString())
                        }
                    }
                    return true
                } else println("[!] У данного поста нет вложений")
            }
        }
        return false
    }

    fun update(post: Post): Boolean {
        for (postId in posts) {
            if (post.id == postId.id) {
                posts[postId.id.toInt() - 1] = post.copy(date = posts[postId.id.toInt() - 1].date)
                return true
            }
        }
        return false
    }

    fun get(): Array<Post> {
        return posts
    }

    fun showAllPost() {
        for (post in posts) println(post)
    }

    fun showPost(id: UInt) {
        for (post in posts) if (id == post.id) return println(post)
        println("[!] Поста с данным id не существует")
    }

    fun clearAllPost() {
        posts = emptyArray()
        id = 1u
    }

}

class PostNotFoundException(message: String) : RuntimeException(message)
