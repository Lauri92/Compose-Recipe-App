package fi.lauriari.recipe_app.data.model

import com.google.gson.annotations.SerializedName

data class EdamamSearchResult(
    @SerializedName("from") val from: Int,
    @SerializedName("to") val to: Int,
    @SerializedName("count") val count: Int,
    @SerializedName("_links") val _links: _links,
    @SerializedName("hits") val hits: List<Hits>
)

data class _links(

    @SerializedName("self") val self: Self,
    @SerializedName("next") val next: Next
)

data class Next(

    @SerializedName("href") val href: String,
    @SerializedName("title") val title: String
)

data class Self(

    @SerializedName("href") val href: String,
    @SerializedName("title") val title: String
)

data class Hits(

    @SerializedName("recipe") val recipe: Recipe,
    @SerializedName("_links") val _links: _links
)

data class Recipe(

    @SerializedName("uri") val uri: String,
    @SerializedName("label") val label: String,
    @SerializedName("image") val image: String,
    @SerializedName("images") val images: Images,
    @SerializedName("source") val source: String,
    @SerializedName("url") val url: String,
    @SerializedName("shareAs") val shareAs: String,
    @SerializedName("yield") val yield: Double,
    @SerializedName("dietLabels") val dietLabels: List<String>,
    @SerializedName("healthLabels") val healthLabels: List<String>,
    @SerializedName("cautions") val cautions: List<String>,
    @SerializedName("ingredientLines") val ingredientLines: List<String>,
    @SerializedName("ingredients") val ingredients: List<Ingredients>,
    @SerializedName("calories") val calories: Double,
    @SerializedName("totalWeight") val totalWeight: Double,
    @SerializedName("totalTime") val totalTime: Double,
    @SerializedName("cuisineType") val cuisineType: List<String>,
    @SerializedName("mealType") val mealType: List<String>,
    @SerializedName("dishType") val dishType: List<String>
)

data class Images(

    @SerializedName("THUMBNAIL") val tHUMBNAIL: THUMBNAIL,
    @SerializedName("SMALL") val sMALL: SMALL,
    @SerializedName("REGULAR") val rEGULAR: REGULAR
)

data class REGULAR(

    @SerializedName("url") val url: String,
    @SerializedName("width") val width: Int,
    @SerializedName("height") val height: Int
)

data class SMALL(

    @SerializedName("url") val url: String,
    @SerializedName("width") val width: Int,
    @SerializedName("height") val height: Int
)

data class THUMBNAIL(

    @SerializedName("url") val url: String,
    @SerializedName("width") val width: Int,
    @SerializedName("height") val height: Int
)

data class Ingredients(

    @SerializedName("text") val text: String,
    @SerializedName("quantity") val quantity: Double,
    @SerializedName("measure") val measure: String,
    @SerializedName("food") val food: String,
    @SerializedName("weight") val weight: Double,
    @SerializedName("foodCategory") val foodCategory: String,
    @SerializedName("foodId") val foodId: String,
    @SerializedName("image") val image: String
)