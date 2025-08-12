# EventLogApi

All URIs are relative to */api/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**getEventLog**](EventLogApi.md#getEventLog) | **GET** /eventLogs/{event_log_id} | Get a log entry |
| [**getEventLogs**](EventLogApi.md#getEventLogs) | **GET** /eventLogs | List log entries |


<a id="getEventLog"></a>
# **getEventLog**
> EventLog getEventLog(eventLogId)

Get a log entry

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.EventLogApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    EventLogApi apiInstance = new EventLogApi(defaultClient);
    Integer eventLogId = 56; // Integer | The event log ID.
    try {
      EventLog result = apiInstance.getEventLog(eventLogId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling EventLogApi#getEventLog");
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
| **eventLogId** | **Integer**| The event log ID. | |

### Return type

[**EventLog**](EventLog.md)

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

<a id="getEventLogs"></a>
# **getEventLogs**
> EventLogCollection getEventLogs(limit, offset, orderBy, dagId, taskId, runId, mapIndex, tryNumber, event, owner, before, after, includedEvents, excludedEvents)

List log entries

List log entries from event log.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.EventLogApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    EventLogApi apiInstance = new EventLogApi(defaultClient);
    Integer limit = 100; // Integer | The numbers of items to return.
    Integer offset = 56; // Integer | The number of items to skip before starting to collect the result set.
    String orderBy = "orderBy_example"; // String | The name of the field to order the results by. Prefix a field name with `-` to reverse the sort order.  *New in version 2.1.0* 
    String dagId = "dagId_example"; // String | Returns objects matched by the DAG ID.
    String taskId = "taskId_example"; // String | Returns objects matched by the Task ID.
    String runId = "runId_example"; // String | Returns objects matched by the Run ID.
    Integer mapIndex = 56; // Integer | Filter on map index for mapped task.
    Integer tryNumber = 56; // Integer | Filter on try_number for task instance.
    String event = "event_example"; // String | The name of event log.
    String owner = "owner_example"; // String | The owner's name of event log.
    OffsetDateTime before = OffsetDateTime.now(); // OffsetDateTime | Timestamp to select event logs occurring before.
    OffsetDateTime after = OffsetDateTime.now(); // OffsetDateTime | Timestamp to select event logs occurring after.
    String includedEvents = "includedEvents_example"; // String | One or more event names separated by commas. If set, only return event logs with events matching this pattern. *New in version 2.9.0* 
    String excludedEvents = "excludedEvents_example"; // String | One or more event names separated by commas. If set, only return event logs with events that do not match this pattern. *New in version 2.9.0* 
    try {
      EventLogCollection result = apiInstance.getEventLogs(limit, offset, orderBy, dagId, taskId, runId, mapIndex, tryNumber, event, owner, before, after, includedEvents, excludedEvents);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling EventLogApi#getEventLogs");
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
| **dagId** | **String**| Returns objects matched by the DAG ID. | [optional] |
| **taskId** | **String**| Returns objects matched by the Task ID. | [optional] |
| **runId** | **String**| Returns objects matched by the Run ID. | [optional] |
| **mapIndex** | **Integer**| Filter on map index for mapped task. | [optional] |
| **tryNumber** | **Integer**| Filter on try_number for task instance. | [optional] |
| **event** | **String**| The name of event log. | [optional] |
| **owner** | **String**| The owner&#39;s name of event log. | [optional] |
| **before** | **OffsetDateTime**| Timestamp to select event logs occurring before. | [optional] |
| **after** | **OffsetDateTime**| Timestamp to select event logs occurring after. | [optional] |
| **includedEvents** | **String**| One or more event names separated by commas. If set, only return event logs with events matching this pattern. *New in version 2.9.0*  | [optional] |
| **excludedEvents** | **String**| One or more event names separated by commas. If set, only return event logs with events that do not match this pattern. *New in version 2.9.0*  | [optional] |

### Return type

[**EventLogCollection**](EventLogCollection.md)

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

