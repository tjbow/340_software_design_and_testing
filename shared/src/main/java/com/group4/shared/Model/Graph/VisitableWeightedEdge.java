package com.group4.shared.Model.Graph;

import org.jgrapht.graph.DefaultWeightedEdge;

/*
 * Created by Tom on 6/5/2017.
 */

/**
 * An edge class that keeps track of whether it has been visited
 */
public class VisitableWeightedEdge extends DefaultWeightedEdge
{
    private boolean visited = false;

    public boolean isVisited()
    {
        return visited;
    }

    public void setVisited(boolean visited)
    {
        this.visited = visited;
    }

}
