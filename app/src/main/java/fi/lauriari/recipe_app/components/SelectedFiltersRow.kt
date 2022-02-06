package fi.lauriari.recipe_app.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fi.lauriari.recipe_app.viewmodels.MainViewModel

@Composable
fun SelectedFiltersRow(
    mainViewModel: MainViewModel,
    onResetCuisineType: () -> Unit,
    onResetMealType: () -> Unit,
    onResetDishType: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(bottom = 5.dp)
    ) {

        val list = mutableListOf<Pair<String, () -> Unit>>()

        if (mainViewModel.cuisineType.value != "") {
            list.add(Pair(mainViewModel.cuisineType.value, onResetCuisineType))
        }
        if (mainViewModel.mealType.value != "") {
            list.add(Pair(mainViewModel.mealType.value, onResetMealType))
        }
        if (mainViewModel.dishType.value != "") {
            list.add(Pair(mainViewModel.dishType.value, onResetDishType))
        }

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(items = list) { item ->
                SelectedFilterItem(selectedFilterValue = item.first) {
                    item.second()
                }
            }
        }

    }
}