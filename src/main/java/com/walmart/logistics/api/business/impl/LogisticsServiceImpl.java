package com.walmart.logistics.api.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walmart.logistics.api.business.DijkstraAlgorithmForWalmart;
import com.walmart.logistics.api.business.LogisticsService;
import com.walmart.logistics.api.model.BestRoute;
import com.walmart.logistics.api.model.MapWalmart;
import com.walmart.logistics.api.repository.LogisticsRepository;

@Service
public class LogisticsServiceImpl implements LogisticsService {

	@Autowired private LogisticsRepository logisticsRepository;
	
	@Override
	public String addMap(MapWalmart map) {
		MapWalmart check = logisticsRepository.getMap(map.getMapName());
		
		if (check != null) {
			return "ERROR: Map already registered";
		}
		logisticsRepository.addMap(map);
		
		return "OK: Map created successfully";
	}

	@Override
	public BestRoute bestRoute(String mapName, String routeFrom, String routeTo
			, Double engine, Double litresPrice) {
		MapWalmart map = logisticsRepository.getMap(mapName);
		DijkstraAlgorithmForWalmart algorithm = new DijkstraAlgorithmForWalmart(map.getRoutes());
		algorithm.execute(routeFrom);
		BestRoute bestRoute = algorithm.getPath(routeTo);
		bestRoute.setAmount(bestRoute.getDistance()/engine*litresPrice);
		return bestRoute;
	}

}
