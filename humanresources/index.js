"use strict"
const axios = require("axios")

exports.getResource = endpoint => {
  const url = endpoint.url
  const id = endpoint.id

  console.log(endpoint)

  return axios
    .request({
      method: "GET",
      baseURL: url,
      url: "/client/" + id,
      headers: { Accept: "application/json" },
    })
    .then(response => response.data)
}