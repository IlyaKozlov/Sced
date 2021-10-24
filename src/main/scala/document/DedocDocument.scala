package document

import play.api.libs.json.{Json, Reads}

case class DedocDocument(metadata: DocumentMetadata, content: DocumentContent, attachments: Option[Seq[DedocDocument]])

object DedocDocument {
    implicit val dedocDocumentReads: Reads[DedocDocument] = Json.reads[DedocDocument]
}