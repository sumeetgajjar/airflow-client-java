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
import org.openapitools.client.model.BasicDAGRun;
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
 * A dataset event.  *New in version 2.4.0* 
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-08-11T18:15:04.477716-07:00[America/Los_Angeles]", comments = "Generator version: 7.14.0")
public class DatasetEvent {
  public static final String SERIALIZED_NAME_DATASET_ID = "dataset_id";
  @SerializedName(SERIALIZED_NAME_DATASET_ID)
  @javax.annotation.Nullable
  private Integer datasetId;

  public static final String SERIALIZED_NAME_DATASET_URI = "dataset_uri";
  @SerializedName(SERIALIZED_NAME_DATASET_URI)
  @javax.annotation.Nullable
  private String datasetUri;

  public static final String SERIALIZED_NAME_EXTRA = "extra";
  @SerializedName(SERIALIZED_NAME_EXTRA)
  @javax.annotation.Nullable
  private Object extra;

  public static final String SERIALIZED_NAME_SOURCE_DAG_ID = "source_dag_id";
  @SerializedName(SERIALIZED_NAME_SOURCE_DAG_ID)
  @javax.annotation.Nullable
  private String sourceDagId;

  public static final String SERIALIZED_NAME_SOURCE_TASK_ID = "source_task_id";
  @SerializedName(SERIALIZED_NAME_SOURCE_TASK_ID)
  @javax.annotation.Nullable
  private String sourceTaskId;

  public static final String SERIALIZED_NAME_SOURCE_RUN_ID = "source_run_id";
  @SerializedName(SERIALIZED_NAME_SOURCE_RUN_ID)
  @javax.annotation.Nullable
  private String sourceRunId;

  public static final String SERIALIZED_NAME_SOURCE_MAP_INDEX = "source_map_index";
  @SerializedName(SERIALIZED_NAME_SOURCE_MAP_INDEX)
  @javax.annotation.Nullable
  private Integer sourceMapIndex;

  public static final String SERIALIZED_NAME_CREATED_DAGRUNS = "created_dagruns";
  @SerializedName(SERIALIZED_NAME_CREATED_DAGRUNS)
  @javax.annotation.Nullable
  private List<BasicDAGRun> createdDagruns = new ArrayList<>();

  public static final String SERIALIZED_NAME_TIMESTAMP = "timestamp";
  @SerializedName(SERIALIZED_NAME_TIMESTAMP)
  @javax.annotation.Nullable
  private String timestamp;

  public DatasetEvent() {
  }

  public DatasetEvent datasetId(@javax.annotation.Nullable Integer datasetId) {
    this.datasetId = datasetId;
    return this;
  }

  /**
   * The dataset id
   * @return datasetId
   */
  @javax.annotation.Nullable
  public Integer getDatasetId() {
    return datasetId;
  }

  public void setDatasetId(@javax.annotation.Nullable Integer datasetId) {
    this.datasetId = datasetId;
  }


  public DatasetEvent datasetUri(@javax.annotation.Nullable String datasetUri) {
    this.datasetUri = datasetUri;
    return this;
  }

  /**
   * The URI of the dataset
   * @return datasetUri
   */
  @javax.annotation.Nullable
  public String getDatasetUri() {
    return datasetUri;
  }

  public void setDatasetUri(@javax.annotation.Nullable String datasetUri) {
    this.datasetUri = datasetUri;
  }


  public DatasetEvent extra(@javax.annotation.Nullable Object extra) {
    this.extra = extra;
    return this;
  }

  /**
   * The dataset event extra
   * @return extra
   */
  @javax.annotation.Nullable
  public Object getExtra() {
    return extra;
  }

  public void setExtra(@javax.annotation.Nullable Object extra) {
    this.extra = extra;
  }


  public DatasetEvent sourceDagId(@javax.annotation.Nullable String sourceDagId) {
    this.sourceDagId = sourceDagId;
    return this;
  }

  /**
   * The DAG ID that updated the dataset.
   * @return sourceDagId
   */
  @javax.annotation.Nullable
  public String getSourceDagId() {
    return sourceDagId;
  }

  public void setSourceDagId(@javax.annotation.Nullable String sourceDagId) {
    this.sourceDagId = sourceDagId;
  }


  public DatasetEvent sourceTaskId(@javax.annotation.Nullable String sourceTaskId) {
    this.sourceTaskId = sourceTaskId;
    return this;
  }

  /**
   * The task ID that updated the dataset.
   * @return sourceTaskId
   */
  @javax.annotation.Nullable
  public String getSourceTaskId() {
    return sourceTaskId;
  }

  public void setSourceTaskId(@javax.annotation.Nullable String sourceTaskId) {
    this.sourceTaskId = sourceTaskId;
  }


  public DatasetEvent sourceRunId(@javax.annotation.Nullable String sourceRunId) {
    this.sourceRunId = sourceRunId;
    return this;
  }

  /**
   * The DAG run ID that updated the dataset.
   * @return sourceRunId
   */
  @javax.annotation.Nullable
  public String getSourceRunId() {
    return sourceRunId;
  }

  public void setSourceRunId(@javax.annotation.Nullable String sourceRunId) {
    this.sourceRunId = sourceRunId;
  }


  public DatasetEvent sourceMapIndex(@javax.annotation.Nullable Integer sourceMapIndex) {
    this.sourceMapIndex = sourceMapIndex;
    return this;
  }

  /**
   * The task map index that updated the dataset.
   * @return sourceMapIndex
   */
  @javax.annotation.Nullable
  public Integer getSourceMapIndex() {
    return sourceMapIndex;
  }

  public void setSourceMapIndex(@javax.annotation.Nullable Integer sourceMapIndex) {
    this.sourceMapIndex = sourceMapIndex;
  }


  public DatasetEvent createdDagruns(@javax.annotation.Nullable List<BasicDAGRun> createdDagruns) {
    this.createdDagruns = createdDagruns;
    return this;
  }

  public DatasetEvent addCreatedDagrunsItem(BasicDAGRun createdDagrunsItem) {
    if (this.createdDagruns == null) {
      this.createdDagruns = new ArrayList<>();
    }
    this.createdDagruns.add(createdDagrunsItem);
    return this;
  }

  /**
   * Get createdDagruns
   * @return createdDagruns
   */
  @javax.annotation.Nullable
  public List<BasicDAGRun> getCreatedDagruns() {
    return createdDagruns;
  }

  public void setCreatedDagruns(@javax.annotation.Nullable List<BasicDAGRun> createdDagruns) {
    this.createdDagruns = createdDagruns;
  }


  public DatasetEvent timestamp(@javax.annotation.Nullable String timestamp) {
    this.timestamp = timestamp;
    return this;
  }

  /**
   * The dataset event creation time
   * @return timestamp
   */
  @javax.annotation.Nullable
  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(@javax.annotation.Nullable String timestamp) {
    this.timestamp = timestamp;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DatasetEvent datasetEvent = (DatasetEvent) o;
    return Objects.equals(this.datasetId, datasetEvent.datasetId) &&
        Objects.equals(this.datasetUri, datasetEvent.datasetUri) &&
        Objects.equals(this.extra, datasetEvent.extra) &&
        Objects.equals(this.sourceDagId, datasetEvent.sourceDagId) &&
        Objects.equals(this.sourceTaskId, datasetEvent.sourceTaskId) &&
        Objects.equals(this.sourceRunId, datasetEvent.sourceRunId) &&
        Objects.equals(this.sourceMapIndex, datasetEvent.sourceMapIndex) &&
        Objects.equals(this.createdDagruns, datasetEvent.createdDagruns) &&
        Objects.equals(this.timestamp, datasetEvent.timestamp);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(datasetId, datasetUri, extra, sourceDagId, sourceTaskId, sourceRunId, sourceMapIndex, createdDagruns, timestamp);
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
    sb.append("class DatasetEvent {\n");
    sb.append("    datasetId: ").append(toIndentedString(datasetId)).append("\n");
    sb.append("    datasetUri: ").append(toIndentedString(datasetUri)).append("\n");
    sb.append("    extra: ").append(toIndentedString(extra)).append("\n");
    sb.append("    sourceDagId: ").append(toIndentedString(sourceDagId)).append("\n");
    sb.append("    sourceTaskId: ").append(toIndentedString(sourceTaskId)).append("\n");
    sb.append("    sourceRunId: ").append(toIndentedString(sourceRunId)).append("\n");
    sb.append("    sourceMapIndex: ").append(toIndentedString(sourceMapIndex)).append("\n");
    sb.append("    createdDagruns: ").append(toIndentedString(createdDagruns)).append("\n");
    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
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
    openapiFields = new HashSet<String>(Arrays.asList("dataset_id", "dataset_uri", "extra", "source_dag_id", "source_task_id", "source_run_id", "source_map_index", "created_dagruns", "timestamp"));

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>(0);
  }

  /**
   * Validates the JSON Element and throws an exception if issues found
   *
   * @param jsonElement JSON Element
   * @throws IOException if the JSON Element is invalid with respect to DatasetEvent
   */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!DatasetEvent.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in DatasetEvent is not found in the empty JSON string", DatasetEvent.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!DatasetEvent.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `DatasetEvent` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if ((jsonObj.get("dataset_uri") != null && !jsonObj.get("dataset_uri").isJsonNull()) && !jsonObj.get("dataset_uri").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `dataset_uri` to be a primitive type in the JSON string but got `%s`", jsonObj.get("dataset_uri").toString()));
      }
      if ((jsonObj.get("source_dag_id") != null && !jsonObj.get("source_dag_id").isJsonNull()) && !jsonObj.get("source_dag_id").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `source_dag_id` to be a primitive type in the JSON string but got `%s`", jsonObj.get("source_dag_id").toString()));
      }
      if ((jsonObj.get("source_task_id") != null && !jsonObj.get("source_task_id").isJsonNull()) && !jsonObj.get("source_task_id").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `source_task_id` to be a primitive type in the JSON string but got `%s`", jsonObj.get("source_task_id").toString()));
      }
      if ((jsonObj.get("source_run_id") != null && !jsonObj.get("source_run_id").isJsonNull()) && !jsonObj.get("source_run_id").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `source_run_id` to be a primitive type in the JSON string but got `%s`", jsonObj.get("source_run_id").toString()));
      }
      if (jsonObj.get("created_dagruns") != null && !jsonObj.get("created_dagruns").isJsonNull()) {
        JsonArray jsonArraycreatedDagruns = jsonObj.getAsJsonArray("created_dagruns");
        if (jsonArraycreatedDagruns != null) {
          // ensure the json data is an array
          if (!jsonObj.get("created_dagruns").isJsonArray()) {
            throw new IllegalArgumentException(String.format("Expected the field `created_dagruns` to be an array in the JSON string but got `%s`", jsonObj.get("created_dagruns").toString()));
          }

          // validate the optional field `created_dagruns` (array)
          for (int i = 0; i < jsonArraycreatedDagruns.size(); i++) {
            BasicDAGRun.validateJsonElement(jsonArraycreatedDagruns.get(i));
          };
        }
      }
      if ((jsonObj.get("timestamp") != null && !jsonObj.get("timestamp").isJsonNull()) && !jsonObj.get("timestamp").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `timestamp` to be a primitive type in the JSON string but got `%s`", jsonObj.get("timestamp").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!DatasetEvent.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'DatasetEvent' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<DatasetEvent> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(DatasetEvent.class));

       return (TypeAdapter<T>) new TypeAdapter<DatasetEvent>() {
           @Override
           public void write(JsonWriter out, DatasetEvent value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public DatasetEvent read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

  /**
   * Create an instance of DatasetEvent given an JSON string
   *
   * @param jsonString JSON string
   * @return An instance of DatasetEvent
   * @throws IOException if the JSON string is invalid with respect to DatasetEvent
   */
  public static DatasetEvent fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, DatasetEvent.class);
  }

  /**
   * Convert an instance of DatasetEvent to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

