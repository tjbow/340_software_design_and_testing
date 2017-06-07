package com.group4.shared.Model.Graph;

/*
 * Created by Tom on 6/5/2017.
 */

import com.group4.shared.Model.Map.RouteSegment;

import java.util.Collections;

/**
 * Holds information about the connections formed by player owned
 * routes
 */
public class RoutePaths
{
    private TicketToRideGraph playerGraph;

    public RoutePaths()
    {
        playerGraph = new TicketToRideGraph(VisitableWeightedEdge.class);
    }

    /**
     * Adds the route to the connections
     * @param route the route to be added
     */
    public void add(RouteSegment route)
    {
        VisitableWeightedEdge edge = new VisitableWeightedEdge();
        if(!playerGraph.vertexSet().contains(route.getCityA())) // graph does not contain cityA
        {
            playerGraph.addVertex(route.getCityA());
        }
        if(!playerGraph.vertexSet().contains(route.getCityB())) // graph does not contain cityB
        {
            playerGraph.addVertex(route.getCityB());
        }
        playerGraph.addEdge(route.getCityA(), route.getCityB(), edge);
        playerGraph.setEdgeWeight(edge, route.getLength());
    }

    /**
     * Returns true if the destination has been completed
     * @param cityA the name of the starting city
     * @param cityB the name of the ending city
     * @return completion of the destination
     */
    public boolean destinationComplete(String cityA, String cityB)
    {
        return playerGraph.pathExists(cityA, cityB);
    }

    /**
     * Gets the length of longest path of connected routes
     * @return the length of longest path of connected routes
     */
    public int longestPath()
    {
        return playerGraph.getLongestPath();
    }

    /**
     * Clears the set of routes
     */
    public void clear()
    {
        playerGraph.clear();
    }
}
