package dev.mikeburgess.todoapp.web

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@WebMvcTest(FrontendController::class)
class FrontendControllerTest(
    @Autowired val mockMvc: MockMvc
) {

    @Test
    fun `URL returns index page`() {
        mockMvc.get("/index").andExpect {
            status { isOk() }
            forwardedUrl("/index.html")
        }
    }
}
