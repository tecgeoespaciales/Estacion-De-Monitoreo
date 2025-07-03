/*
Activez le Bluetooth et scannez les équipements avant de lancer l'application
L'application vérifie si la permission BLUETOOTH_CONNECT est accordée. Si ce n'est pas le cas, elle demande la permission.
Si la permission est accordée, elle récupère liste des équipements associés.
Si le HC-05 en fait partie elle essaye de s'y connecter
Un champ connectStatus affiche l'état de la connexion dans l'UI
Le bouton `LED ON` transmet le caractère 'A' vers le HC-05
Le bouton `LED OFF` transmet le caractère 'B' vers le HC-05
Le bouton bouton `READ` transmet le caractère 'C', lit 5 caractères et les affiche dans le champ capteur1 de l'UI
fait par:   Abdelmajid OUMNAD   aoumnad@gmail.com
*/


@file:Suppress("LiftReturnOrAssignment")

package com.example.ema_app

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothSocket
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.ema_app.ui.theme.EMA_APPTheme
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.util.UUID


class Test : ComponentActivity() {
    private lateinit var bluetoothManager: BluetoothManager
    private lateinit var bluetoothAdapter: BluetoothAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bluetoothManager = getSystemService(BluetoothManager::class.java)
        bluetoothAdapter = bluetoothManager.adapter
        val status = mutableStateOf("Bluetooth & Arduino\n")
        // Handler pour remonter les messages à partir des Threads
        val handler = Handler(Looper.getMainLooper()) { msg ->
            when (msg.what) {
                CONNECTION_FAILED -> {
                    status.value += "La connexion à HC-05 a échoué\n"
                    true
                }
                CONNECTION_SUCCESS -> {
                    status.value += "Connexion à HC-05 réussie\n"
                    true
                }
                else -> false
            }
        }
        val blutoothPermission = android.Manifest.permission.BLUETOOTH_CONNECT
        // enregistrement du laucher de demande de permission
        // ce launcher sera appelé si la permission n'a pas déja été accordée
        val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission())
        { isGranted: Boolean ->
            if (isGranted) {
                status.value += "Permission acceptée\nTentative de connexion\n"
                status.value += connectHC05( bluetoothAdapter, handler)
            } else {
                status.value += "====>  Permission refusée\n"
            }
        }
        //Vérifier si l'appli a déjà l'autorisation
        if (ContextCompat.checkSelfPermission(applicationContext,blutoothPermission) == PackageManager.PERMISSION_GRANTED) {
            status.value += "Permission déjà accordée \nTentative de connexion\n"
            status.value += connectHC05( bluetoothAdapter, handler)
        } else {
            status.value += "On va demander la permission\n"
            requestPermissionLauncher.launch(blutoothPermission)
        }
        setContent {

            EMA_APPTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyUI(status)
                }
            }
        }
    }
}
//##################################################################################################
@SuppressLint("MissingPermission")
private fun connectHC05(bluetoothAdapter: BluetoothAdapter?, handler: Handler): String {
    // récupérer la liste des équipements associés
    val pairedDevices: Set<BluetoothDevice>? = bluetoothAdapter?.bondedDevices
    // localiser le HC-05 dans la liste
    val hc05Device = pairedDevices?.find { it.name == "HC-05" }
    // Si le HC-05 est associé, essayer de le connecter
    if (hc05Device != null) {
        ConnectThread(hc05Device, handler).start()
        // les messages d'état sont remonté de ConnectThread() vers le handler
        return ""
    }else {
        return "HC-05 Non Associé\n"
    }

}
//##################################################################################################
private val MY_UUID: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
const val CONNECTION_FAILED: Int = 0
const val CONNECTION_SUCCESS: Int = 1
@SuppressLint("MissingPermission")
class ConnectThread(private val monDevice: BluetoothDevice, private val handler: Handler) : Thread() {
    private val mmSocket: BluetoothSocket? by lazy(LazyThreadSafetyMode.NONE) {
        monDevice.createRfcommSocketToServiceRecord(MY_UUID)
    }
    override fun run() {
        mmSocket?.let { socket ->
            try {
                socket.connect()
                handler.obtainMessage(CONNECTION_SUCCESS).sendToTarget()
            } catch (e: Exception) {
                handler.obtainMessage(CONNECTION_FAILED).sendToTarget()
            }
            dataExchaneInstance = DataExchange(socket)
        }
    }
}

//##################################################################################################
var dataExchaneInstance: DataExchange? = null
class DataExchange(mmSocket: BluetoothSocket) : Thread() {
    private val length = 5
    private val mmInStream: InputStream = mmSocket.inputStream
    private val mmOutStream: OutputStream = mmSocket.outputStream
    private val mmBuffer: ByteArray = ByteArray(length)

    fun write(bytes: ByteArray) {
        try {
            mmOutStream.write(bytes)
        } catch (_: IOException) {
        }
    }
    fun read(): String {
        try {
            mmOutStream.write("C".toByteArray())
        } catch (_: IOException) {
        }

        var numBytesReaded = 0
        try {
            while (numBytesReaded < length) {
                val num = mmInStream.read(mmBuffer, numBytesReaded, length - numBytesReaded)
                if (num == -1) {
                    // La fin du flux a été atteinte
                    break
                }
                numBytesReaded += num
            }
            return String(mmBuffer, 0, numBytesReaded)
        } catch (e: IOException) {
            return "erreur" // Retourner une chaîne vide en cas d'erreur
        }
    }
}

//The UI ##################################################################################################
@Composable
fun MyUI(connectStatus: MutableState<String>) {
    //val connectStatus = remember { mutableStateOf(status.toString()) }
    val capteur1 = remember { mutableStateOf("Rien") }
    Column {
        Text(
            text = connectStatus.value,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .background(Color(0x80E2EBEA))
                .padding(start = 16.dp)  // marge intérieure
        )

        Spacer(modifier = Modifier.height(16.dp))
        Row (horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
        ){
            Button(onClick = {
                dataExchaneInstance?.write("A".toByteArray())
            }
            )
            {
                Text("  LED ON  ")
            }
            Button(onClick = {
                dataExchaneInstance?.write("B".toByteArray())
            }
            )
            {
                Text("  LED OFF  ")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row (verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth())
        {
            Button(onClick = {
                val str = dataExchaneInstance?.read()
                if (str != null) {
                    capteur1.value = str
                }else connectStatus.value = "rien"
            },
                modifier=Modifier.padding(start = 48.dp)
            )
            {
                Text("  READ  ")
            }
            Text(
                text = capteur1.value,
                modifier = Modifier
                    .padding(start = 96.dp)
                    .background(Color(0x80E2EBEA))
                    .padding(horizontal = 16.dp)  // marge intérieure
            )
        }
    }
}