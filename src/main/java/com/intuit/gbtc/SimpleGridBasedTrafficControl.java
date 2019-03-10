package com.intuit.gbtc;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Responsible for allocating the shortest possible path for a journey to a
 * Vehicle.
 * 
 * Shortest possible path calculation strategy is dependent on the
 * implementation of {@code PathCalculationStrategy} that is supplied to it
 * during construction. The path thus obtained for a journey is checked for any
 * possible collision with vehicles in transit using the
 * {@code RouteConflictChecker}. If a conflict is detected, then an empty path
 * is returned and vehicle is expected to retry after sometime.
 * 
 * Known Limitation : If the suggested path collides with an in-transit
 * vehicle's path it doesn't have the capability to find an alternative path
 * which may be slightly longer.
 * 
 * @ThreadSafe : This class is designed as a Thread Safe class. All methods in
 *             this implementation are marked as synchronized as they either
 *             mutate the internal state or depend on the internal state to
 *             deliver correct results.
 * 
 * @author uvalsala
 * @see PathCalculationStrategy
 * @see CollisionDetectionStrategy
 */
public class SimpleGridBasedTrafficControl implements GridBasedTrafficControl {

	public SimpleGridBasedTrafficControl(int grid[][], PathCalculationStrategy pathCalculator,
			CollisionDetectionStrategy routeConflitChecker) {
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
	@Override
	public synchronized List<Point> allocateRoute(String vehicleId, Point src, Point dest) {
		List<Point> route = pathCalculator.getPath(grid, src, dest);

		if (routeConflitChecker.canCollide(inTransitVehicles, route)) {
			System.err.println("Route allocation can result in collision. Please try after sometime.");
			return Collections.emptyList();
		}

		inTransitVehicles.put(vehicleId, route);

		return route;
	}

	@Override
	public synchronized void updateLocation(String vehicleId, Point oldLoc, Point newLoc) {
		grid[newLoc.getX()][newLoc.getY()] = 1;
		grid[oldLoc.getX()][oldLoc.getY()] = 0;
	}

	@Override
	public synchronized void markJourneyComplete(String vehicleId, Point oldLoc, Point newLoc) {
		// Update the vehicle location.
		grid[newLoc.getX()][newLoc.getY()] = 1;
		grid[oldLoc.getX()][oldLoc.getY()] = 0;

		inTransitVehicles.remove(vehicleId);
	}

	private int grid[][];
	private PathCalculationStrategy pathCalculator;
	private CollisionDetectionStrategy routeConflitChecker;
	private Map<String, List<Point>> inTransitVehicles = new HashMap<>();

}
