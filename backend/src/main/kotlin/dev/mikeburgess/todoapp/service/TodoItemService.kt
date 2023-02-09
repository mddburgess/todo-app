package dev.mikeburgess.todoapp.service

import dev.mikeburgess.todoapp.api.model.TodoItemData
import dev.mikeburgess.todoapp.exceptions.NotFoundException
import dev.mikeburgess.todoapp.mappers.asData
import dev.mikeburgess.todoapp.mappers.asEntity
import dev.mikeburgess.todoapp.repository.TodoItemRepository
import org.springframework.stereotype.Service

@Service
class TodoItemService(
    val repository: TodoItemRepository
) {

    fun listAll() =
        repository.findAll().map { it.asData() }

    fun create(todoItem: TodoItemData) =
        repository.save(todoItem.asEntity()).asData()

    fun findById(id: Int) =
        repository.findById(id).orElseThrow(::NotFoundException).asData()

    fun deleteById(id: Int) {
        if (repository.existsById(id)) {
            repository.deleteById(id)
        } else {
            throw NotFoundException()
        }
    }
}
