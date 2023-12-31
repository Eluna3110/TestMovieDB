package com.software.testmoviedb.presentation.maps.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.software.testmoviedb.databinding.MapsFragmentBinding
import com.software.testmoviedb.presentation.maps.viewmodel.MapsViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.lifecycle.Observer
import com.google.android.gms.maps.OnMapReadyCallback
import com.software.testmoviedb.R
import com.software.testmoviedb.common.Constants
import com.software.testmoviedb.domain.model.LocationInfo

@AndroidEntryPoint
class MapsFragment : Fragment(), OnMapReadyCallback
{
    private lateinit var binding: MapsFragmentBinding
    private val fireStoreDatabase = FirebaseFirestore.getInstance()
    private val viewModel: MapsViewModel by viewModels()

    private lateinit var mMap : GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MapsFragmentBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = this
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        readDataFromFireStore()
        createFragment()
        viewModel.locationInfo.observe(viewLifecycleOwner, Observer {
            createMarker()
        })
    }

    private fun createFragment(){
        val mapFragment : SupportMapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
    }

    private fun readDataFromFireStore(){
        val docRef = fireStoreDatabase.collection(Constants.LOCATION_INFO).document(Constants.LOCATION_LIST)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    document.toObject(LocationInfo::class.java)
                        ?.let { viewModel.setLocationInfo(it) }
                }
            }
            .addOnFailureListener { exception ->
                Log.d(Constants.TAG_FIRESTORE, getString(R.string.error_read_firestore), exception)
            }
    }

    private fun createMarker(){
        for (mk in viewModel.locationInfo.value!!.location){
            val coordinate = LatLng(mk.latitude, mk.length)
            val marker : MarkerOptions = MarkerOptions().position(coordinate).title(mk.label)
            mMap.addMarker(marker)
        }

        val coordinate = LatLng(viewModel.locationInfo.value!!.location[1].latitude,
            viewModel.locationInfo.value!!.location[1].length)
        mMap.animateCamera(
            CameraUpdateFactory.newLatLngZoom(coordinate, 4f),
            4000,
            null
        )
    }
}