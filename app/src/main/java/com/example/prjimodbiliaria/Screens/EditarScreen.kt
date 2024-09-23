package com.example.prjimodbiliaria.Screens

import androidx.compose.runtime.*
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.prjimodbiliaria.Navigation.BottomNavItem
import com.example.prjimodbiliaria.Navigation.MyTopAppBar
import com.example.prjimodbiliaria.RoomDB.Imovel
import com.example.prjimodbiliaria.ViewModel.ImovelViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarScreen(navController: NavController, viewModel: ImovelViewModel, id: Int) {
    val contexto = LocalContext.current
    var imovel by remember { mutableStateOf<Imovel?>(null) }

    // Recupera os dados do imóvel pelo ID
    LaunchedEffect(id) {
        imovel = viewModel.getImovelById(id)
    }

    // Se o imóvel não estiver carregado ainda, mostra um carregando ou algo similar
    if (imovel == null) {
        CircularProgressIndicator()
        return
    }

    var tipoImovel by remember { mutableStateOf(imovel!!.tipoImovel) }
    var disponivelParaVenda by remember { mutableStateOf(imovel!!.disponivelParaVenda) }
    var localizacao by remember { mutableStateOf(imovel!!.localizacao) }
    var preco by remember { mutableStateOf(imovel!!.preco.toString()) }
    var descricao by remember { mutableStateOf(imovel!!.descricao) }
    var contato by remember { mutableStateOf(imovel!!.contato) }
    var uriImagem by remember { mutableStateOf<Uri?>(imovel!!.imagemUri?.let { Uri.parse(it) }) }

    // Seletor de Imagem
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? -> uriImagem = uri }
    )

    // Função para converter a URI da imagem em String
    fun getUriString(uri: Uri?): String? {
        return uri?.toString()
    }

    val redColor = Color(0xFFFF0000) // Vermelho de Portugal
    val greenColor = Color(0xFF006600) // Verde de Portugal
    val backgroundColor = Color(0xFFF0F0F0) // Cor de fundo suave

        Column(
            modifier = Modifier.fillMaxSize().background(Color(0xFFF6E0C8)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Text(
                        text = "Edição de Imóveis",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.5.sp
                        ),
                        color = greenColor,
                        modifier = Modifier.fillMaxWidth().padding(8.dp),
                        textAlign = TextAlign.Center
                    )
                }

                // Tipo do Imóvel
                item {
                    TextField(
                        value = tipoImovel,
                        onValueChange = { tipoImovel = it },
                        label = { Text("Tipo do Imóvel") },
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = backgroundColor,
                            focusedIndicatorColor = redColor,
                            focusedLabelColor = greenColor,
                            cursorColor = greenColor
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth().shadow(4.dp, RoundedCornerShape(12.dp))
                    )
                }

                // Disponibilidade
                item {
                    Text("Disponível para:", color = greenColor)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            RadioButton(
                                selected = disponivelParaVenda,
                                onClick = { disponivelParaVenda = true },
                                colors = RadioButtonDefaults.colors(selectedColor = redColor)
                            )
                            Text("Venda", color = greenColor)
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            RadioButton(
                                selected = !disponivelParaVenda,
                                onClick = { disponivelParaVenda = false },
                                colors = RadioButtonDefaults.colors(selectedColor = redColor)
                            )
                            Text("Aluguel", color = greenColor)
                        }
                    }
                }

                // Localização
                item {
                    TextField(
                        value = localizacao,
                        onValueChange = { localizacao = it },
                        label = { Text("Localização") },
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = backgroundColor,
                            focusedIndicatorColor = redColor,
                            focusedLabelColor = greenColor,
                            cursorColor = greenColor
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth().shadow(4.dp, RoundedCornerShape(12.dp))
                    )
                }

                // Preço
                item {
                    TextField(
                        value = preco,
                        onValueChange = { preco = it },
                        label = { Text("Preço (€)") },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = backgroundColor,
                            focusedIndicatorColor = redColor,
                            focusedLabelColor = greenColor,
                            cursorColor = greenColor
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth().shadow(4.dp, RoundedCornerShape(12.dp))
                    )
                }

                // Descrição
                item {
                    TextField(
                        value = descricao,
                        onValueChange = { descricao = it },
                        label = { Text("Descrição") },
                        maxLines = 3,
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = backgroundColor,
                            focusedIndicatorColor = redColor,
                            focusedLabelColor = greenColor,
                            cursorColor = greenColor
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth().shadow(4.dp, RoundedCornerShape(12.dp))
                    )
                }

                // Contato
                item {
                    TextField(
                        value = contato,
                        onValueChange = { contato = it },
                        label = { Text("Contato do Cliente") },
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = backgroundColor,
                            focusedIndicatorColor = redColor,
                            focusedLabelColor = greenColor,
                            cursorColor = greenColor
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth().shadow(4.dp, RoundedCornerShape(12.dp))
                    )
                }

                // Upload de Imagens
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(if (uriImagem != null) 250.dp else 120.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .border(2.dp, greenColor, RoundedCornerShape(12.dp))
                            .clickable { launcher.launch("image/*") }
                            .background(Color.LightGray),
                        contentAlignment = Alignment.Center
                    ) {
                        if (uriImagem != null) {
                            Image(
                                painter = rememberImagePainter(uriImagem),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                        } else {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Selecionar Imagem",
                                    tint = Color.Gray,
                                    modifier = Modifier.size(40.dp)
                                )
                                Text("Selecionar Imagem", color = Color.Gray)
                            }
                        }
                    }
                }

                // Botão de Atualização
                item {
                    Button(
                        onClick = {
                            // Validação simples
                            val precoDouble = preco.toDoubleOrNull()
                            if (tipoImovel.isEmpty() || localizacao.isEmpty() || precoDouble == null || descricao.isEmpty() || contato.isEmpty()) {
                                Toast.makeText(
                                    contexto,
                                    "Preencha todos os campos corretamente",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                // Criar objeto Imovel atualizado
                                val imovelAtualizado = Imovel(
                                    id = imovel!!.id, // Preservar o ID do imóvel
                                    tipoImovel = tipoImovel,
                                    disponivelParaVenda = disponivelParaVenda,
                                    localizacao = localizacao,
                                    preco = precoDouble,
                                    descricao = descricao,
                                    contato = contato,
                                    imagemUri = getUriString(uriImagem)
                                )

                                // Chamar a função de atualizar imóvel no ViewModel
                                viewModel.updateImovel(imovelAtualizado)

                                // Exibir uma mensagem de sucesso
                                Toast.makeText(
                                    contexto,
                                    "Imóvel atualizado com sucesso!",
                                    Toast.LENGTH_SHORT
                                ).show()

                                // Navegar para ConsultarScreen
                                navController.navigate(BottomNavItem.Settings.route)
                            }
                        },
                        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = greenColor),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Atualizar Imóvel", color = Color.White)
                    }
                }
            }
        }

}