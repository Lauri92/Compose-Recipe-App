package fi.lauriari.recipe_app.util

import fi.lauriari.recipe_app.data.model.*

object Constants {

    const val BASE_URL = "https://api.edamam.com/api/recipes/"
    const val BASE_URL_APPENDABLE = "v2"

    const val SEARCH_SCREEN = "search"
    const val DETAILED_RECIPE_SCREEN = "detailedrecipe"
    const val FAVORITES_SCREEN = "favorites"

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


    val testItem = Recipe(
        uri = "http://www.edamam.com/ontologies/edamam.owl#recipe_2348edbc718e2005dd15a1854a593631",
        label = "Hello Dolly Bars",
        image = "https://www.edamam.com/web-img/fbd/fbd36881a1390ba85ccc6232c5ab0eb7.jpg",
        images = Images(
            tHUMBNAIL = THUMBNAIL(
                url = "https://www.edamam.com/web-img/fbd/fbd36881a1390ba85ccc6232c5ab0eb7-s.jpg",
                width = 100,
                height = 100
            ),
            sMALL = SMALL(
                url = "https://www.edamam.com/web-img/fbd/fbd36881a1390ba85ccc6232c5ab0eb7-m.jpg",
                width = 200,
                height = 200
            ),
            rEGULAR = REGULAR(
                url = "https://www.edamam.com/web-img/fbd/fbd36881a1390ba85ccc6232c5ab0eb7.jpg",
                width = 300,
                height = 300
            )
        ),
        source = "Smitten Kitchen",
        url = "http://smittenkitchen.com/2007/10/lookin-swell-dolly/",
        shareAs = "http://www.edamam.com/recipe/hello-dolly-bars-2348edbc718e2005dd15a1854a593631/hello",
        yield = 8.0,
        dietLabels = listOf(),
        healthLabels = listOf(
            "Kidney-Friendly",
            "Vegetarian",
            "Pescatarian",
            "Egg-Free",
            "Peanut-Free",
            "Soy-Free",
            "Fish-Free",
            "Shellfish-Free",
            "Pork-Free",
            "Red-Meat-Free",
            "Crustacean-Free",
            "Celery-Free",
            "Mustard-Free",
            "Sesame-Free",
            "Lupine-Free",
            "Mollusk-Free",
            "Alcohol-Free",
            "Sulfite-Free",
            "Kosher"
        ),
        cautions = listOf("Tree-Nuts", "Sulfites"),
        ingredientLines = listOf(
            "1/2 cup (1 stick) salted butter cut into large pieces",
            "1 1/2 cups graham crackers crumbs (about 8 graham crackers, pulsed in a food processor)",
            "1 1/2 cups chocolate chips, 1 cup butterscotch chips",
            "1 cup shredded coconut, 1 cup pecans, coarsely chopped",
            "1/3 cup sweetened condensed milk (just under half a small can)"
        ),
        ingredients = listOf<Ingredients>(
            Ingredients(
                text = "1/2 cup (1 stick) salted butter cut into large pieces",
                quantity = 0.5,
                measure = "cup",
                food = "salted butter",
                weight = 113.5,
                foodCategory = "Dairy",
                foodId = "food_axwam0ga2lqqlabfq1kqtbloozm3",
                image = "https://www.edamam.com/food-img/515/515af390107678fce1533a31ee4cc35b.jpeg",
            ),
            Ingredients(
                text =
                "1 1/2 cups graham crackers crumbs (about 8 graham crackers, pulsed in a food processor)",
                quantity = 1.5,
                measure = "cup",
                food = "crackers crumbs",
                weight = 78.0,
                foodCategory = "crackers",
                foodId = "food_bngjmfmb13ckgcaefl7gta8r5zx5",
                image = "https://www.edamam.com/food-img/533/5335c3d911793785012a46379a6ad2d3.jpg",
            ),
            Ingredients(
                text = "1 / 2 cups chocolate chips",
                quantity = 1.5,
                measure = "cup",
                food = "chocolate chips",
                weight = 336.0,
                foodCategory = "chocolate",
                foodId = "food_b0camz2asgzienbko2epvbzzv6qj",
                image = "https://www.edamam.com/food-img/36c/36ce336d10d51259f365ec2dbc1e28c4.jpg",
            ),

            Ingredients(
                text = "1 cup butterscotch chips",
                quantity = 1.0,
                measure = "< unit >",
                food = "butterscotch chips",
                weight = 5.3,
                foodCategory = "candy",
                foodId = "food_a5nqo2hb1qbgb3bri53uxbu8768d",
                image = "https://www.edamam.com/food-img/6eb/6eb3d4288e5caa0ca57d06d12927f65f.jpg"
            ),
            Ingredients(
                text = "1 cup shredded coconut",
                quantity = 1.0,
                measure = "cup",
                food = "shredded coconut",
                weight = 85.0,
                foodCategory = "plant - based protein",
                foodId = "food_a9ua70bboyd4rebvrdwb0benu8pn",
                image = "https://www.edamam.com/food-img/d76/d76b9333b99d55d69b023d81d179aa39.jpg"
            ),
            Ingredients(
                text = "1 cup pecans coarsely chopped",
                quantity = 1.0,
                measure = "cup",
                food = "pecans",
                weight = 109.0,
                foodCategory = "plant - based protein",
                foodId = "food_a9apeypa4rgl7eax9hbjradaa4ry",
                image = "https://www.edamam.com/food-img/42f/42ff4c36d4d0b4e8069604c97af1cfef.jpg"
            ),
            Ingredients(
                text = "1 / 3 cup sweetened condensed milk(just under half a small can)",
                quantity = 0.3333333333333333,
                measure = "cup",
                food = "sweetened condensed milk",
                weight = 102.0,
                foodCategory = "Milk",
                foodId = "food_a89byenbz1jssabxmx7i6aa77jz1",
                image = "https://www.edamam.com/food-img/bc9/bc97e9aa15ccef74dbad4d6267e30e3f.jpg"
            )
        ),
        calories = 4560.648,
        totalWeight = 828.8,
        totalTime = 0.0,
        cuisineType = listOf("american"),
        mealType = listOf("lunch / dinner)"),
        dishType = listOf("dessert"),
    )

}