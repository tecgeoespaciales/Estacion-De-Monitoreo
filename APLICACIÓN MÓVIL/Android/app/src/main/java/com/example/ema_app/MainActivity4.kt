package com.example.ema_app

import android.annotation.SuppressLint
import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCallback
import android.bluetooth.BluetoothGattCharacteristic
import android.bluetooth.BluetoothGattService
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Build
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Label
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.ema_app.ui.theme.EMA_APPTheme
import java.util.UUID


var wifitF = mutableStateOf("Alejo")
var wifiN = mutableStateOf("")

var wifiClavetF = mutableStateOf("Alejo1993")
var wifiClavetN = mutableStateOf("")

var serverMqttF = mutableStateOf("138.128.244.81")
var serverMqttN = mutableStateOf("")

var puertoMqttF = mutableStateOf("1883")
var puertoMqttN = mutableStateOf("")

var usuarioMqttF = mutableStateOf("EMA")
var usuarioMqttN = mutableStateOf("")

var claveMqttF = mutableStateOf("EMASGC")
var claveMqttN = mutableStateOf("")

var datos = mutableStateOf("")
var ajustesAvanzados = mutableStateOf(0f)
var ajustesAvanzados2 = mutableStateOf(false)

class MainActivity4 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EMA_APPTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    CONFIG(Modifier)
                }
            }
        }
    }

}

@SuppressLint("MissingPermission")
private fun buttonClicked1(context: Context) {

    val gat = bluetoothGatt
    controlEnvio.value = 1
    datos.value = "W"+wifiN.value + ";" + "X"+wifiClavetN.value + ";" + "S"+serverMqttN.value + ";" + "P"+puertoMqttN.value + ";" + "U"+usuarioMqttN.value + ";" + "C"+claveMqttN.value
    gat.requestMtu(512)
}




private fun buttonClicked2(context: Context){
    val navigate = Intent(context, AjusteSensores::class.java)
    context.startActivity(navigate)

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CONFIG(modifier: Modifier = Modifier) {
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
                .fillMaxWidth()
                .height(height = 745.dp)
        ) {
            Box(
                modifier = Modifier
                    .width(width = 414.dp)
                    .height(height = 710.dp)
                    .clip(shape = RoundedCornerShape(34.dp))
                    .background(color = Color(0xE6f7e9e9)))
            Text(
                text = "Ajustes",
                color = Color(0xff2d0c57),
                textAlign = TextAlign.Center,
                lineHeight = 1.21.em,
                style = TextStyle(
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.41.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(
                        x = 3.dp,
                        y = (-20).dp
                    )
                    .requiredWidth(width = 326.dp)
                    .requiredHeight(height = 80.dp)
                    .wrapContentHeight(align = Alignment.CenterVertically))
            Icon(
                Icons.Rounded.Build,
                contentDescription = "Localized description",
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(
                        x = (70).dp,
                        y = (10).dp
                    ),
                tint = Color(0xff2d0c57)
                    
            )
            HorizontalDivider(thickness = 2.dp,
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(
                        x = 0.dp,
                        y = 50.dp
                    )
                    .width(width = 374.dp)
                    .fillMaxHeight(0.7f))
            Text(
                text = "Ajustes Actuales",
                color = Color.Black,
                lineHeight = 1.em,
                style = TextStyle(
                    fontSize = 12.sp,
                    letterSpacing = (-0.01).sp,
                    background = Color(0xDFF5E4E4)
                ),
                textAlign = TextAlign.End,
                fontWeight = FontWeight.Bold,

                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .requiredHeight(height = 33.dp)
                    .wrapContentHeight(align = Alignment.CenterVertically)
                    .offset(
                        x = 280.dp,
                        y = 33.dp
                    )
            )

            Box(
                modifier = Modifier
                    .offset(
                        x = 230.dp,
                        y = 70.dp
                    )
            ){
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .clip(shape = RoundedCornerShape(10.dp))
                        .border(width = 1.dp, color = Color(0x996E6464), shape = RoundedCornerShape(10.dp))
                        .height(height = 100.dp)
                        .width(width = 150.dp)
                    ,
                ){
                    Text(
                        text = "WIFI: "+ wifitF.value,
                        color = Color.Black,
                        lineHeight = 1.em,
                        style = TextStyle(
                            fontSize = 10.sp,
                            letterSpacing = (-0.01).sp),
                        textAlign = TextAlign.End,
                        fontWeight = FontWeight.Normal,

                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = (10).dp,
                                y = 10.dp
                            )
                            .requiredHeight(height = 33.dp)
                            .wrapContentHeight(align = Alignment.CenterVertically))
                    Text(
                        text = "Clave: "+ wifiClavetF.value,
                        color = Color.Black,
                        lineHeight = 1.em,
                        style = TextStyle(
                            fontSize = 10.sp,
                            letterSpacing = (-0.01).sp),
                        textAlign = TextAlign.End,
                        fontWeight = FontWeight.Normal,

                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = (10).dp,
                                y = 30.dp
                            )
                            .requiredHeight(height = 33.dp)
                            .wrapContentHeight(align = Alignment.CenterVertically))
                }}
            Text(
                text = "Ajustes de Red",
                color = Color.Black,
                lineHeight = 1.em,
                style = TextStyle(
                    fontSize = 10.sp,
                    letterSpacing = (-0.01).sp,
                    background = Color(0xDFF5E4E4)
                ),
                textAlign = TextAlign.End,
                fontWeight = FontWeight.Normal,

                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .requiredHeight(height = 33.dp)
                    .wrapContentHeight(align = Alignment.CenterVertically)
                    .offset(
                        x = 240.dp,
                        y = 53.dp
                    )
            )


            Box(
                modifier = Modifier
                    .offset(
                        x = 60.dp,
                        y = 70.dp
                    )
            ){
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .border(width = 1.dp, color = Color(0x996E6464), shape = RoundedCornerShape(10.dp))
                    .height(height = 100.dp)
                    .width(width = 150.dp)
,
            ){
                Text(
                    text = "MQTT: "+ serverMqttF.value,
                    color = Color.Black,
                    lineHeight = 1.em,
                    style = TextStyle(
                        fontSize = 10.sp,
                        letterSpacing = (-0.01).sp),
                    textAlign = TextAlign.End,
                    fontWeight = FontWeight.Normal,

                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = (10).dp,
                            y = 10.dp
                        )
                        .requiredHeight(height = 33.dp)
                        .wrapContentHeight(align = Alignment.CenterVertically))
                Text(
                    text = "Puerto: "+ puertoMqttF.value,
                    color = Color.Black,
                    lineHeight = 1.em,
                    style = TextStyle(
                        fontSize = 10.sp,
                        letterSpacing = (-0.01).sp),
                    textAlign = TextAlign.End,
                    fontWeight = FontWeight.Normal,

                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = (10).dp,
                            y = 30.dp
                        )
                        .requiredHeight(height = 33.dp)
                        .wrapContentHeight(align = Alignment.CenterVertically))
                Text(
                    text = "Usuario: "+ usuarioMqttF.value,
                    color = Color.Black,
                    lineHeight = 1.em,
                    style = TextStyle(
                        fontSize = 10.sp,
                        letterSpacing = (-0.01).sp),
                    textAlign = TextAlign.End,
                    fontWeight = FontWeight.Normal,

                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = (10).dp,
                            y = 50.dp
                        )
                        .requiredHeight(height = 33.dp)
                        .wrapContentHeight(align = Alignment.CenterVertically))
                Text(
                    text = "Clave: "+ claveMqttF.value,
                    color = Color.Black,
                    lineHeight = 1.em,
                    style = TextStyle(
                        fontSize = 10.sp,
                        letterSpacing = (-0.01).sp),
                    textAlign = TextAlign.End,
                    fontWeight = FontWeight.Normal,

                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = (10).dp,
                            y = 70.dp
                        )
                        .requiredHeight(height = 33.dp)
                        .wrapContentHeight(align = Alignment.CenterVertically))
            }}
            Text(
                text = "Ajustes IOT",
                color = Color.Black,
                lineHeight = 1.em,
                style = TextStyle(
                    fontSize = 10.sp,
                    letterSpacing = (-0.01).sp,
                    background = Color(0xDFF5E4E4)
                ),
                textAlign = TextAlign.End,
                fontWeight = FontWeight.Normal,

                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .requiredHeight(height = 33.dp)
                    .wrapContentHeight(align = Alignment.CenterVertically)
                    .offset(
                        x = 70.dp,
                        y = 53.dp
                    )
                    )


            HorizontalDivider(thickness = 2.dp,
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(
                        x = 0.dp,
                        y = 180.dp
                    )
                    .width(width = 374.dp)
                    .fillMaxHeight(0.7f))

            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(
                        x = 0.dp,
                        y = 180.dp
                    )
                    .width(width = 374.dp)
                    .fillMaxHeight(0.7f)

            ) {


                val offsetW = Offset(5.0f, 10.0f)
                Text(text = "WIFI:",
                    color = Color.Black,
                    lineHeight = 1.2.em,
                    style = TextStyle(
                        fontSize = 15.sp,
                        letterSpacing = (-0.01).sp,
                        shadow = Shadow(color = Color.hsv(300f, 0.2f, 0.6f), offset = offsetW, blurRadius = 4f)),
                        textAlign = TextAlign.End,
                        fontWeight = FontWeight.Bold,

                    modifier = Modifier
                        .align(alignment = Alignment.TopEnd)
                        .offset(
                            x = (-30).dp,
                            y = 0.dp
                        )
                        .requiredHeight(height = 33.dp)
                        .wrapContentHeight(align = Alignment.CenterVertically))
                var wifit by remember { mutableStateOf(" ") }
                    OutlinedTextField(
                        textStyle = TextStyle(
                            fontSize = 15.sp,
                            letterSpacing = (-0.01).sp,
                            color = Color.Black),
                        value = wifit,
                        onValueChange = { wifit = it
                            wifiN.value=wifit
                            Log.d("CambioLabel", wifiN.value)},
                        label = { Text("Red Nueva") },
                        modifier = Modifier
                            .height(height = 55.dp)
                            .fillMaxWidth(0.9f)
                            .offset(
                                x = 30.dp,
                                y = 30.dp
                            ),
                        )

                var wifiClavet by remember { mutableStateOf(" ") }

                    OutlinedTextField(
                        textStyle = TextStyle(
                            fontSize = 15.sp,
                            letterSpacing = (-0.01).sp,
                            color = Color.Black),
                        value = wifiClavet,
                        onValueChange = { wifiClavet = it
                            wifiClavetN.value=wifiClavet
                            Log.d("CambioLabel", wifiClavetN.value)},
                        label = { Text("Clave Nueva") },
                        modifier = Modifier
                            .height(height = 55.dp)
                            .fillMaxWidth(0.9f)
                            .offset (
                                x = 30.dp,
                                y = 85.dp
                            ),
                    )

                Row(modifier = Modifier
                    .align(alignment = Alignment.CenterEnd)
                    .offset(
                        x = 0.dp,
                        y = 10.dp
                    )
                ){
                    Text(text = "MQTT (Avanzado):",
                        color = Color.Black,
                        lineHeight = 1.2.em,
                        style = TextStyle(
                            fontSize = 15.sp,
                            letterSpacing = (-0.01).sp,
                            shadow = Shadow(color = Color.hsv(300f, 0.2f, 0.6f), offset = offsetW, blurRadius = 4f)),
                        textAlign = TextAlign.End,
                        fontWeight = FontWeight.Bold,

                        modifier = Modifier
                            .offset(
                                x = (0).dp,
                                y = (-100).dp
                            )
                            .requiredHeight(height = 33.dp)
                            .wrapContentHeight(align = Alignment.CenterVertically))
                    Checkbox(
                        checked = ajustesAvanzados2.value,
                        onCheckedChange = {
                            ajustesAvanzados.value = if (it) 1f else 0f
                            ajustesAvanzados2.value = it

                        },
                        modifier = Modifier
                            .offset(
                                x = (0).dp,
                                y = (-105).dp
                            )
                    )

                }


                var serverMqtt by remember { mutableStateOf(" ") }
                OutlinedTextField(
                    textStyle = TextStyle(
                        fontSize = 15.sp,
                        letterSpacing = (-0.01).sp,
                        color = Color.Black),
                    value = serverMqtt,
                    onValueChange = { serverMqtt = it
                                     serverMqttN.value=serverMqtt
                        Log.d("CambioLabel", serverMqttN.value)},                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    label = { Text("Servidor") },
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .height(height = 55.dp)
                        .fillMaxWidth(0.9f)
                        .alpha(ajustesAvanzados.value)
                        .offset(
                            x = 30.dp,
                            y = 170.dp
                        )
                )
                var puertoMqtt by remember { mutableStateOf(" ") }
                OutlinedTextField(
                    textStyle = TextStyle(
                        fontSize = 15.sp,
                        letterSpacing = (-0.01).sp,
                        color = Color.Black),
                    value = puertoMqtt,
                    onValueChange = { puertoMqtt = it
                                     puertoMqttN.value=puertoMqtt
                        Log.d("CambioLabel", puertoMqttN.value)},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    label = { Text("Puerto") },
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .height(height = 55.dp)
                        .fillMaxWidth(0.9f)
                        .alpha(ajustesAvanzados.value)
                        .offset(
                            x = 30.dp,
                            y = 225.dp
                        )
                )
                var usuarioMqtt by remember { mutableStateOf(" ") }
                OutlinedTextField(
                    textStyle = TextStyle(
                        fontSize = 15.sp,
                        letterSpacing = (-0.01).sp,
                        color = Color.Black),
                    value = usuarioMqtt,
                    onValueChange = { usuarioMqtt = it
                                     usuarioMqttN.value=usuarioMqtt
                        Log.d("CambioLabel", usuarioMqttN.value)},                    label = { Text("Usuario") },
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .alpha(ajustesAvanzados.value)
                        .height(height = 55.dp)
                        .fillMaxWidth(0.9f)
                        .offset(
                            x = 30.dp,
                            y = 280.dp
                        )
                )
                var claveMqtt by remember { mutableStateOf(" ") }
                OutlinedTextField(
                    textStyle = TextStyle(
                        fontSize = 15.sp,
                        letterSpacing = (-0.01).sp,
                        color = Color.Black),
                    value = claveMqtt,
                    onValueChange = { claveMqtt = it
                                     claveMqttN.value=claveMqtt
                        Log.d("CambioLabel", claveMqttN.value)},                    label = { Text("Contraseña") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .alpha(ajustesAvanzados.value)
                        .height(height = 55.dp)
                        .fillMaxWidth(0.9f)
                        .offset(
                            x = 30.dp,
                            y = 335.dp
                        )
                )
            }
        }

        ElevatedButton(
            onClick = { buttonClicked2(context) },
            shape = CutCornerShape(0.dp, 0.dp, 55.dp, 0.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xa0d793e6)),
            modifier = Modifier
                .align(alignment = Alignment.BottomCenter)
                .offset(
                    x = (-70).dp,
                    y = (-20).dp
                )
                .fillMaxWidth(0.45f)
                .requiredHeight(height = 56.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 374.dp)
                    .requiredHeight(height = 56.dp)
            ) {
                Text(
                    text = "Sensores",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    lineHeight = 1.2.em,
                    style = TextStyle(
                        fontSize = 20.sp,
                        letterSpacing = (-0.01).sp),
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentHeight(align = Alignment.CenterVertically))
            }
        }
        ElevatedButton(
            onClick = { buttonClicked1(context) },
            shape = CutCornerShape(55.dp, 0.dp, 0.dp, 0.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xa0d793e6)),
            modifier = Modifier
                .align(alignment = Alignment.BottomCenter)
                .offset(
                    x = 70.dp,
                    y = (-20).dp
                )
                .fillMaxWidth(0.45f)
                .requiredHeight(height = 56.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 374.dp)
                    .requiredHeight(height = 56.dp)
            ) {
                Text(
                    text = "Enviar",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    lineHeight = 1.2.em,
                    style = TextStyle(
                        fontSize = 20.sp,
                        letterSpacing = (-0.01).sp),
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentHeight(align = Alignment.CenterVertically))
            }
        }
    }
}

@Preview(widthDp = 414, heightDp = 896)
@Composable
private fun CONFPreview() {
    CONFIG(Modifier)
}