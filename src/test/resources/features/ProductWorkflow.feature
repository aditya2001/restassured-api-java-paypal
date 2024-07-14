@bookerAPI @createProduct
Feature: To create a new product in paypal

  @createViewUpdateProductDataTable @ignore
  Scenario Outline: To create new product using cucumber Data Table
    Given user has access to endpoint "/v1/catalogs/products"
    When user sends a request to create a product
      | name        | type   | description   | category      | image_url     | home_url   |
      | <name>      | <type> | <description> | <category>    | <image_url>   | <home_url> |
    Then user receives the response code 201
    And user validates the response body against input message
    And user validates the response with JSON schema "createProductSchema.json"

    Given user has access to endpoint "/v1/catalogs/products"
    And user sends a request to view details of a product ID
    Then user receives the response code 200
    And user validates the response with JSON schema "createProductSchema.json"

    Given user has access to endpoint "/v1/catalogs/products"
    And user sends a request to update the details of a product
      | op     | path    | value     |
      | <op>   | <path>  | <value>   |
    And user sends a request to view details of a product ID



    Examples:
      | name                    | type    | description            | category | image_url                         | home_url                 |  op     |  path        |  value  |
      | Video Streaming Service | SERVICE | Video Streaming Service| SOFTWARE | https://example.com/streaming.jpg | https://example.com/home | replace | /description | Premium video|


  @createProductFromJSON
  Scenario Outline: To create new product using JSON data
    Given user has access to endpoint "/v1/catalogs/products"
    When user creates a product using data "<dataKey>" from JSON file "<JSONFile>"
    Then user receives the response code 201
    And user validates the response with JSON schema "createProductSchema.json"

    And user sends a request to view details of a product ID
    Then user receives the response code 200
    And user validates the response with JSON schema "createProductSchema.json"

    Given user has access to endpoint "/v1/catalogs/products"
    And user sends a request to update product details using data "<updateDataKey>" from JSON file "<JSONFile>"
    And user sends a request to view details of a product ID
    Examples:
      | dataKey        | JSONFile         | updateDataKey    |
      | createProduct1 | productBody.json |  updateBooking1  |

