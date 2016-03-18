package com.walmart.logistics.api.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.walmart.logistics.api.model.BestRoute;
import com.walmart.logistics.api.model.Route;

public class DijkstraAlgorithm {

	  private final List<Route> routes;
	  private Set<String> settledNodes;
	  private Set<String> unSettledNodes;
	  private Map<String, String> predecessors;
	  private Map<String, Double> distance;

	  public DijkstraAlgorithm(List<Route> routes) {
	    this.routes = routes;
	  }

	  public void execute(String source) {
	    settledNodes = new HashSet<String>();
	    unSettledNodes = new HashSet<String>();
	    distance = new HashMap<String, Double>();
	    predecessors = new HashMap<String, String>();
	    distance.put(source, 0d);
	    unSettledNodes.add(source);
	    while (unSettledNodes.size() > 0) {
	      String node = getMinimum(unSettledNodes);
	      settledNodes.add(node);
	      unSettledNodes.remove(node);
	      findMinimalDistances(node);
	    }
	  }

	  private void findMinimalDistances(String node) {
	    List<String> adjacentNodes = getNeighbors(node);
	    for (String target : adjacentNodes) {
	      if (getShortestDistance(target) > getShortestDistance(node)
	          + getDistance(node, target)) {
	        distance.put(target, getShortestDistance(node)
	            + getDistance(node, target));
	        predecessors.put(target, node);
	        unSettledNodes.add(target);
	      }
	    }

	  }

	  private double getDistance(String node, String target) {
	    for (Route route : routes) {
	      if (route.getSource().equals(node)
	          && route.getDestination().equals(target)) {
	        return route.getDistance();
	      }
	    }
	    throw new RuntimeException("Should not happen");
	  }

	  private List<String> getNeighbors(String node) {
	    List<String> neighbors = new ArrayList<String>();
	    for (Route route : routes) {
	      if (route.getSource().equals(node)
	          && !isSettled(route.getDestination())) {
	        neighbors.add(route.getDestination());
	      }
	    }
	    return neighbors;
	  }

	  private String getMinimum(Set<String> vertexes) {
		  String minimum = null;
	    for (String vertex : vertexes) {
	      if (minimum == null) {
	        minimum = vertex;
	      } else {
	        if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
	          minimum = vertex;
	        }
	      }
	    }
	    return minimum;
	  }

	  private boolean isSettled(String vertex) {
	    return settledNodes.contains(vertex);
	  }

	  private double getShortestDistance(String destination) {
		  Double d = distance.get(destination);
	    if (d == null) {
	      return Integer.MAX_VALUE;
	    } else {
	      return d;
	    }
	  }

	  /*
	   * This method returns the path from the source to the selected target and
	   * NULL if no path exists
	   */
	  public BestRoute getPath(String target) {
	    LinkedList<String> path = new LinkedList<String>();
	    String step = target;
	    // check if a path exists
	    if (predecessors.get(step) == null) {
	      return null;
	    }
	    path.add(step);
	    while (predecessors.get(step) != null) {
	      step = predecessors.get(step);
	      path.add(step);
	    }
	    // Put it into the correct order
	    Collections.reverse(path);
	    BestRoute bestRoute = new BestRoute();
	    bestRoute.setRoute(path);
	    bestRoute.setDistance(distance.get(target));
	    return bestRoute;
	  }
} 

