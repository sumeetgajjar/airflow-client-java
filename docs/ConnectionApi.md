# ConnectionApi

All URIs are relative to */api/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**deleteConnection**](ConnectionApi.md#deleteConnection) | **DELETE** /connections/{connection_id} | Delete a connection |
| [**getConnection**](ConnectionApi.md#getConnection) | **GET** /connections/{connection_id} | Get a connection |
| [**getConnections**](ConnectionApi.md#getConnections) | **GET** /connections | List connections |
| [**patchConnection**](ConnectionApi.md#patchConnection) | **PATCH** /connections/{connection_id} | Update a connection |
| [**postConnection**](ConnectionApi.md#postConnection) | **POST** /connections | Create a connection |
| [**testConnection**](ConnectionApi.md#testConnection) | **POST** /connections/test | Test a connection |


<a id="deleteConnection"></a>
# **deleteConnection**
> deleteConnection(connectionId)

Delete a connection

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ConnectionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    ConnectionApi apiInstance = new ConnectionApi(defaultClient);
    String connectionId = "connectionId_example"; // String | The connection ID.
    try {
      apiInstance.deleteConnection(connectionId);
    } catch (ApiException e) {
      System.err.println("Exception when calling ConnectionApi#deleteConnection");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **connectionId** | **String**| The connection ID. | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **204** | Success. |  -  |
| **400** | Client specified an invalid argument. |  -  |
| **401** | Request not authenticated due to missing, invalid, authentication info. |  -  |
| **403** | Client does not have sufficient permission. |  -  |
| **404** | A specified resource is not found. |  -  |

<a id="getConnection"></a>
# **getConnection**
> Connection getConnection(connectionId)

Get a connection

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ConnectionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    ConnectionApi apiInstance = new ConnectionApi(defaultClient);
    String connectionId = "connectionId_example"; // String | The connection ID.
    try {
      Connection result = apiInstance.getConnection(connectionId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ConnectionApi#getConnection");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **connectionId** | **String**| The connection ID. | |

### Return type

[**Connection**](Connection.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Success. |  -  |
| **401** | Request not authenticated due to missing, invalid, authentication info. |  -  |
| **403** | Client does not have sufficient permission. |  -  |
| **404** | A specified resource is not found. |  -  |

<a id="getConnections"></a>
# **getConnections**
> ConnectionCollection getConnections(limit, offset, orderBy)

List connections

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ConnectionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    ConnectionApi apiInstance = new ConnectionApi(defaultClient);
    Integer limit = 100; // Integer | The numbers of items to return.
    Integer offset = 56; // Integer | The number of items to skip before starting to collect the result set.
    String orderBy = "orderBy_example"; // String | The name of the field to order the results by. Prefix a field name with `-` to reverse the sort order.  *New in version 2.1.0* 
    try {
      ConnectionCollection result = apiInstance.getConnections(limit, offset, orderBy);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ConnectionApi#getConnections");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **limit** | **Integer**| The numbers of items to return. | [optional] [default to 100] |
| **offset** | **Integer**| The number of items to skip before starting to collect the result set. | [optional] |
| **orderBy** | **String**| The name of the field to order the results by. Prefix a field name with &#x60;-&#x60; to reverse the sort order.  *New in version 2.1.0*  | [optional] |

### Return type

[**ConnectionCollection**](ConnectionCollection.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Success. |  -  |
| **401** | Request not authenticated due to missing, invalid, authentication info. |  -  |
| **403** | Client does not have sufficient permission. |  -  |

<a id="patchConnection"></a>
# **patchConnection**
> Connection patchConnection(connectionId, connection, updateMask)

Update a connection

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ConnectionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    ConnectionApi apiInstance = new ConnectionApi(defaultClient);
    String connectionId = "connectionId_example"; // String | The connection ID.
    Connection connection = new Connection(); // Connection | 
    List<String> updateMask = Arrays.asList(); // List<String> | The fields to update on the resource. If absent or empty, all modifiable fields are updated. A comma-separated list of fully qualified names of fields. 
    try {
      Connection result = apiInstance.patchConnection(connectionId, connection, updateMask);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ConnectionApi#patchConnection");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **connectionId** | **String**| The connection ID. | |
| **connection** | [**Connection**](Connection.md)|  | |
| **updateMask** | [**List&lt;String&gt;**](String.md)| The fields to update on the resource. If absent or empty, all modifiable fields are updated. A comma-separated list of fully qualified names of fields.  | [optional] |

### Return type

[**Connection**](Connection.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Success. |  -  |
| **400** | Client specified an invalid argument. |  -  |
| **401** | Request not authenticated due to missing, invalid, authentication info. |  -  |
| **403** | Client does not have sufficient permission. |  -  |
| **404** | A specified resource is not found. |  -  |

<a id="postConnection"></a>
# **postConnection**
> Connection postConnection(connection)

Create a connection

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ConnectionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    ConnectionApi apiInstance = new ConnectionApi(defaultClient);
    Connection connection = new Connection(); // Connection | 
    try {
      Connection result = apiInstance.postConnection(connection);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ConnectionApi#postConnection");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **connection** | [**Connection**](Connection.md)|  | |

### Return type

[**Connection**](Connection.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Success. |  -  |
| **400** | Client specified an invalid argument. |  -  |
| **401** | Request not authenticated due to missing, invalid, authentication info. |  -  |
| **403** | Client does not have sufficient permission. |  -  |

<a id="testConnection"></a>
# **testConnection**
> ConnectionTest testConnection(connection)

Test a connection

Test a connection.  For security reasons, the test connection functionality is disabled by default across Airflow UI, API and CLI. For more information on capabilities of users, see the documentation: https://airflow.apache.org/docs/apache-airflow/stable/security/security_model.html#capabilities-of-authenticated-ui-users. It is strongly advised to not enable the feature until you make sure that only highly trusted UI/API users have \&quot;edit connection\&quot; permissions.  Set the \&quot;test_connection\&quot; flag to \&quot;Enabled\&quot; in the \&quot;core\&quot; section of Airflow configuration (airflow.cfg) to enable testing of collections. It can also be controlled by the environment variable &#x60;AIRFLOW__CORE__TEST_CONNECTION&#x60;.  *New in version 2.2.0* 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ConnectionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    ConnectionApi apiInstance = new ConnectionApi(defaultClient);
    Connection connection = new Connection(); // Connection | 
    try {
      ConnectionTest result = apiInstance.testConnection(connection);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ConnectionApi#testConnection");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **connection** | [**Connection**](Connection.md)|  | |

### Return type

[**ConnectionTest**](ConnectionTest.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Success. |  -  |
| **400** | Client specified an invalid argument. |  -  |
| **401** | Request not authenticated due to missing, invalid, authentication info. |  -  |
| **403** | Client does not have sufficient permission. |  -  |
| **404** | A specified resource is not found. |  -  |

