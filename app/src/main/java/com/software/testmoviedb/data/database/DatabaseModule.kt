package com.software.testmoviedb.data.database

import android.content.Context
import androidx.room.Room
import com.software.testmoviedb.data.database.db.MovieDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
        fun provideMovieDB(@ApplicationContext context : Context) : MovieDB{
        return Room.databaseBuilder(context, MovieDB::class.java, "MovieDB")
            .build()
    }
}
