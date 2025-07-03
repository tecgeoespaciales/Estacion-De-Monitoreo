package com.example.ema_app

import android.annotation.SuppressLint
import android.app.Activity
import android.Manifest
import android.annotation.TargetApi
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothDevice.TRANSPORT_LE
import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCallback
import android.bluetooth.BluetoothGattCharacteristic
import android.bluetooth.BluetoothGattDescriptor
import android.bluetooth.BluetoothGattService
import android.bluetooth.BluetoothProfile
import android.bluetooth.le.BluetoothLeScanner
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.util.MutableFloat
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.collection.mutableObjectListOf
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.ema_app.ui.theme.EMA_APPTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.juul.kable.Bluetooth
import com.juul.kable.Scanner
import kotlinx.coroutines.delay
import java.util.UUID


val status = mutableStateOf("Dispositivos encontrados: \n\n")
val ipPort = mutableStateOf("192.168.43.54")
val tittleStatus = mutableStateOf("")
val devices = mutableListOf("Dispositivos Disponibles: \n")
val phisicDevices = mutableListOf<BluetoothDevice>()
val emas = mutableListOf("")
val ButtonStatus = mutableStateOf("Escanear")
var indexclick = 0
var buttonStateCon = mutableStateOf(true)
var buttonState = mutableStateOf(true)
var sensores = mutableListOf("0","0","0","0","0","0","0","0","0","0")
var controlEnvio = mutableStateOf(0);
val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
val alphaVal = mutableStateOf(0.2f)
lateinit var bluetoothGatt: BluetoothGatt

private const val PERMISSION_REQUEST_CODE = 1



fun Context.hasPermission(permissionType: String): Boolean {
    return ContextCompat.checkSelfPermission(this, permissionType) ==
            PackageManager.PERMISSION_GRANTED
}
fun Context.hasRequiredBluetoothPermissions(): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        hasPermission(Manifest.permission.BLUETOOTH_SCAN) &&
                hasPermission(android.Manifest.permission.BLUETOOTH_CONNECT)
    } else {
        hasPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)
    }
}
class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {


            EMA_APPTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    BLUETOOTH(Modifier) { startBleScan() }
                }
            }
        }

    }
    private fun startBleScan() {
        if (!hasRequiredBluetoothPermissions()) {
            requestRelevantRuntimePermissions()
        } else { buttonClicked1(this) }
    }


    private fun Activity.requestRelevantRuntimePermissions() {
        if (hasRequiredBluetoothPermissions()) { return }
        when {
            Build.VERSION.SDK_INT < Build.VERSION_CODES.S -> {
                requestLocationPermission()
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
                requestBluetoothPermissions()
            }
        }
    }
    private fun requestLocationPermission() = runOnUiThread {
        AlertDialog.Builder(this)
            .setTitle("Ubicacíon Requerida")
            .setMessage(
                "Para interactuar con dispositivos Bluetooth, se requiere permiso para " +
                        "usar la ubicacion, por favor conceda este permiso."
            )
            .setCancelable(false)
            .setPositiveButton(android.R.string.ok) { _, _ ->
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    PERMISSION_REQUEST_CODE
                )
            }
            .show()
    }
    @RequiresApi(Build.VERSION_CODES.S)
    private fun requestBluetoothPermissions() = runOnUiThread {
        AlertDialog.Builder(this)
            .setTitle("Permiso bluetooth requerido")
            .setMessage(
                "Se requiere permiso para el uso del bluetooth" +
                        "por favor, conceda este permiso."
            )
            .setCancelable(false)
            .setPositiveButton(android.R.string.ok) { _, _ ->
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        Manifest.permission.BLUETOOTH_SCAN,
                        Manifest.permission.BLUETOOTH_CONNECT
                    ),
                    PERMISSION_REQUEST_CODE
                )
            }
            .show()
    }
}
@SuppressLint("MissingPermission", "SuspiciousIndentation")
private fun buttonClicked1(context: Context){

    val scanCallback = object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult) {
            super.onScanResult(callbackType, result)
            if (ButtonStatus.value == "Escanear") {
                for (device in devices) {
                    if (device.contains("EMA") && !emas.contains(device)) {
                        emas.add(device)
                    }
                }
                devices.clear()
                devices.add("Dispositivos Disponibles: \n")
                for (device in emas) {
                    devices.add(device)

                }
                if (emas.isNotEmpty()) {
                    tittleStatus.value = "E.M.A. Encontradas:"
                    buttonStateCon.value = true
                }else{
                    tittleStatus.value = "No se encontraron E.M.A."
                }
            }
            else {
                tittleStatus.value = "Buscando Dispositivos..."
                buttonStateCon.value = false
                buttonState.value = false
                alphaVal.value = 0.2f
                emas.clear()
                val device = result.device

                if (!devices.contains(device.name + "\n") && device.name != null) {
                    if (device.name.contains("EMA")) {
                        phisicDevices.add(device)
                    }
                    devices.add(device.name + "\n")
                    status.value += devices.last()
                    Log.i("ScanCallback", "Scan result: ${result.device.name}")
                }
            }
        }

        override fun onScanFailed(errorCode: Int) {
            super.onScanFailed(errorCode)
            Log.e("ScanCallback", "Scan failed: $errorCode")
        }
    }

    val bluetoothLeScanner: BluetoothLeScanner? = bluetoothAdapter?.bluetoothLeScanner
    if (ButtonStatus.value == "Escanear") {
        bluetoothLeScanner?.startScan(scanCallback)
        ButtonStatus.value = "Detener"
        Toast.makeText(context, "Escaneando...", Toast.LENGTH_SHORT).show()
        Log.i("ScanCallback", "Scan started")

    }else if (ButtonStatus.value == "Detener"){
        bluetoothLeScanner?.stopScan(scanCallback)
        ButtonStatus.value = "Escanear"
        Toast.makeText(context, "Escaneo Detenido...", Toast.LENGTH_SHORT).show()
        Log.i("ScanCallback", "Scan stopped")
    }
}
private fun buttonClicked2(context: Context){
    val navigate = Intent(context, MainActivity3::class.java)
    context.startActivity(navigate)
    Log.d("Test", "Func Button 2")
}
private fun buttonClicked3(context: Context){
    val navigate = Intent(context, MainActivity4::class.java)
    context.startActivity(navigate)
}
fun BluetoothGattCharacteristic.isReadable(): Boolean =
    containsProperty(BluetoothGattCharacteristic.PROPERTY_READ)
fun BluetoothGattCharacteristic.isWritable(): Boolean =
    containsProperty(BluetoothGattCharacteristic.PROPERTY_WRITE)
fun BluetoothGattCharacteristic.isWritableWithoutResponse(): Boolean =
    containsProperty(BluetoothGattCharacteristic.PROPERTY_WRITE_NO_RESPONSE)
fun BluetoothGattCharacteristic.containsProperty(property: Int): Boolean {
    return properties and property != 0
}
private fun BluetoothGatt.printGattTable() {
    if (services.isEmpty()) {
        Log.i("printGattTable", "No service and characteristic available, call discoverServices() first?")
        return
    }
    services.forEach { service ->
        val characteristicsTable = service.characteristics.joinToString(
            separator = "\n|--",
            prefix = "|--"
        ) { it.uuid.toString() }
        Log.i("printGattTable", "\nService ${service.uuid}\nCharacteristics:\n$characteristicsTable"
        )
    }
}
private val gattCallback = object : BluetoothGattCallback() {
    override fun onMtuChanged(gatt: BluetoothGatt, mtu: Int, status: Int) {
        Log.w("BluetoothGattCallback", "ATT MTU changed to $mtu, success: ${status == BluetoothGatt.GATT_SUCCESS}")
        if (status == BluetoothGatt.GATT_SUCCESS) {
            val service: BluetoothGattService = gatt.getService(UUID.fromString("6e400001-b5a3-f393-e0a9-e50e24dcca9e"))
            val characteristic = service.getCharacteristic(UUID.fromString("6e400002-b5a3-f393-e0a9-e50e24dcca9e"))
            //characteristic.value = "Hello, BLE!".toByteArray()
            if (controlEnvio.value == 1){
                characteristic.value = datos.value.toByteArray()
                writeCharacteristic(gatt, characteristic, characteristic.value)
            }

        }
    }
    @SuppressLint("MissingPermission")
    override fun onConnectionStateChange(gatt: BluetoothGatt, status: Int, newState: Int) {
        val deviceAddress = gatt.device.address
        if (status == BluetoothGatt.GATT_SUCCESS) {
            if (newState == BluetoothProfile.STATE_CONNECTED) {
                Log.w("BluetoothGattCallback", "Successfully connected to $deviceAddress")
                Handler(Looper.getMainLooper()).post {
                    gatt.discoverServices()
                }
            } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                Log.w("BluetoothGattCallback", "Successfully disconnected from $deviceAddress")
                gatt.close()
            }
        } else {
            Log.w("BluetoothGattCallback", "Error $status encountered for $deviceAddress! Disconnecting...")
            gatt.close()
        }
    }
    @SuppressLint("MissingPermission")
    override fun onServicesDiscovered(gatt: BluetoothGatt, status: Int) {
        with(gatt) {
            Log.w("BluetoothGattCallback", "Discovered ${services.size} services for ${device.address}")
            printGattTable()// See implementation just above this section
            // Consider connection setup as complete here
            //onCharacteristicRead(gatt, gatt.getService(UUID.fromString("6e400001-b5a3-f393-e0a9-e50e24dcca9e")).getCharacteristic(UUID.fromString("6E400003-B5A3-F393-E0A9-E50E24DCCA9E")), ByteArray(10), 0)
            val temp = gatt.getService(UUID.fromString("6e400001-b5a3-f393-e0a9-e50e24dcca9e")).getCharacteristic(UUID.fromString("6E400003-B5A3-F393-E0A9-E50E24DCCA9E"))
             //gatt.readCharacteristic(temp)
            gatt.setCharacteristicNotification(temp, true)
            val descriptor = temp.getDescriptor(UUID.fromString("00002902-0000-1000-8000-00805f9b34fb"))
            descriptor?.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE)
            gatt.writeDescriptor(descriptor)
            //readBatteryLevel(gatt)
        }
    }
    @Deprecated("Deprecated for Android 13+")
    @Suppress("DEPRECATION")
    override fun onCharacteristicRead(
        gatt: BluetoothGatt,
        characteristic: BluetoothGattCharacteristic,
        status: Int
    ) {
        with(characteristic) {
            when (status) {
                BluetoothGatt.GATT_SUCCESS -> {
                    Log.i("BluetoothGattCallback", "Read characteristic $uuid:\n${value.toString(Charsets.UTF_8)}")
                }
                BluetoothGatt.GATT_READ_NOT_PERMITTED -> {
                    Log.e("BluetoothGattCallback", "Read not permitted for $uuid!")
                }
                else -> {
                    Log.e("BluetoothGattCallback", "Characteristic read failed for $uuid, error: $status")
                }
            }
        }
    }
    override fun onCharacteristicRead(
        gatt: BluetoothGatt,
        characteristic: BluetoothGattCharacteristic,
        value: ByteArray,
        status: Int
    ) {
        val uuid = characteristic.uuid
        when (status) {
            BluetoothGatt.GATT_SUCCESS -> {
                Log.i("BluetoothGattCallback", "Read characteristic $uuid:\n${value.toHexString()}")
            }
            BluetoothGatt.GATT_READ_NOT_PERMITTED -> {
                Log.e("BluetoothGattCallback", "Read not permitted for $uuid!")
            }
            else -> {
                Log.e("BluetoothGattCallback", "Characteristic read failed for $uuid, error: $status")
            }
        }


    }
    @Deprecated("Deprecated in Java")
    override fun onCharacteristicChanged(
        gatt: BluetoothGatt?,
        characteristic: BluetoothGattCharacteristic
    ) {
        super.onCharacteristicChanged(gatt, characteristic)
        buttonState.value = true
        alphaVal.value = 1f
        var temp = characteristic.value.decodeToString()
        Log.v("bluetoothche", temp)
        temp = temp.replace("\n", "")
        temp = temp.replace("\r", "")
        sensores = temp.split(",").toMutableList()
        if (sensores[0] == "G") {
            magnetometro.value = sensores[1] + " uT"
            acelerometro.value = sensores[2] + " m/s^2"
            temperatura.value = sensores[3] + " °C"
        }else if (sensores[0] == "H"){
            lluvia.value = sensores[2] + " mm"
            distancia.value = sensores[3] + " cm"
            calidad.value = sensores[4] + " mg/L"
        }else if (sensores[0] == "I"){
            ipPort.value = sensores[1]
        }
        else if (sensores[0] == "S"){
            serverMqttF.value = sensores[1]
        }
        else if (sensores[0] == "P"){
            puertoMqttF.value = sensores[1]
        }
        else if (sensores[0] == "U"){
            usuarioMqttF.value = sensores[1]
        }
        else if (sensores[0] == "C"){
            claveMqttF.value = sensores[1]
        }
        else if (sensores[0] == "W"){
            wifitF.value = sensores[1]
        }
        else if (sensores[0] == "X"){
            wifiClavetF.value = sensores[1]
        }






    }
        override fun onCharacteristicWrite(
        gatt: BluetoothGatt,
        characteristic: BluetoothGattCharacteristic,
        status: Int
    ) {
        val uuid = characteristic.uuid
        when (status) {
            BluetoothGatt.GATT_SUCCESS -> {
                Log.i("BluetoothGattCallback", "Wrote characteristic $uuid")
            }
            BluetoothGatt.GATT_WRITE_NOT_PERMITTED -> {
                Log.e("BluetoothGattCallback", "Write not permitted for $uuid!")
            }
            else -> {
                Log.e("BluetoothGattCallback", "Characteristic write failed for $uuid, error: $status")
            }
        }
    }
}
fun ByteArray.toHexString(): String =
    joinToString(separator = " ", prefix = "0x") { String.format("%02X", it) }
@SuppressLint("MissingPermission")
fun writeCharacteristic(gatt: BluetoothGatt, characteristic: BluetoothGattCharacteristic, value: ByteArray) {
    characteristic.setValue(value)
    characteristic.writeType = BluetoothGattCharacteristic.WRITE_TYPE_NO_RESPONSE
    gatt.writeCharacteristic(characteristic)
}
@SuppressLint("MissingPermission")
fun ConectButt(context: Context) {

    val pairedDevices = bluetoothAdapter?.bondedDevices
    if (pairedDevices?.map { it.name }?.contains(phisicDevices[indexclick].name) == true) {
        phisicDevices[indexclick].javaClass.getMethod("removeBond").invoke(phisicDevices[indexclick])
        ConectButt(context)
    } else {
        phisicDevices[indexclick].createBond()
        bluetoothGatt = phisicDevices[indexclick].connectGatt(context, false, gattCallback)
        nombreVersion.value = phisicDevices[indexclick].name.drop(3)

        //ConectButt(context)
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun BLUETOOTH(modifier: Modifier = Modifier, startBleScan: () -> Unit) {

    var selectedIndex by remember {
        mutableStateOf(-1)
    }
    val context= LocalContext.current

    Box(
        modifier = modifier
            .background(color = Color.White)
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
        ) {
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.Center)
                    .fillMaxWidth()
                    .requiredHeight(height = 725.dp)
                    .clip(shape = RoundedCornerShape(topStart = 34.dp, topEnd = 34.dp))
                    .background(color = Color(0xE6f7e9e9)))
            Button(
                onClick = { startBleScan()},
                shape = RoundedCornerShape(10.dp,
                    10.dp,
                    0.dp,
                    0.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xa0d793e6)),
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(
                        x = 0.dp,
                        y = 360.dp
                    )
                    .height(height = 35.dp)
                    .width(width = 250.dp)
            ) {
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 164.dp)
                        .requiredHeight(height = 56.dp)
                ) {
                    Text(
                        text = ButtonStatus.value,
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
                onClick = { localSearch(context) },
                enabled = buttonStateCon.value,
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFAAC9AB)),
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(
                        x = 0.dp,
                        y = 480.dp
                    )
                    .height(height = 55.dp)
                    .width(width = 250.dp)
            ) {
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.csvfile),
                        contentDescription = "logo telegram",
                        contentScale = ContentScale.Crop,
                        alpha = alphaVal.value,
                        modifier = Modifier
                            .size(42.dp)
                    )
                    Spacer(modifier = Modifier.width(30.dp))
                    Text(
                        text = "Datos Locales",
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 14.sp,
                            letterSpacing = (-0.01).sp),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(alignment = Alignment.CenterVertically))
                }
            }


            Button(
                onClick = { ConectButt(context) },
                enabled = buttonStateCon.value,
                shape = RoundedCornerShape(0.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xa0d793e6)),
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(
                        x = 0.dp,
                        y = 400.dp
                    )
                    .height(height = 35.dp)
                    .width(width = 250.dp)

            ) {
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 164.dp)
                        .requiredHeight(height = 56.dp)
                ) {
                    Text(
                        text = "Conectar",
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
                onClick = { buttonClicked2(context) },
                enabled = buttonState.value,
                shape = RoundedCornerShape(0.dp,0.dp,0.dp,0.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xa0d793e6)),
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(
                        x = (-65).dp,
                        y = 440.dp
                    )
                    .height(height = 35.dp)
                    .width(width = 120.dp)
            ) {
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 374.dp)
                        .requiredHeight(height = 56.dp)
                ) {
                    Text(
                        text = "Visualización",
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
                onClick = { buttonClicked3(context) },
                enabled = buttonState.value,
                shape = RoundedCornerShape(0.dp,0.dp,0.dp,0.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xa0d793e6)),
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(
                        x = 65.dp,
                        y = 440.dp
                    )
                    .height(height = 35.dp)
                    .width(width = 120.dp)
            ) {
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 374.dp)
                        .requiredHeight(height = 56.dp)
                ) {
                    Text(
                        text = "Ajustes",
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


            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(y = 50.dp)
                    .width(width = 374.dp)
                    .height(height = 300.dp)
                    .background(color = Color(0xffd9d9d9))
            ) {
                //var text by remember { mutableStateOf("Hola") }
                LazyColumn(state = rememberLazyListState()) {
                    val items : List<String> = listOf(status.value)
                    items(devices.size-1) { index ->
                        Text(
                            text = devices[index+1],
                            color = Color.Black,
                            textAlign = TextAlign.Start,
                            lineHeight = 1.2.em,
                            style = TextStyle(
                                fontSize = 15.sp,
                                letterSpacing = (-0.01).sp,
                            ),
                            modifier = Modifier
                                .wrapContentHeight(align = Alignment.CenterVertically)
                                .fillMaxWidth()
                                .wrapContentHeight(align = Alignment.CenterVertically)
                                .offset(
                                    x = 20.dp,
                                    y = 10.dp
                                )
                                .background(
                                    if (selectedIndex == index) Color(0xFFBEA7EC)
                                    else Color.Transparent
                                )
                                .selectable(
                                    selected = selectedIndex == index,
                                    onClick = {
                                        selectedIndex = index
                                        indexclick = index
                                        Toast
                                            .makeText(
                                                context,
                                                "Seleccionado: ${devices[index + 1]}",
                                                Toast.LENGTH_SHORT
                                            )
                                            .show()
                                    }
                                )
                        )
                    }
                }
                Text(
                    text = tittleStatus.value,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    lineHeight = 0.9.em,
                    style = TextStyle(
                        fontSize = 20.sp,
                        letterSpacing = (-0.01).sp),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = (-10).dp,
                            y = (-50).dp
                        )
                        .requiredWidth(width = 360.dp)
                        .requiredHeight(height = 56.dp)
                        .wrapContentHeight(align = Alignment.CenterVertically))
            }
        }
    }
}

private fun localSearch(context: Context) {
    Log.d("Test1", "Local search")
    val navigate = Intent(context, MainActivity5::class.java)
    context.startActivity(navigate)
}

@Preview(widthDp = 414, heightDp = 896)
@Composable
private fun BLUETOOTHPreview() {
    BLUETOOTH(Modifier, startBleScan = {})
}