package com.example.yumsnap.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Category(
    @SerialName("strCategory") val type: String,
)

@Serializable
data class CategoryList(
    @SerialName("meals") val category: List<Category>,
)

@Serializable
data class RecipeList(
    @SerialName("meals") val recipes: List<Meal>
)

@Serializable
data class Meal(
    @SerialName("strMeal") val meal: String,
    @SerialName("strMealThumb") val thumbnail: String,
    @SerialName("idMeal") val id: String,
)

@Serializable
data class RecipeDetails(
    @SerialName("meals") val recipes: List<Recipe>
)

@Serializable
data class Recipe(
    @JvmField @SerialName("idMeal") val id: String?,
    @JvmField @SerialName("strMeal") val name: String?,
    @JvmField val strDrinkAlternate: String?,
    @JvmField @SerialName("strCategory") val category: String?,
    @JvmField @SerialName("strArea") val area: String?,
    @JvmField @SerialName("strInstructions") val instructions: String?,
    @JvmField @SerialName("strMealThumb") val mealThumb: String?,
    @JvmField @SerialName("strTags") val tags: String?,
    @JvmField @SerialName("strYoutube") val youtubeLink: String?,
    @JvmField val strIngredient1: String?,
    @JvmField val strIngredient2: String?,
    @JvmField val strIngredient3: String?,
    @JvmField val strIngredient4: String?,
    @JvmField val strIngredient5: String?,
    @JvmField val strIngredient6: String?,
    @JvmField val strIngredient7: String?,
    @JvmField val strIngredient8: String?,
    @JvmField val strIngredient9: String?,
    @JvmField val strIngredient10: String?,
    @JvmField val strIngredient11: String?,
    @JvmField val strIngredient12: String?,
    @JvmField val strIngredient13: String?,
    @JvmField val strIngredient14: String?,
    @JvmField val strIngredient15: String?,
    @JvmField val strIngredient16: String?,
    @JvmField val strIngredient17: String?,
    @JvmField val strIngredient18: String?,
    @JvmField val strIngredient19: String?,
    @JvmField val strIngredient20: String?,
    @JvmField val strMeasure1: String?,
    @JvmField val strMeasure2: String?,
    @JvmField val strMeasure3: String?,
    @JvmField val strMeasure4: String?,
    @JvmField val strMeasure5: String?,
    @JvmField val strMeasure6: String?,
    @JvmField val strMeasure7: String?,
    @JvmField val strMeasure8: String?,
    @JvmField val strMeasure9: String?,
    @JvmField val strMeasure10: String?,
    @JvmField val strMeasure11: String?,
    @JvmField val strMeasure12: String?,
    @JvmField val strMeasure13: String?,
    @JvmField val strMeasure14: String?,
    @JvmField val strMeasure15: String?,
    @JvmField val strMeasure16: String?,
    @JvmField val strMeasure17: String?,
    @JvmField val strMeasure18: String?,
    @JvmField val strMeasure19: String?,
    @JvmField val strSource: String?,
    @JvmField val strImageSource: String?,
    @JvmField val strCreativeCommonsConfirmed: String?,
    @JvmField val dateModified: String?,
)
