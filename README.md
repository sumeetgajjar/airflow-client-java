# airflow-client-java

Airflow API (Stable)
- API version: 2.10.5
  - Build date: 2025-08-11T18:15:04.477716-07:00[America/Los_Angeles]
  - Generator version: 7.14.0

# Overview

To facilitate management, Apache Airflow supports a range of REST API endpoints across its
objects.
This section provides an overview of the API design, methods, and supported use cases.

Most of the endpoints accept `JSON` as input and return `JSON` responses.
This means that you must usually add the following headers to your request:
```
Content-type: application/json
Accept: application/json
```

## Resources

The term `resource` refers to a single type of object in the Airflow metadata. An API is broken up by its
endpoint's corresponding resource.
The name of a resource is typically plural and expressed in camelCase. Example: `dagRuns`.

Resource names are used as part of endpoint URLs, as well as in API parameters and responses.

## CRUD Operations

The platform supports **C**reate, **R**ead, **U**pdate, and **D**elete operations on most resources.
You can review the standards for these operations and their standard parameters below.

Some endpoints have special behavior as exceptions.

### Create

To create a resource, you typically submit an HTTP `POST` request with the resource's required metadata
in the request body.
The response returns a `201 Created` response code upon success with the resource's metadata, including
its internal `id`, in the response body.

### Read

The HTTP `GET` request can be used to read a resource or to list a number of resources.

A resource's `id` can be submitted in the request parameters to read a specific resource.
The response usually returns a `200 OK` response code upon success, with the resource's metadata in
the response body.

If a `GET` request does not include a specific resource `id`, it is treated as a list request.
The response usually returns a `200 OK` response code upon success, with an object containing a list
of resources' metadata in the response body.

When reading resources, some common query parameters are usually available. e.g.:
```
v1/connections?limit=25&offset=25
```

|Query Parameter|Type|Description|
|---------------|----|-----------|
|limit|integer|Maximum number of objects to fetch. Usually 25 by default|
|offset|integer|Offset after which to start returning objects. For use with limit query parameter.|

### Update

Updating a resource requires the resource `id`, and is typically done using an HTTP `PATCH` request,
with the fields to modify in the request body.
The response usually returns a `200 OK` response code upon success, with information about the modified
resource in the response body.

### Delete

Deleting a resource requires the resource `id` and is typically executed via an HTTP `DELETE` request.
The response usually returns a `204 No Content` response code upon success.

## Conventions

- Resource names are plural and expressed in camelCase.
- Names are consistent between URL parameter name and field name.

- Field names are in snake_case.
```json
{
    \"description\": \"string\",
    \"name\": \"string\",
    \"occupied_slots\": 0,
    \"open_slots\": 0
    \"queued_slots\": 0,
    \"running_slots\": 0,
    \"scheduled_slots\": 0,
    \"slots\": 0,
}
```

### Update Mask

Update mask is available as a query parameter in patch endpoints. It is used to notify the
API which fields you want to update. Using `update_mask` makes it easier to update objects
by helping the server know which fields to update in an object instead of updating all fields.
The update request ignores any fields that aren't specified in the field mask, leaving them with
their current values.

Example:
```
  resource = request.get('/resource/my-id').json()
  resource['my_field'] = 'new-value'
  request.patch('/resource/my-id?update_mask=my_field', data=json.dumps(resource))
```

## Versioning and Endpoint Lifecycle

- API versioning is not synchronized to specific releases of the Apache Airflow.
- APIs are designed to be backward compatible.
- Any changes to the API will first go through a deprecation phase.

# Trying the API

You can use a third party client, such as [curl](https://curl.haxx.se/), [HTTPie](https://httpie.org/),
[Postman](https://www.postman.com/) or [the Insomnia rest client](https://insomnia.rest/) to test
the Apache Airflow API.

Note that you will need to pass credentials data.

For e.g., here is how to pause a DAG with [curl](https://curl.haxx.se/), when basic authorization is used:
```bash
curl -X PATCH 'https://example.com/api/v1/dags/{dag_id}?update_mask=is_paused' \\
-H 'Content-Type: application/json' \\
--user \"username:password\" \\
-d '{
    \"is_paused\": true
}'
```

Using a graphical tool such as [Postman](https://www.postman.com/) or [Insomnia](https://insomnia.rest/),
it is possible to import the API specifications directly:

1. Download the API specification by clicking the **Download** button at the top of this document
2. Import the JSON specification in the graphical tool of your choice.
  - In *Postman*, you can click the **import** button at the top
  - With *Insomnia*, you can just drag-and-drop the file on the UI

Note that with *Postman*, you can also generate code snippets by selecting a request and clicking on
the **Code** button.

## Enabling CORS

[Cross-origin resource sharing (CORS)](https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS)
is a browser security feature that restricts HTTP requests that are
initiated from scripts running in the browser.

For details on enabling/configuring CORS, see
[Enabling CORS](https://airflow.apache.org/docs/apache-airflow/stable/security/api.html).

# Authentication

To be able to meet the requirements of many organizations, Airflow supports many authentication methods,
and it is even possible to add your own method.

If you want to check which auth backend is currently set, you can use
`airflow config get-value api auth_backends` command as in the example below.
```bash
$ airflow config get-value api auth_backends
airflow.api.auth.backend.basic_auth
```
The default is to deny all requests.

For details on configuring the authentication, see
[API Authorization](https://airflow.apache.org/docs/apache-airflow/stable/security/api.html).

# Errors

We follow the error response format proposed in [RFC 7807](https://tools.ietf.org/html/rfc7807)
also known as Problem Details for HTTP APIs. As with our normal API responses,
your client must be prepared to gracefully handle additional members of the response.

## Unauthenticated

This indicates that the request has not been applied because it lacks valid authentication
credentials for the target resource. Please check that you have valid credentials.

## PermissionDenied

This response means that the server understood the request but refuses to authorize
it because it lacks sufficient rights to the resource. It happens when you do not have the
necessary permission to execute the action you performed. You need to get the appropriate
permissions in other to resolve this error.

## BadRequest

This response means that the server cannot or will not process the request due to something
that is perceived to be a client error (e.g., malformed request syntax, invalid request message
framing, or deceptive request routing). To resolve this, please ensure that your syntax is correct.

## NotFound

This client error response indicates that the server cannot find the requested resource.

## MethodNotAllowed

Indicates that the request method is known by the server but is not supported by the target resource.

## NotAcceptable

The target resource does not have a current representation that would be acceptable to the user
agent, according to the proactive negotiation header fields received in the request, and the
server is unwilling to supply a default representation.

## AlreadyExists

The request could not be completed due to a conflict with the current state of the target
resource, e.g. the resource it tries to create already exists.

## Unknown

This means that the server encountered an unexpected condition that prevented it from
fulfilling the request.


  For more information, please visit [https://airflow.apache.org](https://airflow.apache.org)

*Automatically generated by the [OpenAPI Generator](https://openapi-generator.tech)*


## Requirements

Building the API client library requires:
1. Java 1.8+
2. Maven (3.8.3+)/Gradle (7.2+)

## Installation

To install the API client library to your local Maven repository, simply execute:

```shell
mvn clean install
```

To deploy it to a remote Maven repository instead, configure the settings of the repository and execute:

```shell
mvn clean deploy
```

Refer to the [OSSRH Guide](http://central.sonatype.org/pages/ossrh-guide.html) for more information.

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
  <groupId>org.openapitools</groupId>
  <artifactId>airflow-client-java</artifactId>
  <version>2.10.5</version>
  <scope>compile</scope>
</dependency>
```

### Gradle users

Add this dependency to your project's build file:

```groovy
  repositories {
    mavenCentral()     // Needed if the 'airflow-client-java' jar has been published to maven central.
    mavenLocal()       // Needed if the 'airflow-client-java' jar has been published to the local maven repo.
  }

  dependencies {
     implementation "org.openapitools:airflow-client-java:2.10.5"
  }
```

### Others

At first generate the JAR by executing:

```shell
mvn clean package
```

Then manually install the following JARs:

* `target/airflow-client-java-2.10.5.jar`
* `target/lib/*.jar`

## Getting Started

Please follow the [installation](#installation) instruction and execute the following Java code:

```java

// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.model.*;
import org.openapitools.client.api.ConfigApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/api/v1");

    ConfigApi apiInstance = new ConfigApi(defaultClient);
    String section = "section_example"; // String | If given, only return config of this section.
    try {
      Config result = apiInstance.getConfig(section);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ConfigApi#getConfig");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}

```

## Documentation for API Endpoints

All URIs are relative to */api/v1*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*ConfigApi* | [**getConfig**](docs/ConfigApi.md#getConfig) | **GET** /config | Get current configuration
*ConfigApi* | [**getValue**](docs/ConfigApi.md#getValue) | **GET** /config/section/{section}/option/{option} | Get a option from configuration
*ConnectionApi* | [**deleteConnection**](docs/ConnectionApi.md#deleteConnection) | **DELETE** /connections/{connection_id} | Delete a connection
*ConnectionApi* | [**getConnection**](docs/ConnectionApi.md#getConnection) | **GET** /connections/{connection_id} | Get a connection
*ConnectionApi* | [**getConnections**](docs/ConnectionApi.md#getConnections) | **GET** /connections | List connections
*ConnectionApi* | [**patchConnection**](docs/ConnectionApi.md#patchConnection) | **PATCH** /connections/{connection_id} | Update a connection
*ConnectionApi* | [**postConnection**](docs/ConnectionApi.md#postConnection) | **POST** /connections | Create a connection
*ConnectionApi* | [**testConnection**](docs/ConnectionApi.md#testConnection) | **POST** /connections/test | Test a connection
*DagApi* | [**deleteDag**](docs/DagApi.md#deleteDag) | **DELETE** /dags/{dag_id} | Delete a DAG
*DagApi* | [**getDag**](docs/DagApi.md#getDag) | **GET** /dags/{dag_id} | Get basic information about a DAG
*DagApi* | [**getDagDetails**](docs/DagApi.md#getDagDetails) | **GET** /dags/{dag_id}/details | Get a simplified representation of DAG
*DagApi* | [**getDagSource**](docs/DagApi.md#getDagSource) | **GET** /dagSources/{file_token} | Get a source code
*DagApi* | [**getDags**](docs/DagApi.md#getDags) | **GET** /dags | List DAGs
*DagApi* | [**getTask**](docs/DagApi.md#getTask) | **GET** /dags/{dag_id}/tasks/{task_id} | Get simplified representation of a task
*DagApi* | [**getTasks**](docs/DagApi.md#getTasks) | **GET** /dags/{dag_id}/tasks | Get tasks for DAG
*DagApi* | [**patchDag**](docs/DagApi.md#patchDag) | **PATCH** /dags/{dag_id} | Update a DAG
*DagApi* | [**patchDags**](docs/DagApi.md#patchDags) | **PATCH** /dags | Update DAGs
*DagApi* | [**postClearTaskInstances**](docs/DagApi.md#postClearTaskInstances) | **POST** /dags/{dag_id}/clearTaskInstances | Clear a set of task instances
*DagApi* | [**postSetTaskInstancesState**](docs/DagApi.md#postSetTaskInstancesState) | **POST** /dags/{dag_id}/updateTaskInstancesState | Set a state of task instances
*DagApi* | [**reparseDagFile**](docs/DagApi.md#reparseDagFile) | **PUT** /parseDagFile/{file_token} | Request re-parsing of a DAG file
*DagRunApi* | [**clearDagRun**](docs/DagRunApi.md#clearDagRun) | **POST** /dags/{dag_id}/dagRuns/{dag_run_id}/clear | Clear a DAG run
*DagRunApi* | [**deleteDagRun**](docs/DagRunApi.md#deleteDagRun) | **DELETE** /dags/{dag_id}/dagRuns/{dag_run_id} | Delete a DAG run
*DagRunApi* | [**getDagRun**](docs/DagRunApi.md#getDagRun) | **GET** /dags/{dag_id}/dagRuns/{dag_run_id} | Get a DAG run
*DagRunApi* | [**getDagRuns**](docs/DagRunApi.md#getDagRuns) | **GET** /dags/{dag_id}/dagRuns | List DAG runs
*DagRunApi* | [**getDagRunsBatch**](docs/DagRunApi.md#getDagRunsBatch) | **POST** /dags/~/dagRuns/list | List DAG runs (batch)
*DagRunApi* | [**getUpstreamDatasetEvents**](docs/DagRunApi.md#getUpstreamDatasetEvents) | **GET** /dags/{dag_id}/dagRuns/{dag_run_id}/upstreamDatasetEvents | Get dataset events for a DAG run
*DagRunApi* | [**postDagRun**](docs/DagRunApi.md#postDagRun) | **POST** /dags/{dag_id}/dagRuns | Trigger a new DAG run.
*DagRunApi* | [**setDagRunNote**](docs/DagRunApi.md#setDagRunNote) | **PATCH** /dags/{dag_id}/dagRuns/{dag_run_id}/setNote | Update the DagRun note.
*DagRunApi* | [**updateDagRunState**](docs/DagRunApi.md#updateDagRunState) | **PATCH** /dags/{dag_id}/dagRuns/{dag_run_id} | Modify a DAG run
*DagStatsApi* | [**getDagStats**](docs/DagStatsApi.md#getDagStats) | **GET** /dagStats | List Dag statistics
*DagWarningApi* | [**getDagWarnings**](docs/DagWarningApi.md#getDagWarnings) | **GET** /dagWarnings | List dag warnings
*DatasetApi* | [**createDatasetEvent**](docs/DatasetApi.md#createDatasetEvent) | **POST** /datasets/events | Create dataset event
*DatasetApi* | [**deleteDagDatasetQueuedEvent**](docs/DatasetApi.md#deleteDagDatasetQueuedEvent) | **DELETE** /dags/{dag_id}/datasets/queuedEvent/{uri} | Delete a queued Dataset event for a DAG.
*DatasetApi* | [**deleteDagDatasetQueuedEvents**](docs/DatasetApi.md#deleteDagDatasetQueuedEvents) | **DELETE** /dags/{dag_id}/datasets/queuedEvent | Delete queued Dataset events for a DAG.
*DatasetApi* | [**deleteDatasetQueuedEvents**](docs/DatasetApi.md#deleteDatasetQueuedEvents) | **DELETE** /datasets/queuedEvent/{uri} | Delete queued Dataset events for a Dataset.
*DatasetApi* | [**getDagDatasetQueuedEvent**](docs/DatasetApi.md#getDagDatasetQueuedEvent) | **GET** /dags/{dag_id}/datasets/queuedEvent/{uri} | Get a queued Dataset event for a DAG
*DatasetApi* | [**getDagDatasetQueuedEvents**](docs/DatasetApi.md#getDagDatasetQueuedEvents) | **GET** /dags/{dag_id}/datasets/queuedEvent | Get queued Dataset events for a DAG.
*DatasetApi* | [**getDataset**](docs/DatasetApi.md#getDataset) | **GET** /datasets/{uri} | Get a dataset
*DatasetApi* | [**getDatasetEvents**](docs/DatasetApi.md#getDatasetEvents) | **GET** /datasets/events | Get dataset events
*DatasetApi* | [**getDatasetQueuedEvents**](docs/DatasetApi.md#getDatasetQueuedEvents) | **GET** /datasets/queuedEvent/{uri} | Get queued Dataset events for a Dataset.
*DatasetApi* | [**getDatasets**](docs/DatasetApi.md#getDatasets) | **GET** /datasets | List datasets
*DatasetApi* | [**getUpstreamDatasetEvents**](docs/DatasetApi.md#getUpstreamDatasetEvents) | **GET** /dags/{dag_id}/dagRuns/{dag_run_id}/upstreamDatasetEvents | Get dataset events for a DAG run
*EventLogApi* | [**getEventLog**](docs/EventLogApi.md#getEventLog) | **GET** /eventLogs/{event_log_id} | Get a log entry
*EventLogApi* | [**getEventLogs**](docs/EventLogApi.md#getEventLogs) | **GET** /eventLogs | List log entries
*ImportErrorApi* | [**getImportError**](docs/ImportErrorApi.md#getImportError) | **GET** /importErrors/{import_error_id} | Get an import error
*ImportErrorApi* | [**getImportErrors**](docs/ImportErrorApi.md#getImportErrors) | **GET** /importErrors | List import errors
*MonitoringApi* | [**getHealth**](docs/MonitoringApi.md#getHealth) | **GET** /health | Get instance status
*MonitoringApi* | [**getVersion**](docs/MonitoringApi.md#getVersion) | **GET** /version | Get version information
*PermissionApi* | [**getPermissions**](docs/PermissionApi.md#getPermissions) | **GET** /permissions | List permissions
*PluginApi* | [**getPlugins**](docs/PluginApi.md#getPlugins) | **GET** /plugins | Get a list of loaded plugins
*PoolApi* | [**deletePool**](docs/PoolApi.md#deletePool) | **DELETE** /pools/{pool_name} | Delete a pool
*PoolApi* | [**getPool**](docs/PoolApi.md#getPool) | **GET** /pools/{pool_name} | Get a pool
*PoolApi* | [**getPools**](docs/PoolApi.md#getPools) | **GET** /pools | List pools
*PoolApi* | [**patchPool**](docs/PoolApi.md#patchPool) | **PATCH** /pools/{pool_name} | Update a pool
*PoolApi* | [**postPool**](docs/PoolApi.md#postPool) | **POST** /pools | Create a pool
*ProviderApi* | [**getProviders**](docs/ProviderApi.md#getProviders) | **GET** /providers | List providers
*RoleApi* | [**deleteRole**](docs/RoleApi.md#deleteRole) | **DELETE** /roles/{role_name} | Delete a role
*RoleApi* | [**getRole**](docs/RoleApi.md#getRole) | **GET** /roles/{role_name} | Get a role
*RoleApi* | [**getRoles**](docs/RoleApi.md#getRoles) | **GET** /roles | List roles
*RoleApi* | [**patchRole**](docs/RoleApi.md#patchRole) | **PATCH** /roles/{role_name} | Update a role
*RoleApi* | [**postRole**](docs/RoleApi.md#postRole) | **POST** /roles | Create a role
*TaskInstanceApi* | [**getExtraLinks**](docs/TaskInstanceApi.md#getExtraLinks) | **GET** /dags/{dag_id}/dagRuns/{dag_run_id}/taskInstances/{task_id}/links | List extra links
*TaskInstanceApi* | [**getLog**](docs/TaskInstanceApi.md#getLog) | **GET** /dags/{dag_id}/dagRuns/{dag_run_id}/taskInstances/{task_id}/logs/{task_try_number} | Get logs
*TaskInstanceApi* | [**getMappedTaskInstance**](docs/TaskInstanceApi.md#getMappedTaskInstance) | **GET** /dags/{dag_id}/dagRuns/{dag_run_id}/taskInstances/{task_id}/{map_index} | Get a mapped task instance
*TaskInstanceApi* | [**getMappedTaskInstanceDependencies**](docs/TaskInstanceApi.md#getMappedTaskInstanceDependencies) | **GET** /dags/{dag_id}/dagRuns/{dag_run_id}/taskInstances/{task_id}/{map_index}/dependencies | Get task dependencies blocking task from getting scheduled.
*TaskInstanceApi* | [**getMappedTaskInstanceTries**](docs/TaskInstanceApi.md#getMappedTaskInstanceTries) | **GET** /dags/{dag_id}/dagRuns/{dag_run_id}/taskInstances/{task_id}/{map_index}/tries | List mapped task instance tries
*TaskInstanceApi* | [**getMappedTaskInstanceTryDetails**](docs/TaskInstanceApi.md#getMappedTaskInstanceTryDetails) | **GET** /dags/{dag_id}/dagRuns/{dag_run_id}/taskInstances/{task_id}/{map_index}/tries/{task_try_number} | get mapped taskinstance try
*TaskInstanceApi* | [**getMappedTaskInstances**](docs/TaskInstanceApi.md#getMappedTaskInstances) | **GET** /dags/{dag_id}/dagRuns/{dag_run_id}/taskInstances/{task_id}/listMapped | List mapped task instances
*TaskInstanceApi* | [**getTaskInstance**](docs/TaskInstanceApi.md#getTaskInstance) | **GET** /dags/{dag_id}/dagRuns/{dag_run_id}/taskInstances/{task_id} | Get a task instance
*TaskInstanceApi* | [**getTaskInstanceDependencies**](docs/TaskInstanceApi.md#getTaskInstanceDependencies) | **GET** /dags/{dag_id}/dagRuns/{dag_run_id}/taskInstances/{task_id}/dependencies | Get task dependencies blocking task from getting scheduled.
*TaskInstanceApi* | [**getTaskInstanceTries**](docs/TaskInstanceApi.md#getTaskInstanceTries) | **GET** /dags/{dag_id}/dagRuns/{dag_run_id}/taskInstances/{task_id}/tries | List task instance tries
*TaskInstanceApi* | [**getTaskInstanceTryDetails**](docs/TaskInstanceApi.md#getTaskInstanceTryDetails) | **GET** /dags/{dag_id}/dagRuns/{dag_run_id}/taskInstances/{task_id}/tries/{task_try_number} | get taskinstance try
*TaskInstanceApi* | [**getTaskInstances**](docs/TaskInstanceApi.md#getTaskInstances) | **GET** /dags/{dag_id}/dagRuns/{dag_run_id}/taskInstances | List task instances
*TaskInstanceApi* | [**getTaskInstancesBatch**](docs/TaskInstanceApi.md#getTaskInstancesBatch) | **POST** /dags/~/dagRuns/~/taskInstances/list | List task instances (batch)
*TaskInstanceApi* | [**patchMappedTaskInstance**](docs/TaskInstanceApi.md#patchMappedTaskInstance) | **PATCH** /dags/{dag_id}/dagRuns/{dag_run_id}/taskInstances/{task_id}/{map_index} | Updates the state of a mapped task instance
*TaskInstanceApi* | [**patchTaskInstance**](docs/TaskInstanceApi.md#patchTaskInstance) | **PATCH** /dags/{dag_id}/dagRuns/{dag_run_id}/taskInstances/{task_id} | Updates the state of a task instance
*TaskInstanceApi* | [**setMappedTaskInstanceNote**](docs/TaskInstanceApi.md#setMappedTaskInstanceNote) | **PATCH** /dags/{dag_id}/dagRuns/{dag_run_id}/taskInstances/{task_id}/{map_index}/setNote | Update the TaskInstance note.
*TaskInstanceApi* | [**setTaskInstanceNote**](docs/TaskInstanceApi.md#setTaskInstanceNote) | **PATCH** /dags/{dag_id}/dagRuns/{dag_run_id}/taskInstances/{task_id}/setNote | Update the TaskInstance note.
*UserApi* | [**deleteUser**](docs/UserApi.md#deleteUser) | **DELETE** /users/{username} | Delete a user
*UserApi* | [**getUser**](docs/UserApi.md#getUser) | **GET** /users/{username} | Get a user
*UserApi* | [**getUsers**](docs/UserApi.md#getUsers) | **GET** /users | List users
*UserApi* | [**patchUser**](docs/UserApi.md#patchUser) | **PATCH** /users/{username} | Update a user
*UserApi* | [**postUser**](docs/UserApi.md#postUser) | **POST** /users | Create a user
*VariableApi* | [**deleteVariable**](docs/VariableApi.md#deleteVariable) | **DELETE** /variables/{variable_key} | Delete a variable
*VariableApi* | [**getVariable**](docs/VariableApi.md#getVariable) | **GET** /variables/{variable_key} | Get a variable
*VariableApi* | [**getVariables**](docs/VariableApi.md#getVariables) | **GET** /variables | List variables
*VariableApi* | [**patchVariable**](docs/VariableApi.md#patchVariable) | **PATCH** /variables/{variable_key} | Update a variable
*VariableApi* | [**postVariables**](docs/VariableApi.md#postVariables) | **POST** /variables | Create a variable
*XComApi* | [**getXcomEntries**](docs/XComApi.md#getXcomEntries) | **GET** /dags/{dag_id}/dagRuns/{dag_run_id}/taskInstances/{task_id}/xcomEntries | List XCom entries
*XComApi* | [**getXcomEntry**](docs/XComApi.md#getXcomEntry) | **GET** /dags/{dag_id}/dagRuns/{dag_run_id}/taskInstances/{task_id}/xcomEntries/{xcom_key} | Get an XCom entry


## Documentation for Models

 - [Action](docs/Action.md)
 - [ActionCollection](docs/ActionCollection.md)
 - [ActionResource](docs/ActionResource.md)
 - [BasicDAGRun](docs/BasicDAGRun.md)
 - [ClassReference](docs/ClassReference.md)
 - [ClearDagRun](docs/ClearDagRun.md)
 - [ClearDagRun200Response](docs/ClearDagRun200Response.md)
 - [ClearTaskInstances](docs/ClearTaskInstances.md)
 - [CollectionInfo](docs/CollectionInfo.md)
 - [Config](docs/Config.md)
 - [ConfigOption](docs/ConfigOption.md)
 - [ConfigSection](docs/ConfigSection.md)
 - [Connection](docs/Connection.md)
 - [ConnectionCollection](docs/ConnectionCollection.md)
 - [ConnectionCollectionItem](docs/ConnectionCollectionItem.md)
 - [ConnectionTest](docs/ConnectionTest.md)
 - [CreateDatasetEvent](docs/CreateDatasetEvent.md)
 - [CronExpression](docs/CronExpression.md)
 - [DAG](docs/DAG.md)
 - [DAGCollection](docs/DAGCollection.md)
 - [DAGDetail](docs/DAGDetail.md)
 - [DAGRun](docs/DAGRun.md)
 - [DAGRunCollection](docs/DAGRunCollection.md)
 - [DagProcessorStatus](docs/DagProcessorStatus.md)
 - [DagScheduleDatasetReference](docs/DagScheduleDatasetReference.md)
 - [DagState](docs/DagState.md)
 - [DagStatsCollectionItem](docs/DagStatsCollectionItem.md)
 - [DagStatsCollectionSchema](docs/DagStatsCollectionSchema.md)
 - [DagStatsStateCollectionItem](docs/DagStatsStateCollectionItem.md)
 - [DagWarning](docs/DagWarning.md)
 - [DagWarningCollection](docs/DagWarningCollection.md)
 - [Dataset](docs/Dataset.md)
 - [DatasetCollection](docs/DatasetCollection.md)
 - [DatasetEvent](docs/DatasetEvent.md)
 - [DatasetEventCollection](docs/DatasetEventCollection.md)
 - [Error](docs/Error.md)
 - [EventLog](docs/EventLog.md)
 - [EventLogCollection](docs/EventLogCollection.md)
 - [ExtraLink](docs/ExtraLink.md)
 - [ExtraLinkCollection](docs/ExtraLinkCollection.md)
 - [GetDagSource200Response](docs/GetDagSource200Response.md)
 - [GetLog200Response](docs/GetLog200Response.md)
 - [GetProviders200Response](docs/GetProviders200Response.md)
 - [HealthInfo](docs/HealthInfo.md)
 - [HealthStatus](docs/HealthStatus.md)
 - [ImportError](docs/ImportError.md)
 - [ImportErrorCollection](docs/ImportErrorCollection.md)
 - [Job](docs/Job.md)
 - [ListDagRunsForm](docs/ListDagRunsForm.md)
 - [ListTaskInstanceForm](docs/ListTaskInstanceForm.md)
 - [MetadatabaseStatus](docs/MetadatabaseStatus.md)
 - [PluginCollection](docs/PluginCollection.md)
 - [PluginCollectionItem](docs/PluginCollectionItem.md)
 - [Pool](docs/Pool.md)
 - [PoolCollection](docs/PoolCollection.md)
 - [Provider](docs/Provider.md)
 - [ProviderCollection](docs/ProviderCollection.md)
 - [QueuedEvent](docs/QueuedEvent.md)
 - [QueuedEventCollection](docs/QueuedEventCollection.md)
 - [RelativeDelta](docs/RelativeDelta.md)
 - [Resource](docs/Resource.md)
 - [Role](docs/Role.md)
 - [RoleCollection](docs/RoleCollection.md)
 - [SLAMiss](docs/SLAMiss.md)
 - [ScheduleInterval](docs/ScheduleInterval.md)
 - [SchedulerStatus](docs/SchedulerStatus.md)
 - [SetDagRunNote](docs/SetDagRunNote.md)
 - [SetTaskInstanceNote](docs/SetTaskInstanceNote.md)
 - [Tag](docs/Tag.md)
 - [Task](docs/Task.md)
 - [TaskCollection](docs/TaskCollection.md)
 - [TaskExtraLinksInner](docs/TaskExtraLinksInner.md)
 - [TaskFailedDependency](docs/TaskFailedDependency.md)
 - [TaskInstance](docs/TaskInstance.md)
 - [TaskInstanceCollection](docs/TaskInstanceCollection.md)
 - [TaskInstanceDependencyCollection](docs/TaskInstanceDependencyCollection.md)
 - [TaskInstanceHistory](docs/TaskInstanceHistory.md)
 - [TaskInstanceHistoryCollection](docs/TaskInstanceHistoryCollection.md)
 - [TaskInstanceReference](docs/TaskInstanceReference.md)
 - [TaskInstanceReferenceCollection](docs/TaskInstanceReferenceCollection.md)
 - [TaskOutletDatasetReference](docs/TaskOutletDatasetReference.md)
 - [TaskState](docs/TaskState.md)
 - [TimeDelta](docs/TimeDelta.md)
 - [Trigger](docs/Trigger.md)
 - [TriggerRule](docs/TriggerRule.md)
 - [TriggererStatus](docs/TriggererStatus.md)
 - [UpdateDagRunState](docs/UpdateDagRunState.md)
 - [UpdateTaskInstance](docs/UpdateTaskInstance.md)
 - [UpdateTaskInstancesState](docs/UpdateTaskInstancesState.md)
 - [UpdateTaskState](docs/UpdateTaskState.md)
 - [User](docs/User.md)
 - [UserCollection](docs/UserCollection.md)
 - [UserCollectionItem](docs/UserCollectionItem.md)
 - [UserCollectionItemRolesInner](docs/UserCollectionItemRolesInner.md)
 - [Variable](docs/Variable.md)
 - [VariableCollection](docs/VariableCollection.md)
 - [VariableCollectionItem](docs/VariableCollectionItem.md)
 - [VersionInfo](docs/VersionInfo.md)
 - [WeightRule](docs/WeightRule.md)
 - [XCom](docs/XCom.md)
 - [XComCollection](docs/XComCollection.md)
 - [XComCollectionItem](docs/XComCollectionItem.md)


<a id="documentation-for-authorization"></a>
## Documentation for Authorization


Authentication schemes defined for the API:
<a id="Basic"></a>
### Basic

- **Type**: HTTP basic authentication

<a id="GoogleOpenId"></a>
### GoogleOpenId


<a id="Kerberos"></a>
### Kerberos



## Recommendation

It's recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issues.

## Author

dev@airflow.apache.org

