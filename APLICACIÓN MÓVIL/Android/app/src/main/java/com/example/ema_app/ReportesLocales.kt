package com.example.ema_app

import android.content.Context
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
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
import com.example.ema_app.ui.theme.EMA_APPTheme
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.concurrent.thread


var archivoLocal = mutableStateOf("SD/datos.csv")
var passwrdWR = mutableStateOf("EMASGC")

var ajustesAvanzados1 = mutableStateOf(0f)
var ajustesAvanzados3 = mutableStateOf(false)
val botonlocales = mutableStateOf(true)

class MainActivity5 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        setContent {
            EMA_APPTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FILE(Modifier)
                }
            }
        }
    }
}


private fun extraccionArchivo(context: Context){
    alphaloadingreportes.value=1.0f
    botonlocales.value=false
    val thread = thread(start = false){
        remoteFilesWR().obtenerArchivo(ipPort.value, archivoLocal.value, passwrdWR.value, context)
    }
    thread.start()

    }


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FILE(modifier: Modifier = Modifier) {
    val context= LocalContext.current
    val offsetW = Offset(5.0f, 10.0f)
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

        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .fillMaxHeight(0.7f)
                    .clip(shape = RoundedCornerShape(34.dp))
                    .background(color = Color(0xE6f7e9e9))){

                CircularProgressIndicator(
                    color = Color(0xff2d0c57),
                    strokeWidth = 5.dp,
                    modifier = Modifier
                        .size(50.dp)
                        .align(Alignment.TopEnd)
                        .alpha(alphaloadingreportes.value)
                        .offset(
                            x = (-30).dp,
                            y = (30).dp
                        )
                )


            }
            Image( painter = painterResource(id = R.drawable.csvdownload),
                contentDescription = "imagen1 2",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxSize(0.3f)
                    .align(alignment = Alignment.TopCenter)
                    .offset(
                        y = (-50).dp
                    )
                    .alpha(0.4f))

            Text(
                text = "EMA VERSIÓN: "+nombreVersion.value,
                color = Color(0xff2d0c57),
                textAlign = TextAlign.Center,
                lineHeight = 1.21.em,
                style = TextStyle(
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.41.sp),
                modifier = Modifier
                    .align(alignment = Alignment.Center)
                    .offset(
                        x = 0.dp,
                        y = (-130).dp
                    ))
            var archivo by remember { mutableStateOf("SD/datos.csv") }
            OutlinedTextField(
                textStyle = TextStyle(
                    fontSize = 13.sp,
                    letterSpacing = (-0.01).sp,
                    color = Color.Black),
                value = archivo,
                onValueChange = { archivo = it
                    archivoLocal.value=archivo
                    Log.d("CambioLabel", archivoLocal.value)},
                label = { Text("Direccion Local") },
                modifier = Modifier
                    .align(alignment = Alignment.Center)
                    .height(height = 55.dp)
                    .offset(
                        x = 0.dp,
                        y = (-40).dp
                    ),

                )
            OutlinedTextField(
                textStyle = TextStyle(
                    fontSize = 13.sp,
                    letterSpacing = (-0.01).sp,
                    color = Color.Black),
                value = "192.168.43.54",
                onValueChange = { archivo = it
                    ipPort.value=archivo
                    Log.d("CambioLabel", archivoLocal.value)},
                label = { Text("IP") },
                modifier = Modifier
                    .align(alignment = Alignment.Center)
                    .height(height = 55.dp)
                    .offset(
                        x = 0.dp,
                        y = 40.dp
                    ),

                )
            Row(modifier = Modifier
                .align(alignment = Alignment.Center)
                .offset(
                    x = 70.dp,
                    y = 100.dp
                )
            ){
                Text(text = "Ajustes avanzados:",
                    color = Color.Black,
                    lineHeight = 1.2.em,
                    style = TextStyle(
                        fontSize = 15.sp,
                        letterSpacing = (-0.01).sp,
                        shadow = Shadow(color = Color.hsv(300f, 0.2f, 0.6f), offset = offsetW, blurRadius = 4f)
                    ),
                    textAlign = TextAlign.End,
                    fontWeight = FontWeight.Bold,

                    modifier = Modifier
                        .offset(
                            x = (0).dp,
                            y = (0).dp
                        )
                        .requiredHeight(height = 33.dp)
                        .wrapContentHeight(align = Alignment.CenterVertically))
                Checkbox(
                    checked = ajustesAvanzados3.value,
                    onCheckedChange = {
                        ajustesAvanzados1.value = if (it) 1f else 0f
                        ajustesAvanzados3.value = it

                    },
                    modifier = Modifier
                        .offset(
                            x = (0).dp,
                            y = (-5).dp
                        )
                )

            }
            var passWR by remember { mutableStateOf("EMASGC") }
            OutlinedTextField(
                textStyle = TextStyle(
                    fontSize = 13.sp,
                    letterSpacing = (-0.01).sp,
                    color = Color.Black),
                value = passWR,
                onValueChange = { passWR = it
                    passwrdWR.value=passWR
                    Log.d("CambioLabel", passwrdWR.value)},
                label = { Text("Contraseña") },
                modifier = Modifier
                    .align(alignment = Alignment.Center)
                    .height(height = 55.dp)
                    .alpha(ajustesAvanzados1.value)
                    .offset(
                        x = 0.dp,
                        y = 150.dp
                    ),

                )
            Button(
                onClick = { extraccionArchivo(context) },
                enabled = botonlocales.value,
                shape = RoundedCornerShape(0.dp, 0.dp, 25.dp, 25.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xe0d793e6)),
                modifier = Modifier
                    .align(alignment = Alignment.BottomCenter)
                    .offset(
                        x = 0.dp,
                        y = (-20).dp
                    )
                    .width(width = 150.dp)
                    .requiredHeight(height = 56.dp)
            ) {
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 374.dp)
                        .requiredHeight(height = 56.dp)
                ) {
                    Text(
                        text = "Descargar Archivo",
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        lineHeight = 1.2.em,
                        style = TextStyle(
                            fontSize = 10.sp,
                            letterSpacing = (-0.01).sp),
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentHeight(align = Alignment.CenterVertically))
                }
            }
        }


    }
}

@Preview(widthDp = 414, heightDp = 896)
@Composable
private fun FILEPreview() {
    FILE(Modifier)
}