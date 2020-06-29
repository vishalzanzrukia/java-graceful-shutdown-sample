package com.vishal.sample.gracefulshutdown;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

public class GracefulShutdown {

	private static final Logger LOG = Logger.getLogger(GracefulShutdown.class);

	// Maximum number of threads in thread pool
	private static final int MAX_THREADS = 3;

	// Maximum number of seconds to wait before force shutdown, if not defined by
	// use input
	private static final int DEFAULT_SHUTDOWN_WAIT_TIME_IN_SEC = 60;

	public static void main(String[] args) {
		int i = 0;
		int waitBeforeForceShutdownInSec = DEFAULT_SHUTDOWN_WAIT_TIME_IN_SEC;
		while (args != null && i < args.length) {
			LOG.info("Command line argument [" + i + "] : " + args[i]);
			if (i == 0) {
				try {
					waitBeforeForceShutdownInSec = Integer.valueOf(args[i]);
				} catch (NumberFormatException e) {
					throw new IllegalArgumentException(
							"Firsr argument must be time in seconds to wait before force shutdown");
				}
			}
			i++;
		}

		GracefulShutdown app = new GracefulShutdown();
		ExecutorService threadPool = Executors.newFixedThreadPool(MAX_THREADS);

		ShutdownResources shutdownResources = new ShutdownResources();
		shutdownResources.setThreadPool(threadPool);

		Runtime.getRuntime().addShutdownHook(new ShutdownHook(shutdownResources, waitBeforeForceShutdownInSec));
		LOG.info("Shutdown hook registered successfully");

		app.runTasks(threadPool);
		LOG.info("Application tasks triggered successfully");

	}

	private void runTasks(ExecutorService pool) {
		// creates five tasks
		Runnable r1 = new Task("Task-1");
		Runnable r2 = new Task("Task-2");
		Runnable r3 = new Task("Task-3");
		Runnable r4 = new Task("Task-4");
		Runnable r5 = new Task("Task-5");
		//100  + 100 = 200 

		// passes the Task objects to the pool to execute (Step 3)
		pool.execute(r1);
		pool.execute(r2);
		pool.execute(r3);
		pool.execute(r4);
		pool.execute(r5);
		// pool.shutdown();
	}
}
