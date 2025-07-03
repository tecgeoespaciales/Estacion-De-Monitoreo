package com.example.ema_app

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.le.BluetoothLeScanner
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanFilter
import android.bluetooth.le.ScanResult
import android.bluetooth.le.ScanSettings
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.location.LocationRequest
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
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
import androidx.core.app.ActivityCompat
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.ema_app.ui.theme.EMA_APPTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.juul.kable.AndroidAdvertisement
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.juul.kable.Bluetooth
import com.juul.kable.Bluetooth.Availability.Available
import com.juul.kable.Bluetooth.Availability.Unavailable
import com.juul.kable.Reason.LocationServicesDisabled
import com.juul.kable.Reason.Off
import com.juul.kable.Reason.TurningOff
import com.juul.kable.Reason.TurningOn
import com.example.ema_app.permissionsNeeded
import com.google.accompanist.permissions.MultiplePermissionsState


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            if (!isLocationEnabled(this)) {
                startActivity(Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS))
            }
            val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.BLUETOOTH_SCAN
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                if (bluetoothAdapter?.isEnabled == false) {
                    val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                    startActivityForResult(enableBtIntent, 1)
                }
            }


            EMA_APPTheme {
                val bluetooth = Bluetooth.availability.collectAsState(initial = null).value
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {

                    OPTIONS(Modifier)
                }
            }
        }
    }

    private fun isLocationEnabled(context: Context): Boolean {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

private fun buttonClicked1(context: Context){
    val navigate = Intent(context, MainActivity2::class.java)
    context.startActivity(navigate)

}


private fun buttonClicked2(context: Context){
    val navigate = Intent(context, MainActivity6::class.java)
    context.startActivity(navigate)
    Log.d("Test", "Func Button 2")
}



@Composable
fun OPTIONS(modifier: Modifier = Modifier) {

    val context = LocalContext.current
    Box(

        modifier = modifier


            .background(color = Color.Black)
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
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.7f)
                .clip(shape = RoundedCornerShape(34.dp))
                .background(color = Color(0xE6f7e9e9)))
        AnimatedPreloader(modifier = Modifier.size(600.dp).align(Alignment.Center).offset(x = 0.dp, y = (-30).dp))
        Text(
            text = "EMA",
            color = Color(0xff2d0c57),
            textAlign = TextAlign.Center,
            lineHeight = 1.21.em,
            style = TextStyle(
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.41.sp),
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .offset(
                    x = 0.dp,
                    y = 50.dp
                )
                .width(width = 326.dp)
                .height(height = 80.dp))
        Text(
            text = "Estación de Monitoreo Automático",
            color = Color(0xff2d0c57),
            textAlign = TextAlign.Center,
            lineHeight = 1.21.em,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.41.sp),
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .offset(
                    x = 0.dp,
                    y = 120.dp
                )
                .width(width = 326.dp)
                .height(height = 80.dp))
        Button(
            onClick = { buttonClicked1(context) },
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xe0d793e6)),
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .offset(
                    x = 0.dp,
                    y = 160.dp
                )
                .height(height = 56.dp)
                .width(width = 300.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 374.dp)
                    .requiredHeight(height = 56.dp)
            ) {
                Text(
                    text = "MONITOREO BLUETOOTH",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    lineHeight = 1.2.em,
                    style = TextStyle(
                        fontSize = 15.sp,
                        letterSpacing = (-0.01).sp),
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentHeight(align = Alignment.CenterVertically))
            }
        }
        Button(
            onClick = { buttonClicked2(context)
            },
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xe0d793e6)),
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .offset(
                    x = 0.dp,
                    y = 220.dp
                )
                .height(height = 56.dp)
                .width(width = 300.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 374.dp)
                    .requiredHeight(height = 56.dp)
            ) {
                Text(
                    text = "BASE DE DATOS",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    lineHeight = 1.2.em,
                    style = TextStyle(
                        fontSize = 15.sp,
                        letterSpacing = (-0.01).sp),
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentHeight(align = Alignment.CenterVertically))
            }
        }
        ElevatedButton(
            onClick = { telegramF(context)
            },
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xe0229ED9)),
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .offset(
                    x = 0.dp,
                    y = 300.dp
                )
                .height(height = 56.dp)
                .width(width = 300.dp)
        ) {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.telegram),
                    contentDescription = "logo telegram",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(52.dp)
                )
                Spacer(modifier = Modifier.width(30.dp))
                Text(
                    text = "Telegram Bot",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 20.sp,
                        letterSpacing = (-0.01).sp),
                        fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(alignment = Alignment.CenterVertically))
            }

        }


    }
}

    private fun telegramF(context: Context) {
        try {
            val telegram = Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/EMASGCbot"))
            telegram.setPackage("org.telegram.messenger")
            startActivity(telegram)
        }catch (e: Exception){
            Toast.makeText(context, "Telegram no está instalado", Toast.LENGTH_SHORT).show()
            val telegram = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=org.telegram.messenger"))
            startActivity(telegram)
        }


    }

    @Composable
    fun AnimatedPreloader(modifier: Modifier = Modifier) {
        val preloaderLottieComposition by rememberLottieComposition(
            LottieCompositionSpec.RawRes(
                R.raw.anim3
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
private fun OPTIONSPreview() {
    OPTIONS(Modifier)
}}


