

# Pool

The pool

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**name** | **String** | The name of pool. |  [optional] |
|**slots** | **Integer** | The maximum number of slots that can be assigned to tasks. One job may occupy one or more slots.  |  [optional] |
|**occupiedSlots** | **Integer** | The number of slots used by running/queued tasks at the moment. May include deferred tasks if &#39;include_deferred&#39; is set to true. |  [optional] [readonly] |
|**runningSlots** | **Integer** | The number of slots used by running tasks at the moment. |  [optional] [readonly] |
|**queuedSlots** | **Integer** | The number of slots used by queued tasks at the moment. |  [optional] [readonly] |
|**openSlots** | **Integer** | The number of free slots at the moment. |  [optional] [readonly] |
|**scheduledSlots** | **Integer** | The number of slots used by scheduled tasks at the moment. |  [optional] [readonly] |
|**deferredSlots** | **Integer** | The number of slots used by deferred tasks at the moment. Relevant if &#39;include_deferred&#39; is set to true.  *New in version 2.7.0*  |  [optional] [readonly] |
|**description** | **String** | The description of the pool.  *New in version 2.3.0*  |  [optional] |
|**includeDeferred** | **Boolean** | If set to true, deferred tasks are considered when calculating open pool slots.  *New in version 2.7.0*  |  [optional] |



