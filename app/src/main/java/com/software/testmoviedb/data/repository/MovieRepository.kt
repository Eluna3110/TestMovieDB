package com.software.testmoviedb.data.repository

import com.software.testmoviedb.data.database.db.MovieDB
import com.software.testmoviedb.data.database.entities.MoviePopular
import com.software.testmoviedb.data.database.entities.MovieTopRated
import com.software.testmoviedb.data.server.ApiService
import com.software.testmoviedb.domain.model.Movie
import com.software.testmoviedb.domain.repository.IMovieRepository
import javax.inject.Inject

class MovieRepository @Inject constructor(private val apiService: ApiService, private val db: MovieDB) : IMovieRepository
{
    override suspend fun getMoviePopulate(page: Int, apiKey: String, language: String): List<Movie.Results> {
        return try {
            val response = apiService.getListMoviePopular(page = page, api_key = apiKey, language = language)
            response.results?.let {
                addMoviePopular(response.results!!)
                return it
            } ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getMovieTopRated(page: Int, apiKey: String, language: String): List<Movie.Results> {
        return try {
            val response = apiService.getListMovieTopRated(page = page, api_key = apiKey, language = language)
            response.results?.let {
                addTopRade(response.results!!)
                return it
            } ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getMoviePopulateLocalDB(): List<Movie.Results> {
        val listMoviePopular = ArrayList<Movie.Results>()
        val movies = db.getMovieDao().getMoviesPopular()
        movies.forEach {m ->
            val movieNew = Movie.Results()
            movieNew.id = m.id.toInt()
            movieNew.title = m.title
            movieNew.poster_path = m.posterPath
            listMoviePopular.add(movieNew)
        }
        return listMoviePopular
    }

    override suspend fun getMovieTopRatedLocalDB(): List<Movie.Results> {
        val listMovieTopRated = ArrayList<Movie.Results>()
        val movies = db.getMovieDao().getMoviesRated()
        movies.forEach {m ->
            val movieNew = Movie.Results()
            movieNew.id = m.id.toInt()
            movieNew.title = m.title
            movieNew.poster_path = m.posterPath
            listMovieTopRated.add(movieNew)
        }
        return listMovieTopRated
    }

    private suspend fun addMoviePopular(list : List<Movie.Results>){
        val listEntity = arrayListOf<MoviePopular>()
        list.forEach { movie ->
            val entity = MoviePopular(
                movie.id.toString(),
                movie.title!!,
                movie.release_date!!,
                movie.poster_path!!
            )
            listEntity.add(entity)
        }
        db.getMovieDao().addMoviePopular(listEntity)
    }

    private suspend fun addTopRade(list : List<Movie.Results>){
        val listEntity = arrayListOf<MovieTopRated>()
        list.forEach { movie ->
            val entity = MovieTopRated(
                movie.id.toString(),
                movie.title!!,
                movie.release_date!!,
                movie.poster_path!!
            )
            listEntity.add(entity)
        }
        db.getMovieDao().addMovieRated(listEntity)
    }
}