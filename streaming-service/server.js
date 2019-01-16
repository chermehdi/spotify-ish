const express = require('express')
const bodyParser = require('body-parser')
require('dotenv').config()
const RegistryJob = require('./jobs')
const Store = require('./store')
const fs = require('fs')

const app = express()
const musicStore = new Store()

const scheduledRegistry = new RegistryJob(60 * 1000)

app.use(/\/api\/.*/, bodyParser.json())

app.get('/:artist/:song', async (req, res) => {
  const {artist, song} = req.params
  const filePath = musicStore.resolve(song)
  console.log(filePath)
  res.set('Content-type', 'application/octet-stream')
  fs.createReadStream(filePath)
  .pipe(res)
})

const port = process.env.PORT || 8085
app.listen(port, () => {
  console.log(`Server started listening on port http://localhost:${port}/`)
  // 60 second
  scheduledRegistry.start()
})

