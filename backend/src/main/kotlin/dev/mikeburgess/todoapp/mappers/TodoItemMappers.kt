package dev.mikeburgess.todoapp.mappers

import dev.mikeburgess.todoapp.api.model.TodoItemData
import dev.mikeburgess.todoapp.entity.TodoItem

fun TodoItem.asData(): TodoItemData =
    TodoItemData(summary, id, createdAt, updatedAt)

fun TodoItemData.asEntity(): TodoItem =
    TodoItem(summary, id, createdAt, updatedAt)
