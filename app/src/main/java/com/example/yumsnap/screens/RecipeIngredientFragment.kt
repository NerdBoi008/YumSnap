package com.example.yumsnap.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yumsnap.adapter.IngredientsAdapter
import com.example.yumsnap.databinding.FragmentRecipeIngredientBinding
import com.example.yumsnap.model.AppViewModel

class RecipeIngredientFragment: Fragment() {

    private lateinit var binding: FragmentRecipeIngredientBinding

    private lateinit var viewModel: AppViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipeIngredientBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(requireActivity())[AppViewModel::class.java]

        binding.rvIngredients.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        viewModel.ingredientsList.observe(this.viewLifecycleOwner) { ingredientsPair ->
            binding.rvIngredients.adapter = IngredientsAdapter(ingredientsPair.first, ingredientsPair.second)
        }

        return binding.root
    }

}