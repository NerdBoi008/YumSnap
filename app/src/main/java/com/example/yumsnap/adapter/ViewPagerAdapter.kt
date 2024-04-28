package com.example.yumsnap.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.yumsnap.screens.RecipeIngredientFragment
import com.example.yumsnap.screens.RecipeInstructionsFragment

class ViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> RecipeIngredientFragment()
            1 -> RecipeInstructionsFragment()
            else -> RecipeIngredientFragment()
        }
    }
}