package com.xionlab.exercise.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.xionlab.exercise.model.Quote
import com.xionlab.exercise.repository.ApiClient
import com.xionlab.exercise.repository.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel : ViewModel() {

    val quoteList = MutableLiveData<List<Quote>>()
    private val compositeDisposable = CompositeDisposable()

    init {
        loadQuotes()
    }

    fun loadQuotes(){
            val disposable = ApiClient().requestQuotesList(0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({
                    quoteList.postValue(it)
                },{
                    val trowable = it.localizedMessage
                })
        compositeDisposable.addAll(disposable)
    }
}