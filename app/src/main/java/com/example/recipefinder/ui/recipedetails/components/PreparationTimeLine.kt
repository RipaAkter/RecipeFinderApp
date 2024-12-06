package com.example.recipefinder.ui.recipedetails.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipefinder.data.model.Recipe
import com.example.recipefinder.util.toHourMinuteFormat


@Composable
fun PreparationTimeLine(recipeDetails: Recipe) {
    var totalTime = recipeDetails.preparationMinutes + recipeDetails.cookingMinutes
    if (totalTime == 0) {
        totalTime = recipeDetails.readyInMinutes
    }
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            PreparationTitleAndTime(
                title = "Total Time",
                time = totalTime
            )
            if (recipeDetails.preparationMinutes > 0) {
                PreparationTitleAndTime(title = "Prep Time", time = recipeDetails.preparationMinutes)
            }
            if (recipeDetails.cookingMinutes > 0) {
                PreparationTitleAndTime(title = "Cook Time", time = recipeDetails.cookingMinutes)
            }
        }
    }
}


@Composable
fun PreparationTitleAndTime(
    title: String,
    time: Int,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp
        )
        Text(
            text = time.toHourMinuteFormat()
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewPreparationTimeLine() {
}
