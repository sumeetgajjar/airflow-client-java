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
 * A plugin Item.  *New in version 2.1.0* 
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-08-11T18:15:04.477716-07:00[America/Los_Angeles]", comments = "Generator version: 7.14.0")
public class PluginCollectionItem {
  public static final String SERIALIZED_NAME_NAME = "name";
  @SerializedName(SERIALIZED_NAME_NAME)
  @javax.annotation.Nullable
  private String name;

  public static final String SERIALIZED_NAME_HOOKS = "hooks";
  @SerializedName(SERIALIZED_NAME_HOOKS)
  @javax.annotation.Nullable
  private List<String> hooks = new ArrayList<>();

  public static final String SERIALIZED_NAME_EXECUTORS = "executors";
  @SerializedName(SERIALIZED_NAME_EXECUTORS)
  @javax.annotation.Nullable
  private List<String> executors = new ArrayList<>();

  public static final String SERIALIZED_NAME_MACROS = "macros";
  @SerializedName(SERIALIZED_NAME_MACROS)
  @javax.annotation.Nullable
  private List<String> macros = new ArrayList<>();

  public static final String SERIALIZED_NAME_FLASK_BLUEPRINTS = "flask_blueprints";
  @SerializedName(SERIALIZED_NAME_FLASK_BLUEPRINTS)
  @javax.annotation.Nullable
  private List<String> flaskBlueprints = new ArrayList<>();

  public static final String SERIALIZED_NAME_APPBUILDER_VIEWS = "appbuilder_views";
  @SerializedName(SERIALIZED_NAME_APPBUILDER_VIEWS)
  @javax.annotation.Nullable
  private List<Object> appbuilderViews = new ArrayList<>();

  public static final String SERIALIZED_NAME_APPBUILDER_MENU_ITEMS = "appbuilder_menu_items";
  @SerializedName(SERIALIZED_NAME_APPBUILDER_MENU_ITEMS)
  @javax.annotation.Nullable
  private List<Object> appbuilderMenuItems = new ArrayList<>();

  public static final String SERIALIZED_NAME_GLOBAL_OPERATOR_EXTRA_LINKS = "global_operator_extra_links";
  @SerializedName(SERIALIZED_NAME_GLOBAL_OPERATOR_EXTRA_LINKS)
  @javax.annotation.Nullable
  private List<String> globalOperatorExtraLinks = new ArrayList<>();

  public static final String SERIALIZED_NAME_OPERATOR_EXTRA_LINKS = "operator_extra_links";
  @SerializedName(SERIALIZED_NAME_OPERATOR_EXTRA_LINKS)
  @javax.annotation.Nullable
  private List<String> operatorExtraLinks = new ArrayList<>();

  public static final String SERIALIZED_NAME_SOURCE = "source";
  @SerializedName(SERIALIZED_NAME_SOURCE)
  @javax.annotation.Nullable
  private String source;

  public static final String SERIALIZED_NAME_TI_DEPS = "ti_deps";
  @SerializedName(SERIALIZED_NAME_TI_DEPS)
  @javax.annotation.Nullable
  private List<String> tiDeps = new ArrayList<>();

  public static final String SERIALIZED_NAME_LISTENERS = "listeners";
  @SerializedName(SERIALIZED_NAME_LISTENERS)
  @javax.annotation.Nullable
  private List<String> listeners = new ArrayList<>();

  public static final String SERIALIZED_NAME_TIMETABLES = "timetables";
  @SerializedName(SERIALIZED_NAME_TIMETABLES)
  @javax.annotation.Nullable
  private List<String> timetables = new ArrayList<>();

  public PluginCollectionItem() {
  }

  public PluginCollectionItem name(@javax.annotation.Nullable String name) {
    this.name = name;
    return this;
  }

  /**
   * The name of the plugin
   * @return name
   */
  @javax.annotation.Nullable
  public String getName() {
    return name;
  }

  public void setName(@javax.annotation.Nullable String name) {
    this.name = name;
  }


  public PluginCollectionItem hooks(@javax.annotation.Nullable List<String> hooks) {
    this.hooks = hooks;
    return this;
  }

  public PluginCollectionItem addHooksItem(String hooksItem) {
    if (this.hooks == null) {
      this.hooks = new ArrayList<>();
    }
    this.hooks.add(hooksItem);
    return this;
  }

  /**
   * The plugin hooks
   * @return hooks
   */
  @javax.annotation.Nullable
  public List<String> getHooks() {
    return hooks;
  }

  public void setHooks(@javax.annotation.Nullable List<String> hooks) {
    this.hooks = hooks;
  }


  public PluginCollectionItem executors(@javax.annotation.Nullable List<String> executors) {
    this.executors = executors;
    return this;
  }

  public PluginCollectionItem addExecutorsItem(String executorsItem) {
    if (this.executors == null) {
      this.executors = new ArrayList<>();
    }
    this.executors.add(executorsItem);
    return this;
  }

  /**
   * The plugin executors
   * @return executors
   */
  @javax.annotation.Nullable
  public List<String> getExecutors() {
    return executors;
  }

  public void setExecutors(@javax.annotation.Nullable List<String> executors) {
    this.executors = executors;
  }


  public PluginCollectionItem macros(@javax.annotation.Nullable List<String> macros) {
    this.macros = macros;
    return this;
  }

  public PluginCollectionItem addMacrosItem(String macrosItem) {
    if (this.macros == null) {
      this.macros = new ArrayList<>();
    }
    this.macros.add(macrosItem);
    return this;
  }

  /**
   * The plugin macros
   * @return macros
   */
  @javax.annotation.Nullable
  public List<String> getMacros() {
    return macros;
  }

  public void setMacros(@javax.annotation.Nullable List<String> macros) {
    this.macros = macros;
  }


  public PluginCollectionItem flaskBlueprints(@javax.annotation.Nullable List<String> flaskBlueprints) {
    this.flaskBlueprints = flaskBlueprints;
    return this;
  }

  public PluginCollectionItem addFlaskBlueprintsItem(String flaskBlueprintsItem) {
    if (this.flaskBlueprints == null) {
      this.flaskBlueprints = new ArrayList<>();
    }
    this.flaskBlueprints.add(flaskBlueprintsItem);
    return this;
  }

  /**
   * The flask blueprints
   * @return flaskBlueprints
   */
  @javax.annotation.Nullable
  public List<String> getFlaskBlueprints() {
    return flaskBlueprints;
  }

  public void setFlaskBlueprints(@javax.annotation.Nullable List<String> flaskBlueprints) {
    this.flaskBlueprints = flaskBlueprints;
  }


  public PluginCollectionItem appbuilderViews(@javax.annotation.Nullable List<Object> appbuilderViews) {
    this.appbuilderViews = appbuilderViews;
    return this;
  }

  public PluginCollectionItem addAppbuilderViewsItem(Object appbuilderViewsItem) {
    if (this.appbuilderViews == null) {
      this.appbuilderViews = new ArrayList<>();
    }
    this.appbuilderViews.add(appbuilderViewsItem);
    return this;
  }

  /**
   * The appuilder views
   * @return appbuilderViews
   */
  @javax.annotation.Nullable
  public List<Object> getAppbuilderViews() {
    return appbuilderViews;
  }

  public void setAppbuilderViews(@javax.annotation.Nullable List<Object> appbuilderViews) {
    this.appbuilderViews = appbuilderViews;
  }


  public PluginCollectionItem appbuilderMenuItems(@javax.annotation.Nullable List<Object> appbuilderMenuItems) {
    this.appbuilderMenuItems = appbuilderMenuItems;
    return this;
  }

  public PluginCollectionItem addAppbuilderMenuItemsItem(Object appbuilderMenuItemsItem) {
    if (this.appbuilderMenuItems == null) {
      this.appbuilderMenuItems = new ArrayList<>();
    }
    this.appbuilderMenuItems.add(appbuilderMenuItemsItem);
    return this;
  }

  /**
   * The Flask Appbuilder menu items
   * @return appbuilderMenuItems
   */
  @javax.annotation.Nullable
  public List<Object> getAppbuilderMenuItems() {
    return appbuilderMenuItems;
  }

  public void setAppbuilderMenuItems(@javax.annotation.Nullable List<Object> appbuilderMenuItems) {
    this.appbuilderMenuItems = appbuilderMenuItems;
  }


  public PluginCollectionItem globalOperatorExtraLinks(@javax.annotation.Nullable List<String> globalOperatorExtraLinks) {
    this.globalOperatorExtraLinks = globalOperatorExtraLinks;
    return this;
  }

  public PluginCollectionItem addGlobalOperatorExtraLinksItem(String globalOperatorExtraLinksItem) {
    if (this.globalOperatorExtraLinks == null) {
      this.globalOperatorExtraLinks = new ArrayList<>();
    }
    this.globalOperatorExtraLinks.add(globalOperatorExtraLinksItem);
    return this;
  }

  /**
   * The global operator extra links
   * @return globalOperatorExtraLinks
   */
  @javax.annotation.Nullable
  public List<String> getGlobalOperatorExtraLinks() {
    return globalOperatorExtraLinks;
  }

  public void setGlobalOperatorExtraLinks(@javax.annotation.Nullable List<String> globalOperatorExtraLinks) {
    this.globalOperatorExtraLinks = globalOperatorExtraLinks;
  }


  public PluginCollectionItem operatorExtraLinks(@javax.annotation.Nullable List<String> operatorExtraLinks) {
    this.operatorExtraLinks = operatorExtraLinks;
    return this;
  }

  public PluginCollectionItem addOperatorExtraLinksItem(String operatorExtraLinksItem) {
    if (this.operatorExtraLinks == null) {
      this.operatorExtraLinks = new ArrayList<>();
    }
    this.operatorExtraLinks.add(operatorExtraLinksItem);
    return this;
  }

  /**
   * Operator extra links
   * @return operatorExtraLinks
   */
  @javax.annotation.Nullable
  public List<String> getOperatorExtraLinks() {
    return operatorExtraLinks;
  }

  public void setOperatorExtraLinks(@javax.annotation.Nullable List<String> operatorExtraLinks) {
    this.operatorExtraLinks = operatorExtraLinks;
  }


  public PluginCollectionItem source(@javax.annotation.Nullable String source) {
    this.source = source;
    return this;
  }

  /**
   * The plugin source
   * @return source
   */
  @javax.annotation.Nullable
  public String getSource() {
    return source;
  }

  public void setSource(@javax.annotation.Nullable String source) {
    this.source = source;
  }


  public PluginCollectionItem tiDeps(@javax.annotation.Nullable List<String> tiDeps) {
    this.tiDeps = tiDeps;
    return this;
  }

  public PluginCollectionItem addTiDepsItem(String tiDepsItem) {
    if (this.tiDeps == null) {
      this.tiDeps = new ArrayList<>();
    }
    this.tiDeps.add(tiDepsItem);
    return this;
  }

  /**
   * The plugin task instance dependencies
   * @return tiDeps
   */
  @javax.annotation.Nullable
  public List<String> getTiDeps() {
    return tiDeps;
  }

  public void setTiDeps(@javax.annotation.Nullable List<String> tiDeps) {
    this.tiDeps = tiDeps;
  }


  public PluginCollectionItem listeners(@javax.annotation.Nullable List<String> listeners) {
    this.listeners = listeners;
    return this;
  }

  public PluginCollectionItem addListenersItem(String listenersItem) {
    if (this.listeners == null) {
      this.listeners = new ArrayList<>();
    }
    this.listeners.add(listenersItem);
    return this;
  }

  /**
   * The plugin listeners
   * @return listeners
   */
  @javax.annotation.Nullable
  public List<String> getListeners() {
    return listeners;
  }

  public void setListeners(@javax.annotation.Nullable List<String> listeners) {
    this.listeners = listeners;
  }


  public PluginCollectionItem timetables(@javax.annotation.Nullable List<String> timetables) {
    this.timetables = timetables;
    return this;
  }

  public PluginCollectionItem addTimetablesItem(String timetablesItem) {
    if (this.timetables == null) {
      this.timetables = new ArrayList<>();
    }
    this.timetables.add(timetablesItem);
    return this;
  }

  /**
   * The plugin timetables
   * @return timetables
   */
  @javax.annotation.Nullable
  public List<String> getTimetables() {
    return timetables;
  }

  public void setTimetables(@javax.annotation.Nullable List<String> timetables) {
    this.timetables = timetables;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PluginCollectionItem pluginCollectionItem = (PluginCollectionItem) o;
    return Objects.equals(this.name, pluginCollectionItem.name) &&
        Objects.equals(this.hooks, pluginCollectionItem.hooks) &&
        Objects.equals(this.executors, pluginCollectionItem.executors) &&
        Objects.equals(this.macros, pluginCollectionItem.macros) &&
        Objects.equals(this.flaskBlueprints, pluginCollectionItem.flaskBlueprints) &&
        Objects.equals(this.appbuilderViews, pluginCollectionItem.appbuilderViews) &&
        Objects.equals(this.appbuilderMenuItems, pluginCollectionItem.appbuilderMenuItems) &&
        Objects.equals(this.globalOperatorExtraLinks, pluginCollectionItem.globalOperatorExtraLinks) &&
        Objects.equals(this.operatorExtraLinks, pluginCollectionItem.operatorExtraLinks) &&
        Objects.equals(this.source, pluginCollectionItem.source) &&
        Objects.equals(this.tiDeps, pluginCollectionItem.tiDeps) &&
        Objects.equals(this.listeners, pluginCollectionItem.listeners) &&
        Objects.equals(this.timetables, pluginCollectionItem.timetables);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, hooks, executors, macros, flaskBlueprints, appbuilderViews, appbuilderMenuItems, globalOperatorExtraLinks, operatorExtraLinks, source, tiDeps, listeners, timetables);
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
    sb.append("class PluginCollectionItem {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    hooks: ").append(toIndentedString(hooks)).append("\n");
    sb.append("    executors: ").append(toIndentedString(executors)).append("\n");
    sb.append("    macros: ").append(toIndentedString(macros)).append("\n");
    sb.append("    flaskBlueprints: ").append(toIndentedString(flaskBlueprints)).append("\n");
    sb.append("    appbuilderViews: ").append(toIndentedString(appbuilderViews)).append("\n");
    sb.append("    appbuilderMenuItems: ").append(toIndentedString(appbuilderMenuItems)).append("\n");
    sb.append("    globalOperatorExtraLinks: ").append(toIndentedString(globalOperatorExtraLinks)).append("\n");
    sb.append("    operatorExtraLinks: ").append(toIndentedString(operatorExtraLinks)).append("\n");
    sb.append("    source: ").append(toIndentedString(source)).append("\n");
    sb.append("    tiDeps: ").append(toIndentedString(tiDeps)).append("\n");
    sb.append("    listeners: ").append(toIndentedString(listeners)).append("\n");
    sb.append("    timetables: ").append(toIndentedString(timetables)).append("\n");
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
    openapiFields = new HashSet<String>(Arrays.asList("name", "hooks", "executors", "macros", "flask_blueprints", "appbuilder_views", "appbuilder_menu_items", "global_operator_extra_links", "operator_extra_links", "source", "ti_deps", "listeners", "timetables"));

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>(0);
  }

  /**
   * Validates the JSON Element and throws an exception if issues found
   *
   * @param jsonElement JSON Element
   * @throws IOException if the JSON Element is invalid with respect to PluginCollectionItem
   */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!PluginCollectionItem.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in PluginCollectionItem is not found in the empty JSON string", PluginCollectionItem.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!PluginCollectionItem.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `PluginCollectionItem` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if ((jsonObj.get("name") != null && !jsonObj.get("name").isJsonNull()) && !jsonObj.get("name").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `name` to be a primitive type in the JSON string but got `%s`", jsonObj.get("name").toString()));
      }
      // ensure the optional json data is an array if present
      if (jsonObj.get("hooks") != null && !jsonObj.get("hooks").isJsonNull() && !jsonObj.get("hooks").isJsonArray()) {
        throw new IllegalArgumentException(String.format("Expected the field `hooks` to be an array in the JSON string but got `%s`", jsonObj.get("hooks").toString()));
      }
      // ensure the optional json data is an array if present
      if (jsonObj.get("executors") != null && !jsonObj.get("executors").isJsonNull() && !jsonObj.get("executors").isJsonArray()) {
        throw new IllegalArgumentException(String.format("Expected the field `executors` to be an array in the JSON string but got `%s`", jsonObj.get("executors").toString()));
      }
      // ensure the optional json data is an array if present
      if (jsonObj.get("macros") != null && !jsonObj.get("macros").isJsonNull() && !jsonObj.get("macros").isJsonArray()) {
        throw new IllegalArgumentException(String.format("Expected the field `macros` to be an array in the JSON string but got `%s`", jsonObj.get("macros").toString()));
      }
      // ensure the optional json data is an array if present
      if (jsonObj.get("flask_blueprints") != null && !jsonObj.get("flask_blueprints").isJsonNull() && !jsonObj.get("flask_blueprints").isJsonArray()) {
        throw new IllegalArgumentException(String.format("Expected the field `flask_blueprints` to be an array in the JSON string but got `%s`", jsonObj.get("flask_blueprints").toString()));
      }
      // ensure the optional json data is an array if present
      if (jsonObj.get("appbuilder_views") != null && !jsonObj.get("appbuilder_views").isJsonNull() && !jsonObj.get("appbuilder_views").isJsonArray()) {
        throw new IllegalArgumentException(String.format("Expected the field `appbuilder_views` to be an array in the JSON string but got `%s`", jsonObj.get("appbuilder_views").toString()));
      }
      // ensure the optional json data is an array if present
      if (jsonObj.get("appbuilder_menu_items") != null && !jsonObj.get("appbuilder_menu_items").isJsonNull() && !jsonObj.get("appbuilder_menu_items").isJsonArray()) {
        throw new IllegalArgumentException(String.format("Expected the field `appbuilder_menu_items` to be an array in the JSON string but got `%s`", jsonObj.get("appbuilder_menu_items").toString()));
      }
      // ensure the optional json data is an array if present
      if (jsonObj.get("global_operator_extra_links") != null && !jsonObj.get("global_operator_extra_links").isJsonNull() && !jsonObj.get("global_operator_extra_links").isJsonArray()) {
        throw new IllegalArgumentException(String.format("Expected the field `global_operator_extra_links` to be an array in the JSON string but got `%s`", jsonObj.get("global_operator_extra_links").toString()));
      }
      // ensure the optional json data is an array if present
      if (jsonObj.get("operator_extra_links") != null && !jsonObj.get("operator_extra_links").isJsonNull() && !jsonObj.get("operator_extra_links").isJsonArray()) {
        throw new IllegalArgumentException(String.format("Expected the field `operator_extra_links` to be an array in the JSON string but got `%s`", jsonObj.get("operator_extra_links").toString()));
      }
      if ((jsonObj.get("source") != null && !jsonObj.get("source").isJsonNull()) && !jsonObj.get("source").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `source` to be a primitive type in the JSON string but got `%s`", jsonObj.get("source").toString()));
      }
      // ensure the optional json data is an array if present
      if (jsonObj.get("ti_deps") != null && !jsonObj.get("ti_deps").isJsonNull() && !jsonObj.get("ti_deps").isJsonArray()) {
        throw new IllegalArgumentException(String.format("Expected the field `ti_deps` to be an array in the JSON string but got `%s`", jsonObj.get("ti_deps").toString()));
      }
      // ensure the optional json data is an array if present
      if (jsonObj.get("listeners") != null && !jsonObj.get("listeners").isJsonNull() && !jsonObj.get("listeners").isJsonArray()) {
        throw new IllegalArgumentException(String.format("Expected the field `listeners` to be an array in the JSON string but got `%s`", jsonObj.get("listeners").toString()));
      }
      // ensure the optional json data is an array if present
      if (jsonObj.get("timetables") != null && !jsonObj.get("timetables").isJsonNull() && !jsonObj.get("timetables").isJsonArray()) {
        throw new IllegalArgumentException(String.format("Expected the field `timetables` to be an array in the JSON string but got `%s`", jsonObj.get("timetables").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!PluginCollectionItem.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'PluginCollectionItem' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<PluginCollectionItem> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(PluginCollectionItem.class));

       return (TypeAdapter<T>) new TypeAdapter<PluginCollectionItem>() {
           @Override
           public void write(JsonWriter out, PluginCollectionItem value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public PluginCollectionItem read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

  /**
   * Create an instance of PluginCollectionItem given an JSON string
   *
   * @param jsonString JSON string
   * @return An instance of PluginCollectionItem
   * @throws IOException if the JSON string is invalid with respect to PluginCollectionItem
   */
  public static PluginCollectionItem fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, PluginCollectionItem.class);
  }

  /**
   * Convert an instance of PluginCollectionItem to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

