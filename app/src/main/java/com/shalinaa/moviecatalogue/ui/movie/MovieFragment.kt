package com.shalinaa.moviecatalogue.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.shalinaa.moviecatalogue.R
import com.shalinaa.moviecatalogue.adapter.PopularMovieAdapter
import com.shalinaa.moviecatalogue.adapter.UpcomingMovieAdapter
import com.shalinaa.moviecatalogue.model.movie.MovieItemResponse

import com.shalinaa.moviecatalogue.model.movie.UpcomingResponse
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : Fragment() {

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var popularMovieAdapter: PopularMovieAdapter
    private lateinit var upcomingMovieAdapter: UpcomingMovieAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_movie, container, false)

        movieViewModel =
                ViewModelProvider(this).get(MovieViewModel::class.java)

        //popular
        movieViewModel.init(1)
        movieViewModel.getData()
                .observe(viewLifecycleOwner, Observer{ popularMovie ->
                    getinitPopular(popularMovie)
        })

        //upcoming
        movieViewModel.initup(1)
        movieViewModel.getDataUpcoming().observe(viewLifecycleOwner, Observer{ movieUpcoming ->
            getinitUpcoming(movieUpcoming)
        })
        return root
    }

    private fun getinitUpcoming(movie: List<UpcomingResponse>) {
        rv_upcoming.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true)
            upcomingMovieAdapter = UpcomingMovieAdapter (movie)
            rv_upcoming.adapter = upcomingMovieAdapter
        }
    }

    private fun getinitPopular(movie: List<MovieItemResponse>) {
        rv_popular.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
            popularMovieAdapter = PopularMovieAdapter (movie)
            rv_popular.adapter = popularMovieAdapter
        }
    }


}