"use strict"
const { pactWith } = require("jest-pact")
const { Matchers } = require("@pact-foundation/pact")

const { getResource } = require("../index")

pactWith({ consumer: "HumanResource", provider: "ClientProvider" }, provider => {
  describe("Resources API", () => {
    const RESOURCES_DATA = 
      {
        name: "José Maria",
        cpf: "94168764089"
      }

    const resourcesSuccessResponse = {
      status: 200,
      headers: {
        "Content-Type": "application/json",
      },
      body: RESOURCES_DATA,
    }

    const resourcesListRequest = {
      uponReceiving: "a request for resources",
      withRequest: {
        method: "GET",
        path: "/client/2",
        headers: {
          Accept: "application/json",
        },
      },
    }

    beforeEach(() => {
      const interaction = {
        state: "i have a list of resources",
        ...resourcesListRequest,
        willRespondWith: resourcesSuccessResponse,
      }
      return provider.addInteraction(interaction)
    })

    it("returns a successful body", () => {
      return getResource({
        url: provider.mockService.baseUrl,
        id: "2"
      }).then(resource => {
        expect(resource).toEqual(RESOURCES_DATA)
      })
    })
  })

})
