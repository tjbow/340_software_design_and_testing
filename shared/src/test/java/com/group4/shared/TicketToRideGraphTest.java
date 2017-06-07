package com.group4.shared;

import com.group4.shared.Model.Graph.TicketToRideGraph;
import com.group4.shared.Model.Graph.VisitableWeightedEdge;

import org.junit.Assert;
import org.junit.Test;

/*
 * Created by Tom on 6/7/2017.
 */

public class TicketToRideGraphTest
{

    private TicketToRideGraph graph1;

    public TicketToRideGraphTest()
    {
        setUpGraph1();
    }

    private void setUpGraph1()
    {
        graph1 = new TicketToRideGraph(VisitableWeightedEdge.class);

        graph1.addVertex("city1");
        graph1.addVertex("city2");
        graph1.addVertex("city3");
        graph1.addVertex("city4");
        graph1.addVertex("city5");
        graph1.addVertex("city6");

        VisitableWeightedEdge edge12 = new VisitableWeightedEdge();
        graph1.addEdge("city1", "city2", edge12);
        graph1.setEdgeWeight(edge12, 10);

        VisitableWeightedEdge edge23 = new VisitableWeightedEdge();
        graph1.addEdge("city2", "city3", edge23);
        graph1.setEdgeWeight(edge23, 20);

        VisitableWeightedEdge edge34 = new VisitableWeightedEdge();
        graph1.addEdge("city3", "city4", edge34);
        graph1.setEdgeWeight(edge34, 30);

        VisitableWeightedEdge edge25 = new VisitableWeightedEdge();
        graph1.addEdge("city2", "city5", edge25);
        graph1.setEdgeWeight(edge25, 15);

        VisitableWeightedEdge edge35 = new VisitableWeightedEdge();
        graph1.addEdge("city3", "city5", edge35);
        graph1.setEdgeWeight(edge35, 10);

        VisitableWeightedEdge edge36 = new VisitableWeightedEdge();
        graph1.addEdge("city3", "city6", edge36);
        graph1.setEdgeWeight(edge36, 5);
    }

    @Test
    public void testEachCity()
    {
        Assert.assertEquals(65, graph1.longestPathRec("city1"));
        Assert.assertEquals(55, graph1.longestPathRec("city2"));
        Assert.assertEquals(75, graph1.longestPathRec("city3"));
        Assert.assertEquals(80, graph1.longestPathRec("city4"));
        Assert.assertEquals(65, graph1.longestPathRec("city5"));
        Assert.assertEquals(80, graph1.longestPathRec("city6"));
    }

}
