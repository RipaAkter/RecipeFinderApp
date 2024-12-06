package com.example.recipefinder.ui.home.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
)

@Composable
fun BottomNavigationBar(

) {
    var selectedItemIndex by rememberSaveable { mutableStateOf(0) }
    val items = listOf(
        BottomNavigationItem(title = "Home", selectedIcon = Icons.Default.Home),
        BottomNavigationItem(title = "Profile", selectedIcon = Icons.Default.Person)
    )
    NavigationBar(
        containerColor = Color.White
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = {
                    selectedItemIndex = index
                },
                label = { Text(text = item.title) },
                alwaysShowLabel = true,
                icon = { Icon(imageVector = item.selectedIcon, contentDescription = item.title) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBottomNavigationBar() {
    BottomNavigationBar()
}
