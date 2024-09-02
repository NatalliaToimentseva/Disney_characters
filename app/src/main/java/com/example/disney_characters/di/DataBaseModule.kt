package com.example.disney_characters.di

import android.content.Context
import androidx.room.Room
import com.example.disney_characters.dataSources.roomDB.AppDataBase
import com.example.disney_characters.dataSources.roomDB.CharactersDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

private const val DATA_BASE_NAME = "app_data_base"

@Module
object DataBaseModule {

    @Provides
    @Singleton
    fun provideRoomDB(context: Context): AppDataBase {
        return Room.databaseBuilder(context, AppDataBase::class.java, DATA_BASE_NAME)
            .build()
    }

    @Provides
    @Singleton
    fun provideCharactersDao(appDataBase: AppDataBase): CharactersDao {
        return appDataBase.getCharactersDao()
    }
}