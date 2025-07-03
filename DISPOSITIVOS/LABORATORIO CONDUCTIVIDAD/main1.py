# Calidad de agua y temperatura en uS/cm

# Librerias
from machine import ADC, Pin, reset, WDT
from ds18x20_single import DS18X20Single
from onewire import OneWire
import dftds
from time import sleep

firulais = WDT(timeout=7000)

# Pines usados (todos juntos)
TDS_PIN = 26         # DF robot
LED_PIN = 2         # Pic Ahorro - LED ESP
RES_PIN = 27        # Pic Ahorro - Resistencia
TEMP_PIN = 13        # Temperatura

# Inicialización de pines
led = Pin(LED_PIN, Pin.OUT)
led.on()
Res = Pin(RES_PIN, Pin.OUT, Pin.PULL_UP)
Res.off()
ow = OneWire(Pin(TEMP_PIN))

# Variables
k = 1.0
tds_value = 0.0
tempi = 25

# Instancia de sensores
temp = DS18X20Single(ow)
sleep(1)
tds_sensor = dftds.GravityTDS(TDS_PIN, adc_range=65535, k_value=k)
tds_sensor.begin()
sleep(1)

# Ejecucion
while True:
    try:
        firulais.feed()
        #print("comiendo...")
        tempi = temp.read_temp()
        print("Temperatura: {} C".format(tempi))
        
    except:
        pass

    tds_sensor.temperature = float(tempi)
    firulais.feed()
   # print("comiendo...")

    tds_value = tds_sensor.update()
    firulais.feed()
   # print("comiendo...")

    print("TDS: {}ppm, EC: {} mS/cm".format(tds_value, tds_value*2))
    Res.on()

    sleep(1)
