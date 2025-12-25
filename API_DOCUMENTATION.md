# API Endpoints Documentation

## 🌐 Test APIs Used

This framework tests against multiple public APIs to demonstrate various testing scenarios.

## 📋 API Endpoints

### 1. JSONPlaceholder API
**Base URL**: `https://jsonplaceholder.typicode.com`
**Purpose**: Fake REST API for testing and prototyping

#### Endpoints:

##### GET /posts
- **Description**: Retrieve all posts
- **Response**: Array of post objects
- **Test Coverage**: Array validation, response time

##### GET /posts/{id}
- **Description**: Retrieve specific post
- **Parameters**: `id` (integer) - Post ID
- **Response**: Single post object
- **Test Coverage**: JSON schema validation, data types

##### GET /users
- **Description**: Retrieve all users  
- **Response**: Array of user objects
- **Test Coverage**: Data-driven testing

##### GET /users/{id}
- **Description**: Retrieve specific user
- **Parameters**: `id` (integer) - User ID
- **Response**: Single user object
- **Test Coverage**: Multiple user validation

##### GET /comments
- **Description**: Retrieve all comments
- **Response**: Array of comment objects (500 items)
- **Test Coverage**: Large response handling

##### GET /comments?postId={id}
- **Description**: Retrieve comments for specific post
- **Parameters**: `postId` (integer) - Post ID
- **Response**: Array of comment objects
- **Test Coverage**: Query parameter validation

### 2. Reqres API
**Base URL**: `https://reqres.in/api`
**Purpose**: Real REST API for testing CRUD operations

#### Endpoints:

##### GET /users?page={page}
- **Description**: Retrieve paginated users
- **Parameters**: `page` (integer) - Page number
- **Response**: Paginated user data
- **Test Coverage**: Pagination, query parameters

##### POST /users
- **Description**: Create new user
- **Request Body**: JSON with name, job
- **Response**: Created user with ID and timestamp
- **Test Coverage**: POST requests, JSON bodies

##### PUT /users/{id}
- **Description**: Update user
- **Parameters**: `id` (integer) - User ID
- **Request Body**: JSON with updated data
- **Response**: Updated user data
- **Test Coverage**: PUT requests, POJO validation

##### PATCH /users/{id}
- **Description**: Partially update user
- **Parameters**: `id` (integer) - User ID
- **Request Body**: JSON with partial data
- **Response**: Updated fields
- **Test Coverage**: PATCH requests

##### DELETE /users/{id}
- **Description**: Delete user
- **Parameters**: `id` (integer) - User ID
- **Response**: 204 No Content
- **Test Coverage**: DELETE requests, status codes

### 3. HTTPBin API
**Base URL**: `https://httpbin.org`
**Purpose**: HTTP testing service

#### Endpoints:

##### GET /get
- **Description**: Returns GET request data
- **Parameters**: Any query parameters
- **Response**: Request details including args, headers
- **Test Coverage**: Query parameter validation

##### POST /post
- **Description**: Returns POST request data
- **Request Body**: Any data
- **Response**: Request details including form, files, json
- **Test Coverage**: File uploads, form data

##### GET /bearer
- **Description**: Test Bearer token authentication
- **Headers**: `Authorization: Bearer {token}`
- **Response**: Authentication status
- **Test Coverage**: Bearer token auth

##### GET /basic-auth/{user}/{passwd}
- **Description**: Test Basic authentication
- **Authentication**: Basic auth required
- **Response**: Authentication status
- **Test Coverage**: Basic auth

##### GET /status/{code}
- **Description**: Returns specified HTTP status code
- **Parameters**: `code` (integer) - HTTP status code
- **Response**: Empty with specified status
- **Test Coverage**: Error handling, status codes

##### GET /delay/{seconds}
- **Description**: Delayed response
- **Parameters**: `seconds` (integer) - Delay in seconds
- **Response**: Delayed response
- **Test Coverage**: Timeout handling

##### GET /json
- **Description**: Returns JSON data
- **Response**: Sample JSON object
- **Test Coverage**: File download, JSON parsing

##### GET /image/png
- **Description**: Returns PNG image
- **Response**: Binary PNG image
- **Test Coverage**: Binary file download

### 4. Postman Echo API
**Base URL**: `https://postman-echo.com`
**Purpose**: Testing service by Postman

#### Endpoints:

##### GET /basic-auth
- **Description**: Test basic authentication
- **Authentication**: Basic auth (postman/password)
- **Response**: Authentication success
- **Test Coverage**: Basic auth validation

##### GET /digest-auth
- **Description**: Test digest authentication
- **Authentication**: Digest auth (postman/password)
- **Response**: Authentication success
- **Test Coverage**: Digest auth validation

## 📊 Test Coverage Matrix

| API | GET | POST | PUT | PATCH | DELETE | Auth | Files | Validation |
|-----|-----|------|-----|-------|--------|------|-------|------------|
| JSONPlaceholder | ✅ | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | ✅ |
| Reqres | ✅ | ✅ | ✅ | ✅ | ✅ | ❌ | ❌ | ✅ |
| HTTPBin | ✅ | ✅ | ❌ | ❌ | ❌ | ✅ | ✅ | ✅ |
| Postman Echo | ✅ | ❌ | ❌ | ❌ | ❌ | ✅ | ❌ | ✅ |

## 🔧 Request/Response Examples

### GET Request Example
```java
given()
    .queryParam("page", 2)
    .when()
    .get("https://reqres.in/api/users")
    .then()
    .statusCode(200)
    .body("data", hasSize(6));
```

### POST Request Example
```java
given()
    .header("Content-Type", "application/json")
    .body("{\"name\":\"John\",\"job\":\"Developer\"}")
    .when()
    .post("https://reqres.in/api/users")
    .then()
    .statusCode(201);
```

### Authentication Example
```java
given()
    .header("Authorization", "Bearer test-token")
    .when()
    .get("https://httpbin.org/bearer")
    .then()
    .statusCode(200)
    .body("authenticated", equalTo(true));
```

### File Upload Example
```java
given()
    .multiPart("file", new File("test.txt"), "text/plain")
    .when()
    .post("https://httpbin.org/post")
    .then()
    .statusCode(200);
```

## 🚨 Rate Limiting & Considerations

### JSONPlaceholder
- **Rate Limit**: None specified
- **Reliability**: High
- **Best For**: Basic CRUD testing

### Reqres
- **Rate Limit**: May have limits during high traffic
- **Reliability**: High
- **Best For**: Real API simulation

### HTTPBin
- **Rate Limit**: Reasonable limits
- **Reliability**: High
- **Best For**: HTTP protocol testing

### Postman Echo
- **Rate Limit**: Moderate
- **Reliability**: High
- **Best For**: Authentication testing

## 🔍 Troubleshooting API Issues

### Common Issues:
1. **403 Forbidden**: Rate limiting - wait and retry
2. **Timeout**: Network issues - check connectivity
3. **404 Not Found**: Endpoint changed - verify URL
4. **500 Server Error**: API service down - check status

### Solutions:
- Implement retry mechanisms
- Use multiple test APIs
- Monitor API status pages
- Add proper error handling

---

**Note**: All APIs used are public testing services. No authentication keys or sensitive data required.