package com.walmart.logistics.api.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walmart.logistics.api.business.DijkstraAlgorithmForWalmart;
import com.walmart.logistics.api.business.LogisticsService;
import com.walmart.logistics.api.model.BestRoute;
import com.walmart.logistics.api.model.MapWalmart;
import com.walmart.logistics.api.repository.LogisticsRepository;
import com.walmart.logistics.api.utils.LogisticsConstants;

/**
 * Service for logistic Web Service
 * @author DeAlmeidat
 *
 */
@Service
public class LogisticsServiceImpl implements LogisticsService {

	@Autowired private LogisticsRepository logisticsRepository;
	
	/**
	 * Check if the map already exists and, if not, persist into database
	 */
	@Override
	public String addMap(MapWalmart map) {
		MapWalmart check = logisticsRepository.getMapByName(map.getMapName());
		
		if (check != null) {
			return LogisticsConstants.MESSAGE_MAP_ERROR;
		}
		logisticsRepository.addMap(map);
		
		return LogisticsConstants.MESSAGE_MAP_SUCCESS;
	}

	/**
	 * Find the best route from a point to another
	 */
	@Override
	public BestRoute bestRoute(String mapName, String routeFrom, String routeTo
			, Double engine, Double litresPrice) {
		MapWalmart map = logisticsRepository.getMapByName(mapName);
		DijkstraAlgorithmForWalmart algorithm = new DijkstraAlgorithmForWalmart(map.getRoutes());
		algorithm.execute(routeFrom);
		BestRoute bestRoute = algorithm.getPath(routeTo);
		bestRoute.setAmount(bestRoute.getDistance()/engine*litresPrice);
		return bestRoute;
	}

}
