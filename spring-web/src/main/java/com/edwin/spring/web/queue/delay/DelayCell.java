package com.edwin.spring.web.queue.delay;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.Delayed;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

/**
 * 延时队列元素
 *
 * @author edwin
 * @date 2021/12/4 6:08 下午
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
        this(cell, timestamp, 0L, 0L);
    }

    public DelayCell(T cell,  long timestamp, long sequenceNumber) {
        this(cell, timestamp, sequenceNumber, 0L);
    }

    public DelayCell() {
        this(null, 0L, 0L, 0L);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(timestamp - System.currentTimeMillis(), MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        if (o == this) // compare zero if same object
            return 0;
        if (o instanceof DelayCell) {
            DelayCell<?> x = (DelayCell<?>)o;
            long diff = timestamp - x.timestamp;
            if (diff < 0)
                return -1;
            else if (diff > 0)
                return 1;
            else if (sequenceNumber < x.sequenceNumber)
                return -1;
            else
                return 1;
        }
        long diff = getDelay(MILLISECONDS) - o.getDelay(MILLISECONDS);
        return (diff < 0) ? -1 : (diff > 0) ? 1 : 0;
    }
}
