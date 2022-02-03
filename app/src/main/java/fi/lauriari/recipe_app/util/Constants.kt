package fi.lauriari.recipe_app.util

object Constants {

    const val BASE_URL = "https://api.edamam.com/api/recipes/"

    const val MAINMENU_SCREEN = "mainmenu"
    const val SEARCH_SCREEN = "search"
    const val SEARCH_RESULT_SCREEN = "searchResult"

    val CUISINE_TYPES = listOf(
        "American",
        "Asian",
        "British",
        "Caribbean",
        "Central Europea",
        "Chinese",
        "Eastern Europe",
        "French",
        "Indian",
        "Italin",
        "Japanese",
        "Kosher",
        "Mediterranean",
        "Mexican",
        "Middle Eastern",
        "Nordic",
        "South American",
        "South East Asian"
    )
    val MEAL_TYPES = listOf("Breakfast", "Dinner", "Lunch", "Snack", "Teatime")
    val DISH_TYPES = listOf(
        "Biscuits and Cookies",
        "Breads", "Cereals", "Condiments and sauces",
        "Desserts", "Drinks", "Main Course",
        "Pancake", "Preps", "Preserve",
        "Salad", "Sandwiches", "Side dish",
        "Soup", "Starter", "Sweets"
    )

}