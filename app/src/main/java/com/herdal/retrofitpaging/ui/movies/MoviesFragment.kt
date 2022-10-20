package com.herdal.retrofitpaging.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import com.herdal.retrofitpaging.data.remote.model.movie_list.Movie
import com.herdal.retrofitpaging.databinding.FragmentMoviesBinding
import com.herdal.retrofitpaging.ui.movies.adapter.MoviesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel: MoviesViewModel by viewModels()
    private val popularMoviesAdapter: MoviesAdapter by lazy {
        MoviesAdapter(::onClickMovie)
    }
    private val topRatedMoviesAdapter: MoviesAdapter by lazy {
        MoviesAdapter(::onClickMovie)
    }
    private val upcomingMoviesAdapter: MoviesAdapter by lazy {
        MoviesAdapter(::onClickMovie)
    }
    private val nowPlayingMoviesAdapter: MoviesAdapter by lazy {
        MoviesAdapter(::onClickMovie)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindUI()
        collectLatestData()
    }

    private fun bindUI() = binding.apply {
        setupRecyclerView(rvPopularMovies, popularMoviesAdapter)
        setupRecyclerView(rvNowPlayingMovies, nowPlayingMoviesAdapter)
        setupRecyclerView(rvTopRatedMovies, topRatedMoviesAdapter)
        setupRecyclerView(rvUpcomingMovies, upcomingMoviesAdapter)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView, moviesAdapter: MoviesAdapter) {
        recyclerView.adapter = moviesAdapter
    }

    private fun collectLatestData() = lifecycleScope.launch {
        collectPagingData(viewModel.popularMovies, popularMoviesAdapter)
        collectPagingData(viewModel.topRatedMovies, topRatedMoviesAdapter)
        collectPagingData(viewModel.nowPlayingMovies, nowPlayingMoviesAdapter)
        collectPagingData(viewModel.upcomingMovies, upcomingMoviesAdapter)
    }

    private fun collectPagingData(data: Flow<PagingData<Movie>>, adapter: MoviesAdapter) =
        lifecycleScope.launch {
            data.collectLatest { pagedData ->
                adapter.submitData(pagedData)
            }
        }

    private fun onClickMovie(movieId: Int) {
        val action = MoviesFragmentDirections.actionMoviesFragmentToMovieDetailsFragment(movieId)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}