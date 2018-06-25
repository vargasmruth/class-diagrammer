package Domain.Shape.Models;


import java.util.Arrays;

public class Polygon {

    private Point[] points;

    public Polygon(Point[] points) throws Exception {

        if (points == null) {
            throw new Exception("Points can't be null");
        }
        if (points.length <= 0) {
            throw new Exception("Points length can't be 0");
        }

        this.points = points;
    }

    public int getNumberOfPoints() {
        return points.length;
    }

    public int[] getPointsX() {
        return Arrays.stream(points).mapToInt(Point::getX).toArray();
    }

    public int[] getPointsY() {
        return Arrays.stream(points).mapToInt(Point::getY).toArray();
    }
}