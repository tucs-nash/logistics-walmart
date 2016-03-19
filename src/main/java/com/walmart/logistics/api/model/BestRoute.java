package com.walmart.logistics.api.model;

import java.util.List;

/**
 * Best Route model
 * @author DeAlmeidat
 *
 */
public class BestRoute {
	
	private List<String> route;
	private Double distance;
	private Double amount;
	
	public BestRoute() {
	}
	
	public List<String> getRoute() {
		return route;
	}
	public void setRoute(List<String> route) {
		this.route = route;
	}
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
}
