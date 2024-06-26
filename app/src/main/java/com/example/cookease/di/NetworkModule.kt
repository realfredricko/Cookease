package com.example.cookease.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    //How to provide an instance of OKHTTP Client
private const val BASE_URL = "https://api.spoonacular.com/"
}