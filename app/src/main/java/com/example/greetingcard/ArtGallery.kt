package com.example.greetingcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.greetingcard.ui.theme.GreetingCardTheme


data class Artwork(
    val imageResId: Int,
    val title: String,
    val author: String
)

class ForthActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GreetingCardTheme {
                ArtGalleryApp()
            }
        }
    }
}

@Composable
fun ArtGalleryApp(modifier: Modifier = Modifier) {
    val artworks = listOf(
        Artwork(R.drawable.victor_hugo_harmatiuk_uncontacted_attack, "Uncontacted - Attack", "Victor Hugo Harmatiuk (2025)"),
        Artwork(R.drawable.victor_hugo_harmatiuk_environemtns, "Minas Morgul", "Victor Hugo Harmatiuk (2023)"),
        Artwork(R.drawable.william_chyr_manifold_garden, "Manifold Garden", "William Chyr (2015)")
    )

    val buttonColor = Color(0xFF4CAF50) // Material Green 500
    val buttonDisabledColor = Color(0xFFA5D6A7)
    var currentIndex by remember { mutableStateOf(0) }
    val current = artworks[currentIndex]

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(Color.LightGray)
                .padding(12.dp)
                .shadow(elevation = 10.dp, shape = RoundedCornerShape(16.dp))
        ) {
            Image(
                painter = painterResource(current.imageResId),
                contentDescription = current.title,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .wrapContentSize()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = current.title,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )

        Text(
            text = current.author,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { if (currentIndex > 0) currentIndex-- },
                enabled = currentIndex > 0,
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = buttonColor,
                    disabledContainerColor = buttonDisabledColor,
                    contentColor = Color.White,
                    disabledContentColor = Color.White.copy(alpha = 0.7f)
                )) {
                Text("Previous")
            }

            Button(
                onClick = { if (currentIndex < artworks.size - 1) currentIndex++ },
                enabled = currentIndex < artworks.size - 1,
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = buttonColor,
                    disabledContainerColor = buttonDisabledColor,
                    contentColor = Color.White,
                    disabledContentColor = Color.White.copy(alpha = 0.7f)
                )
            ) {
                Text("Next")
            }
        }
    }
}