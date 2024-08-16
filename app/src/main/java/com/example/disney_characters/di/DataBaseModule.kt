package com.example.disney_characters.di

import android.content.Context
import androidx.room.Room
import com.example.disney_characters.dataSources.roomDB.AppDataBase
import com.example.disney_characters.dataSources.roomDB.CharactersDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val DATA_BASE_NAME = "app_data_base"

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideRoomDB(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(context, AppDataBase::class.java, DATA_BASE_NAME)
            .build()
    }

    @Provides
    @Singleton
    fun provideCharactersDao(appDataBase: AppDataBase): CharactersDao {
        return appDataBase.getCharactersDao()
    }
}