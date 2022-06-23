package service

import data.Post
import java.util.*

object WallService {
    private var posts = emptyArray<Post>()
    private var id = 1u

    fun add(post: Post): Post {
        val currentDate = Date()
        posts += post.copy(id = id, date = currentDate)
        id++
        return posts.last()
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

    fun clearAllPost(){
        posts = emptyArray()
        id = 1u
    }
}