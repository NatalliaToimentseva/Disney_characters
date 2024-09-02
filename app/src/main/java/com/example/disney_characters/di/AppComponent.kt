package com.example.disney_characters.di

import android.content.Context
import com.example.disney_characters.ui.details.DetailsFragment
import com.example.disney_characters.ui.home.HomeFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataBaseModule::class, RetrofitModule::class, RepositoryModule::class])
interface AppComponent {

    fun inject(fragment: HomeFragment)
    fun inject(fragment: DetailsFragment)

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun provideContext(context: Context): Builder
    }
}