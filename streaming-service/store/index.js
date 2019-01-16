const os = require('os')
const path = require('path')
const mappings = require('./mappings')

class MusicStore {
  constructor() {
    this.base = path.join(os.homedir(), 'junk', 'micro-data')
  }

  resolve(reference) {
    return path.join(this.base, mappings[reference])
  }
}

module.exports = MusicStore