// const {join} = require('path');
// const express = require('express');
// const {hostname} = require('os');
// const fetch = require('node-fetch');
//
const axios = require('axios');

async function test() {
    const response = axios.get('http://172.16.238.10:5000/votes');
    console.log(response);
}

test()

//
//
// const HOST = '0.0.0.0';
// const PORT = process.env.PORT || 80;
//
// const DB_HOST = process.env.DB_HOST;
// const DB_PORT = process.env.DB_PORT;
//
// console.log('Starting app.');
// if (process.env.NODE_ENV === 'production') {
//     console.log(`• Mode: ${process.env.NODE_ENV}`);
//     console.log(`• DB_HOST: ${DB_PORT}`);
//     console.log(`• DB_PORT: ${DB_PORT}`);
//     if (!DB_HOST || !DB_HOST) {
//         console.error('Failure: DB_HOST or DB_PORT are not defined');
//         process.exit(1);
//     }
// }
//
// const app = express();
//
// app.get('/', (req, res) => {
//     console.log('Test');
//     res.sendFile(join(__dirname, 'index.html'));
// });
//
// app.get('/ip', (req, res) => {
//     res.json({
//         "remote_address": req.connection.remoteAddress,
//         "remote_port": req.connection.remotePort
//     })
// });
//
// app.get('/results', async (req, res) => {
//     try {
//         console.log('DIO BESTIA')
//         const response = await axios.get('http://172.16.238.10:5000/votes');
//         console.log(response);
//         console.log(response.status);
//         console.log(response.data);
//     } catch (error) {
//         console.error(error);
//     }
//
// });
//
// app.listen(PORT, HOST, () => {
//     console.log('App running!');
//     console.log(`• Host: ${hostname()}`);
//     console.log(`• Port: ${PORT}`);
// });
