package com.walmart.logistics.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.walmart.logistics.api.business.LogisticsService;
import com.walmart.logistics.api.model.BestRoute;
import com.walmart.logistics.api.model.MapWalmart;

/**
 * Controller for the logistic Web Service
 * @author DeAlmeidat
 *
 */
@Controller
public class LogisticsController {

	@Autowired LogisticsService logisticsService;
	
	/**
	 * Request mapping for the Add Map Web Service
	 * @param map
	 * @return response message
	 */
	@RequestMapping(value="/addMap", method=RequestMethod.POST)
	public @ResponseBody String addMap(@RequestBody MapWalmart map) {
		return logisticsService.addMap(map);
	}
	
	/**
	 * Request mapping for the Best Route Web Service
	 * @param mapName
	 * @param routeFrom
	 * @param routeTo
	 * @param engine
	 * @param litresPrice
	 * @return BestRoute JSON
	 */
	@RequestMapping(value="/bestRoute/{mapName}", method=RequestMethod.GET)
	public @ResponseBody BestRoute bestRoute(@PathVariable String mapName, @RequestParam String routeFrom
			, @RequestParam String routeTo, @RequestParam Double engine, @RequestParam Double litresPrice) {
		return logisticsService.bestRoute(mapName, routeFrom, routeTo, engine, litresPrice);
	}
}
