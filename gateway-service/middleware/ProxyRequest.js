const axios = require('axios')

class Forwarder {

  constructor(req) {
    this.req = req
    this.method = req.method
    this.url = req.proxy.path
  }

  async request() {
    if (this.isStreamRequest()) {
      console.log('stream request')
      return axios({
        url: this.url,
        method: this.method,
        responseType: 'stream'
      })
    } else if (this.isGetRequest()) {
      return axios.get(this.url)
    } else {
      return axios[this.method.toLowerCase()](this.url, this.req.body,
          this.req.headers)
    }
  }

  isGetRequest() {
    return this.method === 'GET'
  }

  isStreamRequest() {
    return this.isGetRequest() && this.req.headers['accept']
        === 'application/octet-stream'
  }
}

// merge the response from the service into the response returned back
// to the user
function mergeRequest(returnedResponse, responseFromService) {
  let headers = responseFromService.headers
  delete headers['transfer-encoding']
  returnedResponse.set(headers)
}

const ProxyRequest = async (req, res) => {
  const forwarder = new Forwarder(req)
  try {
    const result = forwarder.request()
    .then(response => {
      mergeRequest(res, response)
      if (response.headers['content-type'] === 'application/json') {
        res.json(response.data)
      } else {
        // this is a binary response

        // pipe the result into the response
        response.data.pipe(res)
      }
    })
  } catch (e) {
    console.error(e.message)
    res.status(500)
    res.end()
  }
}

module.exports = ProxyRequest