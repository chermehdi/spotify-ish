const express = require('express')
const bodyParser = require('body-parser')

require('dotenv').config()

const RegistryJob = require('./jobs')
const {ServiceResolver, ProxyRequest, AuthProxy} = require('./middleware')

const app = express()

// create the registration job
const scheduledRegistry = new RegistryJob(60 * 1000)

// for converting the request body into json
app.use(bodyParser.json())

// the authentication proxy is checked first, for early failure of the request
// returns 401 in case of the failure
app.use(AuthProxy)

// do service resolution and add the proxy entry, otherwise
// do an early 404 return
app.use(ServiceResolver)

// proxy the request and send it to the real server
app.use(ProxyRequest)

// this should never be hit
app.use(async (req, res, next) => {
  res.json(req.proxy)
})

const port = process.env.PORT || 8080

app.listen(port, () => {
  console.log(`Server started listening on port http://localhost:${port}/`)
  // will execute every 60 second
  scheduledRegistry.start()
})

