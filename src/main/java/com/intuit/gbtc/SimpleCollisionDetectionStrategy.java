package com.intuit.gbtc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SimpleRouteConflictChecker implements RouteConflictChecker {

	@Override
	public boolean hasConflict(Map<String, List<Point>> inTransitVehicles, List<Point> route) {
		for (Map.Entry<String, List<Point>> inTransitRoutes : inTransitVehicles.entrySet()) {
			List<Point> inTransitRoute = inTransitRoutes.getValue();

			boolean hasConflit = hasConflit(inTransitRoute, route);

			if (hasConflit) {
				return true;
			}
		}

		return false;
	}

	private boolean hasConflit(List<Point> inTransitRoute, List<Point> route) {
		List<Point> copy = new ArrayList<Point>(inTransitRoute);
		copy.retainAll(route);

		// if intersection has elements
		return copy.size() != 0 ? true : false;
	}

}
