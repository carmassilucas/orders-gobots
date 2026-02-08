package ai.gobots.receiver_api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@SpringBootApplication
class ReceiverApiApplication

fun main(args: Array<String>) {
	runApplication<ReceiverApiApplication>(*args)
}
