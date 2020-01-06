package com.magang.kelilingapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.magang.kelilingapp.R
import com.magang.kelilingapp.databinding.MostVisitedListBinding
import com.magang.kelilingapp.model.Datum

class MostVisitedAdapter(private val list:List<Datum> = listOf(), var context: Context?) :
    RecyclerView.Adapter<MostVisitedAdapter.MostVisitedVH>() {
    private lateinit var binding: MostVisitedListBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MostVisitedVH {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.most_visited_list, parent, false)
        return MostVisitedVH(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MostVisitedVH, position: Int) {
        holder.bindItem(list[position])
    }

    class MostVisitedVH (private val binding: MostVisitedListBinding) : RecyclerView.ViewHolder(binding.root){
        fun bindItem(datum: Datum){
            binding.data = datum
        }
    }
}