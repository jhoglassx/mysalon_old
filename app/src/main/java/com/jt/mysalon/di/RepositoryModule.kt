package com.jt.mysalon.di

import com.jt.mysalon.data.repository.AuthRepository
import com.jt.mysalon.data.repository.AuthRepositoryImpl
import com.jt.mysalon.data.repository.EstablishmentRepository
import com.jt.mysalon.data.repository.EstablishmentRepositoryImpl
import com.jt.mysalon.data.repository.ProfessionalRepository
import com.jt.mysalon.data.repository.ProfessionalRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindEstablishmentRepository(repository: EstablishmentRepositoryImpl): EstablishmentRepository

    @Binds
    fun bindProfessionalRepository(repository: ProfessionalRepositoryImpl): ProfessionalRepository

    @Binds
    fun bindAuthRepository(repository: AuthRepositoryImpl): AuthRepository
}
