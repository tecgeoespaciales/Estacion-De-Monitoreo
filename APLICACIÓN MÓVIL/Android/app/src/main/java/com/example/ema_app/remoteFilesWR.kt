package com.example.ema_app

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.content.FileProvider
import io.ktor.network.selector.SelectorManager
import io.ktor.network.sockets.aSocket
import io.ktor.network.sockets.openReadChannel
import io.ktor.network.sockets.openWriteChannel
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.ByteWriteChannel
import io.ktor.utils.io.readAvailable
import io.ktor.utils.io.readFully
import io.ktor.utils.io.readUTF8Line
import io.ktor.utils.io.writeFully
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.charset.StandardCharsets

class remoteFilesWR {

    val WEBREPL_REQ_S = "<2sBBQLH64s"
    val WEBREPL_PUT_FILE = 1
    val WEBREPL_GET_FILE = 2
    val WEBREPL_GET_VER  = 3
    val WEBREPL_FRAME_TXT = 0x81
    val WEBREPL_FRAME_BIN = 0x82
    val SANDBOX = ""

    var buf =byteArrayOf()

    suspend fun clientHandshake(readchannel: ByteReadChannel, writechannel : ByteWriteChannel) {
        writechannel.writeFully("GET / HTTP/1.1\r\n".toByteArray())
        writechannel.writeFully("Host: echo.websocket.org\r\n".toByteArray())
        writechannel.writeFully("Connection: Upgrade\r\n".toByteArray())
        writechannel.writeFully("Upgrade: websocket\r\n".toByteArray())
        writechannel.writeFully("Sec-WebSocket-Key: foo\r\n".toByteArray())
        writechannel.writeFully("\r\n".toByteArray())
        while (true) {
            val line = readchannel.readUTF8Line()
            println(line)
            if (line.isNullOrEmpty()) {
                break
            }
        }
    }
    suspend fun recvExactly(channel: ByteReadChannel, sz: Int): ByteArray {
        val buffer = ByteArray(sz)
        var totalRead = 0
        channel.readFully(buffer)
        if (buffer.isEmpty()){
            return byteArrayOf()
        }
        return buffer
    }
    suspend fun read(channel: ByteReadChannel, size: Int, textOk: Boolean = false): ByteArray {

        // Si el buffer está vacío
        if (buf.isEmpty()) {
            var sz=0
            var fl=0
            while (true) {
                val hdr = recvExactly(channel, 2)
                require(hdr.size == 2) { "Header must be 2 bytes" }

                fl = hdr[0].toInt() and 0xFF
                sz = hdr[1].toInt() and 0xFF

                if (sz == 126) {
                    val extendedHdr = recvExactly(channel, 2)
                    require(extendedHdr.size == 2) { "Extended header must be 2 bytes" }
                    sz = ((extendedHdr[0].toInt() and 0xFF) shl 8) or (extendedHdr[1].toInt() and 0xFF)
                }

                if (fl == 0x82) {
                    break
                }
                if (textOk && fl == 0x81){
                    break
                }

                while (sz > 0) {
                    val skip = channel.readAvailable(ByteArray(sz))
                    println("Skip data: ${skip}")
                    sz -= skip
                }
            }
            val data = recvExactly(channel, sz)
            require(data.size == sz) { "Data size mismatch" }
            buf = data
        }
        val d = buf.take(size).toByteArray()
        buf = buf.drop(size).toByteArray()
        require(d.size == size) { "Data size mismatch" }
        return d
    }
    suspend fun write(channel: ByteWriteChannel, data: ByteArray, frame: Int = WEBREPL_FRAME_BIN) {
        val length = data.size
        val header: ByteArray

        header = if (length < 126) {
            ByteBuffer.allocate(2).order(ByteOrder.BIG_ENDIAN).put(frame.toByte()).put(length.toByte()).array()
        } else {
            ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN)
                .put(frame.toByte())
                .put(126.toByte())
                .putShort(length.toShort())
                .array()
        }

        channel.writeFully(header)
        channel.writeFully(data)


    }
    suspend fun login(readchannel: ByteReadChannel,writechannel: ByteWriteChannel,passwd: String) {
        while (true) {
            val c = read(readchannel,1, textOk = true)
            println(c.decodeToString())

            if (c[0] == ':'.toByte()) {
                require(read(readchannel,1, textOk = true).contentEquals(" ".toByteArray()))
                break
            }
        }
        write(writechannel,(passwd.encodeToByteArray() + "\r".encodeToByteArray()))
    }
    suspend fun sendReq(writechannel: ByteWriteChannel, op: Int, sz: Int = 0, fname: ByteArray = ByteArray(0)) {
        val buffer = ByteBuffer.allocate(2 + 1 + 1 + 8 + 4 + 4 + 64) // Tamaños de los tipos en WEBREPL_REQ_S

        buffer.put("WA".toByteArray()) // 2s
        buffer.put(op.toByte()) // B
        buffer.put(0.toByte()) // B
        buffer.putLong(0) // Q
        buffer.putInt(sz) // L
        buffer.putShort(fname.size.toShort()) // H
        buffer.put(fname.copyOf(64)) // 64s, asegurando que tenga un tamaño máximo de 64 bytes

        val rec = buffer.array()

        write(writechannel,rec)
    }
    suspend fun getVer(writechannel: ByteWriteChannel, readchannel: ByteReadChannel): Triple<Int, Int, Int> {
        sendReq(writechannel, WEBREPL_GET_VER)
        val d = read(readchannel,3)
        return d.run {
            Triple(this[0].toInt() and 0xFF, this[1].toInt() and 0xFF, this[2].toInt() and 0xFF)
        }
    }

    suspend fun getFile(readchannel: ByteReadChannel, writechannel: ByteWriteChannel, localFile: String, remoteFile: String, context: Context) {

        val srcFname = (SANDBOX + remoteFile).toByteArray(StandardCharsets.UTF_8)
        val buffer = ByteBuffer.allocate(2 + 1 + 1 + 8 + 4 + 4 + 64)
        buffer.put("WA".toByteArray(StandardCharsets.US_ASCII))
        buffer.put(WEBREPL_GET_FILE.toByte())
        buffer.put(0.toByte()) // B
        buffer.putLong(0) // Q
        buffer.putInt(0) // L
        buffer.putShort(srcFname.size.toShort()) // H
        buffer.put(srcFname.copyOf(64))

        val rec = buffer.array()
        write(writechannel,rec)

        val path = context.filesDir

        val letDirectory = File(path, "LET")
        letDirectory.mkdirs()
        val file = File(letDirectory, "reportes.csv")
        file.delete()
        file.createNewFile()

        check(readResp(readchannel) == 0)


        FileOutputStream(file).use { f ->
            var cnt = 0
            while (true) {
                write(writechannel, byteArrayOf(0))
                val szBuffer = read(readchannel, 2)
                var sz = ByteBuffer.wrap(szBuffer).order(ByteOrder.LITTLE_ENDIAN).short.toInt()
                //println(sz)
                if (sz == 0) {
                    break
                }
                while (sz > 0) {
                    val buf = read(readchannel, sz)
                    if (buf.isEmpty()) {
                        throw OSError()
                    }
                    cnt += buf.size
                    f.write(buf)
                    sz -= buf.size
                    print("Received $cnt bytes\r")
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
            alphaloadingreportes.value=0.0f
            botonlocales.value=true
        }

        check(readResp(readchannel) == 0)
    }
    class OSError : Exception("An OS error occurred.")
    suspend fun readResp(readchannel: ByteReadChannel): Int {
        val data = read(readchannel,4)

        val sig = data.copyOfRange(0, 2)
        val code = ByteBuffer.wrap(data.copyOfRange(2, 4)).short.toInt()

        //println(sig.asList())
        require(sig.contentEquals("WB".toByteArray())) { "Invalid signature" }

        return code
    }


    fun obtenerArchivo(ip : String, file : String, password : String, context : Context) {
        runBlocking {
            try {
                val selectorManager = SelectorManager(Dispatchers.IO)
                val socket = aSocket(selectorManager).tcp().connect(ip, 8266)

                val receiveChannel = socket.openReadChannel()
                val sendChannel = socket.openWriteChannel(autoFlush = true)

                clientHandshake(receiveChannel, sendChannel)

                val Password = password
                login(receiveChannel, sendChannel, Password)

                getFile(receiveChannel, sendChannel, "reporte.csv", file, context)

                socket.close()
            }
            catch (e: Exception) {
                Log.i("Error", e.toString())
            }
            alphaloadingreportes.value=0.0f
            botonlocales.value=true

        }
    }



}