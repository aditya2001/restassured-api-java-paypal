# **restassured-paypal-api-automation**

### **Overview**
API is application program interface, it's a piece of code used locally to perform specific operation like Apache POI, Selenium jars. When an API is available over the network for interaction between 2 applications then its called as Web Services API.
This API automation framework is developed using RestAssured with Cucumber.Rest Assured is a Java library that provides interfaces and classes using which you can send API request and receive responses. Cucumber is an open source library, which supports behavior driven development. 
To be more precise, Cucumber can be defined as a testing framework, driven by plain English text. This API automation is done on PayPal API's which is secured by OAuth2.0. We are generating a Token based authentication programmatically before making api request.


### **Some of the key features of this framework:**
1. It generates Extent report with all the step details. Report will be generated both HTML & PDF file format.
2. Generates execution logs, with detailed request and response details.
3. Feature file has examples of reading request details from json and cucumber Datatable.
4. This also has an example to validate response body using json schema and java pojo classes.
5. Test execution can be triggered form command line.
6. Easy integration to CI/CD pipeline.

### **Running Test:**
Open the command prompt and navigate to the folder in which pom.xml file is present. Run the below Maven command.

mvn clean test
Once the execution completes report & log will be generated in below folder.

Report: target/report
Log: target/logs