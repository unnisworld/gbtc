package com.intuit.gbtc;

public class DefaultConfigurator implements Configurator {

	@Override
	public synchronized GridBasedTrafficControl getGridBasedTrafficControl(int grid[][]) {
		if (gbtc == null) {
			gbtc = new SimpleGridBasedTrafficControl(grid, new SimplePathCalculationStrategy(),
					new SimpleRouteConflictChecker());
		}
		
		return gbtc;
	}

	private GridBasedTrafficControl gbtc;

}
