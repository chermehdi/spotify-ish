const axios = require('axios')

/**
 * Forwards the request to the appropriate service based on the path, the path should match /service-name/service-url
 */
class Forwarder {

  constructor(req) {
    this.req = req
    this.method = req.method
    this.url = req.proxy.path
  }

  // performs the request to be returned as a Promise to the ProxyRequest middleware
  // the checking is done in order and judged based on the header types,
  // isStreamRequest() => GET / headers: { 'content-type': 'application/octet-stream' }
  // isGetRequest() => GET
  // otherwise the request is performed with no additional checking

  async request() {
    if (this.isStreamRequest()) {
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
  // TODO: why this should be deleted, when kept we don't get any response which is weird
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
    }).catch(e => console.log('catch ', e))
  } catch (e) {
    console.error('PROXY_REQUEST',e)
    res.status(500)
    res.end()
  }
}

module.exports = ProxyRequest