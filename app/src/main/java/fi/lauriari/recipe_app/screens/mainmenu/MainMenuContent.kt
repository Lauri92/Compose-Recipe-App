package fi.lauriari.recipe_app.screens.mainmenu

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fi.lauriari.recipe_app.R

@Composable
fun MainMenuContent(
    navigateToSearchScreen: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painterResource(R.drawable.foodbackground),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MainMenuButton(
                buttonText = "Browse Recipes",
                navigateToSearchScreen = navigateToSearchScreen
            )
            MainMenuButton(
                buttonText = "Browse Favorites",
                navigateToSearchScreen = {}
            )
        }
    }
}

@Composable
fun MainMenuButton(
    buttonText: String,
    navigateToSearchScreen: () -> Unit
) {
    OutlinedButton(
        modifier = Modifier
            .padding(32.dp),
        onClick = {
            navigateToSearchScreen()
        },
        border = BorderStroke(2.dp, Color.Black),
        shape = RoundedCornerShape(50.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = Color.Black
        ),
    ) {
        Text(
            text = buttonText,
            fontSize = 30.sp
        )
    }
}