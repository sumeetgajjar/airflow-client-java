# DagRunApi

All URIs are relative to */api/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**clearDagRun**](DagRunApi.md#clearDagRun) | **POST** /dags/{dag_id}/dagRuns/{dag_run_id}/clear | Clear a DAG run |
| [**deleteDagRun**](DagRunApi.md#deleteDagRun) | **DELETE** /dags/{dag_id}/dagRuns/{dag_run_id} | Delete a DAG run |
| [**getDagRun**](DagRunApi.md#getDagRun) | **GET** /dags/{dag_id}/dagRuns/{dag_run_id} | Get a DAG run |
| [**getDagRuns**](DagRunApi.md#getDagRuns) | **GET** /dags/{dag_id}/dagRuns | List DAG runs |
| [**getDagRunsBatch**](DagRunApi.md#getDagRunsBatch) | **POST** /dags/~/dagRuns/list | List DAG runs (batch) |
| [**getUpstreamDatasetEvents**](DagRunApi.md#getUpstreamDatasetEvents) | **GET** /dags/{dag_id}/dagRuns/{dag_run_id}/upstreamDatasetEvents | Get dataset events for a DAG run |
| [**postDagRun**](DagRunApi.md#postDagRun) | **POST** /dags/{dag_id}/dagRuns | Trigger a new DAG run. |
| [**setDagRunNote**](DagRunApi.md#setDagRunNote) | **PATCH** /dags/{dag_id}/dagRuns/{dag_run_id}/setNote | Update the DagRun note. |
| [**updateDagRunState**](DagRunApi.md#updateDagRunState) | **PATCH** /dags/{dag_id}/dagRuns/{dag_run_id} | Modify a DAG run |


<a id="clearDagRun"></a>
# **clearDagRun**
> ClearDagRun200Response clearDagRun(dagId, dagRunId, clearDagRun)

Clear a DAG run

Clear a DAG run.  *New in version 2.4.0* 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DagRunApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    DagRunApi apiInstance = new DagRunApi(defaultClient);
    String dagId = "dagId_example"; // String | The DAG ID.
    String dagRunId = "dagRunId_example"; // String | The DAG run ID.
    ClearDagRun clearDagRun = new ClearDagRun(); // ClearDagRun | 
    try {
      ClearDagRun200Response result = apiInstance.clearDagRun(dagId, dagRunId, clearDagRun);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DagRunApi#clearDagRun");
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
| **clearDagRun** | [**ClearDagRun**](ClearDagRun.md)|  | |

### Return type

[**ClearDagRun200Response**](ClearDagRun200Response.md)

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

<a id="deleteDagRun"></a>
# **deleteDagRun**
> deleteDagRun(dagId, dagRunId)

Delete a DAG run

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DagRunApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    DagRunApi apiInstance = new DagRunApi(defaultClient);
    String dagId = "dagId_example"; // String | The DAG ID.
    String dagRunId = "dagRunId_example"; // String | The DAG run ID.
    try {
      apiInstance.deleteDagRun(dagId, dagRunId);
    } catch (ApiException e) {
      System.err.println("Exception when calling DagRunApi#deleteDagRun");
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

<a id="getDagRun"></a>
# **getDagRun**
> DAGRun getDagRun(dagId, dagRunId, fields)

Get a DAG run

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DagRunApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    DagRunApi apiInstance = new DagRunApi(defaultClient);
    String dagId = "dagId_example"; // String | The DAG ID.
    String dagRunId = "dagRunId_example"; // String | The DAG run ID.
    List<String> fields = Arrays.asList(); // List<String> | List of field for return. 
    try {
      DAGRun result = apiInstance.getDagRun(dagId, dagRunId, fields);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DagRunApi#getDagRun");
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
| **fields** | [**List&lt;String&gt;**](String.md)| List of field for return.  | [optional] |

### Return type

[**DAGRun**](DAGRun.md)

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

<a id="getDagRuns"></a>
# **getDagRuns**
> DAGRunCollection getDagRuns(dagId, limit, offset, executionDateGte, executionDateLte, startDateGte, startDateLte, endDateGte, endDateLte, updatedAtGte, updatedAtLte, state, orderBy, fields)

List DAG runs

This endpoint allows specifying &#x60;~&#x60; as the dag_id to retrieve DAG runs for all DAGs. 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DagRunApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    DagRunApi apiInstance = new DagRunApi(defaultClient);
    String dagId = "dagId_example"; // String | The DAG ID.
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
    List<String> state = Arrays.asList(); // List<String> | The value can be repeated to retrieve multiple matching values (OR condition).
    String orderBy = "orderBy_example"; // String | The name of the field to order the results by. Prefix a field name with `-` to reverse the sort order.  *New in version 2.1.0* 
    List<String> fields = Arrays.asList(); // List<String> | List of field for return. 
    try {
      DAGRunCollection result = apiInstance.getDagRuns(dagId, limit, offset, executionDateGte, executionDateLte, startDateGte, startDateLte, endDateGte, endDateLte, updatedAtGte, updatedAtLte, state, orderBy, fields);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DagRunApi#getDagRuns");
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
| **state** | [**List&lt;String&gt;**](String.md)| The value can be repeated to retrieve multiple matching values (OR condition). | [optional] |
| **orderBy** | **String**| The name of the field to order the results by. Prefix a field name with &#x60;-&#x60; to reverse the sort order.  *New in version 2.1.0*  | [optional] |
| **fields** | [**List&lt;String&gt;**](String.md)| List of field for return.  | [optional] |

### Return type

[**DAGRunCollection**](DAGRunCollection.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | List of DAG runs. |  -  |
| **401** | Request not authenticated due to missing, invalid, authentication info. |  -  |

<a id="getDagRunsBatch"></a>
# **getDagRunsBatch**
> DAGRunCollection getDagRunsBatch(listDagRunsForm)

List DAG runs (batch)

This endpoint is a POST to allow filtering across a large number of DAG IDs, where as a GET it would run in to maximum HTTP request URL length limit. 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DagRunApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    DagRunApi apiInstance = new DagRunApi(defaultClient);
    ListDagRunsForm listDagRunsForm = new ListDagRunsForm(); // ListDagRunsForm | 
    try {
      DAGRunCollection result = apiInstance.getDagRunsBatch(listDagRunsForm);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DagRunApi#getDagRunsBatch");
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
| **listDagRunsForm** | [**ListDagRunsForm**](ListDagRunsForm.md)|  | |

### Return type

[**DAGRunCollection**](DAGRunCollection.md)

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

<a id="getUpstreamDatasetEvents"></a>
# **getUpstreamDatasetEvents**
> DatasetEventCollection getUpstreamDatasetEvents(dagId, dagRunId)

Get dataset events for a DAG run

Get datasets for a dag run.  *New in version 2.4.0* 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DagRunApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    DagRunApi apiInstance = new DagRunApi(defaultClient);
    String dagId = "dagId_example"; // String | The DAG ID.
    String dagRunId = "dagRunId_example"; // String | The DAG run ID.
    try {
      DatasetEventCollection result = apiInstance.getUpstreamDatasetEvents(dagId, dagRunId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DagRunApi#getUpstreamDatasetEvents");
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

### Return type

[**DatasetEventCollection**](DatasetEventCollection.md)

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

<a id="postDagRun"></a>
# **postDagRun**
> DAGRun postDagRun(dagId, daGRun)

Trigger a new DAG run.

This will initiate a dagrun. If DAG is paused then dagrun state will remain queued, and the task won&#39;t run. 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DagRunApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    DagRunApi apiInstance = new DagRunApi(defaultClient);
    String dagId = "dagId_example"; // String | The DAG ID.
    DAGRun daGRun = new DAGRun(); // DAGRun | 
    try {
      DAGRun result = apiInstance.postDagRun(dagId, daGRun);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DagRunApi#postDagRun");
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
| **daGRun** | [**DAGRun**](DAGRun.md)|  | |

### Return type

[**DAGRun**](DAGRun.md)

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
| **409** | An existing resource conflicts with the request. |  -  |
| **403** | Client does not have sufficient permission. |  -  |
| **404** | A specified resource is not found. |  -  |

<a id="setDagRunNote"></a>
# **setDagRunNote**
> DAGRun setDagRunNote(dagId, dagRunId, setDagRunNote)

Update the DagRun note.

Update the manual user note of a DagRun.  *New in version 2.5.0* 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DagRunApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    DagRunApi apiInstance = new DagRunApi(defaultClient);
    String dagId = "dagId_example"; // String | The DAG ID.
    String dagRunId = "dagRunId_example"; // String | The DAG run ID.
    SetDagRunNote setDagRunNote = new SetDagRunNote(); // SetDagRunNote | Parameters of set DagRun note.
    try {
      DAGRun result = apiInstance.setDagRunNote(dagId, dagRunId, setDagRunNote);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DagRunApi#setDagRunNote");
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
| **setDagRunNote** | [**SetDagRunNote**](SetDagRunNote.md)| Parameters of set DagRun note. | |

### Return type

[**DAGRun**](DAGRun.md)

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

<a id="updateDagRunState"></a>
# **updateDagRunState**
> DAGRun updateDagRunState(dagId, dagRunId, updateDagRunState)

Modify a DAG run

Modify a DAG run.  *New in version 2.2.0* 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DagRunApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    DagRunApi apiInstance = new DagRunApi(defaultClient);
    String dagId = "dagId_example"; // String | The DAG ID.
    String dagRunId = "dagRunId_example"; // String | The DAG run ID.
    UpdateDagRunState updateDagRunState = new UpdateDagRunState(); // UpdateDagRunState | 
    try {
      DAGRun result = apiInstance.updateDagRunState(dagId, dagRunId, updateDagRunState);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DagRunApi#updateDagRunState");
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
| **updateDagRunState** | [**UpdateDagRunState**](UpdateDagRunState.md)|  | |

### Return type

[**DAGRun**](DAGRun.md)

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

