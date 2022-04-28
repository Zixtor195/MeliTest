package com.example.melitest.core.di

import com.example.melitest.data.datasource.DataSourceMercadoLibreImpl
import com.example.melitest.data.datasource.interfaces.DataSourceMercadoLibre
import com.example.melitest.data.repositories.RepositoryMercadoLibreImpl
import com.example.melitest.data.repositories.interfaces.RepositoryMercadoLibre
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    @Singleton
    abstract fun bindRepositoryMercadoLibre(
        repositoryMercadoLibreImpl: RepositoryMercadoLibreImpl,
    ): RepositoryMercadoLibre

    @Binds
    @Singleton
    abstract fun bindDataSourceMercadoLibre(
        dataSourceMercadoLibreImpl: DataSourceMercadoLibreImpl,
    ): DataSourceMercadoLibre
}