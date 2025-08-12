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


package org.openapitools.client.model;

import java.util.Objects;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import org.openapitools.client.model.Job;
import org.openapitools.client.model.SLAMiss;
import org.openapitools.client.model.TaskState;
import org.openapitools.client.model.Trigger;
import org.openapitools.jackson.nullable.JsonNullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openapitools.client.JSON;

/**
 * TaskInstance
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-08-11T18:15:04.477716-07:00[America/Los_Angeles]", comments = "Generator version: 7.14.0")
public class TaskInstance {
  public static final String SERIALIZED_NAME_TASK_ID = "task_id";
  @SerializedName(SERIALIZED_NAME_TASK_ID)
  @javax.annotation.Nullable
  private String taskId;

  public static final String SERIALIZED_NAME_TASK_DISPLAY_NAME = "task_display_name";
  @SerializedName(SERIALIZED_NAME_TASK_DISPLAY_NAME)
  @javax.annotation.Nullable
  private String taskDisplayName;

  public static final String SERIALIZED_NAME_DAG_ID = "dag_id";
  @SerializedName(SERIALIZED_NAME_DAG_ID)
  @javax.annotation.Nullable
  private String dagId;

  public static final String SERIALIZED_NAME_DAG_RUN_ID = "dag_run_id";
  @SerializedName(SERIALIZED_NAME_DAG_RUN_ID)
  @javax.annotation.Nullable
  private String dagRunId;

  public static final String SERIALIZED_NAME_EXECUTION_DATE = "execution_date";
  @SerializedName(SERIALIZED_NAME_EXECUTION_DATE)
  @javax.annotation.Nullable
  private String executionDate;

  public static final String SERIALIZED_NAME_START_DATE = "start_date";
  @SerializedName(SERIALIZED_NAME_START_DATE)
  @javax.annotation.Nullable
  private String startDate;

  public static final String SERIALIZED_NAME_END_DATE = "end_date";
  @SerializedName(SERIALIZED_NAME_END_DATE)
  @javax.annotation.Nullable
  private String endDate;

  public static final String SERIALIZED_NAME_DURATION = "duration";
  @SerializedName(SERIALIZED_NAME_DURATION)
  @javax.annotation.Nullable
  private BigDecimal duration;

  public static final String SERIALIZED_NAME_STATE = "state";
  @SerializedName(SERIALIZED_NAME_STATE)
  @javax.annotation.Nullable
  private TaskState state;

  public static final String SERIALIZED_NAME_TRY_NUMBER = "try_number";
  @SerializedName(SERIALIZED_NAME_TRY_NUMBER)
  @javax.annotation.Nullable
  private Integer tryNumber;

  public static final String SERIALIZED_NAME_MAP_INDEX = "map_index";
  @SerializedName(SERIALIZED_NAME_MAP_INDEX)
  @javax.annotation.Nullable
  private Integer mapIndex;

  public static final String SERIALIZED_NAME_MAX_TRIES = "max_tries";
  @SerializedName(SERIALIZED_NAME_MAX_TRIES)
  @javax.annotation.Nullable
  private Integer maxTries;

  public static final String SERIALIZED_NAME_HOSTNAME = "hostname";
  @SerializedName(SERIALIZED_NAME_HOSTNAME)
  @javax.annotation.Nullable
  private String hostname;

  public static final String SERIALIZED_NAME_UNIXNAME = "unixname";
  @SerializedName(SERIALIZED_NAME_UNIXNAME)
  @javax.annotation.Nullable
  private String unixname;

  public static final String SERIALIZED_NAME_POOL = "pool";
  @SerializedName(SERIALIZED_NAME_POOL)
  @javax.annotation.Nullable
  private String pool;

  public static final String SERIALIZED_NAME_POOL_SLOTS = "pool_slots";
  @SerializedName(SERIALIZED_NAME_POOL_SLOTS)
  @javax.annotation.Nullable
  private Integer poolSlots;

  public static final String SERIALIZED_NAME_QUEUE = "queue";
  @SerializedName(SERIALIZED_NAME_QUEUE)
  @javax.annotation.Nullable
  private String queue;

  public static final String SERIALIZED_NAME_PRIORITY_WEIGHT = "priority_weight";
  @SerializedName(SERIALIZED_NAME_PRIORITY_WEIGHT)
  @javax.annotation.Nullable
  private Integer priorityWeight;

  public static final String SERIALIZED_NAME_OPERATOR = "operator";
  @SerializedName(SERIALIZED_NAME_OPERATOR)
  @javax.annotation.Nullable
  private String operator;

  public static final String SERIALIZED_NAME_QUEUED_WHEN = "queued_when";
  @SerializedName(SERIALIZED_NAME_QUEUED_WHEN)
  @javax.annotation.Nullable
  private String queuedWhen;

  public static final String SERIALIZED_NAME_PID = "pid";
  @SerializedName(SERIALIZED_NAME_PID)
  @javax.annotation.Nullable
  private Integer pid;

  public static final String SERIALIZED_NAME_EXECUTOR = "executor";
  @SerializedName(SERIALIZED_NAME_EXECUTOR)
  @javax.annotation.Nullable
  private String executor;

  public static final String SERIALIZED_NAME_EXECUTOR_CONFIG = "executor_config";
  @SerializedName(SERIALIZED_NAME_EXECUTOR_CONFIG)
  @javax.annotation.Nullable
  private String executorConfig;

  public static final String SERIALIZED_NAME_SLA_MISS = "sla_miss";
  @SerializedName(SERIALIZED_NAME_SLA_MISS)
  @javax.annotation.Nullable
  private SLAMiss slaMiss;

  public static final String SERIALIZED_NAME_RENDERED_MAP_INDEX = "rendered_map_index";
  @SerializedName(SERIALIZED_NAME_RENDERED_MAP_INDEX)
  @javax.annotation.Nullable
  private String renderedMapIndex;

  public static final String SERIALIZED_NAME_RENDERED_FIELDS = "rendered_fields";
  @SerializedName(SERIALIZED_NAME_RENDERED_FIELDS)
  @javax.annotation.Nullable
  private Object renderedFields;

  public static final String SERIALIZED_NAME_TRIGGER = "trigger";
  @SerializedName(SERIALIZED_NAME_TRIGGER)
  @javax.annotation.Nullable
  private Trigger trigger;

  public static final String SERIALIZED_NAME_TRIGGERER_JOB = "triggerer_job";
  @SerializedName(SERIALIZED_NAME_TRIGGERER_JOB)
  @javax.annotation.Nullable
  private Job triggererJob;

  public static final String SERIALIZED_NAME_NOTE = "note";
  @SerializedName(SERIALIZED_NAME_NOTE)
  @javax.annotation.Nullable
  private String note;

  public TaskInstance() {
  }

  public TaskInstance taskId(@javax.annotation.Nullable String taskId) {
    this.taskId = taskId;
    return this;
  }

  /**
   * Get taskId
   * @return taskId
   */
  @javax.annotation.Nullable
  public String getTaskId() {
    return taskId;
  }

  public void setTaskId(@javax.annotation.Nullable String taskId) {
    this.taskId = taskId;
  }


  public TaskInstance taskDisplayName(@javax.annotation.Nullable String taskDisplayName) {
    this.taskDisplayName = taskDisplayName;
    return this;
  }

  /**
   * Human centric display text for the task.  *New in version 2.9.0* 
   * @return taskDisplayName
   */
  @javax.annotation.Nullable
  public String getTaskDisplayName() {
    return taskDisplayName;
  }

  public void setTaskDisplayName(@javax.annotation.Nullable String taskDisplayName) {
    this.taskDisplayName = taskDisplayName;
  }


  public TaskInstance dagId(@javax.annotation.Nullable String dagId) {
    this.dagId = dagId;
    return this;
  }

  /**
   * Get dagId
   * @return dagId
   */
  @javax.annotation.Nullable
  public String getDagId() {
    return dagId;
  }

  public void setDagId(@javax.annotation.Nullable String dagId) {
    this.dagId = dagId;
  }


  public TaskInstance dagRunId(@javax.annotation.Nullable String dagRunId) {
    this.dagRunId = dagRunId;
    return this;
  }

  /**
   * The DagRun ID for this task instance  *New in version 2.3.0* 
   * @return dagRunId
   */
  @javax.annotation.Nullable
  public String getDagRunId() {
    return dagRunId;
  }

  public void setDagRunId(@javax.annotation.Nullable String dagRunId) {
    this.dagRunId = dagRunId;
  }


  public TaskInstance executionDate(@javax.annotation.Nullable String executionDate) {
    this.executionDate = executionDate;
    return this;
  }

  /**
   * Get executionDate
   * @return executionDate
   */
  @javax.annotation.Nullable
  public String getExecutionDate() {
    return executionDate;
  }

  public void setExecutionDate(@javax.annotation.Nullable String executionDate) {
    this.executionDate = executionDate;
  }


  public TaskInstance startDate(@javax.annotation.Nullable String startDate) {
    this.startDate = startDate;
    return this;
  }

  /**
   * Get startDate
   * @return startDate
   */
  @javax.annotation.Nullable
  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(@javax.annotation.Nullable String startDate) {
    this.startDate = startDate;
  }


  public TaskInstance endDate(@javax.annotation.Nullable String endDate) {
    this.endDate = endDate;
    return this;
  }

  /**
   * Get endDate
   * @return endDate
   */
  @javax.annotation.Nullable
  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(@javax.annotation.Nullable String endDate) {
    this.endDate = endDate;
  }


  public TaskInstance duration(@javax.annotation.Nullable BigDecimal duration) {
    this.duration = duration;
    return this;
  }

  /**
   * Get duration
   * @return duration
   */
  @javax.annotation.Nullable
  public BigDecimal getDuration() {
    return duration;
  }

  public void setDuration(@javax.annotation.Nullable BigDecimal duration) {
    this.duration = duration;
  }


  public TaskInstance state(@javax.annotation.Nullable TaskState state) {
    this.state = state;
    return this;
  }

  /**
   * Get state
   * @return state
   */
  @javax.annotation.Nullable
  public TaskState getState() {
    return state;
  }

  public void setState(@javax.annotation.Nullable TaskState state) {
    this.state = state;
  }


  public TaskInstance tryNumber(@javax.annotation.Nullable Integer tryNumber) {
    this.tryNumber = tryNumber;
    return this;
  }

  /**
   * Get tryNumber
   * @return tryNumber
   */
  @javax.annotation.Nullable
  public Integer getTryNumber() {
    return tryNumber;
  }

  public void setTryNumber(@javax.annotation.Nullable Integer tryNumber) {
    this.tryNumber = tryNumber;
  }


  public TaskInstance mapIndex(@javax.annotation.Nullable Integer mapIndex) {
    this.mapIndex = mapIndex;
    return this;
  }

  /**
   * Get mapIndex
   * @return mapIndex
   */
  @javax.annotation.Nullable
  public Integer getMapIndex() {
    return mapIndex;
  }

  public void setMapIndex(@javax.annotation.Nullable Integer mapIndex) {
    this.mapIndex = mapIndex;
  }


  public TaskInstance maxTries(@javax.annotation.Nullable Integer maxTries) {
    this.maxTries = maxTries;
    return this;
  }

  /**
   * Get maxTries
   * @return maxTries
   */
  @javax.annotation.Nullable
  public Integer getMaxTries() {
    return maxTries;
  }

  public void setMaxTries(@javax.annotation.Nullable Integer maxTries) {
    this.maxTries = maxTries;
  }


  public TaskInstance hostname(@javax.annotation.Nullable String hostname) {
    this.hostname = hostname;
    return this;
  }

  /**
   * Get hostname
   * @return hostname
   */
  @javax.annotation.Nullable
  public String getHostname() {
    return hostname;
  }

  public void setHostname(@javax.annotation.Nullable String hostname) {
    this.hostname = hostname;
  }


  public TaskInstance unixname(@javax.annotation.Nullable String unixname) {
    this.unixname = unixname;
    return this;
  }

  /**
   * Get unixname
   * @return unixname
   */
  @javax.annotation.Nullable
  public String getUnixname() {
    return unixname;
  }

  public void setUnixname(@javax.annotation.Nullable String unixname) {
    this.unixname = unixname;
  }


  public TaskInstance pool(@javax.annotation.Nullable String pool) {
    this.pool = pool;
    return this;
  }

  /**
   * Get pool
   * @return pool
   */
  @javax.annotation.Nullable
  public String getPool() {
    return pool;
  }

  public void setPool(@javax.annotation.Nullable String pool) {
    this.pool = pool;
  }


  public TaskInstance poolSlots(@javax.annotation.Nullable Integer poolSlots) {
    this.poolSlots = poolSlots;
    return this;
  }

  /**
   * Get poolSlots
   * @return poolSlots
   */
  @javax.annotation.Nullable
  public Integer getPoolSlots() {
    return poolSlots;
  }

  public void setPoolSlots(@javax.annotation.Nullable Integer poolSlots) {
    this.poolSlots = poolSlots;
  }


  public TaskInstance queue(@javax.annotation.Nullable String queue) {
    this.queue = queue;
    return this;
  }

  /**
   * Get queue
   * @return queue
   */
  @javax.annotation.Nullable
  public String getQueue() {
    return queue;
  }

  public void setQueue(@javax.annotation.Nullable String queue) {
    this.queue = queue;
  }


  public TaskInstance priorityWeight(@javax.annotation.Nullable Integer priorityWeight) {
    this.priorityWeight = priorityWeight;
    return this;
  }

  /**
   * Get priorityWeight
   * @return priorityWeight
   */
  @javax.annotation.Nullable
  public Integer getPriorityWeight() {
    return priorityWeight;
  }

  public void setPriorityWeight(@javax.annotation.Nullable Integer priorityWeight) {
    this.priorityWeight = priorityWeight;
  }


  public TaskInstance operator(@javax.annotation.Nullable String operator) {
    this.operator = operator;
    return this;
  }

  /**
   * *Changed in version 2.1.1*&amp;#58; Field becomes nullable. 
   * @return operator
   */
  @javax.annotation.Nullable
  public String getOperator() {
    return operator;
  }

  public void setOperator(@javax.annotation.Nullable String operator) {
    this.operator = operator;
  }


  public TaskInstance queuedWhen(@javax.annotation.Nullable String queuedWhen) {
    this.queuedWhen = queuedWhen;
    return this;
  }

  /**
   * The datetime that the task enter the state QUEUE, also known as queue_at 
   * @return queuedWhen
   */
  @javax.annotation.Nullable
  public String getQueuedWhen() {
    return queuedWhen;
  }

  public void setQueuedWhen(@javax.annotation.Nullable String queuedWhen) {
    this.queuedWhen = queuedWhen;
  }


  public TaskInstance pid(@javax.annotation.Nullable Integer pid) {
    this.pid = pid;
    return this;
  }

  /**
   * Get pid
   * @return pid
   */
  @javax.annotation.Nullable
  public Integer getPid() {
    return pid;
  }

  public void setPid(@javax.annotation.Nullable Integer pid) {
    this.pid = pid;
  }


  public TaskInstance executor(@javax.annotation.Nullable String executor) {
    this.executor = executor;
    return this;
  }

  /**
   * Executor the task is configured to run on or None (which indicates the default executor)  *New in version 2.10.0* 
   * @return executor
   */
  @javax.annotation.Nullable
  public String getExecutor() {
    return executor;
  }

  public void setExecutor(@javax.annotation.Nullable String executor) {
    this.executor = executor;
  }


  public TaskInstance executorConfig(@javax.annotation.Nullable String executorConfig) {
    this.executorConfig = executorConfig;
    return this;
  }

  /**
   * Get executorConfig
   * @return executorConfig
   */
  @javax.annotation.Nullable
  public String getExecutorConfig() {
    return executorConfig;
  }

  public void setExecutorConfig(@javax.annotation.Nullable String executorConfig) {
    this.executorConfig = executorConfig;
  }


  public TaskInstance slaMiss(@javax.annotation.Nullable SLAMiss slaMiss) {
    this.slaMiss = slaMiss;
    return this;
  }

  /**
   * Get slaMiss
   * @return slaMiss
   */
  @javax.annotation.Nullable
  public SLAMiss getSlaMiss() {
    return slaMiss;
  }

  public void setSlaMiss(@javax.annotation.Nullable SLAMiss slaMiss) {
    this.slaMiss = slaMiss;
  }


  public TaskInstance renderedMapIndex(@javax.annotation.Nullable String renderedMapIndex) {
    this.renderedMapIndex = renderedMapIndex;
    return this;
  }

  /**
   * Rendered name of an expanded task instance, if the task is mapped.  *New in version 2.9.0* 
   * @return renderedMapIndex
   */
  @javax.annotation.Nullable
  public String getRenderedMapIndex() {
    return renderedMapIndex;
  }

  public void setRenderedMapIndex(@javax.annotation.Nullable String renderedMapIndex) {
    this.renderedMapIndex = renderedMapIndex;
  }


  public TaskInstance renderedFields(@javax.annotation.Nullable Object renderedFields) {
    this.renderedFields = renderedFields;
    return this;
  }

  /**
   * JSON object describing rendered fields.  *New in version 2.3.0* 
   * @return renderedFields
   */
  @javax.annotation.Nullable
  public Object getRenderedFields() {
    return renderedFields;
  }

  public void setRenderedFields(@javax.annotation.Nullable Object renderedFields) {
    this.renderedFields = renderedFields;
  }


  public TaskInstance trigger(@javax.annotation.Nullable Trigger trigger) {
    this.trigger = trigger;
    return this;
  }

  /**
   * Get trigger
   * @return trigger
   */
  @javax.annotation.Nullable
  public Trigger getTrigger() {
    return trigger;
  }

  public void setTrigger(@javax.annotation.Nullable Trigger trigger) {
    this.trigger = trigger;
  }


  public TaskInstance triggererJob(@javax.annotation.Nullable Job triggererJob) {
    this.triggererJob = triggererJob;
    return this;
  }

  /**
   * Get triggererJob
   * @return triggererJob
   */
  @javax.annotation.Nullable
  public Job getTriggererJob() {
    return triggererJob;
  }

  public void setTriggererJob(@javax.annotation.Nullable Job triggererJob) {
    this.triggererJob = triggererJob;
  }


  public TaskInstance note(@javax.annotation.Nullable String note) {
    this.note = note;
    return this;
  }

  /**
   * Contains manually entered notes by the user about the TaskInstance.  *New in version 2.5.0* 
   * @return note
   */
  @javax.annotation.Nullable
  public String getNote() {
    return note;
  }

  public void setNote(@javax.annotation.Nullable String note) {
    this.note = note;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TaskInstance taskInstance = (TaskInstance) o;
    return Objects.equals(this.taskId, taskInstance.taskId) &&
        Objects.equals(this.taskDisplayName, taskInstance.taskDisplayName) &&
        Objects.equals(this.dagId, taskInstance.dagId) &&
        Objects.equals(this.dagRunId, taskInstance.dagRunId) &&
        Objects.equals(this.executionDate, taskInstance.executionDate) &&
        Objects.equals(this.startDate, taskInstance.startDate) &&
        Objects.equals(this.endDate, taskInstance.endDate) &&
        Objects.equals(this.duration, taskInstance.duration) &&
        Objects.equals(this.state, taskInstance.state) &&
        Objects.equals(this.tryNumber, taskInstance.tryNumber) &&
        Objects.equals(this.mapIndex, taskInstance.mapIndex) &&
        Objects.equals(this.maxTries, taskInstance.maxTries) &&
        Objects.equals(this.hostname, taskInstance.hostname) &&
        Objects.equals(this.unixname, taskInstance.unixname) &&
        Objects.equals(this.pool, taskInstance.pool) &&
        Objects.equals(this.poolSlots, taskInstance.poolSlots) &&
        Objects.equals(this.queue, taskInstance.queue) &&
        Objects.equals(this.priorityWeight, taskInstance.priorityWeight) &&
        Objects.equals(this.operator, taskInstance.operator) &&
        Objects.equals(this.queuedWhen, taskInstance.queuedWhen) &&
        Objects.equals(this.pid, taskInstance.pid) &&
        Objects.equals(this.executor, taskInstance.executor) &&
        Objects.equals(this.executorConfig, taskInstance.executorConfig) &&
        Objects.equals(this.slaMiss, taskInstance.slaMiss) &&
        Objects.equals(this.renderedMapIndex, taskInstance.renderedMapIndex) &&
        Objects.equals(this.renderedFields, taskInstance.renderedFields) &&
        Objects.equals(this.trigger, taskInstance.trigger) &&
        Objects.equals(this.triggererJob, taskInstance.triggererJob) &&
        Objects.equals(this.note, taskInstance.note);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(taskId, taskDisplayName, dagId, dagRunId, executionDate, startDate, endDate, duration, state, tryNumber, mapIndex, maxTries, hostname, unixname, pool, poolSlots, queue, priorityWeight, operator, queuedWhen, pid, executor, executorConfig, slaMiss, renderedMapIndex, renderedFields, trigger, triggererJob, note);
  }

  private static <T> int hashCodeNullable(JsonNullable<T> a) {
    if (a == null) {
      return 1;
    }
    return a.isPresent() ? Arrays.deepHashCode(new Object[]{a.get()}) : 31;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TaskInstance {\n");
    sb.append("    taskId: ").append(toIndentedString(taskId)).append("\n");
    sb.append("    taskDisplayName: ").append(toIndentedString(taskDisplayName)).append("\n");
    sb.append("    dagId: ").append(toIndentedString(dagId)).append("\n");
    sb.append("    dagRunId: ").append(toIndentedString(dagRunId)).append("\n");
    sb.append("    executionDate: ").append(toIndentedString(executionDate)).append("\n");
    sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
    sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
    sb.append("    duration: ").append(toIndentedString(duration)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
    sb.append("    tryNumber: ").append(toIndentedString(tryNumber)).append("\n");
    sb.append("    mapIndex: ").append(toIndentedString(mapIndex)).append("\n");
    sb.append("    maxTries: ").append(toIndentedString(maxTries)).append("\n");
    sb.append("    hostname: ").append(toIndentedString(hostname)).append("\n");
    sb.append("    unixname: ").append(toIndentedString(unixname)).append("\n");
    sb.append("    pool: ").append(toIndentedString(pool)).append("\n");
    sb.append("    poolSlots: ").append(toIndentedString(poolSlots)).append("\n");
    sb.append("    queue: ").append(toIndentedString(queue)).append("\n");
    sb.append("    priorityWeight: ").append(toIndentedString(priorityWeight)).append("\n");
    sb.append("    operator: ").append(toIndentedString(operator)).append("\n");
    sb.append("    queuedWhen: ").append(toIndentedString(queuedWhen)).append("\n");
    sb.append("    pid: ").append(toIndentedString(pid)).append("\n");
    sb.append("    executor: ").append(toIndentedString(executor)).append("\n");
    sb.append("    executorConfig: ").append(toIndentedString(executorConfig)).append("\n");
    sb.append("    slaMiss: ").append(toIndentedString(slaMiss)).append("\n");
    sb.append("    renderedMapIndex: ").append(toIndentedString(renderedMapIndex)).append("\n");
    sb.append("    renderedFields: ").append(toIndentedString(renderedFields)).append("\n");
    sb.append("    trigger: ").append(toIndentedString(trigger)).append("\n");
    sb.append("    triggererJob: ").append(toIndentedString(triggererJob)).append("\n");
    sb.append("    note: ").append(toIndentedString(note)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }


  public static HashSet<String> openapiFields;
  public static HashSet<String> openapiRequiredFields;

  static {
    // a set of all properties/fields (JSON key names)
    openapiFields = new HashSet<String>(Arrays.asList("task_id", "task_display_name", "dag_id", "dag_run_id", "execution_date", "start_date", "end_date", "duration", "state", "try_number", "map_index", "max_tries", "hostname", "unixname", "pool", "pool_slots", "queue", "priority_weight", "operator", "queued_when", "pid", "executor", "executor_config", "sla_miss", "rendered_map_index", "rendered_fields", "trigger", "triggerer_job", "note"));

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>(0);
  }

  /**
   * Validates the JSON Element and throws an exception if issues found
   *
   * @param jsonElement JSON Element
   * @throws IOException if the JSON Element is invalid with respect to TaskInstance
   */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!TaskInstance.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in TaskInstance is not found in the empty JSON string", TaskInstance.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!TaskInstance.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `TaskInstance` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if ((jsonObj.get("task_id") != null && !jsonObj.get("task_id").isJsonNull()) && !jsonObj.get("task_id").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `task_id` to be a primitive type in the JSON string but got `%s`", jsonObj.get("task_id").toString()));
      }
      if ((jsonObj.get("task_display_name") != null && !jsonObj.get("task_display_name").isJsonNull()) && !jsonObj.get("task_display_name").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `task_display_name` to be a primitive type in the JSON string but got `%s`", jsonObj.get("task_display_name").toString()));
      }
      if ((jsonObj.get("dag_id") != null && !jsonObj.get("dag_id").isJsonNull()) && !jsonObj.get("dag_id").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `dag_id` to be a primitive type in the JSON string but got `%s`", jsonObj.get("dag_id").toString()));
      }
      if ((jsonObj.get("dag_run_id") != null && !jsonObj.get("dag_run_id").isJsonNull()) && !jsonObj.get("dag_run_id").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `dag_run_id` to be a primitive type in the JSON string but got `%s`", jsonObj.get("dag_run_id").toString()));
      }
      if ((jsonObj.get("execution_date") != null && !jsonObj.get("execution_date").isJsonNull()) && !jsonObj.get("execution_date").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `execution_date` to be a primitive type in the JSON string but got `%s`", jsonObj.get("execution_date").toString()));
      }
      if ((jsonObj.get("start_date") != null && !jsonObj.get("start_date").isJsonNull()) && !jsonObj.get("start_date").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `start_date` to be a primitive type in the JSON string but got `%s`", jsonObj.get("start_date").toString()));
      }
      if ((jsonObj.get("end_date") != null && !jsonObj.get("end_date").isJsonNull()) && !jsonObj.get("end_date").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `end_date` to be a primitive type in the JSON string but got `%s`", jsonObj.get("end_date").toString()));
      }
      // validate the optional field `state`
      if (jsonObj.get("state") != null && !jsonObj.get("state").isJsonNull()) {
        TaskState.validateJsonElement(jsonObj.get("state"));
      }
      if ((jsonObj.get("hostname") != null && !jsonObj.get("hostname").isJsonNull()) && !jsonObj.get("hostname").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `hostname` to be a primitive type in the JSON string but got `%s`", jsonObj.get("hostname").toString()));
      }
      if ((jsonObj.get("unixname") != null && !jsonObj.get("unixname").isJsonNull()) && !jsonObj.get("unixname").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `unixname` to be a primitive type in the JSON string but got `%s`", jsonObj.get("unixname").toString()));
      }
      if ((jsonObj.get("pool") != null && !jsonObj.get("pool").isJsonNull()) && !jsonObj.get("pool").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `pool` to be a primitive type in the JSON string but got `%s`", jsonObj.get("pool").toString()));
      }
      if ((jsonObj.get("queue") != null && !jsonObj.get("queue").isJsonNull()) && !jsonObj.get("queue").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `queue` to be a primitive type in the JSON string but got `%s`", jsonObj.get("queue").toString()));
      }
      if ((jsonObj.get("operator") != null && !jsonObj.get("operator").isJsonNull()) && !jsonObj.get("operator").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `operator` to be a primitive type in the JSON string but got `%s`", jsonObj.get("operator").toString()));
      }
      if ((jsonObj.get("queued_when") != null && !jsonObj.get("queued_when").isJsonNull()) && !jsonObj.get("queued_when").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `queued_when` to be a primitive type in the JSON string but got `%s`", jsonObj.get("queued_when").toString()));
      }
      if ((jsonObj.get("executor") != null && !jsonObj.get("executor").isJsonNull()) && !jsonObj.get("executor").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `executor` to be a primitive type in the JSON string but got `%s`", jsonObj.get("executor").toString()));
      }
      if ((jsonObj.get("executor_config") != null && !jsonObj.get("executor_config").isJsonNull()) && !jsonObj.get("executor_config").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `executor_config` to be a primitive type in the JSON string but got `%s`", jsonObj.get("executor_config").toString()));
      }
      // validate the optional field `sla_miss`
      if (jsonObj.get("sla_miss") != null && !jsonObj.get("sla_miss").isJsonNull()) {
        SLAMiss.validateJsonElement(jsonObj.get("sla_miss"));
      }
      if ((jsonObj.get("rendered_map_index") != null && !jsonObj.get("rendered_map_index").isJsonNull()) && !jsonObj.get("rendered_map_index").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `rendered_map_index` to be a primitive type in the JSON string but got `%s`", jsonObj.get("rendered_map_index").toString()));
      }
      // validate the optional field `trigger`
      if (jsonObj.get("trigger") != null && !jsonObj.get("trigger").isJsonNull()) {
        Trigger.validateJsonElement(jsonObj.get("trigger"));
      }
      // validate the optional field `triggerer_job`
      if (jsonObj.get("triggerer_job") != null && !jsonObj.get("triggerer_job").isJsonNull()) {
        Job.validateJsonElement(jsonObj.get("triggerer_job"));
      }
      if ((jsonObj.get("note") != null && !jsonObj.get("note").isJsonNull()) && !jsonObj.get("note").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `note` to be a primitive type in the JSON string but got `%s`", jsonObj.get("note").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!TaskInstance.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'TaskInstance' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<TaskInstance> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(TaskInstance.class));

       return (TypeAdapter<T>) new TypeAdapter<TaskInstance>() {
           @Override
           public void write(JsonWriter out, TaskInstance value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public TaskInstance read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

  /**
   * Create an instance of TaskInstance given an JSON string
   *
   * @param jsonString JSON string
   * @return An instance of TaskInstance
   * @throws IOException if the JSON string is invalid with respect to TaskInstance
   */
  public static TaskInstance fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, TaskInstance.class);
  }

  /**
   * Convert an instance of TaskInstance to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

