package dev.mikeburgess.todoapp.repository

import dev.mikeburgess.todoapp.entity.TodoItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TodoItemRepository : JpaRepository<TodoItem, Int>
