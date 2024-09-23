package com.example.prjimodbiliaria.RoomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Imovel::class], version = 1, exportSchema = false)
abstract class ImovelDataBase : RoomDatabase() {

    abstract fun imovelDao(): ImovelDao

    companion object {
        @Volatile
        private var INSTANCE: ImovelDataBase? = null

        fun getDatabase(context: Context): ImovelDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ImovelDataBase::class.java,
                    "imovel_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}