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


import org.openapitools.client.model.ClearDagRun;
import org.openapitools.client.model.ClearDagRun200Response;
import org.openapitools.client.model.DAGRun;
import org.openapitools.client.model.DAGRunCollection;
import org.openapitools.client.model.DatasetEventCollection;
import org.openapitools.client.model.Error;
import org.openapitools.client.model.ListDagRunsForm;
import java.time.OffsetDateTime;
import org.openapitools.client.model.SetDagRunNote;
import org.openapitools.client.model.UpdateDagRunState;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DagRunApi {
    private ApiClient localVarApiClient;
    private int localHostIndex;
    private String localCustomBaseUrl;

    public DagRunApi() {
        this(Configuration.getDefaultApiClient());
    }

    public DagRunApi(ApiClient apiClient) {
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
     * Build call for clearDagRun
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param clearDagRun  (required)
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
    public okhttp3.Call clearDagRunCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull ClearDagRun clearDagRun, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = clearDagRun;

        // create path and map variables
        String localVarPath = "/dags/{dag_id}/dagRuns/{dag_run_id}/clear"
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
    private okhttp3.Call clearDagRunValidateBeforeCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull ClearDagRun clearDagRun, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'dagId' is set
        if (dagId == null) {
            throw new ApiException("Missing the required parameter 'dagId' when calling clearDagRun(Async)");
        }

        // verify the required parameter 'dagRunId' is set
        if (dagRunId == null) {
            throw new ApiException("Missing the required parameter 'dagRunId' when calling clearDagRun(Async)");
        }

        // verify the required parameter 'clearDagRun' is set
        if (clearDagRun == null) {
            throw new ApiException("Missing the required parameter 'clearDagRun' when calling clearDagRun(Async)");
        }

        return clearDagRunCall(dagId, dagRunId, clearDagRun, _callback);

    }

    /**
     * Clear a DAG run
     * Clear a DAG run.  *New in version 2.4.0* 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param clearDagRun  (required)
     * @return ClearDagRun200Response
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
    public ClearDagRun200Response clearDagRun(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull ClearDagRun clearDagRun) throws ApiException {
        ApiResponse<ClearDagRun200Response> localVarResp = clearDagRunWithHttpInfo(dagId, dagRunId, clearDagRun);
        return localVarResp.getData();
    }

    /**
     * Clear a DAG run
     * Clear a DAG run.  *New in version 2.4.0* 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param clearDagRun  (required)
     * @return ApiResponse&lt;ClearDagRun200Response&gt;
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
    public ApiResponse<ClearDagRun200Response> clearDagRunWithHttpInfo(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull ClearDagRun clearDagRun) throws ApiException {
        okhttp3.Call localVarCall = clearDagRunValidateBeforeCall(dagId, dagRunId, clearDagRun, null);
        Type localVarReturnType = new TypeToken<ClearDagRun200Response>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Clear a DAG run (asynchronously)
     * Clear a DAG run.  *New in version 2.4.0* 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param clearDagRun  (required)
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
    public okhttp3.Call clearDagRunAsync(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull ClearDagRun clearDagRun, final ApiCallback<ClearDagRun200Response> _callback) throws ApiException {

        okhttp3.Call localVarCall = clearDagRunValidateBeforeCall(dagId, dagRunId, clearDagRun, _callback);
        Type localVarReturnType = new TypeToken<ClearDagRun200Response>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for deleteDagRun
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
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
    public okhttp3.Call deleteDagRunCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/dags/{dag_id}/dagRuns/{dag_run_id}"
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
        return localVarApiClient.buildCall(basePath, localVarPath, "DELETE", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call deleteDagRunValidateBeforeCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'dagId' is set
        if (dagId == null) {
            throw new ApiException("Missing the required parameter 'dagId' when calling deleteDagRun(Async)");
        }

        // verify the required parameter 'dagRunId' is set
        if (dagRunId == null) {
            throw new ApiException("Missing the required parameter 'dagRunId' when calling deleteDagRun(Async)");
        }

        return deleteDagRunCall(dagId, dagRunId, _callback);

    }

    /**
     * Delete a DAG run
     * 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
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
    public void deleteDagRun(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId) throws ApiException {
        deleteDagRunWithHttpInfo(dagId, dagRunId);
    }

    /**
     * Delete a DAG run
     * 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
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
    public ApiResponse<Void> deleteDagRunWithHttpInfo(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId) throws ApiException {
        okhttp3.Call localVarCall = deleteDagRunValidateBeforeCall(dagId, dagRunId, null);
        return localVarApiClient.execute(localVarCall);
    }

    /**
     * Delete a DAG run (asynchronously)
     * 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
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
    public okhttp3.Call deleteDagRunAsync(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = deleteDagRunValidateBeforeCall(dagId, dagRunId, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
    /**
     * Build call for getDagRun
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param fields List of field for return.  (optional)
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
    public okhttp3.Call getDagRunCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nullable List<String> fields, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/dags/{dag_id}/dagRuns/{dag_run_id}"
            .replace("{" + "dag_id" + "}", localVarApiClient.escapeString(dagId.toString()))
            .replace("{" + "dag_run_id" + "}", localVarApiClient.escapeString(dagRunId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (fields != null) {
            localVarCollectionQueryParams.addAll(localVarApiClient.parameterToPairs("multi", "fields", fields));
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
    private okhttp3.Call getDagRunValidateBeforeCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nullable List<String> fields, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'dagId' is set
        if (dagId == null) {
            throw new ApiException("Missing the required parameter 'dagId' when calling getDagRun(Async)");
        }

        // verify the required parameter 'dagRunId' is set
        if (dagRunId == null) {
            throw new ApiException("Missing the required parameter 'dagRunId' when calling getDagRun(Async)");
        }

        return getDagRunCall(dagId, dagRunId, fields, _callback);

    }

    /**
     * Get a DAG run
     * 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param fields List of field for return.  (optional)
     * @return DAGRun
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
    public DAGRun getDagRun(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nullable List<String> fields) throws ApiException {
        ApiResponse<DAGRun> localVarResp = getDagRunWithHttpInfo(dagId, dagRunId, fields);
        return localVarResp.getData();
    }

    /**
     * Get a DAG run
     * 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param fields List of field for return.  (optional)
     * @return ApiResponse&lt;DAGRun&gt;
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
    public ApiResponse<DAGRun> getDagRunWithHttpInfo(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nullable List<String> fields) throws ApiException {
        okhttp3.Call localVarCall = getDagRunValidateBeforeCall(dagId, dagRunId, fields, null);
        Type localVarReturnType = new TypeToken<DAGRun>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Get a DAG run (asynchronously)
     * 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param fields List of field for return.  (optional)
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
    public okhttp3.Call getDagRunAsync(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nullable List<String> fields, final ApiCallback<DAGRun> _callback) throws ApiException {

        okhttp3.Call localVarCall = getDagRunValidateBeforeCall(dagId, dagRunId, fields, _callback);
        Type localVarReturnType = new TypeToken<DAGRun>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getDagRuns
     * @param dagId The DAG ID. (required)
     * @param limit The numbers of items to return. (optional, default to 100)
     * @param offset The number of items to skip before starting to collect the result set. (optional)
     * @param executionDateGte Returns objects greater or equal to the specified date.  This can be combined with execution_date_lte parameter to receive only the selected period.  (optional)
     * @param executionDateLte Returns objects less than or equal to the specified date.  This can be combined with execution_date_gte parameter to receive only the selected period.  (optional)
     * @param startDateGte Returns objects greater or equal the specified date.  This can be combined with start_date_lte parameter to receive only the selected period.  (optional)
     * @param startDateLte Returns objects less or equal the specified date.  This can be combined with start_date_gte parameter to receive only the selected period.  (optional)
     * @param endDateGte Returns objects greater or equal the specified date.  This can be combined with start_date_lte parameter to receive only the selected period.  (optional)
     * @param endDateLte Returns objects less than or equal to the specified date.  This can be combined with start_date_gte parameter to receive only the selected period.  (optional)
     * @param updatedAtGte Returns objects greater or equal the specified date.  This can be combined with updated_at_lte parameter to receive only the selected period.  *New in version 2.6.0*  (optional)
     * @param updatedAtLte Returns objects less or equal the specified date.  This can be combined with updated_at_gte parameter to receive only the selected period.  *New in version 2.6.0*  (optional)
     * @param state The value can be repeated to retrieve multiple matching values (OR condition). (optional)
     * @param orderBy The name of the field to order the results by. Prefix a field name with &#x60;-&#x60; to reverse the sort order.  *New in version 2.1.0*  (optional)
     * @param fields List of field for return.  (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> List of DAG runs. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getDagRunsCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nullable Integer limit, @javax.annotation.Nullable Integer offset, @javax.annotation.Nullable OffsetDateTime executionDateGte, @javax.annotation.Nullable OffsetDateTime executionDateLte, @javax.annotation.Nullable OffsetDateTime startDateGte, @javax.annotation.Nullable OffsetDateTime startDateLte, @javax.annotation.Nullable OffsetDateTime endDateGte, @javax.annotation.Nullable OffsetDateTime endDateLte, @javax.annotation.Nullable OffsetDateTime updatedAtGte, @javax.annotation.Nullable OffsetDateTime updatedAtLte, @javax.annotation.Nullable List<String> state, @javax.annotation.Nullable String orderBy, @javax.annotation.Nullable List<String> fields, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/dags/{dag_id}/dagRuns"
            .replace("{" + "dag_id" + "}", localVarApiClient.escapeString(dagId.toString()));

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

        if (executionDateGte != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("execution_date_gte", executionDateGte));
        }

        if (executionDateLte != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("execution_date_lte", executionDateLte));
        }

        if (startDateGte != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("start_date_gte", startDateGte));
        }

        if (startDateLte != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("start_date_lte", startDateLte));
        }

        if (endDateGte != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("end_date_gte", endDateGte));
        }

        if (endDateLte != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("end_date_lte", endDateLte));
        }

        if (updatedAtGte != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("updated_at_gte", updatedAtGte));
        }

        if (updatedAtLte != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("updated_at_lte", updatedAtLte));
        }

        if (state != null) {
            localVarCollectionQueryParams.addAll(localVarApiClient.parameterToPairs("multi", "state", state));
        }

        if (orderBy != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("order_by", orderBy));
        }

        if (fields != null) {
            localVarCollectionQueryParams.addAll(localVarApiClient.parameterToPairs("multi", "fields", fields));
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
    private okhttp3.Call getDagRunsValidateBeforeCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nullable Integer limit, @javax.annotation.Nullable Integer offset, @javax.annotation.Nullable OffsetDateTime executionDateGte, @javax.annotation.Nullable OffsetDateTime executionDateLte, @javax.annotation.Nullable OffsetDateTime startDateGte, @javax.annotation.Nullable OffsetDateTime startDateLte, @javax.annotation.Nullable OffsetDateTime endDateGte, @javax.annotation.Nullable OffsetDateTime endDateLte, @javax.annotation.Nullable OffsetDateTime updatedAtGte, @javax.annotation.Nullable OffsetDateTime updatedAtLte, @javax.annotation.Nullable List<String> state, @javax.annotation.Nullable String orderBy, @javax.annotation.Nullable List<String> fields, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'dagId' is set
        if (dagId == null) {
            throw new ApiException("Missing the required parameter 'dagId' when calling getDagRuns(Async)");
        }

        return getDagRunsCall(dagId, limit, offset, executionDateGte, executionDateLte, startDateGte, startDateLte, endDateGte, endDateLte, updatedAtGte, updatedAtLte, state, orderBy, fields, _callback);

    }

    /**
     * List DAG runs
     * This endpoint allows specifying &#x60;~&#x60; as the dag_id to retrieve DAG runs for all DAGs. 
     * @param dagId The DAG ID. (required)
     * @param limit The numbers of items to return. (optional, default to 100)
     * @param offset The number of items to skip before starting to collect the result set. (optional)
     * @param executionDateGte Returns objects greater or equal to the specified date.  This can be combined with execution_date_lte parameter to receive only the selected period.  (optional)
     * @param executionDateLte Returns objects less than or equal to the specified date.  This can be combined with execution_date_gte parameter to receive only the selected period.  (optional)
     * @param startDateGte Returns objects greater or equal the specified date.  This can be combined with start_date_lte parameter to receive only the selected period.  (optional)
     * @param startDateLte Returns objects less or equal the specified date.  This can be combined with start_date_gte parameter to receive only the selected period.  (optional)
     * @param endDateGte Returns objects greater or equal the specified date.  This can be combined with start_date_lte parameter to receive only the selected period.  (optional)
     * @param endDateLte Returns objects less than or equal to the specified date.  This can be combined with start_date_gte parameter to receive only the selected period.  (optional)
     * @param updatedAtGte Returns objects greater or equal the specified date.  This can be combined with updated_at_lte parameter to receive only the selected period.  *New in version 2.6.0*  (optional)
     * @param updatedAtLte Returns objects less or equal the specified date.  This can be combined with updated_at_gte parameter to receive only the selected period.  *New in version 2.6.0*  (optional)
     * @param state The value can be repeated to retrieve multiple matching values (OR condition). (optional)
     * @param orderBy The name of the field to order the results by. Prefix a field name with &#x60;-&#x60; to reverse the sort order.  *New in version 2.1.0*  (optional)
     * @param fields List of field for return.  (optional)
     * @return DAGRunCollection
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> List of DAG runs. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
     </table>
     */
    public DAGRunCollection getDagRuns(@javax.annotation.Nonnull String dagId, @javax.annotation.Nullable Integer limit, @javax.annotation.Nullable Integer offset, @javax.annotation.Nullable OffsetDateTime executionDateGte, @javax.annotation.Nullable OffsetDateTime executionDateLte, @javax.annotation.Nullable OffsetDateTime startDateGte, @javax.annotation.Nullable OffsetDateTime startDateLte, @javax.annotation.Nullable OffsetDateTime endDateGte, @javax.annotation.Nullable OffsetDateTime endDateLte, @javax.annotation.Nullable OffsetDateTime updatedAtGte, @javax.annotation.Nullable OffsetDateTime updatedAtLte, @javax.annotation.Nullable List<String> state, @javax.annotation.Nullable String orderBy, @javax.annotation.Nullable List<String> fields) throws ApiException {
        ApiResponse<DAGRunCollection> localVarResp = getDagRunsWithHttpInfo(dagId, limit, offset, executionDateGte, executionDateLte, startDateGte, startDateLte, endDateGte, endDateLte, updatedAtGte, updatedAtLte, state, orderBy, fields);
        return localVarResp.getData();
    }

    /**
     * List DAG runs
     * This endpoint allows specifying &#x60;~&#x60; as the dag_id to retrieve DAG runs for all DAGs. 
     * @param dagId The DAG ID. (required)
     * @param limit The numbers of items to return. (optional, default to 100)
     * @param offset The number of items to skip before starting to collect the result set. (optional)
     * @param executionDateGte Returns objects greater or equal to the specified date.  This can be combined with execution_date_lte parameter to receive only the selected period.  (optional)
     * @param executionDateLte Returns objects less than or equal to the specified date.  This can be combined with execution_date_gte parameter to receive only the selected period.  (optional)
     * @param startDateGte Returns objects greater or equal the specified date.  This can be combined with start_date_lte parameter to receive only the selected period.  (optional)
     * @param startDateLte Returns objects less or equal the specified date.  This can be combined with start_date_gte parameter to receive only the selected period.  (optional)
     * @param endDateGte Returns objects greater or equal the specified date.  This can be combined with start_date_lte parameter to receive only the selected period.  (optional)
     * @param endDateLte Returns objects less than or equal to the specified date.  This can be combined with start_date_gte parameter to receive only the selected period.  (optional)
     * @param updatedAtGte Returns objects greater or equal the specified date.  This can be combined with updated_at_lte parameter to receive only the selected period.  *New in version 2.6.0*  (optional)
     * @param updatedAtLte Returns objects less or equal the specified date.  This can be combined with updated_at_gte parameter to receive only the selected period.  *New in version 2.6.0*  (optional)
     * @param state The value can be repeated to retrieve multiple matching values (OR condition). (optional)
     * @param orderBy The name of the field to order the results by. Prefix a field name with &#x60;-&#x60; to reverse the sort order.  *New in version 2.1.0*  (optional)
     * @param fields List of field for return.  (optional)
     * @return ApiResponse&lt;DAGRunCollection&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> List of DAG runs. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<DAGRunCollection> getDagRunsWithHttpInfo(@javax.annotation.Nonnull String dagId, @javax.annotation.Nullable Integer limit, @javax.annotation.Nullable Integer offset, @javax.annotation.Nullable OffsetDateTime executionDateGte, @javax.annotation.Nullable OffsetDateTime executionDateLte, @javax.annotation.Nullable OffsetDateTime startDateGte, @javax.annotation.Nullable OffsetDateTime startDateLte, @javax.annotation.Nullable OffsetDateTime endDateGte, @javax.annotation.Nullable OffsetDateTime endDateLte, @javax.annotation.Nullable OffsetDateTime updatedAtGte, @javax.annotation.Nullable OffsetDateTime updatedAtLte, @javax.annotation.Nullable List<String> state, @javax.annotation.Nullable String orderBy, @javax.annotation.Nullable List<String> fields) throws ApiException {
        okhttp3.Call localVarCall = getDagRunsValidateBeforeCall(dagId, limit, offset, executionDateGte, executionDateLte, startDateGte, startDateLte, endDateGte, endDateLte, updatedAtGte, updatedAtLte, state, orderBy, fields, null);
        Type localVarReturnType = new TypeToken<DAGRunCollection>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * List DAG runs (asynchronously)
     * This endpoint allows specifying &#x60;~&#x60; as the dag_id to retrieve DAG runs for all DAGs. 
     * @param dagId The DAG ID. (required)
     * @param limit The numbers of items to return. (optional, default to 100)
     * @param offset The number of items to skip before starting to collect the result set. (optional)
     * @param executionDateGte Returns objects greater or equal to the specified date.  This can be combined with execution_date_lte parameter to receive only the selected period.  (optional)
     * @param executionDateLte Returns objects less than or equal to the specified date.  This can be combined with execution_date_gte parameter to receive only the selected period.  (optional)
     * @param startDateGte Returns objects greater or equal the specified date.  This can be combined with start_date_lte parameter to receive only the selected period.  (optional)
     * @param startDateLte Returns objects less or equal the specified date.  This can be combined with start_date_gte parameter to receive only the selected period.  (optional)
     * @param endDateGte Returns objects greater or equal the specified date.  This can be combined with start_date_lte parameter to receive only the selected period.  (optional)
     * @param endDateLte Returns objects less than or equal to the specified date.  This can be combined with start_date_gte parameter to receive only the selected period.  (optional)
     * @param updatedAtGte Returns objects greater or equal the specified date.  This can be combined with updated_at_lte parameter to receive only the selected period.  *New in version 2.6.0*  (optional)
     * @param updatedAtLte Returns objects less or equal the specified date.  This can be combined with updated_at_gte parameter to receive only the selected period.  *New in version 2.6.0*  (optional)
     * @param state The value can be repeated to retrieve multiple matching values (OR condition). (optional)
     * @param orderBy The name of the field to order the results by. Prefix a field name with &#x60;-&#x60; to reverse the sort order.  *New in version 2.1.0*  (optional)
     * @param fields List of field for return.  (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> List of DAG runs. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getDagRunsAsync(@javax.annotation.Nonnull String dagId, @javax.annotation.Nullable Integer limit, @javax.annotation.Nullable Integer offset, @javax.annotation.Nullable OffsetDateTime executionDateGte, @javax.annotation.Nullable OffsetDateTime executionDateLte, @javax.annotation.Nullable OffsetDateTime startDateGte, @javax.annotation.Nullable OffsetDateTime startDateLte, @javax.annotation.Nullable OffsetDateTime endDateGte, @javax.annotation.Nullable OffsetDateTime endDateLte, @javax.annotation.Nullable OffsetDateTime updatedAtGte, @javax.annotation.Nullable OffsetDateTime updatedAtLte, @javax.annotation.Nullable List<String> state, @javax.annotation.Nullable String orderBy, @javax.annotation.Nullable List<String> fields, final ApiCallback<DAGRunCollection> _callback) throws ApiException {

        okhttp3.Call localVarCall = getDagRunsValidateBeforeCall(dagId, limit, offset, executionDateGte, executionDateLte, startDateGte, startDateLte, endDateGte, endDateLte, updatedAtGte, updatedAtLte, state, orderBy, fields, _callback);
        Type localVarReturnType = new TypeToken<DAGRunCollection>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getDagRunsBatch
     * @param listDagRunsForm  (required)
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
     </table>
     */
    public okhttp3.Call getDagRunsBatchCall(@javax.annotation.Nonnull ListDagRunsForm listDagRunsForm, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = listDagRunsForm;

        // create path and map variables
        String localVarPath = "/dags/~/dagRuns/list";

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
    private okhttp3.Call getDagRunsBatchValidateBeforeCall(@javax.annotation.Nonnull ListDagRunsForm listDagRunsForm, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'listDagRunsForm' is set
        if (listDagRunsForm == null) {
            throw new ApiException("Missing the required parameter 'listDagRunsForm' when calling getDagRunsBatch(Async)");
        }

        return getDagRunsBatchCall(listDagRunsForm, _callback);

    }

    /**
     * List DAG runs (batch)
     * This endpoint is a POST to allow filtering across a large number of DAG IDs, where as a GET it would run in to maximum HTTP request URL length limit. 
     * @param listDagRunsForm  (required)
     * @return DAGRunCollection
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Client specified an invalid argument. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Client does not have sufficient permission. </td><td>  -  </td></tr>
     </table>
     */
    public DAGRunCollection getDagRunsBatch(@javax.annotation.Nonnull ListDagRunsForm listDagRunsForm) throws ApiException {
        ApiResponse<DAGRunCollection> localVarResp = getDagRunsBatchWithHttpInfo(listDagRunsForm);
        return localVarResp.getData();
    }

    /**
     * List DAG runs (batch)
     * This endpoint is a POST to allow filtering across a large number of DAG IDs, where as a GET it would run in to maximum HTTP request URL length limit. 
     * @param listDagRunsForm  (required)
     * @return ApiResponse&lt;DAGRunCollection&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Client specified an invalid argument. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Client does not have sufficient permission. </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<DAGRunCollection> getDagRunsBatchWithHttpInfo(@javax.annotation.Nonnull ListDagRunsForm listDagRunsForm) throws ApiException {
        okhttp3.Call localVarCall = getDagRunsBatchValidateBeforeCall(listDagRunsForm, null);
        Type localVarReturnType = new TypeToken<DAGRunCollection>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * List DAG runs (batch) (asynchronously)
     * This endpoint is a POST to allow filtering across a large number of DAG IDs, where as a GET it would run in to maximum HTTP request URL length limit. 
     * @param listDagRunsForm  (required)
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
     </table>
     */
    public okhttp3.Call getDagRunsBatchAsync(@javax.annotation.Nonnull ListDagRunsForm listDagRunsForm, final ApiCallback<DAGRunCollection> _callback) throws ApiException {

        okhttp3.Call localVarCall = getDagRunsBatchValidateBeforeCall(listDagRunsForm, _callback);
        Type localVarReturnType = new TypeToken<DAGRunCollection>(){}.getType();
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
    /**
     * Build call for postDagRun
     * @param dagId The DAG ID. (required)
     * @param daGRun  (required)
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
        <tr><td> 409 </td><td> An existing resource conflicts with the request. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Client does not have sufficient permission. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> A specified resource is not found. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call postDagRunCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull DAGRun daGRun, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = daGRun;

        // create path and map variables
        String localVarPath = "/dags/{dag_id}/dagRuns"
            .replace("{" + "dag_id" + "}", localVarApiClient.escapeString(dagId.toString()));

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
    private okhttp3.Call postDagRunValidateBeforeCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull DAGRun daGRun, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'dagId' is set
        if (dagId == null) {
            throw new ApiException("Missing the required parameter 'dagId' when calling postDagRun(Async)");
        }

        // verify the required parameter 'daGRun' is set
        if (daGRun == null) {
            throw new ApiException("Missing the required parameter 'daGRun' when calling postDagRun(Async)");
        }

        return postDagRunCall(dagId, daGRun, _callback);

    }

    /**
     * Trigger a new DAG run.
     * This will initiate a dagrun. If DAG is paused then dagrun state will remain queued, and the task won&#39;t run. 
     * @param dagId The DAG ID. (required)
     * @param daGRun  (required)
     * @return DAGRun
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Client specified an invalid argument. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> An existing resource conflicts with the request. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Client does not have sufficient permission. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> A specified resource is not found. </td><td>  -  </td></tr>
     </table>
     */
    public DAGRun postDagRun(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull DAGRun daGRun) throws ApiException {
        ApiResponse<DAGRun> localVarResp = postDagRunWithHttpInfo(dagId, daGRun);
        return localVarResp.getData();
    }

    /**
     * Trigger a new DAG run.
     * This will initiate a dagrun. If DAG is paused then dagrun state will remain queued, and the task won&#39;t run. 
     * @param dagId The DAG ID. (required)
     * @param daGRun  (required)
     * @return ApiResponse&lt;DAGRun&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Client specified an invalid argument. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> An existing resource conflicts with the request. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Client does not have sufficient permission. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> A specified resource is not found. </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<DAGRun> postDagRunWithHttpInfo(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull DAGRun daGRun) throws ApiException {
        okhttp3.Call localVarCall = postDagRunValidateBeforeCall(dagId, daGRun, null);
        Type localVarReturnType = new TypeToken<DAGRun>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Trigger a new DAG run. (asynchronously)
     * This will initiate a dagrun. If DAG is paused then dagrun state will remain queued, and the task won&#39;t run. 
     * @param dagId The DAG ID. (required)
     * @param daGRun  (required)
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
        <tr><td> 409 </td><td> An existing resource conflicts with the request. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Client does not have sufficient permission. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> A specified resource is not found. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call postDagRunAsync(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull DAGRun daGRun, final ApiCallback<DAGRun> _callback) throws ApiException {

        okhttp3.Call localVarCall = postDagRunValidateBeforeCall(dagId, daGRun, _callback);
        Type localVarReturnType = new TypeToken<DAGRun>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for setDagRunNote
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param setDagRunNote Parameters of set DagRun note. (required)
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
    public okhttp3.Call setDagRunNoteCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull SetDagRunNote setDagRunNote, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = setDagRunNote;

        // create path and map variables
        String localVarPath = "/dags/{dag_id}/dagRuns/{dag_run_id}/setNote"
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
            "application/json"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] {  };
        return localVarApiClient.buildCall(basePath, localVarPath, "PATCH", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call setDagRunNoteValidateBeforeCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull SetDagRunNote setDagRunNote, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'dagId' is set
        if (dagId == null) {
            throw new ApiException("Missing the required parameter 'dagId' when calling setDagRunNote(Async)");
        }

        // verify the required parameter 'dagRunId' is set
        if (dagRunId == null) {
            throw new ApiException("Missing the required parameter 'dagRunId' when calling setDagRunNote(Async)");
        }

        // verify the required parameter 'setDagRunNote' is set
        if (setDagRunNote == null) {
            throw new ApiException("Missing the required parameter 'setDagRunNote' when calling setDagRunNote(Async)");
        }

        return setDagRunNoteCall(dagId, dagRunId, setDagRunNote, _callback);

    }

    /**
     * Update the DagRun note.
     * Update the manual user note of a DagRun.  *New in version 2.5.0* 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param setDagRunNote Parameters of set DagRun note. (required)
     * @return DAGRun
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
    public DAGRun setDagRunNote(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull SetDagRunNote setDagRunNote) throws ApiException {
        ApiResponse<DAGRun> localVarResp = setDagRunNoteWithHttpInfo(dagId, dagRunId, setDagRunNote);
        return localVarResp.getData();
    }

    /**
     * Update the DagRun note.
     * Update the manual user note of a DagRun.  *New in version 2.5.0* 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param setDagRunNote Parameters of set DagRun note. (required)
     * @return ApiResponse&lt;DAGRun&gt;
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
    public ApiResponse<DAGRun> setDagRunNoteWithHttpInfo(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull SetDagRunNote setDagRunNote) throws ApiException {
        okhttp3.Call localVarCall = setDagRunNoteValidateBeforeCall(dagId, dagRunId, setDagRunNote, null);
        Type localVarReturnType = new TypeToken<DAGRun>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Update the DagRun note. (asynchronously)
     * Update the manual user note of a DagRun.  *New in version 2.5.0* 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param setDagRunNote Parameters of set DagRun note. (required)
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
    public okhttp3.Call setDagRunNoteAsync(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull SetDagRunNote setDagRunNote, final ApiCallback<DAGRun> _callback) throws ApiException {

        okhttp3.Call localVarCall = setDagRunNoteValidateBeforeCall(dagId, dagRunId, setDagRunNote, _callback);
        Type localVarReturnType = new TypeToken<DAGRun>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for updateDagRunState
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param updateDagRunState  (required)
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
    public okhttp3.Call updateDagRunStateCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull UpdateDagRunState updateDagRunState, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = updateDagRunState;

        // create path and map variables
        String localVarPath = "/dags/{dag_id}/dagRuns/{dag_run_id}"
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
            "application/json"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] {  };
        return localVarApiClient.buildCall(basePath, localVarPath, "PATCH", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call updateDagRunStateValidateBeforeCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull UpdateDagRunState updateDagRunState, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'dagId' is set
        if (dagId == null) {
            throw new ApiException("Missing the required parameter 'dagId' when calling updateDagRunState(Async)");
        }

        // verify the required parameter 'dagRunId' is set
        if (dagRunId == null) {
            throw new ApiException("Missing the required parameter 'dagRunId' when calling updateDagRunState(Async)");
        }

        // verify the required parameter 'updateDagRunState' is set
        if (updateDagRunState == null) {
            throw new ApiException("Missing the required parameter 'updateDagRunState' when calling updateDagRunState(Async)");
        }

        return updateDagRunStateCall(dagId, dagRunId, updateDagRunState, _callback);

    }

    /**
     * Modify a DAG run
     * Modify a DAG run.  *New in version 2.2.0* 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param updateDagRunState  (required)
     * @return DAGRun
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
    public DAGRun updateDagRunState(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull UpdateDagRunState updateDagRunState) throws ApiException {
        ApiResponse<DAGRun> localVarResp = updateDagRunStateWithHttpInfo(dagId, dagRunId, updateDagRunState);
        return localVarResp.getData();
    }

    /**
     * Modify a DAG run
     * Modify a DAG run.  *New in version 2.2.0* 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param updateDagRunState  (required)
     * @return ApiResponse&lt;DAGRun&gt;
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
    public ApiResponse<DAGRun> updateDagRunStateWithHttpInfo(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull UpdateDagRunState updateDagRunState) throws ApiException {
        okhttp3.Call localVarCall = updateDagRunStateValidateBeforeCall(dagId, dagRunId, updateDagRunState, null);
        Type localVarReturnType = new TypeToken<DAGRun>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Modify a DAG run (asynchronously)
     * Modify a DAG run.  *New in version 2.2.0* 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param updateDagRunState  (required)
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
    public okhttp3.Call updateDagRunStateAsync(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull UpdateDagRunState updateDagRunState, final ApiCallback<DAGRun> _callback) throws ApiException {

        okhttp3.Call localVarCall = updateDagRunStateValidateBeforeCall(dagId, dagRunId, updateDagRunState, _callback);
        Type localVarReturnType = new TypeToken<DAGRun>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}
