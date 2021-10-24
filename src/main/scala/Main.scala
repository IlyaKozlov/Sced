import java.io.File

import client.DedocClient
import exceptions.DedocException

import scala.concurrent.duration.{DAYS, FiniteDuration}
import scala.util.{Failure, Success}


object Main extends App {
    import sttp.client3._

    val path: String = ???  // path to file
    val file = new File(path)

    val options = SttpBackendOptions.Default.copy(connectionTimeout = FiniteDuration(1, DAYS))
    val backend = HttpURLConnectionBackend(options=options)
    val dedocClient = new DedocClient(host = "localhost", port = 1231, backend = backend)

    val document = dedocClient.handleFile(file, parameters = Map("language" -> "rus"))
    document match {
        case Failure(exception: DedocException) => println("Failure: " + exception.getMessage)
        case Failure(exception) => println("Other fail: " + exception.getMessage)
        case Success(value) => println(value.metadata.accessTime)
    }

}

