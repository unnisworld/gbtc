package com.intuit.gbtc;

import java.util.List;

/**
 * Interface definition for Grid based traffic control.
 * 
 * @author uvalsala
 *
 */
public interface GridBasedTrafficControl {
	
	/**
	 * Allocates a route to a vehicle.
	 * 
	 * 
	 * @param src the source location.
	 * @param dest the destination.
	 * @return A List containing coordinates to follow 
	 * 		   in order to reach the destination.
	 */
	List<Point> allocateRoute(String vehicleId, Point src, Point dest);
	
	/**
	 * 
	 * @param vehicleId
	 * @param oldLoc
	 * @param newLoc
	 */
	void updateLocation(String vehicleId, Point oldLoc, Point newLoc);
	
	void markJourneyAsDone(String vehicleId,  Point oldLoc, Point newLoc);
}
