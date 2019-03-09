package com.intuit.gbtc.client;

import java.util.List;

import com.intuit.gbtc.GridBasedTrafficControl;
import com.intuit.gbtc.Point;

public class GridConnectedVehicle {

	GridConnectedVehicle(String vehicleId, GridBasedTrafficControl gbtc) {
		this.vehicleId = vehicleId;
		this.gbtc = gbtc;
	}

	void selfDrive(List<Point> path) {
		System.out.println("Vehicle "+ vehicleId + " starting journey.");
		boolean firstTime = true;
		Point oldLoc = null, newLoc = null;
		for (Point p : path) {
			if (firstTime) {
				oldLoc = p;
				firstTime = false;
				continue;
			}
			newLoc = p;
			
			System.out.print("Reached... " + p + " ,");
			gbtc.updateLocation(vehicleId, oldLoc, newLoc);
			oldLoc = p;
		}
		
		System.out.println();
	}

	private String vehicleId;
	private GridBasedTrafficControl gbtc;
}
