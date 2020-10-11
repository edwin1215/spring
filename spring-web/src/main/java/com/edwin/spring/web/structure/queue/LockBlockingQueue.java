package com.edwin.spring.web.structure.queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 基于Lock实现的blocking queue
 * 
 * @author caojunming
 *
 */
public class LockBlockingQueue<T> {

	Object[] queue = new Object[2];
	final Lock lock = new ReentrantLock();
	private Condition isFull = lock.newCondition();
	private Condition isEmpty = lock.newCondition();

	int poi, pui, count;

	@SuppressWarnings("unchecked")
	public T bpoll() {
		lock.lock();
		System.out.println("bpoll lock");
		T t = null;
		try {
			while (count == 0) {
				System.out.println("queue is empty.");
				isEmpty.await();
			}
			t = (T) queue[poi];
			if (++poi == queue.length) {
				poi = 0;
			}
			count--;
			System.out.println("isFull 唤醒");
			isFull.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
			System.out.println("bpoll 解锁");
		}
		return t;
	}

	public void bpush(T t) {
		lock.lock();
		System.out.println("bpush lock");
		try {
			while (count == queue.length) {
				System.out.println("queue is full.");
				isFull.await();
			}
			queue[pui] = t;
			if (++pui == queue.length) {
				pui = 0;
			}
			count++;
			System.out.println("isEmpty 唤醒");
			isEmpty.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
			System.out.println("bpush 解锁");
		}
	}

	public static void main(String[] args) {
		final LockBlockingQueue<String> queue = new LockBlockingQueue<>();
		System.out.println(Thread.currentThread() + "," + queue);
		new Thread(() -> {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread() + "," + queue);
                queue.bpush("xx");
                queue.bpush("yy");
                queue.bpush("zz");
                queue.bpush("zz");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
		System.out.println(queue.bpoll());
	}
}
