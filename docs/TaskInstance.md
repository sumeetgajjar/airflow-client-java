

# TaskInstance


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**taskId** | **String** |  |  [optional] |
|**taskDisplayName** | **String** | Human centric display text for the task.  *New in version 2.9.0*  |  [optional] |
|**dagId** | **String** |  |  [optional] |
|**dagRunId** | **String** | The DagRun ID for this task instance  *New in version 2.3.0*  |  [optional] |
|**executionDate** | **String** |  |  [optional] |
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
|**slaMiss** | [**SLAMiss**](SLAMiss.md) |  |  [optional] |
|**renderedMapIndex** | **String** | Rendered name of an expanded task instance, if the task is mapped.  *New in version 2.9.0*  |  [optional] |
|**renderedFields** | **Object** | JSON object describing rendered fields.  *New in version 2.3.0*  |  [optional] |
|**trigger** | [**Trigger**](Trigger.md) |  |  [optional] |
|**triggererJob** | [**Job**](Job.md) |  |  [optional] |
|**note** | **String** | Contains manually entered notes by the user about the TaskInstance.  *New in version 2.5.0*  |  [optional] |



