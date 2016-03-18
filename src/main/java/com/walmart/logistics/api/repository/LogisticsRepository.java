package com.walmart.logistics.api.repository;

import com.walmart.logistics.api.model.MapWalmart;

public interface LogisticsRepository {

	public void addMap(MapWalmart map);
	public MapWalmart getMap(String mapName);

}
