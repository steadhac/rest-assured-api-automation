package utils;

import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;

/**
 * Centralized API endpoints configuration class.
 * Manages all API base URLs and endpoints used across the test framework.
 */
public class APIEndpoints {
    private static final Properties properties = new Properties();
    
    static {
        try (InputStream input = APIEndpoints.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input != null) {
                properties.load(input);
            }
        } catch (IOException e) {
            System.err.println("Failed to load API endpoints configuration: " + e.getMessage());
        }
    }
    
    // Base URLs
    public static final String REQRES_BASE_URL = getProperty("reqres.base.url", "https://reqres.in/api");
    public static final String JSONPLACEHOLDER_BASE_URL = getProperty("jsonplaceholder.base.url", "https://jsonplaceholder.typicode.com");
    public static final String HTTPBIN_BASE_URL = getProperty("httpbin.base.url", "https://httpbin.org");
    public static final String POSTMAN_ECHO_BASE_URL = getProperty("postman.echo.base.url", "https://postman-echo.com");
    
    // Common endpoints
    public static final String USERS_ENDPOINT = "/users";
    public static final String POSTS_ENDPOINT = "/posts";
    public static final String COMMENTS_ENDPOINT = "/comments";
    public static final String TODOS_ENDPOINT = "/todos";
    public static final String BASIC_AUTH_ENDPOINT = "/basic-auth";
    public static final String BEARER_ENDPOINT = "/bearer";
    
    private static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
    
    /**
     * Get full URL for Reqres API endpoint
     */
    public static String getReqresUrl(String endpoint) {
        return REQRES_BASE_URL + endpoint;
    }
    
    /**
     * Get full URL for JSONPlaceholder API endpoint
     */
    public static String getJsonPlaceholderUrl(String endpoint) {
        return JSONPLACEHOLDER_BASE_URL + endpoint;
    }
    
    /**
     * Get full URL for HTTPBin API endpoint
     */
    public static String getHttpBinUrl(String endpoint) {
        return HTTPBIN_BASE_URL + endpoint;
    }
    
    /**
     * Get full URL for Postman Echo API endpoint
     */
    public static String getPostmanEchoUrl(String endpoint) {
        return POSTMAN_ECHO_BASE_URL + endpoint;
    }
}