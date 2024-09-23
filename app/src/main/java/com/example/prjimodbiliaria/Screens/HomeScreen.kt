package com.example.prjimodbiliaria.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.prjimodbiliaria.R

@Composable
fun HomeScreen() {
    // Define a cor de fundo
    val backgroundColor = Color(0xFF5A7C5A) // Altere para a cor desejada

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        contentAlignment = Alignment.Center // Centraliza o conteúdo
    ) {
        Image(
            painter = painterResource(id = R.drawable.portuga), // Substitua pelo seu logo
            contentDescription = "Logo",
            modifier = Modifier.size(200.dp) // Altere o tamanho conforme necessário
        )
    }
}