package com.example.prjimodbiliaria.Repository

import com.example.prjimodbiliaria.RoomDB.Imovel
import com.example.prjimodbiliaria.RoomDB.ImovelDao
import kotlinx.coroutines.flow.Flow

class ImovelRepository(private val imovelDao: ImovelDao) {

    fun getAllImoveis(): Flow<List<Imovel>> = imovelDao.getAllImoveis()

    suspend fun getImovelById(id: Int): Imovel? = imovelDao.getImovelById(id)

    suspend fun insertImovel(imovel: Imovel) = imovelDao.insertImovel(imovel)

    suspend fun updateImovel(imovel: Imovel) = imovelDao.updateImovel(imovel)

    suspend fun deleteImovel(imovel: Imovel) = imovelDao.deleteImovel(imovel)
}