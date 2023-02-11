package dev.mikeburgess.todoapp.service

import dev.mikeburgess.todoapp.api.model.TodoItemData
import dev.mikeburgess.todoapp.entity.TodoItem
import dev.mikeburgess.todoapp.exceptions.NotFoundException
import dev.mikeburgess.todoapp.repository.TodoItemRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import io.mockk.slot
import io.mockk.verifySequence
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.time.OffsetDateTime
import java.util.Optional

@ExtendWith(MockKExtension::class)
class TodoItemServiceTest {

    @InjectMockKs
    lateinit var todoItemService: TodoItemService

    @MockK
    lateinit var todoItemRepository: TodoItemRepository

    private val todoItem = TodoItem(
        id = 1,
        summary = "summary",
        createdAt = OffsetDateTime.now(),
        updatedAt = OffsetDateTime.now()
    )

    private val todoItemData = TodoItemData(
        id = 1,
        summary = "summary",
        createdAt = todoItem.createdAt,
        updatedAt = todoItem.updatedAt
    )

    @Test
    fun `listAll returns list of todo items`() {
        every { todoItemRepository.findAll() } returns listOf(todoItem)

        val result = todoItemService.listAll()

        assertThat(result).containsExactly(todoItemData)

        verifySequence {
            todoItemRepository.findAll()
        }
    }

    @Test
    fun `create returns created todo item`() {
        every { todoItemRepository.save(any()) } returns todoItem

        val newTodoItemData = TodoItemData(summary = "summary")
        val result = todoItemService.create(newTodoItemData)

        assertThat(result).isEqualTo(todoItemData)

        val slot = slot<TodoItem>()
        verifySequence {
            todoItemRepository.save(capture(slot))
        }

        assertThat(slot.captured)
            .usingRecursiveComparison()
            .isEqualTo(TodoItem(summary = "summary"))
    }

    @Test
    fun `findById returns todo item`() {
        every { todoItemRepository.findById(any()) } returns Optional.of(todoItem)

        val result = todoItemService.findById(1)

        assertThat(result).isEqualTo(todoItemData)

        verifySequence {
            todoItemRepository.findById(1)
        }
    }

    @Test
    fun `findById throws NotFoundException`() {
        every { todoItemRepository.findById(any()) } returns Optional.empty()

        assertThatThrownBy { todoItemService.findById(1) }
            .isInstanceOf(NotFoundException::class.java)

        verifySequence {
            todoItemRepository.findById(1)
        }
    }

    @Test
    fun delete() {
        every { todoItemRepository.existsById(any()) } returns true
        every { todoItemRepository.deleteById(any()) } just runs

        todoItemService.deleteById(1)

        verifySequence {
            todoItemRepository.existsById(1)
            todoItemRepository.deleteById(1)
        }
    }

    @Test
    fun `delete throws NotFoundException`() {
        every { todoItemRepository.existsById(any()) } returns false

        assertThatThrownBy { todoItemService.deleteById(1) }
            .isInstanceOf(NotFoundException::class.java)

        verifySequence {
            todoItemRepository.existsById(1)
        }
    }
}
