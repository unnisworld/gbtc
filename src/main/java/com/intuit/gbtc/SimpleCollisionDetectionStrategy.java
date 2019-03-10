package com.intuit.gbtc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A simple implementation of CollisionDetectionStrategy.
 * Does an intersection (A ∩ B)  of proposed route with routes of in-transit vehicles
 * to determine whether there is a chance of collision.
 * 
 * @author uvalsala
 *
 */
public class SimpleCollisionDetectionStrategy implements CollisionDetectionStrategy {

	@Override
	public boolean canCollide(Map<String, List<Point>> inTransitVehicles, List<Point> route) {
		
		Set<Point> routeSet = new HashSet<>(route);
		for (Map.Entry<String, List<Point>> inTransitRoutes : inTransitVehicles.entrySet()) {
			List<Point> inTransitRoute = inTransitRoutes.getValue();

			boolean hasConflit = hasConflit(inTransitRoute, routeSet);

			if (hasConflit) {
				return true;
			}
		}

		return false;
	}

	private boolean hasConflit(List<Point> inTransitRoute, Set<Point> routeSet) {
		for(Point p : inTransitRoute) {
			if (routeSet.contains(p)) {
				return true;
			}
		}

		return false;
	}

}
