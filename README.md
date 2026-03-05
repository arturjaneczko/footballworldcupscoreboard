#Football World Cup Score Board

---
##Solution

###Assumptions

Layers:
- **api** - access point
- **domain** - domain logic layer
- **service** - service logic (more technical) layer
- **infra** - infrastructure layer (storage)

Entities:

`Board` - domain business logic: flow, rules, exceptions (`Game`, `Match`, `Team`, `TeamScore`, `Summary` - domain entities)

`Service` - **Board** delegate to **Service** valid (form business point of view) data, communicates with infrastructure

`Storage` - pure technical component to store data