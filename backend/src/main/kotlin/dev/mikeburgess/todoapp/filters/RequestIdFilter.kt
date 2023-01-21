package dev.mikeburgess.todoapp.filters

import mu.withLoggingContext
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.util.UUID
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

private const val X_REQUEST_ID = "X-Request-Id"

@Component
@Order(Filters.REQUEST_ID)
class RequestIdFilter : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val requestId = UUID.randomUUID().toString().substring(24)
        response.addHeader(X_REQUEST_ID, requestId)

        withLoggingContext(X_REQUEST_ID to requestId) {
            filterChain.doFilter(request, response)
        }
    }
}
