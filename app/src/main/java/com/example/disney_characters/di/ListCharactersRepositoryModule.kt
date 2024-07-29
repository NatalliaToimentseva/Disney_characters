package com.example.disney_characters.di

import com.example.disney_characters.dataSources.repository.DisneyCharactersListRetrofitRepository
import com.example.disney_characters.ui.repository.DisneyCharactersListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ListCharactersRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindDisneyListRepository(
        charactersListRepository: DisneyCharactersListRetrofitRepository
    ): DisneyCharactersListRepository
}