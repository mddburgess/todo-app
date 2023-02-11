package dev.mikeburgess.todoapp.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.OffsetDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id

@Entity
class TodoItem(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Int? = null,
    val summary: String,
    @CreationTimestamp
    val createdAt: OffsetDateTime? = null,
    @UpdateTimestamp
    val updatedAt: OffsetDateTime? = null,
)
