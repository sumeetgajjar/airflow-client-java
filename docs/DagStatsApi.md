# DagStatsApi

All URIs are relative to */api/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**getDagStats**](DagStatsApi.md#getDagStats) | **GET** /dagStats | List Dag statistics |


<a id="getDagStats"></a>
# **getDagStats**
> DagStatsCollectionSchema getDagStats(dagIds)

List Dag statistics

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DagStatsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    DagStatsApi apiInstance = new DagStatsApi(defaultClient);
    String dagIds = "dagIds_example"; // String | One or more DAG IDs separated by commas to filter relevant Dags. 
    try {
      DagStatsCollectionSchema result = apiInstance.getDagStats(dagIds);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DagStatsApi#getDagStats");
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
| **dagIds** | **String**| One or more DAG IDs separated by commas to filter relevant Dags.  | |

### Return type

[**DagStatsCollectionSchema**](DagStatsCollectionSchema.md)

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

