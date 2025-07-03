module.exports = {
    uiPort: process.env.PORT || 1880,
    userDir: './data/',
    logging: {
        console: {
            level: "info",
            metrics: false,
            audit: false
        }
    },
    adminAuth: {
        type: "credentials",
        users: [
            {
                username: "admin",
                password: "$2a$08$ins0cekq7U3sdVdXIFGhKOwzVJQiWzVAt3uhG0.3Fp0TZS4TGdhGK",
                permissions: "*"
            }
        ]
    },

    https: {
        key: require("fs").readFileSync("/data/certs/server-key.key"),
        cert: require("fs").readFileSync("/data/certs/server-cert.crt")
     }
};
