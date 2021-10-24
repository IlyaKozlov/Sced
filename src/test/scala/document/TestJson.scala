package document
import java.nio.file.Paths

import org.scalatest.funsuite.AnyFunSuite
import play.api.libs.json.{JsResultException, Json}

import scala.io.Source

class TestJson extends AnyFunSuite {

    test("Parse Annotation") {
        val s: String = getJsonString("annotation.json")
        Json.parse(s).as[Annotation]
    }

    test("Parse Document") {
        val s: String = getJsonString("document.json")
        Json.parse(s).as[DedocDocument]
    }


    test("Parse DocumentContent") {
        val s: String = getJsonString("document_content.json")
        Json.parse(s).as[DocumentContent]
    }

    test("Parse DocumentMetadata") {
        val s: String = getJsonString("document_metadata.json")
        Json.parse(s).as[DocumentMetadata]
    }

    test("Parse ParagraphMetadata") {
        val s: String = getJsonString("paragraph_metadata.json")
        Json.parse(s).as[ParagraphMetadata]
    }

    test("Parse Table") {
        val s: String = getJsonString("table.json")
        Json.parse(s).as[Table]
    }

    test("Parse TableMetadata") {
        val s: String = getJsonString("table_metadata.json")
        Json.parse(s).as[TableMetadata]
    }

    test("Parse TreeNode") {
        val s: String = getJsonString("tree_node.json")
        Json.parse(s).as[TreeNode]
    }

    private def getJsonString(fileName: String): String = {
        val path = Paths.get("src/test/data/jsons/" + fileName).toAbsolutePath
        val source = Source.fromFile(path.toFile)
        val s = source.mkString
        source.close()
        s
    }

    test("Broken json exception") {
        assertThrows[JsResultException] {
            val s: String = getJsonString("table_metadata.json")
            Json.parse(s).as[Table]
        }
    }

}
