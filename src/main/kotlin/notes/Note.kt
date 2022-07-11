package notes

import java.util.*

data class Note(
    val title: String,
    val text: String,
    val privacy: Int = 0,
    val commentPrivacy: Int = 0,
    val noteId: Int = 0,
    val date: Date,
    var noteDelete: Boolean = false
)
