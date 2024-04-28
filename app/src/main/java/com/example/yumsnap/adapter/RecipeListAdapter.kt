package com.example.yumsnap.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.yumsnap.R
import com.example.yumsnap.databinding.RecipeItemLayoutBinding
import com.example.yumsnap.model.Meal

class RecipeListAdapter(
    private val dataList: List<Meal>,
    private val onClick: (Int) -> Unit
): RecyclerView.Adapter<RecipeListAdapter.RecipeLayoutViewHolder>() {

    inner class RecipeLayoutViewHolder(private val binding: RecipeItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(meal: Meal) {
            binding.recipeImg.load(meal.thumbnail) {
                crossfade(true)
                placeholder(R.drawable.recipe_placeholder)
            }
            binding.recipeName.text = meal.meal
            binding.recipeCard.setOnClickListener {
                onClick(meal.id.toInt())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeLayoutViewHolder {
        return RecipeLayoutViewHolder(
            RecipeItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: RecipeLayoutViewHolder, position: Int) {
        val meal = dataList[position]
        holder.bind(meal)
    }
}