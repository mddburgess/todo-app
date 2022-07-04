package dev.mikeburgess.todoapp.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class FrontendController {

    @GetMapping(path = ["/{x:[\\w\\-]+}", "/{x:(?!api$).*}/*/{y:[\\w\\-]+}"])
    fun forwardToFrontend() = "/index.html"
}
