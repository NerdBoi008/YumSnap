package com.example.yumsnap.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yumsnap.databinding.IngredientItemLayoutBinding

class IngredientsAdapter(
    private val nameList: List<String>,
    private val amountList: List<String>,
) : RecyclerView.Adapter<IngredientsAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: IngredientItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(name: String, amount: String) {
            binding.ingredientName.text = name
            binding.ingredientAmount.text = amount
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            IngredientItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = nameList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(nameList.get(position), amountList.get(position))
    }
}