package net.epoxide.tests.gen;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import net.epoxide.tinker.tile.Tile;
import net.epoxide.tinker.util.Direction;

public class DijkstraAlgorithm {
    
    private final List<Point> nodes;
    private Set<Point> settledNodes;
    private Set<Point> unSettledNodes;
    private Map<Point, Point> predecessors;
    private Map<Point, Integer> distance;
    
    public DijkstraAlgorithm(int width, int height) {
        this.nodes = new ArrayList<>();
        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++)
                this.nodes.add(new Point(x, y));
    }
    
    public void execute (Point source) {
        
        this.settledNodes = new HashSet<>();
        this.unSettledNodes = new HashSet<>();
        this.distance = new HashMap<>();
        this.predecessors = new HashMap<>();
        this.distance.put(source, 0);
        this.unSettledNodes.add(source);
        while (this.unSettledNodes.size() > 0) {
            final Point node = this.getMinimum(this.unSettledNodes);
            this.settledNodes.add(node);
            this.unSettledNodes.remove(node);
            this.findMinimalDistances(node);
        }
    }
    
    private void findMinimalDistances (Point node) {
        
        final List<Point> adjacentNodes = this.getNeighbors(node);
        adjacentNodes.stream().filter(target -> this.getShortestDistance(target) > this.getShortestDistance(node) + this.getDistance(node, target)).forEach(target -> {
            this.distance.put(target, this.getShortestDistance(node) + this.getDistance(node, target));
            this.predecessors.put(target, node);
            this.unSettledNodes.add(target);
        });
        
    }
    
    private int getDistance (Point node, Point target) {
        
        for (final Direction d : Direction.getUDLR())
            if (node.x + d.x == target.x && node.y + d.y == target.y) {
                final Tile tile = DungeonGen.tileMap[target.x][target.y];
                return tile == Tile.SLIME ? 1 : tile == Tile.STONE ? 100 : 5;
            }
        throw new RuntimeException("Should not happen");
    }
    
    private List<Point> getNeighbors (Point node) {
        
        final List<Point> neighbors = new ArrayList<>();
        for (final Direction d : Direction.getUDLR()) {
            final Optional<Point> dest = this.nodes.stream().filter(point -> point.x == node.x + d.x && point.y == node.y + d.y && !this.settledNodes.contains(point)).findFirst();
            if (dest.isPresent())
                neighbors.add(dest.get());
        }
        return neighbors;
    }
    
    private Point getMinimum (Set<Point> vertexes) {
        
        Point minimum = null;
        for (final Point vertex : vertexes)
            if (minimum == null)
                minimum = vertex;
            else if (this.getShortestDistance(vertex) < this.getShortestDistance(minimum))
                minimum = vertex;
        return minimum;
    }
    
    private int getShortestDistance (Point destination) {
        
        final Integer d = this.distance.get(destination);
        if (d == null)
            return Integer.MAX_VALUE;
        else
            return d;
    }
    
    /*
     * This method returns the path from the source to the selected target and NULL if no path
     * exists
     */
    public LinkedList<Point> getPath (Point target) {
        
        final LinkedList<Point> path = new LinkedList<>();
        Point step = target;
        // check if a path exists
        if (this.predecessors.get(step) == null)
            return null;
        path.add(step);
        while (this.predecessors.get(step) != null) {
            step = this.predecessors.get(step);
            path.add(step);
        }
        // Put it into the correct order
        Collections.reverse(path);
        return path;
    }
    
}
