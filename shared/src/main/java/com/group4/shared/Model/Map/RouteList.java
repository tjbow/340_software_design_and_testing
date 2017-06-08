package com.group4.shared.Model.Map;

import com.group4.shared.Model.Player;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by tyler on 5/22/17.
 */

public class RouteList
{
//    private List<RouteSegment> routeList;
    private Set<RouteSegment> routeList;

    public RouteList()
    {
        this.routeList = new HashSet<>();
    }

    public Set<RouteSegment> getRouteList() {
        return routeList;
    }

    public void setRouteList(Set<RouteSegment> routeList) {
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
                PLAYER_COLOR color;
                if(owner.getColor().equals("blue")) color = PLAYER_COLOR.BLUE;
                else if(owner.getColor().equals("red")) color = PLAYER_COLOR.RED;
                else if(owner.getColor().equals("green")) color = PLAYER_COLOR.GREEN;
                else if(owner.getColor().equals("yellow")) color = PLAYER_COLOR.YELLOW;
                else color = PLAYER_COLOR.BLACK;

                routeSegment.setPlayer_color(color);
                //routeSegment.setOwner(owner);
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
                //return routeSegment.getOwner();
            }
        }

        return null;
    }

    public boolean isClaimed(String routeId)
    {
        for(RouteSegment routeSegment : routeList){
            if(routeSegment.getRouteId().equals(routeId))
            {
                return routeSegment.isClaimed();
            }
        }
        return false;
    }
}
