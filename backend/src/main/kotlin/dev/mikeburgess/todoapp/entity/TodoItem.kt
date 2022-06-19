package dev.mikeburgess.todoapp.entity

import dev.mikeburgess.todoapp.api.model.TodoItemData
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.OffsetDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id

@Entity
class TodoItem(
    private val summary: String,
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Int?,
    @CreationTimestamp
    val createdAt: OffsetDateTime?,
    @UpdateTimestamp
    val updatedAt: OffsetDateTime?,
) {
    constructor(data: TodoItemData) : this(
        data.summary,
        data.id,
        data.createdAt,
        data.updatedAt,
    )

    fun toData() = TodoItemData(summary, id, createdAt, updatedAt)
}
