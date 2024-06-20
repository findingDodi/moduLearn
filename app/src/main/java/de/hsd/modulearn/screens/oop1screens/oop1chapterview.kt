package de.hsd.modulearn.screens.oop1screens

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import de.hsd.modulearn.components.*
import de.hsd.modulearn.theme.*

@Composable
fun Oop1ChapterView(navController: NavController, title :String, content: String) {
    Box(modifier = Modifier
        .background(White)
        .fillMaxSize()
    ){
        Column {
            Header(title = "OOP1", navController = navController)
            Text(
                text = title,
                style = Typography.headlineSmall,
                modifier = Modifier
                    .padding(15.dp)
            )

            Text(text = content,
                style = Typography.bodyLarge,
                modifier = Modifier
                    .padding(15.dp))
        }
    }
}