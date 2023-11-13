package com.software.testmoviedb.domain.usecase

import com.software.testmoviedb.domain.repository.IMovieRepository
import javax.inject.Inject

class GetMoviePopulate @Inject constructor(private val movieRepository: IMovieRepository) {
    suspend operator fun invoke(page: Int, apiKey: String, language: String) = movieRepository.getMoviePopulate(page, apiKey, language)
    suspend fun invokeLocalDB() = movieRepository.getMoviePopulateLocalDB()
}