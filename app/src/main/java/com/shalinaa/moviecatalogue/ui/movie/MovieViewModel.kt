package com.shalinaa.moviecatalogue.ui.movie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shalinaa.moviecatalogue.BuildConfig
import com.shalinaa.moviecatalogue.model.movie.*
import com.shalinaa.moviecatalogue.network.RetrofitConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel() {

    private val data = MutableLiveData<List<MovieItemResponse>>()

    fun init(page: Int){
        getPopularMovie(page)
    }

    private fun getPopularMovie(page: Int){
        RetrofitConfig().getApiService().getPopular(BuildConfig.API_KEY, page)
            .enqueue(object : Callback<PopularResponse>{
                override fun onFailure(call: Call<PopularResponse>, t: Throwable) {
                    Log.e("failure", t.toString())
                }

                override fun onResponse(
                        call: Call<PopularResponse>,
                        response: Response<PopularResponse>
                ) {
                    if (response.isSuccessful){
                        val responseMovie : PopularResponse?= response.body()
                        data.postValue(responseMovie?.result)
                    }
                }

            })
    }

    //get all atribute dr movie popular
    fun getData(): LiveData<List<MovieItemResponse>>{
        return data
    }


//        ================= upcoming movie ====================


    //call page
    fun initup(page: Int){
        getUpcoming(page)
    }

    private val dataUpcoming = MutableLiveData<List<UpcomingResponse>>()
    private fun getUpcoming(page: Int) {
        RetrofitConfig().getApiService().getUpcoming(BuildConfig.API_KEY, page)
            .enqueue(object : Callback<MovieUpcomingItemResponse>{
                override fun onFailure(call: Call<MovieUpcomingItemResponse>, t: Throwable) {
                    Log.e("failure",t.toString())
                }

                override fun onResponse(call: Call<MovieUpcomingItemResponse>, response: Response<MovieUpcomingItemResponse>) {
                   val upcomingResponse: MovieUpcomingItemResponse? = response.body()
                    dataUpcoming.postValue(upcomingResponse?.result)
                }
            })
    }
    fun getDataUpcoming():LiveData<List<UpcomingResponse>>{
        return dataUpcoming
    }

}