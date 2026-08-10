package com.example.lab5.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab5.data.mockArticles
import com.example.lab5.model.Article
import com.example.lab5.ui.components.*

/*
 * RUTA B - RESPUESTAS:
 * 1. ¿Qué pasa si quitas el weight(1f) de la columna del artículo?
 * Si se quita el `weight(1f)`, la columna intentará ocupar todo el ancho que necesiten sus textos.
 * Al no estar limitada, empujará la miniatura (Box) fuera de la pantalla o la comprimirá,
 * rompiendo el diseño. El `weight` obliga a la columna a medir primero a la miniatura y ocupar
 * únicamente el espacio sobrante.
 *
 * 2. ¿Por qué el componente de artículo recibe un Modifier por parámetro en lugar de fijar su margen?
 * Para garantizar la reutilización. Si el componente tuviera un `padding` escrito adentro (hardcoded),
 * no se podría usar en dos pantallas distintas que requieran diferentes márgenes (por ejemplo, una con
 * 16.dp y otra con 8.dp) sin modificar el componente. Delegar el Modifier permite que la pantalla
 * decida el espacio exterior.
 */

@Composable
fun FeedScreen(articles: List<Article>, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        TopBar()
        Spacer(modifier = Modifier.height(8.dp))
        TabsRow()
        Spacer(modifier = Modifier.height(16.dp))
        Separator()
        Spacer(modifier = Modifier.height(16.dp))

        articles.forEach { article ->
            ArticleItem(article = article)
            Spacer(modifier = Modifier.height(24.dp))
            Separator()
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FeedScreenPreview() {
    FeedScreen(articles = mockArticles)
}