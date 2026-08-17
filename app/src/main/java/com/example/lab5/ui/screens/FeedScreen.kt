package com.example.lab5.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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

    // 1. ESTADOS DE LA PANTALLA
    var searchQuery by rememberSaveable { mutableStateOf("") }
    var showShortReadsOnly by rememberSaveable { mutableStateOf(value = false) }
    var selectedTab by rememberSaveable { mutableStateOf("Para ti") }
    var applauseCount by rememberSaveable { mutableIntStateOf(0) }

    // 2. LÓGICA DE FILTRADO DERIVADO
    val visibleArticles = articles.filter { article ->
        val matchesSearch = article.title.contains(searchQuery, ignoreCase = true) ||
                article.author.contains(searchQuery, ignoreCase = true)

        val matchesLength = if (showShortReadsOnly) article.readingMinutes <= 5 else true

        val matchesTab = when (selectedTab) {
            "Siguiendo" -> article.isAuthorFollowed
            "Destacados" -> article.isFeatured
            else -> true
        }

        matchesSearch && matchesLength && matchesTab
    }

    // 3. INTERFAZ GRÁFICA
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
    ) {
        TopBar()
        Spacer(modifier = Modifier.height(8.dp))

        // Controles de Búsqueda
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Buscar por título o autor") },
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Controles de Filtro Corto y Aplausos
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Switch(
                    checked = showShortReadsOnly,
                    onCheckedChange = { showShortReadsOnly = it }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Solo lecturas cortas")
            }

            TextButton(onClick = { applauseCount++ }) {
                Text("Aplaudir · $applauseCount")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Pestañas
        TabsRow(
            selectedTab = selectedTab,
            onTabSelected = { selectedTab = it }
        )

        Spacer(modifier = Modifier.height(16.dp))
        Separator()
        Spacer(modifier = Modifier.height(16.dp))

        // 4. MOSTRAR RESULTADOS
        if (visibleArticles.isEmpty()) {
            Text("0 resultados", style = MaterialTheme.typography.labelLarge)
            Spacer(modifier = Modifier.height(16.dp))
            Text("No se encontraron artículos\nCambia la pestaña, la búsqueda o el filtro.")
        } else {
            Text("${visibleArticles.size} resultados", style = MaterialTheme.typography.labelLarge)
            Spacer(modifier = Modifier.height(16.dp))

            visibleArticles.forEach { article ->
                ArticleItem(article = article)
                Spacer(modifier = Modifier.height(24.dp))
                Separator()
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FeedScreenPreview() {
    FeedScreen(articles = mockArticles)
}