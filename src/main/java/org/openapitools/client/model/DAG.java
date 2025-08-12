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
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.client.model.ScheduleInterval;
import org.openapitools.client.model.Tag;
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
 * DAG
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-08-11T18:15:04.477716-07:00[America/Los_Angeles]", comments = "Generator version: 7.14.0")
public class DAG {
  public static final String SERIALIZED_NAME_DAG_ID = "dag_id";
  @SerializedName(SERIALIZED_NAME_DAG_ID)
  @javax.annotation.Nullable
  private String dagId;

  public static final String SERIALIZED_NAME_DAG_DISPLAY_NAME = "dag_display_name";
  @SerializedName(SERIALIZED_NAME_DAG_DISPLAY_NAME)
  @javax.annotation.Nullable
  private String dagDisplayName;

  public static final String SERIALIZED_NAME_ROOT_DAG_ID = "root_dag_id";
  @SerializedName(SERIALIZED_NAME_ROOT_DAG_ID)
  @javax.annotation.Nullable
  private String rootDagId;

  public static final String SERIALIZED_NAME_IS_PAUSED = "is_paused";
  @SerializedName(SERIALIZED_NAME_IS_PAUSED)
  @javax.annotation.Nullable
  private Boolean isPaused;

  public static final String SERIALIZED_NAME_IS_ACTIVE = "is_active";
  @SerializedName(SERIALIZED_NAME_IS_ACTIVE)
  @javax.annotation.Nullable
  private Boolean isActive;

  public static final String SERIALIZED_NAME_IS_SUBDAG = "is_subdag";
  @SerializedName(SERIALIZED_NAME_IS_SUBDAG)
  @javax.annotation.Nullable
  private Boolean isSubdag;

  public static final String SERIALIZED_NAME_LAST_PARSED_TIME = "last_parsed_time";
  @SerializedName(SERIALIZED_NAME_LAST_PARSED_TIME)
  @javax.annotation.Nullable
  private OffsetDateTime lastParsedTime;

  public static final String SERIALIZED_NAME_LAST_PICKLED = "last_pickled";
  @SerializedName(SERIALIZED_NAME_LAST_PICKLED)
  @javax.annotation.Nullable
  private OffsetDateTime lastPickled;

  public static final String SERIALIZED_NAME_LAST_EXPIRED = "last_expired";
  @SerializedName(SERIALIZED_NAME_LAST_EXPIRED)
  @javax.annotation.Nullable
  private OffsetDateTime lastExpired;

  public static final String SERIALIZED_NAME_SCHEDULER_LOCK = "scheduler_lock";
  @SerializedName(SERIALIZED_NAME_SCHEDULER_LOCK)
  @javax.annotation.Nullable
  private Boolean schedulerLock;

  public static final String SERIALIZED_NAME_PICKLE_ID = "pickle_id";
  @SerializedName(SERIALIZED_NAME_PICKLE_ID)
  @javax.annotation.Nullable
  private String pickleId;

  public static final String SERIALIZED_NAME_DEFAULT_VIEW = "default_view";
  @SerializedName(SERIALIZED_NAME_DEFAULT_VIEW)
  @javax.annotation.Nullable
  private String defaultView;

  public static final String SERIALIZED_NAME_FILELOC = "fileloc";
  @SerializedName(SERIALIZED_NAME_FILELOC)
  @javax.annotation.Nullable
  private String fileloc;

  public static final String SERIALIZED_NAME_FILE_TOKEN = "file_token";
  @SerializedName(SERIALIZED_NAME_FILE_TOKEN)
  @javax.annotation.Nullable
  private String fileToken;

  public static final String SERIALIZED_NAME_OWNERS = "owners";
  @SerializedName(SERIALIZED_NAME_OWNERS)
  @javax.annotation.Nullable
  private List<String> owners = new ArrayList<>();

  public static final String SERIALIZED_NAME_DESCRIPTION = "description";
  @SerializedName(SERIALIZED_NAME_DESCRIPTION)
  @javax.annotation.Nullable
  private String description;

  public static final String SERIALIZED_NAME_SCHEDULE_INTERVAL = "schedule_interval";
  @SerializedName(SERIALIZED_NAME_SCHEDULE_INTERVAL)
  @javax.annotation.Nullable
  private ScheduleInterval scheduleInterval;

  public static final String SERIALIZED_NAME_TIMETABLE_DESCRIPTION = "timetable_description";
  @SerializedName(SERIALIZED_NAME_TIMETABLE_DESCRIPTION)
  @javax.annotation.Nullable
  private String timetableDescription;

  public static final String SERIALIZED_NAME_TAGS = "tags";
  @SerializedName(SERIALIZED_NAME_TAGS)
  @javax.annotation.Nullable
  private List<Tag> tags;

  public static final String SERIALIZED_NAME_MAX_ACTIVE_TASKS = "max_active_tasks";
  @SerializedName(SERIALIZED_NAME_MAX_ACTIVE_TASKS)
  @javax.annotation.Nullable
  private Integer maxActiveTasks;

  public static final String SERIALIZED_NAME_MAX_ACTIVE_RUNS = "max_active_runs";
  @SerializedName(SERIALIZED_NAME_MAX_ACTIVE_RUNS)
  @javax.annotation.Nullable
  private Integer maxActiveRuns;

  public static final String SERIALIZED_NAME_HAS_TASK_CONCURRENCY_LIMITS = "has_task_concurrency_limits";
  @SerializedName(SERIALIZED_NAME_HAS_TASK_CONCURRENCY_LIMITS)
  @javax.annotation.Nullable
  private Boolean hasTaskConcurrencyLimits;

  public static final String SERIALIZED_NAME_HAS_IMPORT_ERRORS = "has_import_errors";
  @SerializedName(SERIALIZED_NAME_HAS_IMPORT_ERRORS)
  @javax.annotation.Nullable
  private Boolean hasImportErrors;

  public static final String SERIALIZED_NAME_NEXT_DAGRUN = "next_dagrun";
  @SerializedName(SERIALIZED_NAME_NEXT_DAGRUN)
  @javax.annotation.Nullable
  private OffsetDateTime nextDagrun;

  public static final String SERIALIZED_NAME_NEXT_DAGRUN_DATA_INTERVAL_START = "next_dagrun_data_interval_start";
  @SerializedName(SERIALIZED_NAME_NEXT_DAGRUN_DATA_INTERVAL_START)
  @javax.annotation.Nullable
  private OffsetDateTime nextDagrunDataIntervalStart;

  public static final String SERIALIZED_NAME_NEXT_DAGRUN_DATA_INTERVAL_END = "next_dagrun_data_interval_end";
  @SerializedName(SERIALIZED_NAME_NEXT_DAGRUN_DATA_INTERVAL_END)
  @javax.annotation.Nullable
  private OffsetDateTime nextDagrunDataIntervalEnd;

  public static final String SERIALIZED_NAME_NEXT_DAGRUN_CREATE_AFTER = "next_dagrun_create_after";
  @SerializedName(SERIALIZED_NAME_NEXT_DAGRUN_CREATE_AFTER)
  @javax.annotation.Nullable
  private OffsetDateTime nextDagrunCreateAfter;

  public static final String SERIALIZED_NAME_MAX_CONSECUTIVE_FAILED_DAG_RUNS = "max_consecutive_failed_dag_runs";
  @SerializedName(SERIALIZED_NAME_MAX_CONSECUTIVE_FAILED_DAG_RUNS)
  @javax.annotation.Nullable
  private Integer maxConsecutiveFailedDagRuns;

  public DAG() {
  }

  public DAG(
     String dagId, 
     String dagDisplayName, 
     String rootDagId, 
     Boolean isActive, 
     Boolean isSubdag, 
     OffsetDateTime lastParsedTime, 
     OffsetDateTime lastPickled, 
     OffsetDateTime lastExpired, 
     Boolean schedulerLock, 
     String pickleId, 
     String defaultView, 
     String fileloc, 
     String fileToken, 
     List<String> owners, 
     String description, 
     String timetableDescription, 
     List<Tag> tags, 
     Integer maxActiveTasks, 
     Integer maxActiveRuns, 
     Boolean hasTaskConcurrencyLimits, 
     Boolean hasImportErrors, 
     OffsetDateTime nextDagrun, 
     OffsetDateTime nextDagrunDataIntervalStart, 
     OffsetDateTime nextDagrunDataIntervalEnd, 
     OffsetDateTime nextDagrunCreateAfter, 
     Integer maxConsecutiveFailedDagRuns
  ) {
    this();
    this.dagId = dagId;
    this.dagDisplayName = dagDisplayName;
    this.rootDagId = rootDagId;
    this.isActive = isActive;
    this.isSubdag = isSubdag;
    this.lastParsedTime = lastParsedTime;
    this.lastPickled = lastPickled;
    this.lastExpired = lastExpired;
    this.schedulerLock = schedulerLock;
    this.pickleId = pickleId;
    this.defaultView = defaultView;
    this.fileloc = fileloc;
    this.fileToken = fileToken;
    this.owners = owners;
    this.description = description;
    this.timetableDescription = timetableDescription;
    this.tags = tags;
    this.maxActiveTasks = maxActiveTasks;
    this.maxActiveRuns = maxActiveRuns;
    this.hasTaskConcurrencyLimits = hasTaskConcurrencyLimits;
    this.hasImportErrors = hasImportErrors;
    this.nextDagrun = nextDagrun;
    this.nextDagrunDataIntervalStart = nextDagrunDataIntervalStart;
    this.nextDagrunDataIntervalEnd = nextDagrunDataIntervalEnd;
    this.nextDagrunCreateAfter = nextDagrunCreateAfter;
    this.maxConsecutiveFailedDagRuns = maxConsecutiveFailedDagRuns;
  }

  /**
   * The ID of the DAG.
   * @return dagId
   */
  @javax.annotation.Nullable
  public String getDagId() {
    return dagId;
  }



  /**
   * Human centric display text for the DAG.  *New in version 2.9.0* 
   * @return dagDisplayName
   */
  @javax.annotation.Nullable
  public String getDagDisplayName() {
    return dagDisplayName;
  }



  /**
   * If the DAG is SubDAG then it is the top level DAG identifier. Otherwise, null.
   * @return rootDagId
   */
  @javax.annotation.Nullable
  public String getRootDagId() {
    return rootDagId;
  }



  public DAG isPaused(@javax.annotation.Nullable Boolean isPaused) {
    this.isPaused = isPaused;
    return this;
  }

  /**
   * Whether the DAG is paused.
   * @return isPaused
   */
  @javax.annotation.Nullable
  public Boolean getIsPaused() {
    return isPaused;
  }

  public void setIsPaused(@javax.annotation.Nullable Boolean isPaused) {
    this.isPaused = isPaused;
  }


  /**
   * Whether the DAG is currently seen by the scheduler(s).  *New in version 2.1.1*  *Changed in version 2.2.0*&amp;#58; Field is read-only. 
   * @return isActive
   */
  @javax.annotation.Nullable
  public Boolean getIsActive() {
    return isActive;
  }



  /**
   * Whether the DAG is SubDAG.
   * @return isSubdag
   */
  @javax.annotation.Nullable
  public Boolean getIsSubdag() {
    return isSubdag;
  }



  /**
   * The last time the DAG was parsed.  *New in version 2.3.0* 
   * @return lastParsedTime
   */
  @javax.annotation.Nullable
  public OffsetDateTime getLastParsedTime() {
    return lastParsedTime;
  }



  /**
   * The last time the DAG was pickled.  *New in version 2.3.0* 
   * @return lastPickled
   */
  @javax.annotation.Nullable
  public OffsetDateTime getLastPickled() {
    return lastPickled;
  }



  /**
   * Time when the DAG last received a refresh signal (e.g. the DAG&#39;s \&quot;refresh\&quot; button was clicked in the web UI)  *New in version 2.3.0* 
   * @return lastExpired
   */
  @javax.annotation.Nullable
  public OffsetDateTime getLastExpired() {
    return lastExpired;
  }



  /**
   * Whether (one of) the scheduler is scheduling this DAG at the moment  *New in version 2.3.0* 
   * @return schedulerLock
   */
  @javax.annotation.Nullable
  public Boolean getSchedulerLock() {
    return schedulerLock;
  }



  /**
   * Foreign key to the latest pickle_id  *New in version 2.3.0* 
   * @return pickleId
   */
  @javax.annotation.Nullable
  public String getPickleId() {
    return pickleId;
  }



  /**
   * Default view of the DAG inside the webserver  *New in version 2.3.0* 
   * @return defaultView
   */
  @javax.annotation.Nullable
  public String getDefaultView() {
    return defaultView;
  }



  /**
   * The absolute path to the file.
   * @return fileloc
   */
  @javax.annotation.Nullable
  public String getFileloc() {
    return fileloc;
  }



  /**
   * The key containing the encrypted path to the file. Encryption and decryption take place only on the server. This prevents the client from reading an non-DAG file. This also ensures API extensibility, because the format of encrypted data may change. 
   * @return fileToken
   */
  @javax.annotation.Nullable
  public String getFileToken() {
    return fileToken;
  }



  /**
   * Get owners
   * @return owners
   */
  @javax.annotation.Nullable
  public List<String> getOwners() {
    return owners;
  }



  /**
   * User-provided DAG description, which can consist of several sentences or paragraphs that describe DAG contents. 
   * @return description
   */
  @javax.annotation.Nullable
  public String getDescription() {
    return description;
  }



  public DAG scheduleInterval(@javax.annotation.Nullable ScheduleInterval scheduleInterval) {
    this.scheduleInterval = scheduleInterval;
    return this;
  }

  /**
   * Get scheduleInterval
   * @return scheduleInterval
   */
  @javax.annotation.Nullable
  public ScheduleInterval getScheduleInterval() {
    return scheduleInterval;
  }

  public void setScheduleInterval(@javax.annotation.Nullable ScheduleInterval scheduleInterval) {
    this.scheduleInterval = scheduleInterval;
  }


  /**
   * Timetable/Schedule Interval description.  *New in version 2.3.0* 
   * @return timetableDescription
   */
  @javax.annotation.Nullable
  public String getTimetableDescription() {
    return timetableDescription;
  }



  /**
   * List of tags.
   * @return tags
   */
  @javax.annotation.Nullable
  public List<Tag> getTags() {
    return tags;
  }



  /**
   * Maximum number of active tasks that can be run on the DAG  *New in version 2.3.0* 
   * @return maxActiveTasks
   */
  @javax.annotation.Nullable
  public Integer getMaxActiveTasks() {
    return maxActiveTasks;
  }



  /**
   * Maximum number of active DAG runs for the DAG  *New in version 2.3.0* 
   * @return maxActiveRuns
   */
  @javax.annotation.Nullable
  public Integer getMaxActiveRuns() {
    return maxActiveRuns;
  }



  /**
   * Whether the DAG has task concurrency limits  *New in version 2.3.0* 
   * @return hasTaskConcurrencyLimits
   */
  @javax.annotation.Nullable
  public Boolean getHasTaskConcurrencyLimits() {
    return hasTaskConcurrencyLimits;
  }



  /**
   * Whether the DAG has import errors  *New in version 2.3.0* 
   * @return hasImportErrors
   */
  @javax.annotation.Nullable
  public Boolean getHasImportErrors() {
    return hasImportErrors;
  }



  /**
   * The logical date of the next dag run.  *New in version 2.3.0* 
   * @return nextDagrun
   */
  @javax.annotation.Nullable
  public OffsetDateTime getNextDagrun() {
    return nextDagrun;
  }



  /**
   * The start of the interval of the next dag run.  *New in version 2.3.0* 
   * @return nextDagrunDataIntervalStart
   */
  @javax.annotation.Nullable
  public OffsetDateTime getNextDagrunDataIntervalStart() {
    return nextDagrunDataIntervalStart;
  }



  /**
   * The end of the interval of the next dag run.  *New in version 2.3.0* 
   * @return nextDagrunDataIntervalEnd
   */
  @javax.annotation.Nullable
  public OffsetDateTime getNextDagrunDataIntervalEnd() {
    return nextDagrunDataIntervalEnd;
  }



  /**
   * Earliest time at which this &#x60;&#x60;next_dagrun&#x60;&#x60; can be created.  *New in version 2.3.0* 
   * @return nextDagrunCreateAfter
   */
  @javax.annotation.Nullable
  public OffsetDateTime getNextDagrunCreateAfter() {
    return nextDagrunCreateAfter;
  }



  /**
   * (experimental) The maximum number of consecutive DAG failures before DAG is automatically paused.  *New in version 2.9.0* 
   * @return maxConsecutiveFailedDagRuns
   */
  @javax.annotation.Nullable
  public Integer getMaxConsecutiveFailedDagRuns() {
    return maxConsecutiveFailedDagRuns;
  }




  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DAG DAG = (DAG) o;
    return Objects.equals(this.dagId, DAG.dagId) &&
        Objects.equals(this.dagDisplayName, DAG.dagDisplayName) &&
        Objects.equals(this.rootDagId, DAG.rootDagId) &&
        Objects.equals(this.isPaused, DAG.isPaused) &&
        Objects.equals(this.isActive, DAG.isActive) &&
        Objects.equals(this.isSubdag, DAG.isSubdag) &&
        Objects.equals(this.lastParsedTime, DAG.lastParsedTime) &&
        Objects.equals(this.lastPickled, DAG.lastPickled) &&
        Objects.equals(this.lastExpired, DAG.lastExpired) &&
        Objects.equals(this.schedulerLock, DAG.schedulerLock) &&
        Objects.equals(this.pickleId, DAG.pickleId) &&
        Objects.equals(this.defaultView, DAG.defaultView) &&
        Objects.equals(this.fileloc, DAG.fileloc) &&
        Objects.equals(this.fileToken, DAG.fileToken) &&
        Objects.equals(this.owners, DAG.owners) &&
        Objects.equals(this.description, DAG.description) &&
        Objects.equals(this.scheduleInterval, DAG.scheduleInterval) &&
        Objects.equals(this.timetableDescription, DAG.timetableDescription) &&
        Objects.equals(this.tags, DAG.tags) &&
        Objects.equals(this.maxActiveTasks, DAG.maxActiveTasks) &&
        Objects.equals(this.maxActiveRuns, DAG.maxActiveRuns) &&
        Objects.equals(this.hasTaskConcurrencyLimits, DAG.hasTaskConcurrencyLimits) &&
        Objects.equals(this.hasImportErrors, DAG.hasImportErrors) &&
        Objects.equals(this.nextDagrun, DAG.nextDagrun) &&
        Objects.equals(this.nextDagrunDataIntervalStart, DAG.nextDagrunDataIntervalStart) &&
        Objects.equals(this.nextDagrunDataIntervalEnd, DAG.nextDagrunDataIntervalEnd) &&
        Objects.equals(this.nextDagrunCreateAfter, DAG.nextDagrunCreateAfter) &&
        Objects.equals(this.maxConsecutiveFailedDagRuns, DAG.maxConsecutiveFailedDagRuns);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(dagId, dagDisplayName, rootDagId, isPaused, isActive, isSubdag, lastParsedTime, lastPickled, lastExpired, schedulerLock, pickleId, defaultView, fileloc, fileToken, owners, description, scheduleInterval, timetableDescription, tags, maxActiveTasks, maxActiveRuns, hasTaskConcurrencyLimits, hasImportErrors, nextDagrun, nextDagrunDataIntervalStart, nextDagrunDataIntervalEnd, nextDagrunCreateAfter, maxConsecutiveFailedDagRuns);
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
    sb.append("class DAG {\n");
    sb.append("    dagId: ").append(toIndentedString(dagId)).append("\n");
    sb.append("    dagDisplayName: ").append(toIndentedString(dagDisplayName)).append("\n");
    sb.append("    rootDagId: ").append(toIndentedString(rootDagId)).append("\n");
    sb.append("    isPaused: ").append(toIndentedString(isPaused)).append("\n");
    sb.append("    isActive: ").append(toIndentedString(isActive)).append("\n");
    sb.append("    isSubdag: ").append(toIndentedString(isSubdag)).append("\n");
    sb.append("    lastParsedTime: ").append(toIndentedString(lastParsedTime)).append("\n");
    sb.append("    lastPickled: ").append(toIndentedString(lastPickled)).append("\n");
    sb.append("    lastExpired: ").append(toIndentedString(lastExpired)).append("\n");
    sb.append("    schedulerLock: ").append(toIndentedString(schedulerLock)).append("\n");
    sb.append("    pickleId: ").append(toIndentedString(pickleId)).append("\n");
    sb.append("    defaultView: ").append(toIndentedString(defaultView)).append("\n");
    sb.append("    fileloc: ").append(toIndentedString(fileloc)).append("\n");
    sb.append("    fileToken: ").append(toIndentedString(fileToken)).append("\n");
    sb.append("    owners: ").append(toIndentedString(owners)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    scheduleInterval: ").append(toIndentedString(scheduleInterval)).append("\n");
    sb.append("    timetableDescription: ").append(toIndentedString(timetableDescription)).append("\n");
    sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
    sb.append("    maxActiveTasks: ").append(toIndentedString(maxActiveTasks)).append("\n");
    sb.append("    maxActiveRuns: ").append(toIndentedString(maxActiveRuns)).append("\n");
    sb.append("    hasTaskConcurrencyLimits: ").append(toIndentedString(hasTaskConcurrencyLimits)).append("\n");
    sb.append("    hasImportErrors: ").append(toIndentedString(hasImportErrors)).append("\n");
    sb.append("    nextDagrun: ").append(toIndentedString(nextDagrun)).append("\n");
    sb.append("    nextDagrunDataIntervalStart: ").append(toIndentedString(nextDagrunDataIntervalStart)).append("\n");
    sb.append("    nextDagrunDataIntervalEnd: ").append(toIndentedString(nextDagrunDataIntervalEnd)).append("\n");
    sb.append("    nextDagrunCreateAfter: ").append(toIndentedString(nextDagrunCreateAfter)).append("\n");
    sb.append("    maxConsecutiveFailedDagRuns: ").append(toIndentedString(maxConsecutiveFailedDagRuns)).append("\n");
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
    openapiFields = new HashSet<String>(Arrays.asList("dag_id", "dag_display_name", "root_dag_id", "is_paused", "is_active", "is_subdag", "last_parsed_time", "last_pickled", "last_expired", "scheduler_lock", "pickle_id", "default_view", "fileloc", "file_token", "owners", "description", "schedule_interval", "timetable_description", "tags", "max_active_tasks", "max_active_runs", "has_task_concurrency_limits", "has_import_errors", "next_dagrun", "next_dagrun_data_interval_start", "next_dagrun_data_interval_end", "next_dagrun_create_after", "max_consecutive_failed_dag_runs"));

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>(0);
  }

  /**
   * Validates the JSON Element and throws an exception if issues found
   *
   * @param jsonElement JSON Element
   * @throws IOException if the JSON Element is invalid with respect to DAG
   */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!DAG.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in DAG is not found in the empty JSON string", DAG.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!DAG.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `DAG` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if ((jsonObj.get("dag_id") != null && !jsonObj.get("dag_id").isJsonNull()) && !jsonObj.get("dag_id").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `dag_id` to be a primitive type in the JSON string but got `%s`", jsonObj.get("dag_id").toString()));
      }
      if ((jsonObj.get("dag_display_name") != null && !jsonObj.get("dag_display_name").isJsonNull()) && !jsonObj.get("dag_display_name").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `dag_display_name` to be a primitive type in the JSON string but got `%s`", jsonObj.get("dag_display_name").toString()));
      }
      if ((jsonObj.get("root_dag_id") != null && !jsonObj.get("root_dag_id").isJsonNull()) && !jsonObj.get("root_dag_id").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `root_dag_id` to be a primitive type in the JSON string but got `%s`", jsonObj.get("root_dag_id").toString()));
      }
      if ((jsonObj.get("pickle_id") != null && !jsonObj.get("pickle_id").isJsonNull()) && !jsonObj.get("pickle_id").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `pickle_id` to be a primitive type in the JSON string but got `%s`", jsonObj.get("pickle_id").toString()));
      }
      if ((jsonObj.get("default_view") != null && !jsonObj.get("default_view").isJsonNull()) && !jsonObj.get("default_view").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `default_view` to be a primitive type in the JSON string but got `%s`", jsonObj.get("default_view").toString()));
      }
      if ((jsonObj.get("fileloc") != null && !jsonObj.get("fileloc").isJsonNull()) && !jsonObj.get("fileloc").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `fileloc` to be a primitive type in the JSON string but got `%s`", jsonObj.get("fileloc").toString()));
      }
      if ((jsonObj.get("file_token") != null && !jsonObj.get("file_token").isJsonNull()) && !jsonObj.get("file_token").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `file_token` to be a primitive type in the JSON string but got `%s`", jsonObj.get("file_token").toString()));
      }
      // ensure the optional json data is an array if present
      if (jsonObj.get("owners") != null && !jsonObj.get("owners").isJsonNull() && !jsonObj.get("owners").isJsonArray()) {
        throw new IllegalArgumentException(String.format("Expected the field `owners` to be an array in the JSON string but got `%s`", jsonObj.get("owners").toString()));
      }
      if ((jsonObj.get("description") != null && !jsonObj.get("description").isJsonNull()) && !jsonObj.get("description").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `description` to be a primitive type in the JSON string but got `%s`", jsonObj.get("description").toString()));
      }
      // validate the optional field `schedule_interval`
      if (jsonObj.get("schedule_interval") != null && !jsonObj.get("schedule_interval").isJsonNull()) {
        ScheduleInterval.validateJsonElement(jsonObj.get("schedule_interval"));
      }
      if ((jsonObj.get("timetable_description") != null && !jsonObj.get("timetable_description").isJsonNull()) && !jsonObj.get("timetable_description").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `timetable_description` to be a primitive type in the JSON string but got `%s`", jsonObj.get("timetable_description").toString()));
      }
      if (jsonObj.get("tags") != null && !jsonObj.get("tags").isJsonNull()) {
        JsonArray jsonArraytags = jsonObj.getAsJsonArray("tags");
        if (jsonArraytags != null) {
          // ensure the json data is an array
          if (!jsonObj.get("tags").isJsonArray()) {
            throw new IllegalArgumentException(String.format("Expected the field `tags` to be an array in the JSON string but got `%s`", jsonObj.get("tags").toString()));
          }

          // validate the optional field `tags` (array)
          for (int i = 0; i < jsonArraytags.size(); i++) {
            Tag.validateJsonElement(jsonArraytags.get(i));
          };
        }
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!DAG.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'DAG' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<DAG> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(DAG.class));

       return (TypeAdapter<T>) new TypeAdapter<DAG>() {
           @Override
           public void write(JsonWriter out, DAG value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public DAG read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

  /**
   * Create an instance of DAG given an JSON string
   *
   * @param jsonString JSON string
   * @return An instance of DAG
   * @throws IOException if the JSON string is invalid with respect to DAG
   */
  public static DAG fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, DAG.class);
  }

  /**
   * Convert an instance of DAG to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

