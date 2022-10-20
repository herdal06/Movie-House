package com.herdal.retrofitpaging.ui.movie_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.herdal.retrofitpaging.data.remote.model.movie_details.MovieDetails
import com.herdal.retrofitpaging.data.remote.util.ApiConstants
import com.herdal.retrofitpaging.databinding.FragmentMovieDetailsBinding
import com.herdal.retrofitpaging.util.Resource
import com.herdal.retrofitpaging.util.extensions.loadImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {
    private var _binding: FragmentMovieDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: MovieDetailsViewModel by viewModels()
    private val navigationArgs: MovieDetailsFragmentArgs by navArgs()

    fun getMovieId(): Int = navigationArgs.movieId

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectMovieDetails()
        getMovieById()
    }

    private fun getMovieById() {
        viewModel.getMovieById(getMovieId())
    }

    private fun collectMovieDetails() = lifecycleScope.launch {
        viewModel.movie.collect {
            when (it) {
                is Resource.Loading -> {
                    binding.progressBarDetails.visibility = View.VISIBLE
                    binding.tvErrorMessageDetails.visibility = View.GONE
                    binding.layoutMovieDetails.visibility = View.GONE
                }
                is Resource.Success -> {
                    it.data.let { movie ->
                        setupUI(movie)
                    }
                    binding.layoutMovieDetails.visibility = View.VISIBLE
                    binding.progressBarDetails.visibility = View.GONE
                    binding.tvErrorMessageDetails.visibility = View.GONE
                }
                is Resource.Error -> {
                    binding.progressBarDetails.visibility = View.GONE
                    binding.tvErrorMessageDetails.visibility = View.VISIBLE
                    binding.layoutMovieDetails.visibility = View.GONE
                }
            }
        }
    }

    private fun setupUI(movie: MovieDetails) = binding.apply {
        ivBackdrop.loadImage(ApiConstants.getPosterPath(movie.backdrop_path))
        ivPoster.loadImage(ApiConstants.getPosterPath(movie.poster_path))
        tvMovieNameDetails.text = movie.title
        tvVoteAverageDetails.text = movie.vote_average.toString()
        tvReleaseDate.text = movie.release_date
        tvMovieTagline.text = movie.tagline
        tvMovieOverview.text = movie.overview
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}