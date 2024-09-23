package com.example.prjimodbiliaria.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prjimodbiliaria.Repository.ImovelRepository
import com.example.prjimodbiliaria.RoomDB.Imovel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ImovelViewModel(private val repository: ImovelRepository) : ViewModel() {

    fun getAllImoveis(): Flow<List<Imovel>> = repository.getAllImoveis()

    suspend fun getImovelById(id: Int): Imovel? = repository.getImovelById(id)

    fun addImovel(imovel: Imovel) {
        viewModelScope.launch {
            repository.insertImovel(imovel)
        }
    }

    fun updateImovel(imovel: Imovel) {
        viewModelScope.launch {
            repository.updateImovel(imovel)
        }
    }

    fun deleteImovel(imovel: Imovel) {
        viewModelScope.launch {
            repository.deleteImovel(imovel)
        }
    }
}