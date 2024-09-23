package com.example.prjimodbiliaria.RoomDB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "imovel")
data class Imovel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val tipoImovel: String,
    val disponivelParaVenda: Boolean,
    val localizacao: String,
    val preco: Double,
    val descricao: String,
    val contato: String,
    val imagemUri: String?
)