package service

import notes.CommentNote
import notes.INote
import notes.Note
import notes.Sort
import java.util.*

object NoteService : INote<Note, CommentNote> {
    private var notes = mutableListOf<Note>()
    private var comments = mutableListOf<CommentNote>()
    private var idNote = 1
    private var idComment = 1

    override fun add(element: Note): Note {
        val currentDate = Date()
        notes += element.copy(noteId = idNote++, date = currentDate)
        return notes.last()
    }

    override fun createComment(noteId: Int, element: CommentNote): Int {
        for (note in notes) {
            if (noteId == note.noteId) {
                val currentDate = Date()
                comments += element.copy(id = idComment++, nid = noteId, date = currentDate)
                return comments.last().id
            }
        }
        throw NoteNotFoundException("no note with id $noteId")
    }

    override fun delete(noteId: Int): Boolean {
        for ((index, note) in notes.withIndex()) {
            if (noteId == note.noteId) {
                if (note.noteDelete) throw NoteNotDeleteException("note with id $noteId removed")
                notes[index].noteDelete = true
                for ((indexComment, comment) in comments.withIndex()) {
                    if (noteId == comment.nid) {
                        comments[indexComment].commentDelete = true
                        comments[indexComment].noteCommentDelete = true
                    }
                }
                return true
            }
        }
        throw NoteNotFoundException("no note with id $noteId")
    }

    override fun deleteComment(commentId: Int): Boolean {
        for ((index, comment) in comments.withIndex()) {
            if (commentId == comment.id) {
                if (comment.commentDelete) throw CommentNotDeleteException("comment with id $commentId removed")
                comments[index].commentDelete = true
                return true
            }
        }
        throw CommentNotFoundException("no comment with id $commentId")
    }

    override fun get(noteId: Int, count: Int, sort: Sort): ArrayList<Note> {
        if (noteId + count - 1 <= notes.size) {
            val result = arrayListOf<Note>()
            var countSize = count
            var noteIdNum = noteId

            for (note in notes) {
                if (note.noteId == noteId && note.noteDelete) throw NoteNotFoundException("no note with id")
            }

            for (note in notes) {
                if (noteIdNum == note.noteId && countSize > 0) {
                    if (!note.noteDelete) {
                        result += note.copy()
                    }
                    countSize--
                    noteIdNum++
                }
            }
            when (sort) {
                Sort.DESCENDING -> result.sortByDescending { it.date }
                Sort.INCREASE -> result.sortBy { it.date }
            }
            return result
        }
        throw NoteCountException("notes exceeded")
    }

    override fun getById(noteId: Int): Note {
        for (note in notes) {
            if (noteId == note.noteId && !note.noteDelete) {
                return note
            }
        }
        throw NoteNotFoundException("no note with id $noteId")
    }

    override fun getComments(noteId: Int, count: Int, sort: Sort): ArrayList<CommentNote> {
        val result = arrayListOf<CommentNote>()

        if (comments.size >= count) {
            for ((index, comment) in comments.withIndex()) {
                if (noteId == comment.nid && index <= count && !comment.commentDelete) {
                    result += comment.copy()
                }
            }
            when (sort) {
                Sort.DESCENDING -> result.sortByDescending { it.date }
                Sort.INCREASE -> result.sortBy { it.date }
            }
            return result
        }
        throw CommentCountException("Number of comments exceeded")
    }

    override fun restoreComment(commentId: Int): Boolean {
        for ((index, comment) in comments.withIndex()) {
            if (commentId == comment.id) {
                if (!comment.commentDelete || comment.noteCommentDelete) throw CommentNotResetException("comment with id $commentId is unrecoverable")
                comments[index].commentDelete = false
                return true
            }
        }
        throw CommentNotFoundException("no comment with id $commentId")
    }

    override fun editComment(commentId: Int, element: CommentNote): Boolean {
        for (comment in comments) {
            if (commentId == comment.id) {
                if (comment.commentDelete) throw CommentNotEditException("comment with id $commentId cannot be edited")
                delete(commentId)
                comments += element.copy(id = commentId)
                return true
            }
        }
        throw CommentNotFoundException("no note with id $commentId")
    }

    override fun edit(noteId: Int, element: Note): Boolean {
        for (note in notes) {
            if (noteId == note.noteId) {
                if (note.noteDelete) throw NoteNotEditException("note with id $noteId cannot be edited")
                delete(noteId)
                notes += element.copy(noteId = noteId)
                return true
            }
        }
        throw NoteNotFoundException("no note with id $noteId")
    }

    fun clearAllNote() {
        notes.clear()
        comments.clear()
        idNote = 1
        idComment = 1
    }

}

class NoteNotFoundException(message: String) : RuntimeException(message)
class NoteNotEditException(message: String) : RuntimeException(message)
class NoteNotDeleteException(message: String) : RuntimeException(message)
class NoteCountException(message: String) : RuntimeException(message)

class CommentNotFoundException(message: String) : RuntimeException(message)
class CommentNotResetException(message: String) : RuntimeException(message)
class CommentNotEditException(message: String) : RuntimeException(message)
class CommentNotDeleteException(message: String) : RuntimeException(message)
class CommentCountException(message: String) : RuntimeException(message)