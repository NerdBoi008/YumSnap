package com.example.yumsnap.screens

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yumsnap.R
import com.example.yumsnap.adapter.RecipeListAdapter
import com.example.yumsnap.databinding.ActivityHomeScreenBinding
import com.example.yumsnap.model.AppViewModel
import com.google.android.material.chip.Chip
import kotlinx.coroutines.launch

class HomeScreen : AppCompatActivity() {

    private lateinit var binding: ActivityHomeScreenBinding

    private val viewModel: AppViewModel by viewModels()

    private var currentCategory: String = ""
    private var lastCheckedChipId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            // get categories list data from Api
            val chipList = viewModel.getCategories().sortedBy { it.type }.reversed()
            viewModel.updateCategory(chipList[0].type)

            for (categoryType in chipList) {
                val chip = Chip(this@HomeScreen)
                chip.text = categoryType.type
                chip.id = categoryType.type.hashCode()

                // select the initial chip from list
                if (chip.text == chipList.first().type) {
                    chip.isChecked = true
                    chip.chipStrokeColor = getColorStateList(R.color.colorPrimary)
                    chip.setTextColor(getColor(R.color.colorOnPrimary))
                    lastCheckedChipId = chip.id
                }

                chip.setOnClickListener {
                    if (lastCheckedChipId == chip.id) {
                        return@setOnClickListener // just return on selecting the same chip again
                    } else {
                        val oldChip: Chip = findViewById(lastCheckedChipId) // change the old chip to unselected style
                        oldChip.chipStrokeColor = getColorStateList(R.color.colorBlackShadow)
                        oldChip.setTextColor(getColor(R.color.colorBlackSoft))

                        chip.chipStrokeColor = getColorStateList(R.color.colorPrimary) // change the new chip to selected style
                        chip.setTextColor(getColor(R.color.colorOnPrimary))

                        viewModel.updateCategory(chip.text.toString()) // Update the viewModel category so that we can observe it

                        lastCheckedChipId = chip.id
                    }
                }
                binding.chipGroup.addView(chip)
            }
        }

        viewModel.currentCategory.observe(this) { newCategory ->

            if (newCategory.isNullOrEmpty()) {
                return@observe
            } else {
                currentCategory = newCategory
                binding.rvRecipesList.visibility = View.VISIBLE
                binding.loadingLayout.visibility = View.GONE

                binding.rvRecipesList.setHasFixedSize(true)
                binding.rvRecipesList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

                lifecycleScope.launch {
                    val data = viewModel.getRecipesByCategoryType(newCategory)
                    binding.recipeHeader.text = getString(R.string.recipe_header, data.size)
                    binding.rvRecipesList.layoutAnimation = AnimationUtils.loadLayoutAnimation(this@HomeScreen, R.anim.layout_animation)
                    binding.rvRecipesList.adapter = RecipeListAdapter(data) { mealId ->

                        val intent = Intent(this@HomeScreen, RecipeDetailsActivity::class.java)
                        intent.putExtra("mealId", mealId)
                        startActivity(intent)
                    }
                }
            }

        }

    }
}