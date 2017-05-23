package com.group4.shared.command.Client;

import com.group4.shared.Model.City;
import com.group4.shared.Model.RouteList;
import com.group4.shared.command.ClientCommand;
import com.group4.shared.command.Command;

import java.util.List;

/**
 * Created by tyler on 5/23/17.
 */

public class CUpdateMapCommandData extends ClientCommand
{
    RouteList routeSegments;
    List<City> cities;

    public RouteList getRouteSegments()
    {
        return routeSegments;
    }

    public void setRouteSegments(RouteList routeSegments)
    {
        this.routeSegments = routeSegments;
    }

    public List<City> getCities()
    {
        return cities;
    }

    public void setCities(List<City> cities)
    {
        this.cities = cities;
    }
}
