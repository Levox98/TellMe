package com.tellme.data_questions.domain.entity

import java.util.Date

data class Question(
    val objectId: String,
    val text: String,
    val assignedDate: Date
)
