package com.walmart.logistics.api.model;

/**
 * Route model
 * @author DeAlmeidat
 *
 */
public class Route {

	private String source;
	private String destination;
	private Double distance;
	
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
}
