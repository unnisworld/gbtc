package com.intuit.gbtc;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A simple implementation of Grid based traffic control system. This
 * implementation suggests the shortest possible path for a journey.
 * 
 * Limitation : If the suggested path collides with an in-transit vehicle's path
 * it doesn't have the capability to find an alternative path which may be
 * slightly longer.
 * 
 * @author uvalsala
 *
 */
public class SimpleGridBasedTrafficControl implements GridBasedTrafficControl {

	public SimpleGridBasedTrafficControl(int grid[][], PathCalculationStrategy pathCalculator,
			RouteConflictChecker routeConflitChecker) {
		this.grid = grid;
		this.pathCalculator = pathCalculator;
		this.routeConflitChecker = routeConflitChecker;
	}

	/**
	 * Uses a {@code PathCalculationStrategy} to allocate a route for the requested
	 * destination. If the suggested route conflicts with any in-transit vehicle,
	 * then an empty List is returned.
	 * 
	 * @param vehicleId
	 * @param src
	 *            source
	 * @param dest
	 *            destination
	 * @return a List containing the route or empty list if route is not available.
	 */
	public List<Point> allocateRoute(String vehicleId, Point src, Point dest) {
		List<Point> route = pathCalculator.getPath(grid, src, dest);

		if (routeConflitChecker.hasConflict(inTransitVehicles, route)) {
			System.err.println("Route allocation can result in collision. Please try after sometime.");
			return Collections.emptyList();
		}

		inTransitVehicles.put(vehicleId, route);

		return route;
	}

	public void updateLocation(String vehicleId, Point oldLoc, Point newLoc) {
		grid[newLoc.getX()][newLoc.getY()] = 1;
		grid[oldLoc.getX()][oldLoc.getY()] = 0;
	}

	public void markJourneyAsDone(String vehicleId, Point oldLoc, Point newLoc) {
		// Update the vehicle location.
		grid[newLoc.getX()][newLoc.getY()] = 1;
		grid[oldLoc.getX()][oldLoc.getY()] = 0;

		inTransitVehicles.remove(vehicleId);
	}

	private int grid[][];
	private PathCalculationStrategy pathCalculator;
	private RouteConflictChecker routeConflitChecker;
	private Map<String, List<Point>> inTransitVehicles = new ConcurrentHashMap<>();

}
