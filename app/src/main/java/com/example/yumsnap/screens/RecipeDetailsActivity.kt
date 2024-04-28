package com.example.yumsnap.screens

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.yumsnap.adapter.ViewPagerAdapter
import com.example.yumsnap.databinding.ActivityRecipeDetailsBinding
import com.example.yumsnap.model.AppViewModel
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.launch

class RecipeDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeDetailsBinding

    private val viewModel: AppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mealId: Int = intent.getIntExtra("mealId", 0)


        binding.backButton.setOnClickListener {
            finish()
        }

        binding.viewPager.adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = if(position == 0) "Ingredients" else "Instructions"
        }.attach()

        lifecycleScope.launch {
            val recipe = viewModel.getRecipeDetails(mealId)

            binding.recipeName.text = recipe.name
            binding.recipeTags.text = recipe.tags?.replace(","," | ") ?: recipe.name
            binding.recipeArea.text = recipe.area
            binding.recipeCategory.text = recipe.category

            binding.recipeImage.load(recipe.mealThumb) {
                crossfade(true)
            }

            binding.ytBtn.setOnClickListener {
                val intentYt = Intent(Intent.ACTION_VIEW)
                intentYt.data = Uri.parse(recipe.youtubeLink)
                startActivity(intentYt)
            }

            binding.shareBtn.setOnClickListener {
                val intentShare = Intent(Intent.ACTION_SEND)
                intentShare.type = "text/plain"
                intentShare.putExtra(Intent.EXTRA_TEXT, recipe.strSource)
                startActivity(Intent.createChooser(intentShare, "Share with"))
            }

            val nameList = mutableListOf<String>()
            val amountList = mutableListOf<String>()

            for (index in 1..19) {
                val ingredientProp = "strIngredient$index"
                val amountProp = "strMeasure$index"

                val ingredientValue = recipe::class.java.getDeclaredField(ingredientProp).get(recipe) as? String
                val amountValue = recipe::class.java.getDeclaredField(amountProp).get(recipe) as? String

                if (!ingredientValue.isNullOrBlank() && !amountValue.isNullOrBlank()) {
                    nameList.add(ingredientValue)
                    amountList.add(amountValue)
                }
            }

            recipe.instructions?.let { viewModel.setInstructions(it) }

            viewModel.setIngredientData(nameList, amountList)
        }
    }
}