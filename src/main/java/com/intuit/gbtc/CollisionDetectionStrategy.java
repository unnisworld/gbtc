package com.intuit.gbtc;

import java.util.List;
import java.util.Map;

/**
 * Defines an interface for checking for possible collision of routes.
 * 
 * @author uvalsala
 *
 */
public interface CollisionDetectionStrategy {
	
	/**
	 * Checks whether a proposed route can collide with in transit vehicles.
	 * 
	 * @param inTransitVehiclesRoute route used by vehicles that are in transit.
	 * @param proposedRoute the proposed route for new journey.
	 * @return boolean true if there is a chance of collision.
	 */
	boolean canCollide(Map<String, List<Point>> inTransitVehiclesRoute, List<Point> proposedRoute);
}
