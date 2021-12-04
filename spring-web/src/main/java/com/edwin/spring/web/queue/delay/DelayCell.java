package com.edwin.spring.web.queue.delay;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

/**
 * 延时队列元素
 */
@Getter
@Setter
@AllArgsConstructor
public class DelayCell<T> implements Delayed {

    private T cell;

    private long timestamp;

    private final long sequenceNumber;

    private final long period;

    public DelayCell(T cell,  long timestamp) {
        this.cell = cell;
        this.timestamp = timestamp;
        this.sequenceNumber = 0L;
        this.period = 0L;
    }

    public DelayCell() {
        this.sequenceNumber = 0L;
        this.period = 0L;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(timestamp - System.currentTimeMillis(), MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return 0;
    }
}
