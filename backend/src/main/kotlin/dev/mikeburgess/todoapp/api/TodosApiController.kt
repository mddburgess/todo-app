package dev.mikeburgess.todoapp.api

import dev.mikeburgess.todoapp.api.model.TodoItemData
import dev.mikeburgess.todoapp.entity.TodoItem
import dev.mikeburgess.todoapp.repository.TodoItemRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
class TodosApiController(
    val repository: TodoItemRepository,
) : TodosApi {

    override fun todosGet(): ResponseEntity<List<TodoItemData>> {
        val entities = repository.findAll()
        return ResponseEntity.ok(entities.map { it.toData() })
    }

    override fun todosPost(todoItemData: TodoItemData): ResponseEntity<TodoItemData> {
        val entity = repository.save(TodoItem(todoItemData))
        val location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(entity.id)
            .toUri()
        return ResponseEntity.created(location).body(entity.toData())
    }

    override fun todosIdGet(id: Int): ResponseEntity<TodoItemData> {
        val entity = repository.findByIdOrNull(id)
        return if (entity != null) {
            ResponseEntity.ok(entity.toData())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    override fun todosIdDelete(id: Int): ResponseEntity<Unit> {
        val entity = repository.findByIdOrNull(id)
        return if (entity != null) {
            repository.delete(entity)
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
