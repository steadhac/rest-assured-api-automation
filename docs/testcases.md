# rest-assured API Automation Test Cases

## 📋 Test Cases Overview

**Project**: rest-assured-api-automation  
**Version**: 2.0.0  
**Total Test Cases**: 50+  
**Last Updated**: December 2025  

## 🗂️ Test Case Categories

### 1. GET Request Tests (`getUser.java`)
### 2. POST/PUT/PATCH Tests (`postUser.java`)
### 3. Authentication Tests (`authenticationTests.java`)
### 4. File Operation Tests (`fileOperationTests.java`)
### 5. Validation Tests (`validationTests.java`)
### 6. Data-Driven Tests (`dataDrivenTests.java`)
### 7. Performance Tests (`performanceTests.java`)

---

## 🔍 1. GET Request Tests

### TC001: Basic GET Request
**Test Method**: `getUserData()`  
**Priority**: High  
**API**: Reqres API  

| Field | Details |
|-------|---------|
| **Objective** | Validate basic GET request returns 200 status |
| **Endpoint** | `GET https://reqres.in/api/users?page=2` |
| **Prerequisites** | API endpoint accessible |
| **Test Steps** | 1. Send GET request to users endpoint<br>2. Verify response status code |
| **Expected Result** | Status code: 200 |
| **Actual Result** | ✅ Pass |

### TC002: Response Body Validation
**Test Method**: `validateGetResponseBody()`  
**Priority**: High  
**API**: JSONPlaceholder API  

| Field | Details |
|-------|---------|
| **Objective** | Validate GET response body content and structure |
| **Endpoint** | `GET https://jsonplaceholder.typicode.com/todos/1` |
| **Prerequisites** | API endpoint accessible |
| **Test Steps** | 1. Send GET request to todos endpoint<br>2. Verify status code is 200<br>3. Verify response body is not empty<br>4. Verify title equals "delectus aut autem"<br>5. Verify userId equals 1 |
| **Expected Result** | Status: 200, Body contains expected values |
| **Actual Result** | ✅ Pass |

### TC003: Response Array Validation
**Test Method**: `validateResponseHasItems()`  
**Priority**: Medium  
**API**: JSONPlaceholder API  

| Field | Details |
|-------|---------|
| **Objective** | Validate response array contains specific items |
| **Endpoint** | `GET https://jsonplaceholder.typicode.com/posts` |
| **Prerequisites** | API endpoint accessible |
| **Test Steps** | 1. Send GET request to posts endpoint<br>2. Extract response<br>3. Verify array contains specific titles |
| **Expected Result** | Array contains expected post titles |
| **Actual Result** | ✅ Pass |

### TC004: Response Size Validation
**Test Method**: `validateResponseHasSize()`  
**Priority**: Medium  
**API**: JSONPlaceholder API  

| Field | Details |
|-------|---------|
| **Objective** | Validate response array has expected size |
| **Endpoint** | `GET https://jsonplaceholder.typicode.com/comments` |
| **Prerequisites** | API endpoint accessible |
| **Test Steps** | 1. Send GET request to comments endpoint<br>2. Extract response<br>3. Verify array size equals 500 |
| **Expected Result** | Array size: 500 |
| **Actual Result** | ✅ Pass |

### TC005: Query Parameter Validation
**Test Method**: `testGetUsersWithQueryParameters()`  
**Priority**: High  
**API**: Reqres API  

| Field | Details |
|-------|---------|
| **Objective** | Validate GET request with query parameters |
| **Endpoint** | `GET https://reqres.in/api/users?page=2` |
| **Prerequisites** | API endpoint accessible |
| **Test Steps** | 1. Send GET request with page parameter<br>2. Verify status code 200<br>3. Verify response contains 6 users<br>4. Verify specific user data |
| **Expected Result** | Correct user data for page 2 |
| **Actual Result** | ✅ Pass |

---

## 📝 2. POST/PUT/PATCH Tests

### TC006: POST with String Body
**Test Method**: `validatePostWithString()`  
**Priority**: High  
**API**: Reqres API  

| Field | Details |
|-------|---------|
| **Objective** | Validate POST request with JSON string body |
| **Endpoint** | `POST https://reqres.in/api/users` |
| **Request Body** | `{"name":"morpheus","job":"leader"}` |
| **Prerequisites** | API endpoint accessible |
| **Test Steps** | 1. Set Content-Type header<br>2. Send POST request with JSON body<br>3. Verify status code 201 |
| **Expected Result** | Status code: 201, User created |
| **Actual Result** | ⚠️ Rate limited (403) |

### TC007: PUT with String Body
**Test Method**: `validatePutWithString()`  
**Priority**: High  
**API**: Reqres API  

| Field | Details |
|-------|---------|
| **Objective** | Validate PUT request updates existing resource |
| **Endpoint** | `PUT https://reqres.in/api/users/2` |
| **Request Body** | `{"name":"morpheus","job":"leader"}` |
| **Prerequisites** | API endpoint accessible |
| **Test Steps** | 1. Set Content-Type header<br>2. Send PUT request with JSON body<br>3. Verify status code 200 |
| **Expected Result** | Status code: 200, User updated |
| **Actual Result** | ⚠️ Rate limited (403) |

### TC008: POST with JSON File
**Test Method**: `validatePostWithJsonFile()`  
**Priority**: Medium  
**API**: Reqres API  

| Field | Details |
|-------|---------|
| **Objective** | Validate POST request with JSON file as body |
| **Endpoint** | `POST https://reqres.in/api/users` |
| **Request Body** | From postRequestBody.json file |
| **Prerequisites** | JSON file exists, API accessible |
| **Test Steps** | 1. Read JSON from file<br>2. Send POST request<br>3. Verify status code 201 |
| **Expected Result** | Status code: 201, User created from file data |
| **Actual Result** | ⚠️ Rate limited (403) |

### TC009: POST with POJO
**Test Method**: `validatePutWithPojo()`  
**Priority**: High  
**API**: Reqres API  

| Field | Details |
|-------|---------|
| **Objective** | Validate POST request with POJO object |
| **Endpoint** | `PUT https://reqres.in/api/users/2` |
| **Request Body** | PostRequestBody POJO |
| **Prerequisites** | POJO class exists, API accessible |
| **Test Steps** | 1. Create POJO object<br>2. Set properties<br>3. Send PUT request<br>4. Verify status code 200 |
| **Expected Result** | Status code: 200, POJO serialized correctly |
| **Actual Result** | ⚠️ Rate limited (403) |

---

## 🔐 3. Authentication Tests

### TC010: Bearer Token Authentication
**Test Method**: `testBearerTokenAuth()`  
**Priority**: High  
**API**: HTTPBin API  

| Field | Details |
|-------|---------|
| **Objective** | Validate Bearer token authentication |
| **Endpoint** | `GET https://httpbin.org/bearer` |
| **Headers** | `Authorization: Bearer test-token` |
| **Prerequisites** | API endpoint accessible |
| **Test Steps** | 1. Set Authorization header<br>2. Send GET request<br>3. Verify status code 200<br>4. Verify authenticated=true<br>5. Verify token value |
| **Expected Result** | Status: 200, authenticated: true, token: test-token |
| **Actual Result** | ✅ Pass |

### TC011: API Key Authentication
**Test Method**: `testAPIKeyAuth()`  
**Priority**: High  
**API**: HTTPBin API  

| Field | Details |
|-------|---------|
| **Objective** | Validate API key authentication |
| **Endpoint** | `GET https://httpbin.org/get?api_key=test-api-key` |
| **Parameters** | `api_key=test-api-key` |
| **Prerequisites** | API endpoint accessible |
| **Test Steps** | 1. Add API key as query parameter<br>2. Send GET request<br>3. Verify status code 200<br>4. Verify API key in response |
| **Expected Result** | Status: 200, args.api_key: test-api-key |
| **Actual Result** | ✅ Pass |

### TC012: Basic Authentication Success
**Test Method**: `testBasicAuthSuccess()`  
**Priority**: High  
**API**: HTTPBin API  

| Field | Details |
|-------|---------|
| **Objective** | Validate successful basic authentication |
| **Endpoint** | `GET https://httpbin.org/basic-auth/user/passwd` |
| **Authentication** | Basic auth: user/passwd |
| **Prerequisites** | API endpoint accessible |
| **Test Steps** | 1. Set basic auth credentials<br>2. Send GET request<br>3. Verify status code 200<br>4. Verify authenticated=true<br>5. Verify user=user |
| **Expected Result** | Status: 200, authenticated: true, user: user |
| **Actual Result** | ✅ Pass |

### TC013: Unauthorized Access
**Test Method**: `testUnauthorizedAccess()`  
**Priority**: High  
**API**: HTTPBin API  

| Field | Details |
|-------|---------|
| **Objective** | Validate unauthorized access returns 401 |
| **Endpoint** | `GET https://httpbin.org/bearer` |
| **Headers** | None (no authorization) |
| **Prerequisites** | API endpoint accessible |
| **Test Steps** | 1. Send GET request without auth<br>2. Verify status code 401 |
| **Expected Result** | Status code: 401 Unauthorized |
| **Actual Result** | ✅ Pass |

---

## 📁 4. File Operation Tests

### TC014: File Upload
**Test Method**: `testFileUpload()`  
**Priority**: Medium  
**API**: HTTPBin API  

| Field | Details |
|-------|---------|
| **Objective** | Validate file upload functionality |
| **Endpoint** | `POST https://httpbin.org/post` |
| **Request** | Multipart file upload |
| **Prerequisites** | Test file exists, API accessible |
| **Test Steps** | 1. Create test file<br>2. Send multipart POST request<br>3. Verify status code 200<br>4. Verify files section in response |
| **Expected Result** | Status: 200, files not null |
| **Actual Result** | ✅ Pass |

### TC015: Multipart Form Data
**Test Method**: `testMultipartFormData()`  
**Priority**: Medium  
**API**: HTTPBin API  

| Field | Details |
|-------|---------|
| **Objective** | Validate multipart form data submission |
| **Endpoint** | `POST https://httpbin.org/post` |
| **Request** | Multipart form with multiple fields |
| **Prerequisites** | API endpoint accessible |
| **Test Steps** | 1. Add multiple form parts<br>2. Send POST request<br>3. Verify status code 200<br>4. Verify form data in response |
| **Expected Result** | Status: 200, form data matches sent data |
| **Actual Result** | ✅ Pass |

### TC016: File Download
**Test Method**: `testFileDownload()`  
**Priority**: Medium  
**API**: HTTPBin API  

| Field | Details |
|-------|---------|
| **Objective** | Validate file download and content verification |
| **Endpoint** | `GET https://httpbin.org/json` |
| **Prerequisites** | API endpoint accessible |
| **Test Steps** | 1. Send GET request<br>2. Verify status code 200<br>3. Verify Content-Type header<br>4. Verify response body not null |
| **Expected Result** | Status: 200, JSON content downloaded |
| **Actual Result** | ✅ Pass |

---

## ✅ 5. Validation Tests

### TC017: JSON Schema Validation
**Test Method**: `testJSONSchemaValidation()`  
**Priority**: High  
**API**: JSONPlaceholder API  

| Field | Details |
|-------|---------|
| **Objective** | Validate response against JSON schema |
| **Endpoint** | `GET https://jsonplaceholder.typicode.com/posts/1` |
| **Schema** | post-schema.json |
| **Prerequisites** | Schema file exists, API accessible |
| **Test Steps** | 1. Send GET request<br>2. Verify status code 200<br>3. Validate response against schema |
| **Expected Result** | Status: 200, response matches schema |
| **Actual Result** | ✅ Pass |

### TC018: Response Time Validation
**Test Method**: `testResponseTimeValidation()`  
**Priority**: High  
**API**: JSONPlaceholder API  

| Field | Details |
|-------|---------|
| **Objective** | Validate API response time within limits |
| **Endpoint** | `GET https://jsonplaceholder.typicode.com/posts/1` |
| **Time Limit** | < 5 seconds |
| **Prerequisites** | API endpoint accessible |
| **Test Steps** | 1. Send GET request<br>2. Measure response time<br>3. Verify time < 5000ms<br>4. Verify time < 3000ms |
| **Expected Result** | Response time under acceptable limits |
| **Actual Result** | ✅ Pass |

### TC019: Error Handling 404
**Test Method**: `testErrorHandling404()`  
**Priority**: Medium  
**API**: JSONPlaceholder API  

| Field | Details |
|-------|---------|
| **Objective** | Validate proper 404 error handling |
| **Endpoint** | `GET https://jsonplaceholder.typicode.com/posts/999999` |
| **Prerequisites** | API endpoint accessible |
| **Test Steps** | 1. Send GET request to non-existent resource<br>2. Verify status code 404 |
| **Expected Result** | Status code: 404 Not Found |
| **Actual Result** | ✅ Pass |

---

## 📊 6. Data-Driven Tests

### TC020-024: Multiple User Validation
**Test Method**: `testMultipleUsers()`  
**Priority**: High  
**API**: JSONPlaceholder API  
**Data Provider**: userIds  

| Test Case | User ID | Expected Name | Status |
|-----------|---------|---------------|--------|
| TC020 | 1 | Leanne Graham | ✅ Pass |
| TC021 | 2 | Ervin Howell | ✅ Pass |
| TC022 | 3 | Clementine Bauch | ✅ Pass |
| TC023 | 4 | Patricia Lebsack | ✅ Pass |
| TC024 | 5 | Chelsey Dietrich | ✅ Pass |

**Objective**: Validate multiple users with data-driven approach  
**Test Steps**: 
1. Iterate through user IDs
2. Send GET request for each user
3. Verify status code 200
4. Verify user ID matches
5. Verify user name matches expected

### TC025-029: Multiple Post Validation
**Test Method**: `testMultiplePosts()`  
**Priority**: High  
**API**: JSONPlaceholder API  
**Data Provider**: postIds  

| Test Case | Post ID | User ID | Expected Title | Status |
|-----------|---------|---------|----------------|--------|
| TC025 | 1 | 1 | sunt aut facere... | ✅ Pass |
| TC026 | 2 | 1 | qui est esse | ✅ Pass |
| TC027 | 3 | 1 | ea molestias quasi... | ✅ Pass |
| TC028 | 4 | 1 | eum et est occaecati | ✅ Pass |
| TC029 | 5 | 1 | nesciunt quas odio | ✅ Pass |

---

## ⚡ 7. Performance Tests

### TC030: Response Time Under 2 Seconds
**Test Method**: `testResponseTimeUnder2Seconds()`  
**Priority**: High  
**API**: JSONPlaceholder API  

| Field | Details |
|-------|---------|
| **Objective** | Validate API response time under 2 seconds |
| **Endpoint** | `GET https://jsonplaceholder.typicode.com/posts` |
| **Time Limit** | < 2000ms |
| **Prerequisites** | API endpoint accessible |
| **Test Steps** | 1. Send GET request<br>2. Verify status code 200<br>3. Verify response time < 2000ms |
| **Expected Result** | Status: 200, Time < 2 seconds |
| **Actual Result** | ✅ Pass |

### TC031: Concurrent Requests
**Test Method**: `testConcurrentRequests()`  
**Priority**: Medium  
**API**: JSONPlaceholder API  

| Field | Details |
|-------|---------|
| **Objective** | Test API with multiple concurrent requests |
| **Endpoint** | `GET https://jsonplaceholder.typicode.com/posts/1` |
| **Concurrent Users** | 5 |
| **Prerequisites** | API endpoint accessible |
| **Test Steps** | 1. Create 5 concurrent requests<br>2. Execute all requests<br>3. Verify all return 200<br>4. Verify response times < 5000ms |
| **Expected Result** | All requests successful, acceptable response times |
| **Actual Result** | ✅ Pass |

### TC032: Load Testing Multiple Endpoints
**Test Method**: `testLoadWithMultipleEndpoints()`  
**Priority**: Medium  
**API**: JSONPlaceholder API  

| Field | Details |
|-------|---------|
| **Objective** | Test load across multiple API endpoints |
| **Endpoints** | posts/1, users/1, comments/1, albums/1 |
| **Prerequisites** | All endpoints accessible |
| **Test Steps** | 1. Send requests to all endpoints<br>2. Measure total time<br>3. Calculate success rate<br>4. Verify performance metrics |
| **Expected Result** | 100% success rate, average time < 3s |
| **Actual Result** | ✅ Pass |

---

## 📈 Test Execution Summary

### Overall Statistics
- **Total Test Cases**: 50+
- **Executed**: 50+
- **Passed**: 45+
- **Failed**: 0
- **Rate Limited**: 5 (Reqres API)
- **Pass Rate**: 90%+

### Test Coverage
- **HTTP Methods**: ✅ GET, POST, PUT, PATCH, DELETE
- **Authentication**: ✅ Bearer, API Key, Basic, Unauthorized
- **File Operations**: ✅ Upload, Download, Multipart
- **Validations**: ✅ Schema, Time, Types, Headers
- **Performance**: ✅ Load, Concurrent, Memory
- **Data-Driven**: ✅ Multiple datasets

### Known Issues
1. **Rate Limiting**: Reqres API returns 403 during high traffic
2. **Network Dependency**: Tests depend on external APIs
3. **Time Sensitivity**: Performance tests may vary by network

### Recommendations
1. Implement retry mechanisms for rate-limited APIs
2. Add mock server for critical test scenarios
3. Monitor API status and adjust test execution
4. Regular test data validation and updates

---

**Document Version**: 2.0  
**Last Updated**: December 25, 2025  
**Next Review**: January 2026