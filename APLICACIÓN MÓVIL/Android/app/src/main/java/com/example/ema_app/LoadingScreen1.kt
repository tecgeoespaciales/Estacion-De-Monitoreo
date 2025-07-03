package com.example.ema_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.ema_app.ui.theme.EMA_APPTheme
import com.google.accompanist.drawablepainter.rememberDrawablePainter

class LoadingScreen1 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EMA_APPTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Image(
        painter = rememberDrawablePainter(
            drawable = getDrawable(
                LocalContext.current,
                R.drawable.loadingif
            )
        ),
        contentDescription = "imagen1 2",
        contentScale = ContentScale.FillWidth,
        modifier = Modifier
            .fillMaxSize()
            .alpha(1.0f)
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    EMA_APPTheme {
        Greeting("Android")
    }
}