package notes

import java.util.*

data class CommentNote(
    val id: Int = 0,
    val uid: Int,
    val nid: Int = 0,
    val date: Date,
    val message: String,
    var noteCommentDelete: Boolean = false,
    var commentDelete: Boolean = false
)
