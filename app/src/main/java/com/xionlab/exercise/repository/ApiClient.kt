package com.xionlab.exercise.repository

import com.xionlab.exercise.BuildConfig
import com.xionlab.exercise.model.Cat
import com.xionlab.exercise.model.Dog
import com.xionlab.exercise.model.Quote
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    private val quoteService : ApiService
    private val catService : ApiService
    private val dogService : ApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.base_url)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        val catRetrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.cat_url)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        val dogRetrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.dog_url)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        dogService = dogRetrofit.create(ApiService::class.java)
        catService = catRetrofit.create(ApiService::class.java)
        quoteService = retrofit.create(ApiService::class.java)
    }

    fun requestQuotesList(page : Int) : Observable<List<Quote>> {
        return quoteService.getQuotesByPage()
    }

    fun requestCat() : Observable<Cat>{
        return catService.getCat()
    }

    fun requestDog() : Observable<Dog>{
        return dogService.getDog()
    }
}