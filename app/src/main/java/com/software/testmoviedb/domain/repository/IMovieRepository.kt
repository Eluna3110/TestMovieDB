package com.software.testmoviedb.domain.repository

import com.software.testmoviedb.domain.model.Movie

interface IMovieRepository
{
    suspend fun getMoviePopulate(page: Int, apiKey: String, language: String) : List<Movie.Results>
    suspend fun getMovieTopRated(page: Int, apiKey: String, language: String) : List<Movie.Results>

    suspend fun getMoviePopulateLocalDB() : List<Movie.Results>
    suspend fun getMovieTopRatedLocalDB() : List<Movie.Results>
}