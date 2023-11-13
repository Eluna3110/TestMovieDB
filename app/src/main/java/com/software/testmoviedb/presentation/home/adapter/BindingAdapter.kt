package com.software.testmoviedb.presentation.home.adapter

import androidx.databinding.BindingAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

@BindingAdapter("bind_viewPager")
fun setWithViewPager(tabLayout : TabLayout, pager : ViewPager) {
    tabLayout.setupWithViewPager(pager)
}