package com.example.ema_app

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.collection.mutableFloatListOf
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
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
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.csvconvertor.CsvConverter
import com.example.ema_app.ui.theme.EMA_APPTheme
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import com.itextpdf.text.Document
import com.itextpdf.text.Element
import com.itextpdf.text.PageSize
import com.itextpdf.text.pdf.PdfPTable
import com.itextpdf.text.pdf.PdfWriter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.File
import java.io.FileOutputStream


val alphaloadingreportes = mutableStateOf(0.0f)
val botonreportes = mutableStateOf(true)
class Reportes : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        if (checkPermissions()) {
            setContent {
                EMA_APPTheme {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        Reports(
                            name = "Android",
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
        //Otherwise request permission
        else {
            requestPermissions();
        }

    }

    @Deprecated("This method has been deprecated in favor of using the Activity Result API\n      which brings increased type safety via an {@link ActivityResultContract} and the prebuilt\n      contracts for common intents available in\n      {@link androidx.activity.result.contract.ActivityResultContracts}, provides hooks for\n      testing, and allow receiving results in separate, testable classes independent from your\n      activity. Use\n      {@link #registerForActivityResult(ActivityResultContract, ActivityResultCallback)} passing\n      in a {@link RequestMultiplePermissions} object for the {@link ActivityResultContract} and\n      handling the result in the {@link ActivityResultCallback#onActivityResult(Object) callback}.")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
        } else {
            setContent {
                EMA_APPTheme {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        Reports(
                            name = "Android",
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(READ_EXTERNAL_STORAGE),
            1
        )
    }
    private fun checkPermissions(): Boolean {
        val result = ContextCompat.checkSelfPermission(applicationContext, READ_EXTERNAL_STORAGE)
        return result == PackageManager.PERMISSION_GRANTED
    }


}

@Composable
fun Reports(name: String, modifier: Modifier = Modifier) {
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
                    y = 70.dp
                )
        ) {
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .fillMaxWidth(0.95f)
                    .fillMaxHeight(0.8f)
                    .clip(shape = RoundedCornerShape(topStart = 34.dp, topEnd = 34.dp))
                    .background(color = Color(0xE6f7e9e9))

            ) {
                Text(
                    text = "Reportes",
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
                AnimatedPreloader(modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.Center)
                    .offset(x = 0.dp, y = (-130).dp),R.raw.animdb)
                Row(
                    modifier = Modifier
                        .align(alignment = Alignment.Center)
                        .offset(
                            y = 0.dp
                        )
                ) {
                    ElevatedButton(
                        onClick = {exportCSV( context)},
                        enabled = botonreportes.value,
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xa0d793e6)),
                        modifier = Modifier

                            .offset(x = 0.dp, y = 0.dp)
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "CSV",
                            color = Color(0xff2d0c57),
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 0.41.sp
                            )
                        )
                    }
                    ElevatedButton(
                        onClick = {exportPDF( context)},
                        enabled = botonreportes.value,
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xa0d793e6)),
                        modifier = Modifier

                            .offset(x = 0.dp, y = 0.dp)
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "PDF",
                            color = Color(0xff2d0c57),
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 0.41.sp
                            )
                        )
                    }
                }

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
                        .fillMaxSize(0.5f)
                        .align(Alignment.BottomCenter)
                        .alpha(alphaloadingreportes.value)
                )

            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    EMA_APPTheme {
        Reports("Android")
    }
}

fun exportCSV(context : Context) {

    alphaloadingreportes.value=1.0f
    botonreportes.value=false
GlobalScope.launch {
    val path = context.filesDir

    val letDirectory = File(path, "LET")
    letDirectory.mkdirs()
    val file = File(letDirectory, "reportes.csv")
    file.delete()
    file.createNewFile()

    FileOutputStream(file).use {
        connectToDatabase()?.let {
            val statement = it.createStatement()
            val resultSet = statement.executeQuery("SELECT fecha,${sensoresDB.toString().removePrefix("[").removeSuffix("]")} FROM sensador WHERE fecha >= '${fecha1.value.toString()}' and fecha <= '${fecha2.value.toString()}' order by fecha desc")
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
            Log.d("TestDB43", "sizeSensoresDB: ${sensoresDB.toString()}")
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
            Log.d("TestDB43", "size: ${datosTotal.size}")
        }
        if(sensoresDB.contains("dist")){
            sensoresDB.remove("dist")
            sensoresDB.add("Distancia")
        }
        if(sensoresDB.contains("LoRa")){
            sensoresDB.remove("LoRa")
            sensoresDB.add("Calidad de agua")
        }
        if(sensoresDB.contains("acelx")){
            sensoresDB.remove("acelx")
            sensoresDB.add("Aceleración")
        }
        if(sensoresDB.contains("magx")){
            sensoresDB.remove("magx")
            sensoresDB.add("Magnetometro")
        }
        if(sensoresDB.contains("temp")){
            sensoresDB.remove("temp")
            sensoresDB.add("Temperatura")
        }
        if(sensoresDB.contains("pluv")){
            sensoresDB.remove("pluv")
            sensoresDB.add("Lluvia")
        }
        Log.d("TestDv1", "sensoresDB: $sensoresDB")

        it.write("Fecha y hora,${sensoresDB.toString().removePrefix("[").removeSuffix("]")}\n".toByteArray())
        //it.write("Fecha y hora,Distancia,Calidad de agua\n".toByteArray())
        for (i in 1 until fechaDB.size) {
            it.write(fechaDB[i].toByteArray())
            for (j in 0 until  datosTotal.size){
                it.write(",${datosTotal[j][i]}".toByteArray())
            }
            it.write("\n".toByteArray())
        }
    }
    try {
        if(file.exists()) {
            val uri = FileProvider.getUriForFile(context, context.applicationContext.packageName + ".provider", file)
            val intent = Intent(Intent.ACTION_SEND)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            intent.setType("*/*")
            intent.putExtra(Intent.EXTRA_STREAM, uri)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent)
        }
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
    }
    if(sensoresDB.contains("Distancia")) {
        sensoresDB.remove("Distancia")
        sensoresDB.add("dist")
    }
    if(sensoresDB.contains("Calidad de agua")) {
        sensoresDB.remove("Calidad de agua")
        sensoresDB.add("LoRa")
    }
    if(sensoresDB.contains("Aceleración")) {
        sensoresDB.remove("Aceleración")
        sensoresDB.add("acelx")
    }
    if(sensoresDB.contains("Magnetometro")) {
        sensoresDB.remove("Magnetometro")
        sensoresDB.add("magx")
    }
    if(sensoresDB.contains("Temperatura")) {
        sensoresDB.remove("Temperatura")
        sensoresDB.add("temp")
    }
    if(sensoresDB.contains("Lluvia")) {
        sensoresDB.remove("Lluvia")
        sensoresDB.add("pluv")
    }


    alphaloadingreportes.value=0.0f
    botonreportes.value=true
}

}


fun exportPDF(context : Context) {

    alphaloadingreportes.value=1.0f
    botonreportes.value=false

    GlobalScope.launch {
        val path = context.filesDir


        val letDirectory = File(path, "LET")
        letDirectory.mkdirs()
        val file = File(letDirectory, "reportes.pdf")
        file.delete()
        file.createNewFile()

        FileOutputStream(file).use {
            connectToDatabase()?.let {
                val statement = it.createStatement()
                val resultSet = statement.executeQuery(
                    "SELECT fecha,${
                        sensoresDB.toString().removePrefix("[").removeSuffix("]")
                    } FROM sensador WHERE fecha >= '${fecha1.value.toString()}' and fecha <= '${fecha2.value.toString()}' order by fecha desc"
                )
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
                    if (sensoresDB.contains("temp")) {
                        temperaturaDB.add(resultSet.getString("temp"))
                    }
                    if (sensoresDB.contains("dist")) {
                        DistanciaDB.add(resultSet.getString("dist"))
                    }
                    if (sensoresDB.contains("acelx")) {
                        aceleracionDB.add(resultSet.getString("acelx"))
                    }
                    if (sensoresDB.contains("magx")) {
                        magnetometroDB.add(resultSet.getString("magx"))
                    }
                    if (sensoresDB.contains("LoRa")) {
                        QualityDB.add(resultSet.getString("LoRa"))
                    }
                    if (sensoresDB.contains("pluv")) {
                        lluviaDB.add(resultSet.getString("pluv"))
                    }
                    fechaDB.add(resultSet.getString("fecha"))
                    Log.d("TestDB", "iteracion: $pruebas")
                    pruebas++
                }


                if (DistanciaDB.size > 1) {
                    datosTotal.add(DistanciaDB)
                }
                if (QualityDB.size > 1) {
                    datosTotal.add(QualityDB)
                }
                if (aceleracionDB.size > 1) {
                    datosTotal.add(aceleracionDB)
                }
                if (magnetometroDB.size > 1) {
                    datosTotal.add(magnetometroDB)
                }
                if (temperaturaDB.size > 1) {
                    datosTotal.add(temperaturaDB)
                }
                if (lluviaDB.size > 1) {
                    datosTotal.add(lluviaDB)
                }
                Log.d("TestDB", "QualityDB1: $QualityDB")
                Log.d("TestDB", "DistanciaDB1: $DistanciaDB")
            }

            if (sensoresDB.contains("dist")) {
                sensoresDB.remove("dist")
                sensoresDB.add("Distancia")
            }
            if (sensoresDB.contains("LoRa")) {
                sensoresDB.remove("LoRa")
                sensoresDB.add("Calidad de agua")
            }
            if (sensoresDB.contains("acelx")) {
                sensoresDB.remove("acelx")
                sensoresDB.add("Aceleración")
            }
            if (sensoresDB.contains("magx")) {
                sensoresDB.remove("magx")
                sensoresDB.add("Magnetometro")
            }
            if (sensoresDB.contains("temp")) {
                sensoresDB.remove("temp")
                sensoresDB.add("Temperatura")
            }
            if (sensoresDB.contains("pluv")) {
                sensoresDB.remove("pluv")
                sensoresDB.add("Lluvia")
            }

            val document = Document()
            document.setMargins(24f, 24f, 32f, 32f)
            document.pageSize = PageSize.A4

            var pdf = PdfWriter.getInstance(document, FileOutputStream(file))
            pdf.setFullCompression()
            document.open()
            document.addTitle("Reporte de datos")
            document.addSubject("Reporte de datos")
            document.addKeywords("Reporte de datos")
            document.addAuthor("EMA")
            document.addCreator("EMA")
            document.addCreationDate()
            var tablewith = FloatArray(sensoresDB.size + 1)
            for (i in 0 until sensoresDB.size + 1) {
                tablewith[i] = 2f
            }
            val table = createTable(sensoresDB.size + 1, tablewith)
            table.addCell("Fecha y hora")
            for (i in 0 until sensoresDB.size) {
                table.addCell(sensoresDB[i])
            }
            for (i in 1 until fechaDB.size) {
                table.addCell(fechaDB[i])
                for (j in 0 until datosTotal.size) {
                    table.addCell(datosTotal[j][i])
                }
            }
            document.add(table)
            document.close()
            pdf.close()
        }
        try {
            if (file.exists()) {
                val uri = FileProvider.getUriForFile(
                    context,
                    context.applicationContext.packageName + ".provider",
                    file
                )
                val intent = Intent(Intent.ACTION_SEND)
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                intent.setType("*/*")
                intent.putExtra(Intent.EXTRA_STREAM, uri)
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent)
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
        }
        if(sensoresDB.contains("Distancia")) {
            sensoresDB.remove("Distancia")
            sensoresDB.add("dist")
        }
        if(sensoresDB.contains("Calidad de agua")) {
            sensoresDB.remove("Calidad de agua")
            sensoresDB.add("LoRa")
        }
        if(sensoresDB.contains("Aceleración")) {
            sensoresDB.remove("Aceleración")
            sensoresDB.add("acelx")
        }
        if(sensoresDB.contains("Magnetometro")) {
            sensoresDB.remove("Magnetometro")
            sensoresDB.add("magx")
        }
        if(sensoresDB.contains("Temperatura")) {
            sensoresDB.remove("Temperatura")
            sensoresDB.add("temp")
        }
        if(sensoresDB.contains("Lluvia")) {
            sensoresDB.remove("Lluvia")
            sensoresDB.add("pluv")
        }
        alphaloadingreportes.value = 0.0f
        botonreportes.value=true

    }

}


fun createTable(column: Int, columnWidth: FloatArray): PdfPTable {
    val table = PdfPTable(column)
    table.widthPercentage = 100f
    table.setWidths(columnWidth)
    table.headerRows = 1
    table.defaultCell.verticalAlignment = Element.ALIGN_CENTER
    table.defaultCell.horizontalAlignment = Element.ALIGN_CENTER
    return table
}

