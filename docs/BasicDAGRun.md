

# BasicDAGRun


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**runId** | **String** | Run ID.  |  [optional] |
|**dagId** | **String** |  |  [optional] [readonly] |
|**logicalDate** | **OffsetDateTime** | The logical date (previously called execution date). This is the time or interval covered by this DAG run, according to the DAG definition.  The value of this field can be set only when creating the object. If you try to modify the field of an existing object, the request fails with an BAD_REQUEST error.  This together with DAG_ID are a unique key.  *New in version 2.2.0*  |  [optional] |
|**startDate** | **OffsetDateTime** | The start time. The time when DAG run was actually created.  *Changed in version 2.1.3*&amp;#58; Field becomes nullable.  |  [optional] [readonly] |
|**endDate** | **OffsetDateTime** |  |  [optional] [readonly] |
|**dataIntervalStart** | **OffsetDateTime** |  |  [optional] [readonly] |
|**dataIntervalEnd** | **OffsetDateTime** |  |  [optional] [readonly] |
|**state** | **DagState** |  |  [optional] |



