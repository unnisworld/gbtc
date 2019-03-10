# Grid Based Traffic Control (GBTC) System
In implementation of Grid Based Traffic Control System. Vehicles are assumed to be present in an NxM matrix, 
where no. of vehicles are less than no. of grid elements. Vehicles can move N,S,E & W. Before the start of a Journey the Vehicle
has to contact the GridBasedTrafficControl to get a route allocated for its journey by providing source and destination coordinates.
At the end of a journey the vehicle is supposed to update the GridBasedTrafficControl that the journey is complete.
The GridBasedTrafficControl is responsible for route allocation by avoiding any possible collision in routes allocated to different
vehicles.
