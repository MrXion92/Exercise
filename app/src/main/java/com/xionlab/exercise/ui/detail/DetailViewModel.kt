package com.xionlab.exercise.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.xionlab.exercise.model.Animals
import com.xionlab.exercise.repository.ApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailViewModel(val animals: Animals) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val animalString = MutableLiveData<String>()

    init {
        loadAnimal()
    }

    fun loadAnimal(){
        when(animals) {
            Animals.CAT -> loadCat()
            Animals.DOG -> loadDog()
        }
    }

    fun loadCat(){
        val disposable = ApiClient().requestCat().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                animalString.postValue(it.file)
            },{
                val trowable = it.localizedMessage
            })
        compositeDisposable.addAll(disposable)
    }

    fun loadDog(){
        val disposable = ApiClient().requestDog().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                animalString.postValue(it.url)
            },{
                val trowable = it.localizedMessage
            })
        compositeDisposable.addAll(disposable)
    }
}