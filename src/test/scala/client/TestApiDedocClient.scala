package client

import java.nio.file.Paths

import exceptions.DedocException
import org.scalatest.funsuite.AnyFunSuite
import sttp.client3.HttpURLConnectionBackend
import sttp.client3.SttpClientException.ConnectException

import scala.util.Failure

class TestApiDedocClient extends AnyFunSuite {

    test("correct document") {
        val client = new DedocClient(host = "localhost", port = 1231, backend = HttpURLConnectionBackend())
        val file = Paths.get("src/test/data/some.txt").toFile
        val result = client.handleFile(file = file)
        assert(result.isSuccess)
    }

    test("incorrect document") {
        val client = new DedocClient(host = "localhost", port = 1231, backend = HttpURLConnectionBackend())
        val file = Paths.get("src/test/data/some.bin").toFile
        val result = client.handleFile(file = file)
        assert(result.isFailure)
        result match {
            case Failure(exception) => assert(exception.isInstanceOf[DedocException])
        }
    }


    test("incorrect host or port") {
        val client = new DedocClient(host = "localhost", port = 1232, backend = HttpURLConnectionBackend())
        val file = Paths.get("src/test/data/some.txt").toFile
        val result = client.handleFile(file = file)
        assert(result.isFailure)
        result match {
            case Failure(exception) => assert(exception.isInstanceOf[ConnectException])
        }
    }

}
