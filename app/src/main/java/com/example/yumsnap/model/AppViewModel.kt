package com.example.yumsnap.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yumsnap.network.Api
import com.example.yumsnap.network.KtorClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class AppViewModel: ViewModel() {

    private val client = KtorClient.client

    private val _currentCategory = MutableLiveData<String>()
    val currentCategory: LiveData<String> = _currentCategory

    private var _ingredientsList = MutableLiveData<Pair<List<String>, List<String>>>()
    val ingredientsList: LiveData<Pair<List<String>, List<String>>> = _ingredientsList

    private var _instructions = MutableLiveData<String>()
    val instructions: LiveData<String> = _instructions

    suspend fun getCategories(): List<Category> {
        val response = client
            .get(Api.Categories)
            .body<CategoryList>()

        return response.category
    }

    fun updateCategory(category: String) {
        _currentCategory.value = category
    }

    suspend fun getRecipesByCategoryType(categoryType: String): List<Meal> {
        val respose = client
            .get(Api.Filter + categoryType)
            .body<RecipeList>()

        return respose.recipes
    }

    suspend fun getRecipeDetails(id: Int): Recipe {
        val response = client
            .get(Api.DetailRecipe + id.toString())
            .body<RecipeDetails>()

        return response.recipes.first()
    }

    fun setIngredientData(category: List<String>, amount: List<String>) {
        _ingredientsList.value = Pair(category, amount)
    }

    fun setInstructions(instructions: String) {
        _instructions.value = instructions
    }
}