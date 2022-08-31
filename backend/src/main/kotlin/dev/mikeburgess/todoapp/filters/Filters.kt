package dev.mikeburgess.todoapp.filters

import org.springframework.core.Ordered

object Filters {
    const val REQUEST_ID = Ordered.HIGHEST_PRECEDENCE
    const val REQUEST_LOG = REQUEST_ID + 1
    const val CLACKS_OVERHEAD = REQUEST_LOG + 1
}
