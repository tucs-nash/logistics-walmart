package com.walmart.logistics.api.business;

import com.walmart.logistics.api.model.BestRoute;
import com.walmart.logistics.api.model.MapWalmart;

public interface LogisticsService {

	public String addMap(MapWalmart map);

	public BestRoute bestRoute(String mapName, String routeFrom,String routeTo, Double engine, Double litresPrice);

}
