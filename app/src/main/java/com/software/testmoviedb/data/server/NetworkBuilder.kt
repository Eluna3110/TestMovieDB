package com.software.testmoviedb.data.server

import com.software.testmoviedb.data.database.db.MovieDB
import com.software.testmoviedb.data.repository.MovieRepository
import com.software.testmoviedb.domain.repository.IMovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkBuilder
{
    @Singleton
    @Provides
    fun providesRetrofit() : Retrofit
    {
        val interceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        val clientBuilder = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)

        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(clientBuilder.build())
            .build()
    }

    @Singleton
    @Provides
    fun providesAPI(retrofit: Retrofit) : ApiService{
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun providesMovieRepository(apiService: ApiService, db : MovieDB) : IMovieRepository{
        return MovieRepository(apiService, db)
    }
}