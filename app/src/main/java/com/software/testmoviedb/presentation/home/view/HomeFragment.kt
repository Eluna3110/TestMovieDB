package com.software.testmoviedb.presentation.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.software.testmoviedb.R
import com.software.testmoviedb.databinding.HomeFragmentBinding
import com.software.testmoviedb.presentation.home.adapter.TabPagerAdapter
import com.software.testmoviedb.presentation.maps.view.MapsFragment
import com.software.testmoviedb.presentation.movie.view.MovieFragment
import com.software.testmoviedb.presentation.perfil.view.ProfileFragment
import com.software.testmoviedb.presentation.storage.view.StorageFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment()
{
    private lateinit var binding: HomeFragmentBinding
    private lateinit var tabPagerAdapter : TabPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = this
        }
        return binding.root
    }

    override fun onViewCreated(view : View, savedInstanceState : Bundle?)
    {
        val listFragments = ArrayList<Fragment>()
        listFragments.add(ProfileFragment())
        listFragments.add(MovieFragment())
        listFragments.add(MapsFragment())
        listFragments.add(StorageFragment())

        val lisTitles = ArrayList<String>()
        lisTitles.add(getString(R.string.tab_profile))
        lisTitles.add(getString(R.string.tab_movie))
        lisTitles.add(getString(R.string.tab_maps))
        lisTitles.add(getString(R.string.tab_storage))

        tabPagerAdapter = TabPagerAdapter(fragmentManager = childFragmentManager, fragments = listFragments, tabTitles =  lisTitles)
        binding.pagerAdapter = tabPagerAdapter
    }
}