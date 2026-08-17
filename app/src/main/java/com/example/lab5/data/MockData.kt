package com.example.lab5.data

import com.example.lab5.model.Article

val mockArticles = listOf(
    Article(
        author = "Ana Robles",
        title = "Por qué su primera app se siente lenta",
        excerpt = "Tres decisiones de arranque que nadie revisa hasta que ya es tarde.",
        readingMinutes = 5,
        date = "12 dic",
        avatarColor = 0xFF4CAF50,
        thumbnailColor = 0xFF2196F3,
        isAuthorFollowed = true,
        isFeatured = false
    ),
    Article(
        author = "Diego Marroquín",
        title = "El error de medir productividad en líneas de código",
        excerpt = "Qué pasa cuando el equipo optimiza para la métrica equivocada.",
        readingMinutes = 8,
        date = "9 dic",
        avatarColor = 0xFFFF9800,
        thumbnailColor = 0xFF9C27B0,
        isAuthorFollowed = false,
        isFeatured = true
    ),
    Article(
        author = "Sofía René",
        title = "Leí la documentación completa para que no tengas que hacerlo",
        excerpt = "Un resumen honesto de lo que sí importa del primer capítulo.",
        readingMinutes = 4,
        date = "3 dic",
        avatarColor = 0xFFE91E63,
        thumbnailColor = 0xFF00BCD4,
        isAuthorFollowed = true,
        isFeatured = true
    )
)
