package com.magang.kelilingapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.magang.kelilingapp.R
import com.magang.kelilingapp.databinding.WisataListBinding
import com.magang.kelilingapp.model.Datum
import com.magang.kelilingapp.util.Params

class WisataListAdapter(private var list:List<Datum> = listOf(), var context: Context?) :
    RecyclerView.Adapter<WisataListAdapter.WisataVH>() {
    private lateinit var binding: WisataListBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WisataVH {
         binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.wisata_list, parent, false)
        return WisataVH(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: WisataVH, position: Int) {
        holder.bindItem(list[position])
        holder.itemView.setOnClickListener {
            val bundle = bundleOf(Params.DashboardToDetail to list[position])
            it.findNavController().navigate(R.id.action_dashboardList_to_detailPage, bundle)
        }

    }


    inner class WisataVH(private val binding: WisataListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(wisata : Datum){
           binding.datum = wisata
        }
    }
}