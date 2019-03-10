package com.intuit.gbtc;

import java.util.List;
import java.util.Map;

public interface RouteConflictChecker {
	boolean hasConflict(Map<String, List<Point>> inTransitVehicles, List<Point> route);
}
