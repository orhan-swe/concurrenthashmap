package com.javacodegeeks.examples.concurrenthashmap.callables;

import java.util.concurrent.Callable;

import com.javacodegeeks.examples.concurrenthashmap.beans.Plane;
import com.javacodegeeks.examples.concurrenthashmap.service.DistribuitionPlaneService;
import com.javacodegeeks.examples.concurrenthashmap.service.DistribuitionVehicleService;

public class VehicleDistributor implements Callable<Plane> {
	public static enum OPERATION { ARRIVAL, DEPARTURE, INPROGRESS }
	
	private DistribuitionVehicleService<Plane> truckService;
	
	private Plane vehicle;
	private OPERATION operation;
	
	public VehicleDistributor() { }
	
	public VehicleDistributor(Plane vehicle, OPERATION operation) {
		this.vehicle = vehicle;
		this.operation = operation;
		
		this.truckService = DistribuitionPlaneService.getInstance();
	}

	@Override
	public Plane call() throws Exception {
		
		switch (this.operation) {
			case ARRIVAL:
				System.out.print("Arriving: ");
				this.truckService.arrivalQueue(this.vehicle);
				break;
			case DEPARTURE:
				System.out.print("Departing: ");
				this.truckService.departureQueue(this.vehicle);
				break;
			case INPROGRESS:
				System.out.print("In Progress: ");
				this.vehicle.setInprogress(this.truckService.unloadInProgress(this.vehicle));
				break;
		}
		
		return this.vehicle;
	}
}
