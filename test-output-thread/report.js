$(document).ready(function() {
CucumberHTML.timelineItems.pushArray([
  {
    "id": "3eca34ec-fc61-4fbd-8c57-796bd2f99f98",
    "feature": "To create a new product in paypal",
    "scenario": "To create new product using JSON data",
    "start": 1720972940344,
    "group": 17,
    "content": "",
    "tags": "@bookerapi,@createproduct,@createproductfromjson,",
    "end": 1720972949481,
    "className": "passed"
  },
  {
    "id": "0c3284bc-0804-481a-a424-00706dea1465",
    "feature": "To create a new product in paypal",
    "scenario": "To create new product using cucumber Data Table",
    "start": 1720972940340,
    "group": 16,
    "content": "",
    "tags": "@bookerapi,@createproduct,@createviewupdateproductdatatable,@ignore,",
    "end": 1720972940914,
    "className": "failed"
  }
]);
CucumberHTML.timelineGroups.pushArray([
  {
    "id": 16,
    "content": "Thread[TestNG-PoolService-0,5,main]"
  },
  {
    "id": 17,
    "content": "Thread[TestNG-PoolService-1,5,main]"
  }
]);
});