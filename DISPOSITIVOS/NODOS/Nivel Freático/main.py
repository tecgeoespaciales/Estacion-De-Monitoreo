from libreria import Perifericos
from machine import reset, Pin, deepsleep
import time

def ord_burbuja(arreglo):
    n = len(arreglo)

    for i in range(n-1):       # <-- bucle padre
        for j in range(n-1-i): # <-- bucle hijo
            if arreglo[j] > arreglo[j+1]:
                arreglo[j], arreglo[j+1] = arreglo[j+1], arreglo[j]
                
ledAlert = Pin(2, Pin.OUT)
try:
    perifericos = Perifericos()
    for i in range(5):
        ledAlert.value(1)
        time.sleep(0.3)
        ledAlert.value(0)
        time.sleep(0.3)
except:
    print("error")
    for i in range(5):
        ledAlert.value(1)
        time.sleep(0.3)
        ledAlert.value(0)
        time.sleep(0.3)
    reset()

buffer=[]
perifericos.borrarEEPROM()
fecha, hora = perifericos.leerDS1307()
print(hora)
print(fecha)
while True:
    fecha, hora = perifericos.leerDS1307()
    ledAlert.value(1)
    time.sleep(0.3)
    perifericos.escribirEEPROM(0,fecha+hora)
    ledAlert.value(0)
    time.sleep(0.3)
    #buf, co = perifericos.leerSensorUltras()
    #buf = perifericos.leerSensorUltras()
    while True:
        sensor=perifericos.leerLidar()
        if sensor > 0:
            buffer.append(sensor)
        if len(buffer)>=50:
            break
        time.sleep(0.01)
    ord_burbuja(buffer)
    buf=((buffer[int(len(buffer)/2)-1]+buffer[int(len(buffer)/2)]+buffer[int(len(buffer)/2)+1])/3)
    buffer.clear()
    ledAlert.value(1)
    time.sleep(0.3)
    perifericos.escribirSD(fecha, hora, buf)
    ledAlert.value(0)
    for i in range(5):
        ledAlert.value(1)
        time.sleep(0.3)
        ledAlert.value(0)
        time.sleep(0.3)
    print("fin")
    #time.sleep(5)
    deepsleep(600000)
