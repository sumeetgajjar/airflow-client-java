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


import java.math.BigDecimal;
import org.openapitools.client.model.Error;
import org.openapitools.client.model.ExtraLinkCollection;
import org.openapitools.client.model.GetLog200Response;
import org.openapitools.client.model.ListTaskInstanceForm;
import java.time.OffsetDateTime;
import org.openapitools.client.model.SetTaskInstanceNote;
import org.openapitools.client.model.TaskInstance;
import org.openapitools.client.model.TaskInstanceCollection;
import org.openapitools.client.model.TaskInstanceDependencyCollection;
import org.openapitools.client.model.TaskInstanceHistory;
import org.openapitools.client.model.TaskInstanceHistoryCollection;
import org.openapitools.client.model.TaskInstanceReference;
import org.openapitools.client.model.UpdateTaskInstance;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskInstanceApi {
    private ApiClient localVarApiClient;
    private int localHostIndex;
    private String localCustomBaseUrl;

    public TaskInstanceApi() {
        this(Configuration.getDefaultApiClient());
    }

    public TaskInstanceApi(ApiClient apiClient) {
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
     * Build call for getExtraLinks
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
     * @param mapIndex Filter on map index for mapped task. (optional)
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
    public okhttp3.Call getExtraLinksCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nullable Integer mapIndex, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/dags/{dag_id}/dagRuns/{dag_run_id}/taskInstances/{task_id}/links"
            .replace("{" + "dag_id" + "}", localVarApiClient.escapeString(dagId.toString()))
            .replace("{" + "dag_run_id" + "}", localVarApiClient.escapeString(dagRunId.toString()))
            .replace("{" + "task_id" + "}", localVarApiClient.escapeString(taskId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (mapIndex != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("map_index", mapIndex));
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
    private okhttp3.Call getExtraLinksValidateBeforeCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nullable Integer mapIndex, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'dagId' is set
        if (dagId == null) {
            throw new ApiException("Missing the required parameter 'dagId' when calling getExtraLinks(Async)");
        }

        // verify the required parameter 'dagRunId' is set
        if (dagRunId == null) {
            throw new ApiException("Missing the required parameter 'dagRunId' when calling getExtraLinks(Async)");
        }

        // verify the required parameter 'taskId' is set
        if (taskId == null) {
            throw new ApiException("Missing the required parameter 'taskId' when calling getExtraLinks(Async)");
        }

        return getExtraLinksCall(dagId, dagRunId, taskId, mapIndex, _callback);

    }

    /**
     * List extra links
     * List extra links for task instance. 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
     * @param mapIndex Filter on map index for mapped task. (optional)
     * @return ExtraLinkCollection
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
    public ExtraLinkCollection getExtraLinks(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nullable Integer mapIndex) throws ApiException {
        ApiResponse<ExtraLinkCollection> localVarResp = getExtraLinksWithHttpInfo(dagId, dagRunId, taskId, mapIndex);
        return localVarResp.getData();
    }

    /**
     * List extra links
     * List extra links for task instance. 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
     * @param mapIndex Filter on map index for mapped task. (optional)
     * @return ApiResponse&lt;ExtraLinkCollection&gt;
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
    public ApiResponse<ExtraLinkCollection> getExtraLinksWithHttpInfo(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nullable Integer mapIndex) throws ApiException {
        okhttp3.Call localVarCall = getExtraLinksValidateBeforeCall(dagId, dagRunId, taskId, mapIndex, null);
        Type localVarReturnType = new TypeToken<ExtraLinkCollection>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * List extra links (asynchronously)
     * List extra links for task instance. 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
     * @param mapIndex Filter on map index for mapped task. (optional)
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
    public okhttp3.Call getExtraLinksAsync(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nullable Integer mapIndex, final ApiCallback<ExtraLinkCollection> _callback) throws ApiException {

        okhttp3.Call localVarCall = getExtraLinksValidateBeforeCall(dagId, dagRunId, taskId, mapIndex, _callback);
        Type localVarReturnType = new TypeToken<ExtraLinkCollection>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getLog
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
     * @param taskTryNumber The task try number. (required)
     * @param fullContent A full content will be returned. By default, only the first fragment will be returned.  (optional)
     * @param mapIndex Filter on map index for mapped task. (optional)
     * @param token A token that allows you to continue fetching logs. If passed, it will specify the location from which the download should be continued.  (optional)
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
    public okhttp3.Call getLogCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nonnull Integer taskTryNumber, @javax.annotation.Nullable Boolean fullContent, @javax.annotation.Nullable Integer mapIndex, @javax.annotation.Nullable String token, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/dags/{dag_id}/dagRuns/{dag_run_id}/taskInstances/{task_id}/logs/{task_try_number}"
            .replace("{" + "dag_id" + "}", localVarApiClient.escapeString(dagId.toString()))
            .replace("{" + "dag_run_id" + "}", localVarApiClient.escapeString(dagRunId.toString()))
            .replace("{" + "task_id" + "}", localVarApiClient.escapeString(taskId.toString()))
            .replace("{" + "task_try_number" + "}", localVarApiClient.escapeString(taskTryNumber.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (fullContent != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("full_content", fullContent));
        }

        if (mapIndex != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("map_index", mapIndex));
        }

        if (token != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("token", token));
        }

        final String[] localVarAccepts = {
            "application/json",
            "text/plain"
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
    private okhttp3.Call getLogValidateBeforeCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nonnull Integer taskTryNumber, @javax.annotation.Nullable Boolean fullContent, @javax.annotation.Nullable Integer mapIndex, @javax.annotation.Nullable String token, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'dagId' is set
        if (dagId == null) {
            throw new ApiException("Missing the required parameter 'dagId' when calling getLog(Async)");
        }

        // verify the required parameter 'dagRunId' is set
        if (dagRunId == null) {
            throw new ApiException("Missing the required parameter 'dagRunId' when calling getLog(Async)");
        }

        // verify the required parameter 'taskId' is set
        if (taskId == null) {
            throw new ApiException("Missing the required parameter 'taskId' when calling getLog(Async)");
        }

        // verify the required parameter 'taskTryNumber' is set
        if (taskTryNumber == null) {
            throw new ApiException("Missing the required parameter 'taskTryNumber' when calling getLog(Async)");
        }

        return getLogCall(dagId, dagRunId, taskId, taskTryNumber, fullContent, mapIndex, token, _callback);

    }

    /**
     * Get logs
     * Get logs for a specific task instance and its try number. To get log from specific character position, following way of using URLSafeSerializer can be used.  Example: &#x60;&#x60;&#x60; from itsdangerous.url_safe import URLSafeSerializer  request_url &#x3D; f\&quot;api/v1/dags/{DAG_ID}/dagRuns/{RUN_ID}/taskInstances/{TASK_ID}/logs/1\&quot; key &#x3D; app.config[\&quot;SECRET_KEY\&quot;] serializer &#x3D; URLSafeSerializer(key) token &#x3D; serializer.dumps({\&quot;log_pos\&quot;: 10000})  response &#x3D; self.client.get(     request_url,     query_string&#x3D;{\&quot;token\&quot;: token},     headers&#x3D;{\&quot;Accept\&quot;: \&quot;text/plain\&quot;},     environ_overrides&#x3D;{\&quot;REMOTE_USER\&quot;: \&quot;test\&quot;}, ) continuation_token &#x3D; response.json[\&quot;continuation_token\&quot;]     metadata &#x3D; URLSafeSerializer(key).loads(continuation_token)     log_pos &#x3D; metadata[\&quot;log_pos\&quot;]     end_of_log &#x3D; metadata[\&quot;end_of_log\&quot;] &#x60;&#x60;&#x60; If log_pos is passed as 10000 like the above example, it renders the logs starting from char position 10000 to last (not the end as the logs may be tailing behind in running state). This way pagination can be done with metadata as part of the token. 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
     * @param taskTryNumber The task try number. (required)
     * @param fullContent A full content will be returned. By default, only the first fragment will be returned.  (optional)
     * @param mapIndex Filter on map index for mapped task. (optional)
     * @param token A token that allows you to continue fetching logs. If passed, it will specify the location from which the download should be continued.  (optional)
     * @return GetLog200Response
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
    public GetLog200Response getLog(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nonnull Integer taskTryNumber, @javax.annotation.Nullable Boolean fullContent, @javax.annotation.Nullable Integer mapIndex, @javax.annotation.Nullable String token) throws ApiException {
        ApiResponse<GetLog200Response> localVarResp = getLogWithHttpInfo(dagId, dagRunId, taskId, taskTryNumber, fullContent, mapIndex, token);
        return localVarResp.getData();
    }

    /**
     * Get logs
     * Get logs for a specific task instance and its try number. To get log from specific character position, following way of using URLSafeSerializer can be used.  Example: &#x60;&#x60;&#x60; from itsdangerous.url_safe import URLSafeSerializer  request_url &#x3D; f\&quot;api/v1/dags/{DAG_ID}/dagRuns/{RUN_ID}/taskInstances/{TASK_ID}/logs/1\&quot; key &#x3D; app.config[\&quot;SECRET_KEY\&quot;] serializer &#x3D; URLSafeSerializer(key) token &#x3D; serializer.dumps({\&quot;log_pos\&quot;: 10000})  response &#x3D; self.client.get(     request_url,     query_string&#x3D;{\&quot;token\&quot;: token},     headers&#x3D;{\&quot;Accept\&quot;: \&quot;text/plain\&quot;},     environ_overrides&#x3D;{\&quot;REMOTE_USER\&quot;: \&quot;test\&quot;}, ) continuation_token &#x3D; response.json[\&quot;continuation_token\&quot;]     metadata &#x3D; URLSafeSerializer(key).loads(continuation_token)     log_pos &#x3D; metadata[\&quot;log_pos\&quot;]     end_of_log &#x3D; metadata[\&quot;end_of_log\&quot;] &#x60;&#x60;&#x60; If log_pos is passed as 10000 like the above example, it renders the logs starting from char position 10000 to last (not the end as the logs may be tailing behind in running state). This way pagination can be done with metadata as part of the token. 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
     * @param taskTryNumber The task try number. (required)
     * @param fullContent A full content will be returned. By default, only the first fragment will be returned.  (optional)
     * @param mapIndex Filter on map index for mapped task. (optional)
     * @param token A token that allows you to continue fetching logs. If passed, it will specify the location from which the download should be continued.  (optional)
     * @return ApiResponse&lt;GetLog200Response&gt;
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
    public ApiResponse<GetLog200Response> getLogWithHttpInfo(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nonnull Integer taskTryNumber, @javax.annotation.Nullable Boolean fullContent, @javax.annotation.Nullable Integer mapIndex, @javax.annotation.Nullable String token) throws ApiException {
        okhttp3.Call localVarCall = getLogValidateBeforeCall(dagId, dagRunId, taskId, taskTryNumber, fullContent, mapIndex, token, null);
        Type localVarReturnType = new TypeToken<GetLog200Response>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Get logs (asynchronously)
     * Get logs for a specific task instance and its try number. To get log from specific character position, following way of using URLSafeSerializer can be used.  Example: &#x60;&#x60;&#x60; from itsdangerous.url_safe import URLSafeSerializer  request_url &#x3D; f\&quot;api/v1/dags/{DAG_ID}/dagRuns/{RUN_ID}/taskInstances/{TASK_ID}/logs/1\&quot; key &#x3D; app.config[\&quot;SECRET_KEY\&quot;] serializer &#x3D; URLSafeSerializer(key) token &#x3D; serializer.dumps({\&quot;log_pos\&quot;: 10000})  response &#x3D; self.client.get(     request_url,     query_string&#x3D;{\&quot;token\&quot;: token},     headers&#x3D;{\&quot;Accept\&quot;: \&quot;text/plain\&quot;},     environ_overrides&#x3D;{\&quot;REMOTE_USER\&quot;: \&quot;test\&quot;}, ) continuation_token &#x3D; response.json[\&quot;continuation_token\&quot;]     metadata &#x3D; URLSafeSerializer(key).loads(continuation_token)     log_pos &#x3D; metadata[\&quot;log_pos\&quot;]     end_of_log &#x3D; metadata[\&quot;end_of_log\&quot;] &#x60;&#x60;&#x60; If log_pos is passed as 10000 like the above example, it renders the logs starting from char position 10000 to last (not the end as the logs may be tailing behind in running state). This way pagination can be done with metadata as part of the token. 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
     * @param taskTryNumber The task try number. (required)
     * @param fullContent A full content will be returned. By default, only the first fragment will be returned.  (optional)
     * @param mapIndex Filter on map index for mapped task. (optional)
     * @param token A token that allows you to continue fetching logs. If passed, it will specify the location from which the download should be continued.  (optional)
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
    public okhttp3.Call getLogAsync(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nonnull Integer taskTryNumber, @javax.annotation.Nullable Boolean fullContent, @javax.annotation.Nullable Integer mapIndex, @javax.annotation.Nullable String token, final ApiCallback<GetLog200Response> _callback) throws ApiException {

        okhttp3.Call localVarCall = getLogValidateBeforeCall(dagId, dagRunId, taskId, taskTryNumber, fullContent, mapIndex, token, _callback);
        Type localVarReturnType = new TypeToken<GetLog200Response>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getMappedTaskInstance
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
     * @param mapIndex The map index. (required)
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
    public okhttp3.Call getMappedTaskInstanceCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nonnull Integer mapIndex, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/dags/{dag_id}/dagRuns/{dag_run_id}/taskInstances/{task_id}/{map_index}"
            .replace("{" + "dag_id" + "}", localVarApiClient.escapeString(dagId.toString()))
            .replace("{" + "dag_run_id" + "}", localVarApiClient.escapeString(dagRunId.toString()))
            .replace("{" + "task_id" + "}", localVarApiClient.escapeString(taskId.toString()))
            .replace("{" + "map_index" + "}", localVarApiClient.escapeString(mapIndex.toString()));

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
    private okhttp3.Call getMappedTaskInstanceValidateBeforeCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nonnull Integer mapIndex, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'dagId' is set
        if (dagId == null) {
            throw new ApiException("Missing the required parameter 'dagId' when calling getMappedTaskInstance(Async)");
        }

        // verify the required parameter 'dagRunId' is set
        if (dagRunId == null) {
            throw new ApiException("Missing the required parameter 'dagRunId' when calling getMappedTaskInstance(Async)");
        }

        // verify the required parameter 'taskId' is set
        if (taskId == null) {
            throw new ApiException("Missing the required parameter 'taskId' when calling getMappedTaskInstance(Async)");
        }

        // verify the required parameter 'mapIndex' is set
        if (mapIndex == null) {
            throw new ApiException("Missing the required parameter 'mapIndex' when calling getMappedTaskInstance(Async)");
        }

        return getMappedTaskInstanceCall(dagId, dagRunId, taskId, mapIndex, _callback);

    }

    /**
     * Get a mapped task instance
     * Get details of a mapped task instance.  *New in version 2.3.0* 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
     * @param mapIndex The map index. (required)
     * @return TaskInstance
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
    public TaskInstance getMappedTaskInstance(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nonnull Integer mapIndex) throws ApiException {
        ApiResponse<TaskInstance> localVarResp = getMappedTaskInstanceWithHttpInfo(dagId, dagRunId, taskId, mapIndex);
        return localVarResp.getData();
    }

    /**
     * Get a mapped task instance
     * Get details of a mapped task instance.  *New in version 2.3.0* 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
     * @param mapIndex The map index. (required)
     * @return ApiResponse&lt;TaskInstance&gt;
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
    public ApiResponse<TaskInstance> getMappedTaskInstanceWithHttpInfo(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nonnull Integer mapIndex) throws ApiException {
        okhttp3.Call localVarCall = getMappedTaskInstanceValidateBeforeCall(dagId, dagRunId, taskId, mapIndex, null);
        Type localVarReturnType = new TypeToken<TaskInstance>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Get a mapped task instance (asynchronously)
     * Get details of a mapped task instance.  *New in version 2.3.0* 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
     * @param mapIndex The map index. (required)
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
    public okhttp3.Call getMappedTaskInstanceAsync(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nonnull Integer mapIndex, final ApiCallback<TaskInstance> _callback) throws ApiException {

        okhttp3.Call localVarCall = getMappedTaskInstanceValidateBeforeCall(dagId, dagRunId, taskId, mapIndex, _callback);
        Type localVarReturnType = new TypeToken<TaskInstance>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getMappedTaskInstanceDependencies
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
     * @param mapIndex The map index. (required)
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
    public okhttp3.Call getMappedTaskInstanceDependenciesCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nonnull Integer mapIndex, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/dags/{dag_id}/dagRuns/{dag_run_id}/taskInstances/{task_id}/{map_index}/dependencies"
            .replace("{" + "dag_id" + "}", localVarApiClient.escapeString(dagId.toString()))
            .replace("{" + "dag_run_id" + "}", localVarApiClient.escapeString(dagRunId.toString()))
            .replace("{" + "task_id" + "}", localVarApiClient.escapeString(taskId.toString()))
            .replace("{" + "map_index" + "}", localVarApiClient.escapeString(mapIndex.toString()));

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
    private okhttp3.Call getMappedTaskInstanceDependenciesValidateBeforeCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nonnull Integer mapIndex, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'dagId' is set
        if (dagId == null) {
            throw new ApiException("Missing the required parameter 'dagId' when calling getMappedTaskInstanceDependencies(Async)");
        }

        // verify the required parameter 'dagRunId' is set
        if (dagRunId == null) {
            throw new ApiException("Missing the required parameter 'dagRunId' when calling getMappedTaskInstanceDependencies(Async)");
        }

        // verify the required parameter 'taskId' is set
        if (taskId == null) {
            throw new ApiException("Missing the required parameter 'taskId' when calling getMappedTaskInstanceDependencies(Async)");
        }

        // verify the required parameter 'mapIndex' is set
        if (mapIndex == null) {
            throw new ApiException("Missing the required parameter 'mapIndex' when calling getMappedTaskInstanceDependencies(Async)");
        }

        return getMappedTaskInstanceDependenciesCall(dagId, dagRunId, taskId, mapIndex, _callback);

    }

    /**
     * Get task dependencies blocking task from getting scheduled.
     * Get task dependencies blocking task from getting scheduled.  *New in version 2.10.0* 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
     * @param mapIndex The map index. (required)
     * @return TaskInstanceDependencyCollection
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
    public TaskInstanceDependencyCollection getMappedTaskInstanceDependencies(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nonnull Integer mapIndex) throws ApiException {
        ApiResponse<TaskInstanceDependencyCollection> localVarResp = getMappedTaskInstanceDependenciesWithHttpInfo(dagId, dagRunId, taskId, mapIndex);
        return localVarResp.getData();
    }

    /**
     * Get task dependencies blocking task from getting scheduled.
     * Get task dependencies blocking task from getting scheduled.  *New in version 2.10.0* 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
     * @param mapIndex The map index. (required)
     * @return ApiResponse&lt;TaskInstanceDependencyCollection&gt;
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
    public ApiResponse<TaskInstanceDependencyCollection> getMappedTaskInstanceDependenciesWithHttpInfo(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nonnull Integer mapIndex) throws ApiException {
        okhttp3.Call localVarCall = getMappedTaskInstanceDependenciesValidateBeforeCall(dagId, dagRunId, taskId, mapIndex, null);
        Type localVarReturnType = new TypeToken<TaskInstanceDependencyCollection>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Get task dependencies blocking task from getting scheduled. (asynchronously)
     * Get task dependencies blocking task from getting scheduled.  *New in version 2.10.0* 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
     * @param mapIndex The map index. (required)
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
    public okhttp3.Call getMappedTaskInstanceDependenciesAsync(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nonnull Integer mapIndex, final ApiCallback<TaskInstanceDependencyCollection> _callback) throws ApiException {

        okhttp3.Call localVarCall = getMappedTaskInstanceDependenciesValidateBeforeCall(dagId, dagRunId, taskId, mapIndex, _callback);
        Type localVarReturnType = new TypeToken<TaskInstanceDependencyCollection>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getMappedTaskInstanceTries
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
     * @param mapIndex The map index. (required)
     * @param limit The numbers of items to return. (optional, default to 100)
     * @param offset The number of items to skip before starting to collect the result set. (optional)
     * @param orderBy The name of the field to order the results by. Prefix a field name with &#x60;-&#x60; to reverse the sort order.  *New in version 2.1.0*  (optional)
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
    public okhttp3.Call getMappedTaskInstanceTriesCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nonnull Integer mapIndex, @javax.annotation.Nullable Integer limit, @javax.annotation.Nullable Integer offset, @javax.annotation.Nullable String orderBy, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/dags/{dag_id}/dagRuns/{dag_run_id}/taskInstances/{task_id}/{map_index}/tries"
            .replace("{" + "dag_id" + "}", localVarApiClient.escapeString(dagId.toString()))
            .replace("{" + "dag_run_id" + "}", localVarApiClient.escapeString(dagRunId.toString()))
            .replace("{" + "task_id" + "}", localVarApiClient.escapeString(taskId.toString()))
            .replace("{" + "map_index" + "}", localVarApiClient.escapeString(mapIndex.toString()));

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
    private okhttp3.Call getMappedTaskInstanceTriesValidateBeforeCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nonnull Integer mapIndex, @javax.annotation.Nullable Integer limit, @javax.annotation.Nullable Integer offset, @javax.annotation.Nullable String orderBy, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'dagId' is set
        if (dagId == null) {
            throw new ApiException("Missing the required parameter 'dagId' when calling getMappedTaskInstanceTries(Async)");
        }

        // verify the required parameter 'dagRunId' is set
        if (dagRunId == null) {
            throw new ApiException("Missing the required parameter 'dagRunId' when calling getMappedTaskInstanceTries(Async)");
        }

        // verify the required parameter 'taskId' is set
        if (taskId == null) {
            throw new ApiException("Missing the required parameter 'taskId' when calling getMappedTaskInstanceTries(Async)");
        }

        // verify the required parameter 'mapIndex' is set
        if (mapIndex == null) {
            throw new ApiException("Missing the required parameter 'mapIndex' when calling getMappedTaskInstanceTries(Async)");
        }

        return getMappedTaskInstanceTriesCall(dagId, dagRunId, taskId, mapIndex, limit, offset, orderBy, _callback);

    }

    /**
     * List mapped task instance tries
     * Get details of all task instance tries.  *New in version 2.10.0* 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
     * @param mapIndex The map index. (required)
     * @param limit The numbers of items to return. (optional, default to 100)
     * @param offset The number of items to skip before starting to collect the result set. (optional)
     * @param orderBy The name of the field to order the results by. Prefix a field name with &#x60;-&#x60; to reverse the sort order.  *New in version 2.1.0*  (optional)
     * @return TaskInstanceHistoryCollection
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
    public TaskInstanceHistoryCollection getMappedTaskInstanceTries(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nonnull Integer mapIndex, @javax.annotation.Nullable Integer limit, @javax.annotation.Nullable Integer offset, @javax.annotation.Nullable String orderBy) throws ApiException {
        ApiResponse<TaskInstanceHistoryCollection> localVarResp = getMappedTaskInstanceTriesWithHttpInfo(dagId, dagRunId, taskId, mapIndex, limit, offset, orderBy);
        return localVarResp.getData();
    }

    /**
     * List mapped task instance tries
     * Get details of all task instance tries.  *New in version 2.10.0* 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
     * @param mapIndex The map index. (required)
     * @param limit The numbers of items to return. (optional, default to 100)
     * @param offset The number of items to skip before starting to collect the result set. (optional)
     * @param orderBy The name of the field to order the results by. Prefix a field name with &#x60;-&#x60; to reverse the sort order.  *New in version 2.1.0*  (optional)
     * @return ApiResponse&lt;TaskInstanceHistoryCollection&gt;
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
    public ApiResponse<TaskInstanceHistoryCollection> getMappedTaskInstanceTriesWithHttpInfo(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nonnull Integer mapIndex, @javax.annotation.Nullable Integer limit, @javax.annotation.Nullable Integer offset, @javax.annotation.Nullable String orderBy) throws ApiException {
        okhttp3.Call localVarCall = getMappedTaskInstanceTriesValidateBeforeCall(dagId, dagRunId, taskId, mapIndex, limit, offset, orderBy, null);
        Type localVarReturnType = new TypeToken<TaskInstanceHistoryCollection>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * List mapped task instance tries (asynchronously)
     * Get details of all task instance tries.  *New in version 2.10.0* 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
     * @param mapIndex The map index. (required)
     * @param limit The numbers of items to return. (optional, default to 100)
     * @param offset The number of items to skip before starting to collect the result set. (optional)
     * @param orderBy The name of the field to order the results by. Prefix a field name with &#x60;-&#x60; to reverse the sort order.  *New in version 2.1.0*  (optional)
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
    public okhttp3.Call getMappedTaskInstanceTriesAsync(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nonnull Integer mapIndex, @javax.annotation.Nullable Integer limit, @javax.annotation.Nullable Integer offset, @javax.annotation.Nullable String orderBy, final ApiCallback<TaskInstanceHistoryCollection> _callback) throws ApiException {

        okhttp3.Call localVarCall = getMappedTaskInstanceTriesValidateBeforeCall(dagId, dagRunId, taskId, mapIndex, limit, offset, orderBy, _callback);
        Type localVarReturnType = new TypeToken<TaskInstanceHistoryCollection>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getMappedTaskInstanceTryDetails
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
     * @param mapIndex The map index. (required)
     * @param taskTryNumber The task try number. (required)
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
    public okhttp3.Call getMappedTaskInstanceTryDetailsCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nonnull Integer mapIndex, @javax.annotation.Nonnull Integer taskTryNumber, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/dags/{dag_id}/dagRuns/{dag_run_id}/taskInstances/{task_id}/{map_index}/tries/{task_try_number}"
            .replace("{" + "dag_id" + "}", localVarApiClient.escapeString(dagId.toString()))
            .replace("{" + "dag_run_id" + "}", localVarApiClient.escapeString(dagRunId.toString()))
            .replace("{" + "task_id" + "}", localVarApiClient.escapeString(taskId.toString()))
            .replace("{" + "map_index" + "}", localVarApiClient.escapeString(mapIndex.toString()))
            .replace("{" + "task_try_number" + "}", localVarApiClient.escapeString(taskTryNumber.toString()));

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
    private okhttp3.Call getMappedTaskInstanceTryDetailsValidateBeforeCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nonnull Integer mapIndex, @javax.annotation.Nonnull Integer taskTryNumber, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'dagId' is set
        if (dagId == null) {
            throw new ApiException("Missing the required parameter 'dagId' when calling getMappedTaskInstanceTryDetails(Async)");
        }

        // verify the required parameter 'dagRunId' is set
        if (dagRunId == null) {
            throw new ApiException("Missing the required parameter 'dagRunId' when calling getMappedTaskInstanceTryDetails(Async)");
        }

        // verify the required parameter 'taskId' is set
        if (taskId == null) {
            throw new ApiException("Missing the required parameter 'taskId' when calling getMappedTaskInstanceTryDetails(Async)");
        }

        // verify the required parameter 'mapIndex' is set
        if (mapIndex == null) {
            throw new ApiException("Missing the required parameter 'mapIndex' when calling getMappedTaskInstanceTryDetails(Async)");
        }

        // verify the required parameter 'taskTryNumber' is set
        if (taskTryNumber == null) {
            throw new ApiException("Missing the required parameter 'taskTryNumber' when calling getMappedTaskInstanceTryDetails(Async)");
        }

        return getMappedTaskInstanceTryDetailsCall(dagId, dagRunId, taskId, mapIndex, taskTryNumber, _callback);

    }

    /**
     * get mapped taskinstance try
     * Get details of a mapped task instance try.  *New in version 2.10.0* 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
     * @param mapIndex The map index. (required)
     * @param taskTryNumber The task try number. (required)
     * @return TaskInstanceHistory
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
    public TaskInstanceHistory getMappedTaskInstanceTryDetails(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nonnull Integer mapIndex, @javax.annotation.Nonnull Integer taskTryNumber) throws ApiException {
        ApiResponse<TaskInstanceHistory> localVarResp = getMappedTaskInstanceTryDetailsWithHttpInfo(dagId, dagRunId, taskId, mapIndex, taskTryNumber);
        return localVarResp.getData();
    }

    /**
     * get mapped taskinstance try
     * Get details of a mapped task instance try.  *New in version 2.10.0* 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
     * @param mapIndex The map index. (required)
     * @param taskTryNumber The task try number. (required)
     * @return ApiResponse&lt;TaskInstanceHistory&gt;
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
    public ApiResponse<TaskInstanceHistory> getMappedTaskInstanceTryDetailsWithHttpInfo(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nonnull Integer mapIndex, @javax.annotation.Nonnull Integer taskTryNumber) throws ApiException {
        okhttp3.Call localVarCall = getMappedTaskInstanceTryDetailsValidateBeforeCall(dagId, dagRunId, taskId, mapIndex, taskTryNumber, null);
        Type localVarReturnType = new TypeToken<TaskInstanceHistory>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * get mapped taskinstance try (asynchronously)
     * Get details of a mapped task instance try.  *New in version 2.10.0* 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
     * @param mapIndex The map index. (required)
     * @param taskTryNumber The task try number. (required)
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
    public okhttp3.Call getMappedTaskInstanceTryDetailsAsync(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nonnull Integer mapIndex, @javax.annotation.Nonnull Integer taskTryNumber, final ApiCallback<TaskInstanceHistory> _callback) throws ApiException {

        okhttp3.Call localVarCall = getMappedTaskInstanceTryDetailsValidateBeforeCall(dagId, dagRunId, taskId, mapIndex, taskTryNumber, _callback);
        Type localVarReturnType = new TypeToken<TaskInstanceHistory>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getMappedTaskInstances
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
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
     * @param durationGte Returns objects greater than or equal to the specified values.  This can be combined with duration_lte parameter to receive only the selected period.  (optional)
     * @param durationLte Returns objects less than or equal to the specified values.  This can be combined with duration_gte parameter to receive only the selected range.  (optional)
     * @param state The value can be repeated to retrieve multiple matching values (OR condition). (optional)
     * @param pool The value can be repeated to retrieve multiple matching values (OR condition). (optional)
     * @param queue The value can be repeated to retrieve multiple matching values (OR condition). (optional)
     * @param executor The value can be repeated to retrieve multiple matching values (OR condition). (optional)
     * @param orderBy The name of the field to order the results by. Prefix a field name with &#x60;-&#x60; to reverse the sort order.  *New in version 2.1.0*  (optional)
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
    public okhttp3.Call getMappedTaskInstancesCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nullable Integer limit, @javax.annotation.Nullable Integer offset, @javax.annotation.Nullable OffsetDateTime executionDateGte, @javax.annotation.Nullable OffsetDateTime executionDateLte, @javax.annotation.Nullable OffsetDateTime startDateGte, @javax.annotation.Nullable OffsetDateTime startDateLte, @javax.annotation.Nullable OffsetDateTime endDateGte, @javax.annotation.Nullable OffsetDateTime endDateLte, @javax.annotation.Nullable OffsetDateTime updatedAtGte, @javax.annotation.Nullable OffsetDateTime updatedAtLte, @javax.annotation.Nullable BigDecimal durationGte, @javax.annotation.Nullable BigDecimal durationLte, @javax.annotation.Nullable List<String> state, @javax.annotation.Nullable List<String> pool, @javax.annotation.Nullable List<String> queue, @javax.annotation.Nullable List<String> executor, @javax.annotation.Nullable String orderBy, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/dags/{dag_id}/dagRuns/{dag_run_id}/taskInstances/{task_id}/listMapped"
            .replace("{" + "dag_id" + "}", localVarApiClient.escapeString(dagId.toString()))
            .replace("{" + "dag_run_id" + "}", localVarApiClient.escapeString(dagRunId.toString()))
            .replace("{" + "task_id" + "}", localVarApiClient.escapeString(taskId.toString()));

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

        if (durationGte != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("duration_gte", durationGte));
        }

        if (durationLte != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("duration_lte", durationLte));
        }

        if (state != null) {
            localVarCollectionQueryParams.addAll(localVarApiClient.parameterToPairs("multi", "state", state));
        }

        if (pool != null) {
            localVarCollectionQueryParams.addAll(localVarApiClient.parameterToPairs("multi", "pool", pool));
        }

        if (queue != null) {
            localVarCollectionQueryParams.addAll(localVarApiClient.parameterToPairs("multi", "queue", queue));
        }

        if (executor != null) {
            localVarCollectionQueryParams.addAll(localVarApiClient.parameterToPairs("multi", "executor", executor));
        }

        if (orderBy != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("order_by", orderBy));
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
    private okhttp3.Call getMappedTaskInstancesValidateBeforeCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nullable Integer limit, @javax.annotation.Nullable Integer offset, @javax.annotation.Nullable OffsetDateTime executionDateGte, @javax.annotation.Nullable OffsetDateTime executionDateLte, @javax.annotation.Nullable OffsetDateTime startDateGte, @javax.annotation.Nullable OffsetDateTime startDateLte, @javax.annotation.Nullable OffsetDateTime endDateGte, @javax.annotation.Nullable OffsetDateTime endDateLte, @javax.annotation.Nullable OffsetDateTime updatedAtGte, @javax.annotation.Nullable OffsetDateTime updatedAtLte, @javax.annotation.Nullable BigDecimal durationGte, @javax.annotation.Nullable BigDecimal durationLte, @javax.annotation.Nullable List<String> state, @javax.annotation.Nullable List<String> pool, @javax.annotation.Nullable List<String> queue, @javax.annotation.Nullable List<String> executor, @javax.annotation.Nullable String orderBy, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'dagId' is set
        if (dagId == null) {
            throw new ApiException("Missing the required parameter 'dagId' when calling getMappedTaskInstances(Async)");
        }

        // verify the required parameter 'dagRunId' is set
        if (dagRunId == null) {
            throw new ApiException("Missing the required parameter 'dagRunId' when calling getMappedTaskInstances(Async)");
        }

        // verify the required parameter 'taskId' is set
        if (taskId == null) {
            throw new ApiException("Missing the required parameter 'taskId' when calling getMappedTaskInstances(Async)");
        }

        return getMappedTaskInstancesCall(dagId, dagRunId, taskId, limit, offset, executionDateGte, executionDateLte, startDateGte, startDateLte, endDateGte, endDateLte, updatedAtGte, updatedAtLte, durationGte, durationLte, state, pool, queue, executor, orderBy, _callback);

    }

    /**
     * List mapped task instances
     * Get details of all mapped task instances.  *New in version 2.3.0* 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
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
     * @param durationGte Returns objects greater than or equal to the specified values.  This can be combined with duration_lte parameter to receive only the selected period.  (optional)
     * @param durationLte Returns objects less than or equal to the specified values.  This can be combined with duration_gte parameter to receive only the selected range.  (optional)
     * @param state The value can be repeated to retrieve multiple matching values (OR condition). (optional)
     * @param pool The value can be repeated to retrieve multiple matching values (OR condition). (optional)
     * @param queue The value can be repeated to retrieve multiple matching values (OR condition). (optional)
     * @param executor The value can be repeated to retrieve multiple matching values (OR condition). (optional)
     * @param orderBy The name of the field to order the results by. Prefix a field name with &#x60;-&#x60; to reverse the sort order.  *New in version 2.1.0*  (optional)
     * @return TaskInstanceCollection
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
    public TaskInstanceCollection getMappedTaskInstances(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nullable Integer limit, @javax.annotation.Nullable Integer offset, @javax.annotation.Nullable OffsetDateTime executionDateGte, @javax.annotation.Nullable OffsetDateTime executionDateLte, @javax.annotation.Nullable OffsetDateTime startDateGte, @javax.annotation.Nullable OffsetDateTime startDateLte, @javax.annotation.Nullable OffsetDateTime endDateGte, @javax.annotation.Nullable OffsetDateTime endDateLte, @javax.annotation.Nullable OffsetDateTime updatedAtGte, @javax.annotation.Nullable OffsetDateTime updatedAtLte, @javax.annotation.Nullable BigDecimal durationGte, @javax.annotation.Nullable BigDecimal durationLte, @javax.annotation.Nullable List<String> state, @javax.annotation.Nullable List<String> pool, @javax.annotation.Nullable List<String> queue, @javax.annotation.Nullable List<String> executor, @javax.annotation.Nullable String orderBy) throws ApiException {
        ApiResponse<TaskInstanceCollection> localVarResp = getMappedTaskInstancesWithHttpInfo(dagId, dagRunId, taskId, limit, offset, executionDateGte, executionDateLte, startDateGte, startDateLte, endDateGte, endDateLte, updatedAtGte, updatedAtLte, durationGte, durationLte, state, pool, queue, executor, orderBy);
        return localVarResp.getData();
    }

    /**
     * List mapped task instances
     * Get details of all mapped task instances.  *New in version 2.3.0* 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
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
     * @param durationGte Returns objects greater than or equal to the specified values.  This can be combined with duration_lte parameter to receive only the selected period.  (optional)
     * @param durationLte Returns objects less than or equal to the specified values.  This can be combined with duration_gte parameter to receive only the selected range.  (optional)
     * @param state The value can be repeated to retrieve multiple matching values (OR condition). (optional)
     * @param pool The value can be repeated to retrieve multiple matching values (OR condition). (optional)
     * @param queue The value can be repeated to retrieve multiple matching values (OR condition). (optional)
     * @param executor The value can be repeated to retrieve multiple matching values (OR condition). (optional)
     * @param orderBy The name of the field to order the results by. Prefix a field name with &#x60;-&#x60; to reverse the sort order.  *New in version 2.1.0*  (optional)
     * @return ApiResponse&lt;TaskInstanceCollection&gt;
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
    public ApiResponse<TaskInstanceCollection> getMappedTaskInstancesWithHttpInfo(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nullable Integer limit, @javax.annotation.Nullable Integer offset, @javax.annotation.Nullable OffsetDateTime executionDateGte, @javax.annotation.Nullable OffsetDateTime executionDateLte, @javax.annotation.Nullable OffsetDateTime startDateGte, @javax.annotation.Nullable OffsetDateTime startDateLte, @javax.annotation.Nullable OffsetDateTime endDateGte, @javax.annotation.Nullable OffsetDateTime endDateLte, @javax.annotation.Nullable OffsetDateTime updatedAtGte, @javax.annotation.Nullable OffsetDateTime updatedAtLte, @javax.annotation.Nullable BigDecimal durationGte, @javax.annotation.Nullable BigDecimal durationLte, @javax.annotation.Nullable List<String> state, @javax.annotation.Nullable List<String> pool, @javax.annotation.Nullable List<String> queue, @javax.annotation.Nullable List<String> executor, @javax.annotation.Nullable String orderBy) throws ApiException {
        okhttp3.Call localVarCall = getMappedTaskInstancesValidateBeforeCall(dagId, dagRunId, taskId, limit, offset, executionDateGte, executionDateLte, startDateGte, startDateLte, endDateGte, endDateLte, updatedAtGte, updatedAtLte, durationGte, durationLte, state, pool, queue, executor, orderBy, null);
        Type localVarReturnType = new TypeToken<TaskInstanceCollection>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * List mapped task instances (asynchronously)
     * Get details of all mapped task instances.  *New in version 2.3.0* 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
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
     * @param durationGte Returns objects greater than or equal to the specified values.  This can be combined with duration_lte parameter to receive only the selected period.  (optional)
     * @param durationLte Returns objects less than or equal to the specified values.  This can be combined with duration_gte parameter to receive only the selected range.  (optional)
     * @param state The value can be repeated to retrieve multiple matching values (OR condition). (optional)
     * @param pool The value can be repeated to retrieve multiple matching values (OR condition). (optional)
     * @param queue The value can be repeated to retrieve multiple matching values (OR condition). (optional)
     * @param executor The value can be repeated to retrieve multiple matching values (OR condition). (optional)
     * @param orderBy The name of the field to order the results by. Prefix a field name with &#x60;-&#x60; to reverse the sort order.  *New in version 2.1.0*  (optional)
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
    public okhttp3.Call getMappedTaskInstancesAsync(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nullable Integer limit, @javax.annotation.Nullable Integer offset, @javax.annotation.Nullable OffsetDateTime executionDateGte, @javax.annotation.Nullable OffsetDateTime executionDateLte, @javax.annotation.Nullable OffsetDateTime startDateGte, @javax.annotation.Nullable OffsetDateTime startDateLte, @javax.annotation.Nullable OffsetDateTime endDateGte, @javax.annotation.Nullable OffsetDateTime endDateLte, @javax.annotation.Nullable OffsetDateTime updatedAtGte, @javax.annotation.Nullable OffsetDateTime updatedAtLte, @javax.annotation.Nullable BigDecimal durationGte, @javax.annotation.Nullable BigDecimal durationLte, @javax.annotation.Nullable List<String> state, @javax.annotation.Nullable List<String> pool, @javax.annotation.Nullable List<String> queue, @javax.annotation.Nullable List<String> executor, @javax.annotation.Nullable String orderBy, final ApiCallback<TaskInstanceCollection> _callback) throws ApiException {

        okhttp3.Call localVarCall = getMappedTaskInstancesValidateBeforeCall(dagId, dagRunId, taskId, limit, offset, executionDateGte, executionDateLte, startDateGte, startDateLte, endDateGte, endDateLte, updatedAtGte, updatedAtLte, durationGte, durationLte, state, pool, queue, executor, orderBy, _callback);
        Type localVarReturnType = new TypeToken<TaskInstanceCollection>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getTaskInstance
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
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
    public okhttp3.Call getTaskInstanceCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/dags/{dag_id}/dagRuns/{dag_run_id}/taskInstances/{task_id}"
            .replace("{" + "dag_id" + "}", localVarApiClient.escapeString(dagId.toString()))
            .replace("{" + "dag_run_id" + "}", localVarApiClient.escapeString(dagRunId.toString()))
            .replace("{" + "task_id" + "}", localVarApiClient.escapeString(taskId.toString()));

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
    private okhttp3.Call getTaskInstanceValidateBeforeCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'dagId' is set
        if (dagId == null) {
            throw new ApiException("Missing the required parameter 'dagId' when calling getTaskInstance(Async)");
        }

        // verify the required parameter 'dagRunId' is set
        if (dagRunId == null) {
            throw new ApiException("Missing the required parameter 'dagRunId' when calling getTaskInstance(Async)");
        }

        // verify the required parameter 'taskId' is set
        if (taskId == null) {
            throw new ApiException("Missing the required parameter 'taskId' when calling getTaskInstance(Async)");
        }

        return getTaskInstanceCall(dagId, dagRunId, taskId, _callback);

    }

    /**
     * Get a task instance
     * 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
     * @return TaskInstance
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
    public TaskInstance getTaskInstance(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId) throws ApiException {
        ApiResponse<TaskInstance> localVarResp = getTaskInstanceWithHttpInfo(dagId, dagRunId, taskId);
        return localVarResp.getData();
    }

    /**
     * Get a task instance
     * 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
     * @return ApiResponse&lt;TaskInstance&gt;
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
    public ApiResponse<TaskInstance> getTaskInstanceWithHttpInfo(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId) throws ApiException {
        okhttp3.Call localVarCall = getTaskInstanceValidateBeforeCall(dagId, dagRunId, taskId, null);
        Type localVarReturnType = new TypeToken<TaskInstance>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Get a task instance (asynchronously)
     * 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
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
    public okhttp3.Call getTaskInstanceAsync(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, final ApiCallback<TaskInstance> _callback) throws ApiException {

        okhttp3.Call localVarCall = getTaskInstanceValidateBeforeCall(dagId, dagRunId, taskId, _callback);
        Type localVarReturnType = new TypeToken<TaskInstance>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getTaskInstanceDependencies
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
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
    public okhttp3.Call getTaskInstanceDependenciesCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/dags/{dag_id}/dagRuns/{dag_run_id}/taskInstances/{task_id}/dependencies"
            .replace("{" + "dag_id" + "}", localVarApiClient.escapeString(dagId.toString()))
            .replace("{" + "dag_run_id" + "}", localVarApiClient.escapeString(dagRunId.toString()))
            .replace("{" + "task_id" + "}", localVarApiClient.escapeString(taskId.toString()));

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
    private okhttp3.Call getTaskInstanceDependenciesValidateBeforeCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'dagId' is set
        if (dagId == null) {
            throw new ApiException("Missing the required parameter 'dagId' when calling getTaskInstanceDependencies(Async)");
        }

        // verify the required parameter 'dagRunId' is set
        if (dagRunId == null) {
            throw new ApiException("Missing the required parameter 'dagRunId' when calling getTaskInstanceDependencies(Async)");
        }

        // verify the required parameter 'taskId' is set
        if (taskId == null) {
            throw new ApiException("Missing the required parameter 'taskId' when calling getTaskInstanceDependencies(Async)");
        }

        return getTaskInstanceDependenciesCall(dagId, dagRunId, taskId, _callback);

    }

    /**
     * Get task dependencies blocking task from getting scheduled.
     * Get task dependencies blocking task from getting scheduled.  *New in version 2.10.0* 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
     * @return TaskInstanceDependencyCollection
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
    public TaskInstanceDependencyCollection getTaskInstanceDependencies(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId) throws ApiException {
        ApiResponse<TaskInstanceDependencyCollection> localVarResp = getTaskInstanceDependenciesWithHttpInfo(dagId, dagRunId, taskId);
        return localVarResp.getData();
    }

    /**
     * Get task dependencies blocking task from getting scheduled.
     * Get task dependencies blocking task from getting scheduled.  *New in version 2.10.0* 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
     * @return ApiResponse&lt;TaskInstanceDependencyCollection&gt;
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
    public ApiResponse<TaskInstanceDependencyCollection> getTaskInstanceDependenciesWithHttpInfo(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId) throws ApiException {
        okhttp3.Call localVarCall = getTaskInstanceDependenciesValidateBeforeCall(dagId, dagRunId, taskId, null);
        Type localVarReturnType = new TypeToken<TaskInstanceDependencyCollection>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Get task dependencies blocking task from getting scheduled. (asynchronously)
     * Get task dependencies blocking task from getting scheduled.  *New in version 2.10.0* 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
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
    public okhttp3.Call getTaskInstanceDependenciesAsync(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, final ApiCallback<TaskInstanceDependencyCollection> _callback) throws ApiException {

        okhttp3.Call localVarCall = getTaskInstanceDependenciesValidateBeforeCall(dagId, dagRunId, taskId, _callback);
        Type localVarReturnType = new TypeToken<TaskInstanceDependencyCollection>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getTaskInstanceTries
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
     * @param limit The numbers of items to return. (optional, default to 100)
     * @param offset The number of items to skip before starting to collect the result set. (optional)
     * @param orderBy The name of the field to order the results by. Prefix a field name with &#x60;-&#x60; to reverse the sort order.  *New in version 2.1.0*  (optional)
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
    public okhttp3.Call getTaskInstanceTriesCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nullable Integer limit, @javax.annotation.Nullable Integer offset, @javax.annotation.Nullable String orderBy, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/dags/{dag_id}/dagRuns/{dag_run_id}/taskInstances/{task_id}/tries"
            .replace("{" + "dag_id" + "}", localVarApiClient.escapeString(dagId.toString()))
            .replace("{" + "dag_run_id" + "}", localVarApiClient.escapeString(dagRunId.toString()))
            .replace("{" + "task_id" + "}", localVarApiClient.escapeString(taskId.toString()));

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
    private okhttp3.Call getTaskInstanceTriesValidateBeforeCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nullable Integer limit, @javax.annotation.Nullable Integer offset, @javax.annotation.Nullable String orderBy, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'dagId' is set
        if (dagId == null) {
            throw new ApiException("Missing the required parameter 'dagId' when calling getTaskInstanceTries(Async)");
        }

        // verify the required parameter 'dagRunId' is set
        if (dagRunId == null) {
            throw new ApiException("Missing the required parameter 'dagRunId' when calling getTaskInstanceTries(Async)");
        }

        // verify the required parameter 'taskId' is set
        if (taskId == null) {
            throw new ApiException("Missing the required parameter 'taskId' when calling getTaskInstanceTries(Async)");
        }

        return getTaskInstanceTriesCall(dagId, dagRunId, taskId, limit, offset, orderBy, _callback);

    }

    /**
     * List task instance tries
     * Get details of all task instance tries.  *New in version 2.10.0* 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
     * @param limit The numbers of items to return. (optional, default to 100)
     * @param offset The number of items to skip before starting to collect the result set. (optional)
     * @param orderBy The name of the field to order the results by. Prefix a field name with &#x60;-&#x60; to reverse the sort order.  *New in version 2.1.0*  (optional)
     * @return TaskInstanceHistoryCollection
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
    public TaskInstanceHistoryCollection getTaskInstanceTries(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nullable Integer limit, @javax.annotation.Nullable Integer offset, @javax.annotation.Nullable String orderBy) throws ApiException {
        ApiResponse<TaskInstanceHistoryCollection> localVarResp = getTaskInstanceTriesWithHttpInfo(dagId, dagRunId, taskId, limit, offset, orderBy);
        return localVarResp.getData();
    }

    /**
     * List task instance tries
     * Get details of all task instance tries.  *New in version 2.10.0* 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
     * @param limit The numbers of items to return. (optional, default to 100)
     * @param offset The number of items to skip before starting to collect the result set. (optional)
     * @param orderBy The name of the field to order the results by. Prefix a field name with &#x60;-&#x60; to reverse the sort order.  *New in version 2.1.0*  (optional)
     * @return ApiResponse&lt;TaskInstanceHistoryCollection&gt;
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
    public ApiResponse<TaskInstanceHistoryCollection> getTaskInstanceTriesWithHttpInfo(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nullable Integer limit, @javax.annotation.Nullable Integer offset, @javax.annotation.Nullable String orderBy) throws ApiException {
        okhttp3.Call localVarCall = getTaskInstanceTriesValidateBeforeCall(dagId, dagRunId, taskId, limit, offset, orderBy, null);
        Type localVarReturnType = new TypeToken<TaskInstanceHistoryCollection>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * List task instance tries (asynchronously)
     * Get details of all task instance tries.  *New in version 2.10.0* 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
     * @param limit The numbers of items to return. (optional, default to 100)
     * @param offset The number of items to skip before starting to collect the result set. (optional)
     * @param orderBy The name of the field to order the results by. Prefix a field name with &#x60;-&#x60; to reverse the sort order.  *New in version 2.1.0*  (optional)
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
    public okhttp3.Call getTaskInstanceTriesAsync(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nullable Integer limit, @javax.annotation.Nullable Integer offset, @javax.annotation.Nullable String orderBy, final ApiCallback<TaskInstanceHistoryCollection> _callback) throws ApiException {

        okhttp3.Call localVarCall = getTaskInstanceTriesValidateBeforeCall(dagId, dagRunId, taskId, limit, offset, orderBy, _callback);
        Type localVarReturnType = new TypeToken<TaskInstanceHistoryCollection>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getTaskInstanceTryDetails
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
     * @param taskTryNumber The task try number. (required)
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
    public okhttp3.Call getTaskInstanceTryDetailsCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nonnull Integer taskTryNumber, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/dags/{dag_id}/dagRuns/{dag_run_id}/taskInstances/{task_id}/tries/{task_try_number}"
            .replace("{" + "dag_id" + "}", localVarApiClient.escapeString(dagId.toString()))
            .replace("{" + "dag_run_id" + "}", localVarApiClient.escapeString(dagRunId.toString()))
            .replace("{" + "task_id" + "}", localVarApiClient.escapeString(taskId.toString()))
            .replace("{" + "task_try_number" + "}", localVarApiClient.escapeString(taskTryNumber.toString()));

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
    private okhttp3.Call getTaskInstanceTryDetailsValidateBeforeCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nonnull Integer taskTryNumber, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'dagId' is set
        if (dagId == null) {
            throw new ApiException("Missing the required parameter 'dagId' when calling getTaskInstanceTryDetails(Async)");
        }

        // verify the required parameter 'dagRunId' is set
        if (dagRunId == null) {
            throw new ApiException("Missing the required parameter 'dagRunId' when calling getTaskInstanceTryDetails(Async)");
        }

        // verify the required parameter 'taskId' is set
        if (taskId == null) {
            throw new ApiException("Missing the required parameter 'taskId' when calling getTaskInstanceTryDetails(Async)");
        }

        // verify the required parameter 'taskTryNumber' is set
        if (taskTryNumber == null) {
            throw new ApiException("Missing the required parameter 'taskTryNumber' when calling getTaskInstanceTryDetails(Async)");
        }

        return getTaskInstanceTryDetailsCall(dagId, dagRunId, taskId, taskTryNumber, _callback);

    }

    /**
     * get taskinstance try
     * Get details of a task instance try.  *New in version 2.10.0* 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
     * @param taskTryNumber The task try number. (required)
     * @return TaskInstanceHistory
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
    public TaskInstanceHistory getTaskInstanceTryDetails(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nonnull Integer taskTryNumber) throws ApiException {
        ApiResponse<TaskInstanceHistory> localVarResp = getTaskInstanceTryDetailsWithHttpInfo(dagId, dagRunId, taskId, taskTryNumber);
        return localVarResp.getData();
    }

    /**
     * get taskinstance try
     * Get details of a task instance try.  *New in version 2.10.0* 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
     * @param taskTryNumber The task try number. (required)
     * @return ApiResponse&lt;TaskInstanceHistory&gt;
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
    public ApiResponse<TaskInstanceHistory> getTaskInstanceTryDetailsWithHttpInfo(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nonnull Integer taskTryNumber) throws ApiException {
        okhttp3.Call localVarCall = getTaskInstanceTryDetailsValidateBeforeCall(dagId, dagRunId, taskId, taskTryNumber, null);
        Type localVarReturnType = new TypeToken<TaskInstanceHistory>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * get taskinstance try (asynchronously)
     * Get details of a task instance try.  *New in version 2.10.0* 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
     * @param taskTryNumber The task try number. (required)
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
    public okhttp3.Call getTaskInstanceTryDetailsAsync(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nonnull Integer taskTryNumber, final ApiCallback<TaskInstanceHistory> _callback) throws ApiException {

        okhttp3.Call localVarCall = getTaskInstanceTryDetailsValidateBeforeCall(dagId, dagRunId, taskId, taskTryNumber, _callback);
        Type localVarReturnType = new TypeToken<TaskInstanceHistory>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getTaskInstances
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param executionDateGte Returns objects greater or equal to the specified date.  This can be combined with execution_date_lte parameter to receive only the selected period.  (optional)
     * @param executionDateLte Returns objects less than or equal to the specified date.  This can be combined with execution_date_gte parameter to receive only the selected period.  (optional)
     * @param startDateGte Returns objects greater or equal the specified date.  This can be combined with start_date_lte parameter to receive only the selected period.  (optional)
     * @param startDateLte Returns objects less or equal the specified date.  This can be combined with start_date_gte parameter to receive only the selected period.  (optional)
     * @param endDateGte Returns objects greater or equal the specified date.  This can be combined with start_date_lte parameter to receive only the selected period.  (optional)
     * @param endDateLte Returns objects less than or equal to the specified date.  This can be combined with start_date_gte parameter to receive only the selected period.  (optional)
     * @param updatedAtGte Returns objects greater or equal the specified date.  This can be combined with updated_at_lte parameter to receive only the selected period.  *New in version 2.6.0*  (optional)
     * @param updatedAtLte Returns objects less or equal the specified date.  This can be combined with updated_at_gte parameter to receive only the selected period.  *New in version 2.6.0*  (optional)
     * @param durationGte Returns objects greater than or equal to the specified values.  This can be combined with duration_lte parameter to receive only the selected period.  (optional)
     * @param durationLte Returns objects less than or equal to the specified values.  This can be combined with duration_gte parameter to receive only the selected range.  (optional)
     * @param state The value can be repeated to retrieve multiple matching values (OR condition). (optional)
     * @param pool The value can be repeated to retrieve multiple matching values (OR condition). (optional)
     * @param queue The value can be repeated to retrieve multiple matching values (OR condition). (optional)
     * @param executor The value can be repeated to retrieve multiple matching values (OR condition). (optional)
     * @param limit The numbers of items to return. (optional, default to 100)
     * @param offset The number of items to skip before starting to collect the result set. (optional)
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
    public okhttp3.Call getTaskInstancesCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nullable OffsetDateTime executionDateGte, @javax.annotation.Nullable OffsetDateTime executionDateLte, @javax.annotation.Nullable OffsetDateTime startDateGte, @javax.annotation.Nullable OffsetDateTime startDateLte, @javax.annotation.Nullable OffsetDateTime endDateGte, @javax.annotation.Nullable OffsetDateTime endDateLte, @javax.annotation.Nullable OffsetDateTime updatedAtGte, @javax.annotation.Nullable OffsetDateTime updatedAtLte, @javax.annotation.Nullable BigDecimal durationGte, @javax.annotation.Nullable BigDecimal durationLte, @javax.annotation.Nullable List<String> state, @javax.annotation.Nullable List<String> pool, @javax.annotation.Nullable List<String> queue, @javax.annotation.Nullable List<String> executor, @javax.annotation.Nullable Integer limit, @javax.annotation.Nullable Integer offset, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/dags/{dag_id}/dagRuns/{dag_run_id}/taskInstances"
            .replace("{" + "dag_id" + "}", localVarApiClient.escapeString(dagId.toString()))
            .replace("{" + "dag_run_id" + "}", localVarApiClient.escapeString(dagRunId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

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

        if (durationGte != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("duration_gte", durationGte));
        }

        if (durationLte != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("duration_lte", durationLte));
        }

        if (state != null) {
            localVarCollectionQueryParams.addAll(localVarApiClient.parameterToPairs("multi", "state", state));
        }

        if (pool != null) {
            localVarCollectionQueryParams.addAll(localVarApiClient.parameterToPairs("multi", "pool", pool));
        }

        if (queue != null) {
            localVarCollectionQueryParams.addAll(localVarApiClient.parameterToPairs("multi", "queue", queue));
        }

        if (executor != null) {
            localVarCollectionQueryParams.addAll(localVarApiClient.parameterToPairs("multi", "executor", executor));
        }

        if (limit != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("limit", limit));
        }

        if (offset != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("offset", offset));
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
    private okhttp3.Call getTaskInstancesValidateBeforeCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nullable OffsetDateTime executionDateGte, @javax.annotation.Nullable OffsetDateTime executionDateLte, @javax.annotation.Nullable OffsetDateTime startDateGte, @javax.annotation.Nullable OffsetDateTime startDateLte, @javax.annotation.Nullable OffsetDateTime endDateGte, @javax.annotation.Nullable OffsetDateTime endDateLte, @javax.annotation.Nullable OffsetDateTime updatedAtGte, @javax.annotation.Nullable OffsetDateTime updatedAtLte, @javax.annotation.Nullable BigDecimal durationGte, @javax.annotation.Nullable BigDecimal durationLte, @javax.annotation.Nullable List<String> state, @javax.annotation.Nullable List<String> pool, @javax.annotation.Nullable List<String> queue, @javax.annotation.Nullable List<String> executor, @javax.annotation.Nullable Integer limit, @javax.annotation.Nullable Integer offset, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'dagId' is set
        if (dagId == null) {
            throw new ApiException("Missing the required parameter 'dagId' when calling getTaskInstances(Async)");
        }

        // verify the required parameter 'dagRunId' is set
        if (dagRunId == null) {
            throw new ApiException("Missing the required parameter 'dagRunId' when calling getTaskInstances(Async)");
        }

        return getTaskInstancesCall(dagId, dagRunId, executionDateGte, executionDateLte, startDateGte, startDateLte, endDateGte, endDateLte, updatedAtGte, updatedAtLte, durationGte, durationLte, state, pool, queue, executor, limit, offset, _callback);

    }

    /**
     * List task instances
     * This endpoint allows specifying &#x60;~&#x60; as the dag_id, dag_run_id to retrieve DAG runs for all DAGs and DAG runs. 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param executionDateGte Returns objects greater or equal to the specified date.  This can be combined with execution_date_lte parameter to receive only the selected period.  (optional)
     * @param executionDateLte Returns objects less than or equal to the specified date.  This can be combined with execution_date_gte parameter to receive only the selected period.  (optional)
     * @param startDateGte Returns objects greater or equal the specified date.  This can be combined with start_date_lte parameter to receive only the selected period.  (optional)
     * @param startDateLte Returns objects less or equal the specified date.  This can be combined with start_date_gte parameter to receive only the selected period.  (optional)
     * @param endDateGte Returns objects greater or equal the specified date.  This can be combined with start_date_lte parameter to receive only the selected period.  (optional)
     * @param endDateLte Returns objects less than or equal to the specified date.  This can be combined with start_date_gte parameter to receive only the selected period.  (optional)
     * @param updatedAtGte Returns objects greater or equal the specified date.  This can be combined with updated_at_lte parameter to receive only the selected period.  *New in version 2.6.0*  (optional)
     * @param updatedAtLte Returns objects less or equal the specified date.  This can be combined with updated_at_gte parameter to receive only the selected period.  *New in version 2.6.0*  (optional)
     * @param durationGte Returns objects greater than or equal to the specified values.  This can be combined with duration_lte parameter to receive only the selected period.  (optional)
     * @param durationLte Returns objects less than or equal to the specified values.  This can be combined with duration_gte parameter to receive only the selected range.  (optional)
     * @param state The value can be repeated to retrieve multiple matching values (OR condition). (optional)
     * @param pool The value can be repeated to retrieve multiple matching values (OR condition). (optional)
     * @param queue The value can be repeated to retrieve multiple matching values (OR condition). (optional)
     * @param executor The value can be repeated to retrieve multiple matching values (OR condition). (optional)
     * @param limit The numbers of items to return. (optional, default to 100)
     * @param offset The number of items to skip before starting to collect the result set. (optional)
     * @return TaskInstanceCollection
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
    public TaskInstanceCollection getTaskInstances(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nullable OffsetDateTime executionDateGte, @javax.annotation.Nullable OffsetDateTime executionDateLte, @javax.annotation.Nullable OffsetDateTime startDateGte, @javax.annotation.Nullable OffsetDateTime startDateLte, @javax.annotation.Nullable OffsetDateTime endDateGte, @javax.annotation.Nullable OffsetDateTime endDateLte, @javax.annotation.Nullable OffsetDateTime updatedAtGte, @javax.annotation.Nullable OffsetDateTime updatedAtLte, @javax.annotation.Nullable BigDecimal durationGte, @javax.annotation.Nullable BigDecimal durationLte, @javax.annotation.Nullable List<String> state, @javax.annotation.Nullable List<String> pool, @javax.annotation.Nullable List<String> queue, @javax.annotation.Nullable List<String> executor, @javax.annotation.Nullable Integer limit, @javax.annotation.Nullable Integer offset) throws ApiException {
        ApiResponse<TaskInstanceCollection> localVarResp = getTaskInstancesWithHttpInfo(dagId, dagRunId, executionDateGte, executionDateLte, startDateGte, startDateLte, endDateGte, endDateLte, updatedAtGte, updatedAtLte, durationGte, durationLte, state, pool, queue, executor, limit, offset);
        return localVarResp.getData();
    }

    /**
     * List task instances
     * This endpoint allows specifying &#x60;~&#x60; as the dag_id, dag_run_id to retrieve DAG runs for all DAGs and DAG runs. 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param executionDateGte Returns objects greater or equal to the specified date.  This can be combined with execution_date_lte parameter to receive only the selected period.  (optional)
     * @param executionDateLte Returns objects less than or equal to the specified date.  This can be combined with execution_date_gte parameter to receive only the selected period.  (optional)
     * @param startDateGte Returns objects greater or equal the specified date.  This can be combined with start_date_lte parameter to receive only the selected period.  (optional)
     * @param startDateLte Returns objects less or equal the specified date.  This can be combined with start_date_gte parameter to receive only the selected period.  (optional)
     * @param endDateGte Returns objects greater or equal the specified date.  This can be combined with start_date_lte parameter to receive only the selected period.  (optional)
     * @param endDateLte Returns objects less than or equal to the specified date.  This can be combined with start_date_gte parameter to receive only the selected period.  (optional)
     * @param updatedAtGte Returns objects greater or equal the specified date.  This can be combined with updated_at_lte parameter to receive only the selected period.  *New in version 2.6.0*  (optional)
     * @param updatedAtLte Returns objects less or equal the specified date.  This can be combined with updated_at_gte parameter to receive only the selected period.  *New in version 2.6.0*  (optional)
     * @param durationGte Returns objects greater than or equal to the specified values.  This can be combined with duration_lte parameter to receive only the selected period.  (optional)
     * @param durationLte Returns objects less than or equal to the specified values.  This can be combined with duration_gte parameter to receive only the selected range.  (optional)
     * @param state The value can be repeated to retrieve multiple matching values (OR condition). (optional)
     * @param pool The value can be repeated to retrieve multiple matching values (OR condition). (optional)
     * @param queue The value can be repeated to retrieve multiple matching values (OR condition). (optional)
     * @param executor The value can be repeated to retrieve multiple matching values (OR condition). (optional)
     * @param limit The numbers of items to return. (optional, default to 100)
     * @param offset The number of items to skip before starting to collect the result set. (optional)
     * @return ApiResponse&lt;TaskInstanceCollection&gt;
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
    public ApiResponse<TaskInstanceCollection> getTaskInstancesWithHttpInfo(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nullable OffsetDateTime executionDateGte, @javax.annotation.Nullable OffsetDateTime executionDateLte, @javax.annotation.Nullable OffsetDateTime startDateGte, @javax.annotation.Nullable OffsetDateTime startDateLte, @javax.annotation.Nullable OffsetDateTime endDateGte, @javax.annotation.Nullable OffsetDateTime endDateLte, @javax.annotation.Nullable OffsetDateTime updatedAtGte, @javax.annotation.Nullable OffsetDateTime updatedAtLte, @javax.annotation.Nullable BigDecimal durationGte, @javax.annotation.Nullable BigDecimal durationLte, @javax.annotation.Nullable List<String> state, @javax.annotation.Nullable List<String> pool, @javax.annotation.Nullable List<String> queue, @javax.annotation.Nullable List<String> executor, @javax.annotation.Nullable Integer limit, @javax.annotation.Nullable Integer offset) throws ApiException {
        okhttp3.Call localVarCall = getTaskInstancesValidateBeforeCall(dagId, dagRunId, executionDateGte, executionDateLte, startDateGte, startDateLte, endDateGte, endDateLte, updatedAtGte, updatedAtLte, durationGte, durationLte, state, pool, queue, executor, limit, offset, null);
        Type localVarReturnType = new TypeToken<TaskInstanceCollection>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * List task instances (asynchronously)
     * This endpoint allows specifying &#x60;~&#x60; as the dag_id, dag_run_id to retrieve DAG runs for all DAGs and DAG runs. 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param executionDateGte Returns objects greater or equal to the specified date.  This can be combined with execution_date_lte parameter to receive only the selected period.  (optional)
     * @param executionDateLte Returns objects less than or equal to the specified date.  This can be combined with execution_date_gte parameter to receive only the selected period.  (optional)
     * @param startDateGte Returns objects greater or equal the specified date.  This can be combined with start_date_lte parameter to receive only the selected period.  (optional)
     * @param startDateLte Returns objects less or equal the specified date.  This can be combined with start_date_gte parameter to receive only the selected period.  (optional)
     * @param endDateGte Returns objects greater or equal the specified date.  This can be combined with start_date_lte parameter to receive only the selected period.  (optional)
     * @param endDateLte Returns objects less than or equal to the specified date.  This can be combined with start_date_gte parameter to receive only the selected period.  (optional)
     * @param updatedAtGte Returns objects greater or equal the specified date.  This can be combined with updated_at_lte parameter to receive only the selected period.  *New in version 2.6.0*  (optional)
     * @param updatedAtLte Returns objects less or equal the specified date.  This can be combined with updated_at_gte parameter to receive only the selected period.  *New in version 2.6.0*  (optional)
     * @param durationGte Returns objects greater than or equal to the specified values.  This can be combined with duration_lte parameter to receive only the selected period.  (optional)
     * @param durationLte Returns objects less than or equal to the specified values.  This can be combined with duration_gte parameter to receive only the selected range.  (optional)
     * @param state The value can be repeated to retrieve multiple matching values (OR condition). (optional)
     * @param pool The value can be repeated to retrieve multiple matching values (OR condition). (optional)
     * @param queue The value can be repeated to retrieve multiple matching values (OR condition). (optional)
     * @param executor The value can be repeated to retrieve multiple matching values (OR condition). (optional)
     * @param limit The numbers of items to return. (optional, default to 100)
     * @param offset The number of items to skip before starting to collect the result set. (optional)
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
    public okhttp3.Call getTaskInstancesAsync(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nullable OffsetDateTime executionDateGte, @javax.annotation.Nullable OffsetDateTime executionDateLte, @javax.annotation.Nullable OffsetDateTime startDateGte, @javax.annotation.Nullable OffsetDateTime startDateLte, @javax.annotation.Nullable OffsetDateTime endDateGte, @javax.annotation.Nullable OffsetDateTime endDateLte, @javax.annotation.Nullable OffsetDateTime updatedAtGte, @javax.annotation.Nullable OffsetDateTime updatedAtLte, @javax.annotation.Nullable BigDecimal durationGte, @javax.annotation.Nullable BigDecimal durationLte, @javax.annotation.Nullable List<String> state, @javax.annotation.Nullable List<String> pool, @javax.annotation.Nullable List<String> queue, @javax.annotation.Nullable List<String> executor, @javax.annotation.Nullable Integer limit, @javax.annotation.Nullable Integer offset, final ApiCallback<TaskInstanceCollection> _callback) throws ApiException {

        okhttp3.Call localVarCall = getTaskInstancesValidateBeforeCall(dagId, dagRunId, executionDateGte, executionDateLte, startDateGte, startDateLte, endDateGte, endDateLte, updatedAtGte, updatedAtLte, durationGte, durationLte, state, pool, queue, executor, limit, offset, _callback);
        Type localVarReturnType = new TypeToken<TaskInstanceCollection>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getTaskInstancesBatch
     * @param listTaskInstanceForm  (required)
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
    public okhttp3.Call getTaskInstancesBatchCall(@javax.annotation.Nonnull ListTaskInstanceForm listTaskInstanceForm, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = listTaskInstanceForm;

        // create path and map variables
        String localVarPath = "/dags/~/dagRuns/~/taskInstances/list";

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
    private okhttp3.Call getTaskInstancesBatchValidateBeforeCall(@javax.annotation.Nonnull ListTaskInstanceForm listTaskInstanceForm, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'listTaskInstanceForm' is set
        if (listTaskInstanceForm == null) {
            throw new ApiException("Missing the required parameter 'listTaskInstanceForm' when calling getTaskInstancesBatch(Async)");
        }

        return getTaskInstancesBatchCall(listTaskInstanceForm, _callback);

    }

    /**
     * List task instances (batch)
     * List task instances from all DAGs and DAG runs. This endpoint is a POST to allow filtering across a large number of DAG IDs, where as a GET it would run in to maximum HTTP request URL length limits. 
     * @param listTaskInstanceForm  (required)
     * @return TaskInstanceCollection
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
    public TaskInstanceCollection getTaskInstancesBatch(@javax.annotation.Nonnull ListTaskInstanceForm listTaskInstanceForm) throws ApiException {
        ApiResponse<TaskInstanceCollection> localVarResp = getTaskInstancesBatchWithHttpInfo(listTaskInstanceForm);
        return localVarResp.getData();
    }

    /**
     * List task instances (batch)
     * List task instances from all DAGs and DAG runs. This endpoint is a POST to allow filtering across a large number of DAG IDs, where as a GET it would run in to maximum HTTP request URL length limits. 
     * @param listTaskInstanceForm  (required)
     * @return ApiResponse&lt;TaskInstanceCollection&gt;
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
    public ApiResponse<TaskInstanceCollection> getTaskInstancesBatchWithHttpInfo(@javax.annotation.Nonnull ListTaskInstanceForm listTaskInstanceForm) throws ApiException {
        okhttp3.Call localVarCall = getTaskInstancesBatchValidateBeforeCall(listTaskInstanceForm, null);
        Type localVarReturnType = new TypeToken<TaskInstanceCollection>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * List task instances (batch) (asynchronously)
     * List task instances from all DAGs and DAG runs. This endpoint is a POST to allow filtering across a large number of DAG IDs, where as a GET it would run in to maximum HTTP request URL length limits. 
     * @param listTaskInstanceForm  (required)
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
    public okhttp3.Call getTaskInstancesBatchAsync(@javax.annotation.Nonnull ListTaskInstanceForm listTaskInstanceForm, final ApiCallback<TaskInstanceCollection> _callback) throws ApiException {

        okhttp3.Call localVarCall = getTaskInstancesBatchValidateBeforeCall(listTaskInstanceForm, _callback);
        Type localVarReturnType = new TypeToken<TaskInstanceCollection>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for patchMappedTaskInstance
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
     * @param mapIndex The map index. (required)
     * @param updateTaskInstance Parameters of action (optional)
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
    public okhttp3.Call patchMappedTaskInstanceCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nonnull Integer mapIndex, @javax.annotation.Nullable UpdateTaskInstance updateTaskInstance, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = updateTaskInstance;

        // create path and map variables
        String localVarPath = "/dags/{dag_id}/dagRuns/{dag_run_id}/taskInstances/{task_id}/{map_index}"
            .replace("{" + "dag_id" + "}", localVarApiClient.escapeString(dagId.toString()))
            .replace("{" + "dag_run_id" + "}", localVarApiClient.escapeString(dagRunId.toString()))
            .replace("{" + "task_id" + "}", localVarApiClient.escapeString(taskId.toString()))
            .replace("{" + "map_index" + "}", localVarApiClient.escapeString(mapIndex.toString()));

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
    private okhttp3.Call patchMappedTaskInstanceValidateBeforeCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nonnull Integer mapIndex, @javax.annotation.Nullable UpdateTaskInstance updateTaskInstance, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'dagId' is set
        if (dagId == null) {
            throw new ApiException("Missing the required parameter 'dagId' when calling patchMappedTaskInstance(Async)");
        }

        // verify the required parameter 'dagRunId' is set
        if (dagRunId == null) {
            throw new ApiException("Missing the required parameter 'dagRunId' when calling patchMappedTaskInstance(Async)");
        }

        // verify the required parameter 'taskId' is set
        if (taskId == null) {
            throw new ApiException("Missing the required parameter 'taskId' when calling patchMappedTaskInstance(Async)");
        }

        // verify the required parameter 'mapIndex' is set
        if (mapIndex == null) {
            throw new ApiException("Missing the required parameter 'mapIndex' when calling patchMappedTaskInstance(Async)");
        }

        return patchMappedTaskInstanceCall(dagId, dagRunId, taskId, mapIndex, updateTaskInstance, _callback);

    }

    /**
     * Updates the state of a mapped task instance
     * Updates the state for single mapped task instance. *New in version 2.5.0* 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
     * @param mapIndex The map index. (required)
     * @param updateTaskInstance Parameters of action (optional)
     * @return TaskInstanceReference
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
    public TaskInstanceReference patchMappedTaskInstance(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nonnull Integer mapIndex, @javax.annotation.Nullable UpdateTaskInstance updateTaskInstance) throws ApiException {
        ApiResponse<TaskInstanceReference> localVarResp = patchMappedTaskInstanceWithHttpInfo(dagId, dagRunId, taskId, mapIndex, updateTaskInstance);
        return localVarResp.getData();
    }

    /**
     * Updates the state of a mapped task instance
     * Updates the state for single mapped task instance. *New in version 2.5.0* 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
     * @param mapIndex The map index. (required)
     * @param updateTaskInstance Parameters of action (optional)
     * @return ApiResponse&lt;TaskInstanceReference&gt;
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
    public ApiResponse<TaskInstanceReference> patchMappedTaskInstanceWithHttpInfo(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nonnull Integer mapIndex, @javax.annotation.Nullable UpdateTaskInstance updateTaskInstance) throws ApiException {
        okhttp3.Call localVarCall = patchMappedTaskInstanceValidateBeforeCall(dagId, dagRunId, taskId, mapIndex, updateTaskInstance, null);
        Type localVarReturnType = new TypeToken<TaskInstanceReference>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Updates the state of a mapped task instance (asynchronously)
     * Updates the state for single mapped task instance. *New in version 2.5.0* 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
     * @param mapIndex The map index. (required)
     * @param updateTaskInstance Parameters of action (optional)
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
    public okhttp3.Call patchMappedTaskInstanceAsync(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nonnull Integer mapIndex, @javax.annotation.Nullable UpdateTaskInstance updateTaskInstance, final ApiCallback<TaskInstanceReference> _callback) throws ApiException {

        okhttp3.Call localVarCall = patchMappedTaskInstanceValidateBeforeCall(dagId, dagRunId, taskId, mapIndex, updateTaskInstance, _callback);
        Type localVarReturnType = new TypeToken<TaskInstanceReference>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for patchTaskInstance
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
     * @param updateTaskInstance Parameters of action (required)
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
    public okhttp3.Call patchTaskInstanceCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nonnull UpdateTaskInstance updateTaskInstance, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = updateTaskInstance;

        // create path and map variables
        String localVarPath = "/dags/{dag_id}/dagRuns/{dag_run_id}/taskInstances/{task_id}"
            .replace("{" + "dag_id" + "}", localVarApiClient.escapeString(dagId.toString()))
            .replace("{" + "dag_run_id" + "}", localVarApiClient.escapeString(dagRunId.toString()))
            .replace("{" + "task_id" + "}", localVarApiClient.escapeString(taskId.toString()));

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
    private okhttp3.Call patchTaskInstanceValidateBeforeCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nonnull UpdateTaskInstance updateTaskInstance, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'dagId' is set
        if (dagId == null) {
            throw new ApiException("Missing the required parameter 'dagId' when calling patchTaskInstance(Async)");
        }

        // verify the required parameter 'dagRunId' is set
        if (dagRunId == null) {
            throw new ApiException("Missing the required parameter 'dagRunId' when calling patchTaskInstance(Async)");
        }

        // verify the required parameter 'taskId' is set
        if (taskId == null) {
            throw new ApiException("Missing the required parameter 'taskId' when calling patchTaskInstance(Async)");
        }

        // verify the required parameter 'updateTaskInstance' is set
        if (updateTaskInstance == null) {
            throw new ApiException("Missing the required parameter 'updateTaskInstance' when calling patchTaskInstance(Async)");
        }

        return patchTaskInstanceCall(dagId, dagRunId, taskId, updateTaskInstance, _callback);

    }

    /**
     * Updates the state of a task instance
     * Updates the state for single task instance. *New in version 2.5.0* 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
     * @param updateTaskInstance Parameters of action (required)
     * @return TaskInstanceReference
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
    public TaskInstanceReference patchTaskInstance(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nonnull UpdateTaskInstance updateTaskInstance) throws ApiException {
        ApiResponse<TaskInstanceReference> localVarResp = patchTaskInstanceWithHttpInfo(dagId, dagRunId, taskId, updateTaskInstance);
        return localVarResp.getData();
    }

    /**
     * Updates the state of a task instance
     * Updates the state for single task instance. *New in version 2.5.0* 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
     * @param updateTaskInstance Parameters of action (required)
     * @return ApiResponse&lt;TaskInstanceReference&gt;
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
    public ApiResponse<TaskInstanceReference> patchTaskInstanceWithHttpInfo(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nonnull UpdateTaskInstance updateTaskInstance) throws ApiException {
        okhttp3.Call localVarCall = patchTaskInstanceValidateBeforeCall(dagId, dagRunId, taskId, updateTaskInstance, null);
        Type localVarReturnType = new TypeToken<TaskInstanceReference>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Updates the state of a task instance (asynchronously)
     * Updates the state for single task instance. *New in version 2.5.0* 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
     * @param updateTaskInstance Parameters of action (required)
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
    public okhttp3.Call patchTaskInstanceAsync(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nonnull UpdateTaskInstance updateTaskInstance, final ApiCallback<TaskInstanceReference> _callback) throws ApiException {

        okhttp3.Call localVarCall = patchTaskInstanceValidateBeforeCall(dagId, dagRunId, taskId, updateTaskInstance, _callback);
        Type localVarReturnType = new TypeToken<TaskInstanceReference>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for setMappedTaskInstanceNote
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
     * @param mapIndex The map index. (required)
     * @param setTaskInstanceNote Parameters of set Task Instance note. (required)
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
    public okhttp3.Call setMappedTaskInstanceNoteCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nonnull Integer mapIndex, @javax.annotation.Nonnull SetTaskInstanceNote setTaskInstanceNote, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = setTaskInstanceNote;

        // create path and map variables
        String localVarPath = "/dags/{dag_id}/dagRuns/{dag_run_id}/taskInstances/{task_id}/{map_index}/setNote"
            .replace("{" + "dag_id" + "}", localVarApiClient.escapeString(dagId.toString()))
            .replace("{" + "dag_run_id" + "}", localVarApiClient.escapeString(dagRunId.toString()))
            .replace("{" + "task_id" + "}", localVarApiClient.escapeString(taskId.toString()))
            .replace("{" + "map_index" + "}", localVarApiClient.escapeString(mapIndex.toString()));

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
    private okhttp3.Call setMappedTaskInstanceNoteValidateBeforeCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nonnull Integer mapIndex, @javax.annotation.Nonnull SetTaskInstanceNote setTaskInstanceNote, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'dagId' is set
        if (dagId == null) {
            throw new ApiException("Missing the required parameter 'dagId' when calling setMappedTaskInstanceNote(Async)");
        }

        // verify the required parameter 'dagRunId' is set
        if (dagRunId == null) {
            throw new ApiException("Missing the required parameter 'dagRunId' when calling setMappedTaskInstanceNote(Async)");
        }

        // verify the required parameter 'taskId' is set
        if (taskId == null) {
            throw new ApiException("Missing the required parameter 'taskId' when calling setMappedTaskInstanceNote(Async)");
        }

        // verify the required parameter 'mapIndex' is set
        if (mapIndex == null) {
            throw new ApiException("Missing the required parameter 'mapIndex' when calling setMappedTaskInstanceNote(Async)");
        }

        // verify the required parameter 'setTaskInstanceNote' is set
        if (setTaskInstanceNote == null) {
            throw new ApiException("Missing the required parameter 'setTaskInstanceNote' when calling setMappedTaskInstanceNote(Async)");
        }

        return setMappedTaskInstanceNoteCall(dagId, dagRunId, taskId, mapIndex, setTaskInstanceNote, _callback);

    }

    /**
     * Update the TaskInstance note.
     * Update the manual user note of a mapped Task Instance.  *New in version 2.5.0* 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
     * @param mapIndex The map index. (required)
     * @param setTaskInstanceNote Parameters of set Task Instance note. (required)
     * @return TaskInstance
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
    public TaskInstance setMappedTaskInstanceNote(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nonnull Integer mapIndex, @javax.annotation.Nonnull SetTaskInstanceNote setTaskInstanceNote) throws ApiException {
        ApiResponse<TaskInstance> localVarResp = setMappedTaskInstanceNoteWithHttpInfo(dagId, dagRunId, taskId, mapIndex, setTaskInstanceNote);
        return localVarResp.getData();
    }

    /**
     * Update the TaskInstance note.
     * Update the manual user note of a mapped Task Instance.  *New in version 2.5.0* 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
     * @param mapIndex The map index. (required)
     * @param setTaskInstanceNote Parameters of set Task Instance note. (required)
     * @return ApiResponse&lt;TaskInstance&gt;
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
    public ApiResponse<TaskInstance> setMappedTaskInstanceNoteWithHttpInfo(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nonnull Integer mapIndex, @javax.annotation.Nonnull SetTaskInstanceNote setTaskInstanceNote) throws ApiException {
        okhttp3.Call localVarCall = setMappedTaskInstanceNoteValidateBeforeCall(dagId, dagRunId, taskId, mapIndex, setTaskInstanceNote, null);
        Type localVarReturnType = new TypeToken<TaskInstance>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Update the TaskInstance note. (asynchronously)
     * Update the manual user note of a mapped Task Instance.  *New in version 2.5.0* 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
     * @param mapIndex The map index. (required)
     * @param setTaskInstanceNote Parameters of set Task Instance note. (required)
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
    public okhttp3.Call setMappedTaskInstanceNoteAsync(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nonnull Integer mapIndex, @javax.annotation.Nonnull SetTaskInstanceNote setTaskInstanceNote, final ApiCallback<TaskInstance> _callback) throws ApiException {

        okhttp3.Call localVarCall = setMappedTaskInstanceNoteValidateBeforeCall(dagId, dagRunId, taskId, mapIndex, setTaskInstanceNote, _callback);
        Type localVarReturnType = new TypeToken<TaskInstance>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for setTaskInstanceNote
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
     * @param setTaskInstanceNote Parameters of set Task Instance note. (required)
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
    public okhttp3.Call setTaskInstanceNoteCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nonnull SetTaskInstanceNote setTaskInstanceNote, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = setTaskInstanceNote;

        // create path and map variables
        String localVarPath = "/dags/{dag_id}/dagRuns/{dag_run_id}/taskInstances/{task_id}/setNote"
            .replace("{" + "dag_id" + "}", localVarApiClient.escapeString(dagId.toString()))
            .replace("{" + "dag_run_id" + "}", localVarApiClient.escapeString(dagRunId.toString()))
            .replace("{" + "task_id" + "}", localVarApiClient.escapeString(taskId.toString()));

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
    private okhttp3.Call setTaskInstanceNoteValidateBeforeCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nonnull SetTaskInstanceNote setTaskInstanceNote, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'dagId' is set
        if (dagId == null) {
            throw new ApiException("Missing the required parameter 'dagId' when calling setTaskInstanceNote(Async)");
        }

        // verify the required parameter 'dagRunId' is set
        if (dagRunId == null) {
            throw new ApiException("Missing the required parameter 'dagRunId' when calling setTaskInstanceNote(Async)");
        }

        // verify the required parameter 'taskId' is set
        if (taskId == null) {
            throw new ApiException("Missing the required parameter 'taskId' when calling setTaskInstanceNote(Async)");
        }

        // verify the required parameter 'setTaskInstanceNote' is set
        if (setTaskInstanceNote == null) {
            throw new ApiException("Missing the required parameter 'setTaskInstanceNote' when calling setTaskInstanceNote(Async)");
        }

        return setTaskInstanceNoteCall(dagId, dagRunId, taskId, setTaskInstanceNote, _callback);

    }

    /**
     * Update the TaskInstance note.
     * Update the manual user note of a non-mapped Task Instance.  *New in version 2.5.0* 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
     * @param setTaskInstanceNote Parameters of set Task Instance note. (required)
     * @return TaskInstance
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
    public TaskInstance setTaskInstanceNote(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nonnull SetTaskInstanceNote setTaskInstanceNote) throws ApiException {
        ApiResponse<TaskInstance> localVarResp = setTaskInstanceNoteWithHttpInfo(dagId, dagRunId, taskId, setTaskInstanceNote);
        return localVarResp.getData();
    }

    /**
     * Update the TaskInstance note.
     * Update the manual user note of a non-mapped Task Instance.  *New in version 2.5.0* 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
     * @param setTaskInstanceNote Parameters of set Task Instance note. (required)
     * @return ApiResponse&lt;TaskInstance&gt;
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
    public ApiResponse<TaskInstance> setTaskInstanceNoteWithHttpInfo(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nonnull SetTaskInstanceNote setTaskInstanceNote) throws ApiException {
        okhttp3.Call localVarCall = setTaskInstanceNoteValidateBeforeCall(dagId, dagRunId, taskId, setTaskInstanceNote, null);
        Type localVarReturnType = new TypeToken<TaskInstance>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Update the TaskInstance note. (asynchronously)
     * Update the manual user note of a non-mapped Task Instance.  *New in version 2.5.0* 
     * @param dagId The DAG ID. (required)
     * @param dagRunId The DAG run ID. (required)
     * @param taskId The task ID. (required)
     * @param setTaskInstanceNote Parameters of set Task Instance note. (required)
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
    public okhttp3.Call setTaskInstanceNoteAsync(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String dagRunId, @javax.annotation.Nonnull String taskId, @javax.annotation.Nonnull SetTaskInstanceNote setTaskInstanceNote, final ApiCallback<TaskInstance> _callback) throws ApiException {

        okhttp3.Call localVarCall = setTaskInstanceNoteValidateBeforeCall(dagId, dagRunId, taskId, setTaskInstanceNote, _callback);
        Type localVarReturnType = new TypeToken<TaskInstance>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}
