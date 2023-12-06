package com.software.testmoviedb.presentation.movie.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.software.testmoviedb.databinding.MovieFragmentBinding
import com.software.testmoviedb.presentation.movie.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : Fragment()
{
    private lateinit var binding: MovieFragmentBinding
    private val viewModel: MovieViewModel by viewModels()
    private lateinit var moviePopularAdapter: MovieAdapter
    private lateinit var movieTopRadeAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MovieFragmentBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = this
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moviePopularAdapter = MovieAdapter(requireContext())
        binding.rvPopular.adapter = moviePopularAdapter

        movieTopRadeAdapter = MovieAdapter(requireContext())
        binding.rvRated.adapter = movieTopRadeAdapter

        viewModel.getDefaultUiModel()
        viewModel.model.observe(viewLifecycleOwner, Observer(::updateUI))
        viewModel.setUpView()
    }

    private fun updateUI(model: MovieViewModel.UiModel) {
        binding.pbLoading.visibility = View.GONE

        when (model) {
            is MovieViewModel.UiModel.Loading -> {
                binding.pbLoading.visibility = View.VISIBLE
            }
            is MovieViewModel.UiModel.MovieContent -> {
                if (model.moviePopulate.isNotEmpty()) {
                    moviePopularAdapter.submitList(model.moviePopulate)
                    movieTopRadeAdapter.submitList(model.movieTopRade)
                }else {
                    viewModel.getInfoLocal()
                }
            }
            is MovieViewModel.UiModel.MovieContentLocal -> {
                if (model.moviePopulate.isNotEmpty()) {
                    moviePopularAdapter.submitList(model.moviePopulate)
                    movieTopRadeAdapter.submitList(model.movieTopRade)
                }
            }
        }
    }

}