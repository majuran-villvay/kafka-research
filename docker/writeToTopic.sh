#! /bin/bash

docker exec --interactive --tty broker \
kafka-console-producer --bootstrap-server broker:9092 \
                       --topic spring.boot.kafka.stream.input