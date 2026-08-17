package com.example.lab5.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun TabsRow(
    selectedTab: String,
    onTabSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val tabs = listOf("Para ti", "Siguiendo", "Destacados")

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        tabs.forEach { tab ->
            Text(
                text = tab,
                fontWeight = if (selectedTab == tab) FontWeight.Bold else FontWeight.Normal,
                color = if (selectedTab == tab) Color.Black else Color.Gray,
                modifier = Modifier
                    .clickable { onTabSelected(tab) }
                    .padding(8.dp)
            )
        }
    }
}