package com.group4.shared;

import com.group4.shared.Model.Graph.RoutePaths;
import com.group4.shared.Model.Map.RouteSegment;

import org.junit.Assert;
import org.junit.Test;

/*
 * Created by Tom on 6/7/2017.
 */

public class RoutePathsTest
{
    private RoutePaths routes1;
    private RoutePaths routes2;
    private RoutePaths routes3;


    public RoutePathsTest()
    {
        setUpRoutes1();
        setUpRoutes2();
        setUpRoutes3();
    }

    private void setUpRoutes1()
    {
        routes1 = new RoutePaths();

        RouteSegment route12 = new RouteSegment();
        route12.setCityA("city1");
        route12.setCityB("city2");
        route12.setLength(10);
        routes1.add(route12);

        RouteSegment route23 = new RouteSegment();
        route23.setCityA("city2");
        route23.setCityB("city3");
        route23.setLength(20);
        routes1.add(route23);

        RouteSegment route34 = new RouteSegment();
        route34.setCityA("city3");
        route34.setCityB("city4");
        route34.setLength(30);
        routes1.add(route34);

        RouteSegment route25 = new RouteSegment();
        route25.setCityA("city2");
        route25.setCityB("city5");
        route25.setLength(15);
        routes1.add(route25);

        RouteSegment route35 = new RouteSegment();
        route35.setCityA("city3");
        route35.setCityB("city5");
        route35.setLength(10);
        routes1.add(route35);

        RouteSegment route36 = new RouteSegment();
        route36.setCityA("city3");
        route36.setCityB("city6");
        route36.setLength(5);
        routes1.add(route36);

        RouteSegment route78 = new RouteSegment();
        route78.setCityA("city7");
        route78.setCityB("city8");
        route78.setLength(5);
        routes1.add(route78);

        RouteSegment route79 = new RouteSegment();
        route79.setCityA("city7");
        route79.setCityB("city9");
        route79.setLength(5);
        routes1.add(route79);

        RouteSegment route89 = new RouteSegment();
        route89.setCityA("city8");
        route89.setCityB("city9");
        route89.setLength(5);
        routes1.add(route89);
    }

    private void setUpRoutes2()
    {
        routes2 = new RoutePaths();

        RouteSegment route12 = new RouteSegment();
        route12.setCityA("city1");
        route12.setCityB("city2");
        route12.setLength(10);
        routes2.add(route12);

        RouteSegment route23 = new RouteSegment();
        route23.setCityA("city2");
        route23.setCityB("city3");
        route23.setLength(10);
        routes2.add(route23);

        RouteSegment route34 = new RouteSegment();
        route34.setCityA("city3");
        route34.setCityB("city4");
        route34.setLength(10);
        routes2.add(route34);

        RouteSegment route45 = new RouteSegment();
        route45.setCityA("city4");
        route45.setCityB("city5");
        route45.setLength(10);
        routes2.add(route45);

        RouteSegment route67 = new RouteSegment();
        route67.setCityA("city6");
        route67.setCityB("city7");
        route67.setLength(10);
        routes2.add(route67);

        RouteSegment route35 = new RouteSegment();
        route35.setCityA("city3");
        route35.setCityB("city5");
        route35.setLength(10);
        routes2.add(route35);

        RouteSegment route36 = new RouteSegment();
        route36.setCityA("city3");
        route36.setCityB("city6");
        route36.setLength(10);
        routes2.add(route36);

        RouteSegment route37 = new RouteSegment();
        route37.setCityA("city3");
        route37.setCityB("city7");
        route37.setLength(10);
        routes2.add(route37);

        RouteSegment route38 = new RouteSegment();
        route38.setCityA("city3");
        route38.setCityB("city8");
        route38.setLength(10);
        routes2.add(route38);

        RouteSegment route910 = new RouteSegment();
        route910.setCityA("city9");
        route910.setCityB("city10");
        route910.setLength(10);
        routes2.add(route910);
    }

    private void setUpRoutes3()
    {
        routes3 = new RoutePaths();

        RouteSegment route12 = new RouteSegment();
        route12.setCityA("city1");
        route12.setCityB("city2");
        route12.setLength(10);
        routes3.add(route12);

        RouteSegment route23 = new RouteSegment();
        route23.setCityA("city2");
        route23.setCityB("city3");
        route23.setLength(10);
        routes3.add(route23);

        RouteSegment route34 = new RouteSegment();
        route34.setCityA("city3");
        route34.setCityB("city4");
        route34.setLength(10);
        routes3.add(route34);

        RouteSegment route45 = new RouteSegment();
        route45.setCityA("city4");
        route45.setCityB("city5");
        route45.setLength(10);
        routes3.add(route45);

        RouteSegment route67 = new RouteSegment();
        route67.setCityA("city6");
        route67.setCityB("city7");
        route67.setLength(10);
        routes3.add(route67);

        RouteSegment route35 = new RouteSegment();
        route35.setCityA("city3");
        route35.setCityB("city5");
        route35.setLength(10);
        routes3.add(route35);

        RouteSegment route36 = new RouteSegment();
        route36.setCityA("city3");
        route36.setCityB("city6");
        route36.setLength(10);
        routes3.add(route36);

        RouteSegment route37 = new RouteSegment();
        route37.setCityA("city3");
        route37.setCityB("city7");
        route37.setLength(10);
        routes3.add(route37);

        RouteSegment route38 = new RouteSegment();
        route38.setCityA("city3");
        route38.setCityB("city8");
        route38.setLength(10);
        routes3.add(route38);

        RouteSegment route910 = new RouteSegment();
        route910.setCityA("city9");
        route910.setCityB("city10");
        route910.setLength(100);
        routes3.add(route910);
    }

    @Test
    public void simpleConnection()
    {
        Assert.assertTrue(routes1.destinationComplete("city1", "city2"));
        Assert.assertTrue(routes2.destinationComplete("city3", "city4"));
        Assert.assertTrue(routes3.destinationComplete("city9", "city10"));
    }

    @Test
    public void complexConnection()
    {
        Assert.assertTrue(routes1.destinationComplete("city1", "city3"));
        Assert.assertTrue(routes1.destinationComplete("city7", "city8"));
        Assert.assertTrue(routes1.destinationComplete("city8", "city9"));
        Assert.assertTrue(routes1.destinationComplete("city7", "city9"));
        Assert.assertTrue(routes2.destinationComplete("city1", "city8"));
    }

    @Test
    public void reverseConnection()
    {
        Assert.assertTrue(routes1.destinationComplete("city4", "city1"));
        Assert.assertTrue(routes2.destinationComplete("city2", "city1"));
    }

    @Test
    public void noConnection()
    {
        Assert.assertFalse(routes1.destinationComplete("city6", "city7"));
        Assert.assertFalse(routes2.destinationComplete("city1", "city9"));
        Assert.assertFalse(routes3.destinationComplete("city8", "city9"));
    }

    @Test
    public void longestPath()
    {
        Assert.assertEquals(80, routes1.longestPath());
        Assert.assertEquals(90, routes2.longestPath());
        Assert.assertEquals(100, routes3.longestPath());
    }

}
