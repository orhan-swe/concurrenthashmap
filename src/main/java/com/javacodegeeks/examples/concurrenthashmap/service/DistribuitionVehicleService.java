package com.javacodegeeks.examples.concurrenthashmap.service;

public interface DistribuitionVehicleService<T> {
	public void arrivalQueue(T vehicle);
	public boolean unloadInProgress(T vehicle);
	public void departureQueue(T vehicle);
}
