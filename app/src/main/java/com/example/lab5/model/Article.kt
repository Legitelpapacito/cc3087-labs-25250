package com.example.lab5.model

data class Article(
    val author: String,
    val title: String,
    val extract: String,
    val readTimeMinutes: Int,
    val date: String,
    val avatarColor: Long,
    val thumbnailColor: Long
)