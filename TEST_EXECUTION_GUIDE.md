# Test Execution Guide

## 🎯 Quick Start

### Run All Tests
```bash
mvn clean test
```

### Run Specific Test Categories

#### 1. Authentication Tests
```bash
mvn test -Dtest=authenticationTests
```
**Tests included:**
- Bearer token authentication
- API key validation
- Basic auth success/failure
- Unauthorized access handling

#### 2. File Operation Tests
```bash
mvn test -Dtest=fileOperationTests
```
**Tests included:**
- File upload functionality
- Multipart form data
- File download validation
- Image download with binary content

#### 3. Data-Driven Tests
```bash
mvn test -Dtest=dataDrivenTests
```
**Tests included:**
- Multiple user validation (5 users)
- Multiple post validation (5 posts)
- HTTP status code testing
- Boundary value testing

#### 4. Performance Tests
```bash
mvn test -Dtest=performanceTests
```
**Tests included:**
- Response time validation
- Concurrent request handling
- Load testing across endpoints
- Memory usage validation
- Timeout handling

#### 5. Advanced Validation Tests
```bash
mvn test -Dtest=validationTests
```
**Tests included:**
- JSON schema validation
- Response time assertions
- Error handling (404, 400)
- Data type validation
- Header validation

## 🎮 Individual Test Execution

### GET Request Tests
```bash
# Basic GET request
mvn test -Dtest=getUser#getUserData

# Response body validation
mvn test -Dtest=getUser#validateGetResponseBody

# Query parameter tests
mvn test -Dtest=getUser#testGetUsersWithQueryParameters

# Hamcrest matchers
mvn test -Dtest=getUser#validateResponseHasItems
```

### POST/PUT/PATCH Tests
```bash
# POST with string body
mvn test -Dtest=postUser#validatePostWithString

# POST with JSON file
mvn test -Dtest=postUser#validatePostWithJsonFile

# POST with POJO
mvn test -Dtest=postUser#validatePutWithPojo

# Complex POJO with lists
mvn test -Dtest=postUser#validatePostWithPojoListObject
```

### Authentication Tests
```bash
# Bearer token
mvn test -Dtest=authenticationTests#testBearerTokenAuth

# API key
mvn test -Dtest=authenticationTests#testAPIKeyAuth

# Basic auth
mvn test -Dtest=authenticationTests#testBasicAuthSuccess

# Unauthorized access
mvn test -Dtest=authenticationTests#testUnauthorizedAccess
```

## 📊 Test Suites

### Smoke Suite
```bash
mvn test -DsuiteXmlFile=Resources/TestSuites/PackageLevelSmokeSuite.xml
```

### Regression Suite
```bash
mvn test -DsuiteXmlFile=Resources/TestSuites/RegressionSuite.xml
```

### Custom Suite
```bash
mvn test -DsuiteXmlFile=Resources/TestSuites/ClassLevelSuite.xml
```

## 🔍 Test Filtering

### By Groups
```bash
# Run only smoke tests
mvn test -Dgroups=SmokeSuite

# Run regression tests
mvn test -Dgroups=RegressionSuite

# Run multiple groups
mvn test -Dgroups="SmokeSuite,RegressionSuite"
```

### By Test Methods
```bash
# Multiple specific tests
mvn test -Dtest=getUser#validateGetResponseBody,authenticationTests#testAPIKeyAuth,dataDrivenTests#testMultipleUsers
```

## 📈 Parallel Execution

### Configure in pom.xml
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <configuration>
        <parallel>methods</parallel>
        <threadCount>3</threadCount>
    </configuration>
</plugin>
```

### Run with parallel execution
```bash
mvn test -Dparallel=methods -DthreadCount=3
```

## 📋 Test Results

### Console Output
- Real-time test execution status
- Pass/Fail counts
- Execution time
- Error details

### HTML Reports
- **Location**: `reports/[timestamp]/API_Execution_Automation.html`
- **Content**: Detailed test results, logs, system info
- **Features**: Interactive dashboard, charts, filters

### Surefire Reports
- **Location**: `target/surefire-reports/`
- **Format**: XML and TXT files
- **Usage**: CI/CD integration

## 🐛 Debugging Failed Tests

### Verbose Output
```bash
mvn test -Dtest=YourTestClass -X
```

### Debug Mode
```bash
mvn test -Dtest=YourTestClass -Dmaven.surefire.debug
```

### Skip Tests
```bash
mvn clean install -DskipTests
```

### Run Only Failed Tests
```bash
mvn test -Dsurefire.rerunFailingTestsCount=2
```

## 🔧 Environment Configuration

### Different Environments
```bash
# QA Environment
mvn test -Denvironment=QA

# Staging Environment  
mvn test -Denvironment=STAGING

# Production Environment
mvn test -Denvironment=PROD
```

### Custom Properties
```bash
mvn test -Dapi.base.url=https://custom-api.com -Dapi.timeout=5000
```

## 📊 Performance Monitoring

### Response Time Monitoring
```bash
# Run performance tests only
mvn test -Dtest=performanceTests

# With custom timeout
mvn test -Dtest=performanceTests -Dapi.timeout=3000
```

### Memory Usage
```bash
# Run with memory profiling
mvn test -Xmx2g -XX:+PrintGCDetails
```

## 🚀 CI/CD Integration

### Jenkins
```bash
mvn clean test -Dmaven.test.failure.ignore=true
```

### GitHub Actions
```yaml
- name: Run Tests
  run: mvn clean test
  
- name: Generate Report
  run: mvn surefire-report:report
```

### Docker
```bash
docker run -v $(pwd):/app -w /app maven:3.8-openjdk-11 mvn clean test
```

## 📝 Best Practices

### Test Execution Order
1. **Smoke Tests** - Quick validation
2. **Regression Tests** - Full feature validation  
3. **Performance Tests** - Load and stress testing
4. **Data-Driven Tests** - Multiple scenarios

### Error Handling
- Always check test reports for failures
- Re-run failed tests to confirm issues
- Check API endpoint availability
- Verify test data integrity

### Maintenance
- Regular dependency updates
- Clean old reports: `rm -rf reports/*`
- Update test data as needed
- Monitor API changes

---

**Need Help?** Check the main README.md or create an issue in the repository.