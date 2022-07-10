package service

import notes.CommentNote
import notes.Note
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.*

class NoteServiceTest {

    @Test
    fun add() {
        NoteService.clearAllNote()

        val note = Note(
            title = "Заметка 1",
            text = "Текст заметки 1",
            date = Date()
        )
        val service = NoteService
        val result = service.add(note).noteId
        assertEquals(1, result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun createCommentShouldThrow() {
        NoteService.clearAllNote()

        val note = Note(
            title = "Заметка 1",
            text = "Текст заметки 1",
            date = Date()
        )

        val comment = CommentNote(
            uid = 1,
            date = Date(),
            message = "1 комментарий"
        )

        val service = NoteService
        service.add(note)
        service.createComment(2, comment)
    }

    @Test
    fun createCommentShouldNoThrow() {
        NoteService.clearAllNote()

        val note = Note(
            title = "Заметка 1",
            text = "Текст заметки 1",
            date = Date()
        )

        val comment = CommentNote(
            uid = 1,
            date = Date(),
            message = "1 комментарий"
        )

        val service = NoteService
        service.add(note)
        val result = service.createComment(1, comment)
        assertEquals(1, result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun deleteShouldThrow() {
        NoteService.clearAllNote()

        val note = Note(
            title = "Заметка 1",
            text = "Текст заметки 1",
            date = Date()
        )

        val service = NoteService
        service.add(note)
        service.delete(2)
    }

    @Test(expected = NoteNotDeleteException::class)
    fun deleteNoteShouldThrowAlreadyDeleted() {
        NoteService.clearAllNote()

        val note = Note(
            title = "Заметка 1",
            text = "Текст заметки 1",
            date = Date()
        )

        val service = NoteService
        service.add(note)
        service.delete(1)
        service.delete(1)
    }

    @Test
    fun deleteShouldNoThrow() {
        NoteService.clearAllNote()

        val note = Note(
            title = "Заметка 1",
            text = "Текст заметки 1",
            date = Date()
        )

        val comment = CommentNote(
            uid = 1,
            date = Date(),
            message = "1 комментарий"
        )

        val service = NoteService
        service.add(note)
        service.createComment(1, comment)
        val result = service.delete(1)
        assertTrue(result)
    }

    @Test(expected = CommentNotFoundException::class)
    fun deleteCommentShouldThrow() {
        NoteService.clearAllNote()

        val note = Note(
            title = "Заметка 1",
            text = "Текст заметки 1",
            date = Date()
        )

        val comment = CommentNote(
            uid = 1,
            date = Date(),
            message = "1 комментарий"
        )

        val service = NoteService
        service.add(note)
        service.createComment(1, comment)
        service.deleteComment(2)
    }

    @Test(expected = CommentNotDeleteException::class)
    fun deleteCommentShouldThrowAlreadyDeleted() {
        NoteService.clearAllNote()

        val note = Note(
            title = "Заметка 1",
            text = "Текст заметки 1",
            date = Date()
        )

        val comment = CommentNote(
            uid = 1,
            date = Date(),
            message = "1 комментарий"
        )

        val service = NoteService
        service.add(note)
        service.createComment(1, comment)
        service.deleteComment(1)
        service.deleteComment(1)
    }

    @Test
    fun deleteCommentShouldNoThrow() {
        NoteService.clearAllNote()

        val note = Note(
            title = "Заметка 1",
            text = "Текст заметки 1",
            date = Date()
        )

        val comment = CommentNote(
            uid = 1,
            date = Date(),
            message = "1 комментарий"
        )

        val service = NoteService
        service.add(note)
        service.createComment(1, comment)
        val result = service.deleteComment(1)
        assertTrue(result)
    }

    @Test
    fun get() {

    }

    @Test(expected = NoteNotFoundException::class)
    fun getByIdShouldThrow() {
        NoteService.clearAllNote()

        val note = Note(
            title = "Заметка 1",
            text = "Текст заметки 1",
            date = Date()
        )

        val service = NoteService
        service.add(note)
        service.delete(1)
        service.getById(2)
        service.getById(1)
    }

    @Test
    fun getByIdShouldNoThrow() {
        NoteService.clearAllNote()

        val note = Note(
            title = "Заметка 1",
            text = "Текст заметки 1",
            date = Date()
        )

        val service = NoteService
        service.add(note)
        val result = service.getById(1).noteId
        assertEquals(1, result)
    }

    @Test
    fun getComments() {
    }

    @Test(expected = CommentNotFoundException::class)
    fun restoreCommentShouldThrow() {
        NoteService.clearAllNote()

        val note = Note(
            title = "Заметка 1",
            text = "Текст заметки 1",
            date = Date()
        )

        val comment = CommentNote(
            uid = 1,
            date = Date(),
            message = "1 комментарий"
        )

        val service = NoteService
        service.add(note)
        service.createComment(1, comment)
        service.restoreComment(2)
    }

    @Test(expected = CommentNotResetException::class)
    fun restoreCommentShouldThrowAlreadyDeleted() {
        NoteService.clearAllNote()

        val note = Note(
            title = "Заметка 1",
            text = "Текст заметки 1",
            date = Date()
        )

        val comment = CommentNote(
            uid = 1,
            date = Date(),
            message = "1 комментарий"
        )

        val service = NoteService
        service.add(note)
        service.createComment(1, comment)
        service.restoreComment(1)
    }

    @Test
    fun restoreCommentShouldNoThrow() {
        NoteService.clearAllNote()

        val note = Note(
            title = "Заметка 1",
            text = "Текст заметки 1",
            date = Date()
        )

        val comment = CommentNote(
            uid = 1,
            date = Date(),
            message = "1 комментарий"
        )

        val service = NoteService
        service.add(note)
        service.createComment(1, comment)
        service.deleteComment(1)
        val result = service.restoreComment(1)
        assertTrue(result)
    }

    @Test
    fun editComment() {
    }

    @Test
    fun edit() {
    }
}