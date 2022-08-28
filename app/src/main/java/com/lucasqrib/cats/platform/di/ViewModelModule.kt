package com.lucasqrib.cats.platform.di

import com.lucasqrib.cats.domain.usecase.RetrieveCatsUseCase
import com.lucasqrib.cats.domain.usecase.RetrieveCatsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {

    @Binds
    abstract fun bindsUseCase(retrieveCatsUseCaseImpl: RetrieveCatsUseCaseImpl): RetrieveCatsUseCase
}