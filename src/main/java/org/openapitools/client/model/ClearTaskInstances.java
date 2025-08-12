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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
 * ClearTaskInstances
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-08-11T18:15:04.477716-07:00[America/Los_Angeles]", comments = "Generator version: 7.14.0")
public class ClearTaskInstances {
  public static final String SERIALIZED_NAME_DRY_RUN = "dry_run";
  @SerializedName(SERIALIZED_NAME_DRY_RUN)
  @javax.annotation.Nullable
  private Boolean dryRun = true;

  public static final String SERIALIZED_NAME_TASK_IDS = "task_ids";
  @SerializedName(SERIALIZED_NAME_TASK_IDS)
  @javax.annotation.Nullable
  private List<String> taskIds = new ArrayList<>();

  public static final String SERIALIZED_NAME_START_DATE = "start_date";
  @SerializedName(SERIALIZED_NAME_START_DATE)
  @javax.annotation.Nullable
  private String startDate;

  public static final String SERIALIZED_NAME_END_DATE = "end_date";
  @SerializedName(SERIALIZED_NAME_END_DATE)
  @javax.annotation.Nullable
  private String endDate;

  public static final String SERIALIZED_NAME_ONLY_FAILED = "only_failed";
  @SerializedName(SERIALIZED_NAME_ONLY_FAILED)
  @javax.annotation.Nullable
  private Boolean onlyFailed = true;

  public static final String SERIALIZED_NAME_ONLY_RUNNING = "only_running";
  @SerializedName(SERIALIZED_NAME_ONLY_RUNNING)
  @javax.annotation.Nullable
  private Boolean onlyRunning = false;

  public static final String SERIALIZED_NAME_INCLUDE_SUBDAGS = "include_subdags";
  @SerializedName(SERIALIZED_NAME_INCLUDE_SUBDAGS)
  @javax.annotation.Nullable
  private Boolean includeSubdags;

  public static final String SERIALIZED_NAME_INCLUDE_PARENTDAG = "include_parentdag";
  @SerializedName(SERIALIZED_NAME_INCLUDE_PARENTDAG)
  @javax.annotation.Nullable
  private Boolean includeParentdag;

  public static final String SERIALIZED_NAME_RESET_DAG_RUNS = "reset_dag_runs";
  @SerializedName(SERIALIZED_NAME_RESET_DAG_RUNS)
  @javax.annotation.Nullable
  private Boolean resetDagRuns;

  public static final String SERIALIZED_NAME_DAG_RUN_ID = "dag_run_id";
  @SerializedName(SERIALIZED_NAME_DAG_RUN_ID)
  @javax.annotation.Nullable
  private String dagRunId;

  public static final String SERIALIZED_NAME_INCLUDE_UPSTREAM = "include_upstream";
  @SerializedName(SERIALIZED_NAME_INCLUDE_UPSTREAM)
  @javax.annotation.Nullable
  private Boolean includeUpstream = false;

  public static final String SERIALIZED_NAME_INCLUDE_DOWNSTREAM = "include_downstream";
  @SerializedName(SERIALIZED_NAME_INCLUDE_DOWNSTREAM)
  @javax.annotation.Nullable
  private Boolean includeDownstream = false;

  public static final String SERIALIZED_NAME_INCLUDE_FUTURE = "include_future";
  @SerializedName(SERIALIZED_NAME_INCLUDE_FUTURE)
  @javax.annotation.Nullable
  private Boolean includeFuture = false;

  public static final String SERIALIZED_NAME_INCLUDE_PAST = "include_past";
  @SerializedName(SERIALIZED_NAME_INCLUDE_PAST)
  @javax.annotation.Nullable
  private Boolean includePast = false;

  public ClearTaskInstances() {
  }

  public ClearTaskInstances dryRun(@javax.annotation.Nullable Boolean dryRun) {
    this.dryRun = dryRun;
    return this;
  }

  /**
   * If set, don&#39;t actually run this operation. The response will contain a list of task instances planned to be cleaned, but not modified in any way. 
   * @return dryRun
   */
  @javax.annotation.Nullable
  public Boolean getDryRun() {
    return dryRun;
  }

  public void setDryRun(@javax.annotation.Nullable Boolean dryRun) {
    this.dryRun = dryRun;
  }


  public ClearTaskInstances taskIds(@javax.annotation.Nullable List<String> taskIds) {
    this.taskIds = taskIds;
    return this;
  }

  public ClearTaskInstances addTaskIdsItem(String taskIdsItem) {
    if (this.taskIds == null) {
      this.taskIds = new ArrayList<>();
    }
    this.taskIds.add(taskIdsItem);
    return this;
  }

  /**
   * A list of task ids to clear.  *New in version 2.1.0* 
   * @return taskIds
   */
  @javax.annotation.Nullable
  public List<String> getTaskIds() {
    return taskIds;
  }

  public void setTaskIds(@javax.annotation.Nullable List<String> taskIds) {
    this.taskIds = taskIds;
  }


  public ClearTaskInstances startDate(@javax.annotation.Nullable String startDate) {
    this.startDate = startDate;
    return this;
  }

  /**
   * The minimum execution date to clear.
   * @return startDate
   */
  @javax.annotation.Nullable
  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(@javax.annotation.Nullable String startDate) {
    this.startDate = startDate;
  }


  public ClearTaskInstances endDate(@javax.annotation.Nullable String endDate) {
    this.endDate = endDate;
    return this;
  }

  /**
   * The maximum execution date to clear.
   * @return endDate
   */
  @javax.annotation.Nullable
  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(@javax.annotation.Nullable String endDate) {
    this.endDate = endDate;
  }


  public ClearTaskInstances onlyFailed(@javax.annotation.Nullable Boolean onlyFailed) {
    this.onlyFailed = onlyFailed;
    return this;
  }

  /**
   * Only clear failed tasks.
   * @return onlyFailed
   */
  @javax.annotation.Nullable
  public Boolean getOnlyFailed() {
    return onlyFailed;
  }

  public void setOnlyFailed(@javax.annotation.Nullable Boolean onlyFailed) {
    this.onlyFailed = onlyFailed;
  }


  public ClearTaskInstances onlyRunning(@javax.annotation.Nullable Boolean onlyRunning) {
    this.onlyRunning = onlyRunning;
    return this;
  }

  /**
   * Only clear running tasks.
   * @return onlyRunning
   */
  @javax.annotation.Nullable
  public Boolean getOnlyRunning() {
    return onlyRunning;
  }

  public void setOnlyRunning(@javax.annotation.Nullable Boolean onlyRunning) {
    this.onlyRunning = onlyRunning;
  }


  public ClearTaskInstances includeSubdags(@javax.annotation.Nullable Boolean includeSubdags) {
    this.includeSubdags = includeSubdags;
    return this;
  }

  /**
   * Clear tasks in subdags and clear external tasks indicated by ExternalTaskMarker.
   * @return includeSubdags
   */
  @javax.annotation.Nullable
  public Boolean getIncludeSubdags() {
    return includeSubdags;
  }

  public void setIncludeSubdags(@javax.annotation.Nullable Boolean includeSubdags) {
    this.includeSubdags = includeSubdags;
  }


  public ClearTaskInstances includeParentdag(@javax.annotation.Nullable Boolean includeParentdag) {
    this.includeParentdag = includeParentdag;
    return this;
  }

  /**
   * Clear tasks in the parent dag of the subdag.
   * @return includeParentdag
   */
  @javax.annotation.Nullable
  public Boolean getIncludeParentdag() {
    return includeParentdag;
  }

  public void setIncludeParentdag(@javax.annotation.Nullable Boolean includeParentdag) {
    this.includeParentdag = includeParentdag;
  }


  public ClearTaskInstances resetDagRuns(@javax.annotation.Nullable Boolean resetDagRuns) {
    this.resetDagRuns = resetDagRuns;
    return this;
  }

  /**
   * Set state of DAG runs to RUNNING.
   * @return resetDagRuns
   */
  @javax.annotation.Nullable
  public Boolean getResetDagRuns() {
    return resetDagRuns;
  }

  public void setResetDagRuns(@javax.annotation.Nullable Boolean resetDagRuns) {
    this.resetDagRuns = resetDagRuns;
  }


  public ClearTaskInstances dagRunId(@javax.annotation.Nullable String dagRunId) {
    this.dagRunId = dagRunId;
    return this;
  }

  /**
   * The DagRun ID for this task instance
   * @return dagRunId
   */
  @javax.annotation.Nullable
  public String getDagRunId() {
    return dagRunId;
  }

  public void setDagRunId(@javax.annotation.Nullable String dagRunId) {
    this.dagRunId = dagRunId;
  }


  public ClearTaskInstances includeUpstream(@javax.annotation.Nullable Boolean includeUpstream) {
    this.includeUpstream = includeUpstream;
    return this;
  }

  /**
   * If set to true, upstream tasks are also affected.
   * @return includeUpstream
   */
  @javax.annotation.Nullable
  public Boolean getIncludeUpstream() {
    return includeUpstream;
  }

  public void setIncludeUpstream(@javax.annotation.Nullable Boolean includeUpstream) {
    this.includeUpstream = includeUpstream;
  }


  public ClearTaskInstances includeDownstream(@javax.annotation.Nullable Boolean includeDownstream) {
    this.includeDownstream = includeDownstream;
    return this;
  }

  /**
   * If set to true, downstream tasks are also affected.
   * @return includeDownstream
   */
  @javax.annotation.Nullable
  public Boolean getIncludeDownstream() {
    return includeDownstream;
  }

  public void setIncludeDownstream(@javax.annotation.Nullable Boolean includeDownstream) {
    this.includeDownstream = includeDownstream;
  }


  public ClearTaskInstances includeFuture(@javax.annotation.Nullable Boolean includeFuture) {
    this.includeFuture = includeFuture;
    return this;
  }

  /**
   * If set to True, also tasks from future DAG Runs are affected.
   * @return includeFuture
   */
  @javax.annotation.Nullable
  public Boolean getIncludeFuture() {
    return includeFuture;
  }

  public void setIncludeFuture(@javax.annotation.Nullable Boolean includeFuture) {
    this.includeFuture = includeFuture;
  }


  public ClearTaskInstances includePast(@javax.annotation.Nullable Boolean includePast) {
    this.includePast = includePast;
    return this;
  }

  /**
   * If set to True, also tasks from past DAG Runs are affected.
   * @return includePast
   */
  @javax.annotation.Nullable
  public Boolean getIncludePast() {
    return includePast;
  }

  public void setIncludePast(@javax.annotation.Nullable Boolean includePast) {
    this.includePast = includePast;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ClearTaskInstances clearTaskInstances = (ClearTaskInstances) o;
    return Objects.equals(this.dryRun, clearTaskInstances.dryRun) &&
        Objects.equals(this.taskIds, clearTaskInstances.taskIds) &&
        Objects.equals(this.startDate, clearTaskInstances.startDate) &&
        Objects.equals(this.endDate, clearTaskInstances.endDate) &&
        Objects.equals(this.onlyFailed, clearTaskInstances.onlyFailed) &&
        Objects.equals(this.onlyRunning, clearTaskInstances.onlyRunning) &&
        Objects.equals(this.includeSubdags, clearTaskInstances.includeSubdags) &&
        Objects.equals(this.includeParentdag, clearTaskInstances.includeParentdag) &&
        Objects.equals(this.resetDagRuns, clearTaskInstances.resetDagRuns) &&
        Objects.equals(this.dagRunId, clearTaskInstances.dagRunId) &&
        Objects.equals(this.includeUpstream, clearTaskInstances.includeUpstream) &&
        Objects.equals(this.includeDownstream, clearTaskInstances.includeDownstream) &&
        Objects.equals(this.includeFuture, clearTaskInstances.includeFuture) &&
        Objects.equals(this.includePast, clearTaskInstances.includePast);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(dryRun, taskIds, startDate, endDate, onlyFailed, onlyRunning, includeSubdags, includeParentdag, resetDagRuns, dagRunId, includeUpstream, includeDownstream, includeFuture, includePast);
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
    sb.append("class ClearTaskInstances {\n");
    sb.append("    dryRun: ").append(toIndentedString(dryRun)).append("\n");
    sb.append("    taskIds: ").append(toIndentedString(taskIds)).append("\n");
    sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
    sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
    sb.append("    onlyFailed: ").append(toIndentedString(onlyFailed)).append("\n");
    sb.append("    onlyRunning: ").append(toIndentedString(onlyRunning)).append("\n");
    sb.append("    includeSubdags: ").append(toIndentedString(includeSubdags)).append("\n");
    sb.append("    includeParentdag: ").append(toIndentedString(includeParentdag)).append("\n");
    sb.append("    resetDagRuns: ").append(toIndentedString(resetDagRuns)).append("\n");
    sb.append("    dagRunId: ").append(toIndentedString(dagRunId)).append("\n");
    sb.append("    includeUpstream: ").append(toIndentedString(includeUpstream)).append("\n");
    sb.append("    includeDownstream: ").append(toIndentedString(includeDownstream)).append("\n");
    sb.append("    includeFuture: ").append(toIndentedString(includeFuture)).append("\n");
    sb.append("    includePast: ").append(toIndentedString(includePast)).append("\n");
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
    openapiFields = new HashSet<String>(Arrays.asList("dry_run", "task_ids", "start_date", "end_date", "only_failed", "only_running", "include_subdags", "include_parentdag", "reset_dag_runs", "dag_run_id", "include_upstream", "include_downstream", "include_future", "include_past"));

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>(0);
  }

  /**
   * Validates the JSON Element and throws an exception if issues found
   *
   * @param jsonElement JSON Element
   * @throws IOException if the JSON Element is invalid with respect to ClearTaskInstances
   */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!ClearTaskInstances.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in ClearTaskInstances is not found in the empty JSON string", ClearTaskInstances.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!ClearTaskInstances.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `ClearTaskInstances` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      // ensure the optional json data is an array if present
      if (jsonObj.get("task_ids") != null && !jsonObj.get("task_ids").isJsonNull() && !jsonObj.get("task_ids").isJsonArray()) {
        throw new IllegalArgumentException(String.format("Expected the field `task_ids` to be an array in the JSON string but got `%s`", jsonObj.get("task_ids").toString()));
      }
      if ((jsonObj.get("start_date") != null && !jsonObj.get("start_date").isJsonNull()) && !jsonObj.get("start_date").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `start_date` to be a primitive type in the JSON string but got `%s`", jsonObj.get("start_date").toString()));
      }
      if ((jsonObj.get("end_date") != null && !jsonObj.get("end_date").isJsonNull()) && !jsonObj.get("end_date").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `end_date` to be a primitive type in the JSON string but got `%s`", jsonObj.get("end_date").toString()));
      }
      if ((jsonObj.get("dag_run_id") != null && !jsonObj.get("dag_run_id").isJsonNull()) && !jsonObj.get("dag_run_id").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `dag_run_id` to be a primitive type in the JSON string but got `%s`", jsonObj.get("dag_run_id").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!ClearTaskInstances.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'ClearTaskInstances' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<ClearTaskInstances> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(ClearTaskInstances.class));

       return (TypeAdapter<T>) new TypeAdapter<ClearTaskInstances>() {
           @Override
           public void write(JsonWriter out, ClearTaskInstances value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public ClearTaskInstances read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

  /**
   * Create an instance of ClearTaskInstances given an JSON string
   *
   * @param jsonString JSON string
   * @return An instance of ClearTaskInstances
   * @throws IOException if the JSON string is invalid with respect to ClearTaskInstances
   */
  public static ClearTaskInstances fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, ClearTaskInstances.class);
  }

  /**
   * Convert an instance of ClearTaskInstances to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

