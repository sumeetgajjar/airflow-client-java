# DagApi

All URIs are relative to */api/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**deleteDag**](DagApi.md#deleteDag) | **DELETE** /dags/{dag_id} | Delete a DAG |
| [**getDag**](DagApi.md#getDag) | **GET** /dags/{dag_id} | Get basic information about a DAG |
| [**getDagDetails**](DagApi.md#getDagDetails) | **GET** /dags/{dag_id}/details | Get a simplified representation of DAG |
| [**getDagSource**](DagApi.md#getDagSource) | **GET** /dagSources/{file_token} | Get a source code |
| [**getDags**](DagApi.md#getDags) | **GET** /dags | List DAGs |
| [**getTask**](DagApi.md#getTask) | **GET** /dags/{dag_id}/tasks/{task_id} | Get simplified representation of a task |
| [**getTasks**](DagApi.md#getTasks) | **GET** /dags/{dag_id}/tasks | Get tasks for DAG |
| [**patchDag**](DagApi.md#patchDag) | **PATCH** /dags/{dag_id} | Update a DAG |
| [**patchDags**](DagApi.md#patchDags) | **PATCH** /dags | Update DAGs |
| [**postClearTaskInstances**](DagApi.md#postClearTaskInstances) | **POST** /dags/{dag_id}/clearTaskInstances | Clear a set of task instances |
| [**postSetTaskInstancesState**](DagApi.md#postSetTaskInstancesState) | **POST** /dags/{dag_id}/updateTaskInstancesState | Set a state of task instances |
| [**reparseDagFile**](DagApi.md#reparseDagFile) | **PUT** /parseDagFile/{file_token} | Request re-parsing of a DAG file |


<a id="deleteDag"></a>
# **deleteDag**
> deleteDag(dagId)

Delete a DAG

Deletes all metadata related to the DAG, including finished DAG Runs and Tasks. Logs are not deleted. This action cannot be undone.  *New in version 2.2.0* 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DagApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    DagApi apiInstance = new DagApi(defaultClient);
    String dagId = "dagId_example"; // String | The DAG ID.
    try {
      apiInstance.deleteDag(dagId);
    } catch (ApiException e) {
      System.err.println("Exception when calling DagApi#deleteDag");
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
| **dagId** | **String**| The DAG ID. | |

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
| **409** | An existing resource conflicts with the request. |  -  |

<a id="getDag"></a>
# **getDag**
> DAG getDag(dagId, fields)

Get basic information about a DAG

Presents only information available in database (DAGModel). If you need detailed information, consider using GET /dags/{dag_id}/details. 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DagApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    DagApi apiInstance = new DagApi(defaultClient);
    String dagId = "dagId_example"; // String | The DAG ID.
    List<String> fields = Arrays.asList(); // List<String> | List of field for return. 
    try {
      DAG result = apiInstance.getDag(dagId, fields);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DagApi#getDag");
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
| **dagId** | **String**| The DAG ID. | |
| **fields** | [**List&lt;String&gt;**](String.md)| List of field for return.  | [optional] |

### Return type

[**DAG**](DAG.md)

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

<a id="getDagDetails"></a>
# **getDagDetails**
> DAGDetail getDagDetails(dagId, fields)

Get a simplified representation of DAG

The response contains many DAG attributes, so the response can be large. If possible, consider using GET /dags/{dag_id}. 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DagApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    DagApi apiInstance = new DagApi(defaultClient);
    String dagId = "dagId_example"; // String | The DAG ID.
    List<String> fields = Arrays.asList(); // List<String> | List of field for return. 
    try {
      DAGDetail result = apiInstance.getDagDetails(dagId, fields);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DagApi#getDagDetails");
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
| **dagId** | **String**| The DAG ID. | |
| **fields** | [**List&lt;String&gt;**](String.md)| List of field for return.  | [optional] |

### Return type

[**DAGDetail**](DAGDetail.md)

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

<a id="getDagSource"></a>
# **getDagSource**
> GetDagSource200Response getDagSource(fileToken)

Get a source code

Get a source code using file token. 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DagApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    DagApi apiInstance = new DagApi(defaultClient);
    String fileToken = "fileToken_example"; // String | The key containing the encrypted path to the file. Encryption and decryption take place only on the server. This prevents the client from reading an non-DAG file. This also ensures API extensibility, because the format of encrypted data may change. 
    try {
      GetDagSource200Response result = apiInstance.getDagSource(fileToken);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DagApi#getDagSource");
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
| **fileToken** | **String**| The key containing the encrypted path to the file. Encryption and decryption take place only on the server. This prevents the client from reading an non-DAG file. This also ensures API extensibility, because the format of encrypted data may change.  | |

### Return type

[**GetDagSource200Response**](GetDagSource200Response.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json, text/plain

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Success. |  -  |
| **401** | Request not authenticated due to missing, invalid, authentication info. |  -  |
| **403** | Client does not have sufficient permission. |  -  |
| **404** | A specified resource is not found. |  -  |
| **406** | A specified Accept header is not allowed. |  -  |

<a id="getDags"></a>
# **getDags**
> DAGCollection getDags(limit, offset, orderBy, tags, onlyActive, paused, fields, dagIdPattern)

List DAGs

List DAGs in the database. &#x60;dag_id_pattern&#x60; can be set to match dags of a specific pattern 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DagApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    DagApi apiInstance = new DagApi(defaultClient);
    Integer limit = 100; // Integer | The numbers of items to return.
    Integer offset = 56; // Integer | The number of items to skip before starting to collect the result set.
    String orderBy = "orderBy_example"; // String | The name of the field to order the results by. Prefix a field name with `-` to reverse the sort order.  *New in version 2.1.0* 
    List<String> tags = Arrays.asList(); // List<String> | List of tags to filter results.  *New in version 2.2.0* 
    Boolean onlyActive = true; // Boolean | Only filter active DAGs.  *New in version 2.1.1* 
    Boolean paused = true; // Boolean | Only filter paused/unpaused DAGs. If absent or null, it returns paused and unpaused DAGs.  *New in version 2.6.0* 
    List<String> fields = Arrays.asList(); // List<String> | List of field for return. 
    String dagIdPattern = "dagIdPattern_example"; // String | If set, only return DAGs with dag_ids matching this pattern. 
    try {
      DAGCollection result = apiInstance.getDags(limit, offset, orderBy, tags, onlyActive, paused, fields, dagIdPattern);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DagApi#getDags");
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
| **tags** | [**List&lt;String&gt;**](String.md)| List of tags to filter results.  *New in version 2.2.0*  | [optional] |
| **onlyActive** | **Boolean**| Only filter active DAGs.  *New in version 2.1.1*  | [optional] [default to true] |
| **paused** | **Boolean**| Only filter paused/unpaused DAGs. If absent or null, it returns paused and unpaused DAGs.  *New in version 2.6.0*  | [optional] |
| **fields** | [**List&lt;String&gt;**](String.md)| List of field for return.  | [optional] |
| **dagIdPattern** | **String**| If set, only return DAGs with dag_ids matching this pattern.  | [optional] |

### Return type

[**DAGCollection**](DAGCollection.md)

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

<a id="getTask"></a>
# **getTask**
> Task getTask(dagId, taskId)

Get simplified representation of a task

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DagApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    DagApi apiInstance = new DagApi(defaultClient);
    String dagId = "dagId_example"; // String | The DAG ID.
    String taskId = "taskId_example"; // String | The task ID.
    try {
      Task result = apiInstance.getTask(dagId, taskId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DagApi#getTask");
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
| **dagId** | **String**| The DAG ID. | |
| **taskId** | **String**| The task ID. | |

### Return type

[**Task**](Task.md)

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

<a id="getTasks"></a>
# **getTasks**
> TaskCollection getTasks(dagId, orderBy)

Get tasks for DAG

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DagApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    DagApi apiInstance = new DagApi(defaultClient);
    String dagId = "dagId_example"; // String | The DAG ID.
    String orderBy = "orderBy_example"; // String | The name of the field to order the results by. Prefix a field name with `-` to reverse the sort order.  *New in version 2.1.0* 
    try {
      TaskCollection result = apiInstance.getTasks(dagId, orderBy);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DagApi#getTasks");
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
| **dagId** | **String**| The DAG ID. | |
| **orderBy** | **String**| The name of the field to order the results by. Prefix a field name with &#x60;-&#x60; to reverse the sort order.  *New in version 2.1.0*  | [optional] |

### Return type

[**TaskCollection**](TaskCollection.md)

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

<a id="patchDag"></a>
# **patchDag**
> DAG patchDag(dagId, DAG, updateMask)

Update a DAG

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DagApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    DagApi apiInstance = new DagApi(defaultClient);
    String dagId = "dagId_example"; // String | The DAG ID.
    DAG DAG = new DAG(); // DAG | 
    List<String> updateMask = Arrays.asList(); // List<String> | The fields to update on the resource. If absent or empty, all modifiable fields are updated. A comma-separated list of fully qualified names of fields. 
    try {
      DAG result = apiInstance.patchDag(dagId, DAG, updateMask);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DagApi#patchDag");
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
| **dagId** | **String**| The DAG ID. | |
| **DAG** | [**DAG**](DAG.md)|  | |
| **updateMask** | [**List&lt;String&gt;**](String.md)| The fields to update on the resource. If absent or empty, all modifiable fields are updated. A comma-separated list of fully qualified names of fields.  | [optional] |

### Return type

[**DAG**](DAG.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Success. |  -  |
| **401** | Request not authenticated due to missing, invalid, authentication info. |  -  |
| **403** | Client does not have sufficient permission. |  -  |
| **404** | A specified resource is not found. |  -  |

<a id="patchDags"></a>
# **patchDags**
> DAGCollection patchDags(dagIdPattern, DAG, limit, offset, tags, updateMask, onlyActive)

Update DAGs

Update DAGs of a given dag_id_pattern using UpdateMask. This endpoint allows specifying &#x60;~&#x60; as the dag_id_pattern to update all DAGs. *New in version 2.3.0* 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DagApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    DagApi apiInstance = new DagApi(defaultClient);
    String dagIdPattern = "dagIdPattern_example"; // String | If set, only update DAGs with dag_ids matching this pattern. 
    DAG DAG = new DAG(); // DAG | 
    Integer limit = 100; // Integer | The numbers of items to return.
    Integer offset = 56; // Integer | The number of items to skip before starting to collect the result set.
    List<String> tags = Arrays.asList(); // List<String> | List of tags to filter results.  *New in version 2.2.0* 
    List<String> updateMask = Arrays.asList(); // List<String> | The fields to update on the resource. If absent or empty, all modifiable fields are updated. A comma-separated list of fully qualified names of fields. 
    Boolean onlyActive = true; // Boolean | Only filter active DAGs.  *New in version 2.1.1* 
    try {
      DAGCollection result = apiInstance.patchDags(dagIdPattern, DAG, limit, offset, tags, updateMask, onlyActive);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DagApi#patchDags");
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
| **dagIdPattern** | **String**| If set, only update DAGs with dag_ids matching this pattern.  | |
| **DAG** | [**DAG**](DAG.md)|  | |
| **limit** | **Integer**| The numbers of items to return. | [optional] [default to 100] |
| **offset** | **Integer**| The number of items to skip before starting to collect the result set. | [optional] |
| **tags** | [**List&lt;String&gt;**](String.md)| List of tags to filter results.  *New in version 2.2.0*  | [optional] |
| **updateMask** | [**List&lt;String&gt;**](String.md)| The fields to update on the resource. If absent or empty, all modifiable fields are updated. A comma-separated list of fully qualified names of fields.  | [optional] |
| **onlyActive** | **Boolean**| Only filter active DAGs.  *New in version 2.1.1*  | [optional] [default to true] |

### Return type

[**DAGCollection**](DAGCollection.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Success. |  -  |
| **401** | Request not authenticated due to missing, invalid, authentication info. |  -  |
| **403** | Client does not have sufficient permission. |  -  |
| **404** | A specified resource is not found. |  -  |

<a id="postClearTaskInstances"></a>
# **postClearTaskInstances**
> TaskInstanceReferenceCollection postClearTaskInstances(dagId, clearTaskInstances)

Clear a set of task instances

Clears a set of task instances associated with the DAG for a specified date range. 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DagApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    DagApi apiInstance = new DagApi(defaultClient);
    String dagId = "dagId_example"; // String | The DAG ID.
    ClearTaskInstances clearTaskInstances = new ClearTaskInstances(); // ClearTaskInstances | Parameters of action
    try {
      TaskInstanceReferenceCollection result = apiInstance.postClearTaskInstances(dagId, clearTaskInstances);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DagApi#postClearTaskInstances");
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
| **dagId** | **String**| The DAG ID. | |
| **clearTaskInstances** | [**ClearTaskInstances**](ClearTaskInstances.md)| Parameters of action | |

### Return type

[**TaskInstanceReferenceCollection**](TaskInstanceReferenceCollection.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Success. |  -  |
| **401** | Request not authenticated due to missing, invalid, authentication info. |  -  |
| **403** | Client does not have sufficient permission. |  -  |
| **404** | A specified resource is not found. |  -  |

<a id="postSetTaskInstancesState"></a>
# **postSetTaskInstancesState**
> TaskInstanceReferenceCollection postSetTaskInstancesState(dagId, updateTaskInstancesState)

Set a state of task instances

Updates the state for multiple task instances simultaneously. 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DagApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    DagApi apiInstance = new DagApi(defaultClient);
    String dagId = "dagId_example"; // String | The DAG ID.
    UpdateTaskInstancesState updateTaskInstancesState = new UpdateTaskInstancesState(); // UpdateTaskInstancesState | Parameters of action
    try {
      TaskInstanceReferenceCollection result = apiInstance.postSetTaskInstancesState(dagId, updateTaskInstancesState);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DagApi#postSetTaskInstancesState");
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
| **dagId** | **String**| The DAG ID. | |
| **updateTaskInstancesState** | [**UpdateTaskInstancesState**](UpdateTaskInstancesState.md)| Parameters of action | |

### Return type

[**TaskInstanceReferenceCollection**](TaskInstanceReferenceCollection.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Success. |  -  |
| **401** | Request not authenticated due to missing, invalid, authentication info. |  -  |
| **403** | Client does not have sufficient permission. |  -  |
| **404** | A specified resource is not found. |  -  |

<a id="reparseDagFile"></a>
# **reparseDagFile**
> reparseDagFile(fileToken)

Request re-parsing of a DAG file

Request re-parsing of existing DAG files using a file token. 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DagApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    DagApi apiInstance = new DagApi(defaultClient);
    String fileToken = "fileToken_example"; // String | The key containing the encrypted path to the file. Encryption and decryption take place only on the server. This prevents the client from reading an non-DAG file. This also ensures API extensibility, because the format of encrypted data may change. 
    try {
      apiInstance.reparseDagFile(fileToken);
    } catch (ApiException e) {
      System.err.println("Exception when calling DagApi#reparseDagFile");
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
| **fileToken** | **String**| The key containing the encrypted path to the file. Encryption and decryption take place only on the server. This prevents the client from reading an non-DAG file. This also ensures API extensibility, because the format of encrypted data may change.  | |

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
| **201** | Success. |  -  |
| **401** | Request not authenticated due to missing, invalid, authentication info. |  -  |
| **403** | Client does not have sufficient permission. |  -  |
| **404** | A specified resource is not found. |  -  |

