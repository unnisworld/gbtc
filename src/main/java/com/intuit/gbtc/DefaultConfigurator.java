package com.intuit.gbtc;

/**
 * Default implementation of the {@code Configurator} interface.
 * Responsible for instantiating an implementation of GridBasedTrafficControl.
 * 
 * @author uvalsala
 *
 */
public class DefaultConfigurator implements Configurator {

	@Override
	public synchronized GridBasedTrafficControl getGridBasedTrafficControl(int grid[][]) {
		if (gbtc == null) {
			gbtc = new SimpleGridBasedTrafficControl(grid, new SimplePathCalculationStrategy(),
					new SimpleCollisionDetectionStrategy());
		}
		
		return gbtc;
	}

	private GridBasedTrafficControl gbtc;

}
