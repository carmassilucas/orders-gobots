package ai.gobots.marketplace_api.infra.exception

import ai.gobots.marketplace_api.core.exception.MarketplaceAPIException
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ProblemDetail
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MarketplaceAPIException::class)
    fun marketplaceAPIException(exception: MarketplaceAPIException): ProblemDetail {
        val problem = ProblemDetail.forStatusAndDetail(
            HttpStatusCode.valueOf(exception.statusCode),
            exception.detail
        )
        problem.title = exception.message

        return problem
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidException(exception: MethodArgumentNotValidException): ProblemDetail {
        val params = exception.bindingResult.fieldErrors.map {
            InvalidParam(it.field, it.defaultMessage)
        }

        val problem = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY)
        problem.title = "Your request parameters didn't validate"
        problem.setProperty("invalid-params", params)

        return problem
    }

    private data class InvalidParam(
        val name: String,
        val reason: String?
    )
}