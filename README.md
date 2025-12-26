# rest-assured-api-automation

REST API automation framework using Rest-Assured, TestNG, and ExtentReports for comprehensive API testing.

## 🚀 Features

- **Rest-Assured 5.4.0**: Modern REST API testing library
- **TestNG 7.8.0**: Powerful testing framework with data providers
- **ExtentReports 5.1.1**: Rich HTML reporting with modern UI
- **Multiple HTTP Methods**: GET, POST, PUT, PATCH, DELETE
- **Authentication Support**: Bearer Token, API Key, Basic Auth
- **File Operations**: Upload/Download, Multipart form data
- **Advanced Validations**: JSON Schema, Response time, Data types
- **Data-Driven Testing**: TestNG DataProvider support
- **Performance Testing**: Load testing, Concurrent requests
- **Error Handling**: Comprehensive error scenarios (4xx, 5xx)

## 📁 Project Structure

```
rest-assured-api-automation/
├── src/
│   ├── main/java/
│   │   ├── core/
│   │   │   ├── BaseTest.java          # Base test class with setup/teardown
│   │   │   └── StatusCode.java        # HTTP status code enums
│   │   ├── helper/
│   │   │   └── BaseTestHelper.java    # Utility helper methods
│   │   ├── pojo/
│   │   │   ├── CityRequest.java       # POJO for city data
│   │   │   └── PostRequestBody.java   # POJO for request bodies
│   │   └── utils/
│   │       ├── APIEndpoints.java      # Centralized API endpoints
│   │       ├── ExtentReport.java      # ExtentReports configuration
│   │       ├── JsonReader.java        # JSON file reader utility
│   │       └── PropertyReader.java    # Properties file reader
│   ├── test/java/userManagement/
│   │   ├── authenticationTests.java   # Authentication test scenarios
│   │   ├── dataDrivenTests.java       # Data-driven test cases
│   │   ├── fileOperationTests.java    # File upload/download tests
│   │   ├── getUser.java              # GET request tests
│   │   ├── performanceTests.java     # Performance & load tests
│   │   ├── postUser.java             # POST/PUT/PATCH tests
│   │   └── validationTests.java      # Advanced validation tests
│   └── resources/
│       ├── config.properties         # Configuration settings
│       └── schemas/
│           └── post-schema.json      # JSON schema for validation
├── Resources/
│   ├── TestData/                     # Test data files
│   └── TestSuites/                   # TestNG suite files
├── docs/                             # Documentation
├── reports/                          # Generated HTML reports
└── pom.xml                          # Maven dependencies
```

## 🛠️ Setup & Installation

### Prerequisites
- Java 11 or higher
- Maven 3.6+
- IDE (IntelliJ IDEA, Eclipse, VS Code)

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/ccsj2023/APIautomation.git
   cd rest-assured-api-automation
   ```

2. Install dependencies:
   ```bash
   mvn clean install
   ```

3. Run tests:
   ```bash
   mvn test
   ```

## 🧪 Test Categories

### 1. Basic API Tests (`getUser.java`, `postUser.java`)
- GET requests with query parameters
- POST/PUT/PATCH with JSON bodies
- Response validation with Hamcrest matchers
- Status code verification

### 2. Authentication Tests (`authenticationTests.java`)
```java
@Test
public void testBearerTokenAuth() {
    given()
        .header("Authorization", "Bearer test-token")
        .when()
        .get("https://httpbin.org/bearer")
        .then()
        .statusCode(200)
        .body("authenticated", equalTo(true));
}
```

### 3. File Operations (`fileOperationTests.java`)
```java
@Test
public void testFileUpload() {
    File testFile = new File("test-upload.txt");
    given()
        .multiPart("file", testFile, "text/plain")
        .when()
        .post("https://httpbin.org/post")
        .then()
        .statusCode(200);
}
```

### 4. Data-Driven Tests (`dataDrivenTests.java`)
```java
@DataProvider(name = "userIds")
public Object[][] getUserIds() {
    return new Object[][] {
        {1, "Leanne Graham"},
        {2, "Ervin Howell"},
        {3, "Clementine Bauch"}
    };
}

@Test(dataProvider = "userIds")
public void testMultipleUsers(int userId, String expectedName) {
    // Test implementation
}
```

### 5. Performance Tests (`performanceTests.java`)
- Response time validation
- Concurrent request handling
- Load testing across endpoints
- Memory usage monitoring

### 6. Advanced Validations (`validationTests.java`)
- JSON Schema validation
- Response time assertions
- Error handling (404, 400, 500)
- Data type validation

## 📊 Reporting

The framework generates detailed HTML reports using ExtentReports 5.x:

- **Location**: `reports/[timestamp]/rest-assured-api-automation-report.html`
- **Features**: Test results, screenshots, logs, system info
- **Real-time**: Reports updated during test execution

## ⚙️ Configuration

### config.properties
```properties
# API Base URLs
reqres.base.url=https://reqres.in/api
jsonplaceholder.base.url=https://jsonplaceholder.typicode.com

# Test Environment
environment=QA
browser=chrome

# Reporting
report.title=API Automation Test Report
report.name=API Test Results
```

## 🏃‍♂️ Running Tests

### Single Test
```bash
mvn test -Dtest=getUser#getUserData
```

### Test Class
```bash
mvn test -Dtest=authenticationTests
```

### Multiple Tests
```bash
mvn test -Dtest=getUser#validateGetResponseBody,authenticationTests#testAPIKeyAuth
```

### Test Suites
```bash
mvn test -DsuiteXmlFile=Resources/TestSuites/RegressionSuite.xml
```

## 📈 Test Scenarios Covered

### HTTP Methods
- ✅ GET requests with query parameters
- ✅ POST requests with JSON/Form data
- ✅ PUT requests for updates
- ✅ PATCH requests for partial updates
- ✅ DELETE requests

### Authentication
- ✅ Bearer Token authentication
- ✅ API Key authentication
- ✅ Basic authentication
- ✅ Unauthorized access handling

### Validations
- ✅ Status code verification
- ✅ Response body validation
- ✅ JSON Schema validation
- ✅ Response time validation
- ✅ Header validation
- ✅ Data type validation

### Advanced Features
- ✅ File upload/download
- ✅ Multipart form data
- ✅ Data-driven testing
- ✅ Performance testing
- ✅ Error handling
- ✅ Concurrent requests

## 🔧 Customization

### Adding New Tests
1. Create new test class in `src/test/java/userManagement/`
2. Extend `BaseTest` class
3. Use `ExtentReport` for logging
4. Follow existing naming conventions

### Adding Test Data
1. Add JSON files to `Resources/TestData/`
2. Use `JsonReader.getTestData("key")` to read data
3. Create POJOs for complex data structures

### Custom Validations
```java
@Test
public void customValidation() {
    ExtentReport.extentlog = ExtentReport.extentreport.createTest("customValidation", "Description");
    
    given()
        .when()
        .get("https://api.example.com/endpoint")
        .then()
        .statusCode(200)
        .body("customField", equalTo("expectedValue"));
}
```

## 🐛 Troubleshooting

### Common Issues
1. **Compilation Errors**: Ensure Java 11+ and Maven 3.6+
2. **Test Failures**: Check API endpoints are accessible
3. **Report Generation**: Verify reports directory permissions

### Debug Mode
```bash
mvn test -X -Dtest=YourTestClass
```

## 📝 Best Practices

1. **Test Organization**: Group related tests in same class
2. **Data Management**: Use external files for test data
3. **Assertions**: Use meaningful assertion messages
4. **Reporting**: Add descriptive test names and descriptions
5. **Maintenance**: Regular dependency updates

## 🤝 Contributing

1. Fork the repository
2. Create feature branch (`git checkout -b feature/new-feature`)
3. Commit changes (`git commit -am 'Add new feature'`)
4. Push to branch (`git push origin feature/new-feature`)
5. Create Pull Request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

---

## 👤 Author

**Carolina Steadham**
- GitHub: [@steadhac](https://github.com/steadhac)
- LinkedIn: [Carolina Steadham](https://linkedin.com/in/carolinacsteadham)

---

<div align="center">

**⭐ Star this repo if you find it helpful!**

Made with ❤️ and Java

</div>

**Last Updated**: December 2025  
**Framework Version**: 2.0.0  
**Project**: rest-assured-api-automation  
**Maintained by**: API Automation Team