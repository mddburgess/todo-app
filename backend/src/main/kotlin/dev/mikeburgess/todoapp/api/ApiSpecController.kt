package dev.mikeburgess.todoapp.api

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import javax.servlet.http.HttpServletRequest

@Controller
class ApiSpecController {

    @GetMapping(path = ["/api/spec/**"])
    fun getApiSpec(request: HttpServletRequest): String {
        val specPath = request.requestURI.split(request.contextPath + "/api/spec/")
        return when {
            specPath.size == 1 || specPath[1].isEmpty() -> "/spec/openapi.json"
            else -> "/spec/${specPath[1]}"
        }
    }

    @GetMapping(path = ["/api/docs"])
    fun getApiDocs() = "/docs/index.html"
}
