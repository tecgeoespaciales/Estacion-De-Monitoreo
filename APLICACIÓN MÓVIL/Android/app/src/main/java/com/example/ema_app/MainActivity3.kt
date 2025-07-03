package com.example.ema_app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.ema_app.ui.theme.EMA_APPTheme


val magnetometro = mutableStateOf("0")
val acelerometro = mutableStateOf("0")
val temperatura = mutableStateOf("0")
val lluvia = mutableStateOf("0")
val distancia = mutableStateOf("0")
val calidad = mutableStateOf("0")
val fecha = mutableStateOf("0")
val nombreVersion = mutableStateOf("1")

class MainActivity3 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EMA_APPTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DATOS(Modifier)
                }
            }
        }
    }
}


@Composable
fun DATOS(modifier: Modifier = Modifier) {
    val context= LocalContext.current
    Box(
        modifier = modifier

    ) {
        Image(
            painter = painterResource(id = R.drawable.imagen11),
            contentDescription = "imagen1 1",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize())
        Box(
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .offset(
                    x = 0.dp,
                    y = 100.dp
                )
                .width(width = 414.dp)
                .height(height = 659.dp)
                .clip(shape = RoundedCornerShape(34.dp))
                .background(color = Color(0xE6f7e9e9))
        ) {
            AnimatedPrel(modifier = Modifier.size(200.dp).align(Alignment.Center).offset(x = 0.dp, y = (-130).dp))
            Text(
            text = "EMA VERSIÓN: "+ nombreVersion.value,
            color = Color(0xff2d0c57),
            textAlign = TextAlign.Center,
            lineHeight = 1.21.em,
            style = TextStyle(
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.41.sp),
            modifier = Modifier
                .offset(x = (0).dp, y = 0.dp)
                .requiredWidth(width = 326.dp)
                .requiredHeight(height = 80.dp)
                .align(alignment = Alignment.Center))
            if (nombreVersion.value == "3.4"){
                Column(modifier = Modifier
                    .align(alignment = Alignment.CenterStart)
                    .offset(y = 40.dp), verticalArrangement = Arrangement.spacedBy((-10).dp)) {
                    Spacer(modifier = Modifier.height(20.dp))
                    Sensor(text = "Distancia : "+ distancia.value, offs = 0)
                    Sensor(text = "Calidad de agua : "+ calidad.value, offs = 25)
                    Sensor(text = "Fecha y hora : "+ fecha.value, offs = 50)
                }
            }
            else{
            Column(modifier = Modifier
                .align(alignment = Alignment.CenterStart)
                .offset(y = 40.dp), verticalArrangement = Arrangement.spacedBy((-10).dp)) {
                Spacer(modifier = Modifier.height(20.dp))
                Sensor(text = "Magnetometro: "+ magnetometro.value, offs = 0)
                Sensor(text = "Acelerometro : "+ acelerometro.value, offs = 25)
                Sensor(text = "Temperatura : " + temperatura.value, offs = 50)
                Sensor(text = "Lluvia : "+ lluvia.value, offs = 75)
                Sensor(text = "Distancia : "+ distancia.value, offs = 100)
                Sensor(text = "Calidad de agua : "+ calidad.value, offs = 125)
                Sensor(text = "Fecha y hora : "+ fecha.value, offs = 150)
            }
            }



        }


    }
}

private fun buttonClicked3(context: Context) {
    Intent(context, MainActivity2::class.java).also {
        context.startActivity(it)
    }
}
@Composable
fun AnimatedPrel(modifier: Modifier = Modifier) {
    val preloaderLottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            R.raw.animsensor
        )
    )

    val preloaderProgress by animateLottieCompositionAsState(
        preloaderLottieComposition,
        //iterations = LottieConstants.IterateForever,
        isPlaying = true
    )


    LottieAnimation(
        composition = preloaderLottieComposition,
        progress = preloaderProgress,
        modifier = modifier
    )
}
@Preview(widthDp = 414, heightDp = 896)
@Composable
private fun DATOSPreview() {
    DATOS(Modifier)
}

@Composable
fun Sensor(text:String,offs:Int){
    Text(text = text,
        color = Color.Black,
        style = TextStyle(
            fontSize = 15.sp,
            letterSpacing = (-0.01).sp),
        modifier = Modifier
            .offset(
                x = 30.dp,
                y = (offs).dp
            )
            .width(width = 209.dp))

}


