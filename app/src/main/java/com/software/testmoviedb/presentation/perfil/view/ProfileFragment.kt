package com.software.testmoviedb.presentation.perfil.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.software.testmoviedb.databinding.PerfilFragmentBinding
import com.software.testmoviedb.presentation.perfil.viewmodel.ProfileViewModel
import androidx.lifecycle.Observer

class ProfileFragment : Fragment()
{
    private lateinit var binding: PerfilFragmentBinding
    private lateinit var adapter : ProfileAdapterRating
    private val viewModel: ProfileViewModel  by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PerfilFragmentBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = this
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.model.observe(viewLifecycleOwner, Observer(::updateUI))
        viewModel.setUpView()
    }

    private fun updateUI(model: ProfileViewModel.UiModel) {

        when (model) {
            is ProfileViewModel.UiModel.ProfileContent -> {
                adapter = ProfileAdapterRating(model.profileInfo)
                binding.rvProfile.adapter = adapter
            }else -> {}
        }
    }
}