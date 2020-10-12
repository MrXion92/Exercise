package com.xionlab.exercise.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xionlab.exercise.model.Animals

class DetailViewModelFactory(private val animals: Animals) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>)  = DetailViewModel(animals) as T

}