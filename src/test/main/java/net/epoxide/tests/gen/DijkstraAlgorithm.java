package net.epoxide.tests.gen;

import net.epoxide.tinker.tile.Tile;
import net.epoxide.tinker.util.Direction;

import java.awt.*;
import java.util.*;
import java.util.List;

public class DijkstraAlgorithm {

    private final List<Point> nodes;
    private Set<Point> settledNodes;
    private Set<Point> unSettledNodes;
    private Map<Point, Point> predecessors;
    private Map<Point, Integer> distance;

    public DijkstraAlgorithm(int width, int height) {
        this.nodes = new ArrayList<>();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                nodes.add(new Point(x, y));
            }
        }
    }

    public void execute(Point source) {
        settledNodes = new HashSet<>();
        unSettledNodes = new HashSet<>();
        distance = new HashMap<>();
        predecessors = new HashMap<>();
        distance.put(source, 0);
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            Point node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    private void findMinimalDistances(Point node) {
        List<Point> adjacentNodes = getNeighbors(node);
        adjacentNodes.stream().filter(target -> getShortestDistance(target) > getShortestDistance(node) + getDistance(node, target)).forEach(target -> {
            distance.put(target, getShortestDistance(node) + getDistance(node, target));
            predecessors.put(target, node);
            unSettledNodes.add(target);
        });

    }

    private int getDistance(Point node, Point target) {
        for (Direction d : Direction.getUDLR()) {
            if (node.x + d.x == target.x && node.y + d.y == target.y) {
                Tile tile = DungeonGen.tileMap[target.x][target.y];
                return tile == Tile.SLIME ? 1 : tile == Tile.STONE ? 100 : 5;
            }
        }
        throw new RuntimeException("Should not happen");
    }

    private List<Point> getNeighbors(Point node) {
        List<Point> neighbors = new ArrayList<>();
        for (Direction d : Direction.getUDLR()) {
            Optional<Point> dest = this.nodes.stream().filter(point -> point.x == node.x + d.x && point.y == node.y + d.y && !this.settledNodes.contains(point)).findFirst();
            if (dest.isPresent())
                neighbors.add(dest.get());
        }
        return neighbors;
    }

    private Point getMinimum(Set<Point> vertexes) {
        Point minimum = null;
        for (Point vertex : vertexes) {
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

    private int getShortestDistance(Point destination) {
        Integer d = distance.get(destination);
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
    public LinkedList<Point> getPath(Point target) {
        LinkedList<Point> path = new LinkedList<>();
        Point step = target;
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
        return path;
    }

} 
