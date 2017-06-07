package com.group4.shared.Graphs;

import com.group4.shared.Model.Map.City;

import org.jgrapht.EdgeFactory;
import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.Set;

/*
 * Created by Tom on 6/4/2017.
 */

public class GraphTest
{

    SimpleWeightedGraph<City, WeightedEdge> graph;
    ConnectivityInspector<City, WeightedEdge> inspector;
    City city1 = new City();
    City city2 = new City();
    City city3 = new City();
    City city4 = new City();
    City city5 = new City();
    City city6 = new City();

    public GraphTest()
    {
        graph = new SimpleWeightedGraph<>(WeightedEdge.class);
        inspector = new ConnectivityInspector<>(graph);
        city1.setName("city1");
        city2.setName("city2");
        city3.setName("city3");
        city4.setName("city4");
        city5.setName("city5");
        city6.setName("city6");

        graph.addVertex(city1);
        graph.addVertex(city2);
        graph.addVertex(city3);
        graph.addVertex(city4);
        graph.addVertex(city5);
        graph.addVertex(city6);

        WeightedEdge edge12 = new WeightedEdge();
        graph.addEdge(city1, city2, edge12);
        graph.setEdgeWeight(edge12, 10);

        WeightedEdge edge23 = new WeightedEdge();
        graph.addEdge(city2, city3, edge23);
        graph.setEdgeWeight(edge23, 20);

        WeightedEdge edge34 = new WeightedEdge();
        graph.addEdge(city3, city4, edge34);
        graph.setEdgeWeight(edge34, 30);

        WeightedEdge edge25 = new WeightedEdge();
        graph.addEdge(city2, city5, edge25);
        graph.setEdgeWeight(edge25, 15);

        WeightedEdge edge35 = new WeightedEdge();
        graph.addEdge(city3, city5, edge35);
        graph.setEdgeWeight(edge35, 10);

        WeightedEdge edge36 = new WeightedEdge();
        graph.addEdge(city3, city6, edge36);
        graph.setEdgeWeight(edge36, 5);

    }

    @Test
    public void simpleConnection()
    {
        Assert.assertTrue(inspector.pathExists(city1, city2));
    }

    @Test
    public void complexConnection()
    {
        Assert.assertTrue(inspector.pathExists(city1, city3));
    }

    @Test
    public void reverseConnection()
    {
        Assert.assertTrue(inspector.pathExists(city4, city1));
    }

    @Test
    public void longestPath()
    {
        int longestPath = 0;
        for(City city : graph.vertexSet())
        {
            int iterLongPath = longestPathRec(city);
            if(iterLongPath > longestPath)
            {
                longestPath = iterLongPath;
            }
        }
        Assert.assertEquals(80, longestPath);
    }

    @Test
    public void testEachCity()
    {
        Assert.assertEquals(65, longestPathRec(city1));
        Assert.assertEquals(55, longestPathRec(city2));
        Assert.assertEquals(75, longestPathRec(city3));
        Assert.assertEquals(80, longestPathRec(city4));
        Assert.assertEquals(65, longestPathRec(city5));
        Assert.assertEquals(80, longestPathRec(city6));
    }

    public int longestPathRec(City city)
    {
        int additionalPathLength = 0;

        Set<WeightedEdge> edges = graph.edgesOf(city);
        for(WeightedEdge edge: edges) // iterate through all edges off city
        {
            int iterLength = 0;
            if(!edge.visited)
            {
                edge.visited = true;
                City targetCity = graph.getEdgeTarget(edge);
                if(targetCity == city)
                {
                    targetCity = graph.getEdgeSource(edge);
                }
                int callLength = (int) (graph.getEdgeWeight(edge));
                iterLength = callLength + longestPathRec(targetCity);
                if(iterLength > additionalPathLength)
                {
                    additionalPathLength = iterLength;
                }
                edge.visited = false;
            }
        }
        return additionalPathLength;
    }

    public class WeightedEdge extends DefaultWeightedEdge
    {
        public boolean visited = false;
    }
}
