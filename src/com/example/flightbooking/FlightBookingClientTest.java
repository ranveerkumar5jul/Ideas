/**
 * 
 */
package com.example.flightbooking;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FlightBookingClientTest {
	
	@Test
	public void checkIfRouteExistsTest(){
		FlightBookingClient fbc = new FlightBookingClient();
		boolean checkRoutes = fbc.checkIfRouteExists(FlightBookingConstants.AIRPORT_C, FlightBookingConstants.AIRPORT_B);
		assertEquals(true,checkRoutes);
	}
	
	@Test
	public void checkIfRouteNotExistsTest(){
		FlightBookingClient fbc = new FlightBookingClient();
		boolean checkRoutes = fbc.checkIfRouteExists(FlightBookingConstants.AIRPORT_H, FlightBookingConstants.AIRPORT_E);
		assertEquals(false,checkRoutes);
	}
	
	@Test
	public void getShortestPathTest(){
		FlightBookingClient fbc = new FlightBookingClient();
		Double shortestWeight = fbc.getShortestPath(FlightBookingConstants.AIRPORT_C, FlightBookingConstants.AIRPORT_B);
		assertEquals(new Double(3.0),shortestWeight);
	}
		
}
