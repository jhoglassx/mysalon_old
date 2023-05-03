package com.jt.mysalon.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

//    @Binds
//    fun bindGetCharactersUseCase(useCase: GetCharactersUseCaseImpl): GetCharactersUseCase
//
//    @Binds
//    fun bindGetComicsUseCase(
//        useCase: GetCharacterCategoriesUseCaseImpl
//    ): GetCharacterCategoriesUseCase
}