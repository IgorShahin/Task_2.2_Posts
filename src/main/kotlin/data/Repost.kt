package data

/**
 * [count] — число пользователей, скопировавших запись;
 *
 * [userReposted] — наличие репоста от текущего пользователя (true — есть, false — нет).
 */

data class Repost(
    val count: UInt,
    val userReposted: Boolean
)
