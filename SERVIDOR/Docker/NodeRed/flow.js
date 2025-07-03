[
  {
    "id": "225d09db4fc5176a",
    "type": "tab",
    "label": "Flow 4",
    "disabled": false,
    "info": ""
  },
  {
    "id": "b65610c31cd90972",
    "type": "tab",
    "label": "Flow 1",
    "disabled": false,
    "info": "",
    "env": []
  },
  {
    "id": "c0d99e92e3414f7d",
    "type": "tab",
    "label": "Flow 2",
    "disabled": false,
    "info": "",
    "env": []
  },
  {
    "id": "cc1467b6fff338fc",
    "type": "tab",
    "label": "Flow 3",
    "disabled": false,
    "info": "",
    "env": []
  },
  {
    "id": "bfebe03d449d8e30",
    "type": "ui_tab",
    "name": "General",
    "icon": "dashboard",
    "order": 2,
    "disabled": false,
    "hidden": false
  },
  {
    "id": "284a240931d36145",
    "type": "ui_base",
    "theme": {
      "name": "theme-dark",
      "lightTheme": {
        "default": "#0094CE",
        "baseColor": "#0094CE",
        "baseFont": "-apple-system,BlinkMacSystemFont,Segoe UI,Roboto,Oxygen-Sans,Ubuntu,Cantarell,Helvetica Neue,sans-serif",
        "edited": true,
        "reset": false
      },
      "darkTheme": {
        "default": "#097479",
        "baseColor": "#097479",
        "baseFont": "-apple-system,BlinkMacSystemFont,Segoe UI,Roboto,Oxygen-Sans,Ubuntu,Cantarell,Helvetica Neue,sans-serif",
        "edited": true,
        "reset": false
      },
      "customTheme": {
        "name": "Untitled Theme 1",
        "default": "#4B7930",
        "baseColor": "#4B7930",
        "baseFont": "-apple-system,BlinkMacSystemFont,Segoe UI,Roboto,Oxygen-Sans,Ubuntu,Cantarell,Helvetica Neue,sans-serif"
      },
      "themeState": {
        "base-color": {
          "default": "#097479",
          "value": "#097479",
          "edited": false
        },
        "page-titlebar-backgroundColor": {
          "value": "#097479",
          "edited": false
        },
        "page-backgroundColor": {
          "value": "#111111",
          "edited": false
        },
        "page-sidebar-backgroundColor": {
          "value": "#333333",
          "edited": false
        },
        "group-textColor": {
          "value": "#0eb8c0",
          "edited": false
        },
        "group-borderColor": {
          "value": "#555555",
          "edited": false
        },
        "group-backgroundColor": {
          "value": "#333333",
          "edited": false
        },
        "widget-textColor": {
          "value": "#eeeeee",
          "edited": false
        },
        "widget-backgroundColor": {
          "value": "#097479",
          "edited": false
        },
        "widget-borderColor": {
          "value": "#333333",
          "edited": false
        },
        "base-font": {
          "value": "-apple-system,BlinkMacSystemFont,Segoe UI,Roboto,Oxygen-Sans,Ubuntu,Cantarell,Helvetica Neue,sans-serif"
        }
      },
      "angularTheme": {
        "primary": "indigo",
        "accents": "blue",
        "warn": "red",
        "background": "grey",
        "palette": "light"
      }
    },
    "site": {
      "name": "Node-RED Dashboard",
      "hideToolbar": "false",
      "allowSwipe": "false",
      "lockMenu": "false",
      "allowTempTheme": "true",
      "dateFormat": "DD/MM/YYYY",
      "sizes": {
        "sx": 48,
        "sy": 48,
        "gx": 6,
        "gy": 6,
        "cx": 6,
        "cy": 6,
        "px": 0,
        "py": 0
      }
    }
  },
  {
    "id": "9cd572751b2dd8b9",
    "type": "ui_group",
    "name": "Mediciòn Temperatura",
    "tab": "bfebe03d449d8e30",
    "order": 5,
    "disp": true,
    "width": "6",
    "collapse": false,
    "className": ""
  },
  {
    "id": "86403cbab9c5bcfb",
    "type": "telegram bot",
    "botname": "EMASGCbot",
    "usernames": "",
    "chatids": "",
    "baseapiurl": "",
    "testenvironment": false,
    "updatemode": "polling",
    "addressfamily": "",
    "pollinterval": "300",
    "usesocks": false,
    "sockshost": "",
    "socksprotocol": "socks5",
    "socksport": "6667",
    "socksusername": "anonymous",
    "sockspassword": "",
    "bothost": "",
    "botpath": "",
    "localbotport": "8443",
    "publicbotport": "8443",
    "privatekey": "",
    "certificate": "",
    "useselfsignedcertificate": false,
    "sslterminated": false,
    "verboselogging": false
  },
  {
    "id": "ca70cee35108395b",
    "type": "mqtt-broker",
    "name": "",
    "broker": "mqtt-nodered-1",
    "port": "1883",
    "clientid": "",
    "autoConnect": true,
    "usetls": false,
    "protocolVersion": "4",
    "keepalive": "60",
    "cleansession": true,
    "autoUnsubscribe": true,
    "birthTopic": "",
    "birthQos": "0",
    "birthRetain": "false",
    "birthPayload": "",
    "birthMsg": {},
    "closeTopic": "",
    "closeQos": "0",
    "closeRetain": "false",
    "closePayload": "",
    "closeMsg": {},
    "willTopic": "",
    "willQos": "0",
    "willRetain": "false",
    "willPayload": "",
    "willMsg": {},
    "userProps": "",
    "sessionExpiry": ""
  },
  {
    "id": "188482eb62b49c74",
    "type": "MySQLdatabase",
    "name": "",
    "host": "127.0.0.1",
    "port": "3306",
    "db": "emaMonitoreo",
    "tz": "",
    "charset": "UTF8"
  },
  {
    "id": "ef86fa12f49d6610",
    "type": "ui_tab",
    "name": "V3",
    "icon": "dashboard",
    "order": 1,
    "disabled": false,
    "hidden": false
  },
  {
    "id": "cdea3b400f1138bd",
    "type": "ui_group",
    "name": "Medición Acelerometros",
    "tab": "bfebe03d449d8e30",
    "order": 2,
    "disp": true,
    "width": "6",
    "collapse": false,
    "className": ""
  },
  {
    "id": "dbf539f017ffee3e",
    "type": "ui_group",
    "name": "Medición Magnetometros",
    "tab": "bfebe03d449d8e30",
    "order": 3,
    "disp": true,
    "width": "6",
    "collapse": false,
    "className": ""
  },
  {
    "id": "73a5c00898f070d9",
    "type": "ui_group",
    "name": "Medición Calidad de Agua",
    "tab": "bfebe03d449d8e30",
    "order": 4,
    "disp": true,
    "width": "6",
    "collapse": false,
    "className": ""
  },
  {
    "id": "b4e78de0de4f6dc3",
    "type": "ui_group",
    "name": "Medición Distancia",
    "tab": "bfebe03d449d8e30",
    "order": 1,
    "disp": true,
    "width": "6",
    "collapse": false,
    "className": ""
  },
  {
    "id": "bb9b44c54d88e575",
    "type": "ui_group",
    "name": "Envío Correo",
    "tab": "bfebe03d449d8e30",
    "order": 6,
    "disp": true,
    "width": "6",
    "collapse": false,
    "className": ""
  },
  {
    "id": "d46a6aa89299658d",
    "type": "ui_group",
    "name": "Envío Correo",
    "tab": "ef86fa12f49d6610",
    "order": 3,
    "disp": true,
    "width": "6",
    "collapse": false,
    "className": ""
  },
  {
    "id": "08010342df037008",
    "type": "ui_group",
    "name": "Medición Calidad de Agua",
    "tab": "ef86fa12f49d6610",
    "order": 1,
    "disp": true,
    "width": 6,
    "collapse": false,
    "className": ""
  },
  {
    "id": "234d855e40364df4",
    "type": "ui_group",
    "name": "Medición Distancia",
    "tab": "ef86fa12f49d6610",
    "order": 2,
    "disp": true,
    "width": 6,
    "collapse": false,
    "className": ""
  },
  {
    "id": "6685cd162313bb90",
    "type": "mqtt in",
    "z": "225d09db4fc5176a",
    "name": "",
    "topic": "temp",
    "qos": "2",
    "datatype": "auto-detect",
    "broker": "ca70cee35108395b",
    "nl": false,
    "rap": true,
    "rh": 0,
    "inputs": 0,
    "x": 110,
    "y": 100,
    "wires": [
      [
        "5240bacdc95e4e70",
        "151aec37f9a343da",
        "854bfa5f631d3eee"
      ]
    ]
  },
  {
    "id": "5240bacdc95e4e70",
    "type": "ui_gauge",
    "z": "225d09db4fc5176a",
    "name": "",
    "group": "ce713a09c9a3e89c",
    "order": 1,
    "width": 0,
    "height": 0,
    "gtype": "gage",
    "title": "Temperatura",
    "label": "units",
    "format": "{{value}}",
    "min": "10",
    "max": "40",
    "colors": [
      "#00b500",
      "#e6e600",
      "#ca3838"
    ],
    "seg1": "",
    "seg2": "",
    "className": "",
    "x": 330,
    "y": 100,
    "wires": []
  },
  {
    "id": "151aec37f9a343da",
    "type": "ui_chart",
    "z": "225d09db4fc5176a",
    "name": "",
    "group": "ce713a09c9a3e89c",
    "order": 10,
    "width": 0,
    "height": 0,
    "label": "chart",
    "chartType": "line",
    "legend": "false",
    "xformat": "HH:mm:ss",
    "interpolate": "linear",
    "nodata": "",
    "dot": false,
    "ymin": "20",
    "ymax": "40",
    "removeOlder": 1,
    "removeOlderPoints": "",
    "removeOlderUnit": "60",
    "cutout": 0,
    "useOneColor": false,
    "useUTC": false,
    "colors": [
      "#1f77b4",
      "#aec7e8",
      "#ff7f0e",
      "#2ca02c",
      "#98df8a",
      "#d62728",
      "#ff9896",
      "#9467bd",
      "#c5b0d5"
    ],
    "outputs": 1,
    "useDifferentColor": false,
    "className": "",
    "x": 510,
    "y": 100,
    "wires": [
      []
    ]
  },
  {
    "id": "854bfa5f631d3eee",
    "type": "function",
    "z": "225d09db4fc5176a",
    "name": "",
    "func": "var count=global.get(\"temp\")||0;\n\ncount=count+1;\nglobal.set(\"temp\",count);\n\nmsg.payload=global.get(\"temp\");\nreturn msg;",
    "outputs": 1,
    "noerr": 0,
    "initialize": "",
    "finalize": "",
    "libs": [],
    "x": 320,
    "y": 60,
    "wires": [
      []
    ]
  },
  {
    "id": "81e1a660371a3f03",
    "type": "mqtt in",
    "z": "225d09db4fc5176a",
    "name": "",
    "topic": "acelX",
    "qos": "2",
    "datatype": "auto-detect",
    "broker": "64f4635e02ea4ddb",
    "nl": false,
    "rap": true,
    "rh": 0,
    "inputs": 0,
    "x": 110,
    "y": 180,
    "wires": [
      [
        "bb7380faa83e9e57",
        "cae8ab1925998626"
      ]
    ]
  },
  {
    "id": "bb7380faa83e9e57",
    "type": "ui_gauge",
    "z": "225d09db4fc5176a",
    "name": "",
    "group": "ce713a09c9a3e89c",
    "order": 2,
    "width": 0,
    "height": 0,
    "gtype": "gage",
    "title": "Aceleracion eje X",
    "label": "units",
    "format": "{{value}}",
    "min": "-5",
    "max": "5",
    "colors": [
      "#00b500",
      "#e6e600",
      "#ca3838"
    ],
    "seg1": "",
    "seg2": "",
    "className": "",
    "x": 350,
    "y": 180,
    "wires": []
  },
  {
    "id": "cae8ab1925998626",
    "type": "function",
    "z": "225d09db4fc5176a",
    "name": "",
    "func": "var count=global.get(\"acelX\")||0;\n\ncount=count+1;\nglobal.set(\"acelX\",count);\n\nmsg.payload=global.get(\"axelX\");\nreturn msg;",
    "outputs": 1,
    "noerr": 0,
    "initialize": "",
    "finalize": "",
    "libs": [],
    "x": 320,
    "y": 140,
    "wires": [
      []
    ]
  },
  {
    "id": "65a4e608fba38b8e",
    "type": "mqtt in",
    "z": "225d09db4fc5176a",
    "name": "",
    "topic": "acelY",
    "qos": "2",
    "datatype": "auto-detect",
    "broker": "64f4635e02ea4ddb",
    "nl": false,
    "rap": true,
    "rh": 0,
    "inputs": 0,
    "x": 110,
    "y": 260,
    "wires": [
      [
        "b9140d1058912927"
      ]
    ]
  },
  {
    "id": "b9140d1058912927",
    "type": "ui_gauge",
    "z": "225d09db4fc5176a",
    "name": "",
    "group": "ce713a09c9a3e89c",
    "order": 3,
    "width": 0,
    "height": 0,
    "gtype": "gage",
    "title": "Aceleracion eje Y",
    "label": "units",
    "format": "{{value}}",
    "min": "-5",
    "max": "5",
    "colors": [
      "#00b500",
      "#e6e600",
      "#ca3838"
    ],
    "seg1": "",
    "seg2": "",
    "className": "",
    "x": 350,
    "y": 260,
    "wires": []
  },
  {
    "id": "42d739ef555274ec",
    "type": "mqtt in",
    "z": "225d09db4fc5176a",
    "name": "",
    "topic": "acelZ",
    "qos": "2",
    "datatype": "auto-detect",
    "broker": "64f4635e02ea4ddb",
    "nl": false,
    "rap": true,
    "rh": 0,
    "inputs": 0,
    "x": 110,
    "y": 340,
    "wires": [
      [
        "cc4d6fc0fa526d18"
      ]
    ]
  },
  {
    "id": "cc4d6fc0fa526d18",
    "type": "ui_gauge",
    "z": "225d09db4fc5176a",
    "name": "",
    "group": "ce713a09c9a3e89c",
    "order": 4,
    "width": 0,
    "height": 0,
    "gtype": "gage",
    "title": "Aceleracion eje Z",
    "label": "units",
    "format": "{{value}}",
    "min": "-10",
    "max": "10",
    "colors": [
      "#00b500",
      "#e6e600",
      "#ca3838"
    ],
    "seg1": "",
    "seg2": "",
    "className": "",
    "x": 350,
    "y": 340,
    "wires": []
  },
  {
    "id": "317e7208378d1bc1",
    "type": "mqtt in",
    "z": "225d09db4fc5176a",
    "name": "",
    "topic": "Pluv",
    "qos": "2",
    "datatype": "auto-detect",
    "broker": "64f4635e02ea4ddb",
    "nl": false,
    "rap": true,
    "rh": 0,
    "inputs": 0,
    "x": 110,
    "y": 420,
    "wires": [
      [
        "dc7b5075586ed12c"
      ]
    ]
  },
  {
    "id": "dc7b5075586ed12c",
    "type": "ui_gauge",
    "z": "225d09db4fc5176a",
    "name": "",
    "group": "ce713a09c9a3e89c",
    "order": 5,
    "width": 0,
    "height": 0,
    "gtype": "compass",
    "title": "Pluviometro",
    "label": "units",
    "format": "{{value}}",
    "min": "-40",
    "max": "100",
    "colors": [
      "#00b500",
      "#e6e600",
      "#ca3838"
    ],
    "seg1": "",
    "seg2": "",
    "className": "",
    "x": 330,
    "y": 420,
    "wires": []
  },
  {
    "id": "ef42c768b444872c",
    "type": "mqtt in",
    "z": "225d09db4fc5176a",
    "name": "",
    "topic": "Latitud",
    "qos": "2",
    "datatype": "auto-detect",
    "broker": "64f4635e02ea4ddb",
    "nl": false,
    "rap": true,
    "rh": 0,
    "inputs": 0,
    "x": 110,
    "y": 500,
    "wires": [
      [
        "d14537cfa680fbc9"
      ]
    ]
  },
  {
    "id": "d14537cfa680fbc9",
    "type": "ui_text",
    "z": "225d09db4fc5176a",
    "group": "ce713a09c9a3e89c",
    "order": 6,
    "width": 0,
    "height": 0,
    "name": "",
    "label": "Latitud",
    "format": "{{msg.payload}}",
    "layout": "col-center",
    "className": "",
    "x": 330,
    "y": 500,
    "wires": []
  },
  {
    "id": "1f2e15c409489935",
    "type": "mqtt in",
    "z": "225d09db4fc5176a",
    "name": "",
    "topic": "Longitud",
    "qos": "2",
    "datatype": "auto-detect",
    "broker": "64f4635e02ea4ddb",
    "nl": false,
    "rap": true,
    "rh": 0,
    "inputs": 0,
    "x": 120,
    "y": 580,
    "wires": [
      [
        "b2cb32110e0e20f5"
      ]
    ]
  },
  {
    "id": "b2cb32110e0e20f5",
    "type": "ui_text",
    "z": "225d09db4fc5176a",
    "group": "ce713a09c9a3e89c",
    "order": 7,
    "width": 0,
    "height": 0,
    "name": "",
    "label": "Longitud",
    "format": "{{msg.payload}}",
    "layout": "col-center",
    "className": "",
    "x": 340,
    "y": 580,
    "wires": []
  },
  {
    "id": "2cece3cf59c9de2a",
    "type": "mqtt in",
    "z": "225d09db4fc5176a",
    "name": "",
    "topic": "Fecha",
    "qos": "2",
    "datatype": "auto-detect",
    "broker": "64f4635e02ea4ddb",
    "nl": false,
    "rap": true,
    "rh": 0,
    "inputs": 0,
    "x": 110,
    "y": 660,
    "wires": [
      [
        "a47230b114d84846"
      ]
    ]
  },
  {
    "id": "a47230b114d84846",
    "type": "ui_text",
    "z": "225d09db4fc5176a",
    "group": "ce713a09c9a3e89c",
    "order": 8,
    "width": 0,
    "height": 0,
    "name": "",
    "label": "Fecha",
    "format": "{{msg.payload}}",
    "layout": "col-center",
    "className": "",
    "x": 330,
    "y": 660,
    "wires": []
  },
  {
    "id": "9ed4dd4bc8463f41",
    "type": "mqtt in",
    "z": "225d09db4fc5176a",
    "name": "",
    "topic": "Hora",
    "qos": "2",
    "datatype": "auto-detect",
    "broker": "64f4635e02ea4ddb",
    "nl": false,
    "rap": true,
    "rh": 0,
    "inputs": 0,
    "x": 110,
    "y": 740,
    "wires": [
      [
        "894391ada1f546f5"
      ]
    ]
  },
  {
    "id": "894391ada1f546f5",
    "type": "ui_text",
    "z": "225d09db4fc5176a",
    "group": "ce713a09c9a3e89c",
    "order": 9,
    "width": 0,
    "height": 0,
    "name": "",
    "label": "Hora",
    "format": "{{msg.payload}}",
    "layout": "col-center",
    "className": "",
    "x": 330,
    "y": 740,
    "wires": []
  },
  {
    "id": "2338c43685e36db2",
    "type": "mqtt in",
    "z": "b65610c31cd90972",
    "name": "",
    "topic": "Distv3",
    "qos": "2",
    "datatype": "auto-detect",
    "broker": "ca70cee35108395b",
    "nl": false,
    "rap": true,
    "rh": 0,
    "inputs": 0,
    "x": 90,
    "y": 1960,
    "wires": [
      [
        "5631d75ced0aab0c",
        "f788cb25dfa3beac",
        "a79bfba5eb1eb8d4",
        "03980fef8bf1a38e",
        "3967c8c5d28bbe78",
        "b033c4d751e30165"
      ]
    ]
  },
  {
    "id": "5631d75ced0aab0c",
    "type": "ui_chart",
    "z": "b65610c31cd90972",
    "name": "",
    "group": "234d855e40364df4",
    "order": 1,
    "width": 0,
    "height": 0,
    "label": "Distancia",
    "chartType": "line",
    "legend": "false",
    "xformat": "HH:mm:ss",
    "interpolate": "linear",
    "nodata": "",
    "dot": false,
    "ymin": "-1",
    "ymax": "80",
    "removeOlder": "3",
    "removeOlderPoints": "",
    "removeOlderUnit": "86400",
    "cutout": 0,
    "useOneColor": false,
    "useUTC": false,
    "colors": [
      "#097479",
      "#aec7e8",
      "#ff7f0e",
      "#2ca02c",
      "#98df8a",
      "#d62728",
      "#ff9896",
      "#9467bd",
      "#c5b0d5"
    ],
    "outputs": 1,
    "useDifferentColor": false,
    "className": "",
    "x": 260,
    "y": 2020,
    "wires": [
      []
    ]
  },
  {
    "id": "f788cb25dfa3beac",
    "type": "change",
    "z": "b65610c31cd90972",
    "name": "",
    "rules": [
      {
        "t": "set",
        "p": "Distv3",
        "pt": "global",
        "to": "payload",
        "tot": "msg"
      }
    ],
    "action": "",
    "property": "",
    "from": "",
    "to": "",
    "reg": false,
    "x": 290,
    "y": 1900,
    "wires": [
      []
    ]
  },
  {
    "id": "a79bfba5eb1eb8d4",
    "type": "ui_text",
    "z": "b65610c31cd90972",
    "group": "234d855e40364df4",
    "order": 2,
    "width": 0,
    "height": 0,
    "name": "",
    "label": "Distancia",
    "format": "{{msg.payload}}",
    "layout": "row-spread",
    "className": "",
    "style": false,
    "font": "",
    "fontSize": 16,
    "color": "#000000",
    "x": 260,
    "y": 1960,
    "wires": []
  },
  {
    "id": "7eb85fb07f45fef7",
    "type": "mqtt in",
    "z": "b65610c31cd90972",
    "name": "pruebas",
    "topic": "test",
    "qos": "2",
    "datatype": "auto-detect",
    "broker": "ca70cee35108395b",
    "nl": false,
    "rap": true,
    "rh": 0,
    "inputs": 0,
    "x": 100,
    "y": 2220,
    "wires": [
      [
        "c226844355a66472",
        "94464dc2ef0a5148"
      ]
    ]
  },
  {
    "id": "c226844355a66472",
    "type": "function",
    "z": "b65610c31cd90972",
    "name": "function 1",
    "func": "global.set(\"pruebas\", msg.payload);\n\nreturn msg;",
    "outputs": 1,
    "timeout": 0,
    "noerr": 0,
    "initialize": "",
    "finalize": "",
    "libs": [],
    "x": 300,
    "y": 2240,
    "wires": [
      []
    ]
  },
  {
    "id": "94464dc2ef0a5148",
    "type": "ui_text",
    "z": "b65610c31cd90972",
    "group": "9cd572751b2dd8b9",
    "order": 5,
    "width": 0,
    "height": 0,
    "name": "",
    "label": "text",
    "format": "{{msg.payload}}",
    "layout": "row-spread",
    "className": "",
    "style": false,
    "font": "",
    "fontSize": 16,
    "color": "#000000",
    "x": 290,
    "y": 2160,
    "wires": []
  },
  {
    "id": "d48015071e234501",
    "type": "mqtt in",
    "z": "b65610c31cd90972",
    "name": "",
    "topic": "Acelxv3",
    "qos": "2",
    "datatype": "auto-detect",
    "broker": "ca70cee35108395b",
    "nl": false,
    "rap": true,
    "rh": 0,
    "inputs": 0,
    "x": 610,
    "y": 2100,
    "wires": [
      [
        "eff3a187709abcce",
        "e43b276cf2603edf",
        "3b301886b577523f"
      ]
    ]
  },
  {
    "id": "64c032db691f76de",
    "type": "mqtt in",
    "z": "b65610c31cd90972",
    "name": "",
    "topic": "Acelyv3",
    "qos": "2",
    "datatype": "auto-detect",
    "broker": "ca70cee35108395b",
    "nl": false,
    "rap": true,
    "rh": 0,
    "inputs": 0,
    "x": 110,
    "y": 1780,
    "wires": [
      [
        "a59c496e459c8676",
        "f153d8278dabf24e",
        "114e6cbbe5dc1314"
      ]
    ]
  },
  {
    "id": "a599592ccd2fa47a",
    "type": "mqtt in",
    "z": "b65610c31cd90972",
    "name": "",
    "topic": "Acelzv3",
    "qos": "2",
    "datatype": "auto-detect",
    "broker": "ca70cee35108395b",
    "nl": false,
    "rap": true,
    "rh": 0,
    "inputs": 0,
    "x": 750,
    "y": 1440,
    "wires": [
      [
        "90841ba5220738e0",
        "712004eb936dfb21",
        "f3dc6df145d7881f"
      ]
    ]
  },
  {
    "id": "bbc45ea482d97ae1",
    "type": "mqtt in",
    "z": "b65610c31cd90972",
    "name": "",
    "topic": "Magxv3",
    "qos": "2",
    "datatype": "auto-detect",
    "broker": "ca70cee35108395b",
    "nl": false,
    "rap": true,
    "rh": 0,
    "inputs": 0,
    "x": 750,
    "y": 1880,
    "wires": [
      [
        "675f8622fc986226",
        "8a5f63e50051cc80",
        "4fc2a8f101417546"
      ]
    ]
  },
  {
    "id": "b19f870da9b49fc9",
    "type": "mqtt in",
    "z": "b65610c31cd90972",
    "name": "",
    "topic": "Magyv3",
    "qos": "2",
    "datatype": "auto-detect",
    "broker": "ca70cee35108395b",
    "nl": false,
    "rap": true,
    "rh": 0,
    "inputs": 0,
    "x": 750,
    "y": 1660,
    "wires": [
      [
        "75d12c548ba2790f",
        "9d11b8fa55f221da",
        "2652b99582e17a71"
      ]
    ]
  },
  {
    "id": "0dbd19d265711f1b",
    "type": "mqtt in",
    "z": "b65610c31cd90972",
    "name": "",
    "topic": "Magzv3",
    "qos": "2",
    "datatype": "auto-detect",
    "broker": "ca70cee35108395b",
    "nl": false,
    "rap": true,
    "rh": 0,
    "inputs": 0,
    "x": 110,
    "y": 1480,
    "wires": [
      [
        "e37f5654be490e46",
        "a4aeb7d6e6c0e73a",
        "565d2191eebd5d20"
      ]
    ]
  },
  {
    "id": "e6c58c2ef5d57f7f",
    "type": "mqtt in",
    "z": "b65610c31cd90972",
    "name": "",
    "topic": "Tempv3",
    "qos": "2",
    "datatype": "auto-detect",
    "broker": "ca70cee35108395b",
    "nl": false,
    "rap": true,
    "rh": 0,
    "inputs": 0,
    "x": 690,
    "y": 1200,
    "wires": [
      [
        "e15c7301de115bd4",
        "4b26904429aacca1",
        "1b5d375be084050a",
        "791dc00b34ac8b16"
      ]
    ]
  },
  {
    "id": "d2c296a85cb20b4e",
    "type": "mqtt in",
    "z": "b65610c31cd90972",
    "name": "",
    "topic": "LoRav3",
    "qos": "2",
    "datatype": "auto-detect",
    "broker": "ca70cee35108395b",
    "nl": false,
    "rap": true,
    "rh": 0,
    "inputs": 0,
    "x": 70,
    "y": 1240,
    "wires": [
      [
        "1d9f0c138ec86117",
        "941384d383dedc13",
        "bde73a7a5c238641",
        "44b390594260858d",
        "ab63ecab680d60e8",
        "2b98ccbd304f015b",
        "b8e2a6db70c58563"
      ]
    ]
  },
  {
    "id": "eff3a187709abcce",
    "type": "ui_text",
    "z": "b65610c31cd90972",
    "group": "cdea3b400f1138bd",
    "order": 2,
    "width": 0,
    "height": 0,
    "name": "",
    "label": "Aceleracion eje x",
    "format": "{{msg.payload}}",
    "layout": "row-spread",
    "className": "",
    "style": false,
    "font": "",
    "fontSize": 16,
    "color": "#000000",
    "x": 850,
    "y": 2180,
    "wires": []
  },
  {
    "id": "a59c496e459c8676",
    "type": "ui_text",
    "z": "b65610c31cd90972",
    "group": "cdea3b400f1138bd",
    "order": 4,
    "width": 0,
    "height": 0,
    "name": "",
    "label": "Aceleracion eje y",
    "format": "{{msg.payload}}",
    "layout": "row-spread",
    "className": "",
    "style": false,
    "font": "",
    "fontSize": 16,
    "color": "#000000",
    "x": 390,
    "y": 1680,
    "wires": []
  },
  {
    "id": "90841ba5220738e0",
    "type": "ui_text",
    "z": "b65610c31cd90972",
    "group": "cdea3b400f1138bd",
    "order": 6,
    "width": 0,
    "height": 0,
    "name": "",
    "label": "Aceleracion eje Z",
    "format": "{{msg.payload}}",
    "layout": "row-spread",
    "className": "",
    "style": false,
    "font": "",
    "fontSize": 16,
    "color": "#000000",
    "x": 950,
    "y": 1360,
    "wires": []
  },
  {
    "id": "675f8622fc986226",
    "type": "ui_text",
    "z": "b65610c31cd90972",
    "group": "dbf539f017ffee3e",
    "order": 2,
    "width": 0,
    "height": 0,
    "name": "",
    "label": "Magnetrometro eje x",
    "format": "{{msg.payload}}",
    "layout": "row-spread",
    "className": "",
    "style": false,
    "font": "",
    "fontSize": 16,
    "color": "#000000",
    "x": 960,
    "y": 1800,
    "wires": []
  },
  {
    "id": "75d12c548ba2790f",
    "type": "ui_text",
    "z": "b65610c31cd90972",
    "group": "dbf539f017ffee3e",
    "order": 4,
    "width": 0,
    "height": 0,
    "name": "",
    "label": "Magnetrometro eje y",
    "format": "{{msg.payload}}",
    "layout": "row-spread",
    "className": "",
    "style": false,
    "font": "",
    "fontSize": 16,
    "color": "#000000",
    "x": 960,
    "y": 1600,
    "wires": []
  },
  {
    "id": "e37f5654be490e46",
    "type": "ui_text",
    "z": "b65610c31cd90972",
    "group": "dbf539f017ffee3e",
    "order": 6,
    "width": 0,
    "height": 0,
    "name": "",
    "label": "Magnetrometro eje z",
    "format": "{{msg.payload}}",
    "layout": "row-spread",
    "className": "",
    "style": false,
    "font": "",
    "fontSize": 16,
    "color": "#000000",
    "x": 380,
    "y": 1460,
    "wires": []
  },
  {
    "id": "e15c7301de115bd4",
    "type": "ui_text",
    "z": "b65610c31cd90972",
    "group": "9cd572751b2dd8b9",
    "order": 1,
    "width": 0,
    "height": 0,
    "name": "",
    "label": "Temperatura",
    "format": "{{msg.payload}}",
    "layout": "row-spread",
    "className": "",
    "style": false,
    "font": "",
    "fontSize": 16,
    "color": "#000000",
    "x": 950,
    "y": 1160,
    "wires": []
  },
  {
    "id": "1d9f0c138ec86117",
    "type": "ui_text",
    "z": "b65610c31cd90972",
    "group": "08010342df037008",
    "order": 1,
    "width": 0,
    "height": 0,
    "name": "",
    "label": "LoRa",
    "format": "{{msg.payload}}",
    "layout": "row-spread",
    "className": "",
    "style": false,
    "font": "",
    "fontSize": 16,
    "color": "#000000",
    "x": 330,
    "y": 1180,
    "wires": []
  },
  {
    "id": "4b26904429aacca1",
    "type": "ui_chart",
    "z": "b65610c31cd90972",
    "name": "",
    "group": "9cd572751b2dd8b9",
    "order": 2,
    "width": 0,
    "height": 0,
    "label": "Temperatura",
    "chartType": "line",
    "legend": "false",
    "xformat": "HH:mm",
    "interpolate": "linear",
    "nodata": "",
    "dot": false,
    "ymin": "15",
    "ymax": "28",
    "removeOlder": 1,
    "removeOlderPoints": "",
    "removeOlderUnit": "86400",
    "cutout": 0,
    "useOneColor": false,
    "useUTC": false,
    "colors": [
      "#1f77b4",
      "#aec7e8",
      "#ff7f0e",
      "#2ca02c",
      "#98df8a",
      "#d62728",
      "#ff9896",
      "#9467bd",
      "#c5b0d5"
    ],
    "outputs": 1,
    "useDifferentColor": false,
    "className": "",
    "x": 950,
    "y": 1120,
    "wires": [
      []
    ]
  },
  {
    "id": "1b5d375be084050a",
    "type": "ui_gauge",
    "z": "b65610c31cd90972",
    "name": "",
    "group": "9cd572751b2dd8b9",
    "order": 4,
    "width": 0,
    "height": 0,
    "gtype": "gage",
    "title": "Temperatura",
    "label": "°C",
    "format": "{{value}}",
    "min": 0,
    "max": "50",
    "colors": [
      "#00b500",
      "#e6e600",
      "#ca3838"
    ],
    "seg1": "",
    "seg2": "",
    "diff": false,
    "className": "",
    "x": 950,
    "y": 1240,
    "wires": []
  },
  {
    "id": "941384d383dedc13",
    "type": "ui_chart",
    "z": "b65610c31cd90972",
    "name": "",
    "group": "08010342df037008",
    "order": 3,
    "width": 0,
    "height": 0,
    "label": "Calidad de agua",
    "chartType": "line",
    "legend": "false",
    "xformat": "HH:mm:ss",
    "interpolate": "bezier",
    "nodata": "",
    "dot": false,
    "ymin": "0",
    "ymax": "3000",
    "removeOlder": "3",
    "removeOlderPoints": "",
    "removeOlderUnit": "86400",
    "cutout": 0,
    "useOneColor": false,
    "useUTC": false,
    "colors": [
      "#097479",
      "#aec7e8",
      "#ff7f0e",
      "#2ca02c",
      "#98df8a",
      "#e32400",
      "#ff9896",
      "#9467bd",
      "#c5b0d5"
    ],
    "outputs": 1,
    "useDifferentColor": false,
    "className": "",
    "x": 370,
    "y": 1220,
    "wires": [
      []
    ]
  },
  {
    "id": "e43b276cf2603edf",
    "type": "ui_gauge",
    "z": "b65610c31cd90972",
    "name": "",
    "group": "cdea3b400f1138bd",
    "order": 1,
    "width": 0,
    "height": 0,
    "gtype": "compass",
    "title": "Aceleracion eje X",
    "label": "units",
    "format": "{{value}}",
    "min": "-100",
    "max": "100",
    "colors": [
      "#00b500",
      "#e6e600",
      "#ca3838"
    ],
    "seg1": "",
    "seg2": "",
    "diff": false,
    "className": "",
    "x": 850,
    "y": 2080,
    "wires": []
  },
  {
    "id": "bde73a7a5c238641",
    "type": "ui_text",
    "z": "b65610c31cd90972",
    "group": "08010342df037008",
    "order": 2,
    "width": 0,
    "height": 0,
    "name": "",
    "label": "Calidad de agua",
    "format": "{{msg.payload}}",
    "layout": "row-spread",
    "className": "",
    "style": true,
    "font": "Times New Roman,Times,serif",
    "fontSize": "14",
    "color": "#ffffff",
    "x": 370,
    "y": 1260,
    "wires": []
  },
  {
    "id": "f153d8278dabf24e",
    "type": "ui_gauge",
    "z": "b65610c31cd90972",
    "name": "",
    "group": "cdea3b400f1138bd",
    "order": 3,
    "width": 0,
    "height": 0,
    "gtype": "compass",
    "title": "Aceleracion eje Y",
    "label": "units",
    "format": "{{value}}",
    "min": "-100",
    "max": "100",
    "colors": [
      "#00b500",
      "#e6e600",
      "#ca3838"
    ],
    "seg1": "",
    "seg2": "",
    "diff": false,
    "className": "",
    "x": 390,
    "y": 1720,
    "wires": []
  },
  {
    "id": "712004eb936dfb21",
    "type": "ui_gauge",
    "z": "b65610c31cd90972",
    "name": "",
    "group": "cdea3b400f1138bd",
    "order": 5,
    "width": 0,
    "height": 0,
    "gtype": "compass",
    "title": "Aceleracion eje Z",
    "label": "units",
    "format": "{{value}}",
    "min": "-100",
    "max": "100",
    "colors": [
      "#00b500",
      "#e6e600",
      "#ca3838"
    ],
    "seg1": "",
    "seg2": "",
    "diff": false,
    "className": "",
    "x": 950,
    "y": 1420,
    "wires": []
  },
  {
    "id": "8a5f63e50051cc80",
    "type": "ui_gauge",
    "z": "b65610c31cd90972",
    "name": "",
    "group": "dbf539f017ffee3e",
    "order": 1,
    "width": 0,
    "height": 0,
    "gtype": "compass",
    "title": "Eje X",
    "label": "units",
    "format": "{{value}}",
    "min": "-100",
    "max": "100",
    "colors": [
      "#00b500",
      "#e6e600",
      "#ca3838"
    ],
    "seg1": "",
    "seg2": "",
    "diff": false,
    "className": "",
    "x": 910,
    "y": 1860,
    "wires": []
  },
  {
    "id": "9d11b8fa55f221da",
    "type": "ui_gauge",
    "z": "b65610c31cd90972",
    "name": "",
    "group": "dbf539f017ffee3e",
    "order": 3,
    "width": 0,
    "height": 0,
    "gtype": "compass",
    "title": "Eje Y",
    "label": "units",
    "format": "{{value}}",
    "min": "-100",
    "max": "100",
    "colors": [
      "#00b500",
      "#e6e600",
      "#ca3838"
    ],
    "seg1": "",
    "seg2": "",
    "diff": false,
    "className": "",
    "x": 910,
    "y": 1640,
    "wires": []
  },
  {
    "id": "a4aeb7d6e6c0e73a",
    "type": "ui_gauge",
    "z": "b65610c31cd90972",
    "name": "",
    "group": "dbf539f017ffee3e",
    "order": 5,
    "width": 0,
    "height": 0,
    "gtype": "compass",
    "title": "Eje Z",
    "label": "units",
    "format": "{{value}}",
    "min": "-100",
    "max": "100",
    "colors": [
      "#00b500",
      "#e6e600",
      "#ca3838"
    ],
    "seg1": "",
    "seg2": "",
    "diff": false,
    "className": "",
    "x": 350,
    "y": 1520,
    "wires": []
  },
  {
    "id": "d8d128a259019bb3",
    "type": "mqtt in",
    "z": "b65610c31cd90972",
    "name": "",
    "topic": "Pluvv3",
    "qos": "2",
    "datatype": "auto-detect",
    "broker": "ca70cee35108395b",
    "nl": false,
    "rap": true,
    "rh": 0,
    "inputs": 0,
    "x": 1170,
    "y": 840,
    "wires": [
      [
        "567e7989839a1122"
      ]
    ]
  },
  {
    "id": "8be9f3689fbf27ec",
    "type": "ui_text",
    "z": "b65610c31cd90972",
    "group": "08010342df037008",
    "order": 4,
    "width": 0,
    "height": 0,
    "name": "",
    "label": "Hora de la última recepción:",
    "format": "{{msg.payload}}",
    "layout": "row-spread",
    "className": "",
    "style": false,
    "font": "",
    "fontSize": 16,
    "color": "#000000",
    "x": 620,
    "y": 1300,
    "wires": []
  },
  {
    "id": "44b390594260858d",
    "type": "function",
    "z": "b65610c31cd90972",
    "name": "function 2",
    "func": "const currentTime = new Date();\nconst hours = currentTime.getHours();\nconst minutes = currentTime.getMinutes();\nconst seconds = currentTime.getSeconds();\n\nconst colombiaTime = new Date().toLocaleString(\"en-US\", { timeZone: \"America/Bogota\" }).toString();\nconst colombiaTimeString = colombiaTime.toString();\nmsg.payload = colombiaTimeString;\nreturn msg;",
    "outputs": 1,
    "timeout": 0,
    "noerr": 0,
    "initialize": "",
    "finalize": "",
    "libs": [],
    "x": 340,
    "y": 1300,
    "wires": [
      [
        "8be9f3689fbf27ec",
        "f2af7e26914c465d"
      ]
    ]
  },
  {
    "id": "3b301886b577523f",
    "type": "change",
    "z": "b65610c31cd90972",
    "name": "",
    "rules": [
      {
        "t": "set",
        "p": "Accelxv3",
        "pt": "global",
        "to": "payload",
        "tot": "msg"
      }
    ],
    "action": "",
    "property": "",
    "from": "",
    "to": "",
    "reg": false,
    "x": 850,
    "y": 2120,
    "wires": [
      []
    ]
  },
  {
    "id": "114e6cbbe5dc1314",
    "type": "change",
    "z": "b65610c31cd90972",
    "name": "",
    "rules": [
      {
        "t": "set",
        "p": "Accelyv3",
        "pt": "global",
        "to": "payload",
        "tot": "msg"
      }
    ],
    "action": "",
    "property": "",
    "from": "",
    "to": "",
    "reg": false,
    "x": 390,
    "y": 1780,
    "wires": [
      []
    ]
  },
  {
    "id": "ab63ecab680d60e8",
    "type": "change",
    "z": "b65610c31cd90972",
    "name": "",
    "rules": [
      {
        "t": "set",
        "p": "lorav3",
        "pt": "global",
        "to": "payload",
        "tot": "msg"
      }
    ],
    "action": "",
    "property": "",
    "from": "",
    "to": "",
    "reg": false,
    "x": 370,
    "y": 1340,
    "wires": [
      []
    ]
  },
  {
    "id": "f3dc6df145d7881f",
    "type": "change",
    "z": "b65610c31cd90972",
    "name": "",
    "rules": [
      {
        "t": "set",
        "p": "Accelzv3",
        "pt": "global",
        "to": "payload",
        "tot": "msg"
      }
    ],
    "action": "",
    "property": "",
    "from": "",
    "to": "",
    "reg": false,
    "x": 950,
    "y": 1480,
    "wires": [
      []
    ]
  },
  {
    "id": "4fc2a8f101417546",
    "type": "change",
    "z": "b65610c31cd90972",
    "name": "",
    "rules": [
      {
        "t": "set",
        "p": "Magxv3",
        "pt": "global",
        "to": "payload",
        "tot": "msg"
      }
    ],
    "action": "",
    "property": "",
    "from": "",
    "to": "",
    "reg": false,
    "x": 950,
    "y": 1920,
    "wires": [
      []
    ]
  },
  {
    "id": "2652b99582e17a71",
    "type": "change",
    "z": "b65610c31cd90972",
    "name": "",
    "rules": [
      {
        "t": "set",
        "p": "Magyv3",
        "pt": "global",
        "to": "payload",
        "tot": "msg"
      }
    ],
    "action": "",
    "property": "",
    "from": "",
    "to": "",
    "reg": false,
    "x": 950,
    "y": 1700,
    "wires": [
      []
    ]
  },
  {
    "id": "565d2191eebd5d20",
    "type": "change",
    "z": "b65610c31cd90972",
    "name": "",
    "rules": [
      {
        "t": "set",
        "p": "Magzv3",
        "pt": "global",
        "to": "payload",
        "tot": "msg"
      }
    ],
    "action": "",
    "property": "",
    "from": "",
    "to": "",
    "reg": false,
    "x": 390,
    "y": 1580,
    "wires": [
      []
    ]
  },
  {
    "id": "791dc00b34ac8b16",
    "type": "change",
    "z": "b65610c31cd90972",
    "name": "",
    "rules": [
      {
        "t": "set",
        "p": "Tempv3",
        "pt": "global",
        "to": "payload",
        "tot": "msg"
      }
    ],
    "action": "",
    "property": "",
    "from": "",
    "to": "",
    "reg": false,
    "x": 970,
    "y": 1200,
    "wires": [
      []
    ]
  },
  {
    "id": "567e7989839a1122",
    "type": "change",
    "z": "b65610c31cd90972",
    "name": "",
    "rules": [
      {
        "t": "set",
        "p": "Pluvv3",
        "pt": "global",
        "to": "payload",
        "tot": "msg"
      }
    ],
    "action": "",
    "property": "",
    "from": "",
    "to": "",
    "reg": false,
    "x": 1390,
    "y": 840,
    "wires": [
      []
    ]
  },
  {
    "id": "bfa1269da8c76606",
    "type": "debug",
    "z": "b65610c31cd90972",
    "name": "debug 6",
    "active": true,
    "tosidebar": true,
    "console": false,
    "tostatus": false,
    "complete": "payload",
    "targetType": "msg",
    "statusVal": "",
    "statusType": "auto",
    "x": 940,
    "y": 1080,
    "wires": []
  },
  {
    "id": "03980fef8bf1a38e",
    "type": "function",
    "z": "b65610c31cd90972",
    "name": "function 5",
    "func": "const currentTime = new Date();\nconst hours = currentTime.getHours();\nconst minutes = currentTime.getMinutes();\nconst seconds = currentTime.getSeconds();\n\nconst colombiaTime = new Date().toLocaleString(\"en-US\", { timeZone: \"America/Bogota\" }).toString();\nconst colombiaTimeString = colombiaTime.toString();\nmsg.payload = colombiaTimeString;\nreturn msg;",
    "outputs": 1,
    "timeout": 0,
    "noerr": 0,
    "initialize": "",
    "finalize": "",
    "libs": [],
    "x": 260,
    "y": 2080,
    "wires": [
      [
        "b8f3f03e9c7e1610",
        "99fc1e1bea7c3077"
      ]
    ]
  },
  {
    "id": "b8f3f03e9c7e1610",
    "type": "ui_text",
    "z": "b65610c31cd90972",
    "group": "234d855e40364df4",
    "order": 3,
    "width": 0,
    "height": 0,
    "name": "",
    "label": "Hora de la última recepción:",
    "format": "{{msg.payload}}",
    "layout": "row-spread",
    "className": "",
    "style": false,
    "font": "",
    "fontSize": 16,
    "color": "#000000",
    "x": 520,
    "y": 2020,
    "wires": []
  },
  {
    "id": "3967c8c5d28bbe78",
    "type": "ui_chart",
    "z": "b65610c31cd90972",
    "name": "",
    "group": "b4e78de0de4f6dc3",
    "order": 1,
    "width": 0,
    "height": 0,
    "label": "Distancia",
    "chartType": "line",
    "legend": "false",
    "xformat": "HH:mm:ss",
    "interpolate": "linear",
    "nodata": "",
    "dot": false,
    "ymin": "-1",
    "ymax": "80",
    "removeOlder": "3",
    "removeOlderPoints": "",
    "removeOlderUnit": "86400",
    "cutout": 0,
    "useOneColor": false,
    "useUTC": false,
    "colors": [
      "#097479",
      "#aec7e8",
      "#ff7f0e",
      "#2ca02c",
      "#98df8a",
      "#d62728",
      "#ff9896",
      "#9467bd",
      "#c5b0d5"
    ],
    "outputs": 1,
    "useDifferentColor": false,
    "className": "",
    "x": 480,
    "y": 1960,
    "wires": [
      []
    ]
  },
  {
    "id": "b033c4d751e30165",
    "type": "ui_text",
    "z": "b65610c31cd90972",
    "group": "b4e78de0de4f6dc3",
    "order": 2,
    "width": 6,
    "height": 1,
    "name": "",
    "label": "Distancia",
    "format": "{{msg.payload}}",
    "layout": "row-spread",
    "className": "",
    "style": false,
    "font": "",
    "fontSize": 16,
    "color": "#000000",
    "x": 480,
    "y": 1900,
    "wires": []
  },
  {
    "id": "99fc1e1bea7c3077",
    "type": "ui_text",
    "z": "b65610c31cd90972",
    "group": "b4e78de0de4f6dc3",
    "order": 3,
    "width": 0,
    "height": 0,
    "name": "",
    "label": "Hora de la última recepción:",
    "format": "{{msg.payload}}",
    "layout": "row-spread",
    "className": "",
    "style": false,
    "font": "",
    "fontSize": 16,
    "color": "#000000",
    "x": 740,
    "y": 1960,
    "wires": []
  },
  {
    "id": "f2af7e26914c465d",
    "type": "ui_text",
    "z": "b65610c31cd90972",
    "group": "73a5c00898f070d9",
    "order": 4,
    "width": 0,
    "height": 0,
    "name": "",
    "label": "Hora de la última recepción:",
    "format": "{{msg.payload}}",
    "layout": "row-spread",
    "className": "",
    "style": false,
    "font": "",
    "fontSize": 16,
    "color": "#000000",
    "x": 480,
    "y": 1120,
    "wires": []
  },
  {
    "id": "b8e2a6db70c58563",
    "type": "ui_text",
    "z": "b65610c31cd90972",
    "group": "73a5c00898f070d9",
    "order": 2,
    "width": 0,
    "height": 0,
    "name": "",
    "label": "Calidad de agua",
    "format": "{{msg.payload}}",
    "layout": "row-spread",
    "className": "",
    "style": true,
    "font": "Times New Roman,Times,serif",
    "fontSize": "14",
    "color": "#ffffff",
    "x": 230,
    "y": 1100,
    "wires": []
  },
  {
    "id": "2b98ccbd304f015b",
    "type": "ui_chart",
    "z": "b65610c31cd90972",
    "name": "",
    "group": "73a5c00898f070d9",
    "order": 3,
    "width": 0,
    "height": 0,
    "label": "Calidad de agua",
    "chartType": "line",
    "legend": "false",
    "xformat": "HH:mm:ss",
    "interpolate": "bezier",
    "nodata": "",
    "dot": false,
    "ymin": "0",
    "ymax": "3000",
    "removeOlder": "3",
    "removeOlderPoints": "",
    "removeOlderUnit": "86400",
    "cutout": 0,
    "useOneColor": false,
    "useUTC": false,
    "colors": [
      "#097479",
      "#aec7e8",
      "#ff7f0e",
      "#2ca02c",
      "#98df8a",
      "#e32400",
      "#ff9896",
      "#9467bd",
      "#c5b0d5"
    ],
    "outputs": 1,
    "useDifferentColor": false,
    "className": "",
    "x": 450,
    "y": 1060,
    "wires": [
      []
    ]
  },
  {
    "id": "b92bf60de3492653",
    "type": "function",
    "z": "c0d99e92e3414f7d",
    "name": "insertarS1",
    "func": "var prueba =global.get(\"accel\");\nvar accelx = global.get(\"Accelxv3\");\nvar accely = global.get(\"Accelyv3\");\nvar accelz = global.get(\"Accelzv3\");\nvar magx = global.get(\"Magxv3\");\nvar magy = global.get(\"Magyv3\");\nvar magz = global.get(\"Magzv3\"); \nvar lora = global.get(\"lorav3\");\nvar pluv = global.get(\"Pluvv3\");\nvar dist = global.get(\"Distv3\");\n\n\nmsg.topic = \"INSERT INTO sensador (id, fecha, acelx, acely, acelz, magx, magy, magz, LoRa, pluv, dist) VALUES ('ema', NOW(), '\" + accelx + \"', '\" + accely + \"', '\" + accelz + \"', '\" + magx + \"', '\" + magy + \"', '\" + magz + \"', '\" + lora + \"', '\" + pluv + \"', '\" + dist + \"');\";\n\n\nreturn msg;\n\n\n\n\n",
    "outputs": 1,
    "timeout": 0,
    "noerr": 0,
    "initialize": "",
    "finalize": "",
    "libs": [],
    "x": 210,
    "y": 100,
    "wires": [
      [
        "44a41d563af0fdc5"
      ]
    ]
  },
  {
    "id": "44a41d563af0fdc5",
    "type": "mysql",
    "z": "c0d99e92e3414f7d",
    "mydb": "188482eb62b49c74",
    "name": "",
    "x": 440,
    "y": 100,
    "wires": [
      []
    ]
  },
  {
    "id": "3e12f95b3b270776",
    "type": "ui_button",
    "z": "c0d99e92e3414f7d",
    "name": "",
    "group": "bb9b44c54d88e575",
    "order": 5,
    "width": 0,
    "height": 0,
    "passthru": true,
    "label": "Enviar Correo",
    "tooltip": "",
    "color": "",
    "bgcolor": "",
    "className": "",
    "icon": "",
    "payload": "",
    "payloadType": "date",
    "topic": "topic",
    "topicType": "msg",
    "x": 340,
    "y": 340,
    "wires": [
      [
        "44ea9c4ac458874b"
      ]
    ]
  },
  {
    "id": "4b35330a5f3a2a0a",
    "type": "inject",
    "z": "c0d99e92e3414f7d",
    "name": "",
    "props": [
      {
        "p": "payload"
      },
      {
        "p": "topic",
        "vt": "str"
      }
    ],
    "repeat": "86400",
    "crontab": "",
    "once": false,
    "onceDelay": 0.1,
    "topic": "",
    "payload": "",
    "payloadType": "date",
    "x": 130,
    "y": 360,
    "wires": [
      [
        "3e12f95b3b270776"
      ]
    ]
  },
  {
    "id": "44ea9c4ac458874b",
    "type": "ui_button",
    "z": "c0d99e92e3414f7d",
    "name": "",
    "group": "d46a6aa89299658d",
    "order": 1,
    "width": 0,
    "height": 0,
    "passthru": true,
    "label": "Enviar Correo",
    "tooltip": "",
    "color": "",
    "bgcolor": "",
    "className": "",
    "icon": "",
    "payload": "",
    "payloadType": "date",
    "topic": "topic",
    "topicType": "msg",
    "x": 540,
    "y": 220,
    "wires": [
      [
        "04e7c1dfbb4acb7d"
      ]
    ]
  },
  {
    "id": "04e7c1dfbb4acb7d",
    "type": "file in",
    "z": "c0d99e92e3414f7d",
    "name": "",
    "filename": "Lecturas.xlsx",
    "filenameType": "str",
    "format": "",
    "chunk": false,
    "sendError": false,
    "encoding": "none",
    "allProps": false,
    "x": 590,
    "y": 320,
    "wires": [
      [
        "679ebc4836a45238"
      ]
    ]
  },
  {
    "id": "679ebc4836a45238",
    "type": "function",
    "z": "c0d99e92e3414f7d",
    "name": "",
    "func": "/*Con este bloque de función se agregan el asunto del correo\nel cuerpo y el archivo adjunto\n*/\n\nmsg.attachments =\n{\n        filename: \"Lecturas.xlsx\", // name of the file that will be attached\n        content : msg.payload };\nmsg.topic=\"Datos EMAV5\";\nmsg.payload=\"Datos de distancia y lluvia tomados durante el día\";\nreturn msg;",
    "outputs": 1,
    "timeout": "",
    "noerr": 0,
    "initialize": "",
    "finalize": "",
    "libs": [],
    "x": 840,
    "y": 220,
    "wires": [
      [
        "eaba55e2ca89f3f5"
      ]
    ]
  },
  {
    "id": "eaba55e2ca89f3f5",
    "type": "e-mail",
    "z": "c0d99e92e3414f7d",
    "server": "smtp.gmail.com",
    "port": "587",
    "authtype": "BASIC",
    "saslformat": false,
    "token": "",
    "secure": false,
    "tls": false,
    "name": "jescobarm@sgc.gov.co;daocampo@sgc.gov.co;casalazar@sgc.gov.co",
    "dname": "Correos",
    "x": 900,
    "y": 420,
    "wires": []
  },
  {
    "id": "f6ad92d4d4c3c078",
    "type": "debug",
    "z": "c0d99e92e3414f7d",
    "name": "",
    "active": false,
    "tosidebar": true,
    "console": false,
    "tostatus": false,
    "complete": "payload",
    "targetType": "msg",
    "statusVal": "",
    "statusType": "auto",
    "x": 730,
    "y": 500,
    "wires": []
  },
  {
    "id": "3167e0f18d86543f",
    "type": "excel",
    "z": "c0d99e92e3414f7d",
    "name": "",
    "file": "Lecturas.xlsx",
    "x": 650,
    "y": 580,
    "wires": [
      []
    ]
  },
  {
    "id": "ab202041301eae2b",
    "type": "function",
    "z": "c0d99e92e3414f7d",
    "name": "",
    "func": "/*Código de almacenamiento de medidas:\n\nEl bloque de timestamp ejecuta este código cada hora y se realizan\nlos registros de medida de EMA en ese momento. Este código toma el timestamp actual\nlo modifica a GMT+5 y luego almacena las variables de lluvia y distancia.\n\n\n*/\n\n//Se revisa la lista almacenada en la variable global list\n\nvar myList = global.get(\"list\")||[\"Fecha y hora\", \"Lluvia wifi\", \"Distancia wifi\", \"Lluvia GPRS\", \"Distancia GPRS\"];\n\n//Se toma el timestamp, se ajusta a GMT+5 con el offset de 3600000\n\nlet unix_timestamp = msg.payload+3600000;\nvar a = new Date(unix_timestamp);\n\n//Se organiza la fecha y la hora del timestamp\n\nvar months = ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Augosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'];\nvar year = a.getFullYear();\nvar month = months[a.getMonth()];\nvar date = a.getDate();\nvar hour = a.getHours();\nvar min = a.getMinutes();\nvar sec = a.getSeconds();\nvar time = date + '/' + month + '/' + year + ' ' + hour + ':' + min + ':' + sec;\n\n\n//Se define la linea con la fecha y las variables recibidas de EMA\n\nvar extraline=[time,global.get(\"lluvia\")||0,global.get(\"distancia\")||0,global.get(\"lluvia5G\")||0,global.get(\"distancia5G\")||0];\nmyList.push(extraline);\n\n//Si la lista llega a una cota de cien valores se elimina el valor más antiguo\n\nif (myList.length>100){\n    \n    myList.shift();\n    myList.shift();\n    myList.unshift([\"Fecha y hora\", \"Lluvia wifi\", \"Distancia wifi\", \"Lluvia GPRS\", \"Distancia GPRS\"])\n    \n}\n\n//Se almacena la lista modificada en la variable global list\n\nglobal.set(\"list\",myList);\nmsg.payload=global.get(\"list\");\n\n\nreturn msg;",
    "outputs": 1,
    "timeout": "",
    "noerr": 0,
    "initialize": "",
    "finalize": "",
    "libs": [],
    "x": 380,
    "y": 540,
    "wires": [
      [
        "f6ad92d4d4c3c078",
        "3167e0f18d86543f"
      ]
    ]
  },
  {
    "id": "1a0b668bc1ab14a6",
    "type": "inject",
    "z": "c0d99e92e3414f7d",
    "name": "",
    "props": [
      {
        "p": "payload"
      },
      {
        "p": "topic",
        "vt": "str"
      }
    ],
    "repeat": "3600",
    "crontab": "",
    "once": false,
    "onceDelay": 0.1,
    "topic": "",
    "payload": "",
    "payloadType": "date",
    "x": 190,
    "y": 540,
    "wires": [
      [
        "ab202041301eae2b"
      ]
    ]
  },
  {
    "id": "e8c5e8652fc85b82",
    "type": "comment",
    "z": "c0d99e92e3414f7d",
    "name": "ENVIO DATOS",
    "info": "En este flujo se encuentran las tareas de\nadiciomaniento de los datos y de envío de\ndatos al correo.",
    "x": 580,
    "y": 760,
    "wires": []
  },
  {
    "id": "27c02d51dc95bb8b",
    "type": "debug",
    "z": "c0d99e92e3414f7d",
    "name": "debug 1",
    "active": true,
    "tosidebar": true,
    "console": false,
    "tostatus": false,
    "complete": "false",
    "statusVal": "",
    "statusType": "auto",
    "x": 780,
    "y": 1020,
    "wires": []
  },
  {
    "id": "797e91ad32d69627",
    "type": "inject",
    "z": "c0d99e92e3414f7d",
    "name": "",
    "props": [
      {
        "p": "payload"
      },
      {
        "p": "topic",
        "vt": "str"
      }
    ],
    "repeat": "",
    "crontab": "",
    "once": false,
    "onceDelay": 0.1,
    "topic": "",
    "payload": "ejecutar",
    "payloadType": "str",
    "x": 140,
    "y": 820,
    "wires": [
      [
        "94fc1042c288f379"
      ]
    ]
  },
  {
    "id": "030a38e789d42412",
    "type": "debug",
    "z": "c0d99e92e3414f7d",
    "name": "debug 2",
    "active": true,
    "tosidebar": true,
    "console": false,
    "tostatus": false,
    "complete": "payload",
    "targetType": "msg",
    "statusVal": "",
    "statusType": "auto",
    "x": 860,
    "y": 1100,
    "wires": []
  },
  {
    "id": "94fc1042c288f379",
    "type": "function",
    "z": "c0d99e92e3414f7d",
    "name": "seleccionar todo",
    "func": "msg.topic =\"SELECT * FROM nombre_tabla\";\nreturn msg;",
    "outputs": 1,
    "timeout": 0,
    "noerr": 0,
    "initialize": "",
    "finalize": "",
    "libs": [],
    "x": 320,
    "y": 840,
    "wires": [
      []
    ]
  },
  {
    "id": "087a273c07120186",
    "type": "function",
    "z": "c0d99e92e3414f7d",
    "name": "insertarS1",
    "func": "msg.topic = \"INSERT INTO emaMonitoreo.sensado (id, fecha, distancia, conductividad) VALUES('1', '2020-01-01 00:00:00', '36', '452');\"\nreturn msg;\n",
    "outputs": 1,
    "timeout": 0,
    "noerr": 0,
    "initialize": "",
    "finalize": "",
    "libs": [],
    "x": 350,
    "y": 940,
    "wires": [
      []
    ]
  },
  {
    "id": "53e11517dc395dbd",
    "type": "function",
    "z": "c0d99e92e3414f7d",
    "name": "insertarS2",
    "func": "var fecha; var dato;\ndato = global.get(\"pruebas\");\nfecha = new Date();\nvar fecha_actual = fecha.toISOString().slice(0, 19).replace(\"T\", \" \");\nmsg.topic = \"INSERT INTO nombre_tabla (fecha, columna2) VALUES ('\" + dato + \"', 2);\"\nreturn msg;",
    "outputs": 1,
    "timeout": 0,
    "noerr": 0,
    "initialize": "",
    "finalize": "",
    "libs": [],
    "x": 770,
    "y": 860,
    "wires": [
      []
    ]
  },
  {
    "id": "9c77f67690e46e42",
    "type": "inject",
    "z": "c0d99e92e3414f7d",
    "name": "",
    "props": [
      {
        "p": "payload"
      },
      {
        "p": "topic",
        "vt": "str"
      }
    ],
    "repeat": "",
    "crontab": "",
    "once": false,
    "onceDelay": 0.1,
    "topic": "",
    "payload": "insertar",
    "payloadType": "str",
    "x": 150,
    "y": 900,
    "wires": [
      [
        "087a273c07120186"
      ]
    ]
  },
  {
    "id": "fd2065e0f9674ebc",
    "type": "inject",
    "z": "c0d99e92e3414f7d",
    "name": "",
    "props": [
      {
        "p": "payload"
      },
      {
        "p": "topic",
        "vt": "str"
      }
    ],
    "repeat": "",
    "crontab": "",
    "once": false,
    "onceDelay": 0.1,
    "topic": "",
    "payload": "ejecutar",
    "payloadType": "str",
    "x": 220,
    "y": 1060,
    "wires": [
      [
        "1d5b6eb59cc4200e"
      ]
    ]
  },
  {
    "id": "1d5b6eb59cc4200e",
    "type": "function",
    "z": "c0d99e92e3414f7d",
    "name": "seleccionar todo",
    "func": "msg.topic =\"select * from emaMonitoreo.usuarios u where id ='ema1' and clave ='clave'\";\nreturn msg;",
    "outputs": 1,
    "timeout": 0,
    "noerr": 0,
    "initialize": "",
    "finalize": "",
    "libs": [],
    "x": 550,
    "y": 1200,
    "wires": [
      []
    ]
  },
  {
    "id": "0f6098193851640c",
    "type": "function",
    "z": "c0d99e92e3414f7d",
    "name": "insertarS1",
    "func": "var prueba =global.get(\"accel\");\nvar accelx = global.get(\"Accelxv3\");\nvar accely = global.get(\"Accelyv3\");\nvar accelz = global.get(\"Accelzv3\");\nvar magx = global.get(\"Magxv3\");\nvar magy = global.get(\"Magyv3\");\nvar magz = global.get(\"Magzv3\"); \nvar lora = global.get(\"lorav3\");\nvar pluv = global.get(\"Pluvv3\");\nvar dist = global.get(\"Distv3\");\n\n\nmsg.topic = \"INSERT INTO sensador (id, fecha, acelx, acely, acelz, magx, magy, magz, LoRa, pluv, dist) VALUES ('1', NOW(), '\" + accelx + \"', '\" + accely + \"', '\" + accelz + \"', '\" + magx + \"', '\" + magy + \"', '\" + magz + \"', '\" + lora + \"', '\" + pluv + \"', '\" + dist + \"');\";\n\n\nreturn msg;\n\n\n\n\n",
    "outputs": 1,
    "timeout": 0,
    "noerr": 0,
    "initialize": "",
    "finalize": "",
    "libs": [],
    "x": 610,
    "y": 940,
    "wires": [
      []
    ]
  },
  {
    "id": "726bee99c79c5602",
    "type": "function",
    "z": "c0d99e92e3414f7d",
    "name": "insertarS1",
    "func": "var prueba =global.get(\"accel\");\nvar accelx = global.get(\"Accelxv3\");\nvar accely = global.get(\"Accelyv3\");\nvar accelz = global.get(\"Accelzv3\");\nvar magx = global.get(\"Magxv3\");\nvar magy = global.get(\"Magyv3\");\nvar magz = global.get(\"Magzv3\"); \nvar lora = global.get(\"lorav3\");\nvar pluv = global.get(\"Pluvv3\");\nvar dist = global.get(\"Distv3\");\n\n\nmsg.topic = \"INSERT INTO sensador (id, fecha, acelx, acely, acelz, magx, magy, magz, LoRa, pluv, dist) VALUES ('1', NOW(), '\" + accelx + \"', '\" + accely + \"', '\" + accelz + \"', '\" + magx + \"', '\" + magy + \"', '\" + magz + \"', '\" + lora + \"', '\" + pluv + \"', '\" + dist + \"');\";\n\n\nreturn msg;\n\n\n\n\n",
    "outputs": 1,
    "timeout": 0,
    "noerr": 0,
    "initialize": "",
    "finalize": "",
    "libs": [],
    "x": 1190,
    "y": 200,
    "wires": [
      [
        "c3fe3bdf278d48ff"
      ]
    ]
  },
  {
    "id": "00973633dfbe1d69",
    "type": "function",
    "z": "c0d99e92e3414f7d",
    "name": "seleccionar todo",
    "func": "msg.topic =\"select * from emaMonitoreo.usuarios;\";\n\nreturn msg\n\n",
    "outputs": 1,
    "timeout": 0,
    "noerr": 0,
    "initialize": "",
    "finalize": "",
    "libs": [],
    "x": 1170,
    "y": 500,
    "wires": [
      [
        "c3fe3bdf278d48ff"
      ]
    ]
  },
  {
    "id": "c3fe3bdf278d48ff",
    "type": "mysql",
    "z": "c0d99e92e3414f7d",
    "mydb": "188482eb62b49c74",
    "name": "DBEMA",
    "x": 1340,
    "y": 340,
    "wires": [
      [
        "269f803ea10d84d1"
      ]
    ]
  },
  {
    "id": "269f803ea10d84d1",
    "type": "debug",
    "z": "c0d99e92e3414f7d",
    "name": "debug 3",
    "active": true,
    "tosidebar": true,
    "console": false,
    "tostatus": false,
    "complete": "payload",
    "targetType": "msg",
    "statusVal": "",
    "statusType": "auto",
    "x": 1700,
    "y": 140,
    "wires": []
  },
  {
    "id": "ebee99d2951171e1",
    "type": "inject",
    "z": "c0d99e92e3414f7d",
    "name": "",
    "props": [
      {
        "p": "payload"
      },
      {
        "p": "topic",
        "vt": "str"
      }
    ],
    "repeat": "",
    "crontab": "",
    "once": false,
    "onceDelay": 0.1,
    "topic": "",
    "payload": "",
    "payloadType": "date",
    "x": 950,
    "y": 80,
    "wires": [
      [
        "726bee99c79c5602"
      ]
    ]
  },
  {
    "id": "dae4212a79c231ee",
    "type": "function",
    "z": "cc1467b6fff338fc",
    "name": "function 4",
    "func": "var state = global.get('chatID:'+msg.payload.chatId.toString())||1\n\n//ESTADO 0 INICIALIZACIÓN\n/*\nif (state==0){\n\nif (msg.payload.content.toLowerCase() == \"/start\") {\n    global.set(msg.payload.chatId.toString(), 1)\n    msg.payload = {\n        \"chatId\": msg.payload.chatId,\n        \"type\": \"message\",\n        \"content\": \"Iniciado, escribe 'hola' para comenzar\"\n    }\n}\nelse{\n\n    msg.payload = {\n        \"chatId\": msg.payload.chatId,\n        \"type\": \"message\",\n        \"content\": \"Mensaje Inválido\"\n    }\n\n}\n\n\n}\n\n*/\n//ESTADO 1 BIENVENIDA\n\nif (state==1){\n\n\nif (msg.payload.content.toLowerCase()==\"hola\"){\n    global.set('chatID:'+msg.payload.chatId.toString(), 2)\nmsg.payload = {\n    \"chatId\": msg.payload.chatId,\n    \"type\": \"message\",\n    \"content\": \"Bienvenido al servicio de Mensajería Instantánea de EMA\\n\\\n    Por favor Digita el ID de EMA que quieres y la contraseña separados por el caracter ',' \"\n}\n}\n\nelse{\n    msg.payload = {\n        \"chatId\": msg.payload.chatId,\n        \"type\": \"message\",\n        \"content\": \"Mensaje Inválido, escribe 'hola' para iniciar\"\n    }\n\n}\n\n\n\n}\n\n//ESTADO 2\n\nif(state == 2){\n\n    //check database\n\n    if (msg.payload.content == \"ema,clave\") {\n        global.set('chatID:'+msg.payload.chatId.toString(), 3)\n        msg.payload = {\n            \"chatId\": msg.payload.chatId,\n            \"type\": \"message\",\n            \"content\": \"Login Correcto la EMA registrada es de versión 3\"\n        }\n    }\n    else if (msg.payload.content.toLowerCase() == \"salir\"){\n        global.set('chatID:'+msg.payload.chatId.toString(), 1)\n        msg.payload = {\n            \"chatId\": msg.payload.chatId,\n            \"type\": \"message\",\n            \"content\": \"Regreso al inicio escribe 'hola' para iniciar\"\n        }\n    }\n    else{\n        msg.payload = {\n            \"chatId\": msg.payload.chatId,\n            \"type\": \"message\",\n            \"content\": \"Login incorrecto. Porfavor Digita el ID de EMA que quieres y la contraseña separados por el caracter ','\"\n        }\n\n    }\n\n\n}\n\n\n//ESTADO 3\n\nif (state == 3) {\n\n        if (msg.payload.content.toLowerCase() == \"salir\") {\n            global.set('chatID:'+msg.payload.chatId.toString(), 1)\n        msg.payload = {\n            \"chatId\": msg.payload.chatId,\n            \"type\": \"message\",\n            \"content\": \"Regreso al inicio, escribe 'hola' para comenzar\"\n        }\n    }\n}\n\nreturn msg;",
    "outputs": 1,
    "timeout": 0,
    "noerr": 0,
    "initialize": "",
    "finalize": "",
    "libs": [
      {
        "var": "mssql",
        "module": "mssql"
      }
    ],
    "x": 480,
    "y": 140,
    "wires": [
      [
        "78e05b90f4274724"
      ]
    ]
  },
  {
    "id": "4a18e648b446bf99",
    "type": "debug",
    "z": "cc1467b6fff338fc",
    "name": "debug 4",
    "active": true,
    "tosidebar": true,
    "console": false,
    "tostatus": false,
    "complete": "false",
    "statusVal": "",
    "statusType": "auto",
    "x": 460,
    "y": 200,
    "wires": []
  },
  {
    "id": "78e05b90f4274724",
    "type": "telegram sender",
    "z": "cc1467b6fff338fc",
    "name": "",
    "bot": "86403cbab9c5bcfb",
    "haserroroutput": false,
    "outputs": 1,
    "x": 690,
    "y": 140,
    "wires": [
      []
    ]
  },
  {
    "id": "4cc218a4dfe0d511",
    "type": "telegram receiver",
    "z": "cc1467b6fff338fc",
    "name": "",
    "bot": "86403cbab9c5bcfb",
    "saveDataDir": "",
    "filterCommands": false,
    "x": 170,
    "y": 120,
    "wires": [
      [
        "dae4212a79c231ee",
        "4a18e648b446bf99"
      ],
      []
    ]
  }
]