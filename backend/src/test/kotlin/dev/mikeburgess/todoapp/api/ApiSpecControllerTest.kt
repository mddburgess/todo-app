package dev.mikeburgess.todoapp.api

import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(ApiSpecController::class)
class ApiSpecControllerTest(
    @Autowired val mockMvc: MockMvc,
) {

    @ParameterizedTest
    @ValueSource(strings = ["/api/spec", "/api/spec/", "/api/spec/openapi.json"])
    fun `URL returns root specification`(path: String) {
        mockMvc.perform(get(path))
            .andExpect(status().isOk)
            .andExpect(forwardedUrl("/spec/openapi.json"))
    }

    @Test
    fun `URL returns schema specification`() {
        mockMvc.perform(get("/api/spec/schema/todo_item.json"))
            .andExpect(status().isOk)
            .andExpect(forwardedUrl("/spec/schema/todo_item.json"))
    }

    @Test
    fun `URL returns HTML documentation`() {
        mockMvc.perform(get("/api/docs"))
            .andExpect(status().isOk)
            .andExpect(forwardedUrl("/docs/index.html"))
    }
}
