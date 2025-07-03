package com.example.ema_app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
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
import com.example.ema_app.ui.theme.EMA_APPTheme

var QualityDB = mutableListOf("Calidad de agua:")
var DistanciaDB = mutableListOf("Distancia:")
var fechaDB = mutableListOf("Fecha y hora:")
var aceleracionDB = mutableListOf("Aceleración:")
var magnetometroDB = mutableListOf("Magnetometro:")
var temperaturaDB = mutableListOf("Temperatura:")
var lluviaDB = mutableListOf("Lluvia:")

var datosTotal = mutableListOf(fechaDB, DistanciaDB, QualityDB )
class MainActivity8 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EMA_APPTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    connectToDatabase()?.let {
                        val statement = it.createStatement()
                        val resultSet = statement.executeQuery("SELECT fecha,${sensoresDB.toString().removePrefix("[").removeSuffix("]")} FROM sensador WHERE fecha >= '${fecha1.value.toString()}' and fecha <= '${fecha2.value.toString()}' order by fecha desc LIMIT 10")
                        var pruebas = 0
                        QualityDB.clear()
                        DistanciaDB.clear()
                        fechaDB.clear()
                        aceleracionDB.clear()
                        magnetometroDB.clear()
                        temperaturaDB.clear()
                        lluviaDB.clear()
                        QualityDB.add("Calidad de agua:")
                        DistanciaDB.add("Distancia:")
                        fechaDB.add("Fecha y hora:")
                        aceleracionDB.add("Aceleración:")
                        magnetometroDB.add("Magnetometro:")
                        temperaturaDB.add("Temperatura:")
                        lluviaDB.add("Lluvia:")
                        datosTotal.clear()
                        while (resultSet.next()) {
                                if (sensoresDB.contains("temp")){
                                    temperaturaDB.add(resultSet.getString("temp"))
                                }
                                if (sensoresDB.contains("dist")){
                                    DistanciaDB.add(resultSet.getString("dist"))
                                }
                                if (sensoresDB.contains("acelx")){
                                    aceleracionDB.add(resultSet.getString("acelx"))
                                }
                                if (sensoresDB.contains("magx")){
                                    magnetometroDB.add(resultSet.getString("magx"))
                                }
                                if (sensoresDB.contains("LoRa")){
                                    QualityDB.add(resultSet.getString("LoRa"))
                                }
                                if (sensoresDB.contains("pluv")){
                                    lluviaDB.add(resultSet.getString("pluv"))
                                }
                                fechaDB.add(resultSet.getString("fecha"))
                            Log.d("TestDB", "iteracion: $pruebas")
                            pruebas++
                        }

                        if(fechaDB.size > 1){
                            datosTotal.add(fechaDB)
                        }
                        if(DistanciaDB.size > 1){
                            datosTotal.add(DistanciaDB)
                        }
                        if(QualityDB.size > 1){
                            datosTotal.add(QualityDB)
                        }
                        if(aceleracionDB.size > 1){
                            datosTotal.add(aceleracionDB)
                        }
                        if(magnetometroDB.size > 1){
                            datosTotal.add(magnetometroDB)
                        }
                        if(temperaturaDB.size > 1){
                            datosTotal.add(temperaturaDB)
                        }
                        if(lluviaDB.size > 1){
                            datosTotal.add(lluviaDB)
                        }


                        Log.d("TestDB", "QualityDB1: $QualityDB")
                        Log.d("TestDB", "DistanciaDB1: $DistanciaDB")
                    }
                    DBRESULTVALUES(Modifier)
                }
            }
        }
    }
}


private fun buttonClicked1(context: Context){
    val navigate = Intent(context, Reportes::class.java)
    context.startActivity(navigate)
}


@Composable
fun DBRESULTVALUES(modifier: Modifier = Modifier) {
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
        ) {
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .fillMaxWidth(0.95f)
                    .fillMaxHeight(0.9f)
                    .clip(shape = RoundedCornerShape(34.dp))
                    .background(color = Color(0xE6f7e9e9))
            ) {
                Text(
                    text = "Previsualización",
                    color = Color(0xff2d0c57),
                    textAlign = TextAlign.Center,
                    lineHeight = 1.21.em,
                    style = TextStyle(
                        fontSize = 34.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.41.sp
                    ),
                    modifier = Modifier
                        .align(alignment = Alignment.TopCenter)
                        .offset(
                            x = 0.dp,
                            y = 50.dp
                        )
                )

                Row(
                    modifier = Modifier
                        .align(alignment = Alignment.TopCenter)
                        .offset(
                            y = 130.dp
                        )
                ) {
                    Text(
                        text = "datos del "+ fecha1.value+" al "+fecha2.value,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        lineHeight = 2.73.em,
                        style = TextStyle(
                            fontSize = 15.sp,
                            letterSpacing = 0.41.sp
                        ),
                        modifier = Modifier.padding(1.dp)
                    )
                }
                Row(
                    modifier = Modifier
                        .align(alignment = Alignment.TopCenter)
                        .offset(
                            x = 0.dp,
                            y = 160.dp
                        ),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){

                }

            }

        }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .fillMaxWidth(0.95f)
                .fillMaxHeight(0.55f)
                .offset(
                    x = 0.dp,
                    y = 270.dp
                )

        ){
            MainContent(
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)

            )
        }


        Button(
            onClick = { buttonClicked1(context) },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xe0d793e6)),
            modifier = Modifier
                .align(alignment = Alignment.BottomCenter)
                .fillMaxWidth(0.8f)
                .fillMaxHeight(0.07f)
                .offset(
                    x = 0.dp,
                    y = (-50).dp
                )
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 374.dp)
                    .requiredHeight(height = 56.dp)
            ) {
                Text(
                    text = "REPORTE DE DATOS",
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
fun MainContent( modifier: Modifier = Modifier) {
    val scrollStateHorizontal = rememberScrollState()
    val scrollStateVertical = rememberScrollState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(state = scrollStateVertical)
            .horizontalScroll(state = scrollStateHorizontal)
        
    ) {

        repeat( datosTotal[0].size){ c ->
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.33f)
                    .align(alignment = Alignment.Start),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                repeat( datosTotal.size){ r ->
                    Box(
                        modifier = Modifier
                            .width(width = 120.dp)
                            .height(height = 40.dp),
                        contentAlignment = Alignment.Center
                    ) {
                    Item( datosTotal[r][c])}
                }
            }
        }

    }
}

@Composable
fun Item(text: String) {
    Text(
        modifier = Modifier
            .padding(0.dp, 0.dp, 0.dp, 2.dp),
        textAlign = TextAlign.Center,
        style = TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold

        ),
        text = text,
        color = Color.Black

    )
    HorizontalDivider(
        modifier = Modifier
            .offset(
                x = 10.dp,
                y = 20.dp
            ),
        color = Color.Black,
        thickness = 1.dp

    )
}

@Preview(widthDp = 414, heightDp = 896)
@Composable
private fun DBRESULTVALUESPreview() {
    DBRESULTVALUES(Modifier)
}