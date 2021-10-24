package document

import play.api.libs.functional.syntax._
import play.api.libs.json._

case class TreeNode(nodeId: String,
                    text: String,
                    annotations: Seq[Annotation],
                    metadata: ParagraphMetadata,
                    subparagraphs: Seq[TreeNode])

object TreeNode {
    implicit val treeNodeReads: Reads[TreeNode] = (
        (JsPath \ "node_id").read[String] and
        (JsPath \ "text").read[String] and
        (JsPath \ "annotations").read[Seq[Annotation]] and
        (JsPath \ "metadata").read[ParagraphMetadata] and
        (JsPath \ "subparagraphs").lazyRead(Reads.seq[TreeNode](treeNodeReads))
    )(TreeNode.apply _)
}