package com.software.testmoviedb.data.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.software.testmoviedb.data.database.entities.MoviePopular
import com.software.testmoviedb.data.database.entities.MovieTopRated

@Database(entities = [MoviePopular::class, MovieTopRated::class], version = 1)
abstract class MovieDB : RoomDatabase()
{
    abstract fun getMovieDao() : MovieDAO
}