package com.intuit.gbtc;

import java.util.List;

public interface PathCalculationStrategy {
	List<Point> getPath(int grid[][], Point src, Point dest);
}