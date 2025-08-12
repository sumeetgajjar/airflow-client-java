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
 * Relative delta
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-08-11T18:15:04.477716-07:00[America/Los_Angeles]", comments = "Generator version: 7.14.0")
public class RelativeDelta {
  public static final String SERIALIZED_NAME_TYPE = "__type";
  @SerializedName(SERIALIZED_NAME_TYPE)
  @javax.annotation.Nonnull
  private String type;

  public static final String SERIALIZED_NAME_YEARS = "years";
  @SerializedName(SERIALIZED_NAME_YEARS)
  @javax.annotation.Nonnull
  private Integer years;

  public static final String SERIALIZED_NAME_MONTHS = "months";
  @SerializedName(SERIALIZED_NAME_MONTHS)
  @javax.annotation.Nonnull
  private Integer months;

  public static final String SERIALIZED_NAME_DAYS = "days";
  @SerializedName(SERIALIZED_NAME_DAYS)
  @javax.annotation.Nonnull
  private Integer days;

  public static final String SERIALIZED_NAME_LEAPDAYS = "leapdays";
  @SerializedName(SERIALIZED_NAME_LEAPDAYS)
  @javax.annotation.Nonnull
  private Integer leapdays;

  public static final String SERIALIZED_NAME_HOURS = "hours";
  @SerializedName(SERIALIZED_NAME_HOURS)
  @javax.annotation.Nonnull
  private Integer hours;

  public static final String SERIALIZED_NAME_MINUTES = "minutes";
  @SerializedName(SERIALIZED_NAME_MINUTES)
  @javax.annotation.Nonnull
  private Integer minutes;

  public static final String SERIALIZED_NAME_SECONDS = "seconds";
  @SerializedName(SERIALIZED_NAME_SECONDS)
  @javax.annotation.Nonnull
  private Integer seconds;

  public static final String SERIALIZED_NAME_MICROSECONDS = "microseconds";
  @SerializedName(SERIALIZED_NAME_MICROSECONDS)
  @javax.annotation.Nonnull
  private Integer microseconds;

  public static final String SERIALIZED_NAME_YEAR = "year";
  @SerializedName(SERIALIZED_NAME_YEAR)
  @javax.annotation.Nonnull
  private Integer year;

  public static final String SERIALIZED_NAME_MONTH = "month";
  @SerializedName(SERIALIZED_NAME_MONTH)
  @javax.annotation.Nonnull
  private Integer month;

  public static final String SERIALIZED_NAME_DAY = "day";
  @SerializedName(SERIALIZED_NAME_DAY)
  @javax.annotation.Nonnull
  private Integer day;

  public static final String SERIALIZED_NAME_HOUR = "hour";
  @SerializedName(SERIALIZED_NAME_HOUR)
  @javax.annotation.Nonnull
  private Integer hour;

  public static final String SERIALIZED_NAME_MINUTE = "minute";
  @SerializedName(SERIALIZED_NAME_MINUTE)
  @javax.annotation.Nonnull
  private Integer minute;

  public static final String SERIALIZED_NAME_SECOND = "second";
  @SerializedName(SERIALIZED_NAME_SECOND)
  @javax.annotation.Nonnull
  private Integer second;

  public static final String SERIALIZED_NAME_MICROSECOND = "microsecond";
  @SerializedName(SERIALIZED_NAME_MICROSECOND)
  @javax.annotation.Nonnull
  private Integer microsecond;

  public RelativeDelta() {
  }

  public RelativeDelta type(@javax.annotation.Nonnull String type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
   */
  @javax.annotation.Nonnull
  public String getType() {
    return type;
  }

  public void setType(@javax.annotation.Nonnull String type) {
    this.type = type;
  }


  public RelativeDelta years(@javax.annotation.Nonnull Integer years) {
    this.years = years;
    return this;
  }

  /**
   * Get years
   * @return years
   */
  @javax.annotation.Nonnull
  public Integer getYears() {
    return years;
  }

  public void setYears(@javax.annotation.Nonnull Integer years) {
    this.years = years;
  }


  public RelativeDelta months(@javax.annotation.Nonnull Integer months) {
    this.months = months;
    return this;
  }

  /**
   * Get months
   * @return months
   */
  @javax.annotation.Nonnull
  public Integer getMonths() {
    return months;
  }

  public void setMonths(@javax.annotation.Nonnull Integer months) {
    this.months = months;
  }


  public RelativeDelta days(@javax.annotation.Nonnull Integer days) {
    this.days = days;
    return this;
  }

  /**
   * Get days
   * @return days
   */
  @javax.annotation.Nonnull
  public Integer getDays() {
    return days;
  }

  public void setDays(@javax.annotation.Nonnull Integer days) {
    this.days = days;
  }


  public RelativeDelta leapdays(@javax.annotation.Nonnull Integer leapdays) {
    this.leapdays = leapdays;
    return this;
  }

  /**
   * Get leapdays
   * @return leapdays
   */
  @javax.annotation.Nonnull
  public Integer getLeapdays() {
    return leapdays;
  }

  public void setLeapdays(@javax.annotation.Nonnull Integer leapdays) {
    this.leapdays = leapdays;
  }


  public RelativeDelta hours(@javax.annotation.Nonnull Integer hours) {
    this.hours = hours;
    return this;
  }

  /**
   * Get hours
   * @return hours
   */
  @javax.annotation.Nonnull
  public Integer getHours() {
    return hours;
  }

  public void setHours(@javax.annotation.Nonnull Integer hours) {
    this.hours = hours;
  }


  public RelativeDelta minutes(@javax.annotation.Nonnull Integer minutes) {
    this.minutes = minutes;
    return this;
  }

  /**
   * Get minutes
   * @return minutes
   */
  @javax.annotation.Nonnull
  public Integer getMinutes() {
    return minutes;
  }

  public void setMinutes(@javax.annotation.Nonnull Integer minutes) {
    this.minutes = minutes;
  }


  public RelativeDelta seconds(@javax.annotation.Nonnull Integer seconds) {
    this.seconds = seconds;
    return this;
  }

  /**
   * Get seconds
   * @return seconds
   */
  @javax.annotation.Nonnull
  public Integer getSeconds() {
    return seconds;
  }

  public void setSeconds(@javax.annotation.Nonnull Integer seconds) {
    this.seconds = seconds;
  }


  public RelativeDelta microseconds(@javax.annotation.Nonnull Integer microseconds) {
    this.microseconds = microseconds;
    return this;
  }

  /**
   * Get microseconds
   * @return microseconds
   */
  @javax.annotation.Nonnull
  public Integer getMicroseconds() {
    return microseconds;
  }

  public void setMicroseconds(@javax.annotation.Nonnull Integer microseconds) {
    this.microseconds = microseconds;
  }


  public RelativeDelta year(@javax.annotation.Nonnull Integer year) {
    this.year = year;
    return this;
  }

  /**
   * Get year
   * @return year
   */
  @javax.annotation.Nonnull
  public Integer getYear() {
    return year;
  }

  public void setYear(@javax.annotation.Nonnull Integer year) {
    this.year = year;
  }


  public RelativeDelta month(@javax.annotation.Nonnull Integer month) {
    this.month = month;
    return this;
  }

  /**
   * Get month
   * @return month
   */
  @javax.annotation.Nonnull
  public Integer getMonth() {
    return month;
  }

  public void setMonth(@javax.annotation.Nonnull Integer month) {
    this.month = month;
  }


  public RelativeDelta day(@javax.annotation.Nonnull Integer day) {
    this.day = day;
    return this;
  }

  /**
   * Get day
   * @return day
   */
  @javax.annotation.Nonnull
  public Integer getDay() {
    return day;
  }

  public void setDay(@javax.annotation.Nonnull Integer day) {
    this.day = day;
  }


  public RelativeDelta hour(@javax.annotation.Nonnull Integer hour) {
    this.hour = hour;
    return this;
  }

  /**
   * Get hour
   * @return hour
   */
  @javax.annotation.Nonnull
  public Integer getHour() {
    return hour;
  }

  public void setHour(@javax.annotation.Nonnull Integer hour) {
    this.hour = hour;
  }


  public RelativeDelta minute(@javax.annotation.Nonnull Integer minute) {
    this.minute = minute;
    return this;
  }

  /**
   * Get minute
   * @return minute
   */
  @javax.annotation.Nonnull
  public Integer getMinute() {
    return minute;
  }

  public void setMinute(@javax.annotation.Nonnull Integer minute) {
    this.minute = minute;
  }


  public RelativeDelta second(@javax.annotation.Nonnull Integer second) {
    this.second = second;
    return this;
  }

  /**
   * Get second
   * @return second
   */
  @javax.annotation.Nonnull
  public Integer getSecond() {
    return second;
  }

  public void setSecond(@javax.annotation.Nonnull Integer second) {
    this.second = second;
  }


  public RelativeDelta microsecond(@javax.annotation.Nonnull Integer microsecond) {
    this.microsecond = microsecond;
    return this;
  }

  /**
   * Get microsecond
   * @return microsecond
   */
  @javax.annotation.Nonnull
  public Integer getMicrosecond() {
    return microsecond;
  }

  public void setMicrosecond(@javax.annotation.Nonnull Integer microsecond) {
    this.microsecond = microsecond;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RelativeDelta relativeDelta = (RelativeDelta) o;
    return Objects.equals(this.type, relativeDelta.type) &&
        Objects.equals(this.years, relativeDelta.years) &&
        Objects.equals(this.months, relativeDelta.months) &&
        Objects.equals(this.days, relativeDelta.days) &&
        Objects.equals(this.leapdays, relativeDelta.leapdays) &&
        Objects.equals(this.hours, relativeDelta.hours) &&
        Objects.equals(this.minutes, relativeDelta.minutes) &&
        Objects.equals(this.seconds, relativeDelta.seconds) &&
        Objects.equals(this.microseconds, relativeDelta.microseconds) &&
        Objects.equals(this.year, relativeDelta.year) &&
        Objects.equals(this.month, relativeDelta.month) &&
        Objects.equals(this.day, relativeDelta.day) &&
        Objects.equals(this.hour, relativeDelta.hour) &&
        Objects.equals(this.minute, relativeDelta.minute) &&
        Objects.equals(this.second, relativeDelta.second) &&
        Objects.equals(this.microsecond, relativeDelta.microsecond);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, years, months, days, leapdays, hours, minutes, seconds, microseconds, year, month, day, hour, minute, second, microsecond);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RelativeDelta {\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    years: ").append(toIndentedString(years)).append("\n");
    sb.append("    months: ").append(toIndentedString(months)).append("\n");
    sb.append("    days: ").append(toIndentedString(days)).append("\n");
    sb.append("    leapdays: ").append(toIndentedString(leapdays)).append("\n");
    sb.append("    hours: ").append(toIndentedString(hours)).append("\n");
    sb.append("    minutes: ").append(toIndentedString(minutes)).append("\n");
    sb.append("    seconds: ").append(toIndentedString(seconds)).append("\n");
    sb.append("    microseconds: ").append(toIndentedString(microseconds)).append("\n");
    sb.append("    year: ").append(toIndentedString(year)).append("\n");
    sb.append("    month: ").append(toIndentedString(month)).append("\n");
    sb.append("    day: ").append(toIndentedString(day)).append("\n");
    sb.append("    hour: ").append(toIndentedString(hour)).append("\n");
    sb.append("    minute: ").append(toIndentedString(minute)).append("\n");
    sb.append("    second: ").append(toIndentedString(second)).append("\n");
    sb.append("    microsecond: ").append(toIndentedString(microsecond)).append("\n");
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
    openapiFields = new HashSet<String>(Arrays.asList("__type", "years", "months", "days", "leapdays", "hours", "minutes", "seconds", "microseconds", "year", "month", "day", "hour", "minute", "second", "microsecond"));

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>(Arrays.asList("__type", "years", "months", "days", "leapdays", "hours", "minutes", "seconds", "microseconds", "year", "month", "day", "hour", "minute", "second", "microsecond"));
  }

  /**
   * Validates the JSON Element and throws an exception if issues found
   *
   * @param jsonElement JSON Element
   * @throws IOException if the JSON Element is invalid with respect to RelativeDelta
   */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!RelativeDelta.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in RelativeDelta is not found in the empty JSON string", RelativeDelta.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!RelativeDelta.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `RelativeDelta` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }

      // check to make sure all required properties/fields are present in the JSON string
      for (String requiredField : RelativeDelta.openapiRequiredFields) {
        if (jsonElement.getAsJsonObject().get(requiredField) == null) {
          throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", requiredField, jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if (!jsonObj.get("__type").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `__type` to be a primitive type in the JSON string but got `%s`", jsonObj.get("__type").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!RelativeDelta.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'RelativeDelta' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<RelativeDelta> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(RelativeDelta.class));

       return (TypeAdapter<T>) new TypeAdapter<RelativeDelta>() {
           @Override
           public void write(JsonWriter out, RelativeDelta value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public RelativeDelta read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

  /**
   * Create an instance of RelativeDelta given an JSON string
   *
   * @param jsonString JSON string
   * @return An instance of RelativeDelta
   * @throws IOException if the JSON string is invalid with respect to RelativeDelta
   */
  public static RelativeDelta fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, RelativeDelta.class);
  }

  /**
   * Convert an instance of RelativeDelta to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

