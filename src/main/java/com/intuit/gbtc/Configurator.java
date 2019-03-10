package com.intuit.gbtc;

/**
 * Defines an interface for obtaining an instance of GridBasedTrafficControl.
 * 
 * @author uvalsala
 *
 */
public interface Configurator {
	
	/**
	 * Provides an instance of GridBasedTrafficControl. 
	 * 
	 * @param grid
	 * @return GridBasedTrafficControl
	 */
	GridBasedTrafficControl getGridBasedTrafficControl(int grid[][]);
}
