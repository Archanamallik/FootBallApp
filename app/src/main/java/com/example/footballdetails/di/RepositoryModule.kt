package com.codingwithmitch.mvvmrecipeapp.di

import com.example.footballdetails.network.ContinentsApi
import com.example.footballdetails.network.CountriesApi
import com.example.footballdetails.network.ScheduleApi
import com.example.footballdetails.network.LeagueApi
import com.example.footballdetails.network.PlayerApi
import com.example.footballdetails.network.TeamsApi
import com.example.footballdetails.utils.BASE_URL

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun providesContinentService(retrofit: Retrofit): ContinentsApi =
        retrofit.create(ContinentsApi::class.java)
    @Singleton
    @Provides
    fun providesCountryService(retrofit: Retrofit): CountriesApi =
        retrofit.create(CountriesApi::class.java)


    @Singleton
    @Provides
    fun providesLeagueService(retrofit: Retrofit): LeagueApi =
        retrofit.create(LeagueApi::class.java)
    @Singleton
    @Provides
    fun providesTeamService(retrofit: Retrofit): TeamsApi =
        retrofit.create(TeamsApi::class.java)

    @Singleton
    @Provides
    fun providesPlayerService(retrofit: Retrofit): PlayerApi =
        retrofit.create(PlayerApi::class.java)
    @Singleton
    @Provides
    fun providesFixtureService(retrofit: Retrofit): ScheduleApi =
        retrofit.create(ScheduleApi::class.java)
}

