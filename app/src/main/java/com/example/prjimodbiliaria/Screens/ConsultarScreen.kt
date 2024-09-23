package com.example.prjimodbiliaria.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import coil.compose.rememberImagePainter
import com.example.prjimodbiliaria.RoomDB.Imovel
import com.example.prjimodbiliaria.ViewModel.ImovelViewModel
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ConsultarScreen(viewModel: ImovelViewModel, navController: NavController) {
    val imoveis by viewModel.getAllImoveis().collectAsState(initial = emptyList())

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEFEFEF)) // Cor de fundo suave
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)
                        .background(Color(0xFF337233), shape = RoundedCornerShape(16.dp))
                        .border(
                            width = 2.dp, // Ajuste a largura da borda conforme necessário
                            color = Color(0xFF337233), // Cor da borda
                            shape = RoundedCornerShape(12.dp) // Use o mesmo shape para borda
                        ),
                    contentAlignment = Alignment.Center // Alinhando o texto ao centro
                ) {
                    Text(
                        text = "Lista de Imóveis Cadastrados",
                        style = MaterialTheme.typography.titleLarge.copy(
                            color = Color(0xFFE8E8E8),
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(8.dp) // Ajustando o padding para melhor estética
                    )
                }
            }
        }

        if (imoveis.isEmpty()) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Nenhum imóvel cadastrado.",
                        style = MaterialTheme.typography.bodyLarge.copy(color = Color(0xFF7D7D7D)), // Cinza mais escuro
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        } else {
            val imoveisVenda = imoveis.filter { it.disponivelParaVenda }
            val imoveisAluguel = imoveis.filter { !it.disponivelParaVenda }

            if (imoveisVenda.isNotEmpty()) {
                item {
                    Text(
                        text = "Imóveis à Venda",
                        style = MaterialTheme.typography.titleMedium.copy(color = Color(0xFF9B1B30), fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
                items(imoveisVenda) { imovel ->
                    ImovelItem(imovel, viewModel, navController)
                }
            }

            if (imoveisAluguel.isNotEmpty()) {
                // Adicionando um Divider entre as seções
                if (imoveisVenda.isNotEmpty()) {
                    item {
                        Divider(
                            color = Color(0xFF919090), // Cor do Divider
                            thickness = 1.dp // Espessura do Divider
                        )
                    }
                }

                item {
                    Text(
                        text = "Imóveis para Aluguel",
                        style = MaterialTheme.typography.titleMedium.copy(color = Color(0xFF9B1B30), fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
                items(imoveisAluguel) { imovel ->
                    ImovelItem(imovel, viewModel, navController)
                }
            }
        }
    }
}

@Composable
fun ImovelItem(imovel: Imovel, viewModel: ImovelViewModel, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(
                width = 2.dp, // Ajuste a largura da borda conforme necessário
                color = Color(0xFF337233), // Cor da borda
                shape = RoundedCornerShape(16.dp) // Use o mesmo shape para borda
            ),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .background(Color(0xFF337233), shape = RoundedCornerShape(16.dp))
                    .border(
                        width = 2.dp, // Ajuste a largura da borda conforme necessário
                        color = Color(0xFF337233), // Cor da borda
                        shape = RoundedCornerShape(12.dp) // Use o mesmo shape para borda
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Tipo Imóvel: ${imovel.tipoImovel}",
                    style = MaterialTheme.typography.titleMedium.copy(color = Color.White)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Localização: ${imovel.localizacao}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Preço: R$ ${imovel.preco}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Contato: ${imovel.contato}", style = MaterialTheme.typography.bodyMedium)
            Text(text = imovel.descricao, style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(8.dp))

            if (!imovel.imagemUri.isNullOrEmpty()) {
                Image(
                    painter = rememberImagePainter(data = imovel.imagemUri),
                    contentDescription = "Imagem do imóvel",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(8.dp)), // Bordas arredondadas para a imagem
                    contentScale = ContentScale.Crop
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(8.dp)) // Bordas arredondadas
                        .background(Color(0xFFCCCCCC)), // Cinza
                    contentAlignment = Alignment.Center
                ) {
                    Text("Sem imagem disponível", color = Color.White)
                }
            }

            // Botões para editar e excluir
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = {
                    navController.navigate("editar/${imovel.id}")
                }) {
                    Icon(Icons.Default.Edit, contentDescription = "Editar")
                }
                IconButton(onClick = {
                    viewModel.deleteImovel(imovel)
                }) {
                    Icon(Icons.Default.Delete, contentDescription = "Excluir")
                }
            }
        }
    }
}