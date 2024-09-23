package com.example.prjimodbiliaria.Screens

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import coil.compose.rememberImagePainter
import com.example.prjimodbiliaria.RoomDB.Imovel
import com.example.prjimodbiliaria.ViewModel.ImovelViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CadastroScreen(viewModel: ImovelViewModel) {

    var tipoImovel by remember { mutableStateOf("") }
    var disponivelParaVenda by remember { mutableStateOf(true) }
    var localizacao by remember { mutableStateOf("") }
    var preco by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }
    var contato by remember { mutableStateOf("") }
    var uriImagem by remember { mutableStateOf<Uri?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? -> uriImagem = uri }
    )

    val contexto = LocalContext.current

    val redColor = Color(0xFF9B1B30) // Vermelho de Portugal
    val greenColor = Color(0xFF006600) // Verde de Portugal
    val backgroundColor = Color(0xFFF0F0F0) // Cor de fundo suave

    Column(
        modifier = Modifier.fillMaxSize().background(Color(0xFFF0F0F0)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize() .padding(16.dp),

            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = "Cadastro de Imóveis",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.5.sp
                    ),
                    color = greenColor,
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    textAlign = TextAlign.Center
                )
            }

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

            item {
                Text("Disponível para:", color = greenColor)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start // Alinhamento à esquerda sem espaço
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

            item {
                Button(
                    onClick = {
                        val precoDouble = preco.toDoubleOrNull()
                        if (tipoImovel.isEmpty() || localizacao.isEmpty() || precoDouble == null || descricao.isEmpty() || contato.isEmpty()) {
                            Toast.makeText(contexto, "Preencha todos os campos corretamente", Toast.LENGTH_SHORT).show()
                        } else {
                            val novoImovel = Imovel(
                                tipoImovel = tipoImovel,
                                disponivelParaVenda = disponivelParaVenda,
                                localizacao = localizacao,
                                preco = precoDouble,
                                descricao = descricao,
                                contato = contato,
                                imagemUri = uriImagem?.toString()
                            )
                            viewModel.addImovel(novoImovel)
                            Toast.makeText(contexto, "Imóvel cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
                            // Resetando os campos
                            tipoImovel = ""
                            disponivelParaVenda = true
                            localizacao = ""
                            preco = ""
                            descricao = ""
                            contato = ""
                            uriImagem = null
                        }
                    },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = greenColor),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Cadastrar Imóvel", color = Color.White)
                }
            }
        }
    }
}