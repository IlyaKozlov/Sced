package document

import play.api.libs.json._
import play.api.libs.functional.syntax._

case class DocumentMetadata(fileName: String,
                            size: Int,
                            modifiedTime: Int,
                            createdTime: Int,
                            accessTime: Int,
                            fileType: Option[String],
                            )


object DocumentMetadata {
    implicit val documentMetadataReads: Reads[DocumentMetadata] = (
        (JsPath \ "file_name").read[String] and
            (JsPath \ "size").read[Int] and
            (JsPath \ "modified_time").read[Int] and
            (JsPath \ "created_time").read[Int] and
            (JsPath \ "access_time").read[Int] and
            (JsPath \ "file_type").readNullable[String]
        )(DocumentMetadata.apply _)
}