# DagWarningApi

All URIs are relative to */api/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**getDagWarnings**](DagWarningApi.md#getDagWarnings) | **GET** /dagWarnings | List dag warnings |


<a id="getDagWarnings"></a>
# **getDagWarnings**
> DagWarningCollection getDagWarnings(dagId, warningType, limit, offset, orderBy)

List dag warnings

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DagWarningApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    DagWarningApi apiInstance = new DagWarningApi(defaultClient);
    String dagId = "dagId_example"; // String | If set, only return DAG warnings with this dag_id.
    String warningType = "warningType_example"; // String | If set, only return DAG warnings with this type.
    Integer limit = 100; // Integer | The numbers of items to return.
    Integer offset = 56; // Integer | The number of items to skip before starting to collect the result set.
    String orderBy = "orderBy_example"; // String | The name of the field to order the results by. Prefix a field name with `-` to reverse the sort order.  *New in version 2.1.0* 
    try {
      DagWarningCollection result = apiInstance.getDagWarnings(dagId, warningType, limit, offset, orderBy);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DagWarningApi#getDagWarnings");
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
| **dagId** | **String**| If set, only return DAG warnings with this dag_id. | [optional] |
| **warningType** | **String**| If set, only return DAG warnings with this type. | [optional] |
| **limit** | **Integer**| The numbers of items to return. | [optional] [default to 100] |
| **offset** | **Integer**| The number of items to skip before starting to collect the result set. | [optional] |
| **orderBy** | **String**| The name of the field to order the results by. Prefix a field name with &#x60;-&#x60; to reverse the sort order.  *New in version 2.1.0*  | [optional] |

### Return type

[**DagWarningCollection**](DagWarningCollection.md)

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

