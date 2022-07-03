package attachments

class PhotoAttachment(
    private val photo: Photo
) : Attachment {
    override val type: String = "photo"
    override fun toString(): String {
        return "$photo"
    }
}