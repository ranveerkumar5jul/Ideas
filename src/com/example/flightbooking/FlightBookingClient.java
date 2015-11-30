package com.example.flightbooking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.example.flightbooking.FlightBookingConstants.*;

import com.example.flightbooking.Path;

public class FlightBookingClient {
	Map<Path, List<Path>> airportConnectivityMap = null;
	List<Path> airportList = new ArrayList<Path>();

	public FlightBookingClient() {
		populateAirportList();
		createAirportConnectivityMap();
	}

	/*
	 * Below method is to populate a map with key as path object and value as
	 * list of path object where path will have source, destination and
	 * available flight frequency.
	 */

	public void createAirportConnectivityMap() {
		airportConnectivityMap = new HashMap<Path, List<Path>>();
		for (Path edge : airportList) {

			List<Path> destList = airportConnectivityMap.get(edge);
			if (destList == null) {
				destList = new ArrayList<Path>();
			}

			destList.add(edge);
			airportConnectivityMap.put(edge, destList);

		}
	}

	/*
	 * Below method is to populate a list of Path object where path will have
	 * source, destination and available flight frequency.
	 */

	public void populateAirportList() {
		airportList.add(new Path(AIRPORT_A, AIRPORT_F, 1.0));

		airportList.add(new Path(AIRPORT_B, AIRPORT_A, 1.0));
		airportList.add(new Path(AIRPORT_B, AIRPORT_F, 2.0));

		airportList.add(new Path(AIRPORT_C, AIRPORT_D, 1.0));
		airportList.add(new Path(AIRPORT_C, AIRPORT_E, 5.0));
		airportList.add(new Path(AIRPORT_C, AIRPORT_E, 4.0));
		airportList.add(new Path(AIRPORT_C, AIRPORT_E, 2.0));
		airportList.add(new Path(AIRPORT_C, AIRPORT_B, 3.0));
		airportList.add(new Path(AIRPORT_C, AIRPORT_B, 5.0));
		airportList.add(new Path(AIRPORT_C, AIRPORT_B, 6.0));
		airportList.add(new Path(AIRPORT_C, AIRPORT_A, 6.0));
		airportList.add(new Path(AIRPORT_C, AIRPORT_A, 7.0));
		airportList.add(new Path(AIRPORT_C, AIRPORT_A, 4.0));
		airportList.add(new Path(AIRPORT_C, AIRPORT_F, 7.0));
		airportList.add(new Path(AIRPORT_C, AIRPORT_F, 8.0));
		airportList.add(new Path(AIRPORT_C, AIRPORT_F, 5.0));

		airportList.add(new Path(AIRPORT_D, AIRPORT_E, 1.0));
		airportList.add(new Path(AIRPORT_D, AIRPORT_B, 2.0));
		airportList.add(new Path(AIRPORT_D, AIRPORT_A, 3.0));
		airportList.add(new Path(AIRPORT_D, AIRPORT_F, 4.0));

		airportList.add(new Path(AIRPORT_E, AIRPORT_B, 1.0));
		airportList.add(new Path(AIRPORT_E, AIRPORT_A, 2.0));
		airportList.add(new Path(AIRPORT_E, AIRPORT_F, 3.0));

		airportList.add(new Path(AIRPORT_H, AIRPORT_B, 1.0));
		airportList.add(new Path(AIRPORT_H, AIRPORT_A, 2.0));
		airportList.add(new Path(AIRPORT_H, AIRPORT_F, 3.0));
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
		Double shortestWeight = new Double(99.0);
		checkRouteExist = checkIfRouteExists(source, destination);
		if (checkRouteExist) {
			List<Path> destinationList = airportConnectivityMap.get(new Path(source, destination, DEFAULT_FREQUENCY));
			for (Path edge : destinationList) {
				shortestWeight = edge.getFrequency() < shortestWeight ? edge.getFrequency() : shortestWeight;
			}
		}
		return shortestWeight;
	}
}
