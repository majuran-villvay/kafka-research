#! /bin/bash
docker-compose down && docker container prune -f && docker-compose up -d

sleep 30

# Run this command to create a topic named users.
docker-compose exec broker kafka-topics --create --bootstrap-server \
localhost:9092 --replication-factor 1 --partitions 1 --topic spring.boot.kafka.stream.input

docker-compose exec broker kafka-topics --create --bootstrap-server \
localhost:9092 --replication-factor 1 --partitions 1 --topic spring.boot.kafka.stream.output