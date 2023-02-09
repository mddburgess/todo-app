package dev.mikeburgess.todoapp.api

import dev.mikeburgess.todoapp.api.model.TodoItemData
import dev.mikeburgess.todoapp.service.TodoItemService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
class TodosApiController(
    val todoItemService: TodoItemService
) : TodosApi {

    override fun listTodoItems(): ResponseEntity<List<TodoItemData>> {
        val todoItems = todoItemService.listAll()
        return ResponseEntity.ok(todoItems)
    }

    override fun createTodoItem(todoItemData: TodoItemData): ResponseEntity<TodoItemData> {
        val todoItem = todoItemService.create(todoItemData)
        val location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(todoItem.id)
            .toUri()
        return ResponseEntity.created(location).body(todoItem)
    }

    override fun fetchTodoItem(id: Int): ResponseEntity<TodoItemData> {
        val todoItem = todoItemService.findById(id)
        return ResponseEntity.ok(todoItem)
    }

    override fun deleteTodoItem(id: Int): ResponseEntity<Unit> {
        todoItemService.deleteById(id)
        return ResponseEntity.noContent().build()
    }
}
