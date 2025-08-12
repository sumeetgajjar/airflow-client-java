# TaskInstanceApi

All URIs are relative to */api/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**getExtraLinks**](TaskInstanceApi.md#getExtraLinks) | **GET** /dags/{dag_id}/dagRuns/{dag_run_id}/taskInstances/{task_id}/links | List extra links |
| [**getLog**](TaskInstanceApi.md#getLog) | **GET** /dags/{dag_id}/dagRuns/{dag_run_id}/taskInstances/{task_id}/logs/{task_try_number} | Get logs |
| [**getMappedTaskInstance**](TaskInstanceApi.md#getMappedTaskInstance) | **GET** /dags/{dag_id}/dagRuns/{dag_run_id}/taskInstances/{task_id}/{map_index} | Get a mapped task instance |
| [**getMappedTaskInstanceDependencies**](TaskInstanceApi.md#getMappedTaskInstanceDependencies) | **GET** /dags/{dag_id}/dagRuns/{dag_run_id}/taskInstances/{task_id}/{map_index}/dependencies | Get task dependencies blocking task from getting scheduled. |
| [**getMappedTaskInstanceTries**](TaskInstanceApi.md#getMappedTaskInstanceTries) | **GET** /dags/{dag_id}/dagRuns/{dag_run_id}/taskInstances/{task_id}/{map_index}/tries | List mapped task instance tries |
| [**getMappedTaskInstanceTryDetails**](TaskInstanceApi.md#getMappedTaskInstanceTryDetails) | **GET** /dags/{dag_id}/dagRuns/{dag_run_id}/taskInstances/{task_id}/{map_index}/tries/{task_try_number} | get mapped taskinstance try |
| [**getMappedTaskInstances**](TaskInstanceApi.md#getMappedTaskInstances) | **GET** /dags/{dag_id}/dagRuns/{dag_run_id}/taskInstances/{task_id}/listMapped | List mapped task instances |
| [**getTaskInstance**](TaskInstanceApi.md#getTaskInstance) | **GET** /dags/{dag_id}/dagRuns/{dag_run_id}/taskInstances/{task_id} | Get a task instance |
| [**getTaskInstanceDependencies**](TaskInstanceApi.md#getTaskInstanceDependencies) | **GET** /dags/{dag_id}/dagRuns/{dag_run_id}/taskInstances/{task_id}/dependencies | Get task dependencies blocking task from getting scheduled. |
| [**getTaskInstanceTries**](TaskInstanceApi.md#getTaskInstanceTries) | **GET** /dags/{dag_id}/dagRuns/{dag_run_id}/taskInstances/{task_id}/tries | List task instance tries |
| [**getTaskInstanceTryDetails**](TaskInstanceApi.md#getTaskInstanceTryDetails) | **GET** /dags/{dag_id}/dagRuns/{dag_run_id}/taskInstances/{task_id}/tries/{task_try_number} | get taskinstance try |
| [**getTaskInstances**](TaskInstanceApi.md#getTaskInstances) | **GET** /dags/{dag_id}/dagRuns/{dag_run_id}/taskInstances | List task instances |
| [**getTaskInstancesBatch**](TaskInstanceApi.md#getTaskInstancesBatch) | **POST** /dags/~/dagRuns/~/taskInstances/list | List task instances (batch) |
| [**patchMappedTaskInstance**](TaskInstanceApi.md#patchMappedTaskInstance) | **PATCH** /dags/{dag_id}/dagRuns/{dag_run_id}/taskInstances/{task_id}/{map_index} | Updates the state of a mapped task instance |
| [**patchTaskInstance**](TaskInstanceApi.md#patchTaskInstance) | **PATCH** /dags/{dag_id}/dagRuns/{dag_run_id}/taskInstances/{task_id} | Updates the state of a task instance |
| [**setMappedTaskInstanceNote**](TaskInstanceApi.md#setMappedTaskInstanceNote) | **PATCH** /dags/{dag_id}/dagRuns/{dag_run_id}/taskInstances/{task_id}/{map_index}/setNote | Update the TaskInstance note. |
| [**setTaskInstanceNote**](TaskInstanceApi.md#setTaskInstanceNote) | **PATCH** /dags/{dag_id}/dagRuns/{dag_run_id}/taskInstances/{task_id}/setNote | Update the TaskInstance note. |


<a id="getExtraLinks"></a>
# **getExtraLinks**
> ExtraLinkCollection getExtraLinks(dagId, dagRunId, taskId, mapIndex)

List extra links

List extra links for task instance. 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TaskInstanceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    TaskInstanceApi apiInstance = new TaskInstanceApi(defaultClient);
    String dagId = "dagId_example"; // String | The DAG ID.
    String dagRunId = "dagRunId_example"; // String | The DAG run ID.
    String taskId = "taskId_example"; // String | The task ID.
    Integer mapIndex = 56; // Integer | Filter on map index for mapped task.
    try {
      ExtraLinkCollection result = apiInstance.getExtraLinks(dagId, dagRunId, taskId, mapIndex);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TaskInstanceApi#getExtraLinks");
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
| **dagRunId** | **String**| The DAG run ID. | |
| **taskId** | **String**| The task ID. | |
| **mapIndex** | **Integer**| Filter on map index for mapped task. | [optional] |

### Return type

[**ExtraLinkCollection**](ExtraLinkCollection.md)

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

<a id="getLog"></a>
# **getLog**
> GetLog200Response getLog(dagId, dagRunId, taskId, taskTryNumber, fullContent, mapIndex, token)

Get logs

Get logs for a specific task instance and its try number. To get log from specific character position, following way of using URLSafeSerializer can be used.  Example: &#x60;&#x60;&#x60; from itsdangerous.url_safe import URLSafeSerializer  request_url &#x3D; f\&quot;api/v1/dags/{DAG_ID}/dagRuns/{RUN_ID}/taskInstances/{TASK_ID}/logs/1\&quot; key &#x3D; app.config[\&quot;SECRET_KEY\&quot;] serializer &#x3D; URLSafeSerializer(key) token &#x3D; serializer.dumps({\&quot;log_pos\&quot;: 10000})  response &#x3D; self.client.get(     request_url,     query_string&#x3D;{\&quot;token\&quot;: token},     headers&#x3D;{\&quot;Accept\&quot;: \&quot;text/plain\&quot;},     environ_overrides&#x3D;{\&quot;REMOTE_USER\&quot;: \&quot;test\&quot;}, ) continuation_token &#x3D; response.json[\&quot;continuation_token\&quot;]     metadata &#x3D; URLSafeSerializer(key).loads(continuation_token)     log_pos &#x3D; metadata[\&quot;log_pos\&quot;]     end_of_log &#x3D; metadata[\&quot;end_of_log\&quot;] &#x60;&#x60;&#x60; If log_pos is passed as 10000 like the above example, it renders the logs starting from char position 10000 to last (not the end as the logs may be tailing behind in running state). This way pagination can be done with metadata as part of the token. 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TaskInstanceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    TaskInstanceApi apiInstance = new TaskInstanceApi(defaultClient);
    String dagId = "dagId_example"; // String | The DAG ID.
    String dagRunId = "dagRunId_example"; // String | The DAG run ID.
    String taskId = "taskId_example"; // String | The task ID.
    Integer taskTryNumber = 56; // Integer | The task try number.
    Boolean fullContent = true; // Boolean | A full content will be returned. By default, only the first fragment will be returned. 
    Integer mapIndex = 56; // Integer | Filter on map index for mapped task.
    String token = "token_example"; // String | A token that allows you to continue fetching logs. If passed, it will specify the location from which the download should be continued. 
    try {
      GetLog200Response result = apiInstance.getLog(dagId, dagRunId, taskId, taskTryNumber, fullContent, mapIndex, token);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TaskInstanceApi#getLog");
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
| **dagRunId** | **String**| The DAG run ID. | |
| **taskId** | **String**| The task ID. | |
| **taskTryNumber** | **Integer**| The task try number. | |
| **fullContent** | **Boolean**| A full content will be returned. By default, only the first fragment will be returned.  | [optional] |
| **mapIndex** | **Integer**| Filter on map index for mapped task. | [optional] |
| **token** | **String**| A token that allows you to continue fetching logs. If passed, it will specify the location from which the download should be continued.  | [optional] |

### Return type

[**GetLog200Response**](GetLog200Response.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json, text/plain

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Success. |  -  |
| **400** | Client specified an invalid argument. |  -  |
| **401** | Request not authenticated due to missing, invalid, authentication info. |  -  |
| **403** | Client does not have sufficient permission. |  -  |
| **404** | A specified resource is not found. |  -  |

<a id="getMappedTaskInstance"></a>
# **getMappedTaskInstance**
> TaskInstance getMappedTaskInstance(dagId, dagRunId, taskId, mapIndex)

Get a mapped task instance

Get details of a mapped task instance.  *New in version 2.3.0* 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TaskInstanceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    TaskInstanceApi apiInstance = new TaskInstanceApi(defaultClient);
    String dagId = "dagId_example"; // String | The DAG ID.
    String dagRunId = "dagRunId_example"; // String | The DAG run ID.
    String taskId = "taskId_example"; // String | The task ID.
    Integer mapIndex = 56; // Integer | The map index.
    try {
      TaskInstance result = apiInstance.getMappedTaskInstance(dagId, dagRunId, taskId, mapIndex);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TaskInstanceApi#getMappedTaskInstance");
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
| **dagRunId** | **String**| The DAG run ID. | |
| **taskId** | **String**| The task ID. | |
| **mapIndex** | **Integer**| The map index. | |

### Return type

[**TaskInstance**](TaskInstance.md)

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

<a id="getMappedTaskInstanceDependencies"></a>
# **getMappedTaskInstanceDependencies**
> TaskInstanceDependencyCollection getMappedTaskInstanceDependencies(dagId, dagRunId, taskId, mapIndex)

Get task dependencies blocking task from getting scheduled.

Get task dependencies blocking task from getting scheduled.  *New in version 2.10.0* 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TaskInstanceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    TaskInstanceApi apiInstance = new TaskInstanceApi(defaultClient);
    String dagId = "dagId_example"; // String | The DAG ID.
    String dagRunId = "dagRunId_example"; // String | The DAG run ID.
    String taskId = "taskId_example"; // String | The task ID.
    Integer mapIndex = 56; // Integer | The map index.
    try {
      TaskInstanceDependencyCollection result = apiInstance.getMappedTaskInstanceDependencies(dagId, dagRunId, taskId, mapIndex);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TaskInstanceApi#getMappedTaskInstanceDependencies");
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
| **dagRunId** | **String**| The DAG run ID. | |
| **taskId** | **String**| The task ID. | |
| **mapIndex** | **Integer**| The map index. | |

### Return type

[**TaskInstanceDependencyCollection**](TaskInstanceDependencyCollection.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Success. |  -  |
| **400** | Client specified an invalid argument. |  -  |
| **401** | Request not authenticated due to missing, invalid, authentication info. |  -  |
| **403** | Client does not have sufficient permission. |  -  |
| **404** | A specified resource is not found. |  -  |

<a id="getMappedTaskInstanceTries"></a>
# **getMappedTaskInstanceTries**
> TaskInstanceHistoryCollection getMappedTaskInstanceTries(dagId, dagRunId, taskId, mapIndex, limit, offset, orderBy)

List mapped task instance tries

Get details of all task instance tries.  *New in version 2.10.0* 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TaskInstanceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    TaskInstanceApi apiInstance = new TaskInstanceApi(defaultClient);
    String dagId = "dagId_example"; // String | The DAG ID.
    String dagRunId = "dagRunId_example"; // String | The DAG run ID.
    String taskId = "taskId_example"; // String | The task ID.
    Integer mapIndex = 56; // Integer | The map index.
    Integer limit = 100; // Integer | The numbers of items to return.
    Integer offset = 56; // Integer | The number of items to skip before starting to collect the result set.
    String orderBy = "orderBy_example"; // String | The name of the field to order the results by. Prefix a field name with `-` to reverse the sort order.  *New in version 2.1.0* 
    try {
      TaskInstanceHistoryCollection result = apiInstance.getMappedTaskInstanceTries(dagId, dagRunId, taskId, mapIndex, limit, offset, orderBy);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TaskInstanceApi#getMappedTaskInstanceTries");
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
| **dagRunId** | **String**| The DAG run ID. | |
| **taskId** | **String**| The task ID. | |
| **mapIndex** | **Integer**| The map index. | |
| **limit** | **Integer**| The numbers of items to return. | [optional] [default to 100] |
| **offset** | **Integer**| The number of items to skip before starting to collect the result set. | [optional] |
| **orderBy** | **String**| The name of the field to order the results by. Prefix a field name with &#x60;-&#x60; to reverse the sort order.  *New in version 2.1.0*  | [optional] |

### Return type

[**TaskInstanceHistoryCollection**](TaskInstanceHistoryCollection.md)

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

<a id="getMappedTaskInstanceTryDetails"></a>
# **getMappedTaskInstanceTryDetails**
> TaskInstanceHistory getMappedTaskInstanceTryDetails(dagId, dagRunId, taskId, mapIndex, taskTryNumber)

get mapped taskinstance try

Get details of a mapped task instance try.  *New in version 2.10.0* 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TaskInstanceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    TaskInstanceApi apiInstance = new TaskInstanceApi(defaultClient);
    String dagId = "dagId_example"; // String | The DAG ID.
    String dagRunId = "dagRunId_example"; // String | The DAG run ID.
    String taskId = "taskId_example"; // String | The task ID.
    Integer mapIndex = 56; // Integer | The map index.
    Integer taskTryNumber = 56; // Integer | The task try number.
    try {
      TaskInstanceHistory result = apiInstance.getMappedTaskInstanceTryDetails(dagId, dagRunId, taskId, mapIndex, taskTryNumber);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TaskInstanceApi#getMappedTaskInstanceTryDetails");
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
| **dagRunId** | **String**| The DAG run ID. | |
| **taskId** | **String**| The task ID. | |
| **mapIndex** | **Integer**| The map index. | |
| **taskTryNumber** | **Integer**| The task try number. | |

### Return type

[**TaskInstanceHistory**](TaskInstanceHistory.md)

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

<a id="getMappedTaskInstances"></a>
# **getMappedTaskInstances**
> TaskInstanceCollection getMappedTaskInstances(dagId, dagRunId, taskId, limit, offset, executionDateGte, executionDateLte, startDateGte, startDateLte, endDateGte, endDateLte, updatedAtGte, updatedAtLte, durationGte, durationLte, state, pool, queue, executor, orderBy)

List mapped task instances

Get details of all mapped task instances.  *New in version 2.3.0* 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TaskInstanceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    TaskInstanceApi apiInstance = new TaskInstanceApi(defaultClient);
    String dagId = "dagId_example"; // String | The DAG ID.
    String dagRunId = "dagRunId_example"; // String | The DAG run ID.
    String taskId = "taskId_example"; // String | The task ID.
    Integer limit = 100; // Integer | The numbers of items to return.
    Integer offset = 56; // Integer | The number of items to skip before starting to collect the result set.
    OffsetDateTime executionDateGte = OffsetDateTime.now(); // OffsetDateTime | Returns objects greater or equal to the specified date.  This can be combined with execution_date_lte parameter to receive only the selected period. 
    OffsetDateTime executionDateLte = OffsetDateTime.now(); // OffsetDateTime | Returns objects less than or equal to the specified date.  This can be combined with execution_date_gte parameter to receive only the selected period. 
    OffsetDateTime startDateGte = OffsetDateTime.now(); // OffsetDateTime | Returns objects greater or equal the specified date.  This can be combined with start_date_lte parameter to receive only the selected period. 
    OffsetDateTime startDateLte = OffsetDateTime.now(); // OffsetDateTime | Returns objects less or equal the specified date.  This can be combined with start_date_gte parameter to receive only the selected period. 
    OffsetDateTime endDateGte = OffsetDateTime.now(); // OffsetDateTime | Returns objects greater or equal the specified date.  This can be combined with start_date_lte parameter to receive only the selected period. 
    OffsetDateTime endDateLte = OffsetDateTime.now(); // OffsetDateTime | Returns objects less than or equal to the specified date.  This can be combined with start_date_gte parameter to receive only the selected period. 
    OffsetDateTime updatedAtGte = OffsetDateTime.now(); // OffsetDateTime | Returns objects greater or equal the specified date.  This can be combined with updated_at_lte parameter to receive only the selected period.  *New in version 2.6.0* 
    OffsetDateTime updatedAtLte = OffsetDateTime.now(); // OffsetDateTime | Returns objects less or equal the specified date.  This can be combined with updated_at_gte parameter to receive only the selected period.  *New in version 2.6.0* 
    BigDecimal durationGte = new BigDecimal(78); // BigDecimal | Returns objects greater than or equal to the specified values.  This can be combined with duration_lte parameter to receive only the selected period. 
    BigDecimal durationLte = new BigDecimal(78); // BigDecimal | Returns objects less than or equal to the specified values.  This can be combined with duration_gte parameter to receive only the selected range. 
    List<String> state = Arrays.asList(); // List<String> | The value can be repeated to retrieve multiple matching values (OR condition).
    List<String> pool = Arrays.asList(); // List<String> | The value can be repeated to retrieve multiple matching values (OR condition).
    List<String> queue = Arrays.asList(); // List<String> | The value can be repeated to retrieve multiple matching values (OR condition).
    List<String> executor = Arrays.asList(); // List<String> | The value can be repeated to retrieve multiple matching values (OR condition).
    String orderBy = "orderBy_example"; // String | The name of the field to order the results by. Prefix a field name with `-` to reverse the sort order.  *New in version 2.1.0* 
    try {
      TaskInstanceCollection result = apiInstance.getMappedTaskInstances(dagId, dagRunId, taskId, limit, offset, executionDateGte, executionDateLte, startDateGte, startDateLte, endDateGte, endDateLte, updatedAtGte, updatedAtLte, durationGte, durationLte, state, pool, queue, executor, orderBy);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TaskInstanceApi#getMappedTaskInstances");
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
| **dagRunId** | **String**| The DAG run ID. | |
| **taskId** | **String**| The task ID. | |
| **limit** | **Integer**| The numbers of items to return. | [optional] [default to 100] |
| **offset** | **Integer**| The number of items to skip before starting to collect the result set. | [optional] |
| **executionDateGte** | **OffsetDateTime**| Returns objects greater or equal to the specified date.  This can be combined with execution_date_lte parameter to receive only the selected period.  | [optional] |
| **executionDateLte** | **OffsetDateTime**| Returns objects less than or equal to the specified date.  This can be combined with execution_date_gte parameter to receive only the selected period.  | [optional] |
| **startDateGte** | **OffsetDateTime**| Returns objects greater or equal the specified date.  This can be combined with start_date_lte parameter to receive only the selected period.  | [optional] |
| **startDateLte** | **OffsetDateTime**| Returns objects less or equal the specified date.  This can be combined with start_date_gte parameter to receive only the selected period.  | [optional] |
| **endDateGte** | **OffsetDateTime**| Returns objects greater or equal the specified date.  This can be combined with start_date_lte parameter to receive only the selected period.  | [optional] |
| **endDateLte** | **OffsetDateTime**| Returns objects less than or equal to the specified date.  This can be combined with start_date_gte parameter to receive only the selected period.  | [optional] |
| **updatedAtGte** | **OffsetDateTime**| Returns objects greater or equal the specified date.  This can be combined with updated_at_lte parameter to receive only the selected period.  *New in version 2.6.0*  | [optional] |
| **updatedAtLte** | **OffsetDateTime**| Returns objects less or equal the specified date.  This can be combined with updated_at_gte parameter to receive only the selected period.  *New in version 2.6.0*  | [optional] |
| **durationGte** | **BigDecimal**| Returns objects greater than or equal to the specified values.  This can be combined with duration_lte parameter to receive only the selected period.  | [optional] |
| **durationLte** | **BigDecimal**| Returns objects less than or equal to the specified values.  This can be combined with duration_gte parameter to receive only the selected range.  | [optional] |
| **state** | [**List&lt;String&gt;**](String.md)| The value can be repeated to retrieve multiple matching values (OR condition). | [optional] |
| **pool** | [**List&lt;String&gt;**](String.md)| The value can be repeated to retrieve multiple matching values (OR condition). | [optional] |
| **queue** | [**List&lt;String&gt;**](String.md)| The value can be repeated to retrieve multiple matching values (OR condition). | [optional] |
| **executor** | [**List&lt;String&gt;**](String.md)| The value can be repeated to retrieve multiple matching values (OR condition). | [optional] |
| **orderBy** | **String**| The name of the field to order the results by. Prefix a field name with &#x60;-&#x60; to reverse the sort order.  *New in version 2.1.0*  | [optional] |

### Return type

[**TaskInstanceCollection**](TaskInstanceCollection.md)

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

<a id="getTaskInstance"></a>
# **getTaskInstance**
> TaskInstance getTaskInstance(dagId, dagRunId, taskId)

Get a task instance

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TaskInstanceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    TaskInstanceApi apiInstance = new TaskInstanceApi(defaultClient);
    String dagId = "dagId_example"; // String | The DAG ID.
    String dagRunId = "dagRunId_example"; // String | The DAG run ID.
    String taskId = "taskId_example"; // String | The task ID.
    try {
      TaskInstance result = apiInstance.getTaskInstance(dagId, dagRunId, taskId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TaskInstanceApi#getTaskInstance");
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
| **dagRunId** | **String**| The DAG run ID. | |
| **taskId** | **String**| The task ID. | |

### Return type

[**TaskInstance**](TaskInstance.md)

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

<a id="getTaskInstanceDependencies"></a>
# **getTaskInstanceDependencies**
> TaskInstanceDependencyCollection getTaskInstanceDependencies(dagId, dagRunId, taskId)

Get task dependencies blocking task from getting scheduled.

Get task dependencies blocking task from getting scheduled.  *New in version 2.10.0* 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TaskInstanceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    TaskInstanceApi apiInstance = new TaskInstanceApi(defaultClient);
    String dagId = "dagId_example"; // String | The DAG ID.
    String dagRunId = "dagRunId_example"; // String | The DAG run ID.
    String taskId = "taskId_example"; // String | The task ID.
    try {
      TaskInstanceDependencyCollection result = apiInstance.getTaskInstanceDependencies(dagId, dagRunId, taskId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TaskInstanceApi#getTaskInstanceDependencies");
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
| **dagRunId** | **String**| The DAG run ID. | |
| **taskId** | **String**| The task ID. | |

### Return type

[**TaskInstanceDependencyCollection**](TaskInstanceDependencyCollection.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Success. |  -  |
| **400** | Client specified an invalid argument. |  -  |
| **401** | Request not authenticated due to missing, invalid, authentication info. |  -  |
| **403** | Client does not have sufficient permission. |  -  |
| **404** | A specified resource is not found. |  -  |

<a id="getTaskInstanceTries"></a>
# **getTaskInstanceTries**
> TaskInstanceHistoryCollection getTaskInstanceTries(dagId, dagRunId, taskId, limit, offset, orderBy)

List task instance tries

Get details of all task instance tries.  *New in version 2.10.0* 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TaskInstanceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    TaskInstanceApi apiInstance = new TaskInstanceApi(defaultClient);
    String dagId = "dagId_example"; // String | The DAG ID.
    String dagRunId = "dagRunId_example"; // String | The DAG run ID.
    String taskId = "taskId_example"; // String | The task ID.
    Integer limit = 100; // Integer | The numbers of items to return.
    Integer offset = 56; // Integer | The number of items to skip before starting to collect the result set.
    String orderBy = "orderBy_example"; // String | The name of the field to order the results by. Prefix a field name with `-` to reverse the sort order.  *New in version 2.1.0* 
    try {
      TaskInstanceHistoryCollection result = apiInstance.getTaskInstanceTries(dagId, dagRunId, taskId, limit, offset, orderBy);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TaskInstanceApi#getTaskInstanceTries");
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
| **dagRunId** | **String**| The DAG run ID. | |
| **taskId** | **String**| The task ID. | |
| **limit** | **Integer**| The numbers of items to return. | [optional] [default to 100] |
| **offset** | **Integer**| The number of items to skip before starting to collect the result set. | [optional] |
| **orderBy** | **String**| The name of the field to order the results by. Prefix a field name with &#x60;-&#x60; to reverse the sort order.  *New in version 2.1.0*  | [optional] |

### Return type

[**TaskInstanceHistoryCollection**](TaskInstanceHistoryCollection.md)

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

<a id="getTaskInstanceTryDetails"></a>
# **getTaskInstanceTryDetails**
> TaskInstanceHistory getTaskInstanceTryDetails(dagId, dagRunId, taskId, taskTryNumber)

get taskinstance try

Get details of a task instance try.  *New in version 2.10.0* 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TaskInstanceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    TaskInstanceApi apiInstance = new TaskInstanceApi(defaultClient);
    String dagId = "dagId_example"; // String | The DAG ID.
    String dagRunId = "dagRunId_example"; // String | The DAG run ID.
    String taskId = "taskId_example"; // String | The task ID.
    Integer taskTryNumber = 56; // Integer | The task try number.
    try {
      TaskInstanceHistory result = apiInstance.getTaskInstanceTryDetails(dagId, dagRunId, taskId, taskTryNumber);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TaskInstanceApi#getTaskInstanceTryDetails");
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
| **dagRunId** | **String**| The DAG run ID. | |
| **taskId** | **String**| The task ID. | |
| **taskTryNumber** | **Integer**| The task try number. | |

### Return type

[**TaskInstanceHistory**](TaskInstanceHistory.md)

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

<a id="getTaskInstances"></a>
# **getTaskInstances**
> TaskInstanceCollection getTaskInstances(dagId, dagRunId, executionDateGte, executionDateLte, startDateGte, startDateLte, endDateGte, endDateLte, updatedAtGte, updatedAtLte, durationGte, durationLte, state, pool, queue, executor, limit, offset)

List task instances

This endpoint allows specifying &#x60;~&#x60; as the dag_id, dag_run_id to retrieve DAG runs for all DAGs and DAG runs. 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TaskInstanceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    TaskInstanceApi apiInstance = new TaskInstanceApi(defaultClient);
    String dagId = "dagId_example"; // String | The DAG ID.
    String dagRunId = "dagRunId_example"; // String | The DAG run ID.
    OffsetDateTime executionDateGte = OffsetDateTime.now(); // OffsetDateTime | Returns objects greater or equal to the specified date.  This can be combined with execution_date_lte parameter to receive only the selected period. 
    OffsetDateTime executionDateLte = OffsetDateTime.now(); // OffsetDateTime | Returns objects less than or equal to the specified date.  This can be combined with execution_date_gte parameter to receive only the selected period. 
    OffsetDateTime startDateGte = OffsetDateTime.now(); // OffsetDateTime | Returns objects greater or equal the specified date.  This can be combined with start_date_lte parameter to receive only the selected period. 
    OffsetDateTime startDateLte = OffsetDateTime.now(); // OffsetDateTime | Returns objects less or equal the specified date.  This can be combined with start_date_gte parameter to receive only the selected period. 
    OffsetDateTime endDateGte = OffsetDateTime.now(); // OffsetDateTime | Returns objects greater or equal the specified date.  This can be combined with start_date_lte parameter to receive only the selected period. 
    OffsetDateTime endDateLte = OffsetDateTime.now(); // OffsetDateTime | Returns objects less than or equal to the specified date.  This can be combined with start_date_gte parameter to receive only the selected period. 
    OffsetDateTime updatedAtGte = OffsetDateTime.now(); // OffsetDateTime | Returns objects greater or equal the specified date.  This can be combined with updated_at_lte parameter to receive only the selected period.  *New in version 2.6.0* 
    OffsetDateTime updatedAtLte = OffsetDateTime.now(); // OffsetDateTime | Returns objects less or equal the specified date.  This can be combined with updated_at_gte parameter to receive only the selected period.  *New in version 2.6.0* 
    BigDecimal durationGte = new BigDecimal(78); // BigDecimal | Returns objects greater than or equal to the specified values.  This can be combined with duration_lte parameter to receive only the selected period. 
    BigDecimal durationLte = new BigDecimal(78); // BigDecimal | Returns objects less than or equal to the specified values.  This can be combined with duration_gte parameter to receive only the selected range. 
    List<String> state = Arrays.asList(); // List<String> | The value can be repeated to retrieve multiple matching values (OR condition).
    List<String> pool = Arrays.asList(); // List<String> | The value can be repeated to retrieve multiple matching values (OR condition).
    List<String> queue = Arrays.asList(); // List<String> | The value can be repeated to retrieve multiple matching values (OR condition).
    List<String> executor = Arrays.asList(); // List<String> | The value can be repeated to retrieve multiple matching values (OR condition).
    Integer limit = 100; // Integer | The numbers of items to return.
    Integer offset = 56; // Integer | The number of items to skip before starting to collect the result set.
    try {
      TaskInstanceCollection result = apiInstance.getTaskInstances(dagId, dagRunId, executionDateGte, executionDateLte, startDateGte, startDateLte, endDateGte, endDateLte, updatedAtGte, updatedAtLte, durationGte, durationLte, state, pool, queue, executor, limit, offset);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TaskInstanceApi#getTaskInstances");
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
| **dagRunId** | **String**| The DAG run ID. | |
| **executionDateGte** | **OffsetDateTime**| Returns objects greater or equal to the specified date.  This can be combined with execution_date_lte parameter to receive only the selected period.  | [optional] |
| **executionDateLte** | **OffsetDateTime**| Returns objects less than or equal to the specified date.  This can be combined with execution_date_gte parameter to receive only the selected period.  | [optional] |
| **startDateGte** | **OffsetDateTime**| Returns objects greater or equal the specified date.  This can be combined with start_date_lte parameter to receive only the selected period.  | [optional] |
| **startDateLte** | **OffsetDateTime**| Returns objects less or equal the specified date.  This can be combined with start_date_gte parameter to receive only the selected period.  | [optional] |
| **endDateGte** | **OffsetDateTime**| Returns objects greater or equal the specified date.  This can be combined with start_date_lte parameter to receive only the selected period.  | [optional] |
| **endDateLte** | **OffsetDateTime**| Returns objects less than or equal to the specified date.  This can be combined with start_date_gte parameter to receive only the selected period.  | [optional] |
| **updatedAtGte** | **OffsetDateTime**| Returns objects greater or equal the specified date.  This can be combined with updated_at_lte parameter to receive only the selected period.  *New in version 2.6.0*  | [optional] |
| **updatedAtLte** | **OffsetDateTime**| Returns objects less or equal the specified date.  This can be combined with updated_at_gte parameter to receive only the selected period.  *New in version 2.6.0*  | [optional] |
| **durationGte** | **BigDecimal**| Returns objects greater than or equal to the specified values.  This can be combined with duration_lte parameter to receive only the selected period.  | [optional] |
| **durationLte** | **BigDecimal**| Returns objects less than or equal to the specified values.  This can be combined with duration_gte parameter to receive only the selected range.  | [optional] |
| **state** | [**List&lt;String&gt;**](String.md)| The value can be repeated to retrieve multiple matching values (OR condition). | [optional] |
| **pool** | [**List&lt;String&gt;**](String.md)| The value can be repeated to retrieve multiple matching values (OR condition). | [optional] |
| **queue** | [**List&lt;String&gt;**](String.md)| The value can be repeated to retrieve multiple matching values (OR condition). | [optional] |
| **executor** | [**List&lt;String&gt;**](String.md)| The value can be repeated to retrieve multiple matching values (OR condition). | [optional] |
| **limit** | **Integer**| The numbers of items to return. | [optional] [default to 100] |
| **offset** | **Integer**| The number of items to skip before starting to collect the result set. | [optional] |

### Return type

[**TaskInstanceCollection**](TaskInstanceCollection.md)

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

<a id="getTaskInstancesBatch"></a>
# **getTaskInstancesBatch**
> TaskInstanceCollection getTaskInstancesBatch(listTaskInstanceForm)

List task instances (batch)

List task instances from all DAGs and DAG runs. This endpoint is a POST to allow filtering across a large number of DAG IDs, where as a GET it would run in to maximum HTTP request URL length limits. 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TaskInstanceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    TaskInstanceApi apiInstance = new TaskInstanceApi(defaultClient);
    ListTaskInstanceForm listTaskInstanceForm = new ListTaskInstanceForm(); // ListTaskInstanceForm | 
    try {
      TaskInstanceCollection result = apiInstance.getTaskInstancesBatch(listTaskInstanceForm);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TaskInstanceApi#getTaskInstancesBatch");
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
| **listTaskInstanceForm** | [**ListTaskInstanceForm**](ListTaskInstanceForm.md)|  | |

### Return type

[**TaskInstanceCollection**](TaskInstanceCollection.md)

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

<a id="patchMappedTaskInstance"></a>
# **patchMappedTaskInstance**
> TaskInstanceReference patchMappedTaskInstance(dagId, dagRunId, taskId, mapIndex, updateTaskInstance)

Updates the state of a mapped task instance

Updates the state for single mapped task instance. *New in version 2.5.0* 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TaskInstanceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    TaskInstanceApi apiInstance = new TaskInstanceApi(defaultClient);
    String dagId = "dagId_example"; // String | The DAG ID.
    String dagRunId = "dagRunId_example"; // String | The DAG run ID.
    String taskId = "taskId_example"; // String | The task ID.
    Integer mapIndex = 56; // Integer | The map index.
    UpdateTaskInstance updateTaskInstance = new UpdateTaskInstance(); // UpdateTaskInstance | Parameters of action
    try {
      TaskInstanceReference result = apiInstance.patchMappedTaskInstance(dagId, dagRunId, taskId, mapIndex, updateTaskInstance);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TaskInstanceApi#patchMappedTaskInstance");
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
| **dagRunId** | **String**| The DAG run ID. | |
| **taskId** | **String**| The task ID. | |
| **mapIndex** | **Integer**| The map index. | |
| **updateTaskInstance** | [**UpdateTaskInstance**](UpdateTaskInstance.md)| Parameters of action | [optional] |

### Return type

[**TaskInstanceReference**](TaskInstanceReference.md)

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

<a id="patchTaskInstance"></a>
# **patchTaskInstance**
> TaskInstanceReference patchTaskInstance(dagId, dagRunId, taskId, updateTaskInstance)

Updates the state of a task instance

Updates the state for single task instance. *New in version 2.5.0* 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TaskInstanceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    TaskInstanceApi apiInstance = new TaskInstanceApi(defaultClient);
    String dagId = "dagId_example"; // String | The DAG ID.
    String dagRunId = "dagRunId_example"; // String | The DAG run ID.
    String taskId = "taskId_example"; // String | The task ID.
    UpdateTaskInstance updateTaskInstance = new UpdateTaskInstance(); // UpdateTaskInstance | Parameters of action
    try {
      TaskInstanceReference result = apiInstance.patchTaskInstance(dagId, dagRunId, taskId, updateTaskInstance);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TaskInstanceApi#patchTaskInstance");
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
| **dagRunId** | **String**| The DAG run ID. | |
| **taskId** | **String**| The task ID. | |
| **updateTaskInstance** | [**UpdateTaskInstance**](UpdateTaskInstance.md)| Parameters of action | |

### Return type

[**TaskInstanceReference**](TaskInstanceReference.md)

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

<a id="setMappedTaskInstanceNote"></a>
# **setMappedTaskInstanceNote**
> TaskInstance setMappedTaskInstanceNote(dagId, dagRunId, taskId, mapIndex, setTaskInstanceNote)

Update the TaskInstance note.

Update the manual user note of a mapped Task Instance.  *New in version 2.5.0* 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TaskInstanceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    TaskInstanceApi apiInstance = new TaskInstanceApi(defaultClient);
    String dagId = "dagId_example"; // String | The DAG ID.
    String dagRunId = "dagRunId_example"; // String | The DAG run ID.
    String taskId = "taskId_example"; // String | The task ID.
    Integer mapIndex = 56; // Integer | The map index.
    SetTaskInstanceNote setTaskInstanceNote = new SetTaskInstanceNote(); // SetTaskInstanceNote | Parameters of set Task Instance note.
    try {
      TaskInstance result = apiInstance.setMappedTaskInstanceNote(dagId, dagRunId, taskId, mapIndex, setTaskInstanceNote);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TaskInstanceApi#setMappedTaskInstanceNote");
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
| **dagRunId** | **String**| The DAG run ID. | |
| **taskId** | **String**| The task ID. | |
| **mapIndex** | **Integer**| The map index. | |
| **setTaskInstanceNote** | [**SetTaskInstanceNote**](SetTaskInstanceNote.md)| Parameters of set Task Instance note. | |

### Return type

[**TaskInstance**](TaskInstance.md)

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

<a id="setTaskInstanceNote"></a>
# **setTaskInstanceNote**
> TaskInstance setTaskInstanceNote(dagId, dagRunId, taskId, setTaskInstanceNote)

Update the TaskInstance note.

Update the manual user note of a non-mapped Task Instance.  *New in version 2.5.0* 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TaskInstanceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    TaskInstanceApi apiInstance = new TaskInstanceApi(defaultClient);
    String dagId = "dagId_example"; // String | The DAG ID.
    String dagRunId = "dagRunId_example"; // String | The DAG run ID.
    String taskId = "taskId_example"; // String | The task ID.
    SetTaskInstanceNote setTaskInstanceNote = new SetTaskInstanceNote(); // SetTaskInstanceNote | Parameters of set Task Instance note.
    try {
      TaskInstance result = apiInstance.setTaskInstanceNote(dagId, dagRunId, taskId, setTaskInstanceNote);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TaskInstanceApi#setTaskInstanceNote");
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
| **dagRunId** | **String**| The DAG run ID. | |
| **taskId** | **String**| The task ID. | |
| **setTaskInstanceNote** | [**SetTaskInstanceNote**](SetTaskInstanceNote.md)| Parameters of set Task Instance note. | |

### Return type

[**TaskInstance**](TaskInstance.md)

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

