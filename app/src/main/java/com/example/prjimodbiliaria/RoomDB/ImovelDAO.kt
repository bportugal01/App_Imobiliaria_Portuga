package com.example.prjimodbiliaria.RoomDB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ImovelDao {

    @Query("SELECT * FROM imovel")
    fun getAllImoveis(): Flow<List<Imovel>>

    @Query("SELECT * FROM imovel WHERE id = :id")
    suspend fun getImovelById(id: Int): Imovel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImovel(imovel: Imovel)

    @Update
    suspend fun updateImovel(imovel: Imovel)

    @Delete
    suspend fun deleteImovel(imovel: Imovel)
}