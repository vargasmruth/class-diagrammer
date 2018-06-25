package Domain.Shape.Connectors;

import Domain.Shape.Connector;
import Domain.Shape.MainClass;
import Domain.Shape.Models.Line;
import Domain.Shape.Models.Point;
import Domain.Shape.Models.Size;
import Domain.Shape.ObjectsToDraw;
import Domain.Shape.Shape;

import java.util.ArrayList;

public class Inherit extends Connector implements Shape {

    public static final int ARROW_LENGTH = 12;
    public static final int ARROW_ANGLE = 25;
    public static final int HIGH_DIVISOR = 2;


    public Inherit(MainClass firstClass, MainClass secondClass) throws Exception {
        super(firstClass, secondClass);
    }

    public Inherit() {
        super();
    }

    @Override
    public Connector createConnector(MainClass firstClass, MainClass secondClass) throws Exception {
        return new Inherit(firstClass, secondClass);
    }

    @Override
    public ObjectsToDraw getObjectsToDraw() throws Exception {
        Line line = calculateShortLine();
        double angleLine = line.getAngle();
        Point point1 = calculatePointsArrow(line.getFinalPoint(), ARROW_LENGTH, angleLine - Math.toRadians(ARROW_ANGLE));
        Point point2 = calculatePointsArrow(line.getFinalPoint(), ARROW_LENGTH, angleLine + Math.toRadians(ARROW_ANGLE));
        Point point3 = calculateMiddlePoint(point1, point2);
        ArrayList<Line> lines = new ArrayList<Line>();
        lines.add(new Line(line.getStartPoint(), point3));
        lines.add(new Line(point1, line.getFinalPoint()));
        lines.add(new Line(point2, line.getFinalPoint()));
        lines.add(new Line(point1, point2));
        return new ObjectsToDraw(lines, null, null);
    }

    @Override
    public ObjectsToDraw getObjectsToDraw(Point position, Size size) throws Exception {

        int y = Math.round((float) position.getY() + (float) (size.getHeight() / HIGH_DIVISOR));

        Line line = new Line(
                new Point(position.getX(), y),
                new Point(position.getX() + size.getWidth(), y)
        );

        double angleLine = line.getAngle();
        Point point1 = calculatePointsArrow(line.getFinalPoint(), ARROW_LENGTH, angleLine - Math.toRadians(ARROW_ANGLE));
        Point point2 = calculatePointsArrow(line.getFinalPoint(), ARROW_LENGTH, angleLine + Math.toRadians(ARROW_ANGLE));
        Point point3 = calculateMiddlePoint(point1, point2);
        ArrayList<Line> lines = new ArrayList<Line>();
        lines.add(new Line(line.getStartPoint(), point3));
        lines.add(new Line(point1, line.getFinalPoint()));
        lines.add(new Line(point2, line.getFinalPoint()));
        lines.add(new Line(point1, point2));
        return new ObjectsToDraw(lines, null, null);
    }

    @Override
    public boolean isLocated(Point point) {
        return super.isLocated(point);
    }
}
