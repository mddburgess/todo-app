package dev.mikeburgess.todoapp.filters

import mu.KotlinLogging
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
class RequestLogFilter : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val shouldLog = request.requestURI.startsWith("/api")

        try {
            if (shouldLog) {
                log.info { "=>> ${request.method} ${request.requestURI}" }
            }
            filterChain.doFilter(request, response)
        } finally {
            if (shouldLog) {
                val status = HttpStatus.resolve(response.status)
                log.info { "<<= ${status?.value()} ${status?.reasonPhrase}" }
            }
        }
    }
}
