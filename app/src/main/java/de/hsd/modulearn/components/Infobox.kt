package de.hsd.modulearn.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import de.hsd.modulearn.R
import de.hsd.modulearn.theme.*


@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun InfoBox() {
    BoxWithConstraints(
        modifier = Modifier
            .aspectRatio(2.5f)
            .clip(RoundedCornerShape(10.dp))
            .background(LightGrey)
    ) {
        Box(
            modifier = Modifier
                .padding(20.dp)
                .align(Alignment.CenterStart)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.round_info_24),
                    contentDescription = "Info",
                    tint = DarkGrey,
                    modifier = Modifier.size(35.dp)
                )

                Text(
                    text = "Willkommen bei moduLearn! \nDiese App stellt dir Inhalte und Quizze zum Lernen unterwegs zur Verfügung. \nViel Spaß beim Lernen wünschen Amelie Schepping, Dorina Diem und Julius Kohlmetz :)",
                    style = Typography.displaySmall,
                    color = DarkGrey
                )
            }
        }
    }
}

