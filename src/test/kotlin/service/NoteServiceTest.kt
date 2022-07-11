package service

import notes.CommentNote
import notes.Note
import notes.Sort
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.*
import kotlin.test.assertContentEquals

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
    fun getShouldNoThrow() {
        NoteService.clearAllNote()

        val note1 = Note(
            title = "Заметка 1",
            text = "Текст заметки 1",
            date = Date()
        )

        val note2 = Note(
            title = "Заметка 2",
            text = "Текст заметки 2",
            date = Date()
        )

        val note3 = Note(
            title = "Заметка 3",
            text = "Текст заметки 3",
            date = Date()
        )

        val service = NoteService
        service.add(note1)
        service.add(note2)
        service.add(note3)


        val listEqualsResult = arrayListOf(service.getById(1), service.getById(2))
        val results = service.get(1, 2, Sort.INCREASE)
        assertContentEquals(listEqualsResult, results)
    }

    @Test
    fun getSortDescending() {
        NoteService.clearAllNote()

        val note1 = Note(
            title = "Заметка 1",
            text = "Текст заметки 1",
            date = Date()
        )

        val note2 = Note(
            title = "Заметка 2",
            text = "Текст заметки 2",
            date = Date()
        )

        val note3 = Note(
            title = "Заметка 3",
            text = "Текст заметки 3",
            date = Date()
        )

        val service = NoteService
        service.add(note1)
        service.add(note2)
        service.add(note3)


        val listEqualsResult = arrayListOf(service.getById(1), service.getById(2))
        val results = service.get(1, 2, Sort.DESCENDING)
        assertContentEquals(listEqualsResult, results)
    }

    @Test(expected = NoteCountException::class)
    fun getShouldThrow() {
        NoteService.clearAllNote()

        val note1 = Note(
            title = "Заметка 1",
            text = "Текст заметки 1",
            date = Date()
        )

        val note2 = Note(
            title = "Заметка 2",
            text = "Текст заметки 2",
            date = Date()
        )

        val note3 = Note(
            title = "Заметка 3",
            text = "Текст заметки 3",
            date = Date()
        )

        val service = NoteService
        service.add(note1)
        service.add(note2)
        service.add(note3)

        service.get(2, 3, Sort.DESCENDING)
    }

    @Test(expected = NoteCountException::class)
    fun getDeleteException() {
        NoteService.clearAllNote()

        val note1 = Note(
            title = "Заметка 1",
            text = "Текст заметки 1",
            date = Date()
        )

        val note2 = Note(
            title = "Заметка 2",
            text = "Текст заметки 2",
            date = Date()
        )

        val note3 = Note(
            title = "Заметка 3",
            text = "Текст заметки 3",
            date = Date()
        )

        val service = NoteService
        service.add(note1)
        service.add(note2)
        service.add(note3)
        service.delete(2)
        service.get(2, 3, Sort.DESCENDING)
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
    fun getCommentsShouldNoThrow() {
        NoteService.clearAllNote()

        val note1 = Note(
            title = "Заметка 1",
            text = "Текст заметки 1",
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

        val service = NoteService
        service.add(note1)
        service.createComment(1, comment1)
        service.createComment(1, comment2)

        val resultId = arrayListOf<Int>()
        val listEqualsResult = arrayListOf(1, 2)
        val results = service.getComments(1, 2, Sort.INCREASE)
        for (result in results) {
            resultId += result.id
        }
        assertEquals(listEqualsResult, resultId)
    }

    @Test
    fun getCommentsSortDescending() {
        NoteService.clearAllNote()

        val note1 = Note(
            title = "Заметка 1",
            text = "Текст заметки 1",
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

        val service = NoteService
        service.add(note1)
        service.createComment(1, comment1)
        service.createComment(1, comment2)

        val resultId = arrayListOf<Int>()
        val listEqualsResult = arrayListOf(1, 2)
        val results = service.getComments(1, 2, Sort.DESCENDING)
        for (result in results) {
            resultId += result.id
        }
        assertEquals(listEqualsResult, resultId)
    }

    @Test(expected = CommentCountException::class)
    fun getCommentsShouldThrow() {
        NoteService.clearAllNote()

        val note1 = Note(
            title = "Заметка 1",
            text = "Текст заметки 1",
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

        val service = NoteService
        service.add(note1)
        service.createComment(1, comment1)
        service.createComment(1, comment2)

        service.getComments(1, 3, Sort.INCREASE)
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
    fun editCommentShouldNoThrow() {
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

        val commentUpdate = CommentNote(
            uid = 1,
            date = Date(),
            message = "Обновление"
        )

        val service = NoteService
        service.add(note)
        service.createComment(1, comment)
        val result = service.editComment(1, commentUpdate)
        assertTrue(result)
    }

    @Test(expected = CommentNotFoundException::class)
    fun editCommentShouldThrow() {
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

        val commentUpdate = CommentNote(
            uid = 1,
            date = Date(),
            message = "Обновление"
        )

        val service = NoteService
        service.add(note)
        service.createComment(1, comment)
        service.editComment(2, commentUpdate)
    }

    @Test
    fun editShouldNoThrow() {
        NoteService.clearAllNote()

        val note = Note(
            title = "Заметка 1",
            text = "Текст заметки 1",
            date = Date()
        )

        val noteUpdate = Note(
            title = "Обновление",
            text = "Текст обновлен",
            date = Date()
        )

        val service = NoteService
        service.add(note)
        val result = service.edit(1, noteUpdate)
        assertTrue(result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun editShouldThrow() {
        NoteService.clearAllNote()

        val note = Note(
            title = "Заметка 1",
            text = "Текст заметки 1",
            date = Date()
        )

        val noteUpdate = Note(
            title = "Обновление",
            text = "Текст обновлен",
            date = Date()
        )

        val service = NoteService
        service.add(note)
        service.edit(2, noteUpdate)
    }
}