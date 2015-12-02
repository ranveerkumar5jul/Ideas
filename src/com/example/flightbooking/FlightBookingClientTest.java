/**
 * 
 */
package com.example.flightbooking;

import static com.example.flightbooking.FlightBookingConstants.AIRPORT_A;
import static com.example.flightbooking.FlightBookingConstants.AIRPORT_B;
import static com.example.flightbooking.FlightBookingConstants.AIRPORT_C;
import static com.example.flightbooking.FlightBookingConstants.AIRPORT_D;
import static com.example.flightbooking.FlightBookingConstants.AIRPORT_E;
import static com.example.flightbooking.FlightBookingConstants.AIRPORT_F;
import static com.example.flightbooking.FlightBookingConstants.AIRPORT_H;
import static com.example.flightbooking.FlightBookingConstants.DEFAULT_FREQUENCY;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

public class FlightBookingClientTest {
	static Map<Path, List<Path>> airportConnectivityMap = new HashMap<Path, List<Path>>();

	@BeforeClass
	public static void setUp() {
		addRoute(AIRPORT_A, AIRPORT_F, 1.0);
		addRoute(AIRPORT_B, AIRPORT_A, 1.0);
		addRoute(AIRPORT_B, AIRPORT_F, 2.0);

		addRoute(AIRPORT_C, AIRPORT_D, 1.0);
		addRoute(AIRPORT_C, AIRPORT_E, 5.0);
		addRoute(AIRPORT_C, AIRPORT_E, 4.0);
		addRoute(AIRPORT_C, AIRPORT_E, 2.0);
		addRoute(AIRPORT_C, AIRPORT_B, 3.0);
		addRoute(AIRPORT_C, AIRPORT_B, 5.0);
		addRoute(AIRPORT_C, AIRPORT_B, 6.0);
		addRoute(AIRPORT_C, AIRPORT_A, 6.0);
		addRoute(AIRPORT_C, AIRPORT_A, 7.0);
		addRoute(AIRPORT_C, AIRPORT_A, 4.0);
		addRoute(AIRPORT_C, AIRPORT_F, 7.0);
		addRoute(AIRPORT_C, AIRPORT_F, 8.0);
		addRoute(AIRPORT_C, AIRPORT_F, 5.0);

		addRoute(AIRPORT_D, AIRPORT_E, 1.0);
		addRoute(AIRPORT_D, AIRPORT_B, 2.0);
		addRoute(AIRPORT_D, AIRPORT_A, 3.0);
		addRoute(AIRPORT_D, AIRPORT_F, 4.0);

		addRoute(AIRPORT_E, AIRPORT_B, 1.0);
		addRoute(AIRPORT_E, AIRPORT_A, 2.0);
		addRoute(AIRPORT_E, AIRPORT_F, 3.0);

		addRoute(AIRPORT_H, AIRPORT_B, 1.0);
		addRoute(AIRPORT_H, AIRPORT_A, 2.0);
		addRoute(AIRPORT_H, AIRPORT_F, 3.0);
	}

	private static void addRoute(String source, String destination, double flightFrequency) {
		Path path = new Path(source, destination, flightFrequency);
		Path getPath = new Path(source, destination, DEFAULT_FREQUENCY);
		List<Path> destList = airportConnectivityMap.get(getPath);
		if (destList == null) {
			destList = new ArrayList<Path>();
			destList.add(path);
		} else {
			destList.add(path);
		}
		airportConnectivityMap.put(getPath, destList);

	}

	@Test
	public void checkIfRouteExistsTest() {
		FlightBookingClient fbc = new FlightBookingClient(airportConnectivityMap);
		boolean checkRoutes = fbc.checkIfRouteExists(FlightBookingConstants.AIRPORT_C,
				FlightBookingConstants.AIRPORT_B);
		assertEquals(true, checkRoutes);
	}

	@Test
	public void checkIfRouteNotExistsTest() {
		FlightBookingClient fbc = new FlightBookingClient(airportConnectivityMap);
		boolean checkRoutes = fbc.checkIfRouteExists(FlightBookingConstants.AIRPORT_H,
				FlightBookingConstants.AIRPORT_E);
		assertEquals(false, checkRoutes);
	}

	@Test
	public void getShortestPathTest() {
		FlightBookingClient fbc = new FlightBookingClient(airportConnectivityMap);
		Double shortestWeight = fbc.getShortestPath(FlightBookingConstants.AIRPORT_C, FlightBookingConstants.AIRPORT_B);
		assertEquals(new Double(3.0), shortestWeight);
	}

}
