const axios = require('axios')

const REGISTRY = process.env.REGISTRY_URL

const getServiceInfo = (serviceName) => {
  const url = REGISTRY + `/resolve/${serviceName}`
  return axios.get(url)
}

const constructUrl = (url, serviceInfo) => {
  console.log('constructed url',
      `http://${serviceInfo.host}:${serviceInfo.port}/${url}`)
  return `http://${serviceInfo.host}:${serviceInfo.port}/${url}`
}

// Resolves a service's information { port, host } based on it's name
// adds a proxy object to the request to be used by future middleware to indicate the target service's actual url
class Resolver {

  constructor(path) {
    this.path = path
    this.extractPathSegments(path);
  }

  extractPathSegments(path) {
    this.segments = path.split('/').filter(value => value.length > 0)
    this.serviceName = this.segments[0]
    this.segments.shift()
    this.serviceTargetUrl = this.segments.join("/")
  }

  async resolve() {
    return await getServiceInfo(this.serviceName)
  }
}

const ServiceResolver = async (req, res, next) => {
  const resolver = new Resolver(req.path)
  try {
    const serviceInfoResponse = await resolver.resolve()

    req.proxy = {
      path: constructUrl(resolver.serviceTargetUrl, serviceInfoResponse.data)
    }

    next()
  } catch (e) {
    console.log('RESOLVER: ', e)
    res.status(404)
    res.end()
  }
}

module.exports = ServiceResolver