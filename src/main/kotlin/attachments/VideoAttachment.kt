package attachments

class VideoAttachment(
    private val video: Video
) : Attachment {
    override val type = "video"
    override fun toString(): String {
        return "$video"
    }


}