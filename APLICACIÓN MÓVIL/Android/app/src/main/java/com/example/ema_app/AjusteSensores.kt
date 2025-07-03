package com.example.ema_app

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ema_app.ui.theme.EMA_APPTheme


val altura = mutableStateOf("0")
val calibracionK = mutableStateOf("0")
val frecuencia = mutableStateOf("0")
class AjusteSensores : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EMA_APPTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    AjusteSensor(Modifier)
                }
            }
        }
    }
}

@Composable
fun AjusteSensor(modifier: Modifier) {
    val context= LocalContext.current
    Box(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.imagen11),
            contentDescription = "imagen1 1",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
        Box(
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .offset(
                    x = 0.dp,
                    y = 100.dp
                )
                .fillMaxWidth()
                .height(height = 745.dp)
        ) {
            Box(
                modifier = Modifier
                    .width(width = 414.dp)
                    .height(height = 745.dp)
                    .clip(shape = RoundedCornerShape(topStart = 34.dp, topEnd = 34.dp))
                    .background(color = Color(0xE6f7e9e9))
            )
            {
                Text(
                    text = "Ajuste de Sensores",
                    modifier = Modifier
                        .align(alignment = Alignment.TopCenter)
                        .offset(
                            x = 0.dp,
                            y = (60).dp
                        )
                        .fillMaxWidth()
                        .height(height = 50.dp)
                        .clip(shape = RoundedCornerShape(topStart = 34.dp, topEnd = 34.dp))
                        .background(color = Color(0xE6f7e9e9)),
                    style = TextStyle(
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                        color = Color(0xFF000000),
                        fontSize = 24.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold
                    )
                )
                var Altura by remember { mutableStateOf("0") }
                OutlinedTextField(
                    textStyle = TextStyle(
                        fontSize = 15.sp,
                        letterSpacing = (-0.01).sp,
                        color = Color.Black
                    ),
                    value = Altura,
                    onValueChange = {
                        Altura = it
                        altura.value = Altura
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    label = { Text("Altura sensor ultrasonico (cm)") },
                    modifier = Modifier
                        .align(alignment = Alignment.TopCenter)
                        .offset(
                            x = 0.dp,
                            y = 200.dp
                        ),

                    )
                var calibK by remember { mutableStateOf("0") }
                OutlinedTextField(
                    textStyle = TextStyle(
                        fontSize = 15.sp,
                        letterSpacing = (-0.01).sp,
                        color = Color.Black
                    ),
                    value = calibK,
                    onValueChange = {
                        calibK = it
                        calibracionK.value = calibK
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    label = { Text("Calibracion K (cm/V)") },
                    modifier = Modifier
                        .align(alignment = Alignment.TopCenter)
                        .offset(
                            x = 0.dp,
                            y = 280.dp
                        ),

                    )
                var freq by remember { mutableStateOf("0") }
                OutlinedTextField(
                    textStyle = TextStyle(
                        fontSize = 15.sp,
                        letterSpacing = (-0.01).sp,
                        color = Color.Black
                    ),
                    value = freq,
                    onValueChange = {
                        freq = it
                        frecuencia.value = freq
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    label = { Text("Frecuencia de actualización (m)") },
                    modifier = Modifier
                        .align(alignment = Alignment.TopCenter)
                        .offset(
                            x = 0.dp,
                            y = 360.dp
                        ),

                    )
                ElevatedButton(
                    onClick = { envioAjustesSensores( context) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xa0d793e6)),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .align(alignment = Alignment.Center)
                        .offset(
                            x = (0).dp,
                            y = (100).dp
                        )
                        .fillMaxWidth(0.45f)
                        .requiredHeight(height = 40.dp)
                ){
                    Text("Enviar")
                }
            }

        }
    }
}

@SuppressLint("MissingPermission")
fun envioAjustesSensores(context: Context) {
    if (altura.value.toDoubleOrNull() == null || calibracionK.value.toDoubleOrNull() == null || frecuencia.value.toDoubleOrNull() == null) {
        Toast.makeText(context, "Por favor ingrese valores válidos", Toast.LENGTH_SHORT).show()
    }
    else{
        val gat = bluetoothGatt
        controlEnvio.value = 1
        datos.value = "a"+altura.value+";"+"k"+calibracionK.value+";"+"f"+frecuencia.value
        gat.requestMtu(512)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EMA_APPTheme {
        AjusteSensor(Modifier) }
}