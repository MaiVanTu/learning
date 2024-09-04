1. Run docker compose:
docker-compose up -d
2. Kafka UI: provectuslabs/kafka-ui
3. Kafka structure
3.1 Kafka visualization: https://softwaremill.com/kafka-visualisation/
3.2 Partition and Consumer Group
- Number partitions == consumer : 1-1
- Number partitions < consumer : 1-1
- partition > consumer: 1 consumer consume the message fron more than 2 partitions
- Issue: Làm sao quản lý partition: tạo thêm partition và xoá partition
4. Kafka retention
- Segment: define the logic in order to create a new log segment if reach timeout or file size
    + segment.ms
    + segment.bytes
- Retention: define the logic to delete a log segment
    + retention.ms: the latest message in the log segment timout
5. ACK
- Producer: ack means producer want to sure 0,1 or all, replicas in-sync
- Consumer: ack mean commit offset
    + Auto commit: every 3s and commit the last offset of the last batch
6. Health check: every health check is config on consumer and send to broker to tell broker know that properties
- Heartbeat: 3s (separeated theard)
- Session: 10s (just property config send to broker when consumer connect to broker)
- Poll timeout: 5m (Poll is called again after the last batch processed)
https://www.oreilly.com/library/view/kafka-the-definitive/9781491936153/ch04.html
7. Rebalancing
- Consumer Join/Leave:
    + When closing a consumer cleanly, the consumer will notify the group coordinator that it is leaving, and the group coordinator will trigger a rebalance immediately, reducing the gap in processing
- Consumer Timeout: consumer dead - don't send heartbeat => session timeout, poll > 5m
- Partition changes: Add/Remove