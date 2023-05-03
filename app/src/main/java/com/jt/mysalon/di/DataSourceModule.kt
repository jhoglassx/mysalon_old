package com.jt.mysalon.di

import com.jt.mysalon.data.remote.datasource.AuthDataSource
import com.jt.mysalon.data.remote.datasource.AuthDataSourceImpl
import com.jt.mysalon.data.remote.datasource.EstablishmentDataSource
import com.jt.mysalon.data.remote.datasource.EstablishmentDataSourceImpl
import com.jt.mysalon.data.remote.datasource.ProfessionalDataSource
import com.jt.mysalon.data.remote.datasource.ProfessionalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun bindEstablishmentDataSource(datasource: EstablishmentDataSourceImpl): EstablishmentDataSource

    @Binds
    fun bindProfessionalDataSource(datasource: ProfessionalDataSourceImpl): ProfessionalDataSource

    @Binds
    fun bindAuthDataSource(datasource: AuthDataSourceImpl): AuthDataSource
}
