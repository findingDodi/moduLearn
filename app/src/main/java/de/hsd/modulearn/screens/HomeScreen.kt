package de.hsd.modulearn.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.OffsetEffect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import de.hsd.modulearn.R
import de.hsd.modulearn.components.*
import de.hsd.modulearn.data.BottomMenuContent
import de.hsd.modulearn.data.Module
import de.hsd.modulearn.data.Routes.oop1home
import de.hsd.modulearn.theme.*
import kotlin.io.path.Path
import kotlin.io.path.moveTo

@Composable
fun HomeScreen(navController: NavController) {
    Box(modifier = Modifier
        .background(White)
        .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 56.dp) // Optional: Platz für den Footer schaffen
        ) {
            Header("moduLearn", false, navController)
            moduleOverview(
                modules = listOf(
                    Module(
                        title = "OOP1",
                        moduleColor = PrimaryMidBlue
                    ),
                    Module(
                        title = "MCI",
                        moduleColor = PrimaryMidLilac
                    ),
                ), navController
            )
        }

        // Footer at the bottom
        Footer(modifier = Modifier.align(Alignment.BottomCenter), navController)
    }
}



@Composable
fun moduleOverview(modules: List<Module>, navController:NavController) {
    Column (modifier = Modifier
        .fillMaxWidth()){

        Text(
            text = "Modulübersicht",
            style = Typography.headlineSmall,
            modifier = Modifier
                .padding(15.dp)
        )
        
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            contentPadding = PaddingValues(start= 7.5.dp, end=7.5.dp, bottom = 100.dp),
            modifier = Modifier
                .fillMaxHeight()
        ) {
            items(modules.size){
                ModuleItem(module = modules[it], navController)
            }
        }
        
    }
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun ModuleItem (
    module: Module,
    navController:NavController
){
    BoxWithConstraints(
        modifier = Modifier
            .padding(7.5.dp)
            .aspectRatio(2f)
            .clip(RoundedCornerShape(10.dp))
            .background(module.moduleColor)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            Text(
                text = module.title,
                style = Typography.headlineSmall,
                color = White,
                modifier = Modifier.align(Alignment.TopStart)
            )
            // Start Button
            Text(
                text = "Start",
                color = White,
                style = Typography.bodyMedium,
                modifier = Modifier
                    .clickable {
                        navController.navigate(oop1home)
                    }
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(10.dp))
                    .background(PrimaryDarkBlue)
                    .padding(vertical = 6.dp, horizontal = 15.dp)
            )
        }

    }
}