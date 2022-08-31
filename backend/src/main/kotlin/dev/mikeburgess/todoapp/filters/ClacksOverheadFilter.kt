package dev.mikeburgess.todoapp.filters

import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

private const val X_CLACKS_OVERHEAD = "X-Clacks-Overhead"

@Component
@Order(Filters.CLACKS_OVERHEAD)
class ClacksOverheadFilter : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        response.addHeader(X_CLACKS_OVERHEAD, "GNU Terry Pratchett")
        filterChain.doFilter(request, response)
    }
}
