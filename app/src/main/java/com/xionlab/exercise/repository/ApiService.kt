package com.xionlab.exercise.repository

import com.xionlab.exercise.model.Cat
import com.xionlab.exercise.model.Dog
import com.xionlab.exercise.model.Quote
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("quotes/")
    fun getQuotesByPage() : Observable<List<Quote>>

    @GET("meow")
    fun getCat() : Observable<Cat>

    @GET("woof.json")
    fun getDog() : Observable<Dog>

}