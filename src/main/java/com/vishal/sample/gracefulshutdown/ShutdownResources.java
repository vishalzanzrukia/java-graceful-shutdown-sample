package com.vishal.sample.gracefulshutdown;

import java.util.concurrent.ExecutorService;

public class ShutdownResources {

	private ExecutorService threadPool;

	public ExecutorService getThreadPool() {
		return threadPool;
	}

	public void setThreadPool(ExecutorService threadPool) {
		this.threadPool = threadPool;
	}

	// in your case it may be multiple resources to be shutdown
}
