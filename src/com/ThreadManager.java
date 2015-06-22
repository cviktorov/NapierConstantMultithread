package com;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class ThreadManager {

	private int threads;
	private ExecutorService threadServise;
	private E e;
	private int digits;
	private Long time ;

	public ThreadManager(int threads, int digits) {
		this.threads = threads;
		this.digits = digits;
		threadServise = Executors.newCachedThreadPool();
		e = new E();
		time = System.currentTimeMillis();
		runThreads();
	}

	private void runThreads() {
		for (int i = 0; i < threads; i++) {
			threadServise.execute(new CalculatorThread(i, threads, digits, e, time));

		}
		threadServise.shutdown();
	}

	public E getE() {
		while (!threadServise.isTerminated()) {}
		return e;
	}

	public void setE(E e) {
		this.e = e;
	}


}
