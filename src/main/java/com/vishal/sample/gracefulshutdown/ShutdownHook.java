package com.vishal.sample.gracefulshutdown;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

public class ShutdownHook extends Thread {

	private static final Logger LOG = Logger.getLogger(ShutdownHook.class);

	private final ShutdownResources shutdownResources;
	
	// Maximum number of seconds to wait before force shutdown
	private final int waitBeforeForceShutdownInSec;

	public ShutdownHook(final ShutdownResources shutdownResources, final int waitBeforeForceShutdownInSec) {
		this.shutdownResources = shutdownResources;
		this.waitBeforeForceShutdownInSec = waitBeforeForceShutdownInSec;
	}

	@Override
	public void run() {
		ExecutorService threadPool = shutdownResources.getThreadPool();

		LOG.info("Shuting down the application, terminating the thread pool, will wait maximum "
				+ waitBeforeForceShutdownInSec + " secs to complete any pending tasks!");
		threadPool.shutdown();
		try {
			if (!threadPool.awaitTermination(waitBeforeForceShutdownInSec, TimeUnit.SECONDS)) {
				LOG.warn("Tasks did not complete within " + waitBeforeForceShutdownInSec
						+ " secs, terminating now forcefully!");
				threadPool.shutdownNow();
			} else {
				LOG.info("Tasks completed successfully within " + waitBeforeForceShutdownInSec + " secs!");
			}
		} catch (InterruptedException ex) {
			LOG.warn("Thread interrupted while running shutdown hook", ex);
			threadPool.shutdownNow();
			Thread.currentThread().interrupt();
		}
	}
}
