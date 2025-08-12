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

import org.openapitools.client.ApiException;
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
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for TaskInstanceApi
 */
@Disabled
public class TaskInstanceApiTest {

    private final TaskInstanceApi api = new TaskInstanceApi();

    /**
     * List extra links
     *
     * List extra links for task instance. 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getExtraLinksTest() throws ApiException {
        String dagId = null;
        String dagRunId = null;
        String taskId = null;
        Integer mapIndex = null;
        ExtraLinkCollection response = api.getExtraLinks(dagId, dagRunId, taskId, mapIndex);
        // TODO: test validations
    }

    /**
     * Get logs
     *
     * Get logs for a specific task instance and its try number. To get log from specific character position, following way of using URLSafeSerializer can be used.  Example: &#x60;&#x60;&#x60; from itsdangerous.url_safe import URLSafeSerializer  request_url &#x3D; f\&quot;api/v1/dags/{DAG_ID}/dagRuns/{RUN_ID}/taskInstances/{TASK_ID}/logs/1\&quot; key &#x3D; app.config[\&quot;SECRET_KEY\&quot;] serializer &#x3D; URLSafeSerializer(key) token &#x3D; serializer.dumps({\&quot;log_pos\&quot;: 10000})  response &#x3D; self.client.get(     request_url,     query_string&#x3D;{\&quot;token\&quot;: token},     headers&#x3D;{\&quot;Accept\&quot;: \&quot;text/plain\&quot;},     environ_overrides&#x3D;{\&quot;REMOTE_USER\&quot;: \&quot;test\&quot;}, ) continuation_token &#x3D; response.json[\&quot;continuation_token\&quot;]     metadata &#x3D; URLSafeSerializer(key).loads(continuation_token)     log_pos &#x3D; metadata[\&quot;log_pos\&quot;]     end_of_log &#x3D; metadata[\&quot;end_of_log\&quot;] &#x60;&#x60;&#x60; If log_pos is passed as 10000 like the above example, it renders the logs starting from char position 10000 to last (not the end as the logs may be tailing behind in running state). This way pagination can be done with metadata as part of the token. 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getLogTest() throws ApiException {
        String dagId = null;
        String dagRunId = null;
        String taskId = null;
        Integer taskTryNumber = null;
        Boolean fullContent = null;
        Integer mapIndex = null;
        String token = null;
        GetLog200Response response = api.getLog(dagId, dagRunId, taskId, taskTryNumber, fullContent, mapIndex, token);
        // TODO: test validations
    }

    /**
     * Get a mapped task instance
     *
     * Get details of a mapped task instance.  *New in version 2.3.0* 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getMappedTaskInstanceTest() throws ApiException {
        String dagId = null;
        String dagRunId = null;
        String taskId = null;
        Integer mapIndex = null;
        TaskInstance response = api.getMappedTaskInstance(dagId, dagRunId, taskId, mapIndex);
        // TODO: test validations
    }

    /**
     * Get task dependencies blocking task from getting scheduled.
     *
     * Get task dependencies blocking task from getting scheduled.  *New in version 2.10.0* 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getMappedTaskInstanceDependenciesTest() throws ApiException {
        String dagId = null;
        String dagRunId = null;
        String taskId = null;
        Integer mapIndex = null;
        TaskInstanceDependencyCollection response = api.getMappedTaskInstanceDependencies(dagId, dagRunId, taskId, mapIndex);
        // TODO: test validations
    }

    /**
     * List mapped task instance tries
     *
     * Get details of all task instance tries.  *New in version 2.10.0* 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getMappedTaskInstanceTriesTest() throws ApiException {
        String dagId = null;
        String dagRunId = null;
        String taskId = null;
        Integer mapIndex = null;
        Integer limit = null;
        Integer offset = null;
        String orderBy = null;
        TaskInstanceHistoryCollection response = api.getMappedTaskInstanceTries(dagId, dagRunId, taskId, mapIndex, limit, offset, orderBy);
        // TODO: test validations
    }

    /**
     * get mapped taskinstance try
     *
     * Get details of a mapped task instance try.  *New in version 2.10.0* 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getMappedTaskInstanceTryDetailsTest() throws ApiException {
        String dagId = null;
        String dagRunId = null;
        String taskId = null;
        Integer mapIndex = null;
        Integer taskTryNumber = null;
        TaskInstanceHistory response = api.getMappedTaskInstanceTryDetails(dagId, dagRunId, taskId, mapIndex, taskTryNumber);
        // TODO: test validations
    }

    /**
     * List mapped task instances
     *
     * Get details of all mapped task instances.  *New in version 2.3.0* 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getMappedTaskInstancesTest() throws ApiException {
        String dagId = null;
        String dagRunId = null;
        String taskId = null;
        Integer limit = null;
        Integer offset = null;
        OffsetDateTime executionDateGte = null;
        OffsetDateTime executionDateLte = null;
        OffsetDateTime startDateGte = null;
        OffsetDateTime startDateLte = null;
        OffsetDateTime endDateGte = null;
        OffsetDateTime endDateLte = null;
        OffsetDateTime updatedAtGte = null;
        OffsetDateTime updatedAtLte = null;
        BigDecimal durationGte = null;
        BigDecimal durationLte = null;
        List<String> state = null;
        List<String> pool = null;
        List<String> queue = null;
        List<String> executor = null;
        String orderBy = null;
        TaskInstanceCollection response = api.getMappedTaskInstances(dagId, dagRunId, taskId, limit, offset, executionDateGte, executionDateLte, startDateGte, startDateLte, endDateGte, endDateLte, updatedAtGte, updatedAtLte, durationGte, durationLte, state, pool, queue, executor, orderBy);
        // TODO: test validations
    }

    /**
     * Get a task instance
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getTaskInstanceTest() throws ApiException {
        String dagId = null;
        String dagRunId = null;
        String taskId = null;
        TaskInstance response = api.getTaskInstance(dagId, dagRunId, taskId);
        // TODO: test validations
    }

    /**
     * Get task dependencies blocking task from getting scheduled.
     *
     * Get task dependencies blocking task from getting scheduled.  *New in version 2.10.0* 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getTaskInstanceDependenciesTest() throws ApiException {
        String dagId = null;
        String dagRunId = null;
        String taskId = null;
        TaskInstanceDependencyCollection response = api.getTaskInstanceDependencies(dagId, dagRunId, taskId);
        // TODO: test validations
    }

    /**
     * List task instance tries
     *
     * Get details of all task instance tries.  *New in version 2.10.0* 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getTaskInstanceTriesTest() throws ApiException {
        String dagId = null;
        String dagRunId = null;
        String taskId = null;
        Integer limit = null;
        Integer offset = null;
        String orderBy = null;
        TaskInstanceHistoryCollection response = api.getTaskInstanceTries(dagId, dagRunId, taskId, limit, offset, orderBy);
        // TODO: test validations
    }

    /**
     * get taskinstance try
     *
     * Get details of a task instance try.  *New in version 2.10.0* 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getTaskInstanceTryDetailsTest() throws ApiException {
        String dagId = null;
        String dagRunId = null;
        String taskId = null;
        Integer taskTryNumber = null;
        TaskInstanceHistory response = api.getTaskInstanceTryDetails(dagId, dagRunId, taskId, taskTryNumber);
        // TODO: test validations
    }

    /**
     * List task instances
     *
     * This endpoint allows specifying &#x60;~&#x60; as the dag_id, dag_run_id to retrieve DAG runs for all DAGs and DAG runs. 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getTaskInstancesTest() throws ApiException {
        String dagId = null;
        String dagRunId = null;
        OffsetDateTime executionDateGte = null;
        OffsetDateTime executionDateLte = null;
        OffsetDateTime startDateGte = null;
        OffsetDateTime startDateLte = null;
        OffsetDateTime endDateGte = null;
        OffsetDateTime endDateLte = null;
        OffsetDateTime updatedAtGte = null;
        OffsetDateTime updatedAtLte = null;
        BigDecimal durationGte = null;
        BigDecimal durationLte = null;
        List<String> state = null;
        List<String> pool = null;
        List<String> queue = null;
        List<String> executor = null;
        Integer limit = null;
        Integer offset = null;
        TaskInstanceCollection response = api.getTaskInstances(dagId, dagRunId, executionDateGte, executionDateLte, startDateGte, startDateLte, endDateGte, endDateLte, updatedAtGte, updatedAtLte, durationGte, durationLte, state, pool, queue, executor, limit, offset);
        // TODO: test validations
    }

    /**
     * List task instances (batch)
     *
     * List task instances from all DAGs and DAG runs. This endpoint is a POST to allow filtering across a large number of DAG IDs, where as a GET it would run in to maximum HTTP request URL length limits. 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getTaskInstancesBatchTest() throws ApiException {
        ListTaskInstanceForm listTaskInstanceForm = null;
        TaskInstanceCollection response = api.getTaskInstancesBatch(listTaskInstanceForm);
        // TODO: test validations
    }

    /**
     * Updates the state of a mapped task instance
     *
     * Updates the state for single mapped task instance. *New in version 2.5.0* 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void patchMappedTaskInstanceTest() throws ApiException {
        String dagId = null;
        String dagRunId = null;
        String taskId = null;
        Integer mapIndex = null;
        UpdateTaskInstance updateTaskInstance = null;
        TaskInstanceReference response = api.patchMappedTaskInstance(dagId, dagRunId, taskId, mapIndex, updateTaskInstance);
        // TODO: test validations
    }

    /**
     * Updates the state of a task instance
     *
     * Updates the state for single task instance. *New in version 2.5.0* 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void patchTaskInstanceTest() throws ApiException {
        String dagId = null;
        String dagRunId = null;
        String taskId = null;
        UpdateTaskInstance updateTaskInstance = null;
        TaskInstanceReference response = api.patchTaskInstance(dagId, dagRunId, taskId, updateTaskInstance);
        // TODO: test validations
    }

    /**
     * Update the TaskInstance note.
     *
     * Update the manual user note of a mapped Task Instance.  *New in version 2.5.0* 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void setMappedTaskInstanceNoteTest() throws ApiException {
        String dagId = null;
        String dagRunId = null;
        String taskId = null;
        Integer mapIndex = null;
        SetTaskInstanceNote setTaskInstanceNote = null;
        TaskInstance response = api.setMappedTaskInstanceNote(dagId, dagRunId, taskId, mapIndex, setTaskInstanceNote);
        // TODO: test validations
    }

    /**
     * Update the TaskInstance note.
     *
     * Update the manual user note of a non-mapped Task Instance.  *New in version 2.5.0* 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void setTaskInstanceNoteTest() throws ApiException {
        String dagId = null;
        String dagRunId = null;
        String taskId = null;
        SetTaskInstanceNote setTaskInstanceNote = null;
        TaskInstance response = api.setTaskInstanceNote(dagId, dagRunId, taskId, setTaskInstanceNote);
        // TODO: test validations
    }

}
