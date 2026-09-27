package com.example.travelbadge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.travelbadge.ui.theme.TravelBadgeTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

data class TravelHighlight(
    val imageRes: Int,
    val title: String,
    val description: String,
    val color: Color = Color.White
)

data class MustSee(
    val imageRes: Int,
    val title: String,
    val description: String,
)

val highlights = listOf(
    TravelHighlight(
        R.drawable.urso_cantabrico,
        "Ursos-cantábricos",
        "Espécie emblemática e protegida desta região. Vive nas florestas e montanhas dos Picos da Europa.",
    ),
    TravelHighlight(
        R.drawable.camino_santiago,
        "Caminho de Santiago",
        "Os Picos da Europa estão próximos de rotas históricas do Caminho de Santiago, com paisagens incríveis e aldeias tradicionais.",
    ),
    TravelHighlight(
        R.drawable.gastronomia,
        "Gastronomia",
        "Saboreie o queijo Cabrales, o cocido lebaniego e a autenticidade rural asturiana.",
    ),
)

val mustSees = listOf(
    MustSee(
        imageRes = R.drawable.lagos_de_covadonga,
        title = "Lagos de \nCovadonga",
        description = "Lagos glaciares de beleza impressionante.",
    ),
    MustSee(
        imageRes = R.drawable.ruta_del_cares,
        title = "Ruta del \nCares",
        description = "Um dos trilhos mais espetaculares da Europa.",
    )
)

object AppColors {
    val HeaderOverlay = Color.Black.copy(alpha = 0.5f)
    val TitleGreen = Color(0xFF1B4332)
    val ContentBackground = Color(0xFFF1F8F4)
}

val roundCornerSize = 12.dp
val smallGap = 4.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TravelBadgeScreen()
        }
    }
}

@Composable
fun TravelBadgeScreen() {
    TravelBadgeTheme {
        Scaffold { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Column {
                        Header()
                        Content()
                    }
                }
            }
        }
    }
}

@Composable
fun Header(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(200.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.picos_hero),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier.matchParentSize()
        )
        Box(
            modifier = modifier.background(AppColors.HeaderOverlay),
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(smallGap, alignment = Alignment.Bottom)
            )
            {
                Text(
                    text = "Picos da Europa",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "Montanhas, trilhos e sabores do norte de Espanha.",
                    fontSize = 18.sp,
                    color = Color.White
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(smallGap),

                    ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = Color.White
                    )
                    Text(
                        text = "Astúrias - Cantábria - Castela e Leão",
                        fontSize = 11.sp,
                        color = Color.White
                    )
                }

            }
        }
    }
}

@Composable
fun Content(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier.background(AppColors.ContentBackground)
    ) {
        Column(
            modifier = modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(roundCornerSize)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(smallGap)) {
                highlights.forEach { highlight ->
                    Card(
                        imageRes = highlight.imageRes,
                        title = highlight.title,
                        description = highlight.description,
                    )
                }
            }
            MustSee()
        }
    }
}

@Composable
fun CardContent(
    title: String,
    description: String,
    imageRes: Int,
    vertical: Boolean = false
) {
    val imageModifier = if (vertical) {
        Modifier
            .height(100.dp)
            .fillMaxWidth()
    } else {
        Modifier.width(100.dp)
    }

    Image(
        painter = painterResource(id = imageRes),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = imageModifier
            .clip(RoundedCornerShape(roundCornerSize))
    )
    Column(
        modifier = Modifier
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(
            smallGap,
            alignment = Alignment.CenterVertically
        ),
    ) {
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = description,
            fontSize = 11.sp
        )
    }
}

@Composable
fun Card(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    color: Color = Color.White,
    imageRes: Int,
    vertical: Boolean = false,
) {
    Surface(
        modifier = modifier
            .height(if (vertical) 300.dp else 120.dp),
        color = color,
        shape = RoundedCornerShape(roundCornerSize),
        shadowElevation = 2.dp
    ) {
        if (vertical) Column {
            CardContent(title, description, imageRes, true)
        } else {
            Row { CardContent(title, description, imageRes) }
        }
    }
}

@Composable
fun MustSee() {
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Row {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = null,
                tint = AppColors.TitleGreen
            )
            Text(
                text = "Imperdíveis",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = AppColors.TitleGreen
            )
        }

        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(smallGap)
        ) {
            mustSees.forEach { mustSee ->
                Card(
                    imageRes = mustSee.imageRes,
                    title = mustSee.title,
                    description = mustSee.description,
                    vertical = true,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TravelBadgePreview() {
    TravelBadgeScreen()
}
