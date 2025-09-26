package com.dong.reader.model

data class MBook(
    val id: String?,
    val title: String,
    val authors: String,
    val notes: String,
    val photoUrl: String,
    val categories: List<String>,
    val publishedDate: String,
    val pageCount: Int,
)
