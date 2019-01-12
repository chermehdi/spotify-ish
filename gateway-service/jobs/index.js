const axios = require('axios')

class JobScheduler {

  constructor(time) {
    this.time = time
    this.registrationObject = this.constructRegistrationObject()
    this.registryUrl = process.env.REGISTRY_URL || 'http://localhost:8081'
  }

  start() {
    this.sendRegistrationObject()
    this.timerId = setInterval(() => {
      this.sendRegistrationObject()
    }, this.time)
  }

  cancel() {
    clearInterval(this.timerId)
  }

  sendRegistrationObject() {
    axios.post(this.registryUrl + '/register', this.registrationObject)
    .then(() => console.log('service registered'))
    .catch((e) => console.log('error trying to contact the service registry ',
        e.message))
  }

  constructRegistrationObject() {
    const port = +(process.env.PORT || 8085)
    const name = process.env.APP_NAME || 'streaming-service'
    const host = process.env.HOST || '127.0.0.1'
    return {port, name, host}
  }
}

module.exports = JobScheduler