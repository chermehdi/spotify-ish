const express = require('express')
const bodyParser = require('body-parser')
require('dotenv').config()
const RegistryJob = require('./jobs')

const app = express()

const scheduledRegistry = new RegistryJob(60 * 1000)

app.use(/\/api\/.*/, bodyParser.json())

const port = process.env.PORT || 8085
app.listen(port, () => {
  console.log(`Server started listening on port http://localhost:${port}/`)
  // 60 second
  scheduledRegistry.start()
})

