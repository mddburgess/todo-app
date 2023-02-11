package dev.mikeburgess.todoapp.api

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import dev.mikeburgess.todoapp.api.model.TodoItemData
import dev.mikeburgess.todoapp.exceptions.NotFoundException
import dev.mikeburgess.todoapp.service.TodoItemService
import io.mockk.called
import io.mockk.every
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import org.hamcrest.Matchers.endsWith
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import java.time.OffsetDateTime

@WebMvcTest(TodosApiController::class)
class TodosApiControllerTest(
    @Autowired val mockMvc: MockMvc,
    @Autowired val jsonMapper: ObjectMapper
) {

    @MockkBean
    lateinit var todoItemService: TodoItemService

    private val todoItem = TodoItemData(
        id = 1,
        summary = "summary",
        createdAt = OffsetDateTime.now(),
        updatedAt = OffsetDateTime.now()
    )

    @Test
    fun `list todo items`() {
        every { todoItemService.listAll() } returns listOf(todoItem)

        mockMvc.get("/api/v1/todos").andExpect {
            status { isOk() }
            content {
                contentType(MediaType.APPLICATION_JSON)
                json(jsonMapper.writeValueAsString(listOf(todoItem)))
            }
        }
    }

    @Test
    fun `create todo item`() {
        val newTodoItem = TodoItemData(summary = "summary")

        every { todoItemService.create(newTodoItem) } returns todoItem

        mockMvc.post("/api/v1/todos") {
            contentType = MediaType.APPLICATION_JSON
            content = jsonMapper.writeValueAsString(newTodoItem)
        }.andExpect {
            status { isCreated() }
            header { string("location", endsWith("/api/v1/todos/1")) }
            content {
                contentType(MediaType.APPLICATION_JSON)
                json(jsonMapper.writeValueAsString(todoItem))
            }
        }
    }

    @Test
    fun `create empty todo item returns 400`() {
        mockMvc.post("/api/v1/todos") {
            contentType = MediaType.APPLICATION_JSON
            content = "{}"
        }.andExpect {
            status { isBadRequest() }
        }

        verify { todoItemService wasNot called }
    }

    @Test
    fun `fetch todo item`() {
        every { todoItemService.findById(1) } returns todoItem

        mockMvc.get("/api/v1/todos/1").andExpect {
            status { isOk() }
            content {
                contentType(MediaType.APPLICATION_JSON)
                json(jsonMapper.writeValueAsString(todoItem))
            }
        }
    }

    @Test
    fun `fetch nonexistent todo item returns 404`() {
        every { todoItemService.findById(1) } throws NotFoundException()

        mockMvc.get("/api/v1/todos/1").andExpect {
            status { isNotFound() }
        }
    }

    @Test
    fun `delete todo item`() {
        every { todoItemService.deleteById(1) } just runs

        mockMvc.delete("/api/v1/todos/1").andExpect {
            status { isNoContent() }
        }
    }

    @Test
    fun `delete nonexistent todo item returns 404`() {
        every { todoItemService.deleteById(1) } throws NotFoundException()

        mockMvc.delete("/api/v1/todos/1").andExpect {
            status { isNotFound() }
        }
    }
}
