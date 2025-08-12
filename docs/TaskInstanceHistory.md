

# TaskInstanceHistory


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**taskId** | **String** |  |  [optional] |
|**taskDisplayName** | **String** | Human centric display text for the task.  *New in version 2.9.0*  |  [optional] |
|**dagId** | **String** |  |  [optional] |
|**dagRunId** | **String** | The DagRun ID for this task instance  *New in version 2.3.0*  |  [optional] |
|**startDate** | **String** |  |  [optional] |
|**endDate** | **String** |  |  [optional] |
|**duration** | **BigDecimal** |  |  [optional] |
|**state** | **TaskState** |  |  [optional] |
|**tryNumber** | **Integer** |  |  [optional] |
|**mapIndex** | **Integer** |  |  [optional] |
|**maxTries** | **Integer** |  |  [optional] |
|**hostname** | **String** |  |  [optional] |
|**unixname** | **String** |  |  [optional] |
|**pool** | **String** |  |  [optional] |
|**poolSlots** | **Integer** |  |  [optional] |
|**queue** | **String** |  |  [optional] |
|**priorityWeight** | **Integer** |  |  [optional] |
|**operator** | **String** | *Changed in version 2.1.1*&amp;#58; Field becomes nullable.  |  [optional] |
|**queuedWhen** | **String** | The datetime that the task enter the state QUEUE, also known as queue_at  |  [optional] |
|**pid** | **Integer** |  |  [optional] |
|**executor** | **String** | Executor the task is configured to run on or None (which indicates the default executor)  *New in version 2.10.0*  |  [optional] |
|**executorConfig** | **String** |  |  [optional] |



