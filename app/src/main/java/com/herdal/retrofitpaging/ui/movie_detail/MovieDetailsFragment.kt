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
import com.herdal.retrofitpaging.util.extensions.gone
import com.herdal.retrofitpaging.util.extensions.loadImage
import com.herdal.retrofitpaging.util.extensions.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.DecimalFormat

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {
    private var _binding: FragmentMovieDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: MovieDetailsViewModel by viewModels()
    private val navigationArgs: MovieDetailsFragmentArgs by navArgs()

    private fun getMovieId(): Int = navigationArgs.movieId

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
                    binding.progressBarDetails.show()
                    binding.tvErrorMessageDetails.gone()
                    binding.layoutMovieDetails.gone()
                }
                is Resource.Success -> {
                    setupUI(it.data)
                    binding.layoutMovieDetails.show()
                    binding.progressBarDetails.gone()
                    binding.tvErrorMessageDetails.gone()
                }
                is Resource.Error -> {
                    binding.progressBarDetails.gone()
                    binding.tvErrorMessageDetails.show()
                    binding.layoutMovieDetails.gone()
                }
            }
        }
    }

    private fun setupUI(movie: MovieDetails) = binding.apply {
        ivBackdrop.loadImage(ApiConstants.getPosterPath(movie.backdrop_path))
        ivPoster.loadImage(ApiConstants.getPosterPath(movie.poster_path))
        tvMovieNameDetails.text = movie.title
        tvVoteAverageDetails.text = formatVoteAverage(movie.vote_average)
        tvReleaseDate.text = movie.release_date
        tvMovieTagline.text = movie.tagline
        tvMovieOverview.text = movie.overview
    }

    private fun formatVoteAverage(formatValue: Double): String {
        val df = DecimalFormat("#.#")
        return df.format(formatValue)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}