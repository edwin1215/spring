package com.edwin.spring.web.jvm.jdk8;

import java.util.Arrays;
import java.util.Collection;

/**
 * Streams
 * 
 * @author caojunming
 * @data 2017年11月4日 下午2:45:02
 */
public class StreamsTest {
	private enum Status {
		OPEN, CLOSED
	};

	private static final class Task {
		private final Status status;
		private final Integer points;

		Task(final Status status, final Integer points) {
			this.status = status;
			this.points = points;
		}

		public Integer getPoints() {
			return points;
		}

		public Status getStatus() {
			return status;
		}

		@Override
		public String toString() {
			return String.format("[%s, %d]", status, points);
		}
	}

	public static void main(String[] args) {
		Collection<Task> tasks = Arrays.asList(new Task(Status.OPEN, 5),
				new Task(Status.OPEN, 13), new Task(Status.CLOSED, 8));

		long totalPointsOfOpenTasks = tasks.stream()
				.filter(task -> task.getStatus() == Status.OPEN)
				.mapToInt(Task::getPoints).sum();

		System.out.println("Total points: " + totalPointsOfOpenTasks);
	}
}
