package com.xionlab.exercise.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.xionlab.exercise.R
import com.xionlab.exercise.model.Quote

typealias ClickListener = (Quote) -> Unit

class RecyclerAdapter(private val quotes : List<Quote>,private val clickListener: ClickListener) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemLayout = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false) as ViewGroup
        val viewHolder = ViewHolder(itemLayout)
        itemLayout.setOnClickListener{
            clickListener(quotes[viewHolder.adapterPosition])
        }
        return viewHolder

    }

    override fun getItemCount() = quotes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val quote = quotes[position]
        holder.textView.text = quote.en
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.item_text)
    }

}