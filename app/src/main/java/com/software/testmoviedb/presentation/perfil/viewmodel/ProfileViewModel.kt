package com.software.testmoviedb.presentation.perfil.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.software.testmoviedb.R
import com.software.testmoviedb.domain.model.Person
import com.software.testmoviedb.domain.model.ProfileMovieRating
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel  @Inject constructor() : ViewModel()
{
    sealed class UiModel {
        data class ProfileContent(var profileInfo: List<ProfileMovieRating>) : UiModel()
    }

    private val _model = MutableLiveData<UiModel>()

    private val _listPerson = MutableLiveData<ArrayList<Person>>()
    val listPerson : LiveData<ArrayList<Person>>
        get() = _listPerson

    val model: LiveData<UiModel>
        get() {
            return _model
        }

    fun setUpView(){
        val listProfile = ArrayList<ProfileMovieRating>()
        listProfile.add(ProfileMovieRating("Me Before You", "Drama y Romance - 1h 50m", 4f, R.drawable.ic_movie_1))
        listProfile.add(ProfileMovieRating("Terminator Genisys", "Science Fiction, Action y Thriller - 2h 6m", 2f, R.drawable.ic_movie_2))
        listProfile.add(ProfileMovieRating("Game of Thrones", "Drama, Action y Adventure - 2h 6m", 5f, R.drawable.ic_movie_3))

        _model.value = UiModel.ProfileContent(
            listProfile.toList()
        )
    }

    fun getPersonRandom() : Person {
        val listPerson = ArrayList<Person>()

        listPerson.add(Person(R.drawable.ic_emilia, "Emilia", "Acting", "1986-10-23", "London"))
        listPerson.add(Person(R.drawable.ic_jason, "Jason", "Acting","1987-12-23", "USA"))
        listPerson.add(Person(R.drawable.ic_jenna, "Jenna", "Acting", "1989-05-14", "USA"))
        listPerson.add(Person(R.drawable.ic_emma, "Emma", "Acting", "1996-09-30", "Argentina"))

        _listPerson.value = listPerson
        return _listPerson.value!![(0 until 4).random()]
    }
}