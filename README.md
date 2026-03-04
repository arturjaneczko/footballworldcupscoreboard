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

`Board` - domain business logic: flow, rules, exceptions (`Game`, `Match`, `Team`, `TeamScore` - domain entities)

`Service` - **Board** delegate to **Service** valid (form business point of view) data, communicates with infrastructure

`Storage` - pure technical component to store data

---

# Changelog

## [1.0.0] 2026.04.03 | `#f46df471`

### added

- init project by adding `pom.xml`

## [1.0.0] 2026.04.03 | `#3f86fa06`

### added

- `.gitignore`

## [1.0.0] 2026.04.03 | `#ad517e46`

### added

- `README.MD`

## [1.0.0] 2026.04.03 | `#846f0c90`

### added

- dependencies: `commons-lang3`
- test dependencies: `jupiter`, `assertj`, `mockito`

## [1.0.0] 2026.04.03

### updated

- `README.MD`