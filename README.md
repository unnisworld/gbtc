# Grid Based Traffic Control (GBTC) System
An implementation of the Grid Based Traffic Control System. Vehicles are assumed to be present in an NxM matrix, 
where no. of vehicles are less than no. of grid elements. Vehicles can move in N,S,E & W directions on the grid. Before the start of a Journey the Vehicle has to contact the GridBasedTrafficControl to get a route allocated for its journey by providing source and destination coordinates. At the end of a journey the vehicle is supposed to update the GridBasedTrafficControl that the journey is complete. The GridBasedTrafficControl is responsible for route allocation by avoiding any possible collision in routes allocated to different vehicles.

Important classes
1. SimpleGridBasedTrafficControl - A simple implementation of GridBasedTrafficControl
2. SimplePathCalculationStrategy - Implements Lee's algorithm to compute shortest path on a grid.
3. SimpleCollisionDetectionStrategy - Responsible for collision detection.
4. GbtcClient - A demo client program

Instructions for running the program
1. Clone the git repo locally
2. Import the maven project into Eclipse or Intellij
3. Run GbtcClient.java
