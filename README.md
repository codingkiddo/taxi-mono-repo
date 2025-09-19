# Taxi Booking App — Mono‑repo (Skeleton)

**Stack**: Spring Boot 3.3 (Java 21), Docker Compose, Postgres/PostGIS, Kafka, Redis, Keycloak (dev), Jaeger, Grafana. Two Flutter shells can be added later.

## Quick Start

```bash
# 1) Build all services
mvn -q -DskipTests package

# 2) Docker Compose up (build images & run)
docker compose up --build
```

Services (default dev ports):
- Gateway: http://localhost:8088
- Rider:   http://localhost:8081
- Driver:  http://localhost:8082
- Dispatch:http://localhost:8083
- Trip:    http://localhost:8084
- Fare:    http://localhost:8085
- Payment: http://localhost:8086

## Smoke Tests

1) Fare estimate via **gateway → rider → fare**

```bash
curl -s http://localhost:8088/rider/estimate -H 'Content-Type: application/json' -d '{
  "pickup":{"lat":12.9716,"lng":77.5946},
  "drop":{"lat":13.0359,"lng":77.5970},
  "category":"SEDAN"
}'
```

2) Driver online and dispatch ranking

```bash
curl -XPOST 'http://localhost:8088/driver/status?driverId=D1&status=ONLINE'
curl -XPOST 'http://localhost:8088/dispatch/rank?requestId=REQ1' -H 'Content-Type: application/json' -d '["D1","D2","D3"]'
```

3) Trip start/finish

```bash
curl -XPOST 'http://localhost:8088/trip/123/start?riderId=R1&driverId=D1'
curl -XPOST 'http://localhost:8088/trip/123/finish'
```

4) Payment init

```bash
curl -XPOST 'http://localhost:8088/payment/123/init?method=UPI'
```

> This is a functional skeleton intended for local dev. Hardening (Keycloak/OIDC, DB persistence, Kafka topics, Flyway migrations, Observability config) can be layered on next.


## New: Flyway, Schema Registry, and Keycloak Realm

- **DB migrations**: A Flyway container `db-migrator` runs once and applies SQL from `db/migrations`. Edit `V1__baseline_postgis.sql` to evolve schema.
- **Kafka topics**: `kafka-init` creates required topics. Avro schemas are in `schemas/*.avsc`. Schema Registry at http://localhost:8081
- **Keycloak realm import**: A dev realm `taxi` auto-imports with realm roles (`RIDER`, `DRIVER`, `ADMIN`) and a sample `admin/admin` user. Keycloak UI: http://localhost:8080

### Bring-up
```bash
docker compose up --build
# Wait for postgres → db-migrator applies migrations
# Keycloak imports the taxi realm automatically
```

### Verifications
```bash
# Topics list
docker compose exec kafka kafka-topics --bootstrap-server kafka:9092 --list

# Schema Registry health
curl -s http://localhost:8081/subjects | jq .

# Keycloak realm check (login with admin/admin at UI)
open http://localhost:8080
```
