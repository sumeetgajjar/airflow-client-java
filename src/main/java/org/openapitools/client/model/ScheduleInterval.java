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
import org.openapitools.client.model.CronExpression;
import org.openapitools.client.model.RelativeDelta;
import org.openapitools.client.model.TimeDelta;



import java.io.IOException;
import java.lang.reflect.Type;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.JsonPrimitive;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonParseException;

import org.openapitools.client.JSON;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-08-11T18:15:04.477716-07:00[America/Los_Angeles]", comments = "Generator version: 7.14.0")
public class ScheduleInterval extends AbstractOpenApiSchema {
    private static final Logger log = Logger.getLogger(ScheduleInterval.class.getName());

    public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
        @SuppressWarnings("unchecked")
        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            if (!ScheduleInterval.class.isAssignableFrom(type.getRawType())) {
                return null; // this class only serializes 'ScheduleInterval' and its subtypes
            }
            final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
            final TypeAdapter<TimeDelta> adapterTimeDelta = gson.getDelegateAdapter(this, TypeToken.get(TimeDelta.class));
            final TypeAdapter<RelativeDelta> adapterRelativeDelta = gson.getDelegateAdapter(this, TypeToken.get(RelativeDelta.class));
            final TypeAdapter<CronExpression> adapterCronExpression = gson.getDelegateAdapter(this, TypeToken.get(CronExpression.class));

            return (TypeAdapter<T>) new TypeAdapter<ScheduleInterval>() {
                @Override
                public void write(JsonWriter out, ScheduleInterval value) throws IOException {
                    if (value == null || value.getActualInstance() == null) {
                        elementAdapter.write(out, null);
                        return;
                    }

                    // check if the actual instance is of the type `TimeDelta`
                    if (value.getActualInstance() instanceof TimeDelta) {
                        JsonElement element = adapterTimeDelta.toJsonTree((TimeDelta)value.getActualInstance());
                        elementAdapter.write(out, element);
                        return;
                    }
                    // check if the actual instance is of the type `RelativeDelta`
                    if (value.getActualInstance() instanceof RelativeDelta) {
                        JsonElement element = adapterRelativeDelta.toJsonTree((RelativeDelta)value.getActualInstance());
                        elementAdapter.write(out, element);
                        return;
                    }
                    // check if the actual instance is of the type `CronExpression`
                    if (value.getActualInstance() instanceof CronExpression) {
                        JsonElement element = adapterCronExpression.toJsonTree((CronExpression)value.getActualInstance());
                        elementAdapter.write(out, element);
                        return;
                    }
                    throw new IOException("Failed to serialize as the type doesn't match anyOf schemas: CronExpression, RelativeDelta, TimeDelta");
                }

                @Override
                public ScheduleInterval read(JsonReader in) throws IOException {
                    Object deserialized = null;
                    JsonElement jsonElement = elementAdapter.read(in);

                    ArrayList<String> errorMessages = new ArrayList<>();
                    TypeAdapter actualAdapter = elementAdapter;

                    // deserialize TimeDelta
                    try {
                        // validate the JSON object to see if any exception is thrown
                        TimeDelta.validateJsonElement(jsonElement);
                        actualAdapter = adapterTimeDelta;
                        ScheduleInterval ret = new ScheduleInterval();
                        ret.setActualInstance(actualAdapter.fromJsonTree(jsonElement));
                        return ret;
                    } catch (Exception e) {
                        // deserialization failed, continue
                        errorMessages.add(String.format("Deserialization for TimeDelta failed with `%s`.", e.getMessage()));
                        log.log(Level.FINER, "Input data does not match schema 'TimeDelta'", e);
                    }
                    // deserialize RelativeDelta
                    try {
                        // validate the JSON object to see if any exception is thrown
                        RelativeDelta.validateJsonElement(jsonElement);
                        actualAdapter = adapterRelativeDelta;
                        ScheduleInterval ret = new ScheduleInterval();
                        ret.setActualInstance(actualAdapter.fromJsonTree(jsonElement));
                        return ret;
                    } catch (Exception e) {
                        // deserialization failed, continue
                        errorMessages.add(String.format("Deserialization for RelativeDelta failed with `%s`.", e.getMessage()));
                        log.log(Level.FINER, "Input data does not match schema 'RelativeDelta'", e);
                    }
                    // deserialize CronExpression
                    try {
                        // validate the JSON object to see if any exception is thrown
                        CronExpression.validateJsonElement(jsonElement);
                        actualAdapter = adapterCronExpression;
                        ScheduleInterval ret = new ScheduleInterval();
                        ret.setActualInstance(actualAdapter.fromJsonTree(jsonElement));
                        return ret;
                    } catch (Exception e) {
                        // deserialization failed, continue
                        errorMessages.add(String.format("Deserialization for CronExpression failed with `%s`.", e.getMessage()));
                        log.log(Level.FINER, "Input data does not match schema 'CronExpression'", e);
                    }

                    throw new IOException(String.format("Failed deserialization for ScheduleInterval: no class matches result, expected at least 1. Detailed failure message for anyOf schemas: %s. JSON: %s", errorMessages, jsonElement.toString()));
                }
            }.nullSafe();
        }
    }

    // store a list of schema names defined in anyOf
    public static final Map<String, Class<?>> schemas = new HashMap<String, Class<?>>();

    public ScheduleInterval() {
        super("anyOf", Boolean.TRUE);
    }

    public ScheduleInterval(Object o) {
        super("anyOf", Boolean.TRUE);
        setActualInstance(o);
    }

    static {
        schemas.put("TimeDelta", TimeDelta.class);
        schemas.put("RelativeDelta", RelativeDelta.class);
        schemas.put("CronExpression", CronExpression.class);
    }

    @Override
    public Map<String, Class<?>> getSchemas() {
        return ScheduleInterval.schemas;
    }

    /**
     * Set the instance that matches the anyOf child schema, check
     * the instance parameter is valid against the anyOf child schemas:
     * CronExpression, RelativeDelta, TimeDelta
     *
     * It could be an instance of the 'anyOf' schemas.
     */
    @Override
    public void setActualInstance(Object instance) {
        if (instance == null) {
           super.setActualInstance(instance);
           return;
        }

        if (instance instanceof TimeDelta) {
            super.setActualInstance(instance);
            return;
        }

        if (instance instanceof RelativeDelta) {
            super.setActualInstance(instance);
            return;
        }

        if (instance instanceof CronExpression) {
            super.setActualInstance(instance);
            return;
        }

        throw new RuntimeException("Invalid instance type. Must be CronExpression, RelativeDelta, TimeDelta");
    }

    /**
     * Get the actual instance, which can be the following:
     * CronExpression, RelativeDelta, TimeDelta
     *
     * @return The actual instance (CronExpression, RelativeDelta, TimeDelta)
     */
    @SuppressWarnings("unchecked")
    @Override
    public Object getActualInstance() {
        return super.getActualInstance();
    }

    /**
     * Get the actual instance of `TimeDelta`. If the actual instance is not `TimeDelta`,
     * the ClassCastException will be thrown.
     *
     * @return The actual instance of `TimeDelta`
     * @throws ClassCastException if the instance is not `TimeDelta`
     */
    public TimeDelta getTimeDelta() throws ClassCastException {
        return (TimeDelta)super.getActualInstance();
    }

    /**
     * Get the actual instance of `RelativeDelta`. If the actual instance is not `RelativeDelta`,
     * the ClassCastException will be thrown.
     *
     * @return The actual instance of `RelativeDelta`
     * @throws ClassCastException if the instance is not `RelativeDelta`
     */
    public RelativeDelta getRelativeDelta() throws ClassCastException {
        return (RelativeDelta)super.getActualInstance();
    }

    /**
     * Get the actual instance of `CronExpression`. If the actual instance is not `CronExpression`,
     * the ClassCastException will be thrown.
     *
     * @return The actual instance of `CronExpression`
     * @throws ClassCastException if the instance is not `CronExpression`
     */
    public CronExpression getCronExpression() throws ClassCastException {
        return (CronExpression)super.getActualInstance();
    }

    /**
     * Validates the JSON Element and throws an exception if issues found
     *
     * @param jsonElement JSON Element
     * @throws IOException if the JSON Element is invalid with respect to ScheduleInterval
     */
    public static void validateJsonElement(JsonElement jsonElement) throws IOException {
        // validate anyOf schemas one by one
        ArrayList<String> errorMessages = new ArrayList<>();
        // validate the json string with TimeDelta
        try {
            TimeDelta.validateJsonElement(jsonElement);
            return;
        } catch (Exception e) {
            errorMessages.add(String.format("Deserialization for TimeDelta failed with `%s`.", e.getMessage()));
            // continue to the next one
        }
        // validate the json string with RelativeDelta
        try {
            RelativeDelta.validateJsonElement(jsonElement);
            return;
        } catch (Exception e) {
            errorMessages.add(String.format("Deserialization for RelativeDelta failed with `%s`.", e.getMessage()));
            // continue to the next one
        }
        // validate the json string with CronExpression
        try {
            CronExpression.validateJsonElement(jsonElement);
            return;
        } catch (Exception e) {
            errorMessages.add(String.format("Deserialization for CronExpression failed with `%s`.", e.getMessage()));
            // continue to the next one
        }
        throw new IOException(String.format("The JSON string is invalid for ScheduleInterval with anyOf schemas: CronExpression, RelativeDelta, TimeDelta. no class match the result, expected at least 1. Detailed failure message for anyOf schemas: %s. JSON: %s", errorMessages, jsonElement.toString()));
    }

    /**
     * Create an instance of ScheduleInterval given an JSON string
     *
     * @param jsonString JSON string
     * @return An instance of ScheduleInterval
     * @throws IOException if the JSON string is invalid with respect to ScheduleInterval
     */
    public static ScheduleInterval fromJson(String jsonString) throws IOException {
        return JSON.getGson().fromJson(jsonString, ScheduleInterval.class);
    }

    /**
     * Convert an instance of ScheduleInterval to an JSON string
     *
     * @return JSON string
     */
    public String toJson() {
        return JSON.getGson().toJson(this);
    }
}

