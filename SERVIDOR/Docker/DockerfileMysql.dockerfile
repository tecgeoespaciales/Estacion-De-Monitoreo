# Docker de MySQL

FROM mysql:latest
# Entrar en la carpeta de configuración
WORKDIR /etc/mysql/conf.d

#crear tablas de usuario
#  msg.topic = "INSERT INTO sensador (id, fecha, acelx, acely, acelz, magx, magy, magz, LoRa, pluv, dist) VALUES ('ema', NOW(), '" + accelx + "', '" + accely + "', '" + accelz + "', '" + magx + "', '" + magy + "', '" + magz + "', '" + lora + "', '" + pluv + "', '" + dist + "');";
# msg.topic = "DelETE FROM sensador WHERE id = 'ema';";
# msg.topic="CREATE TABLE PLUVIOMETRO (FECHA VARCHAR(20), PLUVIOMETRO VARCHAR(20));";