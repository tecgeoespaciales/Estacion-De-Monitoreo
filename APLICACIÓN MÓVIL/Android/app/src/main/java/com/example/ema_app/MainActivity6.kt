package com.example.ema_app

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.ema_app.ui.theme.EMA_APPTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

var internetCon = false
val intButtState = mutableStateOf(false)
var userdb = mutableStateOf("ema")
var passdb = mutableStateOf("clave")

class MainActivity6 : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fun isOnline(): Boolean {
            val connMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo: NetworkInfo? = connMgr.activeNetworkInfo
            return networkInfo?.isConnected == true
        }
        if (!isOnline()) {
            Toast.makeText(this, "No hay conexión a internet", Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(this, "Conexión a internet establecida", Toast.LENGTH_LONG).show()
            internetCon = true
            intButtState.value = true
        }

        setContent {
            EMA_APPTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DBSEARCH(Modifier)
                }
            }
        }

    }
}

fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    } else @Suppress("deprecation") {
        val activeNetwork = connectivityManager.activeNetworkInfo ?: return false
        return activeNetwork.isConnectedOrConnecting
    }
}

private fun buttonClicked1(context: Context) {
    Log.d("Test1", "Button clicked")
    connectToDatabase()?.let {
        Log.d("Test1", "Connected to database")
        val statement = it.createStatement()
        val resultSet = statement.executeQuery("SELECT * FROM dispositivos WHERE id = '${userdb.value}' AND clave = '${passdb.value}'")
        Log.d("Test2", userdb.value)
        Log.d("Test2", passdb.value)
        if (resultSet.next()) {
            Log.d("Test1", "User found")
            Toast.makeText(context, "Usuario encontrado", Toast.LENGTH_LONG).show()
            val navigate = Intent(context, MainActivity7::class.java)
            context.startActivity(navigate)
        } else {
            Log.d("Test1", "User not found")
            Toast.makeText(context, "Usuario no encontrado", Toast.LENGTH_LONG).show()
        }
    }

}








fun isConnected(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (connectivityManager != null) {
        Log.d("Test1", "Hay conexión")
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            Log.d("Test1", "Hay conexión a internet1")
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                return true
            }
        }
    }
    else{
        Log.d("Test1", "No hay conexión")
    }
    return false
}

fun connectToDatabase(): Connection? {
    val url = "jdbc:mysql://138.128.244.81:3306/emaMonitoreo"
    val user = "root"
    val password = "EmaSGC-2024"
    return try {

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        DriverManager.getConnection(url, user, password)
    } catch (e: SQLException) {
        null
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DBSEARCH(modifier: Modifier = Modifier) {
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
                .offset(x = 0.dp,
                    y = 100.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .fillMaxHeight(0.7f)
                    .align(alignment = Alignment.Center)
                    .clip(shape = RoundedCornerShape(34.dp))
                    .background(color = Color(0xE6f7e9e9)))
            Text(
                text = "BÚSQUEDA POR ID",
                color = Color(0xff2d0c57),
                textAlign = TextAlign.Center,
                lineHeight = 1.21.em,
                style = TextStyle(
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.41.sp),
                modifier = Modifier
                    .align(alignment = Alignment.Center)
                    .offset(
                        x = 0.dp,
                        y = (-120).dp
                    )
                    .requiredWidth(width = 326.dp)
                    .requiredHeight(height = 80.dp)
                    .wrapContentHeight(align = Alignment.CenterVertically))
        }
        var IDDB by remember { mutableStateOf("ema") }
        OutlinedTextField(
            textStyle = TextStyle(
                fontSize = 15.sp,
                letterSpacing = (-0.01).sp,
                color = Color.Black),
            value = IDDB,
            onValueChange = { IDDB = it
                userdb.value=IDDB
                Log.d("CambioLabel", wifitF.value)},
            label = { Text("Identificador") },
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .offset(
                    x = 0.dp,
                    y = 100.dp
                ),

            )
        var Clave by remember { mutableStateOf("clave") }
        OutlinedTextField(
            textStyle = TextStyle(
                fontSize = 15.sp,
                letterSpacing = (-0.01).sp,
                color = Color.Black),
            value = Clave,
            onValueChange = { Clave = it
                passdb.value=Clave
                Log.d("CambioLabel", wifitF.value)},
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .offset(
                    x = 0.dp,
                    y = 200.dp
                ),

            )


        AnimatedPreloader(modifier = Modifier.size(100.dp).align(Alignment.Center).offset(x = 0.dp, y = (-100).dp),R.raw.animdb)
        Button(
            onClick = { buttonClicked1(context) },
            enabled = intButtState.value,
            shape = RoundedCornerShape(5.dp, 5.dp, 5.dp, 5.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xe0d793e6)),
            modifier = Modifier
                .align(alignment = Alignment.BottomCenter)
                .offset(
                    x = 0.dp,
                    y = (-90).dp
                )
                .fillMaxWidth(0.5f)
                .fillMaxHeight(0.07f)
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 374.dp)
                    .requiredHeight(height = 56.dp)
            ) {
                Text(
                    text = "Ingresar",
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

    }

}

@Composable
fun AnimatedPreloader(modifier: Modifier = Modifier, anim: Int = R.raw.animdb) {
    val preloaderLottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            anim
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
private fun DBSEARCHPreview() {
    DBSEARCH(Modifier)
}