package com.example.yumsnap.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.yumsnap.databinding.FragmentRecipeInstructionsBinding
import com.example.yumsnap.model.AppViewModel

class RecipeInstructionsFragment : Fragment() {

    private lateinit var binding: FragmentRecipeInstructionsBinding

    private lateinit var viewModel: AppViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipeInstructionsBinding.inflate(layoutInflater, container, false)

        viewModel = ViewModelProvider(requireActivity())[AppViewModel::class.java]

        viewModel.instructions.observe(this.viewLifecycleOwner) {
            binding.instructions.text = it
        }

        return binding.root
    }

}