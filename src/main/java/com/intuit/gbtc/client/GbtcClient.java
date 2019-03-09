package com.intuit.gbtc.client;

import java.util.List;

import com.intuit.gbtc.Configurator;
import com.intuit.gbtc.DefaultConfigurator;
import com.intuit.gbtc.GridBasedTrafficControl;
import com.intuit.gbtc.Point;


/**
 *  Class used to demo the Grid based traffic control system.
 *  
 * @author uvalsala
 *
 */
public class GbtcClient {

	public static void main(String[] args) {
		// The traffic grid.
		// '0' represents empty location in the grid.
		// '1' represents location where vehicle is present.
		int grid[][] = new int[][]
			    { 
			        { 1, 1, 0, 0, 0, 0, 1, 0, 0, 0 }, 
			        { 0, 1, 0, 1, 0, 0, 0, 1, 0, 0 }, 
			        { 0, 0, 0, 1, 0, 0, 1, 0, 1, 0 }, 
			        { 1, 1, 1, 1, 0, 1, 1, 1, 1, 0 }, 
			        { 0, 0, 0, 1, 0, 0, 0, 1, 0, 1 }, 
			        { 0, 1, 0, 0, 0, 0, 1, 0, 1, 1 }, 
			        { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, 
			        { 0, 1, 0, 0, 0, 0, 1, 0, 0, 0 }, 
			        { 0, 0, 1, 1, 1, 1, 0, 1, 1, 0 } 
			    };
			    
		Configurator cfg = new DefaultConfigurator();
		GridBasedTrafficControl gbtc = cfg.getGridBasedTrafficControl(grid);
		
	    // Journey-1
	    Point source1 = new Point(0, 0);
	    Point dest1 = new Point(3,4);	    
	    String vehicleId1 = formVehicleId(source1);
	    List<Point> path1 = gbtc.allocateRoute(vehicleId1, source1, dest1);
	    System.out.println("Route for Journey-1:" + path1);
	    
	    // Journey-2
	    Point source2 = new Point(3, 0);
	    Point dest2 = new Point(2,4);
	    String vehicleId2 = formVehicleId(source2);
	    // This call will not return a route as the available route conflicts with an in-transit vehicle.
	    List<Point> path2 = gbtc.allocateRoute(vehicleId2, source2, dest2);
	    System.out.println("Route for Journey-2:" + path2);
	    
	    // Start Journey-1
	    GridConnectedVehicle vehicle1 = new GridConnectedVehicle(vehicleId1, gbtc);
	    vehicle1.selfDrive(path1);
	    
	    // Mark Journey-1 as complete.
	    gbtc.markJourneyAsDone(vehicleId1, source1, dest1);
	    
	    // This call should return a valid path as Journey-1 was marked as complete. 
	    path2 = gbtc.allocateRoute(formVehicleId(source2), source2, dest2);
	    System.out.println("Route for Journey-2:" + path2);
	    GridConnectedVehicle vehicle2 = new GridConnectedVehicle(vehicleId2, gbtc);
	    vehicle2.selfDrive(path2);
	    gbtc.markJourneyAsDone(vehicleId2, source2, dest2);
	}

	private static String formVehicleId(Point source) {
		return "V{"+ source.getX() + "," + source.getY() + "}";
	}
}
