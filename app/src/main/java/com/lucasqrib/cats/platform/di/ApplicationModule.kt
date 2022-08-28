package com.lucasqrib.cats.platform.di

import com.lucasqrib.cats.BuildConfig
import com.lucasqrib.cats.data.datasource.CatDatasource
import com.lucasqrib.cats.data.repository.CatsRepositoryImpl
import com.lucasqrib.cats.domain.repository.CatsRepository
import com.lucasqrib.cats.platform.network.CatApiDatasource
import com.lucasqrib.cats.platform.network.CatsHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(CatsHttpClient.createClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    @Singleton
    fun providesCatApi(retrofit: Retrofit): CatDatasource {
        return retrofit.create(CatApiDatasource::class.java)
    }

    @Provides
    @Singleton
    fun providesCatsRepository(catsRepository: CatsRepositoryImpl): CatsRepository = catsRepository

    @Provides
    @Singleton
    fun providesCoroutineContext(): CoroutineDispatcher = Dispatchers.IO


}

