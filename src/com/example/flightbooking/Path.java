package com.example.flightbooking;

public class Path {

	private String source;
	private String destination;

	private double frequency;

	public Path(String source, String destination, double frequency) {
		this.source=source;
		this.destination = destination;
		this.frequency = frequency;
	}

	public String getSource() {
		return source;
	}
	
	public String getDestination() {
		return destination;
	}

	public double getFrequency() {
		return frequency;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((destination == null) ? 0 : destination.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Path other = (Path) obj;
		if (destination == null) {
			if (other.destination != null)
				return false;
		} else if (!destination.equals(other.destination))
			return false;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		return true;
	}

}
