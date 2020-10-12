package com.xionlab.exercise.ui.detail

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.xionlab.exercise.R
import com.xionlab.exercise.model.Animals
import kotlinx.android.synthetic.main.detail_fragment.*

class DetailFragment : Fragment() {

    private val viewModel : DetailViewModel by viewModels(){ DetailViewModelFactory(
        (context?.getSharedPreferences(getString(R.string.shared_pref_key),Context.MODE_PRIVATE)?.getString("animals",Animals.CAT.value) ?: Animals.CAT.value).let {
            Animals.valueOf(it.toUpperCase())
        }
    )
    }
    private val args : DetailFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.animalString.observe(viewLifecycleOwner, Observer {
            Glide.with(animalImage.context).load(it).into(animalImage)
        })
        quoteDetail.text = args.quote
    }

}