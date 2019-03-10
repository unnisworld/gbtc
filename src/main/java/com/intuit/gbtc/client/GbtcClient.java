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
		// Initialize a traffic grid.
		// '0' represent empty location in the grid.
		// '1' represent location where vehicle is present.
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
		
        // Step 1: Obtain an instance of GridBasedTrafficControl.
        Configurator cfg = new DefaultConfigurator();
        GridBasedTrafficControl gbtc = cfg.getGridBasedTrafficControl(grid);
		
	    // Step 2: Obtain path for Journey-1.
	    Point source1 = new Point(0, 0);
	    Point dest1 = new Point(3,4);	    
	    String vehicleId1 = formVehicleId(source1);
	    List<Point> path1 = gbtc.allocateRoute(vehicleId1, source1, dest1);
	    System.out.println("Route for Journey-1:" + path1);
	    
	    // Step 3: Obtain path for Journey-2.
	    // This call will not return a route as the available route conflicts with the route
	    // allocated for Journey-1.
	    Point source2 = new Point(3, 0);
	    Point dest2 = new Point(2,4);
	    String vehicleId2 = formVehicleId(source2);
	    List<Point> path2 = gbtc.allocateRoute(vehicleId2, source2, dest2);
	    System.out.println("Route for Journey-2:" + path2);
	    
	    // Step 4: Start Journey-1 and mark Journey-1 as complete.
	    GridConnectedVehicle vehicle1 = new GridConnectedVehicle(vehicleId1, gbtc);
	    vehicle1.selfDrive(path1);
	    gbtc.markJourneyComplete(vehicleId1, source1, dest1);
	    
	    // Step 5: Retry call to obtain path for Journey-2.
	    // This call should return a valid path as Journey-1 was marked as complete.
	    path2 = gbtc.allocateRoute(formVehicleId(source2), source2, dest2);
	    System.out.println("Route for Journey-2:" + path2);
	    
	    // Step 6: Start Journey-2 and mark Journey-2 as complete.
	    GridConnectedVehicle vehicle2 = new GridConnectedVehicle(vehicleId2, gbtc);
	    vehicle2.selfDrive(path2);
	    gbtc.markJourneyComplete(vehicleId2, source2, dest2);
	}

	/**
	 * Utility method to form a vehicleId.
	 *  
	 * @param source
	 * @return
	 */
	private static String formVehicleId(Point source) {
		return "V{"+ source.getX() + "," + source.getY() + "}";
	}
}
