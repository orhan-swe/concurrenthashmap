package com.javacodegeeks.examples.concurrenthashmap.service;

import java.util.HashMap;
import java.util.Map;

import com.javacodegeeks.examples.concurrenthashmap.beans.Plane;

public class DistribuitionPlaneService implements
		DistribuitionVehicleService<Plane> {
	// Ensure that ONLY ONE ConcurrentHashMap is used for every thread
//	private static final ConcurrentMap<Truck, Long> vehicleQueue = new ConcurrentHashMap<>();
	private static final Map<Plane, Long> vehicleQueue = new HashMap<>();
	private static DistribuitionPlaneService singelton = null;

	private DistribuitionPlaneService(){}
	
	public synchronized static DistribuitionPlaneService getInstance() {
		//race condition
		if (singelton == null ) {
			singelton = new DistribuitionPlaneService();
		}
		return singelton;
	}
	@Override
	public void arrivalQueue(Plane vehicle) {
		long currentTime = System.currentTimeMillis();
		DistribuitionPlaneService.vehicleQueue.putIfAbsent(vehicle, currentTime);
	}

	@Override
	public boolean unloadInProgress(Plane vehicle) {
		return DistribuitionPlaneService.vehicleQueue.get(vehicle) != null;
	}
	
	@Override
	public void departureQueue(Plane vehicle) {
		DistribuitionPlaneService.vehicleQueue.remove(vehicle);
	}
}
