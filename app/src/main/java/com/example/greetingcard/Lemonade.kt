package com.example.greetingcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.greetingcard.ui.theme.GreetingCardTheme
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GreetingCardTheme{
                LemonApp()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LemonApp(){
    LemonadeImage(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun LemonadeImage(modifier: Modifier = Modifier) {
    var result by remember { mutableStateOf(1) }
    var clicksNeeded by remember { mutableStateOf(0) }

    val imageResource = when (result) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val textResource = when (result) {
        1 -> R.string.lemon_tree_action
        2 -> R.string.lemon_action
        3 -> R.string.glass_of_lemonade_action
        else -> R.string.empty_glass_action
    }

    val softYellow = Color(0xFFCAEBCC)

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .clickable {
                    when (result) {
                        1 -> {
                            result = 2
                            clicksNeeded = (2..4).random()
                        }
                        2 -> {
                            clicksNeeded--
                            if (clicksNeeded <= 0) {
                                result = 3
                            }
                        }
                        3 -> {
                            result = 4
                        }
                        4 -> {
                            result = 1
                        }
                    }
                }
                .background(
                    color = softYellow,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(12.dp)
                .sizeIn(maxWidth = 200.dp, maxHeight = 200.dp)
        ) {
            Image(
                painter = painterResource(imageResource),
                contentDescription = stringResource(textResource),

                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(textResource),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}