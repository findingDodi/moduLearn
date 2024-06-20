package de.hsd.modulearn.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import de.hsd.modulearn.R
import de.hsd.modulearn.components.ButtonWithIcon
import de.hsd.modulearn.components.Footer
import de.hsd.modulearn.components.Header
import de.hsd.modulearn.data.Module
import de.hsd.modulearn.data.Routes
import de.hsd.modulearn.data.Routes.oop1home
import de.hsd.modulearn.theme.*

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold (

        topBar = { Header("moduLearn", false, navController) },
        bottomBar = { Footer(navController) }

    ) { innerPadding ->
        Box(modifier = Modifier
            .background(White)
            .fillMaxSize()
            .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 56.dp) // Optional: Platz für den Footer schaffen
            ) {

                Box (
                    modifier = Modifier
                        .padding(15.dp)
                ){
                    ButtonWithIcon(
                        iconId = R.drawable.round_chat_bubble_24,
                        backgroundcolor = PrimaryDarkBlue ,
                        color = White ,
                        text = "ChatBot" ,
                        destinationRoute = Routes.chatBot,
                        navController = navController
                    )
                }

                ModuleOverview(
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
        }
    }
}



@Composable
fun ModuleOverview(modules: List<Module>, navController:NavController) {
    Column (modifier = Modifier
        .fillMaxWidth()){

        Text(
            text = "Modulübersicht",
            style = Typography.headlineSmall,
            modifier = Modifier.padding(15.dp)
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