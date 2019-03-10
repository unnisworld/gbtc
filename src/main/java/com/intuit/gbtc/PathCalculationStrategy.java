package com.intuit.gbtc;

import java.util.List;

/**
 * Defines an interface for path calculation for a Journey.
 * 
 * @author uvalsala
 *
 */
public interface PathCalculationStrategy {
	
	/**
	 * Returns the shortest path for a Journey.
	 * 
	 * @param grid the traffic grid.
	 * @param src the source location.
	 * @param dest the destination location.
	 * 
	 * @return a List containing the path for the Journey.
	 */
	List<Point> getPath(int grid[][], Point src, Point dest);
}