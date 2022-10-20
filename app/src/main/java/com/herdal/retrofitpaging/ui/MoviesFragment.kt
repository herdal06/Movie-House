package com.herdal.retrofitpaging.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.herdal.retrofitpaging.databinding.FragmentMoviesBinding
import com.herdal.retrofitpaging.ui.adapter.MoviesAdapter
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

    private fun setupRecyclerViews()=binding.apply {
        rvPopularMovies.adapter = popularMoviesAdapter
        rvNowPlayingMovies.adapter = nowPlayingMoviesAdapter
        rvTopRatedMovies.adapter = topRatedMoviesAdapter
        rvUpcomingMovies.adapter = upcomingMoviesAdapter
    }

    private fun collectLatestData() = lifecycleScope.launch {
        viewModel.popularMovies.collectLatest { pagedData ->
            popularMoviesAdapter.submitData(pagedData)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}