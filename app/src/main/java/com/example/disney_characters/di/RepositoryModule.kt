package com.example.disney_characters.di

import com.example.disney_characters.repository.CharactersDbRepository
import com.example.disney_characters.repository.repositoryImpl.DisneyCharactersListRetrofitRepository
import com.example.disney_characters.repository.DisneyCharactersListRepository
import com.example.disney_characters.repository.repositoryImpl.CharactersRoomRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindDisneyListRepository(
        charactersListRepository: DisneyCharactersListRetrofitRepository
    ): DisneyCharactersListRepository

    @Binds
    @Singleton
    abstract fun bindCharacterDBRepository(
        characterDbRepository: CharactersRoomRepository
    ): CharactersDbRepository
}