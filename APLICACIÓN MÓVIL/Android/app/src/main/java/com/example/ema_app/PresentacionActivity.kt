package com.example.ema_app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.ema_app.ui.theme.EMA_APPTheme

class PresentacionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            enableEdgeToEdge()
            // A surface container using the 'background' color from the theme
            EMA_APPTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                }
            }
               Presentacion()
            }

    }
}


@Composable
fun Presentacion(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.imagen11),
            contentDescription = "imagen1 1",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize())

        Box(
            modifier = Modifier
                .offset(y = 150.dp)
                .background(MaterialTheme.colorScheme.background.copy(alpha = 0.5f))
                .height(100.dp)
                .fillMaxSize()
                .align(Alignment.TopCenter)

        ){
            Image(
                painter = painterResource(id = R.drawable.cat1),
                contentDescription = "logo sgc",
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .align(Alignment.Center)
                    .height(100.dp)
                    .offset(y = (-70).dp)
            )
            Text(
                text = "Bienvenid@!",
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 50.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                ),
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
        Box(
            modifier = Modifier
                .offset(y = 100.dp)
                .background(MaterialTheme.colorScheme.background.copy(alpha = 0.7f))
                .fillMaxHeight(0.6f)
                .fillMaxSize(0.9f)
                .align(Alignment.Center)
        ){
            Text(
                text = "Este proyecto se encuentra en desarrollo constante, si presenta algún problema o tiene alguna sugerencia, por favor comuníquese con el equipo de desarrollo al correo:\n\n" +
                        "daocampo@sgc.gov.co.",
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Justify
                ),
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .width(300.dp)
                    .offset(y = 70.dp)
            )

        }
        CheckboxMinimalExample( Modifier.align(Alignment.BottomEnd).offset(x = (-70).dp, y = (-100).dp))

    }

}


@Composable
fun CheckboxMinimalExample(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var checked by remember { mutableStateOf(false) }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            "No volver a mostrar"
        )
        Checkbox(
            checked = checked,
            onCheckedChange = { checked = it }
        )
        Button(
            modifier = Modifier,
            onClick = {
                if (checked) {
                    context.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE).edit().putBoolean("isFirstRun", false).apply()
                    val intent = Intent(context, MainActivity::class.java)
                    context.startActivity(intent)
                    (context as AppCompatActivity).finish()
                }
                else{
                    context.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE).edit().putBoolean("isFirstRun", true).apply()
                    val intent = Intent(context, MainActivity::class.java)
                    context.startActivity(intent)
                    (context as AppCompatActivity).finish()
                }
            },
        ) {
            Text("Continuar")
        }
    }

}


@Preview(showBackground = true)
@Composable
fun PreviewPresentacion() {
    Presentacion()
}