/*
 * Airflow API (Stable)
 * # Overview  To facilitate management, Apache Airflow supports a range of REST API endpoints across its objects. This section provides an overview of the API design, methods, and supported use cases.  Most of the endpoints accept `JSON` as input and return `JSON` responses. This means that you must usually add the following headers to your request: ``` Content-type: application/json Accept: application/json ```  ## Resources  The term `resource` refers to a single type of object in the Airflow metadata. An API is broken up by its endpoint's corresponding resource. The name of a resource is typically plural and expressed in camelCase. Example: `dagRuns`.  Resource names are used as part of endpoint URLs, as well as in API parameters and responses.  ## CRUD Operations  The platform supports **C**reate, **R**ead, **U**pdate, and **D**elete operations on most resources. You can review the standards for these operations and their standard parameters below.  Some endpoints have special behavior as exceptions.  ### Create  To create a resource, you typically submit an HTTP `POST` request with the resource's required metadata in the request body. The response returns a `201 Created` response code upon success with the resource's metadata, including its internal `id`, in the response body.  ### Read  The HTTP `GET` request can be used to read a resource or to list a number of resources.  A resource's `id` can be submitted in the request parameters to read a specific resource. The response usually returns a `200 OK` response code upon success, with the resource's metadata in the response body.  If a `GET` request does not include a specific resource `id`, it is treated as a list request. The response usually returns a `200 OK` response code upon success, with an object containing a list of resources' metadata in the response body.  When reading resources, some common query parameters are usually available. e.g.: ``` v1/connections?limit=25&offset=25 ```  |Query Parameter|Type|Description| |---------------|----|-----------| |limit|integer|Maximum number of objects to fetch. Usually 25 by default| |offset|integer|Offset after which to start returning objects. For use with limit query parameter.|  ### Update  Updating a resource requires the resource `id`, and is typically done using an HTTP `PATCH` request, with the fields to modify in the request body. The response usually returns a `200 OK` response code upon success, with information about the modified resource in the response body.  ### Delete  Deleting a resource requires the resource `id` and is typically executed via an HTTP `DELETE` request. The response usually returns a `204 No Content` response code upon success.  ## Conventions  - Resource names are plural and expressed in camelCase. - Names are consistent between URL parameter name and field name.  - Field names are in snake_case. ```json {     \"description\": \"string\",     \"name\": \"string\",     \"occupied_slots\": 0,     \"open_slots\": 0     \"queued_slots\": 0,     \"running_slots\": 0,     \"scheduled_slots\": 0,     \"slots\": 0, } ```  ### Update Mask  Update mask is available as a query parameter in patch endpoints. It is used to notify the API which fields you want to update. Using `update_mask` makes it easier to update objects by helping the server know which fields to update in an object instead of updating all fields. The update request ignores any fields that aren't specified in the field mask, leaving them with their current values.  Example: ```   resource = request.get('/resource/my-id').json()   resource['my_field'] = 'new-value'   request.patch('/resource/my-id?update_mask=my_field', data=json.dumps(resource)) ```  ## Versioning and Endpoint Lifecycle  - API versioning is not synchronized to specific releases of the Apache Airflow. - APIs are designed to be backward compatible. - Any changes to the API will first go through a deprecation phase.  # Trying the API  You can use a third party client, such as [curl](https://curl.haxx.se/), [HTTPie](https://httpie.org/), [Postman](https://www.postman.com/) or [the Insomnia rest client](https://insomnia.rest/) to test the Apache Airflow API.  Note that you will need to pass credentials data.  For e.g., here is how to pause a DAG with [curl](https://curl.haxx.se/), when basic authorization is used: ```bash curl -X PATCH 'https://example.com/api/v1/dags/{dag_id}?update_mask=is_paused' \\ -H 'Content-Type: application/json' \\ --user \"username:password\" \\ -d '{     \"is_paused\": true }' ```  Using a graphical tool such as [Postman](https://www.postman.com/) or [Insomnia](https://insomnia.rest/), it is possible to import the API specifications directly:  1. Download the API specification by clicking the **Download** button at the top of this document 2. Import the JSON specification in the graphical tool of your choice.   - In *Postman*, you can click the **import** button at the top   - With *Insomnia*, you can just drag-and-drop the file on the UI  Note that with *Postman*, you can also generate code snippets by selecting a request and clicking on the **Code** button.  ## Enabling CORS  [Cross-origin resource sharing (CORS)](https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS) is a browser security feature that restricts HTTP requests that are initiated from scripts running in the browser.  For details on enabling/configuring CORS, see [Enabling CORS](https://airflow.apache.org/docs/apache-airflow/stable/security/api.html).  # Authentication  To be able to meet the requirements of many organizations, Airflow supports many authentication methods, and it is even possible to add your own method.  If you want to check which auth backend is currently set, you can use `airflow config get-value api auth_backends` command as in the example below. ```bash $ airflow config get-value api auth_backends airflow.api.auth.backend.basic_auth ``` The default is to deny all requests.  For details on configuring the authentication, see [API Authorization](https://airflow.apache.org/docs/apache-airflow/stable/security/api.html).  # Errors  We follow the error response format proposed in [RFC 7807](https://tools.ietf.org/html/rfc7807) also known as Problem Details for HTTP APIs. As with our normal API responses, your client must be prepared to gracefully handle additional members of the response.  ## Unauthenticated  This indicates that the request has not been applied because it lacks valid authentication credentials for the target resource. Please check that you have valid credentials.  ## PermissionDenied  This response means that the server understood the request but refuses to authorize it because it lacks sufficient rights to the resource. It happens when you do not have the necessary permission to execute the action you performed. You need to get the appropriate permissions in other to resolve this error.  ## BadRequest  This response means that the server cannot or will not process the request due to something that is perceived to be a client error (e.g., malformed request syntax, invalid request message framing, or deceptive request routing). To resolve this, please ensure that your syntax is correct.  ## NotFound  This client error response indicates that the server cannot find the requested resource.  ## MethodNotAllowed  Indicates that the request method is known by the server but is not supported by the target resource.  ## NotAcceptable  The target resource does not have a current representation that would be acceptable to the user agent, according to the proactive negotiation header fields received in the request, and the server is unwilling to supply a default representation.  ## AlreadyExists  The request could not be completed due to a conflict with the current state of the target resource, e.g. the resource it tries to create already exists.  ## Unknown  This means that the server encountered an unexpected condition that prevented it from fulfilling the request. 
 *
 * The version of the OpenAPI document: 2.10.5
 * Contact: dev@airflow.apache.org
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.openapitools.client.api;

import org.openapitools.client.ApiCallback;
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.ApiResponse;
import org.openapitools.client.Configuration;
import org.openapitools.client.Pair;
import org.openapitools.client.ProgressRequestBody;
import org.openapitools.client.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;


import org.openapitools.client.model.CreateDatasetEvent;
import org.openapitools.client.model.Dataset;
import org.openapitools.client.model.DatasetCollection;
import org.openapitools.client.model.DatasetEvent;
import org.openapitools.client.model.DatasetEventCollection;
import org.openapitools.client.model.Error;
import java.time.OffsetDateTime;
import org.openapitools.client.model.QueuedEvent;
import org.openapitools.client.model.QueuedEventCollection;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatasetApi {
    private ApiClient localVarApiClient;
    private int localHostIndex;
    private String localCustomBaseUrl;

    public DatasetApi() {
        this(Configuration.getDefaultApiClient());
    }

    public DatasetApi(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return localVarApiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public int getHostIndex() {
        return localHostIndex;
    }

    public void setHostIndex(int hostIndex) {
        this.localHostIndex = hostIndex;
    }

    public String getCustomBaseUrl() {
        return localCustomBaseUrl;
    }

    public void setCustomBaseUrl(String customBaseUrl) {
        this.localCustomBaseUrl = customBaseUrl;
    }

    /**
     * Build call for createDatasetEvent
     * @param createDatasetEvent  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Client specified an invalid argument. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Client does not have sufficient permission. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> A specified resource is not found. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call createDatasetEventCall(@javax.annotation.Nonnull CreateDatasetEvent createDatasetEvent, final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = createDatasetEvent;

        // create path and map variables
        String localVarPath = "/datasets/events";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] {  };
        return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call createDatasetEventValidateBeforeCall(@javax.annotation.Nonnull CreateDatasetEvent createDatasetEvent, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'createDatasetEvent' is set
        if (createDatasetEvent == null) {
            throw new ApiException("Missing the required parameter 'createDatasetEvent' when calling createDatasetEvent(Async)");
        }

        return createDatasetEventCall(createDatasetEvent, _callback);

    }

    /**
     * Create dataset event
     * Create dataset event
     * @param createDatasetEvent  (required)
     * @return DatasetEvent
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Client specified an invalid argument. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Client does not have sufficient permission. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> A specified resource is not found. </td><td>  -  </td></tr>
     </table>
     */
    public DatasetEvent createDatasetEvent(@javax.annotation.Nonnull CreateDatasetEvent createDatasetEvent) throws ApiException {
        ApiResponse<DatasetEvent> localVarResp = createDatasetEventWithHttpInfo(createDatasetEvent);
        return localVarResp.getData();
    }

    /**
     * Create dataset event
     * Create dataset event
     * @param createDatasetEvent  (required)
     * @return ApiResponse&lt;DatasetEvent&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Client specified an invalid argument. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Client does not have sufficient permission. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> A specified resource is not found. </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<DatasetEvent> createDatasetEventWithHttpInfo(@javax.annotation.Nonnull CreateDatasetEvent createDatasetEvent) throws ApiException {
        okhttp3.Call localVarCall = createDatasetEventValidateBeforeCall(createDatasetEvent, null);
        Type localVarReturnType = new TypeToken<DatasetEvent>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Create dataset event (asynchronously)
     * Create dataset event
     * @param createDatasetEvent  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Client specified an invalid argument. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Client does not have sufficient permission. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> A specified resource is not found. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call createDatasetEventAsync(@javax.annotation.Nonnull CreateDatasetEvent createDatasetEvent, final ApiCallback<DatasetEvent> _callback) throws ApiException {

        okhttp3.Call localVarCall = createDatasetEventValidateBeforeCall(createDatasetEvent, _callback);
        Type localVarReturnType = new TypeToken<DatasetEvent>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for deleteDagDatasetQueuedEvent
     * @param dagId The DAG ID. (required)
     * @param uri The encoded Dataset URI (required)
     * @param before Timestamp to select event logs occurring before. (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 204 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Client specified an invalid argument. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Client does not have sufficient permission. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> A specified resource is not found. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call deleteDagDatasetQueuedEventCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String uri, @javax.annotation.Nullable OffsetDateTime before, final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/dags/{dag_id}/datasets/queuedEvent/{uri}"
            .replace("{" + "dag_id" + "}", localVarApiClient.escapeString(dagId.toString()))
            .replace("{" + "uri" + "}", localVarApiClient.escapeString(uri.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (before != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("before", before));
        }

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] {  };
        return localVarApiClient.buildCall(basePath, localVarPath, "DELETE", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call deleteDagDatasetQueuedEventValidateBeforeCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String uri, @javax.annotation.Nullable OffsetDateTime before, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'dagId' is set
        if (dagId == null) {
            throw new ApiException("Missing the required parameter 'dagId' when calling deleteDagDatasetQueuedEvent(Async)");
        }

        // verify the required parameter 'uri' is set
        if (uri == null) {
            throw new ApiException("Missing the required parameter 'uri' when calling deleteDagDatasetQueuedEvent(Async)");
        }

        return deleteDagDatasetQueuedEventCall(dagId, uri, before, _callback);

    }

    /**
     * Delete a queued Dataset event for a DAG.
     * Delete a queued Dataset event for a DAG.  *New in version 2.9.0* 
     * @param dagId The DAG ID. (required)
     * @param uri The encoded Dataset URI (required)
     * @param before Timestamp to select event logs occurring before. (optional)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 204 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Client specified an invalid argument. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Client does not have sufficient permission. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> A specified resource is not found. </td><td>  -  </td></tr>
     </table>
     */
    public void deleteDagDatasetQueuedEvent(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String uri, @javax.annotation.Nullable OffsetDateTime before) throws ApiException {
        deleteDagDatasetQueuedEventWithHttpInfo(dagId, uri, before);
    }

    /**
     * Delete a queued Dataset event for a DAG.
     * Delete a queued Dataset event for a DAG.  *New in version 2.9.0* 
     * @param dagId The DAG ID. (required)
     * @param uri The encoded Dataset URI (required)
     * @param before Timestamp to select event logs occurring before. (optional)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 204 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Client specified an invalid argument. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Client does not have sufficient permission. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> A specified resource is not found. </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Void> deleteDagDatasetQueuedEventWithHttpInfo(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String uri, @javax.annotation.Nullable OffsetDateTime before) throws ApiException {
        okhttp3.Call localVarCall = deleteDagDatasetQueuedEventValidateBeforeCall(dagId, uri, before, null);
        return localVarApiClient.execute(localVarCall);
    }

    /**
     * Delete a queued Dataset event for a DAG. (asynchronously)
     * Delete a queued Dataset event for a DAG.  *New in version 2.9.0* 
     * @param dagId The DAG ID. (required)
     * @param uri The encoded Dataset URI (required)
     * @param before Timestamp to select event logs occurring before. (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 204 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Client specified an invalid argument. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Client does not have sufficient permission. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> A specified resource is not found. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call deleteDagDatasetQueuedEventAsync(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String uri, @javax.annotation.Nullable OffsetDateTime before, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = deleteDagDatasetQueuedEventValidateBeforeCall(dagId, uri, before, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
    /**
     * Build call for deleteDagDatasetQueuedEvents
     * @param dagId The DAG ID. (required)
     * @param before Timestamp to select event logs occurring before. (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 204 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Client specified an invalid argument. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Client does not have sufficient permission. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> A specified resource is not found. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call deleteDagDatasetQueuedEventsCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nullable OffsetDateTime before, final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/dags/{dag_id}/datasets/queuedEvent"
            .replace("{" + "dag_id" + "}", localVarApiClient.escapeString(dagId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (before != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("before", before));
        }

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] {  };
        return localVarApiClient.buildCall(basePath, localVarPath, "DELETE", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call deleteDagDatasetQueuedEventsValidateBeforeCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nullable OffsetDateTime before, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'dagId' is set
        if (dagId == null) {
            throw new ApiException("Missing the required parameter 'dagId' when calling deleteDagDatasetQueuedEvents(Async)");
        }

        return deleteDagDatasetQueuedEventsCall(dagId, before, _callback);

    }

    /**
     * Delete queued Dataset events for a DAG.
     * Delete queued Dataset events for a DAG.  *New in version 2.9.0* 
     * @param dagId The DAG ID. (required)
     * @param before Timestamp to select event logs occurring before. (optional)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 204 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Client specified an invalid argument. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Client does not have sufficient permission. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> A specified resource is not found. </td><td>  -  </td></tr>
     </table>
     */
    public void deleteDagDatasetQueuedEvents(@javax.annotation.Nonnull String dagId, @javax.annotation.Nullable OffsetDateTime before) throws ApiException {
        deleteDagDatasetQueuedEventsWithHttpInfo(dagId, before);
    }

    /**
     * Delete queued Dataset events for a DAG.
     * Delete queued Dataset events for a DAG.  *New in version 2.9.0* 
     * @param dagId The DAG ID. (required)
     * @param before Timestamp to select event logs occurring before. (optional)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 204 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Client specified an invalid argument. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Client does not have sufficient permission. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> A specified resource is not found. </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Void> deleteDagDatasetQueuedEventsWithHttpInfo(@javax.annotation.Nonnull String dagId, @javax.annotation.Nullable OffsetDateTime before) throws ApiException {
        okhttp3.Call localVarCall = deleteDagDatasetQueuedEventsValidateBeforeCall(dagId, before, null);
        return localVarApiClient.execute(localVarCall);
    }

    /**
     * Delete queued Dataset events for a DAG. (asynchronously)
     * Delete queued Dataset events for a DAG.  *New in version 2.9.0* 
     * @param dagId The DAG ID. (required)
     * @param before Timestamp to select event logs occurring before. (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 204 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Client specified an invalid argument. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Client does not have sufficient permission. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> A specified resource is not found. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call deleteDagDatasetQueuedEventsAsync(@javax.annotation.Nonnull String dagId, @javax.annotation.Nullable OffsetDateTime before, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = deleteDagDatasetQueuedEventsValidateBeforeCall(dagId, before, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
    /**
     * Build call for deleteDatasetQueuedEvents
     * @param uri The encoded Dataset URI (required)
     * @param before Timestamp to select event logs occurring before. (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 204 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Client specified an invalid argument. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Client does not have sufficient permission. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> A specified resource is not found. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call deleteDatasetQueuedEventsCall(@javax.annotation.Nonnull String uri, @javax.annotation.Nullable OffsetDateTime before, final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/datasets/queuedEvent/{uri}"
            .replace("{" + "uri" + "}", localVarApiClient.escapeString(uri.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (before != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("before", before));
        }

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] {  };
        return localVarApiClient.buildCall(basePath, localVarPath, "DELETE", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call deleteDatasetQueuedEventsValidateBeforeCall(@javax.annotation.Nonnull String uri, @javax.annotation.Nullable OffsetDateTime before, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'uri' is set
        if (uri == null) {
            throw new ApiException("Missing the required parameter 'uri' when calling deleteDatasetQueuedEvents(Async)");
        }

        return deleteDatasetQueuedEventsCall(uri, before, _callback);

    }

    /**
     * Delete queued Dataset events for a Dataset.
     * Delete queued Dataset events for a Dataset.  *New in version 2.9.0* 
     * @param uri The encoded Dataset URI (required)
     * @param before Timestamp to select event logs occurring before. (optional)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 204 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Client specified an invalid argument. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Client does not have sufficient permission. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> A specified resource is not found. </td><td>  -  </td></tr>
     </table>
     */
    public void deleteDatasetQueuedEvents(@javax.annotation.Nonnull String uri, @javax.annotation.Nullable OffsetDateTime before) throws ApiException {
        deleteDatasetQueuedEventsWithHttpInfo(uri, before);
    }

    /**
     * Delete queued Dataset events for a Dataset.
     * Delete queued Dataset events for a Dataset.  *New in version 2.9.0* 
     * @param uri The encoded Dataset URI (required)
     * @param before Timestamp to select event logs occurring before. (optional)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 204 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Client specified an invalid argument. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Client does not have sufficient permission. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> A specified resource is not found. </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Void> deleteDatasetQueuedEventsWithHttpInfo(@javax.annotation.Nonnull String uri, @javax.annotation.Nullable OffsetDateTime before) throws ApiException {
        okhttp3.Call localVarCall = deleteDatasetQueuedEventsValidateBeforeCall(uri, before, null);
        return localVarApiClient.execute(localVarCall);
    }

    /**
     * Delete queued Dataset events for a Dataset. (asynchronously)
     * Delete queued Dataset events for a Dataset.  *New in version 2.9.0* 
     * @param uri The encoded Dataset URI (required)
     * @param before Timestamp to select event logs occurring before. (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 204 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Client specified an invalid argument. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Client does not have sufficient permission. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> A specified resource is not found. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call deleteDatasetQueuedEventsAsync(@javax.annotation.Nonnull String uri, @javax.annotation.Nullable OffsetDateTime before, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = deleteDatasetQueuedEventsValidateBeforeCall(uri, before, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
    /**
     * Build call for getDagDatasetQueuedEvent
     * @param dagId The DAG ID. (required)
     * @param uri The encoded Dataset URI (required)
     * @param before Timestamp to select event logs occurring before. (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Client does not have sufficient permission. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> A specified resource is not found. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getDagDatasetQueuedEventCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String uri, @javax.annotation.Nullable OffsetDateTime before, final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/dags/{dag_id}/datasets/queuedEvent/{uri}"
            .replace("{" + "dag_id" + "}", localVarApiClient.escapeString(dagId.toString()))
            .replace("{" + "uri" + "}", localVarApiClient.escapeString(uri.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (before != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("before", before));
        }

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] {  };
        return localVarApiClient.buildCall(basePath, localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call getDagDatasetQueuedEventValidateBeforeCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String uri, @javax.annotation.Nullable OffsetDateTime before, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'dagId' is set
        if (dagId == null) {
            throw new ApiException("Missing the required parameter 'dagId' when calling getDagDatasetQueuedEvent(Async)");
        }

        // verify the required parameter 'uri' is set
        if (uri == null) {
            throw new ApiException("Missing the required parameter 'uri' when calling getDagDatasetQueuedEvent(Async)");
        }

        return getDagDatasetQueuedEventCall(dagId, uri, before, _callback);

    }

    /**
     * Get a queued Dataset event for a DAG
     * Get a queued Dataset event for a DAG.  *New in version 2.9.0* 
     * @param dagId The DAG ID. (required)
     * @param uri The encoded Dataset URI (required)
     * @param before Timestamp to select event logs occurring before. (optional)
     * @return QueuedEvent
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Client does not have sufficient permission. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> A specified resource is not found. </td><td>  -  </td></tr>
     </table>
     */
    public QueuedEvent getDagDatasetQueuedEvent(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String uri, @javax.annotation.Nullable OffsetDateTime before) throws ApiException {
        ApiResponse<QueuedEvent> localVarResp = getDagDatasetQueuedEventWithHttpInfo(dagId, uri, before);
        return localVarResp.getData();
    }

    /**
     * Get a queued Dataset event for a DAG
     * Get a queued Dataset event for a DAG.  *New in version 2.9.0* 
     * @param dagId The DAG ID. (required)
     * @param uri The encoded Dataset URI (required)
     * @param before Timestamp to select event logs occurring before. (optional)
     * @return ApiResponse&lt;QueuedEvent&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Client does not have sufficient permission. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> A specified resource is not found. </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<QueuedEvent> getDagDatasetQueuedEventWithHttpInfo(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String uri, @javax.annotation.Nullable OffsetDateTime before) throws ApiException {
        okhttp3.Call localVarCall = getDagDatasetQueuedEventValidateBeforeCall(dagId, uri, before, null);
        Type localVarReturnType = new TypeToken<QueuedEvent>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Get a queued Dataset event for a DAG (asynchronously)
     * Get a queued Dataset event for a DAG.  *New in version 2.9.0* 
     * @param dagId The DAG ID. (required)
     * @param uri The encoded Dataset URI (required)
     * @param before Timestamp to select event logs occurring before. (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Client does not have sufficient permission. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> A specified resource is not found. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getDagDatasetQueuedEventAsync(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String uri, @javax.annotation.Nullable OffsetDateTime before, final ApiCallback<QueuedEvent> _callback) throws ApiException {

        okhttp3.Call localVarCall = getDagDatasetQueuedEventValidateBeforeCall(dagId, uri, before, _callback);
        Type localVarReturnType = new TypeToken<QueuedEvent>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getDagDatasetQueuedEvents
     * @param dagId The DAG ID. (required)
     * @param before Timestamp to select event logs occurring before. (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Client does not have sufficient permission. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> A specified resource is not found. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getDagDatasetQueuedEventsCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nullable OffsetDateTime before, final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/dags/{dag_id}/datasets/queuedEvent"
            .replace("{" + "dag_id" + "}", localVarApiClient.escapeString(dagId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (before != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("before", before));
        }

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] {  };
        return localVarApiClient.buildCall(basePath, localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call getDagDatasetQueuedEventsValidateBeforeCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nullable OffsetDateTime before, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'dagId' is set
        if (dagId == null) {
            throw new ApiException("Missing the required parameter 'dagId' when calling getDagDatasetQueuedEvents(Async)");
        }

        return getDagDatasetQueuedEventsCall(dagId, before, _callback);

    }

    /**
     * Get queued Dataset events for a DAG.
     * Get queued Dataset events for a DAG.  *New in version 2.9.0* 
     * @param dagId The DAG ID. (required)
     * @param before Timestamp to select event logs occurring before. (optional)
     * @return QueuedEventCollection
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Client does not have sufficient permission. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> A specified resource is not found. </td><td>  -  </td></tr>
     </table>
     */
    public QueuedEventCollection getDagDatasetQueuedEvents(@javax.annotation.Nonnull String dagId, @javax.annotation.Nullable OffsetDateTime before) throws ApiException {
        ApiResponse<QueuedEventCollection> localVarResp = getDagDatasetQueuedEventsWithHttpInfo(dagId, before);
        return localVarResp.getData();
    }

    /**
     * Get queued Dataset events for a DAG.
     * Get queued Dataset events for a DAG.  *New in version 2.9.0* 
     * @param dagId The DAG ID. (required)
     * @param before Timestamp to select event logs occurring before. (optional)
     * @return ApiResponse&lt;QueuedEventCollection&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Client does not have sufficient permission. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> A specified resource is not found. </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<QueuedEventCollection> getDagDatasetQueuedEventsWithHttpInfo(@javax.annotation.Nonnull String dagId, @javax.annotation.Nullable OffsetDateTime before) throws ApiException {
        okhttp3.Call localVarCall = getDagDatasetQueuedEventsValidateBeforeCall(dagId, before, null);
        Type localVarReturnType = new TypeToken<QueuedEventCollection>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Get queued Dataset events for a DAG. (asynchronously)
     * Get queued Dataset events for a DAG.  *New in version 2.9.0* 
     * @param dagId The DAG ID. (required)
     * @param before Timestamp to select event logs occurring before. (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Client does not have sufficient permission. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> A specified resource is not found. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getDagDatasetQueuedEventsAsync(@javax.annotation.Nonnull String dagId, @javax.annotation.Nullable OffsetDateTime before, final ApiCallback<QueuedEventCollection> _callback) throws ApiException {

        okhttp3.Call localVarCall = getDagDatasetQueuedEventsValidateBeforeCall(dagId, before, _callback);
        Type localVarReturnType = new TypeToken<QueuedEventCollection>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getDataset
     * @param uri The encoded Dataset URI (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Client does not have sufficient permission. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> A specified resource is not found. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getDatasetCall(@javax.annotation.Nonnull String uri, final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/datasets/{uri}"
            .replace("{" + "uri" + "}", localVarApiClient.escapeString(uri.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] {  };
        return localVarApiClient.buildCall(basePath, localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call getDatasetValidateBeforeCall(@javax.annotation.Nonnull String uri, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'uri' is set
        if (uri == null) {
            throw new ApiException("Missing the required parameter 'uri' when calling getDataset(Async)");
        }

        return getDatasetCall(uri, _callback);

    }

    /**
     * Get a dataset
     * Get a dataset by uri.
     * @param uri The encoded Dataset URI (required)
     * @return Dataset
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Client does not have sufficient permission. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> A specified resource is not found. </td><td>  -  </td></tr>
     </table>
     */
    public Dataset getDataset(@javax.annotation.Nonnull String uri) throws ApiException {
        ApiResponse<Dataset> localVarResp = getDatasetWithHttpInfo(uri);
        return localVarResp.getData();
    }

    /**
     * Get a dataset
     * Get a dataset by uri.
     * @param uri The encoded Dataset URI (required)
     * @return ApiResponse&lt;Dataset&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Client does not have sufficient permission. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> A specified resource is not found. </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Dataset> getDatasetWithHttpInfo(@javax.annotation.Nonnull String uri) throws ApiException {
        okhttp3.Call localVarCall = getDatasetValidateBeforeCall(uri, null);
        Type localVarReturnType = new TypeToken<Dataset>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Get a dataset (asynchronously)
     * Get a dataset by uri.
     * @param uri The encoded Dataset URI (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Client does not have sufficient permission. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> A specified resource is not found. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getDatasetAsync(@javax.annotation.Nonnull String uri, final ApiCallback<Dataset> _callback) throws ApiException {

        okhttp3.Call localVarCall = getDatasetValidateBeforeCall(uri, _callback);
        Type localVarReturnType = new TypeToken<Dataset>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getDatasetEvents
     * @param limit The numbers of items to return. (optional, default to 100)
     * @param offset The number of items to skip before starting to collect the result set. (optional)
     * @param orderBy The name of the field to order the results by. Prefix a field name with &#x60;-&#x60; to reverse the sort order.  *New in version 2.1.0*  (optional)
     * @param datasetId The Dataset ID that updated the dataset. (optional)
     * @param sourceDagId The DAG ID that updated the dataset. (optional)
     * @param sourceTaskId The task ID that updated the dataset. (optional)
     * @param sourceRunId The DAG run ID that updated the dataset. (optional)
     * @param sourceMapIndex The map index that updated the dataset. (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Client does not have sufficient permission. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> A specified resource is not found. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getDatasetEventsCall(@javax.annotation.Nullable Integer limit, @javax.annotation.Nullable Integer offset, @javax.annotation.Nullable String orderBy, @javax.annotation.Nullable Integer datasetId, @javax.annotation.Nullable String sourceDagId, @javax.annotation.Nullable String sourceTaskId, @javax.annotation.Nullable String sourceRunId, @javax.annotation.Nullable Integer sourceMapIndex, final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/datasets/events";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (limit != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("limit", limit));
        }

        if (offset != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("offset", offset));
        }

        if (orderBy != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("order_by", orderBy));
        }

        if (datasetId != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("dataset_id", datasetId));
        }

        if (sourceDagId != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("source_dag_id", sourceDagId));
        }

        if (sourceTaskId != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("source_task_id", sourceTaskId));
        }

        if (sourceRunId != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("source_run_id", sourceRunId));
        }

        if (sourceMapIndex != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("source_map_index", sourceMapIndex));
        }

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] {  };
        return localVarApiClient.buildCall(basePath, localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call getDatasetEventsValidateBeforeCall(@javax.annotation.Nullable Integer limit, @javax.annotation.Nullable Integer offset, @javax.annotation.Nullable String orderBy, @javax.annotation.Nullable Integer datasetId, @javax.annotation.Nullable String sourceDagId, @javax.annotation.Nullable String sourceTaskId, @javax.annotation.Nullable String sourceRunId, @javax.annotation.Nullable Integer sourceMapIndex, final ApiCallback _callback) throws ApiException {
        return getDatasetEventsCall(limit, offset, orderBy, datasetId, sourceDagId, sourceTaskId, sourceRunId, sourceMapIndex, _callback);

    }

    /**
     * Get dataset events
     * Get dataset events
     * @param limit The numbers of items to return. (optional, default to 100)
     * @param offset The number of items to skip before starting to collect the result set. (optional)
     * @param orderBy The name of the field to order the results by. Prefix a field name with &#x60;-&#x60; to reverse the sort order.  *New in version 2.1.0*  (optional)
     * @param datasetId The Dataset ID that updated the dataset. (optional)
     * @param sourceDagId The DAG ID that updated the dataset. (optional)
     * @param sourceTaskId The task ID that updated the dataset. (optional)
     * @param sourceRunId The DAG run ID that updated the dataset. (optional)
     * @param sourceMapIndex The map index that updated the dataset. (optional)
     * @return DatasetEventCollection
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Client does not have sufficient permission. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> A specified resource is not found. </td><td>  -  </td></tr>
     </table>
     */
    public DatasetEventCollection getDatasetEvents(@javax.annotation.Nullable Integer limit, @javax.annotation.Nullable Integer offset, @javax.annotation.Nullable String orderBy, @javax.annotation.Nullable Integer datasetId, @javax.annotation.Nullable String sourceDagId, @javax.annotation.Nullable String sourceTaskId, @javax.annotation.Nullable String sourceRunId, @javax.annotation.Nullable Integer sourceMapIndex) throws ApiException {
        ApiResponse<DatasetEventCollection> localVarResp = getDatasetEventsWithHttpInfo(limit, offset, orderBy, datasetId, sourceDagId, sourceTaskId, sourceRunId, sourceMapIndex);
        return localVarResp.getData();
    }

    /**
     * Get dataset events
     * Get dataset events
     * @param limit The numbers of items to return. (optional, default to 100)
     * @param offset The number of items to skip before starting to collect the result set. (optional)
     * @param orderBy The name of the field to order the results by. Prefix a field name with &#x60;-&#x60; to reverse the sort order.  *New in version 2.1.0*  (optional)
     * @param datasetId The Dataset ID that updated the dataset. (optional)
     * @param sourceDagId The DAG ID that updated the dataset. (optional)
     * @param sourceTaskId The task ID that updated the dataset. (optional)
     * @param sourceRunId The DAG run ID that updated the dataset. (optional)
     * @param sourceMapIndex The map index that updated the dataset. (optional)
     * @return ApiResponse&lt;DatasetEventCollection&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Client does not have sufficient permission. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> A specified resource is not found. </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<DatasetEventCollection> getDatasetEventsWithHttpInfo(@javax.annotation.Nullable Integer limit, @javax.annotation.Nullable Integer offset, @javax.annotation.Nullable String orderBy, @javax.annotation.Nullable Integer datasetId, @javax.annotation.Nullable String sourceDagId, @javax.annotation.Nullable String sourceTaskId, @javax.annotation.Nullable String sourceRunId, @javax.annotation.Nullable Integer sourceMapIndex) throws ApiException {
        okhttp3.Call localVarCall = getDatasetEventsValidateBeforeCall(limit, offset, orderBy, datasetId, sourceDagId, sourceTaskId, sourceRunId, sourceMapIndex, null);
        Type localVarReturnType = new TypeToken<DatasetEventCollection>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Get dataset events (asynchronously)
     * Get dataset events
     * @param limit The numbers of items to return. (optional, default to 100)
     * @param offset The number of items to skip before starting to collect the result set. (optional)
     * @param orderBy The name of the field to order the results by. Prefix a field name with &#x60;-&#x60; to reverse the sort order.  *New in version 2.1.0*  (optional)
     * @param datasetId The Dataset ID that updated the dataset. (optional)
     * @param sourceDagId The DAG ID that updated the dataset. (optional)
     * @param sourceTaskId The task ID that updated the dataset. (optional)
     * @param sourceRunId The DAG run ID that updated the dataset. (optional)
     * @param sourceMapIndex The map index that updated the dataset. (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Client does not have sufficient permission. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> A specified resource is not found. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getDatasetEventsAsync(@javax.annotation.Nullable Integer limit, @javax.annotation.Nullable Integer offset, @javax.annotation.Nullable String orderBy, @javax.annotation.Nullable Integer datasetId, @javax.annotation.Nullable String sourceDagId, @javax.annotation.Nullable String sourceTaskId, @javax.annotation.Nullable String sourceRunId, @javax.annotation.Nullable Integer sourceMapIndex, final ApiCallback<DatasetEventCollection> _callback) throws ApiException {

        okhttp3.Call localVarCall = getDatasetEventsValidateBeforeCall(limit, offset, orderBy, datasetId, sourceDagId, sourceTaskId, sourceRunId, sourceMapIndex, _callback);
        Type localVarReturnType = new TypeToken<DatasetEventCollection>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getDatasetQueuedEvents
     * @param uri The encoded Dataset URI (required)
     * @param before Timestamp to select event logs occurring before. (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Client does not have sufficient permission. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> A specified resource is not found. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getDatasetQueuedEventsCall(@javax.annotation.Nonnull String uri, @javax.annotation.Nullable OffsetDateTime before, final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/datasets/queuedEvent/{uri}"
            .replace("{" + "uri" + "}", localVarApiClient.escapeString(uri.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (before != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("before", before));
        }

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] {  };
        return localVarApiClient.buildCall(basePath, localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call getDatasetQueuedEventsValidateBeforeCall(@javax.annotation.Nonnull String uri, @javax.annotation.Nullable OffsetDateTime before, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'uri' is set
        if (uri == null) {
            throw new ApiException("Missing the required parameter 'uri' when calling getDatasetQueuedEvents(Async)");
        }

        return getDatasetQueuedEventsCall(uri, before, _callback);

    }

    /**
     * Get queued Dataset events for a Dataset.
     * Get queued Dataset events for a Dataset  *New in version 2.9.0* 
     * @param uri The encoded Dataset URI (required)
     * @param before Timestamp to select event logs occurring before. (optional)
     * @return QueuedEventCollection
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Client does not have sufficient permission. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> A specified resource is not found. </td><td>  -  </td></tr>
     </table>
     */
    public QueuedEventCollection getDatasetQueuedEvents(@javax.annotation.Nonnull String uri, @javax.annotation.Nullable OffsetDateTime before) throws ApiException {
        ApiResponse<QueuedEventCollection> localVarResp = getDatasetQueuedEventsWithHttpInfo(uri, before);
        return localVarResp.getData();
    }

    /**
     * Get queued Dataset events for a Dataset.
     * Get queued Dataset events for a Dataset  *New in version 2.9.0* 
     * @param uri The encoded Dataset URI (required)
     * @param before Timestamp to select event logs occurring before. (optional)
     * @return ApiResponse&lt;QueuedEventCollection&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Client does not have sufficient permission. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> A specified resource is not found. </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<QueuedEventCollection> getDatasetQueuedEventsWithHttpInfo(@javax.annotation.Nonnull String uri, @javax.annotation.Nullable OffsetDateTime before) throws ApiException {
        okhttp3.Call localVarCall = getDatasetQueuedEventsValidateBeforeCall(uri, before, null);
        Type localVarReturnType = new TypeToken<QueuedEventCollection>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Get queued Dataset events for a Dataset. (asynchronously)
     * Get queued Dataset events for a Dataset  *New in version 2.9.0* 
     * @param uri The encoded Dataset URI (required)
     * @param before Timestamp to select event logs occurring before. (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Client does not have sufficient permission. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> A specified resource is not found. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getDatasetQueuedEventsAsync(@javax.annotation.Nonnull String uri, @javax.annotation.Nullable OffsetDateTime before, final ApiCallback<QueuedEventCollection> _callback) throws ApiException {

        okhttp3.Call localVarCall = getDatasetQueuedEventsValidateBeforeCall(uri, before, _callback);
        Type localVarReturnType = new TypeToken<QueuedEventCollection>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getDatasets
     * @param limit The numbers of items to return. (optional, default to 100)
     * @param offset The number of items to skip before starting to collect the result set. (optional)
     * @param orderBy The name of the field to order the results by. Prefix a field name with &#x60;-&#x60; to reverse the sort order.  *New in version 2.1.0*  (optional)
     * @param uriPattern If set, only return datasets with uris matching this pattern.  (optional)
     * @param dagIds One or more DAG IDs separated by commas to filter datasets by associated DAGs either consuming or producing.  *New in version 2.9.0*  (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Client does not have sufficient permission. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getDatasetsCall(@javax.annotation.Nullable Integer limit, @javax.annotation.Nullable Integer offset, @javax.annotation.Nullable String orderBy, @javax.annotation.Nullable String uriPattern, @javax.annotation.Nullable String dagIds, final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/datasets";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (limit != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("limit", limit));
        }

        if (offset != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("offset", offset));
        }

        if (orderBy != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("order_by", orderBy));
        }

        if (uriPattern != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("uri_pattern", uriPattern));
        }

        if (dagIds != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("dag_ids", dagIds));
        }

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] {  };
        return localVarApiClient.buildCall(basePath, localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call getDatasetsValidateBeforeCall(@javax.annotation.Nullable Integer limit, @javax.annotation.Nullable Integer offset, @javax.annotation.Nullable String orderBy, @javax.annotation.Nullable String uriPattern, @javax.annotation.Nullable String dagIds, final ApiCallback _callback) throws ApiException {
        return getDatasetsCall(limit, offset, orderBy, uriPattern, dagIds, _callback);

    }

    /**
     * List datasets
     * 
     * @param limit The numbers of items to return. (optional, default to 100)
     * @param offset The number of items to skip before starting to collect the result set. (optional)
     * @param orderBy The name of the field to order the results by. Prefix a field name with &#x60;-&#x60; to reverse the sort order.  *New in version 2.1.0*  (optional)
     * @param uriPattern If set, only return datasets with uris matching this pattern.  (optional)
     * @param dagIds One or more DAG IDs separated by commas to filter datasets by associated DAGs either consuming or producing.  *New in version 2.9.0*  (optional)
     * @return DatasetCollection
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Client does not have sufficient permission. </td><td>  -  </td></tr>
     </table>
     */
    public DatasetCollection getDatasets(@javax.annotation.Nullable Integer limit, @javax.annotation.Nullable Integer offset, @javax.annotation.Nullable String orderBy, @javax.annotation.Nullable String uriPattern, @javax.annotation.Nullable String dagIds) throws ApiException {
        ApiResponse<DatasetCollection> localVarResp = getDatasetsWithHttpInfo(limit, offset, orderBy, uriPattern, dagIds);
        return localVarResp.getData();
    }

    /**
     * List datasets
     * 
     * @param limit The numbers of items to return. (optional, default to 100)
     * @param offset The number of items to skip before starting to collect the result set. (optional)
     * @param orderBy The name of the field to order the results by. Prefix a field name with &#x60;-&#x60; to reverse the sort order.  *New in version 2.1.0*  (optional)
     * @param uriPattern If set, only return datasets with uris matching this pattern.  (optional)
     * @param dagIds One or more DAG IDs separated by commas to filter datasets by associated DAGs either consuming or producing.  *New in version 2.9.0*  (optional)
     * @return ApiResponse&lt;DatasetCollection&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Client does not have sufficient permission. </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<DatasetCollection> getDatasetsWithHttpInfo(@javax.annotation.Nullable Integer limit, @javax.annotation.Nullable Integer offset, @javax.annotation.Nullable String orderBy, @javax.annotation.Nullable String uriPattern, @javax.annotation.Nullable String dagIds) throws ApiException {
        okhttp3.Call localVarCall = getDatasetsValidateBeforeCall(limit, offset, orderBy, uriPattern, dagIds, null);
        Type localVarReturnType = new TypeToken<DatasetCollection>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * List datasets (asynchronously)
     * 
     * @param limit The numbers of items to return. (optional, default to 100)
     * @param offset The number of items to skip before starting to collect the result set. (optional)
     * @param orderBy The name of the field to order the results by. Prefix a field name with &#x60;-&#x60; to reverse the sort order.  *New in version 2.1.0*  (optional)
     * @param uriPattern If set, only return datasets with uris matching this pattern.  (optional)
     * @param dagIds One or more DAG IDs separated by commas to filter datasets by associated DAGs either consuming or producing.  *New in version 2.9.0*  (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Client does not have sufficient permission. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getDatasetsAsync(@javax.annotation.Nullable Integer limit, @javax.annotation.Nullable Integer offset, @javax.annotation.Nullable String orderBy, @javax.annotation.Nullable String uriPattern, @javax.annotation.Nullable String dagIds, final ApiCallback<DatasetCollection> _callback) throws ApiException {

        okhttp3.Call localVarCall = getDatasetsValidateBeforeCall(limit, offset, orderBy, uriPattern, dagIds, _callback);
        Type localVarReturnType = new TypeToken<DatasetCollection>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getUpstreamDatasetEvents
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Client does not have sufficient permission. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> A specified resource is not found. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getUpstreamDatasetEventsCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/dags/{dag_id}/dagRuns/{dag_run_id}/upstreamDatasetEvents"
            .replace("{" + "dag_id" + "}", localVarApiClient.escapeString(dagId.toString()))
            .replace("{" + "dag_run_id" + "}", localVarApiClient.escapeString(dagRunId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] {  };
        return localVarApiClient.buildCall(basePath, localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call getUpstreamDatasetEventsValidateBeforeCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'dagId' is set
        if (dagId == null) {
            throw new ApiException("Missing the required parameter 'dagId' when calling getUpstreamDatasetEvents(Async)");
        }

        // verify the required parameter 'dagRunId' is set
        if (dagRunId == null) {
            throw new ApiException("Missing the required parameter 'dagRunId' when calling getUpstreamDatasetEvents(Async)");
        }

        return getUpstreamDatasetEventsCall(dagId, dagRunId, _callback);

    }

    /**
     * Get dataset events for a DAG run
     * Get datasets for a dag run.  *New in version 2.4.0* 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @return DatasetEventCollection
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Client does not have sufficient permission. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> A specified resource is not found. </td><td>  -  </td></tr>
     </table>
     */
    public DatasetEventCollection getUpstreamDatasetEvents(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId) throws ApiException {
        ApiResponse<DatasetEventCollection> localVarResp = getUpstreamDatasetEventsWithHttpInfo(dagId, dagRunId);
        return localVarResp.getData();
    }

    /**
     * Get dataset events for a DAG run
     * Get datasets for a dag run.  *New in version 2.4.0* 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @return ApiResponse&lt;DatasetEventCollection&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Client does not have sufficient permission. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> A specified resource is not found. </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<DatasetEventCollection> getUpstreamDatasetEventsWithHttpInfo(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId) throws ApiException {
        okhttp3.Call localVarCall = getUpstreamDatasetEventsValidateBeforeCall(dagId, dagRunId, null);
        Type localVarReturnType = new TypeToken<DatasetEventCollection>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Get dataset events for a DAG run (asynchronously)
     * Get datasets for a dag run.  *New in version 2.4.0* 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Client does not have sufficient permission. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> A specified resource is not found. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getUpstreamDatasetEventsAsync(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, final ApiCallback<DatasetEventCollection> _callback) throws ApiException {

        okhttp3.Call localVarCall = getUpstreamDatasetEventsValidateBeforeCall(dagId, dagRunId, _callback);
        Type localVarReturnType = new TypeToken<DatasetEventCollection>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}
