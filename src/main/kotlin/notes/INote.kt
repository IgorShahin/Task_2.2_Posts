package notes

interface INote<N, C> {

    fun add(element: N): N

    fun createComment(noteId: Int, element: C): Int

    fun delete(noteId: Int): Boolean

    fun deleteComment(commentId: Int): Boolean

    fun edit(noteId: Int, element: N): Boolean

    fun editComment(commentId: Int, element: C): Boolean

    fun get(noteId: Int, count: Int, sort: Sort): ArrayList<N>

    fun getById(noteId: Int): N

    fun getComments(noteId: Int, sort: Sort, count: Int): ArrayList<C>

    fun restoreComment(commentId: Int): Boolean
}