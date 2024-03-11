package processingordermessage.core.util;

public class SnowflakeIdGenerator {
    private long sequence = 0L;
    private final long nodeId;
    private long lastTimestamp = -1L;

    private final long epoch = 1288834974657L;
    private final long nodeIdBits = 5L;
    private final long sequenceBits = 12L;

    private final long maxNodeId = -1L ^ (-1L << nodeIdBits);
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    private final long nodeIdShift = sequenceBits;
    private final long timestampLeftShift = sequenceBits + nodeIdBits;

    public SnowflakeIdGenerator(long nodeId) {
        if (nodeId > maxNodeId || nodeId < 0) {
            throw new IllegalArgumentException(String.format("nodeId must be between 0 and %d", maxNodeId));
        }
        this.nodeId = nodeId;
    }

    public synchronized long nextId() {
        long timestamp = timeGen();

        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards. Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }

        lastTimestamp = timestamp;
        return ((timestamp - epoch) << timestampLeftShift) |
                (nodeId << nodeIdShift) |
                sequence;
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }
}
