package document

import play.api.libs.json.{Json, Reads}

case class Annotation(start: Int, end: Int, name: String, value: String)


object Annotation {
    implicit val annotationReads: Reads[Annotation] = Json.reads[Annotation]
}
