package dev.mikeburgess.todoapp.mappers

import dev.mikeburgess.todoapp.api.model.TodoItemData
import dev.mikeburgess.todoapp.entity.TodoItem

fun TodoItem.asData(): TodoItemData =
    TodoItemData(id, summary, createdAt, updatedAt)

fun TodoItemData.asEntity(): TodoItem =
    TodoItem(id, summary, createdAt, updatedAt)
