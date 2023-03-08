import Keycloak from 'keycloak-js'

const keycloak = new Keycloak({
    realm: "movie-lib",
    url: "http://localhost:8078/",
    clientId: "movie-lib",
  })

export default keycloak