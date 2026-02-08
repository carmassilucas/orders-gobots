package ai.gobots.receiver_api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ReceiverApiApplication

fun main(args: Array<String>) {
	runApplication<ReceiverApiApplication>(*args)
}
