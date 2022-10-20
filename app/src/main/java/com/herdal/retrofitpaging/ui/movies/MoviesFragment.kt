package com.herdal.retrofitpaging.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.herdal.retrofitpaging.databinding.FragmentMoviesBinding
import com.herdal.retrofitpaging.ui.movies.adapter.MoviesAdapter
import dagger.hilt.android.AndroidEntryPoint
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
        MoviesAdapter()
    }
    private val topRatedMoviesAdapter: MoviesAdapter by lazy {
        MoviesAdapter()
    }
    private val upcomingMoviesAdapter: MoviesAdapter by lazy {
        MoviesAdapter()
    }
    private val nowPlayingMoviesAdapter: MoviesAdapter by lazy {
        MoviesAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerViews()
        collectLatestData()
    }


    private fun setupRecyclerViews() = binding.apply {
        setupRecyclerView(rvPopularMovies, popularMoviesAdapter)
        setupRecyclerView(rvNowPlayingMovies, nowPlayingMoviesAdapter)
        setupRecyclerView(rvTopRatedMovies, topRatedMoviesAdapter)
        setupRecyclerView(rvUpcomingMovies, upcomingMoviesAdapter)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView, moviesAdapter: MoviesAdapter) {
        recyclerView.adapter = moviesAdapter
    }

    private fun collectLatestData() = lifecycleScope.launch {
        collectPopularMovies()
        collectTopRatedMovies()
        collectUpcomingMovies()
        collectNowPlayingMovies()
    }

    private fun collectNowPlayingMovies() = lifecycleScope.launch {
        viewModel.nowPlayingMovies.collectLatest { pagedData ->
            nowPlayingMoviesAdapter.submitData(pagedData)
        }
    }

    private fun collectTopRatedMovies() = lifecycleScope.launch {
        viewModel.topRatedMovies.collectLatest { pagedData ->
            topRatedMoviesAdapter.submitData(pagedData)
        }
    }

    private fun collectUpcomingMovies() = lifecycleScope.launch {
        viewModel.upcomingMovies.collectLatest { pagedData ->
            upcomingMoviesAdapter.submitData(pagedData)
        }
    }

    private fun collectPopularMovies() = lifecycleScope.launch {
        viewModel.popularMovies.collectLatest { pagedData ->
            popularMoviesAdapter.submitData(pagedData)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}