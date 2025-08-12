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
import java.util.Arrays;
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
 * Job
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-08-11T18:15:04.477716-07:00[America/Los_Angeles]", comments = "Generator version: 7.14.0")
public class Job {
  public static final String SERIALIZED_NAME_ID = "id";
  @SerializedName(SERIALIZED_NAME_ID)
  @javax.annotation.Nullable
  private Integer id;

  public static final String SERIALIZED_NAME_DAG_ID = "dag_id";
  @SerializedName(SERIALIZED_NAME_DAG_ID)
  @javax.annotation.Nullable
  private String dagId;

  public static final String SERIALIZED_NAME_STATE = "state";
  @SerializedName(SERIALIZED_NAME_STATE)
  @javax.annotation.Nullable
  private String state;

  public static final String SERIALIZED_NAME_JOB_TYPE = "job_type";
  @SerializedName(SERIALIZED_NAME_JOB_TYPE)
  @javax.annotation.Nullable
  private String jobType;

  public static final String SERIALIZED_NAME_START_DATE = "start_date";
  @SerializedName(SERIALIZED_NAME_START_DATE)
  @javax.annotation.Nullable
  private String startDate;

  public static final String SERIALIZED_NAME_END_DATE = "end_date";
  @SerializedName(SERIALIZED_NAME_END_DATE)
  @javax.annotation.Nullable
  private String endDate;

  public static final String SERIALIZED_NAME_LATEST_HEARTBEAT = "latest_heartbeat";
  @SerializedName(SERIALIZED_NAME_LATEST_HEARTBEAT)
  @javax.annotation.Nullable
  private String latestHeartbeat;

  public static final String SERIALIZED_NAME_EXECUTOR_CLASS = "executor_class";
  @SerializedName(SERIALIZED_NAME_EXECUTOR_CLASS)
  @javax.annotation.Nullable
  private String executorClass;

  public static final String SERIALIZED_NAME_HOSTNAME = "hostname";
  @SerializedName(SERIALIZED_NAME_HOSTNAME)
  @javax.annotation.Nullable
  private String hostname;

  public static final String SERIALIZED_NAME_UNIXNAME = "unixname";
  @SerializedName(SERIALIZED_NAME_UNIXNAME)
  @javax.annotation.Nullable
  private String unixname;

  public Job() {
  }

  public Job id(@javax.annotation.Nullable Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   */
  @javax.annotation.Nullable
  public Integer getId() {
    return id;
  }

  public void setId(@javax.annotation.Nullable Integer id) {
    this.id = id;
  }


  public Job dagId(@javax.annotation.Nullable String dagId) {
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


  public Job state(@javax.annotation.Nullable String state) {
    this.state = state;
    return this;
  }

  /**
   * Get state
   * @return state
   */
  @javax.annotation.Nullable
  public String getState() {
    return state;
  }

  public void setState(@javax.annotation.Nullable String state) {
    this.state = state;
  }


  public Job jobType(@javax.annotation.Nullable String jobType) {
    this.jobType = jobType;
    return this;
  }

  /**
   * Get jobType
   * @return jobType
   */
  @javax.annotation.Nullable
  public String getJobType() {
    return jobType;
  }

  public void setJobType(@javax.annotation.Nullable String jobType) {
    this.jobType = jobType;
  }


  public Job startDate(@javax.annotation.Nullable String startDate) {
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


  public Job endDate(@javax.annotation.Nullable String endDate) {
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


  public Job latestHeartbeat(@javax.annotation.Nullable String latestHeartbeat) {
    this.latestHeartbeat = latestHeartbeat;
    return this;
  }

  /**
   * Get latestHeartbeat
   * @return latestHeartbeat
   */
  @javax.annotation.Nullable
  public String getLatestHeartbeat() {
    return latestHeartbeat;
  }

  public void setLatestHeartbeat(@javax.annotation.Nullable String latestHeartbeat) {
    this.latestHeartbeat = latestHeartbeat;
  }


  public Job executorClass(@javax.annotation.Nullable String executorClass) {
    this.executorClass = executorClass;
    return this;
  }

  /**
   * Get executorClass
   * @return executorClass
   */
  @javax.annotation.Nullable
  public String getExecutorClass() {
    return executorClass;
  }

  public void setExecutorClass(@javax.annotation.Nullable String executorClass) {
    this.executorClass = executorClass;
  }


  public Job hostname(@javax.annotation.Nullable String hostname) {
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


  public Job unixname(@javax.annotation.Nullable String unixname) {
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



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Job job = (Job) o;
    return Objects.equals(this.id, job.id) &&
        Objects.equals(this.dagId, job.dagId) &&
        Objects.equals(this.state, job.state) &&
        Objects.equals(this.jobType, job.jobType) &&
        Objects.equals(this.startDate, job.startDate) &&
        Objects.equals(this.endDate, job.endDate) &&
        Objects.equals(this.latestHeartbeat, job.latestHeartbeat) &&
        Objects.equals(this.executorClass, job.executorClass) &&
        Objects.equals(this.hostname, job.hostname) &&
        Objects.equals(this.unixname, job.unixname);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, dagId, state, jobType, startDate, endDate, latestHeartbeat, executorClass, hostname, unixname);
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
    sb.append("class Job {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    dagId: ").append(toIndentedString(dagId)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
    sb.append("    jobType: ").append(toIndentedString(jobType)).append("\n");
    sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
    sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
    sb.append("    latestHeartbeat: ").append(toIndentedString(latestHeartbeat)).append("\n");
    sb.append("    executorClass: ").append(toIndentedString(executorClass)).append("\n");
    sb.append("    hostname: ").append(toIndentedString(hostname)).append("\n");
    sb.append("    unixname: ").append(toIndentedString(unixname)).append("\n");
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
    openapiFields = new HashSet<String>(Arrays.asList("id", "dag_id", "state", "job_type", "start_date", "end_date", "latest_heartbeat", "executor_class", "hostname", "unixname"));

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>(0);
  }

  /**
   * Validates the JSON Element and throws an exception if issues found
   *
   * @param jsonElement JSON Element
   * @throws IOException if the JSON Element is invalid with respect to Job
   */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!Job.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in Job is not found in the empty JSON string", Job.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!Job.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `Job` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if ((jsonObj.get("dag_id") != null && !jsonObj.get("dag_id").isJsonNull()) && !jsonObj.get("dag_id").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `dag_id` to be a primitive type in the JSON string but got `%s`", jsonObj.get("dag_id").toString()));
      }
      if ((jsonObj.get("state") != null && !jsonObj.get("state").isJsonNull()) && !jsonObj.get("state").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `state` to be a primitive type in the JSON string but got `%s`", jsonObj.get("state").toString()));
      }
      if ((jsonObj.get("job_type") != null && !jsonObj.get("job_type").isJsonNull()) && !jsonObj.get("job_type").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `job_type` to be a primitive type in the JSON string but got `%s`", jsonObj.get("job_type").toString()));
      }
      if ((jsonObj.get("start_date") != null && !jsonObj.get("start_date").isJsonNull()) && !jsonObj.get("start_date").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `start_date` to be a primitive type in the JSON string but got `%s`", jsonObj.get("start_date").toString()));
      }
      if ((jsonObj.get("end_date") != null && !jsonObj.get("end_date").isJsonNull()) && !jsonObj.get("end_date").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `end_date` to be a primitive type in the JSON string but got `%s`", jsonObj.get("end_date").toString()));
      }
      if ((jsonObj.get("latest_heartbeat") != null && !jsonObj.get("latest_heartbeat").isJsonNull()) && !jsonObj.get("latest_heartbeat").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `latest_heartbeat` to be a primitive type in the JSON string but got `%s`", jsonObj.get("latest_heartbeat").toString()));
      }
      if ((jsonObj.get("executor_class") != null && !jsonObj.get("executor_class").isJsonNull()) && !jsonObj.get("executor_class").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `executor_class` to be a primitive type in the JSON string but got `%s`", jsonObj.get("executor_class").toString()));
      }
      if ((jsonObj.get("hostname") != null && !jsonObj.get("hostname").isJsonNull()) && !jsonObj.get("hostname").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `hostname` to be a primitive type in the JSON string but got `%s`", jsonObj.get("hostname").toString()));
      }
      if ((jsonObj.get("unixname") != null && !jsonObj.get("unixname").isJsonNull()) && !jsonObj.get("unixname").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `unixname` to be a primitive type in the JSON string but got `%s`", jsonObj.get("unixname").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!Job.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'Job' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<Job> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(Job.class));

       return (TypeAdapter<T>) new TypeAdapter<Job>() {
           @Override
           public void write(JsonWriter out, Job value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public Job read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

  /**
   * Create an instance of Job given an JSON string
   *
   * @param jsonString JSON string
   * @return An instance of Job
   * @throws IOException if the JSON string is invalid with respect to Job
   */
  public static Job fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, Job.class);
  }

  /**
   * Convert an instance of Job to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

