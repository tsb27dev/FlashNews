package ipca.example.flashnews.ui.components

import androidx.compose.material.icons.filled.Favorite
import ipca.example.flashnews.ui.theme.CustomIcons
import androidx.compose.material.icons.Icons
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ipca.example.flashnews.ui.theme.FlashNewsTheme

@Composable
fun MyBottomBar(
    navController: NavController
){
    var index by remember { mutableStateOf(0) }
    BottomAppBar {
        NavigationBarItem(
            selected = index == 0,
            onClick = {
                index = 0
                navController.navigate("futebol")
            },
            icon = {
                Icon(
                    imageVector = CustomIcons.Sports_football,
                    contentDescription = "Futebol"
                )
            },
            label = {
                Text("Futebol")
            }
        )
        NavigationBarItem(
            selected = index == 1,
            onClick = {
                index = 1
                navController.navigate("basquetebol")
            },
            icon = {
                Icon(
                    imageVector = CustomIcons.SportsBasketball,
                    contentDescription = "Basquetebol"
                )
            },
            label = {
                Text("Basquetebol")
            }
        )
        NavigationBarItem(
            selected = index == 2,
            onClick = {
                index = 2
                navController.navigate("favoritos")
            },
            icon = {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Favoritos"
                )
            },
            label = {
                Text("Favoritos")
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MyBottomBarPreview(){
    FlashNewsTheme {
        MyBottomBar(
            rememberNavController()
        )
    }
}