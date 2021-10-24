package document

import play.api.libs.json.{Json, Reads}

case class TableMetadata(uid: String, pageId: Option[Int])

object TableMetadata {
    implicit val tableMetadataReads: Reads[TableMetadata] = Json.reads[TableMetadata]
}