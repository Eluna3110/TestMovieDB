package com.software.testmoviedb.presentation.maps.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.software.testmoviedb.domain.model.LocationInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor() : ViewModel()
{
    private val _locationInfo = MutableLiveData<LocationInfo>()
    val locationInfo : LiveData<LocationInfo>
        get() = _locationInfo

    fun setLocationInfo(locationInfo: LocationInfo){
        _locationInfo.value = locationInfo
    }
}