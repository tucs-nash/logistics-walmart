package com.walmart.logistics.api.model;

import java.util.List;

/**
 * Map model
 * @author DeAlmeidat
 *
 */
public class MapWalmart {

	private String mapName;
	private List<Route> routes;
	
	public MapWalmart() {
	}

	public MapWalmart(String mapName) {
		this.mapName = mapName;
	}
	
	public String getMapName() {
		return mapName;
	}
	public void setMapName(String mapName) {
		this.mapName = mapName;
	}
	public List<Route> getRoutes() {
		return routes;
	}
	public void setRoutes(List<Route> routes) {
		this.routes = routes;
	}
}
