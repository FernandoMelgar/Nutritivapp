package mx.nutritivalabs.nutritivapp.compose.user

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import mx.nutritivalabs.nutritivapp.compose.UpperBar
import mx.nutritivalabs.nutritivapp.ui.theme.NutritivappTheme

@Composable
fun UserSettingsScreen() {
    Column {
        UpperBar(title = "Configuración")
        UserProfileCard(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clip(RoundedCornerShape(8.dp)),
            "https://assets.afcdn.com/story/20150527/674283_w1050h590c1cx526cy249.jpg",
            "Lily Jane Collins"
        )
        UserStatsCard(
            Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
                .clip(RoundedCornerShape(8.dp))
        )
    }


}

@Composable
fun UserProfileCard(modifier: Modifier = Modifier, profilePictureURL: String, name: String) {
    Surface(modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Image(
                painter = rememberImagePainter(
                    data = profilePictureURL,
                    builder = {
                        crossfade(true)
                    }),
                contentDescription = "Nutritionist profile pic",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(180.dp)
                    .clip(CircleShape)
                    .border(3.dp, MaterialTheme.colors.primary, CircleShape)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(name)
        }
    }
}

@Composable
fun UserStatsCard(modifier: Modifier = Modifier) {
    Surface(modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp, start = 20.dp, end = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    "24",
                    color = MaterialTheme.colors.onSurface.copy(alpha = .6f),
                    fontSize = 11.sp
                )
                Text(
                    "SESIONES",
                    fontSize = 13.sp
                )
            }
            Spacer(Modifier.width(12.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    "200",
                    color = MaterialTheme.colors.onSurface.copy(alpha = .6f),
                    fontSize = 11.sp
                )
                Text(
                    "PACIENTES",
                    fontSize = 13.sp
                )
            }
            Spacer(Modifier.width(12.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    "18/02/2021",
                    color = MaterialTheme.colors.onSurface.copy(alpha = .6f),
                    fontSize = 11.sp
                )
                Text(
                    "MIEMBRO DESDE",
                    fontSize = 13.sp
                )
            }

        }
    }
}


@Preview
@Composable
fun UserSettingsScreenPreview() {
    NutritivappTheme {
        UserSettingsScreen()
    }
}

@Preview
@Composable
fun UserStatsPreview() {
    NutritivappTheme {
        UserStatsCard()
    }
}