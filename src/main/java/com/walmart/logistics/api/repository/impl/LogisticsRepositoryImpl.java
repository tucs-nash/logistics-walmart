package com.walmart.logistics.api.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.walmart.logistics.api.model.MapWalmart;
import com.walmart.logistics.api.model.Route;
import com.walmart.logistics.api.repository.LogisticsRepository;

/**
 * JDBC Repository for the logistic web service
 * @author DeAlmeidat
 *
 */
@Repository
public class LogisticsRepositoryImpl implements LogisticsRepository {

	@Autowired private JdbcTemplate jdbcTemplate;

	/**
	 * Persist map
	 */
	@Override
	public void addMap(MapWalmart map) {
		String insertMapSql = "INSERT INTO ROUTE (MAP_NAME, ROUTE_FROM, ROUTE_TO, DISTANCE) VALUES (?,?,?,?)";
		
		for (Route route : map.getRoutes()) {
			jdbcTemplate.update(insertMapSql, map.getMapName(), route.getSource(), route.getDestination(), route.getDistance());
		}
	}

	/**
	 * Retrieve map by name
	 */
	@Override
	public MapWalmart getMapByName(String mapName) {
		String getMapSql = "SELECT MAP_NAME, ROUTE_FROM, ROUTE_TO, DISTANCE FROM ROUTE WHERE MAP_NAME = ?";
		
		List<Route> routes = jdbcTemplate.query(getMapSql, new RowMapper<Route>() {
			public Route mapRow(ResultSet rs, int arg1) throws SQLException {
				Route route = new Route();
				route.setSource(rs.getString("ROUTE_FROM"));
				route.setDestination(rs.getString("ROUTE_TO"));
				route.setDistance(rs.getDouble("DISTANCE"));
				return route;
			}
		}, mapName);
		
		MapWalmart map = new MapWalmart();
		map.setMapName(mapName);
		map.setRoutes(routes);
		return map;
	}
}
