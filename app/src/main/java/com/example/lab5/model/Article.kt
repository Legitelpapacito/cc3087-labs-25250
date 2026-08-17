package com.example.lab5.model


data class Article(
    val author: String,
    val title: String,
    val excerpt: String,
    val readingMinutes: Int,
    val date: String,
    val avatarColor: Long,
    val thumbnailColor: Long,
    val isAuthorFollowed: Boolean = false,
    val isFeatured: Boolean = false
)