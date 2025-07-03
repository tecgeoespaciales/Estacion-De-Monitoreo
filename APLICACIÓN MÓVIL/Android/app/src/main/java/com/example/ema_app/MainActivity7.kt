package com.example.ema_app

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.ema_app.ui.theme.EMA_APPTheme



val sensors = listOf(
    "Aceleracion",
    "Magnetometro",
    "Temperatura",
    "Lluvia",
    "Distancia",
    "Calidad de agua"
)

val ema34 = listOf(
    "Distancia",
    "Calidad de agua"
)
val child34 = mutableStateListOf(false, false)
var childCheckedStates = mutableStateListOf(false, false, false, false, false, false)

var fecha1 = mutableStateOf("")
var fecha2 = mutableStateOf("")
val sensoresDB= mutableListOf("")





class MainActivity7 : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EMA_APPTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DBRESULT(Modifier)
                }
            }
        }
    }
}

private fun buttonClicked1(context: Context){

    if (fecha1.value == "" || fecha2.value == ""){
        Toast.makeText(context, "Por favor seleccione las fechas", Toast.LENGTH_SHORT).show()
        return
    }
    if (fecha1.value > fecha2.value){
        Toast.makeText(context, "La fecha inicial no puede ser mayor a la final", Toast.LENGTH_SHORT).show()
        return
    }
    if (fecha1.value == fecha2.value){
        Toast.makeText(context, "La fecha inicial no puede ser igual a la final", Toast.LENGTH_SHORT).show()
        return
    }
    if (childCheckedStates.none { it }){
        Toast.makeText(context, "Por favor seleccione al menos un sensor", Toast.LENGTH_SHORT).show()
        return
    }


    consultaBaseDatos(context = context, fecha1.value, fecha2.value)
}

fun consultaBaseDatos(context: Context, fecha1: String, fecha2: String){
    connectToDatabase()?.let {
        val statement = it.createStatement()
        Log.d("Test5", "Connected to database")
        if (nombreVersion.value == "3.4"){
            sensoresDB.clear()
            for (e in ema34){
                if (child34[ema34.indexOf(e)]) {
                    sensoresDB.add(e)
                }
            }
        } else {
            sensoresDB.clear()
            for (e in sensors){
                if (childCheckedStates[sensors.indexOf(e)]) {
                    sensoresDB.add(e)
                }
            }

        }
        Log.d("TestDB2", sensoresDB.toString())
        if (sensoresDB.contains("Distancia")){
            sensoresDB.remove("Distancia")
            sensoresDB.add("dist")
        }
        if (sensoresDB.contains("Calidad de agua")){
            sensoresDB.remove("Calidad de agua")
            sensoresDB.add("LoRa")
        }
        if (sensoresDB.contains("Aceleracion")){
            sensoresDB.remove("Aceleracion")
            sensoresDB.add("acelx")
        }
        if (sensoresDB.contains("Magnetometro")){
            sensoresDB.remove("Magnetometro")
            sensoresDB.add("magx")
        }
        if (sensoresDB.contains("Temperatura")){
            sensoresDB.remove("Temperatura")
            sensoresDB.add("temp")
        }
        if (sensoresDB.contains("Lluvia")){
            sensoresDB.remove("Lluvia")
            sensoresDB.add("pluv")
        }
        val resultSet = statement.executeQuery("SELECT ${sensoresDB.toString().removePrefix("[").removeSuffix("]")} FROM sensador WHERE fecha >= '${fecha1}' and fecha <= '${fecha2}' order by fecha desc LIMIT 5")
        Log.d("Test5", "Query executed")
        if (resultSet.next()) {
            val navigate = Intent(context, MainActivity8::class.java)
            context.startActivity(navigate)
            }

        else {
            Toast.makeText(context, "No se encontraron datos", Toast.LENGTH_SHORT).show()
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DBRESULT(modifier: Modifier = Modifier) {
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
                    .align(alignment = Alignment.Center)
                    .clip(shape = RoundedCornerShape(34.dp))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .fillMaxHeight(0.7f)
                        .background(color = Color(0xE6f7e9e9))
                        .clip(shape = RoundedCornerShape(34.dp)))

                CheckboxParentExample(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 70.dp,
                            y = 0.dp
                        ))
            }
            Text(
                text = "ID: "+nombreVersion.value,
                color = Color(0xff2d0c57),
                textAlign = TextAlign.Center,
                lineHeight = 1.21.em,
                style = TextStyle(
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.41.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(
                        x = 0.dp,
                        y = 20.dp
                    ))
            val datePickerState1 = rememberDatePickerState(initialDisplayMode = DisplayMode.Input)
            DatePickerWithDialog1(modifier = Modifier
                .align(alignment = Alignment.Center)
                .offset(x = (70).dp, y = 150.dp))
            val datePickerState2 = rememberDatePickerState(initialDisplayMode = DisplayMode.Input)
            DatePickerWithDialog2(modifier = Modifier
                .align(alignment = Alignment.Center)
                .offset(x = (70).dp, y = 180.dp))

            Text(
                text = "fecha inicial:",
                color = Color.Black,
                lineHeight = 2.73.em,
                style = TextStyle(
                    fontSize = 12.sp,
                    letterSpacing = 0.41.sp),
                modifier = Modifier
                    .align(alignment = Alignment.Center)
                    .offset(
                        x = (-70).dp,
                        y = 150.dp
                    ))
            Text(
                text = "fecha final:",
                color = Color.Black,
                lineHeight = 2.73.em,
                style = TextStyle(
                    fontSize = 12.sp,
                    letterSpacing = 0.41.sp),
                modifier = Modifier
                    .align(alignment = Alignment.Center)
                    .offset(
                        x = (-70).dp,
                        y = 180.dp
                    ))
            Button(
                onClick = { buttonClicked1(context) },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xe0d793e6)),
                modifier = Modifier
                    .align(alignment = Alignment.BottomCenter)
                    .offset(
                        x = 0.dp,
                        y = (-20).dp
                    )
                    .fillMaxWidth(0.8f)
                    .fillMaxHeight(0.07f)
            ) {
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 374.dp)
                        .requiredHeight(height = 56.dp)
                ) {
                    Text(
                        text = "CONSULTAR DATOS",
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
}



@SuppressLint("RememberReturnType")
@Composable
fun CheckboxParentExample(modifier: Modifier) {
    if (nombreVersion.value == "3.4"){
        childCheckedStates = child34
    }
    // Initialize states for the child checkboxes


    // Compute the parent state based on children's states
    val parentState = when {
        childCheckedStates.all { it } -> ToggleableState.On
        childCheckedStates.none { it } -> ToggleableState.Off
        else -> ToggleableState.Indeterminate
    }

    Column(modifier = modifier
        .offset(x = 0.dp, y = 40.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        // Parent TriStateCheckbox
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text("Seleccionar todos",color = Color.Black, style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold))
            TriStateCheckbox(
                state = parentState,
                onClick = {
                    // Determine new state based on current state
                    val newState = parentState != ToggleableState.On
                    childCheckedStates.forEachIndexed { index, _ ->
                        childCheckedStates[index] = newState
                    }
                }
            )

        }
        // Child Checkboxes
        childCheckedStates.forEachIndexed { index, checked ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.align(Alignment.End)
            ) {
                if (nombreVersion.value == "3.4"){
                    Text(ema34[index], color = Color.Black, style = TextStyle(fontSize = 20.sp))
                }
                else{Text(sensors[index], color = Color.Black, style = TextStyle(fontSize = 20.sp))}
                Checkbox(
                    checked = checked,
                    onCheckedChange = { isChecked ->
                        // Update the individual child state
                        childCheckedStates[index] = isChecked
                    }
                )
            }
        }
    }

    if (childCheckedStates.all { it }) {
        Text("All options selected")
    }
}







@RequiresApi(Build.VERSION_CODES.O)
@Preview(widthDp = 414, heightDp = 896)
@Composable
private fun DBRESULTPreview() {
    DBRESULT(Modifier)
}




@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable



fun DatePickerWithDialog1(

    modifier: Modifier = Modifier
) {
    val dateState = rememberDatePickerState( yearRange = 2023..2025)
    val millisToLocalDate = dateState.selectedDateMillis?.let {

        DateUtils().convertMillisToLocalDate(it)
    }
    val dateToString = millisToLocalDate?.let {
        DateUtils().dateToString(millisToLocalDate)

    } ?: "Seleccione fecha"
    var showDialog by remember { mutableStateOf(false) }
    Box(modifier = modifier

)
    {
            Text(
                modifier = Modifier
                    .clickable(onClick = {
                        showDialog = true
                    }),
                text = dateToString,
                textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        color = Color.DarkGray,
            )
            if (showDialog) {
                    DatePickerDialog(

                        onDismissRequest = { showDialog = false },
                        confirmButton = {
                            Button(
                                modifier = Modifier
                                    .padding(end = 20.dp)
                                    .fillMaxWidth(0.3f),
                                onClick = { showDialog = false
                                    fecha1.value = millisToLocalDate.toString()
                                    Log.d("Test1", fecha1.value)
                                }
                            ) {
                                Text(text = "OK")
                            }
                        },
                        dismissButton = {
                            Button(
                                onClick = { showDialog = false }
                            ) {
                                Text(text = "Cancel")
                            }
                        }
                    ) {
                        DatePicker(
                            state = dateState,
                            showModeToggle = true
                        )
                    }

            }

    }
}


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerWithDialog2(

    modifier: Modifier = Modifier
) {
    val dateState = rememberDatePickerState( yearRange = 2023..2025)
    val millisToLocalDate = dateState.selectedDateMillis?.let {

        DateUtils().convertMillisToLocalDate(it)
    }
    val dateToString = millisToLocalDate?.let {
        DateUtils().dateToString(millisToLocalDate)

    } ?: "Seleccione fecha"
    var showDialog by remember { mutableStateOf(false) }
    Box(modifier = modifier

    )
    {
        Text(
            modifier = Modifier
                .clickable(onClick = {
                    showDialog = true
                }),
            text = dateToString,
            textAlign = TextAlign.Center,
            fontSize = 12.sp,
            color = Color.DarkGray,
        )
        if (showDialog) {
            DatePickerDialog(

                onDismissRequest = { showDialog = false },
                confirmButton = {
                    Button(
                        modifier = Modifier
                            .padding(end = 20.dp)
                            .fillMaxWidth(0.3f),
                        onClick = { showDialog = false
                            fecha2.value = millisToLocalDate.toString()
                            Log.d("Test1", millisToLocalDate.toString())
                        }
                    ) {
                        Text(text = "OK")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = { showDialog = false }
                    ) {
                        Text(text = "Cancel")
                    }
                }
            ) {
                DatePicker(
                    state = dateState,
                    showModeToggle = true
                )
            }

        }

    }
}






