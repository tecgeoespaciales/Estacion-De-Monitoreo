from machine import SoftI2C,Pin
import time

class TFminiI2C:

    """
    Interface to the Benewake TFmini distance (Lidar-like) sensor with I2C interface.
    Usage examples:
        
    Define sensor by giving I2C bus number and sensor address (default: 0x10)
    
    Sensor = TFminiI2C(1, 0x10)
    
    Sensor.read()
    """

    def __init__(self):
        self.I2Cbus = SoftI2C(scl=Pin(26),sda=Pin(25))
        self.address = 16
        self.RegSetSlave = [0, 38, 1]  # 0x0026, send adddress between 0x10-0x78
        self.RegTriggerMode = [0, 39, 1]  # 0x0027, set trigger mode, default 0x00
        self.RegDefaultSet = [
            0,
            112,
            1,
        ]  # 0x0070, send 0x02 for reset. restore default values, leaves slave address and trigger mode intact
        self.RegDetPattern = [
            0,
            81,
            1,
        ]  # 0x0051, default 0x00, send 0x01 for fixed detection range limits
        self.RegDetRange = [
            0,
            80,
            1,
        ]  # 0x0050, send 0x00 for short (0.3-2m), 0x03 for middle (0.5-5m) or 0x07 for long (1-12m). Set to fixed detection range first.
        self.RegDistUnit = [
            0,
            102,
            1,
        ]  # 0x0066, send 0x00 for mm or 0x01 for cm (default)

    def _setRegister(self, register, setvalue):
        """ helper function """

        self.register = register
        self.setvalue = setvalue
        self.I2Cbus.writeto(self.address,self.register)
        self.I2Cbus.writeto(self.address,[setvalue])
        
        return

    def readAll(self):
        """ Return the distance value in selected unit. Default: cm """

        self.I2Cbus.writeto(self.address, bytearray([1, 2, 7]))
        self.read = self.I2Cbus.readfrom(self.address, 7)

        self.data = list(self.read)

        self.TrigFlag = self.data[0]
        self.Dist = self.data[3] << 8 | self.data[2]
        self.Strength = self.data[5] << 8 | self.data[4]
        self.Mode = self.data[6]

        return [self.TrigFlag, self.Dist, self.Strength, self.Mode]

    def readDistance(self):
        """ Return the distance value in selected unit. Default: cm """

        self.I2Cbus.writeto(self.address, bytearray([1, 2, 7]))
        self.read = self.I2Cbus.readfrom(self.address, 7)

        self.data = list(self.read)

        self.Dist = self.data[3] << 8 | self.data[2]

        return self.Dist

    def reset(self):
        """reset sensor"""

        self.I2Cbus.writeto(self.address, [0x06])
        time.sleep(0.05)

        return

    def resetdefault(self):

        """ reset sensor to factory settings, leave I2C address as is. """
        self._setRegister(self.RegDefaultSet, 0x02)

    def setAddress(self, newAddress):
        """set new address, needs power cycle to become active - reset apparently not sufficient"""

        self.newAddress = newAddress

        self._setRegister(self.RegSetSlave, self.newAddress)
        print(
            "After power cycle, TFmini "
            + hex(self.address)
            + " will be "
            + hex(self.newAddress)
        )

        return

    def setRange(self, RangeValue):
        """ 
        Use to set a short, medium or long distance range mode.
        Default behaviour is automatic switching, with a loss in accuracy while changing. 
        For some applications, fixed range mode might be more useful. 
        Here, first automatic changes are disabled, then a specific range is locked in.
        Use 0x00, 0x03 or 0x07 for short, medium or long range.
        """

        self.RangeValue = RangeValue

        while RangeValue not in {0x00, 0x03, 0x07}:
            print("Use 0x00, 0x03 or 0x07 for short, medium or long range.")
            return

        self.I2Cbus.writeto(self.address, self.RegDetPattern)
        self.I2Cbus.writeto(self.address, [0x01])
        """deactivate automatic range switching"""

        print("Set range mode to fixed.")

        print("Set range distance.")
        self.I2Cbus.writeto(self.address, self.RegDetRange)
        self.I2Cbus.writeto(self.address, [self.RangeValue])
        """ set fixed range """

        time.sleep(0.01)

        return

    def setUnit(self, RangeUnit):

        self.RangeUnit = RangeUnit

        while self.RangeUnit not in {0x00, 0x01}:
            print("Use 0x00 for mm, or 0x01 for cm.")
            return

        self._setRegister(self.RegDistUnit, self.RangeUnit)
        return

