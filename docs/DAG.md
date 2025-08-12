

# DAG

DAG

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**dagId** | **String** | The ID of the DAG. |  [optional] [readonly] |
|**dagDisplayName** | **String** | Human centric display text for the DAG.  *New in version 2.9.0*  |  [optional] [readonly] |
|**rootDagId** | **String** | If the DAG is SubDAG then it is the top level DAG identifier. Otherwise, null. |  [optional] [readonly] |
|**isPaused** | **Boolean** | Whether the DAG is paused. |  [optional] |
|**isActive** | **Boolean** | Whether the DAG is currently seen by the scheduler(s).  *New in version 2.1.1*  *Changed in version 2.2.0*&amp;#58; Field is read-only.  |  [optional] [readonly] |
|**isSubdag** | **Boolean** | Whether the DAG is SubDAG. |  [optional] [readonly] |
|**lastParsedTime** | **OffsetDateTime** | The last time the DAG was parsed.  *New in version 2.3.0*  |  [optional] [readonly] |
|**lastPickled** | **OffsetDateTime** | The last time the DAG was pickled.  *New in version 2.3.0*  |  [optional] [readonly] |
|**lastExpired** | **OffsetDateTime** | Time when the DAG last received a refresh signal (e.g. the DAG&#39;s \&quot;refresh\&quot; button was clicked in the web UI)  *New in version 2.3.0*  |  [optional] [readonly] |
|**schedulerLock** | **Boolean** | Whether (one of) the scheduler is scheduling this DAG at the moment  *New in version 2.3.0*  |  [optional] [readonly] |
|**pickleId** | **String** | Foreign key to the latest pickle_id  *New in version 2.3.0*  |  [optional] [readonly] |
|**defaultView** | **String** | Default view of the DAG inside the webserver  *New in version 2.3.0*  |  [optional] [readonly] |
|**fileloc** | **String** | The absolute path to the file. |  [optional] [readonly] |
|**fileToken** | **String** | The key containing the encrypted path to the file. Encryption and decryption take place only on the server. This prevents the client from reading an non-DAG file. This also ensures API extensibility, because the format of encrypted data may change.  |  [optional] [readonly] |
|**owners** | **List&lt;String&gt;** |  |  [optional] [readonly] |
|**description** | **String** | User-provided DAG description, which can consist of several sentences or paragraphs that describe DAG contents.  |  [optional] [readonly] |
|**scheduleInterval** | [**ScheduleInterval**](ScheduleInterval.md) |  |  [optional] |
|**timetableDescription** | **String** | Timetable/Schedule Interval description.  *New in version 2.3.0*  |  [optional] [readonly] |
|**tags** | [**List&lt;Tag&gt;**](Tag.md) | List of tags. |  [optional] [readonly] |
|**maxActiveTasks** | **Integer** | Maximum number of active tasks that can be run on the DAG  *New in version 2.3.0*  |  [optional] [readonly] |
|**maxActiveRuns** | **Integer** | Maximum number of active DAG runs for the DAG  *New in version 2.3.0*  |  [optional] [readonly] |
|**hasTaskConcurrencyLimits** | **Boolean** | Whether the DAG has task concurrency limits  *New in version 2.3.0*  |  [optional] [readonly] |
|**hasImportErrors** | **Boolean** | Whether the DAG has import errors  *New in version 2.3.0*  |  [optional] [readonly] |
|**nextDagrun** | **OffsetDateTime** | The logical date of the next dag run.  *New in version 2.3.0*  |  [optional] [readonly] |
|**nextDagrunDataIntervalStart** | **OffsetDateTime** | The start of the interval of the next dag run.  *New in version 2.3.0*  |  [optional] [readonly] |
|**nextDagrunDataIntervalEnd** | **OffsetDateTime** | The end of the interval of the next dag run.  *New in version 2.3.0*  |  [optional] [readonly] |
|**nextDagrunCreateAfter** | **OffsetDateTime** | Earliest time at which this &#x60;&#x60;next_dagrun&#x60;&#x60; can be created.  *New in version 2.3.0*  |  [optional] [readonly] |
|**maxConsecutiveFailedDagRuns** | **Integer** | (experimental) The maximum number of consecutive DAG failures before DAG is automatically paused.  *New in version 2.9.0*  |  [optional] [readonly] |



