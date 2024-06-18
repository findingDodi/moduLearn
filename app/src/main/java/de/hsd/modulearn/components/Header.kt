package de.hsd.modulearn.components

import androidx.compose.material.icons.Icons

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton

import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable

import de.hsd.modulearn.ui.theme.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Header(title: String) {

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = White,
            titleContentColor = PrimaryDarkBlue,
        ),
        title = {
            Text(title)
        },
        actions = {
            Button(
                onClick = {
                    // do something
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryDarkLilac,
                    contentColor = White
                )
            ) {
                Text("50P", style = Typography.headlineSmall)
            }
        }
    )
}
