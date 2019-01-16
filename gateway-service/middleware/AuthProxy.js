const protectedUrls = require('./AuthRoutes')
const jwt = require('jsonwebtoken')

const isUrlProtected = (url) => {
  return protectedUrls.filter(el => el.test(url)).length > 0
}

/**
 * checks if the request is accessible by validating it against a bunch of
 * regular expressions found in the AuthRoutes.js file
 */
class Protector {
  constructor(req) {
    this.req = req
    this.token = this.req.headers['authorization']
  }

  // if the verification fails because the signing of the token this will throw an exception
  isValid() {
    if (!this.token) {
      return false
    }
    return jwt.verify(this.token, process.env.JWT_KEY)
  }
}

// will permit all the routes with paths not matching the regular expressions
// provided by the AuthRoutes.js file, otherwise it will delegate the validation
// to the Protector class, failure to validate the token will be handled by returning
// a {401 UNAUTHORIZED} status to the caller and will block the request accordingly
const AuthProxy = (req, res, next) => {
  if (isUrlProtected(req.path)) {
    const protector = new Protector(req)
    try {
      const unsigned = protector.isValid()
      req.jwt_user = unsigned
      next()
    } catch (e) {
      res.status(401)
      res.end()
    }
  } else {
    next()
  }
}

module.exports = AuthProxy