package dev.mikeburgess.todoapp.filters

import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

private val log = KotlinLogging.logger { }

@Component
@Order(Filters.REQUEST_LOG)
class RequestLogFilter(
    @Value("\${api.base-path:/api/v1}")
    private val apiBasePath: String
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val shouldLog = request.requestURI.startsWith(apiBasePath)

        try {
            if (shouldLog) {
                log.info { "=>> ${request.method} ${request.requestURI}" }
            }
            filterChain.doFilter(request, response)
        } finally {
            if (shouldLog) {
                val status = HttpStatus.resolve(response.status)!!
                val message = "<<= ${status.value()} ${status.reasonPhrase}"
                when {
                    status.is5xxServerError -> log.error { message }
                    status.is4xxClientError -> log.warn { message }
                    else -> log.info { message }
                }
            }
        }
    }
}
