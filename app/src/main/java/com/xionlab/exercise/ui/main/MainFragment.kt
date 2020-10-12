package com.xionlab.exercise.ui.main

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.xionlab.exercise.R
import com.xionlab.exercise.model.Animals
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    private val viewModel : MainViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.quoteList.observe(viewLifecycleOwner, Observer {
            val adapter = RecyclerAdapter(it){
                val actionDirections = MainFragmentDirections.actionMainFragmentToDetailFragment(it.en)
                findNavController().navigate(actionDirections)
            }
            quotesRecyclerView.adapter = adapter
        })

        if(context?.getSharedPreferences(getString(R.string.shared_pref_key), Context.MODE_PRIVATE)?.contains("animals") == false){
            openDialog()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.edit -> openDialog()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun openDialog() {
        AlertDialog.Builder(requireContext())
        .setMessage("Seleziona l'animale da visulizzare")
            .setPositiveButton("Cane") { _, _ ->
                context?.getSharedPreferences(getString(R.string.shared_pref_key), Context.MODE_PRIVATE)?.edit()?.putString("animals",Animals.DOG.value)?.apply()
            }
            .setNegativeButton("Gatto") { _, _ ->
                context?.getSharedPreferences(getString(R.string.shared_pref_key), Context.MODE_PRIVATE)?.edit()?.putString("animals",Animals.CAT.value)?.apply()
            }
            .create().show()
    }
}