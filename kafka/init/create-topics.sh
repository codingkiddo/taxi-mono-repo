#!/usr/bin/env bash
set -euo pipefail
kafka-topics --bootstrap-server kafka:9092 --create --if-not-exists --topic driver.location.v1 --partitions 3 --replication-factor 1
kafka-topics --bootstrap-server kafka:9092 --create --if-not-exists --topic trip.lifecycle.v1 --partitions 3 --replication-factor 1
kafka-topics --bootstrap-server kafka:9092 --create --if-not-exists --topic payment.events.v1 --partitions 3 --replication-factor 1
kafka-topics --bootstrap-server kafka:9092 --create --if-not-exists --topic risk.flags.v1 --partitions 1 --replication-factor 1
kafka-topics --bootstrap-server kafka:9092 --create --if-not-exists --topic payout.events.v1 --partitions 1 --replication-factor 1
echo "Kafka topics created or already exist."
