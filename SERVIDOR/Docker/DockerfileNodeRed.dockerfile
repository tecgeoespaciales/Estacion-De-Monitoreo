FROM nodered/node-red:latest

# Install additional Node-RED nodes
RUN npm install --unsafe-perm \
    node-red-dashboard \
    node-red-node-mysql \
    node-red-contrib-telegrambot \
    node-red-node-email \
    node-red-contrib-excel

