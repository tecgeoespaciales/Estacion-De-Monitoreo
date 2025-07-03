# configuración de mosquitto
FROM eclipse-mosquitto:latest

# entrar en carpeta y actualizar archivo de password en un solo paso
#WORKDIR /mosquitto/passwd
#RUN chmod 0700 /mosquitto/passwd/mosquitto.passwd \
#	chown root:root /mosquitto/passwd/mosquitto.passwd  \
	#mosquitto_passwd -U mosquitto.passwd  \
#	chown root:root /mosquitto/passwd/mosquitto.passwd
