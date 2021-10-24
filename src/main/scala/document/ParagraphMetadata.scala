package document

import play.api.libs.json._
import play.api.libs.functional.syntax._

case class ParagraphMetadata(paragraphType: String,
                             predictedClasses: Option[Map[String, Float]],
                             pageId: Option[Int],
                             lineId: Option[Int])


object ParagraphMetadata {
    implicit val paragraphMetadataReads: Reads[ParagraphMetadata] = (
         (JsPath \ "paragraph_type").read[String] and
         (JsPath \ "predicted_classes").readNullable[Map[String, Float]] and
         (JsPath \ "page_id").readNullable[Int] and
         (JsPath \ "line_id").readNullable[Int]
    )(ParagraphMetadata.apply _)
}
