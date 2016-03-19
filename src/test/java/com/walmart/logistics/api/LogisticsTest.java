package com.walmart.logistics.api;

import java.util.ArrayList;

import junitx.framework.Assert;
import junitx.util.PrivateAccessor;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.modules.junit4.PowerMockRunner;

import com.walmart.logistics.api.business.LogisticsService;
import com.walmart.logistics.api.business.impl.LogisticsServiceImpl;
import com.walmart.logistics.api.controller.LogisticsController;
import com.walmart.logistics.api.model.BestRoute;
import com.walmart.logistics.api.model.MapWalmart;
import com.walmart.logistics.api.model.Route;
import com.walmart.logistics.api.repository.LogisticsRepository;
import com.walmart.logistics.api.utils.LogisticsConstants;

@RunWith(PowerMockRunner.class)
public class LogisticsTest {
	
	private LogisticsController logisticsController;
	private LogisticsService logisticsService;
	private LogisticsRepository logisticsRepository;
	
	@Before
	public void setUp() throws NoSuchFieldException {
		logisticsController = new LogisticsController();
		logisticsService = new LogisticsServiceImpl();
		logisticsRepository = PowerMock.createMock(LogisticsRepository.class);
		
		PrivateAccessor.setField(logisticsService, "logisticsRepository", logisticsRepository);
		PrivateAccessor.setField(logisticsController, "logisticsService", logisticsService);
	}
	
	@Test
	public void testAdd() {
		MapWalmart mapWalmart = createMapWalmart();
		
		EasyMock.expect(logisticsRepository.getMapByName(mapWalmart.getMapName())).andReturn(null);
		logisticsRepository.addMap(mapWalmart);
		EasyMock.expectLastCall().once();
		
		PowerMock.replayAll();
		String message = logisticsController.addMap(mapWalmart);
		PowerMock.verifyAll();
		
		Assert.assertEquals(LogisticsConstants.MESSAGE_MAP_SUCCESS, message);
	}

	@Test
	public void testAddError() {
		MapWalmart mapWalmart = createMapWalmart();
		
		EasyMock.expect(logisticsRepository.getMapByName(mapWalmart.getMapName())).andReturn(mapWalmart);
		
		PowerMock.replayAll();
		String message = logisticsController.addMap(mapWalmart);
//		BestRoute bestRoute = logisticsController.bestRoute(mapWalmart.getMapName(), "A", "D", 10d, 2.5d);
		PowerMock.verifyAll();
		
		Assert.assertEquals(LogisticsConstants.MESSAGE_MAP_ERROR, message);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testBestRoute() {
		MapWalmart mapWalmart = createMapWalmart();
		
		EasyMock.expect(logisticsRepository.getMapByName(mapWalmart.getMapName())).andReturn(mapWalmart);
		
		PowerMock.replayAll();
		BestRoute bestRoute = logisticsController.bestRoute(mapWalmart.getMapName(), "A", "D", 10d, 2.5d);
		PowerMock.verifyAll();

		Assert.assertEquals(1.5d, bestRoute.getAmount());
	}
	
	private MapWalmart createMapWalmart() {
		MapWalmart mapWalmart = new MapWalmart("test");
		mapWalmart.setRoutes(new ArrayList<Route>());
		
		Route route1 = new Route();
		route1.setSource("A");
		route1.setDestination("B");
		route1.setDistance(1d);
		
		Route route2 = new Route();
		route2.setSource("A");
		route2.setDestination("C");
		route2.setDistance(3d);
		
		Route route3 = new Route();
		route3.setSource("B");
		route3.setDestination("D");
		route3.setDistance(5d);

		Route route4 = new Route();
		route4.setSource("B");
		route4.setDestination("D");
		route4.setDistance(10d);

		Route route5 = new Route();
		route5.setSource("C");
		route5.setDestination("D");
		route5.setDistance(30d);
		
		mapWalmart.getRoutes().add(route1);
		mapWalmart.getRoutes().add(route2);
		mapWalmart.getRoutes().add(route3);
		mapWalmart.getRoutes().add(route4);
		mapWalmart.getRoutes().add(route5);
		return mapWalmart;
	}
}
