package client

import java.io.File

import document.DedocDocument
import exceptions.DedocException
import play.api.libs.json.Json
import sttp.client3.{Identity, SttpBackend, basicRequest, multipartFile, _}
import sttp.model.Part

import scala.util.{Failure, Success, Try}


class DedocClient(private val backend: SttpBackend[Identity, Any],
                  private val host: String,
                  private val port: Int) {

    def handleFile(file: File, parameters: Map[String, String] = Map()): Try[DedocDocument] = {
        val url = uri"http://$host:$port/upload"
        val requestParameters: Seq[Part[BasicRequestBody]] = parameters.map{
            case (key, value) => multipart(key, value)
        }.toSeq
        val request = basicRequest.multipartBody(
            multipartFile("file", file).fileName(file.getName),
            requestParameters:_*
        ).post(url)

        val responseTry = Try(request.send(backend)).flatMap(handleResponse)
        responseTry
    }

    private def handleResponse(response: Identity[Response[Either[String, String]]]): Try[DedocDocument] = {
        val code = response.code.code
        response.body match {
            case Left(message) =>  Failure(new DedocException(f"Get code $code and message $message"))
            case Right(message) => Try(Json.parse(message).as[DedocDocument])
        }
    }
}
