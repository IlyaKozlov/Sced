package document

import play.api.libs.json.{Json, Reads}

case class Table(cells: Array[Array[String]], metadata: TableMetadata)


object Table {
    implicit val tableReads: Reads[Table] = Json.reads[Table]
}