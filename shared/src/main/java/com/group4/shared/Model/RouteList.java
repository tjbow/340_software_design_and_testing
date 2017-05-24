package com.group4.shared.Model;

import java.util.List;

/**
 * Created by tyler on 5/22/17.
 */

public class RouteList
{
    private List<RouteSegment> routeList;

    public List<RouteSegment> getRouteList() {
        return routeList;
    }

    public void setRouteList(List<RouteSegment> routeList) {
        this.routeList = routeList;
    }

    public RouteSegment findRoute(String routeId){
        for(RouteSegment routeSegment : routeList){
            if(routeSegment.getRouteId().equals(routeId)) {
                return routeSegment;
            }
        }

        return null;
    }

    public void claimRoute(String routeId, Player owner){
        for(RouteSegment routeSegment : routeList){
            if(routeSegment.getRouteId().equals(routeId)) {
                routeSegment.setOwner(owner);
                routeSegment.setClaimed(true);
                return;
            }
        }
    }

    public int getScore(String routeId){
        for(RouteSegment routeSegment : routeList){
            if(routeSegment.getRouteId().equals(routeId)) {
                return routeSegment.getScore();
            }
        }

        return -1;
    }

    public int getLength(String routeId){
        for(RouteSegment routeSegment : routeList){
            if(routeSegment.getRouteId().equals(routeId)) {
                return routeSegment.getLength();
            }
        }

        return -1;
    }

    public Player getOwner(String routeId){
        for(RouteSegment routeSegment : routeList){
            if(routeSegment.getRouteId().equals(routeId)) {
                return routeSegment.getOwner();
            }
        }

        return null;
    }
}
