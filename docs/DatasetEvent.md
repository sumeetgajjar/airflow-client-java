

# DatasetEvent

A dataset event.  *New in version 2.4.0* 

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**datasetId** | **Integer** | The dataset id |  [optional] |
|**datasetUri** | **String** | The URI of the dataset |  [optional] |
|**extra** | **Object** | The dataset event extra |  [optional] |
|**sourceDagId** | **String** | The DAG ID that updated the dataset. |  [optional] |
|**sourceTaskId** | **String** | The task ID that updated the dataset. |  [optional] |
|**sourceRunId** | **String** | The DAG run ID that updated the dataset. |  [optional] |
|**sourceMapIndex** | **Integer** | The task map index that updated the dataset. |  [optional] |
|**createdDagruns** | [**List&lt;BasicDAGRun&gt;**](BasicDAGRun.md) |  |  [optional] |
|**timestamp** | **String** | The dataset event creation time |  [optional] |



