package com.software.testmoviedb.data.database.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.software.testmoviedb.data.database.entities.MoviePopular
import com.software.testmoviedb.data.database.entities.MovieTopRated

@Dao
interface MovieDAO
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMoviePopular(products : List<MoviePopular>)

    @Query("SELECT * FROM movie_popular_table")
    suspend fun getMoviesPopular() : List<MoviePopular>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovieRated(products : List<MovieTopRated>)

    @Query("SELECT * FROM movie_popular_table")
    suspend fun getMoviesRated() : List<MovieTopRated>
}