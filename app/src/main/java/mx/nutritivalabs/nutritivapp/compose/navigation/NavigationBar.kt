package mx.nutritivalabs.nutritivapp.compose.navigation

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import mx.nutritivalabs.nutritivapp.ui.theme.NutritivappTheme
import mx.nutritivalabs.nutritivapp.ui.theme.black

@Composable
fun BottomNavbar(items: List<NavigationItem>, navController: NavHostController) {
    var selected by remember { mutableStateOf(0) }

    BottomAppBar(
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = MaterialTheme.colors.primary,
        cutoutShape = CircleShape
    ) {
        items.forEachIndexed { i, item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(text = item.title, color = black.copy(alpha = .4f)) },
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = MaterialTheme.colors.primary.copy(0.4f),
                alwaysShowLabel = true,
                selected = selected == i,
                onClick = {
                    selected = i
                    navController.navigate(item.route)
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    NutritivappTheme() {
        BottomNavbar(
            listOf(
                NavigationItem.Schedule,
                NavigationItem.Patients,
                NavigationItem.Settings
            ),
            rememberNavController()
        )
    }
}