import notes.CommentNote
import notes.Note
import notes.Sort
import service.NoteService
import java.lang.Thread.sleep
import java.util.*

fun main() {

    val noteFirst = Note(
        title = "Заметка 1",
        text = "Текст заметки 1",
        date = Date()
    )

    val noteTwo = Note(
        title = "Заметка 2",
        text = "Текст заметки 2",
        date = Date()
    )

    val noteThree = Note(
        title = "Заметка 3",
        text = "Текст заметки 3",
        date = Date()
    )

    val comment1 = CommentNote(
        uid = 1,
        date = Date(),
        message = "1 комментарий"
    )

    val comment2 = CommentNote(
        uid = 1,
        date = Date(),
        message = "2 комментарий"
    )

    val comment3 = CommentNote(
        uid = 1,
        date = Date(),
        message = "3 комментарий"
    )

    val comment4 = CommentNote(
        uid = 1,
        date = Date(),
        message = "4 комментарий"
    )

    val service = NoteService
    service.add(noteFirst)
    service.add(noteTwo)
    service.add(noteThree)
//    println(service.get(1,3, Sort.DESCENDING))
    service.createComment(2, comment1)
    service.createComment(2, comment2)
    service.createComment(1, comment4)
    service.createComment(3, comment3)

    service.deleteComment(2)
}