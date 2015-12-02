package com.example.flightbooking;

import static com.example.flightbooking.FlightBookingConstants.DEFAULT_FREQUENCY;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FlightBookingClient {

	Map<Path, List<Path>> airportConnectivityMap = null;

	public FlightBookingClient(Map<Path, List<Path>> airportConnectivityMap) {
		this.airportConnectivityMap = airportConnectivityMap;
	}

	public boolean checkIfRouteExists(String source, String destination) {
		boolean routeExist = false;
		List<Path> destinationList = new ArrayList<Path>();
		destinationList = airportConnectivityMap.get(new Path(source, destination, DEFAULT_FREQUENCY));
		if (destinationList != null && destinationList.size() > 0)
			routeExist = true;
		return routeExist;
	}

	public Double getShortestPath(String source, String destination) {
		boolean checkRouteExist = false;
		Double shortestWeight = new Double(999.0);
		checkRouteExist = checkIfRouteExists(source, destination);
		if (checkRouteExist) {
			List<Path> destinationList = airportConnectivityMap.get(new Path(source, destination, DEFAULT_FREQUENCY));
			for (Path path : destinationList) {
				shortestWeight = path.getFrequency() < shortestWeight ? path.getFrequency() : shortestWeight;
			}
		}
		return shortestWeight;
	}
}
