package com.example.chickenmasala.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chickenmasala.databinding.ItemCuisineBinding
import com.example.chickenmasala.entities.Cuisine


class CuisinesAdapter(private val cuisineListener: CuisineListener) :
    ListAdapter<Cuisine, CuisinesAdapter.CuisineViewHolder>(CuisineDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CuisineViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCuisineBinding.inflate(layoutInflater, parent, false)
        return CuisineViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CuisineViewHolder, position: Int) {
        val cuisine = getItem(position)
        holder.bind(cuisine, cuisineListener)
    }

    inner class CuisineViewHolder(private val binding: ItemCuisineBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cuisine: Cuisine, cuisineListener: CuisineListener) {
            binding.cardCuisine.setOnClickListener { cuisineListener.onClick(cuisine) }
            binding.textCuisineName.text = cuisine.name
            binding.textCuisinesCount.text = cuisine.recipes.size.toString()
            Glide.with(binding.imageCuisine.context).load(cuisine.recipes.random().imageUrl)
                .into(binding.imageCuisine)
        }
    }

    class CuisineDiffCallback : DiffUtil.ItemCallback<Cuisine>() {
        override fun areItemsTheSame(oldItem: Cuisine, newItem: Cuisine): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Cuisine, newItem: Cuisine): Boolean {
            return oldItem == newItem
        }
    }

    class CuisineListener(private val onClickListener: (Cuisine) -> Unit) {
        fun onClick(cuisine: Cuisine) = onClickListener(cuisine)
    }
}
