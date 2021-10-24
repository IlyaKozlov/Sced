package document

import play.api.libs.json.{Json, Reads}

case class DocumentContent(tables: Seq[Table], structure: TreeNode)


object DocumentContent {
    implicit val documentContentReads: Reads[DocumentContent] = Json.reads[DocumentContent]
}
