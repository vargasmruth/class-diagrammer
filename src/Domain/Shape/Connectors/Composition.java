package Domain.Shape.Connectors;

import Domain.Shape.Connector;
import Domain.Shape.MainClass;
import Domain.Shape.Models.Line;
import Domain.Shape.Models.Point;
import Domain.Shape.Models.Polygon;
import Domain.Shape.Models.Size;
import Domain.Shape.ObjectsToDraw;
import Domain.Shape.Shape;

import java.util.ArrayList;
import java.util.List;

public class Composition extends Connector implements Shape {

    private static final int ARROW_LENGTH = 12;
    private static final int ARROW_ANGLE = 25;
    public static final int HIGH_DIVISOR = 2;

    public Composition(MainClass firstClass, MainClass secondClass) throws Exception {
        super(firstClass, secondClass);
    }

    public Composition() {
        super();
    }

    @Override
    public Connector createConnector(MainClass firstClass, MainClass secondClass) throws Exception {
        return new Composition(firstClass, secondClass);
    }

    @Override
    public ObjectsToDraw getObjectsToDraw() throws Exception {
        Line line = calculateShortLine();
        double angleLine = line.getAngle();
        Point point1 = calculatePointsArrow(line.getFinalPoint(), ARROW_LENGTH, angleLine - Math.toRadians(ARROW_ANGLE));
        Point point2 = calculatePointsArrow(line.getFinalPoint(), ARROW_LENGTH, angleLine + Math.toRadians(ARROW_ANGLE));
        Point point3 = calculatePointsArrow(point1, ARROW_LENGTH, angleLine - Math.toRadians(-ARROW_ANGLE));
        Polygon polygonInherit = new Polygon(new Point[]{line.getFinalPoint(), point1, point3, point2});
        ArrayList<Line> lines = new ArrayList<Line>();
        lines.add(line);
        List<Polygon> polygons = new ArrayList<Polygon>();
        polygons.add(polygonInherit);
        return new ObjectsToDraw(lines, polygons, null);
    }

    @Override
    public ObjectsToDraw getObjectsToDraw(Point position, Size size) throws Exception {
        Line line = new Line(
                new Point(position.getX(), position.getY() + size.getHeight() / HIGH_DIVISOR),
                new Point(position.getX() + size.getWidth(), position.getY() + size.getHeight() / HIGH_DIVISOR)
        );
        double angleLine = line.getAngle();
        Point point1 = calculatePointsArrow(line.getFinalPoint(), ARROW_LENGTH, angleLine - Math.toRadians(ARROW_ANGLE));
        Point point2 = calculatePointsArrow(line.getFinalPoint(), ARROW_LENGTH, angleLine + Math.toRadians(ARROW_ANGLE));
        Point point3 = calculatePointsArrow(point1, ARROW_LENGTH, angleLine - Math.toRadians(-ARROW_ANGLE));
        Polygon polygonInherit = new Polygon(new Point[]{line.getFinalPoint(), point1, point3, point2});
        ArrayList<Line> lines = new ArrayList<Line>();
        lines.add(line);
        List<Polygon> polygons = new ArrayList<Polygon>();
        polygons.add(polygonInherit);
        return new ObjectsToDraw(lines, polygons, null);
    }

    @Override
    public boolean isLocated(Point point) {
        return super.isLocated(point);
    }
}
