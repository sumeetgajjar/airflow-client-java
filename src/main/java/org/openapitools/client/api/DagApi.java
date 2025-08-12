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


import org.openapitools.client.model.ClearTaskInstances;
import org.openapitools.client.model.DAG;
import org.openapitools.client.model.DAGCollection;
import org.openapitools.client.model.DAGDetail;
import org.openapitools.client.model.Error;
import org.openapitools.client.model.GetDagSource200Response;
import org.openapitools.client.model.Task;
import org.openapitools.client.model.TaskCollection;
import org.openapitools.client.model.TaskInstanceReferenceCollection;
import org.openapitools.client.model.UpdateTaskInstancesState;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DagApi {
    private ApiClient localVarApiClient;
    private int localHostIndex;
    private String localCustomBaseUrl;

    public DagApi() {
        this(Configuration.getDefaultApiClient());
    }

    public DagApi(ApiClient apiClient) {
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
     * Build call for deleteDag
     * @param dagId The DAG ID. (required)
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
        <tr><td> 409 </td><td> An existing resource conflicts with the request. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call deleteDagCall(@javax.annotation.Nonnull String dagId, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/dags/{dag_id}"
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
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] {  };
        return localVarApiClient.buildCall(basePath, localVarPath, "DELETE", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call deleteDagValidateBeforeCall(@javax.annotation.Nonnull String dagId, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'dagId' is set
        if (dagId == null) {
            throw new ApiException("Missing the required parameter 'dagId' when calling deleteDag(Async)");
        }

        return deleteDagCall(dagId, _callback);

    }

    /**
     * Delete a DAG
     * Deletes all metadata related to the DAG, including finished DAG Runs and Tasks. Logs are not deleted. This action cannot be undone.  *New in version 2.2.0* 
     * @param dagId The DAG ID. (required)
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
        <tr><td> 409 </td><td> An existing resource conflicts with the request. </td><td>  -  </td></tr>
     </table>
     */
    public void deleteDag(@javax.annotation.Nonnull String dagId) throws ApiException {
        deleteDagWithHttpInfo(dagId);
    }

    /**
     * Delete a DAG
     * Deletes all metadata related to the DAG, including finished DAG Runs and Tasks. Logs are not deleted. This action cannot be undone.  *New in version 2.2.0* 
     * @param dagId The DAG ID. (required)
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
        <tr><td> 409 </td><td> An existing resource conflicts with the request. </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Void> deleteDagWithHttpInfo(@javax.annotation.Nonnull String dagId) throws ApiException {
        okhttp3.Call localVarCall = deleteDagValidateBeforeCall(dagId, null);
        return localVarApiClient.execute(localVarCall);
    }

    /**
     * Delete a DAG (asynchronously)
     * Deletes all metadata related to the DAG, including finished DAG Runs and Tasks. Logs are not deleted. This action cannot be undone.  *New in version 2.2.0* 
     * @param dagId The DAG ID. (required)
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
        <tr><td> 409 </td><td> An existing resource conflicts with the request. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call deleteDagAsync(@javax.annotation.Nonnull String dagId, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = deleteDagValidateBeforeCall(dagId, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
    /**
     * Build call for getDag
     * @param dagId The DAG ID. (required)
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
    public okhttp3.Call getDagCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nullable List<String> fields, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/dags/{dag_id}"
            .replace("{" + "dag_id" + "}", localVarApiClient.escapeString(dagId.toString()));

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
    private okhttp3.Call getDagValidateBeforeCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nullable List<String> fields, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'dagId' is set
        if (dagId == null) {
            throw new ApiException("Missing the required parameter 'dagId' when calling getDag(Async)");
        }

        return getDagCall(dagId, fields, _callback);

    }

    /**
     * Get basic information about a DAG
     * Presents only information available in database (DAGModel). If you need detailed information, consider using GET /dags/{dag_id}/details. 
     * @param dagId The DAG ID. (required)
     * @param fields List of field for return.  (optional)
     * @return DAG
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
    public DAG getDag(@javax.annotation.Nonnull String dagId, @javax.annotation.Nullable List<String> fields) throws ApiException {
        ApiResponse<DAG> localVarResp = getDagWithHttpInfo(dagId, fields);
        return localVarResp.getData();
    }

    /**
     * Get basic information about a DAG
     * Presents only information available in database (DAGModel). If you need detailed information, consider using GET /dags/{dag_id}/details. 
     * @param dagId The DAG ID. (required)
     * @param fields List of field for return.  (optional)
     * @return ApiResponse&lt;DAG&gt;
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
    public ApiResponse<DAG> getDagWithHttpInfo(@javax.annotation.Nonnull String dagId, @javax.annotation.Nullable List<String> fields) throws ApiException {
        okhttp3.Call localVarCall = getDagValidateBeforeCall(dagId, fields, null);
        Type localVarReturnType = new TypeToken<DAG>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Get basic information about a DAG (asynchronously)
     * Presents only information available in database (DAGModel). If you need detailed information, consider using GET /dags/{dag_id}/details. 
     * @param dagId The DAG ID. (required)
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
    public okhttp3.Call getDagAsync(@javax.annotation.Nonnull String dagId, @javax.annotation.Nullable List<String> fields, final ApiCallback<DAG> _callback) throws ApiException {

        okhttp3.Call localVarCall = getDagValidateBeforeCall(dagId, fields, _callback);
        Type localVarReturnType = new TypeToken<DAG>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getDagDetails
     * @param dagId The DAG ID. (required)
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
    public okhttp3.Call getDagDetailsCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nullable List<String> fields, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/dags/{dag_id}/details"
            .replace("{" + "dag_id" + "}", localVarApiClient.escapeString(dagId.toString()));

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
    private okhttp3.Call getDagDetailsValidateBeforeCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nullable List<String> fields, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'dagId' is set
        if (dagId == null) {
            throw new ApiException("Missing the required parameter 'dagId' when calling getDagDetails(Async)");
        }

        return getDagDetailsCall(dagId, fields, _callback);

    }

    /**
     * Get a simplified representation of DAG
     * The response contains many DAG attributes, so the response can be large. If possible, consider using GET /dags/{dag_id}. 
     * @param dagId The DAG ID. (required)
     * @param fields List of field for return.  (optional)
     * @return DAGDetail
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
    public DAGDetail getDagDetails(@javax.annotation.Nonnull String dagId, @javax.annotation.Nullable List<String> fields) throws ApiException {
        ApiResponse<DAGDetail> localVarResp = getDagDetailsWithHttpInfo(dagId, fields);
        return localVarResp.getData();
    }

    /**
     * Get a simplified representation of DAG
     * The response contains many DAG attributes, so the response can be large. If possible, consider using GET /dags/{dag_id}. 
     * @param dagId The DAG ID. (required)
     * @param fields List of field for return.  (optional)
     * @return ApiResponse&lt;DAGDetail&gt;
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
    public ApiResponse<DAGDetail> getDagDetailsWithHttpInfo(@javax.annotation.Nonnull String dagId, @javax.annotation.Nullable List<String> fields) throws ApiException {
        okhttp3.Call localVarCall = getDagDetailsValidateBeforeCall(dagId, fields, null);
        Type localVarReturnType = new TypeToken<DAGDetail>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Get a simplified representation of DAG (asynchronously)
     * The response contains many DAG attributes, so the response can be large. If possible, consider using GET /dags/{dag_id}. 
     * @param dagId The DAG ID. (required)
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
    public okhttp3.Call getDagDetailsAsync(@javax.annotation.Nonnull String dagId, @javax.annotation.Nullable List<String> fields, final ApiCallback<DAGDetail> _callback) throws ApiException {

        okhttp3.Call localVarCall = getDagDetailsValidateBeforeCall(dagId, fields, _callback);
        Type localVarReturnType = new TypeToken<DAGDetail>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getDagSource
     * @param fileToken The key containing the encrypted path to the file. Encryption and decryption take place only on the server. This prevents the client from reading an non-DAG file. This also ensures API extensibility, because the format of encrypted data may change.  (required)
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
        <tr><td> 406 </td><td> A specified Accept header is not allowed. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getDagSourceCall(@javax.annotation.Nonnull String fileToken, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/dagSources/{file_token}"
            .replace("{" + "file_token" + "}", localVarApiClient.escapeString(fileToken.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

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
    private okhttp3.Call getDagSourceValidateBeforeCall(@javax.annotation.Nonnull String fileToken, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'fileToken' is set
        if (fileToken == null) {
            throw new ApiException("Missing the required parameter 'fileToken' when calling getDagSource(Async)");
        }

        return getDagSourceCall(fileToken, _callback);

    }

    /**
     * Get a source code
     * Get a source code using file token. 
     * @param fileToken The key containing the encrypted path to the file. Encryption and decryption take place only on the server. This prevents the client from reading an non-DAG file. This also ensures API extensibility, because the format of encrypted data may change.  (required)
     * @return GetDagSource200Response
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Client does not have sufficient permission. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> A specified resource is not found. </td><td>  -  </td></tr>
        <tr><td> 406 </td><td> A specified Accept header is not allowed. </td><td>  -  </td></tr>
     </table>
     */
    public GetDagSource200Response getDagSource(@javax.annotation.Nonnull String fileToken) throws ApiException {
        ApiResponse<GetDagSource200Response> localVarResp = getDagSourceWithHttpInfo(fileToken);
        return localVarResp.getData();
    }

    /**
     * Get a source code
     * Get a source code using file token. 
     * @param fileToken The key containing the encrypted path to the file. Encryption and decryption take place only on the server. This prevents the client from reading an non-DAG file. This also ensures API extensibility, because the format of encrypted data may change.  (required)
     * @return ApiResponse&lt;GetDagSource200Response&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Client does not have sufficient permission. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> A specified resource is not found. </td><td>  -  </td></tr>
        <tr><td> 406 </td><td> A specified Accept header is not allowed. </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<GetDagSource200Response> getDagSourceWithHttpInfo(@javax.annotation.Nonnull String fileToken) throws ApiException {
        okhttp3.Call localVarCall = getDagSourceValidateBeforeCall(fileToken, null);
        Type localVarReturnType = new TypeToken<GetDagSource200Response>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Get a source code (asynchronously)
     * Get a source code using file token. 
     * @param fileToken The key containing the encrypted path to the file. Encryption and decryption take place only on the server. This prevents the client from reading an non-DAG file. This also ensures API extensibility, because the format of encrypted data may change.  (required)
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
        <tr><td> 406 </td><td> A specified Accept header is not allowed. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getDagSourceAsync(@javax.annotation.Nonnull String fileToken, final ApiCallback<GetDagSource200Response> _callback) throws ApiException {

        okhttp3.Call localVarCall = getDagSourceValidateBeforeCall(fileToken, _callback);
        Type localVarReturnType = new TypeToken<GetDagSource200Response>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getDags
     * @param limit The numbers of items to return. (optional, default to 100)
     * @param offset The number of items to skip before starting to collect the result set. (optional)
     * @param orderBy The name of the field to order the results by. Prefix a field name with &#x60;-&#x60; to reverse the sort order.  *New in version 2.1.0*  (optional)
     * @param tags List of tags to filter results.  *New in version 2.2.0*  (optional)
     * @param onlyActive Only filter active DAGs.  *New in version 2.1.1*  (optional, default to true)
     * @param paused Only filter paused/unpaused DAGs. If absent or null, it returns paused and unpaused DAGs.  *New in version 2.6.0*  (optional)
     * @param fields List of field for return.  (optional)
     * @param dagIdPattern If set, only return DAGs with dag_ids matching this pattern.  (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getDagsCall(@javax.annotation.Nullable Integer limit, @javax.annotation.Nullable Integer offset, @javax.annotation.Nullable String orderBy, @javax.annotation.Nullable List<String> tags, @javax.annotation.Nullable Boolean onlyActive, @javax.annotation.Nullable Boolean paused, @javax.annotation.Nullable List<String> fields, @javax.annotation.Nullable String dagIdPattern, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/dags";

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

        if (tags != null) {
            localVarCollectionQueryParams.addAll(localVarApiClient.parameterToPairs("multi", "tags", tags));
        }

        if (onlyActive != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("only_active", onlyActive));
        }

        if (paused != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("paused", paused));
        }

        if (fields != null) {
            localVarCollectionQueryParams.addAll(localVarApiClient.parameterToPairs("multi", "fields", fields));
        }

        if (dagIdPattern != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("dag_id_pattern", dagIdPattern));
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
    private okhttp3.Call getDagsValidateBeforeCall(@javax.annotation.Nullable Integer limit, @javax.annotation.Nullable Integer offset, @javax.annotation.Nullable String orderBy, @javax.annotation.Nullable List<String> tags, @javax.annotation.Nullable Boolean onlyActive, @javax.annotation.Nullable Boolean paused, @javax.annotation.Nullable List<String> fields, @javax.annotation.Nullable String dagIdPattern, final ApiCallback _callback) throws ApiException {
        return getDagsCall(limit, offset, orderBy, tags, onlyActive, paused, fields, dagIdPattern, _callback);

    }

    /**
     * List DAGs
     * List DAGs in the database. &#x60;dag_id_pattern&#x60; can be set to match dags of a specific pattern 
     * @param limit The numbers of items to return. (optional, default to 100)
     * @param offset The number of items to skip before starting to collect the result set. (optional)
     * @param orderBy The name of the field to order the results by. Prefix a field name with &#x60;-&#x60; to reverse the sort order.  *New in version 2.1.0*  (optional)
     * @param tags List of tags to filter results.  *New in version 2.2.0*  (optional)
     * @param onlyActive Only filter active DAGs.  *New in version 2.1.1*  (optional, default to true)
     * @param paused Only filter paused/unpaused DAGs. If absent or null, it returns paused and unpaused DAGs.  *New in version 2.6.0*  (optional)
     * @param fields List of field for return.  (optional)
     * @param dagIdPattern If set, only return DAGs with dag_ids matching this pattern.  (optional)
     * @return DAGCollection
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
     </table>
     */
    public DAGCollection getDags(@javax.annotation.Nullable Integer limit, @javax.annotation.Nullable Integer offset, @javax.annotation.Nullable String orderBy, @javax.annotation.Nullable List<String> tags, @javax.annotation.Nullable Boolean onlyActive, @javax.annotation.Nullable Boolean paused, @javax.annotation.Nullable List<String> fields, @javax.annotation.Nullable String dagIdPattern) throws ApiException {
        ApiResponse<DAGCollection> localVarResp = getDagsWithHttpInfo(limit, offset, orderBy, tags, onlyActive, paused, fields, dagIdPattern);
        return localVarResp.getData();
    }

    /**
     * List DAGs
     * List DAGs in the database. &#x60;dag_id_pattern&#x60; can be set to match dags of a specific pattern 
     * @param limit The numbers of items to return. (optional, default to 100)
     * @param offset The number of items to skip before starting to collect the result set. (optional)
     * @param orderBy The name of the field to order the results by. Prefix a field name with &#x60;-&#x60; to reverse the sort order.  *New in version 2.1.0*  (optional)
     * @param tags List of tags to filter results.  *New in version 2.2.0*  (optional)
     * @param onlyActive Only filter active DAGs.  *New in version 2.1.1*  (optional, default to true)
     * @param paused Only filter paused/unpaused DAGs. If absent or null, it returns paused and unpaused DAGs.  *New in version 2.6.0*  (optional)
     * @param fields List of field for return.  (optional)
     * @param dagIdPattern If set, only return DAGs with dag_ids matching this pattern.  (optional)
     * @return ApiResponse&lt;DAGCollection&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<DAGCollection> getDagsWithHttpInfo(@javax.annotation.Nullable Integer limit, @javax.annotation.Nullable Integer offset, @javax.annotation.Nullable String orderBy, @javax.annotation.Nullable List<String> tags, @javax.annotation.Nullable Boolean onlyActive, @javax.annotation.Nullable Boolean paused, @javax.annotation.Nullable List<String> fields, @javax.annotation.Nullable String dagIdPattern) throws ApiException {
        okhttp3.Call localVarCall = getDagsValidateBeforeCall(limit, offset, orderBy, tags, onlyActive, paused, fields, dagIdPattern, null);
        Type localVarReturnType = new TypeToken<DAGCollection>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * List DAGs (asynchronously)
     * List DAGs in the database. &#x60;dag_id_pattern&#x60; can be set to match dags of a specific pattern 
     * @param limit The numbers of items to return. (optional, default to 100)
     * @param offset The number of items to skip before starting to collect the result set. (optional)
     * @param orderBy The name of the field to order the results by. Prefix a field name with &#x60;-&#x60; to reverse the sort order.  *New in version 2.1.0*  (optional)
     * @param tags List of tags to filter results.  *New in version 2.2.0*  (optional)
     * @param onlyActive Only filter active DAGs.  *New in version 2.1.1*  (optional, default to true)
     * @param paused Only filter paused/unpaused DAGs. If absent or null, it returns paused and unpaused DAGs.  *New in version 2.6.0*  (optional)
     * @param fields List of field for return.  (optional)
     * @param dagIdPattern If set, only return DAGs with dag_ids matching this pattern.  (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getDagsAsync(@javax.annotation.Nullable Integer limit, @javax.annotation.Nullable Integer offset, @javax.annotation.Nullable String orderBy, @javax.annotation.Nullable List<String> tags, @javax.annotation.Nullable Boolean onlyActive, @javax.annotation.Nullable Boolean paused, @javax.annotation.Nullable List<String> fields, @javax.annotation.Nullable String dagIdPattern, final ApiCallback<DAGCollection> _callback) throws ApiException {

        okhttp3.Call localVarCall = getDagsValidateBeforeCall(limit, offset, orderBy, tags, onlyActive, paused, fields, dagIdPattern, _callback);
        Type localVarReturnType = new TypeToken<DAGCollection>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getTask
     * @param dagId The DAG ID. (required)
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
    public okhttp3.Call getTaskCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String taskId, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/dags/{dag_id}/tasks/{task_id}"
            .replace("{" + "dag_id" + "}", localVarApiClient.escapeString(dagId.toString()))
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
    private okhttp3.Call getTaskValidateBeforeCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String taskId, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'dagId' is set
        if (dagId == null) {
            throw new ApiException("Missing the required parameter 'dagId' when calling getTask(Async)");
        }

        // verify the required parameter 'taskId' is set
        if (taskId == null) {
            throw new ApiException("Missing the required parameter 'taskId' when calling getTask(Async)");
        }

        return getTaskCall(dagId, taskId, _callback);

    }

    /**
     * Get simplified representation of a task
     * 
     * @param dagId The DAG ID. (required)
     * @param taskId The task ID. (required)
     * @return Task
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
    public Task getTask(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String taskId) throws ApiException {
        ApiResponse<Task> localVarResp = getTaskWithHttpInfo(dagId, taskId);
        return localVarResp.getData();
    }

    /**
     * Get simplified representation of a task
     * 
     * @param dagId The DAG ID. (required)
     * @param taskId The task ID. (required)
     * @return ApiResponse&lt;Task&gt;
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
    public ApiResponse<Task> getTaskWithHttpInfo(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String taskId) throws ApiException {
        okhttp3.Call localVarCall = getTaskValidateBeforeCall(dagId, taskId, null);
        Type localVarReturnType = new TypeToken<Task>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Get simplified representation of a task (asynchronously)
     * 
     * @param dagId The DAG ID. (required)
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
    public okhttp3.Call getTaskAsync(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull String taskId, final ApiCallback<Task> _callback) throws ApiException {

        okhttp3.Call localVarCall = getTaskValidateBeforeCall(dagId, taskId, _callback);
        Type localVarReturnType = new TypeToken<Task>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getTasks
     * @param dagId The DAG ID. (required)
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
    public okhttp3.Call getTasksCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nullable String orderBy, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/dags/{dag_id}/tasks"
            .replace("{" + "dag_id" + "}", localVarApiClient.escapeString(dagId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

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
    private okhttp3.Call getTasksValidateBeforeCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nullable String orderBy, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'dagId' is set
        if (dagId == null) {
            throw new ApiException("Missing the required parameter 'dagId' when calling getTasks(Async)");
        }

        return getTasksCall(dagId, orderBy, _callback);

    }

    /**
     * Get tasks for DAG
     * 
     * @param dagId The DAG ID. (required)
     * @param orderBy The name of the field to order the results by. Prefix a field name with &#x60;-&#x60; to reverse the sort order.  *New in version 2.1.0*  (optional)
     * @return TaskCollection
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
    public TaskCollection getTasks(@javax.annotation.Nonnull String dagId, @javax.annotation.Nullable String orderBy) throws ApiException {
        ApiResponse<TaskCollection> localVarResp = getTasksWithHttpInfo(dagId, orderBy);
        return localVarResp.getData();
    }

    /**
     * Get tasks for DAG
     * 
     * @param dagId The DAG ID. (required)
     * @param orderBy The name of the field to order the results by. Prefix a field name with &#x60;-&#x60; to reverse the sort order.  *New in version 2.1.0*  (optional)
     * @return ApiResponse&lt;TaskCollection&gt;
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
    public ApiResponse<TaskCollection> getTasksWithHttpInfo(@javax.annotation.Nonnull String dagId, @javax.annotation.Nullable String orderBy) throws ApiException {
        okhttp3.Call localVarCall = getTasksValidateBeforeCall(dagId, orderBy, null);
        Type localVarReturnType = new TypeToken<TaskCollection>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Get tasks for DAG (asynchronously)
     * 
     * @param dagId The DAG ID. (required)
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
    public okhttp3.Call getTasksAsync(@javax.annotation.Nonnull String dagId, @javax.annotation.Nullable String orderBy, final ApiCallback<TaskCollection> _callback) throws ApiException {

        okhttp3.Call localVarCall = getTasksValidateBeforeCall(dagId, orderBy, _callback);
        Type localVarReturnType = new TypeToken<TaskCollection>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for patchDag
     * @param dagId The DAG ID. (required)
     * @param DAG  (required)
     * @param updateMask The fields to update on the resource. If absent or empty, all modifiable fields are updated. A comma-separated list of fully qualified names of fields.  (optional)
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
    public okhttp3.Call patchDagCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull DAG DAG, @javax.annotation.Nullable List<String> updateMask, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = DAG;

        // create path and map variables
        String localVarPath = "/dags/{dag_id}"
            .replace("{" + "dag_id" + "}", localVarApiClient.escapeString(dagId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (updateMask != null) {
            localVarCollectionQueryParams.addAll(localVarApiClient.parameterToPairs("csv", "update_mask", updateMask));
        }

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
    private okhttp3.Call patchDagValidateBeforeCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull DAG DAG, @javax.annotation.Nullable List<String> updateMask, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'dagId' is set
        if (dagId == null) {
            throw new ApiException("Missing the required parameter 'dagId' when calling patchDag(Async)");
        }

        // verify the required parameter 'DAG' is set
        if (DAG == null) {
            throw new ApiException("Missing the required parameter 'DAG' when calling patchDag(Async)");
        }

        return patchDagCall(dagId, DAG, updateMask, _callback);

    }

    /**
     * Update a DAG
     * 
     * @param dagId The DAG ID. (required)
     * @param DAG  (required)
     * @param updateMask The fields to update on the resource. If absent or empty, all modifiable fields are updated. A comma-separated list of fully qualified names of fields.  (optional)
     * @return DAG
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
    public DAG patchDag(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull DAG DAG, @javax.annotation.Nullable List<String> updateMask) throws ApiException {
        ApiResponse<DAG> localVarResp = patchDagWithHttpInfo(dagId, DAG, updateMask);
        return localVarResp.getData();
    }

    /**
     * Update a DAG
     * 
     * @param dagId The DAG ID. (required)
     * @param DAG  (required)
     * @param updateMask The fields to update on the resource. If absent or empty, all modifiable fields are updated. A comma-separated list of fully qualified names of fields.  (optional)
     * @return ApiResponse&lt;DAG&gt;
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
    public ApiResponse<DAG> patchDagWithHttpInfo(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull DAG DAG, @javax.annotation.Nullable List<String> updateMask) throws ApiException {
        okhttp3.Call localVarCall = patchDagValidateBeforeCall(dagId, DAG, updateMask, null);
        Type localVarReturnType = new TypeToken<DAG>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Update a DAG (asynchronously)
     * 
     * @param dagId The DAG ID. (required)
     * @param DAG  (required)
     * @param updateMask The fields to update on the resource. If absent or empty, all modifiable fields are updated. A comma-separated list of fully qualified names of fields.  (optional)
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
    public okhttp3.Call patchDagAsync(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull DAG DAG, @javax.annotation.Nullable List<String> updateMask, final ApiCallback<DAG> _callback) throws ApiException {

        okhttp3.Call localVarCall = patchDagValidateBeforeCall(dagId, DAG, updateMask, _callback);
        Type localVarReturnType = new TypeToken<DAG>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for patchDags
     * @param dagIdPattern If set, only update DAGs with dag_ids matching this pattern.  (required)
     * @param DAG  (required)
     * @param limit The numbers of items to return. (optional, default to 100)
     * @param offset The number of items to skip before starting to collect the result set. (optional)
     * @param tags List of tags to filter results.  *New in version 2.2.0*  (optional)
     * @param updateMask The fields to update on the resource. If absent or empty, all modifiable fields are updated. A comma-separated list of fully qualified names of fields.  (optional)
     * @param onlyActive Only filter active DAGs.  *New in version 2.1.1*  (optional, default to true)
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
    public okhttp3.Call patchDagsCall(@javax.annotation.Nonnull String dagIdPattern, @javax.annotation.Nonnull DAG DAG, @javax.annotation.Nullable Integer limit, @javax.annotation.Nullable Integer offset, @javax.annotation.Nullable List<String> tags, @javax.annotation.Nullable List<String> updateMask, @javax.annotation.Nullable Boolean onlyActive, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = DAG;

        // create path and map variables
        String localVarPath = "/dags";

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

        if (tags != null) {
            localVarCollectionQueryParams.addAll(localVarApiClient.parameterToPairs("multi", "tags", tags));
        }

        if (updateMask != null) {
            localVarCollectionQueryParams.addAll(localVarApiClient.parameterToPairs("csv", "update_mask", updateMask));
        }

        if (onlyActive != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("only_active", onlyActive));
        }

        if (dagIdPattern != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("dag_id_pattern", dagIdPattern));
        }

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
    private okhttp3.Call patchDagsValidateBeforeCall(@javax.annotation.Nonnull String dagIdPattern, @javax.annotation.Nonnull DAG DAG, @javax.annotation.Nullable Integer limit, @javax.annotation.Nullable Integer offset, @javax.annotation.Nullable List<String> tags, @javax.annotation.Nullable List<String> updateMask, @javax.annotation.Nullable Boolean onlyActive, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'dagIdPattern' is set
        if (dagIdPattern == null) {
            throw new ApiException("Missing the required parameter 'dagIdPattern' when calling patchDags(Async)");
        }

        // verify the required parameter 'DAG' is set
        if (DAG == null) {
            throw new ApiException("Missing the required parameter 'DAG' when calling patchDags(Async)");
        }

        return patchDagsCall(dagIdPattern, DAG, limit, offset, tags, updateMask, onlyActive, _callback);

    }

    /**
     * Update DAGs
     * Update DAGs of a given dag_id_pattern using UpdateMask. This endpoint allows specifying &#x60;~&#x60; as the dag_id_pattern to update all DAGs. *New in version 2.3.0* 
     * @param dagIdPattern If set, only update DAGs with dag_ids matching this pattern.  (required)
     * @param DAG  (required)
     * @param limit The numbers of items to return. (optional, default to 100)
     * @param offset The number of items to skip before starting to collect the result set. (optional)
     * @param tags List of tags to filter results.  *New in version 2.2.0*  (optional)
     * @param updateMask The fields to update on the resource. If absent or empty, all modifiable fields are updated. A comma-separated list of fully qualified names of fields.  (optional)
     * @param onlyActive Only filter active DAGs.  *New in version 2.1.1*  (optional, default to true)
     * @return DAGCollection
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
    public DAGCollection patchDags(@javax.annotation.Nonnull String dagIdPattern, @javax.annotation.Nonnull DAG DAG, @javax.annotation.Nullable Integer limit, @javax.annotation.Nullable Integer offset, @javax.annotation.Nullable List<String> tags, @javax.annotation.Nullable List<String> updateMask, @javax.annotation.Nullable Boolean onlyActive) throws ApiException {
        ApiResponse<DAGCollection> localVarResp = patchDagsWithHttpInfo(dagIdPattern, DAG, limit, offset, tags, updateMask, onlyActive);
        return localVarResp.getData();
    }

    /**
     * Update DAGs
     * Update DAGs of a given dag_id_pattern using UpdateMask. This endpoint allows specifying &#x60;~&#x60; as the dag_id_pattern to update all DAGs. *New in version 2.3.0* 
     * @param dagIdPattern If set, only update DAGs with dag_ids matching this pattern.  (required)
     * @param DAG  (required)
     * @param limit The numbers of items to return. (optional, default to 100)
     * @param offset The number of items to skip before starting to collect the result set. (optional)
     * @param tags List of tags to filter results.  *New in version 2.2.0*  (optional)
     * @param updateMask The fields to update on the resource. If absent or empty, all modifiable fields are updated. A comma-separated list of fully qualified names of fields.  (optional)
     * @param onlyActive Only filter active DAGs.  *New in version 2.1.1*  (optional, default to true)
     * @return ApiResponse&lt;DAGCollection&gt;
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
    public ApiResponse<DAGCollection> patchDagsWithHttpInfo(@javax.annotation.Nonnull String dagIdPattern, @javax.annotation.Nonnull DAG DAG, @javax.annotation.Nullable Integer limit, @javax.annotation.Nullable Integer offset, @javax.annotation.Nullable List<String> tags, @javax.annotation.Nullable List<String> updateMask, @javax.annotation.Nullable Boolean onlyActive) throws ApiException {
        okhttp3.Call localVarCall = patchDagsValidateBeforeCall(dagIdPattern, DAG, limit, offset, tags, updateMask, onlyActive, null);
        Type localVarReturnType = new TypeToken<DAGCollection>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Update DAGs (asynchronously)
     * Update DAGs of a given dag_id_pattern using UpdateMask. This endpoint allows specifying &#x60;~&#x60; as the dag_id_pattern to update all DAGs. *New in version 2.3.0* 
     * @param dagIdPattern If set, only update DAGs with dag_ids matching this pattern.  (required)
     * @param DAG  (required)
     * @param limit The numbers of items to return. (optional, default to 100)
     * @param offset The number of items to skip before starting to collect the result set. (optional)
     * @param tags List of tags to filter results.  *New in version 2.2.0*  (optional)
     * @param updateMask The fields to update on the resource. If absent or empty, all modifiable fields are updated. A comma-separated list of fully qualified names of fields.  (optional)
     * @param onlyActive Only filter active DAGs.  *New in version 2.1.1*  (optional, default to true)
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
    public okhttp3.Call patchDagsAsync(@javax.annotation.Nonnull String dagIdPattern, @javax.annotation.Nonnull DAG DAG, @javax.annotation.Nullable Integer limit, @javax.annotation.Nullable Integer offset, @javax.annotation.Nullable List<String> tags, @javax.annotation.Nullable List<String> updateMask, @javax.annotation.Nullable Boolean onlyActive, final ApiCallback<DAGCollection> _callback) throws ApiException {

        okhttp3.Call localVarCall = patchDagsValidateBeforeCall(dagIdPattern, DAG, limit, offset, tags, updateMask, onlyActive, _callback);
        Type localVarReturnType = new TypeToken<DAGCollection>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for postClearTaskInstances
     * @param dagId The DAG ID. (required)
     * @param clearTaskInstances Parameters of action (required)
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
    public okhttp3.Call postClearTaskInstancesCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull ClearTaskInstances clearTaskInstances, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = clearTaskInstances;

        // create path and map variables
        String localVarPath = "/dags/{dag_id}/clearTaskInstances"
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
    private okhttp3.Call postClearTaskInstancesValidateBeforeCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull ClearTaskInstances clearTaskInstances, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'dagId' is set
        if (dagId == null) {
            throw new ApiException("Missing the required parameter 'dagId' when calling postClearTaskInstances(Async)");
        }

        // verify the required parameter 'clearTaskInstances' is set
        if (clearTaskInstances == null) {
            throw new ApiException("Missing the required parameter 'clearTaskInstances' when calling postClearTaskInstances(Async)");
        }

        return postClearTaskInstancesCall(dagId, clearTaskInstances, _callback);

    }

    /**
     * Clear a set of task instances
     * Clears a set of task instances associated with the DAG for a specified date range. 
     * @param dagId The DAG ID. (required)
     * @param clearTaskInstances Parameters of action (required)
     * @return TaskInstanceReferenceCollection
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
    public TaskInstanceReferenceCollection postClearTaskInstances(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull ClearTaskInstances clearTaskInstances) throws ApiException {
        ApiResponse<TaskInstanceReferenceCollection> localVarResp = postClearTaskInstancesWithHttpInfo(dagId, clearTaskInstances);
        return localVarResp.getData();
    }

    /**
     * Clear a set of task instances
     * Clears a set of task instances associated with the DAG for a specified date range. 
     * @param dagId The DAG ID. (required)
     * @param clearTaskInstances Parameters of action (required)
     * @return ApiResponse&lt;TaskInstanceReferenceCollection&gt;
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
    public ApiResponse<TaskInstanceReferenceCollection> postClearTaskInstancesWithHttpInfo(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull ClearTaskInstances clearTaskInstances) throws ApiException {
        okhttp3.Call localVarCall = postClearTaskInstancesValidateBeforeCall(dagId, clearTaskInstances, null);
        Type localVarReturnType = new TypeToken<TaskInstanceReferenceCollection>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Clear a set of task instances (asynchronously)
     * Clears a set of task instances associated with the DAG for a specified date range. 
     * @param dagId The DAG ID. (required)
     * @param clearTaskInstances Parameters of action (required)
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
    public okhttp3.Call postClearTaskInstancesAsync(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull ClearTaskInstances clearTaskInstances, final ApiCallback<TaskInstanceReferenceCollection> _callback) throws ApiException {

        okhttp3.Call localVarCall = postClearTaskInstancesValidateBeforeCall(dagId, clearTaskInstances, _callback);
        Type localVarReturnType = new TypeToken<TaskInstanceReferenceCollection>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for postSetTaskInstancesState
     * @param dagId The DAG ID. (required)
     * @param updateTaskInstancesState Parameters of action (required)
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
    public okhttp3.Call postSetTaskInstancesStateCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull UpdateTaskInstancesState updateTaskInstancesState, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = updateTaskInstancesState;

        // create path and map variables
        String localVarPath = "/dags/{dag_id}/updateTaskInstancesState"
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
    private okhttp3.Call postSetTaskInstancesStateValidateBeforeCall(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull UpdateTaskInstancesState updateTaskInstancesState, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'dagId' is set
        if (dagId == null) {
            throw new ApiException("Missing the required parameter 'dagId' when calling postSetTaskInstancesState(Async)");
        }

        // verify the required parameter 'updateTaskInstancesState' is set
        if (updateTaskInstancesState == null) {
            throw new ApiException("Missing the required parameter 'updateTaskInstancesState' when calling postSetTaskInstancesState(Async)");
        }

        return postSetTaskInstancesStateCall(dagId, updateTaskInstancesState, _callback);

    }

    /**
     * Set a state of task instances
     * Updates the state for multiple task instances simultaneously. 
     * @param dagId The DAG ID. (required)
     * @param updateTaskInstancesState Parameters of action (required)
     * @return TaskInstanceReferenceCollection
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
    public TaskInstanceReferenceCollection postSetTaskInstancesState(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull UpdateTaskInstancesState updateTaskInstancesState) throws ApiException {
        ApiResponse<TaskInstanceReferenceCollection> localVarResp = postSetTaskInstancesStateWithHttpInfo(dagId, updateTaskInstancesState);
        return localVarResp.getData();
    }

    /**
     * Set a state of task instances
     * Updates the state for multiple task instances simultaneously. 
     * @param dagId The DAG ID. (required)
     * @param updateTaskInstancesState Parameters of action (required)
     * @return ApiResponse&lt;TaskInstanceReferenceCollection&gt;
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
    public ApiResponse<TaskInstanceReferenceCollection> postSetTaskInstancesStateWithHttpInfo(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull UpdateTaskInstancesState updateTaskInstancesState) throws ApiException {
        okhttp3.Call localVarCall = postSetTaskInstancesStateValidateBeforeCall(dagId, updateTaskInstancesState, null);
        Type localVarReturnType = new TypeToken<TaskInstanceReferenceCollection>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Set a state of task instances (asynchronously)
     * Updates the state for multiple task instances simultaneously. 
     * @param dagId The DAG ID. (required)
     * @param updateTaskInstancesState Parameters of action (required)
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
    public okhttp3.Call postSetTaskInstancesStateAsync(@javax.annotation.Nonnull String dagId, @javax.annotation.Nonnull UpdateTaskInstancesState updateTaskInstancesState, final ApiCallback<TaskInstanceReferenceCollection> _callback) throws ApiException {

        okhttp3.Call localVarCall = postSetTaskInstancesStateValidateBeforeCall(dagId, updateTaskInstancesState, _callback);
        Type localVarReturnType = new TypeToken<TaskInstanceReferenceCollection>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for reparseDagFile
     * @param fileToken The key containing the encrypted path to the file. Encryption and decryption take place only on the server. This prevents the client from reading an non-DAG file. This also ensures API extensibility, because the format of encrypted data may change.  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Client does not have sufficient permission. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> A specified resource is not found. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call reparseDagFileCall(@javax.annotation.Nonnull String fileToken, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/parseDagFile/{file_token}"
            .replace("{" + "file_token" + "}", localVarApiClient.escapeString(fileToken.toString()));

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
        return localVarApiClient.buildCall(basePath, localVarPath, "PUT", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call reparseDagFileValidateBeforeCall(@javax.annotation.Nonnull String fileToken, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'fileToken' is set
        if (fileToken == null) {
            throw new ApiException("Missing the required parameter 'fileToken' when calling reparseDagFile(Async)");
        }

        return reparseDagFileCall(fileToken, _callback);

    }

    /**
     * Request re-parsing of a DAG file
     * Request re-parsing of existing DAG files using a file token. 
     * @param fileToken The key containing the encrypted path to the file. Encryption and decryption take place only on the server. This prevents the client from reading an non-DAG file. This also ensures API extensibility, because the format of encrypted data may change.  (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Client does not have sufficient permission. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> A specified resource is not found. </td><td>  -  </td></tr>
     </table>
     */
    public void reparseDagFile(@javax.annotation.Nonnull String fileToken) throws ApiException {
        reparseDagFileWithHttpInfo(fileToken);
    }

    /**
     * Request re-parsing of a DAG file
     * Request re-parsing of existing DAG files using a file token. 
     * @param fileToken The key containing the encrypted path to the file. Encryption and decryption take place only on the server. This prevents the client from reading an non-DAG file. This also ensures API extensibility, because the format of encrypted data may change.  (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Client does not have sufficient permission. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> A specified resource is not found. </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Void> reparseDagFileWithHttpInfo(@javax.annotation.Nonnull String fileToken) throws ApiException {
        okhttp3.Call localVarCall = reparseDagFileValidateBeforeCall(fileToken, null);
        return localVarApiClient.execute(localVarCall);
    }

    /**
     * Request re-parsing of a DAG file (asynchronously)
     * Request re-parsing of existing DAG files using a file token. 
     * @param fileToken The key containing the encrypted path to the file. Encryption and decryption take place only on the server. This prevents the client from reading an non-DAG file. This also ensures API extensibility, because the format of encrypted data may change.  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> Success. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Request not authenticated due to missing, invalid, authentication info. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Client does not have sufficient permission. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> A specified resource is not found. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call reparseDagFileAsync(@javax.annotation.Nonnull String fileToken, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = reparseDagFileValidateBeforeCall(fileToken, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
}
