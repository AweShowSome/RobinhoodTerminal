import com.sksamuel.hoplite.ConfigLoader
import model.ApplicationConfig
import shell.RobinhoodShell
import java.io.File

suspend fun main(args: Array<String>) {
    val config: ApplicationConfig = ConfigLoader().loadConfigOrThrow(File("src/main/resources/application.conf"))
    println("Starting shell\n")
    RobinhoodShell(config).start()
}
