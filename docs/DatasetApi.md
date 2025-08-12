# DatasetApi

All URIs are relative to */api/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createDatasetEvent**](DatasetApi.md#createDatasetEvent) | **POST** /datasets/events | Create dataset event |
| [**deleteDagDatasetQueuedEvent**](DatasetApi.md#deleteDagDatasetQueuedEvent) | **DELETE** /dags/{dag_id}/datasets/queuedEvent/{uri} | Delete a queued Dataset event for a DAG. |
| [**deleteDagDatasetQueuedEvents**](DatasetApi.md#deleteDagDatasetQueuedEvents) | **DELETE** /dags/{dag_id}/datasets/queuedEvent | Delete queued Dataset events for a DAG. |
| [**deleteDatasetQueuedEvents**](DatasetApi.md#deleteDatasetQueuedEvents) | **DELETE** /datasets/queuedEvent/{uri} | Delete queued Dataset events for a Dataset. |
| [**getDagDatasetQueuedEvent**](DatasetApi.md#getDagDatasetQueuedEvent) | **GET** /dags/{dag_id}/datasets/queuedEvent/{uri} | Get a queued Dataset event for a DAG |
| [**getDagDatasetQueuedEvents**](DatasetApi.md#getDagDatasetQueuedEvents) | **GET** /dags/{dag_id}/datasets/queuedEvent | Get queued Dataset events for a DAG. |
| [**getDataset**](DatasetApi.md#getDataset) | **GET** /datasets/{uri} | Get a dataset |
| [**getDatasetEvents**](DatasetApi.md#getDatasetEvents) | **GET** /datasets/events | Get dataset events |
| [**getDatasetQueuedEvents**](DatasetApi.md#getDatasetQueuedEvents) | **GET** /datasets/queuedEvent/{uri} | Get queued Dataset events for a Dataset. |
| [**getDatasets**](DatasetApi.md#getDatasets) | **GET** /datasets | List datasets |
| [**getUpstreamDatasetEvents**](DatasetApi.md#getUpstreamDatasetEvents) | **GET** /dags/{dag_id}/dagRuns/{dag_run_id}/upstreamDatasetEvents | Get dataset events for a DAG run |


<a id="createDatasetEvent"></a>
# **createDatasetEvent**
> DatasetEvent createDatasetEvent(createDatasetEvent)

Create dataset event

Create dataset event

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DatasetApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    DatasetApi apiInstance = new DatasetApi(defaultClient);
    CreateDatasetEvent createDatasetEvent = new CreateDatasetEvent(); // CreateDatasetEvent | 
    try {
      DatasetEvent result = apiInstance.createDatasetEvent(createDatasetEvent);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DatasetApi#createDatasetEvent");
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
| **createDatasetEvent** | [**CreateDatasetEvent**](CreateDatasetEvent.md)|  | |

### Return type

[**DatasetEvent**](DatasetEvent.md)

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

<a id="deleteDagDatasetQueuedEvent"></a>
# **deleteDagDatasetQueuedEvent**
> deleteDagDatasetQueuedEvent(dagId, uri, before)

Delete a queued Dataset event for a DAG.

Delete a queued Dataset event for a DAG.  *New in version 2.9.0* 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DatasetApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    DatasetApi apiInstance = new DatasetApi(defaultClient);
    String dagId = "dagId_example"; // String | The DAG ID.
    String uri = "uri_example"; // String | The encoded Dataset URI
    OffsetDateTime before = OffsetDateTime.now(); // OffsetDateTime | Timestamp to select event logs occurring before.
    try {
      apiInstance.deleteDagDatasetQueuedEvent(dagId, uri, before);
    } catch (ApiException e) {
      System.err.println("Exception when calling DatasetApi#deleteDagDatasetQueuedEvent");
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
| **uri** | **String**| The encoded Dataset URI | |
| **before** | **OffsetDateTime**| Timestamp to select event logs occurring before. | [optional] |

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

<a id="deleteDagDatasetQueuedEvents"></a>
# **deleteDagDatasetQueuedEvents**
> deleteDagDatasetQueuedEvents(dagId, before)

Delete queued Dataset events for a DAG.

Delete queued Dataset events for a DAG.  *New in version 2.9.0* 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DatasetApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    DatasetApi apiInstance = new DatasetApi(defaultClient);
    String dagId = "dagId_example"; // String | The DAG ID.
    OffsetDateTime before = OffsetDateTime.now(); // OffsetDateTime | Timestamp to select event logs occurring before.
    try {
      apiInstance.deleteDagDatasetQueuedEvents(dagId, before);
    } catch (ApiException e) {
      System.err.println("Exception when calling DatasetApi#deleteDagDatasetQueuedEvents");
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
| **before** | **OffsetDateTime**| Timestamp to select event logs occurring before. | [optional] |

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

<a id="deleteDatasetQueuedEvents"></a>
# **deleteDatasetQueuedEvents**
> deleteDatasetQueuedEvents(uri, before)

Delete queued Dataset events for a Dataset.

Delete queued Dataset events for a Dataset.  *New in version 2.9.0* 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DatasetApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    DatasetApi apiInstance = new DatasetApi(defaultClient);
    String uri = "uri_example"; // String | The encoded Dataset URI
    OffsetDateTime before = OffsetDateTime.now(); // OffsetDateTime | Timestamp to select event logs occurring before.
    try {
      apiInstance.deleteDatasetQueuedEvents(uri, before);
    } catch (ApiException e) {
      System.err.println("Exception when calling DatasetApi#deleteDatasetQueuedEvents");
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
| **uri** | **String**| The encoded Dataset URI | |
| **before** | **OffsetDateTime**| Timestamp to select event logs occurring before. | [optional] |

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

<a id="getDagDatasetQueuedEvent"></a>
# **getDagDatasetQueuedEvent**
> QueuedEvent getDagDatasetQueuedEvent(dagId, uri, before)

Get a queued Dataset event for a DAG

Get a queued Dataset event for a DAG.  *New in version 2.9.0* 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DatasetApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    DatasetApi apiInstance = new DatasetApi(defaultClient);
    String dagId = "dagId_example"; // String | The DAG ID.
    String uri = "uri_example"; // String | The encoded Dataset URI
    OffsetDateTime before = OffsetDateTime.now(); // OffsetDateTime | Timestamp to select event logs occurring before.
    try {
      QueuedEvent result = apiInstance.getDagDatasetQueuedEvent(dagId, uri, before);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DatasetApi#getDagDatasetQueuedEvent");
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
| **uri** | **String**| The encoded Dataset URI | |
| **before** | **OffsetDateTime**| Timestamp to select event logs occurring before. | [optional] |

### Return type

[**QueuedEvent**](QueuedEvent.md)

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

<a id="getDagDatasetQueuedEvents"></a>
# **getDagDatasetQueuedEvents**
> QueuedEventCollection getDagDatasetQueuedEvents(dagId, before)

Get queued Dataset events for a DAG.

Get queued Dataset events for a DAG.  *New in version 2.9.0* 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DatasetApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    DatasetApi apiInstance = new DatasetApi(defaultClient);
    String dagId = "dagId_example"; // String | The DAG ID.
    OffsetDateTime before = OffsetDateTime.now(); // OffsetDateTime | Timestamp to select event logs occurring before.
    try {
      QueuedEventCollection result = apiInstance.getDagDatasetQueuedEvents(dagId, before);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DatasetApi#getDagDatasetQueuedEvents");
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
| **before** | **OffsetDateTime**| Timestamp to select event logs occurring before. | [optional] |

### Return type

[**QueuedEventCollection**](QueuedEventCollection.md)

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

<a id="getDataset"></a>
# **getDataset**
> Dataset getDataset(uri)

Get a dataset

Get a dataset by uri.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DatasetApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    DatasetApi apiInstance = new DatasetApi(defaultClient);
    String uri = "uri_example"; // String | The encoded Dataset URI
    try {
      Dataset result = apiInstance.getDataset(uri);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DatasetApi#getDataset");
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
| **uri** | **String**| The encoded Dataset URI | |

### Return type

[**Dataset**](Dataset.md)

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

<a id="getDatasetEvents"></a>
# **getDatasetEvents**
> DatasetEventCollection getDatasetEvents(limit, offset, orderBy, datasetId, sourceDagId, sourceTaskId, sourceRunId, sourceMapIndex)

Get dataset events

Get dataset events

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DatasetApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    DatasetApi apiInstance = new DatasetApi(defaultClient);
    Integer limit = 100; // Integer | The numbers of items to return.
    Integer offset = 56; // Integer | The number of items to skip before starting to collect the result set.
    String orderBy = "orderBy_example"; // String | The name of the field to order the results by. Prefix a field name with `-` to reverse the sort order.  *New in version 2.1.0* 
    Integer datasetId = 56; // Integer | The Dataset ID that updated the dataset.
    String sourceDagId = "sourceDagId_example"; // String | The DAG ID that updated the dataset.
    String sourceTaskId = "sourceTaskId_example"; // String | The task ID that updated the dataset.
    String sourceRunId = "sourceRunId_example"; // String | The DAG run ID that updated the dataset.
    Integer sourceMapIndex = 56; // Integer | The map index that updated the dataset.
    try {
      DatasetEventCollection result = apiInstance.getDatasetEvents(limit, offset, orderBy, datasetId, sourceDagId, sourceTaskId, sourceRunId, sourceMapIndex);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DatasetApi#getDatasetEvents");
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
| **datasetId** | **Integer**| The Dataset ID that updated the dataset. | [optional] |
| **sourceDagId** | **String**| The DAG ID that updated the dataset. | [optional] |
| **sourceTaskId** | **String**| The task ID that updated the dataset. | [optional] |
| **sourceRunId** | **String**| The DAG run ID that updated the dataset. | [optional] |
| **sourceMapIndex** | **Integer**| The map index that updated the dataset. | [optional] |

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

<a id="getDatasetQueuedEvents"></a>
# **getDatasetQueuedEvents**
> QueuedEventCollection getDatasetQueuedEvents(uri, before)

Get queued Dataset events for a Dataset.

Get queued Dataset events for a Dataset  *New in version 2.9.0* 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DatasetApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    DatasetApi apiInstance = new DatasetApi(defaultClient);
    String uri = "uri_example"; // String | The encoded Dataset URI
    OffsetDateTime before = OffsetDateTime.now(); // OffsetDateTime | Timestamp to select event logs occurring before.
    try {
      QueuedEventCollection result = apiInstance.getDatasetQueuedEvents(uri, before);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DatasetApi#getDatasetQueuedEvents");
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
| **uri** | **String**| The encoded Dataset URI | |
| **before** | **OffsetDateTime**| Timestamp to select event logs occurring before. | [optional] |

### Return type

[**QueuedEventCollection**](QueuedEventCollection.md)

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

<a id="getDatasets"></a>
# **getDatasets**
> DatasetCollection getDatasets(limit, offset, orderBy, uriPattern, dagIds)

List datasets

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DatasetApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    DatasetApi apiInstance = new DatasetApi(defaultClient);
    Integer limit = 100; // Integer | The numbers of items to return.
    Integer offset = 56; // Integer | The number of items to skip before starting to collect the result set.
    String orderBy = "orderBy_example"; // String | The name of the field to order the results by. Prefix a field name with `-` to reverse the sort order.  *New in version 2.1.0* 
    String uriPattern = "uriPattern_example"; // String | If set, only return datasets with uris matching this pattern. 
    String dagIds = "dagIds_example"; // String | One or more DAG IDs separated by commas to filter datasets by associated DAGs either consuming or producing.  *New in version 2.9.0* 
    try {
      DatasetCollection result = apiInstance.getDatasets(limit, offset, orderBy, uriPattern, dagIds);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DatasetApi#getDatasets");
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
| **uriPattern** | **String**| If set, only return datasets with uris matching this pattern.  | [optional] |
| **dagIds** | **String**| One or more DAG IDs separated by commas to filter datasets by associated DAGs either consuming or producing.  *New in version 2.9.0*  | [optional] |

### Return type

[**DatasetCollection**](DatasetCollection.md)

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
import org.openapitools.client.api.DatasetApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    DatasetApi apiInstance = new DatasetApi(defaultClient);
    String dagId = "dagId_example"; // String | The DAG ID.
    String dagRunId = "dagRunId_example"; // String | The DAG run ID.
    try {
      DatasetEventCollection result = apiInstance.getUpstreamDatasetEvents(dagId, dagRunId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DatasetApi#getUpstreamDatasetEvents");
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

