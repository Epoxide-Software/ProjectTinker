package net.epoxide.tests.gen;

import net.epoxide.tests.utils.MathUtil;
import net.epoxide.tinker.TinkerGame;
import net.epoxide.tinker.tile.Tile;
import net.epoxide.tinker.util.Direction;
import net.epoxide.tinker.util.RegistryName;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class DungeonGen {

    private final Tile DOOR = new Tile("tinker:door");
    private static final int WIDTH = 100;
    private static final int HEIGHT = 100;

    //TODO Tweak
    int roomAttempts = 200;
    int minRoomSize = 15;
    int maxRoomSize = 30;

    public static Tile[][] tileMap;

    private List<Room> rooms = new ArrayList<>();

    public void generate() {

        this.tileMap = new Tile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                this.tileMap[x][y] = Tile.VOID;
            }
        }
        createRooms();
        createDoor();
        DijkstraAlgorithm test = new DijkstraAlgorithm(WIDTH, HEIGHT);

        HashMap<Point, List<Point>> doorDoor = new HashMap<>();
        for (Room r1 : rooms) {
            for (Room r2 : rooms) {
                if (r1 != r2)
                    for (Point d1 : r1.doors) {
                        Point finalDoor = null;
                        for (Point d2 : r2.doors) {
                            if (finalDoor == null || d1.distance(d2) < d1.distance(finalDoor)) {
                                finalDoor = d2;
                            }
                        }

                        List<Point> map = doorDoor.get(d1);
                        if (map == null) {
                            map = new ArrayList<>();
                            doorDoor.put(d1, map);
                        }
                        map.add(finalDoor);
                        doorDoor.put(d1, map);
                    }
            }
        }
        int i = 1;
        for (Point d1 : doorDoor.keySet()) {
            System.out.println((i++) + "/" + doorDoor.keySet().size());
            test.execute(d1);
            for (Point d2 : doorDoor.get(d1)) {
                if (test.getPath(d2) != null)
                    test.getPath(d2).forEach(point -> this.tileMap[point.x][point.y] = Tile.SLIME);
            }
        }
    }


    private void createRooms() {
        int widthBuffer = 5;
        int heightBuffer = 5;
        for (int i = 0; i < roomAttempts; i++) {
            int width = TinkerGame.RANDOM.nextInt(maxRoomSize - minRoomSize) + minRoomSize;
            int height = TinkerGame.RANDOM.nextInt(maxRoomSize - minRoomSize) + minRoomSize;

            int x = TinkerGame.RANDOM.nextInt(WIDTH - width);
            int y = TinkerGame.RANDOM.nextInt(HEIGHT - height);

            boolean shouldAppend = true;
            for (Room r : rooms) {
                if (MathUtil.rectColliding(x, y, width, height, r.x, r.y, r.width, r.height, widthBuffer, heightBuffer) || x < 0 || y < 0 || x + width > WIDTH || y + height > HEIGHT) {
                    shouldAppend = false;
                    break;
                }
            }

            if (shouldAppend) {
                rooms.add(new Room(width, height, x, y));
            }
        }

        for (Room r : rooms) {
            for (int i = r.x; i < r.x + r.width; i++) {
                for (int j = r.y; j < r.y + r.height; j++) {
                    this.tileMap[i][j] = Tile.STONE;
                }
            }
        }
    }

    private void createDoor() {
        for (Room r : rooms) {
//            for (int i = 0; i < TinkerGame.RANDOM.nextInt(2) + 2; i++) {
            int x = 0;
            int y = TinkerGame.RANDOM.nextInt(r.height);
            long match = r.doors.stream().filter(door -> door.x == x + r.x && door.y == y + r.y).count();
            if (match == 0) {
                r.doors.add(new Point(x + r.x, y + r.y));
                this.tileMap[x + r.x][y + r.y] = DOOR;
            }
        }
//        }
    }

//    private void createHallways() {
//        // closest pathway
//        Point door1 = rooms.get(0).doors.get(0);
//        Point door2 = rooms.get(1).doors.get(0);
//
//        List<PointDistance> distanceList = new ArrayList<>();
//        for (int x = 0; x < WIDTH; x++) {
//            for (int y = 0; y < HEIGHT; y++) {
//                if (door1.getX() == x && door1.getY() == y)
//                    distanceList.add(new PointDistance(x, y, 0, null));
//                if (this.tileMap[x][y] != Tile.STONE) {
//                    distanceList.add(new PointDistance(x, y, Integer.MAX_VALUE, null));
//                    if (x == door2.x && y == door2.y) {
//                        System.out.println("test");
//                    }
//                }
//            }
//        }
//        PointDistance finalDoor = null;
//        Direction prevDir = Direction.UNKNOWN;
//        while (!distanceList.isEmpty()) {
//            PointDistance point = distanceList.stream().sorted((o1, o2) -> (o1.distance < o2.distance) ? -1 : (o1.distance > o2.distance) ? 1 : 0).findFirst().get();
//            distanceList.remove(point);
//
//            for (Direction dir : Direction.getUDLR()) {
//                int x = point.x + dir.x;
//                int y = point.y + dir.y;
//
//                if (x < 0 || y < 0 || x >= WIDTH || y >= HEIGHT || this.tileMap[x][y] == Tile.STONE)
//                    continue;
//
//                Optional<PointDistance> pointDist = distanceList.stream().filter(pointDistance -> pointDistance.x == x && pointDistance.y == y).findFirst();
//                if (pointDist.isPresent()) {
//                    PointDistance pointDir = pointDist.get();
//
//                    int dist = this.tileMap[x][y].ID != new RegistryName("tinker:stone") ? ((dir == prevDir) ? 1 : 2) : 100;
//                    if (point.distance + dist < pointDir.distance) {
//                        pointDir.distance = point.distance + dist;
//                        pointDir.parent = point;
//                        prevDir = dir;
//
//                        if (pointDir.x == door2.x && pointDir.y == door2.y) {
//                            finalDoor = pointDir;
//                            System.out.println(pointDir.distance);
//                        }
//                    }
//                }
//            }
//        }
//        if (finalDoor != null) {
//            PointDistance test = finalDoor;
//            while (test.parent != null) {
//                this.tileMap[test.x][test.y] = Tile.SLIME;
//                test = test.parent;
//            }
//        }
//    }

    public void printToImage() {
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                Tile tile = this.tileMap[x][y];
                if (tile == Tile.STONE) {
                    image.setRGB(x, y, Color.BLUE.getRGB());
                } else if (tile == Tile.VOID) {
                    image.setRGB(x, y, Color.BLACK.getRGB());
                } else if (tile == DOOR) {
                    image.setRGB(x, y, Color.GRAY.getRGB());
                }else if (tile == Tile.SLIME) {
                    image.setRGB(x, y, Color.GREEN.getRGB());
                }
            }
        }

        try {
            ImageIO.write(image, "PNG", new File("output.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
