package com.example.prjimodbiliaria.Screens

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.prjimodbiliaria.R
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun SobreScreen() {
    val iconList = listOf(
        painterResource(R.drawable.facebook),
        painterResource(R.drawable.instagram),
        painterResource(R.drawable.chamada_telefonica),
        painterResource(R.drawable.internet),
        painterResource(R.drawable.o_email)
    )
    val context = LocalContext.current
    val webLauncher: ActivityResultLauncher<Intent> =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            // Lidar com o resultado, se necessário
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF0F0F0))
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF0F0F0))
                .padding(14.dp)
        ) {
            item {
                // Substituição do Card de ícones
                ImovelCard(
                    title = "Contatos",
                    content = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.Black)) {
                            append("Entre em contato conosco: ")
                        }
                    }.toString(),
                    backgroundColor = Color(0xFF006600)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        for (index in iconList.indices) {
                            Box(contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .size(48.dp)
                                    .background(Color.White, shape = CircleShape)
                                    .clickable {
                                        val websiteUrl = when (index) {
                                            0 -> "https://www.facebook.com"
                                            1 -> "https://www.instagram.com"
                                            2 -> "tel:+123456789" // Número de telefone fictício
                                            3 -> "https://www.google.com"
                                            4 -> "mailto:contato@example.com" // E-mail fictício
                                            else -> null
                                        }

                                        websiteUrl?.let {
                                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(websiteUrl))
                                            webLauncher.launch(intent)
                                        }
                                    }) {
                                Image(
                                    painter = iconList[index],
                                    contentDescription = null,
                                    modifier = Modifier.size(28.dp),
                                    colorFilter = ColorFilter.tint(Color.Black)
                                )
                            }
                        }
                    }
                }
            }

            item {
                // Substituição do primeiro Card
                ImovelCard(
                    title = "Quem Somos",
                    content = "Somos uma imobiliária dedicada a ajudar você a encontrar a casa dos seus sonhos em Portugal. Com uma equipe de profissionais experientes, estamos comprometidos em tornar o processo de compra, venda ou aluguel de imóveis o mais simples e tranquilo possível. Nossa missão é proporcionar a você a melhor experiência imobiliária.",
                    backgroundColor = Color(0xFF006600) // Vermelho que lembra a bandeira de Portugal
                )
            }

            item {
                // Substituição do segundo Card
                ImovelCard(
                    title = "Horário de Funcionamento",
                    content = "Segunda a Sexta das 09:00h às 18:00h\n" +
                            "Sábado das 09:00h às 20:00h\n" +
                            "Estamos disponíveis para ajudá-lo a encontrar a propriedade perfeita durante esses horários.",
                    backgroundColor = Color(0xFF006600)
                )
            }

            item {
                // Card para a função Map
                ImovelCard(
                    title = "Localização",
                    content = "Veja onde estamos localizados:",
                    backgroundColor = Color(0xFF006600) // Cor de fundo verde
                ) {
                    // Aqui chamamos a função Map
                    Map()
                }
            }
        }
    }
}

@Composable
fun ImovelCard(
    title: String,
    content: String, // Modificado para aceitar AnnotatedString
    backgroundColor: Color,
    contentModifier: Modifier = Modifier,
    // Adicione um parâmetro de lambda para a Row dos ícones
    iconContent: @Composable () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(
                width = 2.dp,
                color = Color(0xFF337233), // Cor da borda
                shape = RoundedCornerShape(16.dp)
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
                    .background(backgroundColor, shape = RoundedCornerShape(16.dp))
                    .border(
                        width = 2.dp,
                        color = Color(0xFF337233),
                        shape = RoundedCornerShape(12.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium.copy(color = Color.White)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = content,
                textAlign = TextAlign.Justify,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black // Texto em preto para contraste
            )

            // Aqui chamamos o conteúdo dos ícones
            iconContent()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Map() {
    val lisbon = LatLng(38.7223, -9.1393) // Coordenadas de Lisboa
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(lisbon, 10f)
    }

    GoogleMap(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp),
        cameraPositionState = cameraPositionState,
        properties = MapProperties(mapType = MapType.HYBRID)
    ) {
        Marker(
            state = MarkerState(position = lisbon),
            title = "Lisboa",
            snippet = "Marker em Lisboa"
        )
    }
}