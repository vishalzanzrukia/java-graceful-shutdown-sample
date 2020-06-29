package com.vishal.sample.gracefulshutdown;

import org.apache.log4j.Logger;

public class Task implements Runnable {

	private static final Logger LOG = Logger.getLogger(Task.class);

	private String name;

	public Task(String s) {
		name = s;
	}

	public void run() {
		try {
			LOG.info(name + " started");
			for (int i = 0; i <= 50; i++) {
				LOG.info("[" + i + "]Executing task - " + name);
				Thread.sleep(2000);
			}
			LOG.info(name + " success");
		} catch (InterruptedException e) {
			LOG.info(name + " interrupted");
			Thread.currentThread().interrupt();
		} finally {
			LOG.info(name + " completed from finally");
		}
	}
}
