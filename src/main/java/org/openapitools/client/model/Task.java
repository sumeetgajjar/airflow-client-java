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
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.client.model.ClassReference;
import org.openapitools.client.model.DAG;
import org.openapitools.client.model.TaskExtraLinksInner;
import org.openapitools.client.model.TimeDelta;
import org.openapitools.client.model.TriggerRule;
import org.openapitools.client.model.WeightRule;
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
 * For details see: [airflow.models.baseoperator.BaseOperator](https://airflow.apache.org/docs/apache-airflow/stable/_api/airflow/models/baseoperator/index.html#airflow.models.baseoperator.BaseOperator) 
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-08-11T18:15:04.477716-07:00[America/Los_Angeles]", comments = "Generator version: 7.14.0")
public class Task {
  public static final String SERIALIZED_NAME_CLASS_REF = "class_ref";
  @SerializedName(SERIALIZED_NAME_CLASS_REF)
  @javax.annotation.Nullable
  private ClassReference classRef;

  public static final String SERIALIZED_NAME_TASK_ID = "task_id";
  @SerializedName(SERIALIZED_NAME_TASK_ID)
  @javax.annotation.Nullable
  private String taskId;

  public static final String SERIALIZED_NAME_TASK_DISPLAY_NAME = "task_display_name";
  @SerializedName(SERIALIZED_NAME_TASK_DISPLAY_NAME)
  @javax.annotation.Nullable
  private String taskDisplayName;

  public static final String SERIALIZED_NAME_OWNER = "owner";
  @SerializedName(SERIALIZED_NAME_OWNER)
  @javax.annotation.Nullable
  private String owner;

  public static final String SERIALIZED_NAME_START_DATE = "start_date";
  @SerializedName(SERIALIZED_NAME_START_DATE)
  @javax.annotation.Nullable
  private OffsetDateTime startDate;

  public static final String SERIALIZED_NAME_END_DATE = "end_date";
  @SerializedName(SERIALIZED_NAME_END_DATE)
  @javax.annotation.Nullable
  private OffsetDateTime endDate;

  public static final String SERIALIZED_NAME_TRIGGER_RULE = "trigger_rule";
  @SerializedName(SERIALIZED_NAME_TRIGGER_RULE)
  @javax.annotation.Nullable
  private TriggerRule triggerRule;

  public static final String SERIALIZED_NAME_EXTRA_LINKS = "extra_links";
  @SerializedName(SERIALIZED_NAME_EXTRA_LINKS)
  @javax.annotation.Nullable
  private List<TaskExtraLinksInner> extraLinks = new ArrayList<>();

  public static final String SERIALIZED_NAME_DEPENDS_ON_PAST = "depends_on_past";
  @SerializedName(SERIALIZED_NAME_DEPENDS_ON_PAST)
  @javax.annotation.Nullable
  private Boolean dependsOnPast;

  public static final String SERIALIZED_NAME_IS_MAPPED = "is_mapped";
  @SerializedName(SERIALIZED_NAME_IS_MAPPED)
  @javax.annotation.Nullable
  private Boolean isMapped;

  public static final String SERIALIZED_NAME_WAIT_FOR_DOWNSTREAM = "wait_for_downstream";
  @SerializedName(SERIALIZED_NAME_WAIT_FOR_DOWNSTREAM)
  @javax.annotation.Nullable
  private Boolean waitForDownstream;

  public static final String SERIALIZED_NAME_RETRIES = "retries";
  @SerializedName(SERIALIZED_NAME_RETRIES)
  @javax.annotation.Nullable
  private BigDecimal retries;

  public static final String SERIALIZED_NAME_QUEUE = "queue";
  @SerializedName(SERIALIZED_NAME_QUEUE)
  @javax.annotation.Nullable
  private String queue;

  public static final String SERIALIZED_NAME_EXECUTOR = "executor";
  @SerializedName(SERIALIZED_NAME_EXECUTOR)
  @javax.annotation.Nullable
  private String executor;

  public static final String SERIALIZED_NAME_POOL = "pool";
  @SerializedName(SERIALIZED_NAME_POOL)
  @javax.annotation.Nullable
  private String pool;

  public static final String SERIALIZED_NAME_POOL_SLOTS = "pool_slots";
  @SerializedName(SERIALIZED_NAME_POOL_SLOTS)
  @javax.annotation.Nullable
  private BigDecimal poolSlots;

  public static final String SERIALIZED_NAME_EXECUTION_TIMEOUT = "execution_timeout";
  @SerializedName(SERIALIZED_NAME_EXECUTION_TIMEOUT)
  @javax.annotation.Nullable
  private TimeDelta executionTimeout;

  public static final String SERIALIZED_NAME_RETRY_DELAY = "retry_delay";
  @SerializedName(SERIALIZED_NAME_RETRY_DELAY)
  @javax.annotation.Nullable
  private TimeDelta retryDelay;

  public static final String SERIALIZED_NAME_RETRY_EXPONENTIAL_BACKOFF = "retry_exponential_backoff";
  @SerializedName(SERIALIZED_NAME_RETRY_EXPONENTIAL_BACKOFF)
  @javax.annotation.Nullable
  private Boolean retryExponentialBackoff;

  public static final String SERIALIZED_NAME_PRIORITY_WEIGHT = "priority_weight";
  @SerializedName(SERIALIZED_NAME_PRIORITY_WEIGHT)
  @javax.annotation.Nullable
  private BigDecimal priorityWeight;

  public static final String SERIALIZED_NAME_WEIGHT_RULE = "weight_rule";
  @SerializedName(SERIALIZED_NAME_WEIGHT_RULE)
  @javax.annotation.Nullable
  private WeightRule weightRule;

  public static final String SERIALIZED_NAME_UI_COLOR = "ui_color";
  @SerializedName(SERIALIZED_NAME_UI_COLOR)
  @javax.annotation.Nullable
  private String uiColor;

  public static final String SERIALIZED_NAME_UI_FGCOLOR = "ui_fgcolor";
  @SerializedName(SERIALIZED_NAME_UI_FGCOLOR)
  @javax.annotation.Nullable
  private String uiFgcolor;

  public static final String SERIALIZED_NAME_TEMPLATE_FIELDS = "template_fields";
  @SerializedName(SERIALIZED_NAME_TEMPLATE_FIELDS)
  @javax.annotation.Nullable
  private List<String> templateFields = new ArrayList<>();

  public static final String SERIALIZED_NAME_SUB_DAG = "sub_dag";
  @SerializedName(SERIALIZED_NAME_SUB_DAG)
  @javax.annotation.Nullable
  private DAG subDag;

  public static final String SERIALIZED_NAME_DOWNSTREAM_TASK_IDS = "downstream_task_ids";
  @SerializedName(SERIALIZED_NAME_DOWNSTREAM_TASK_IDS)
  @javax.annotation.Nullable
  private List<String> downstreamTaskIds = new ArrayList<>();

  public static final String SERIALIZED_NAME_DOC_MD = "doc_md";
  @SerializedName(SERIALIZED_NAME_DOC_MD)
  @javax.annotation.Nullable
  private String docMd;

  public Task() {
  }

  public Task(
     String taskId, 
     String taskDisplayName, 
     String owner, 
     OffsetDateTime startDate, 
     OffsetDateTime endDate, 
     List<TaskExtraLinksInner> extraLinks, 
     Boolean dependsOnPast, 
     Boolean isMapped, 
     Boolean waitForDownstream, 
     BigDecimal retries, 
     String queue, 
     String executor, 
     String pool, 
     BigDecimal poolSlots, 
     Boolean retryExponentialBackoff, 
     BigDecimal priorityWeight, 
     List<String> templateFields, 
     List<String> downstreamTaskIds, 
     String docMd
  ) {
    this();
    this.taskId = taskId;
    this.taskDisplayName = taskDisplayName;
    this.owner = owner;
    this.startDate = startDate;
    this.endDate = endDate;
    this.extraLinks = extraLinks;
    this.dependsOnPast = dependsOnPast;
    this.isMapped = isMapped;
    this.waitForDownstream = waitForDownstream;
    this.retries = retries;
    this.queue = queue;
    this.executor = executor;
    this.pool = pool;
    this.poolSlots = poolSlots;
    this.retryExponentialBackoff = retryExponentialBackoff;
    this.priorityWeight = priorityWeight;
    this.templateFields = templateFields;
    this.downstreamTaskIds = downstreamTaskIds;
    this.docMd = docMd;
  }

  public Task classRef(@javax.annotation.Nullable ClassReference classRef) {
    this.classRef = classRef;
    return this;
  }

  /**
   * Get classRef
   * @return classRef
   */
  @javax.annotation.Nullable
  public ClassReference getClassRef() {
    return classRef;
  }

  public void setClassRef(@javax.annotation.Nullable ClassReference classRef) {
    this.classRef = classRef;
  }


  /**
   * Get taskId
   * @return taskId
   */
  @javax.annotation.Nullable
  public String getTaskId() {
    return taskId;
  }



  /**
   * Get taskDisplayName
   * @return taskDisplayName
   */
  @javax.annotation.Nullable
  public String getTaskDisplayName() {
    return taskDisplayName;
  }



  /**
   * Get owner
   * @return owner
   */
  @javax.annotation.Nullable
  public String getOwner() {
    return owner;
  }



  /**
   * Get startDate
   * @return startDate
   */
  @javax.annotation.Nullable
  public OffsetDateTime getStartDate() {
    return startDate;
  }



  /**
   * Get endDate
   * @return endDate
   */
  @javax.annotation.Nullable
  public OffsetDateTime getEndDate() {
    return endDate;
  }



  public Task triggerRule(@javax.annotation.Nullable TriggerRule triggerRule) {
    this.triggerRule = triggerRule;
    return this;
  }

  /**
   * Get triggerRule
   * @return triggerRule
   */
  @javax.annotation.Nullable
  public TriggerRule getTriggerRule() {
    return triggerRule;
  }

  public void setTriggerRule(@javax.annotation.Nullable TriggerRule triggerRule) {
    this.triggerRule = triggerRule;
  }


  /**
   * Get extraLinks
   * @return extraLinks
   */
  @javax.annotation.Nullable
  public List<TaskExtraLinksInner> getExtraLinks() {
    return extraLinks;
  }



  /**
   * Get dependsOnPast
   * @return dependsOnPast
   */
  @javax.annotation.Nullable
  public Boolean getDependsOnPast() {
    return dependsOnPast;
  }



  /**
   * Get isMapped
   * @return isMapped
   */
  @javax.annotation.Nullable
  public Boolean getIsMapped() {
    return isMapped;
  }



  /**
   * Get waitForDownstream
   * @return waitForDownstream
   */
  @javax.annotation.Nullable
  public Boolean getWaitForDownstream() {
    return waitForDownstream;
  }



  /**
   * Get retries
   * @return retries
   */
  @javax.annotation.Nullable
  public BigDecimal getRetries() {
    return retries;
  }



  /**
   * Get queue
   * @return queue
   */
  @javax.annotation.Nullable
  public String getQueue() {
    return queue;
  }



  /**
   * Get executor
   * @return executor
   */
  @javax.annotation.Nullable
  public String getExecutor() {
    return executor;
  }



  /**
   * Get pool
   * @return pool
   */
  @javax.annotation.Nullable
  public String getPool() {
    return pool;
  }



  /**
   * Get poolSlots
   * @return poolSlots
   */
  @javax.annotation.Nullable
  public BigDecimal getPoolSlots() {
    return poolSlots;
  }



  public Task executionTimeout(@javax.annotation.Nullable TimeDelta executionTimeout) {
    this.executionTimeout = executionTimeout;
    return this;
  }

  /**
   * Get executionTimeout
   * @return executionTimeout
   */
  @javax.annotation.Nullable
  public TimeDelta getExecutionTimeout() {
    return executionTimeout;
  }

  public void setExecutionTimeout(@javax.annotation.Nullable TimeDelta executionTimeout) {
    this.executionTimeout = executionTimeout;
  }


  public Task retryDelay(@javax.annotation.Nullable TimeDelta retryDelay) {
    this.retryDelay = retryDelay;
    return this;
  }

  /**
   * Get retryDelay
   * @return retryDelay
   */
  @javax.annotation.Nullable
  public TimeDelta getRetryDelay() {
    return retryDelay;
  }

  public void setRetryDelay(@javax.annotation.Nullable TimeDelta retryDelay) {
    this.retryDelay = retryDelay;
  }


  /**
   * Get retryExponentialBackoff
   * @return retryExponentialBackoff
   */
  @javax.annotation.Nullable
  public Boolean getRetryExponentialBackoff() {
    return retryExponentialBackoff;
  }



  /**
   * Get priorityWeight
   * @return priorityWeight
   */
  @javax.annotation.Nullable
  public BigDecimal getPriorityWeight() {
    return priorityWeight;
  }



  public Task weightRule(@javax.annotation.Nullable WeightRule weightRule) {
    this.weightRule = weightRule;
    return this;
  }

  /**
   * Get weightRule
   * @return weightRule
   */
  @javax.annotation.Nullable
  public WeightRule getWeightRule() {
    return weightRule;
  }

  public void setWeightRule(@javax.annotation.Nullable WeightRule weightRule) {
    this.weightRule = weightRule;
  }


  public Task uiColor(@javax.annotation.Nullable String uiColor) {
    this.uiColor = uiColor;
    return this;
  }

  /**
   * Color in hexadecimal notation.
   * @return uiColor
   */
  @javax.annotation.Nullable
  public String getUiColor() {
    return uiColor;
  }

  public void setUiColor(@javax.annotation.Nullable String uiColor) {
    this.uiColor = uiColor;
  }


  public Task uiFgcolor(@javax.annotation.Nullable String uiFgcolor) {
    this.uiFgcolor = uiFgcolor;
    return this;
  }

  /**
   * Color in hexadecimal notation.
   * @return uiFgcolor
   */
  @javax.annotation.Nullable
  public String getUiFgcolor() {
    return uiFgcolor;
  }

  public void setUiFgcolor(@javax.annotation.Nullable String uiFgcolor) {
    this.uiFgcolor = uiFgcolor;
  }


  /**
   * Get templateFields
   * @return templateFields
   */
  @javax.annotation.Nullable
  public List<String> getTemplateFields() {
    return templateFields;
  }



  public Task subDag(@javax.annotation.Nullable DAG subDag) {
    this.subDag = subDag;
    return this;
  }

  /**
   * Get subDag
   * @return subDag
   */
  @javax.annotation.Nullable
  public DAG getSubDag() {
    return subDag;
  }

  public void setSubDag(@javax.annotation.Nullable DAG subDag) {
    this.subDag = subDag;
  }


  /**
   * Get downstreamTaskIds
   * @return downstreamTaskIds
   */
  @javax.annotation.Nullable
  public List<String> getDownstreamTaskIds() {
    return downstreamTaskIds;
  }



  /**
   * Task documentation in markdown.  *New in version 2.10.0* 
   * @return docMd
   */
  @javax.annotation.Nullable
  public String getDocMd() {
    return docMd;
  }




  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Task task = (Task) o;
    return Objects.equals(this.classRef, task.classRef) &&
        Objects.equals(this.taskId, task.taskId) &&
        Objects.equals(this.taskDisplayName, task.taskDisplayName) &&
        Objects.equals(this.owner, task.owner) &&
        Objects.equals(this.startDate, task.startDate) &&
        Objects.equals(this.endDate, task.endDate) &&
        Objects.equals(this.triggerRule, task.triggerRule) &&
        Objects.equals(this.extraLinks, task.extraLinks) &&
        Objects.equals(this.dependsOnPast, task.dependsOnPast) &&
        Objects.equals(this.isMapped, task.isMapped) &&
        Objects.equals(this.waitForDownstream, task.waitForDownstream) &&
        Objects.equals(this.retries, task.retries) &&
        Objects.equals(this.queue, task.queue) &&
        Objects.equals(this.executor, task.executor) &&
        Objects.equals(this.pool, task.pool) &&
        Objects.equals(this.poolSlots, task.poolSlots) &&
        Objects.equals(this.executionTimeout, task.executionTimeout) &&
        Objects.equals(this.retryDelay, task.retryDelay) &&
        Objects.equals(this.retryExponentialBackoff, task.retryExponentialBackoff) &&
        Objects.equals(this.priorityWeight, task.priorityWeight) &&
        Objects.equals(this.weightRule, task.weightRule) &&
        Objects.equals(this.uiColor, task.uiColor) &&
        Objects.equals(this.uiFgcolor, task.uiFgcolor) &&
        Objects.equals(this.templateFields, task.templateFields) &&
        Objects.equals(this.subDag, task.subDag) &&
        Objects.equals(this.downstreamTaskIds, task.downstreamTaskIds) &&
        Objects.equals(this.docMd, task.docMd);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(classRef, taskId, taskDisplayName, owner, startDate, endDate, triggerRule, extraLinks, dependsOnPast, isMapped, waitForDownstream, retries, queue, executor, pool, poolSlots, executionTimeout, retryDelay, retryExponentialBackoff, priorityWeight, weightRule, uiColor, uiFgcolor, templateFields, subDag, downstreamTaskIds, docMd);
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
    sb.append("class Task {\n");
    sb.append("    classRef: ").append(toIndentedString(classRef)).append("\n");
    sb.append("    taskId: ").append(toIndentedString(taskId)).append("\n");
    sb.append("    taskDisplayName: ").append(toIndentedString(taskDisplayName)).append("\n");
    sb.append("    owner: ").append(toIndentedString(owner)).append("\n");
    sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
    sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
    sb.append("    triggerRule: ").append(toIndentedString(triggerRule)).append("\n");
    sb.append("    extraLinks: ").append(toIndentedString(extraLinks)).append("\n");
    sb.append("    dependsOnPast: ").append(toIndentedString(dependsOnPast)).append("\n");
    sb.append("    isMapped: ").append(toIndentedString(isMapped)).append("\n");
    sb.append("    waitForDownstream: ").append(toIndentedString(waitForDownstream)).append("\n");
    sb.append("    retries: ").append(toIndentedString(retries)).append("\n");
    sb.append("    queue: ").append(toIndentedString(queue)).append("\n");
    sb.append("    executor: ").append(toIndentedString(executor)).append("\n");
    sb.append("    pool: ").append(toIndentedString(pool)).append("\n");
    sb.append("    poolSlots: ").append(toIndentedString(poolSlots)).append("\n");
    sb.append("    executionTimeout: ").append(toIndentedString(executionTimeout)).append("\n");
    sb.append("    retryDelay: ").append(toIndentedString(retryDelay)).append("\n");
    sb.append("    retryExponentialBackoff: ").append(toIndentedString(retryExponentialBackoff)).append("\n");
    sb.append("    priorityWeight: ").append(toIndentedString(priorityWeight)).append("\n");
    sb.append("    weightRule: ").append(toIndentedString(weightRule)).append("\n");
    sb.append("    uiColor: ").append(toIndentedString(uiColor)).append("\n");
    sb.append("    uiFgcolor: ").append(toIndentedString(uiFgcolor)).append("\n");
    sb.append("    templateFields: ").append(toIndentedString(templateFields)).append("\n");
    sb.append("    subDag: ").append(toIndentedString(subDag)).append("\n");
    sb.append("    downstreamTaskIds: ").append(toIndentedString(downstreamTaskIds)).append("\n");
    sb.append("    docMd: ").append(toIndentedString(docMd)).append("\n");
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
    openapiFields = new HashSet<String>(Arrays.asList("class_ref", "task_id", "task_display_name", "owner", "start_date", "end_date", "trigger_rule", "extra_links", "depends_on_past", "is_mapped", "wait_for_downstream", "retries", "queue", "executor", "pool", "pool_slots", "execution_timeout", "retry_delay", "retry_exponential_backoff", "priority_weight", "weight_rule", "ui_color", "ui_fgcolor", "template_fields", "sub_dag", "downstream_task_ids", "doc_md"));

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>(0);
  }

  /**
   * Validates the JSON Element and throws an exception if issues found
   *
   * @param jsonElement JSON Element
   * @throws IOException if the JSON Element is invalid with respect to Task
   */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!Task.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in Task is not found in the empty JSON string", Task.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!Task.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `Task` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      // validate the optional field `class_ref`
      if (jsonObj.get("class_ref") != null && !jsonObj.get("class_ref").isJsonNull()) {
        ClassReference.validateJsonElement(jsonObj.get("class_ref"));
      }
      if ((jsonObj.get("task_id") != null && !jsonObj.get("task_id").isJsonNull()) && !jsonObj.get("task_id").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `task_id` to be a primitive type in the JSON string but got `%s`", jsonObj.get("task_id").toString()));
      }
      if ((jsonObj.get("task_display_name") != null && !jsonObj.get("task_display_name").isJsonNull()) && !jsonObj.get("task_display_name").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `task_display_name` to be a primitive type in the JSON string but got `%s`", jsonObj.get("task_display_name").toString()));
      }
      if ((jsonObj.get("owner") != null && !jsonObj.get("owner").isJsonNull()) && !jsonObj.get("owner").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `owner` to be a primitive type in the JSON string but got `%s`", jsonObj.get("owner").toString()));
      }
      // validate the optional field `trigger_rule`
      if (jsonObj.get("trigger_rule") != null && !jsonObj.get("trigger_rule").isJsonNull()) {
        TriggerRule.validateJsonElement(jsonObj.get("trigger_rule"));
      }
      if (jsonObj.get("extra_links") != null && !jsonObj.get("extra_links").isJsonNull()) {
        JsonArray jsonArrayextraLinks = jsonObj.getAsJsonArray("extra_links");
        if (jsonArrayextraLinks != null) {
          // ensure the json data is an array
          if (!jsonObj.get("extra_links").isJsonArray()) {
            throw new IllegalArgumentException(String.format("Expected the field `extra_links` to be an array in the JSON string but got `%s`", jsonObj.get("extra_links").toString()));
          }

          // validate the optional field `extra_links` (array)
          for (int i = 0; i < jsonArrayextraLinks.size(); i++) {
            TaskExtraLinksInner.validateJsonElement(jsonArrayextraLinks.get(i));
          };
        }
      }
      if ((jsonObj.get("queue") != null && !jsonObj.get("queue").isJsonNull()) && !jsonObj.get("queue").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `queue` to be a primitive type in the JSON string but got `%s`", jsonObj.get("queue").toString()));
      }
      if ((jsonObj.get("executor") != null && !jsonObj.get("executor").isJsonNull()) && !jsonObj.get("executor").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `executor` to be a primitive type in the JSON string but got `%s`", jsonObj.get("executor").toString()));
      }
      if ((jsonObj.get("pool") != null && !jsonObj.get("pool").isJsonNull()) && !jsonObj.get("pool").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `pool` to be a primitive type in the JSON string but got `%s`", jsonObj.get("pool").toString()));
      }
      // validate the optional field `execution_timeout`
      if (jsonObj.get("execution_timeout") != null && !jsonObj.get("execution_timeout").isJsonNull()) {
        TimeDelta.validateJsonElement(jsonObj.get("execution_timeout"));
      }
      // validate the optional field `retry_delay`
      if (jsonObj.get("retry_delay") != null && !jsonObj.get("retry_delay").isJsonNull()) {
        TimeDelta.validateJsonElement(jsonObj.get("retry_delay"));
      }
      // validate the optional field `weight_rule`
      if (jsonObj.get("weight_rule") != null && !jsonObj.get("weight_rule").isJsonNull()) {
        WeightRule.validateJsonElement(jsonObj.get("weight_rule"));
      }
      if ((jsonObj.get("ui_color") != null && !jsonObj.get("ui_color").isJsonNull()) && !jsonObj.get("ui_color").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `ui_color` to be a primitive type in the JSON string but got `%s`", jsonObj.get("ui_color").toString()));
      }
      if ((jsonObj.get("ui_fgcolor") != null && !jsonObj.get("ui_fgcolor").isJsonNull()) && !jsonObj.get("ui_fgcolor").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `ui_fgcolor` to be a primitive type in the JSON string but got `%s`", jsonObj.get("ui_fgcolor").toString()));
      }
      // ensure the optional json data is an array if present
      if (jsonObj.get("template_fields") != null && !jsonObj.get("template_fields").isJsonNull() && !jsonObj.get("template_fields").isJsonArray()) {
        throw new IllegalArgumentException(String.format("Expected the field `template_fields` to be an array in the JSON string but got `%s`", jsonObj.get("template_fields").toString()));
      }
      // validate the optional field `sub_dag`
      if (jsonObj.get("sub_dag") != null && !jsonObj.get("sub_dag").isJsonNull()) {
        DAG.validateJsonElement(jsonObj.get("sub_dag"));
      }
      // ensure the optional json data is an array if present
      if (jsonObj.get("downstream_task_ids") != null && !jsonObj.get("downstream_task_ids").isJsonNull() && !jsonObj.get("downstream_task_ids").isJsonArray()) {
        throw new IllegalArgumentException(String.format("Expected the field `downstream_task_ids` to be an array in the JSON string but got `%s`", jsonObj.get("downstream_task_ids").toString()));
      }
      if ((jsonObj.get("doc_md") != null && !jsonObj.get("doc_md").isJsonNull()) && !jsonObj.get("doc_md").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `doc_md` to be a primitive type in the JSON string but got `%s`", jsonObj.get("doc_md").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!Task.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'Task' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<Task> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(Task.class));

       return (TypeAdapter<T>) new TypeAdapter<Task>() {
           @Override
           public void write(JsonWriter out, Task value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public Task read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

  /**
   * Create an instance of Task given an JSON string
   *
   * @param jsonString JSON string
   * @return An instance of Task
   * @throws IOException if the JSON string is invalid with respect to Task
   */
  public static Task fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, Task.class);
  }

  /**
   * Convert an instance of Task to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

