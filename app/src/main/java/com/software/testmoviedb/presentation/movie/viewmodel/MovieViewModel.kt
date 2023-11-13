package com.software.testmoviedb.presentation.movie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.software.testmoviedb.domain.model.Movie
import com.software.testmoviedb.domain.usecase.GetMoviePopulate
import com.software.testmoviedb.domain.usecase.GetMovieTopRade
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val getMoviePopulate: GetMoviePopulate, private val getMovieTopRade: GetMovieTopRade): ViewModel()
{
    sealed class UiModel {
        object Loading : UiModel()
        data class MovieContent(var moviePopulate: List<Movie.Results>, var movieTopRade: List<Movie.Results>) : UiModel()
        data class MovieContentLocal(var moviePopulate: List<Movie.Results>, var movieTopRade: List<Movie.Results>) : UiModel()
    }

    private val _model = MutableLiveData<UiModel>()

    val model: LiveData<UiModel>
        get() {
            return _model
        }

    fun getDefaultUiModel() = viewModelScope.launch {
        _model.value = UiModel.Loading
    }

    fun setUpView() = viewModelScope.launch {
        _model.value = UiModel.MovieContent(
            getMoviePopulate.invoke(page = 1, apiKey = "f1cca3de1acdb7a36b961de832f21eb3", language = "en-US"),
            getMovieTopRade.invoke(page = 2, apiKey = "f1cca3de1acdb7a36b961de832f21eb3", language = "en-US")
        )
    }

    fun getInfoLocal() = viewModelScope.launch {
        _model.value = UiModel.MovieContentLocal(
            getMoviePopulate.invokeLocalDB(),
            getMovieTopRade.invokeLocalDB()
        )
    }
}