package Domain.Shape;

import Domain.Shape.Models.Line;
import Domain.Shape.Models.Point;
import Domain.Shape.Models.Size;
import Domain.Shape.Models.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class MainClass implements Serializable, Shape {

    private static final int PERCENTAGES_START_DETAIL_SQUARE = 80;
    private static final int PERCENTAGE_FINISH_DETAIL_SQUARE = 96;

    public static final int MIN_SIZE_WIDTH = 200;
    public static final int MIN_SIZE_HEIGHT = 100;

    private Point positionPoint;
    private Size size;
    private String text;

    public MainClass(Point positionPoint, Size size, String text) throws Exception {
        if (positionPoint == null) {
            throw new Exception("Position point can't be null");
        }
        if (size == null) {
            throw new Exception("Size can't be null");
        }
        if (text.isEmpty()) {
            throw new Exception("Size can't be null");
        }
        this.positionPoint = positionPoint;
        this.text = text;
        this.setSize(size);
    }

    public abstract MainClass createMainClass(Point positionPoint, Size size, String text) throws Exception;

    public Point getPositionPoint() {
        return positionPoint;
    }

    public void setPositionPoint(Point positionPoint) {
        this.positionPoint = positionPoint;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        Integer maxWidth = Integer.max(MIN_SIZE_WIDTH, size.getWidth());
        Integer maxHeight = Integer.max(MIN_SIZE_HEIGHT, size.getHeight());
        Size newSize = new Size(maxWidth, maxHeight);
        this.size = newSize;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    protected Point[] getUnionPoints() {

        Point point1 = new Point(positionPoint.getX() + size.getWidth(50), positionPoint.getY());
        Point point2 = new Point(positionPoint.getX(), positionPoint.getY() + size.getHeight(50));
        Point point3 = new Point(positionPoint.getX() + size.getWidth(50), positionPoint.getY() + size.getHeight());
        Point point4 = new Point(positionPoint.getX() + size.getWidth(), positionPoint.getY() + size.getHeight(50));
        return new Point[]{point1, point2, point3, point4};

    }

    public boolean isLocated(Point point) {
        return (isBetween(point.getX(), positionPoint.getX(), (positionPoint.getX() + size.getWidth())) &&
                isBetween(point.getY(), positionPoint.getY(), (positionPoint.getY() + size.getHeight())));

    }

    protected boolean isBetween(int value, int first, int second) {
        return first <= value && value <= second;
    }

    public void move(int x, int y) {
        positionPoint = new Point(positionPoint.getX() + x, positionPoint.getY() + y);
    }

    public ObjectsToDraw getObjectsToDraw(Point position, Size size, String typeText, String text) throws Exception{
        List<Line> lines = this.getLinesClass(position, size);

        int widthHalfLittleSquare = size.getWidth(PERCENTAGE_FINISH_DETAIL_SQUARE - PERCENTAGES_START_DETAIL_SQUARE) / 2;

        this.getPointAbstract(position, size, lines, widthHalfLittleSquare);

        int percentageCenterLittleSquare = PERCENTAGES_START_DETAIL_SQUARE + ((PERCENTAGE_FINISH_DETAIL_SQUARE - PERCENTAGES_START_DETAIL_SQUARE) / 2);

        List<Text> texts = new ArrayList<Text>();
        texts.add(new Text(new Point(position.getX() + size.getHalfWidth(), position.getY() + size.getHalfHeight()), text));
        texts.add(new Text(new Point(position.getX() + size.getWidth(percentageCenterLittleSquare), position.getY()), typeText));

        return new ObjectsToDraw(lines, null, texts);
    }

    private List<Line> getLinesClass(Point position, Size size) throws Exception {
        Point point1 = new Point(position.getX(), position.getY());
        Point point2 = new Point(position.getX() + size.getWidth(PERCENTAGES_START_DETAIL_SQUARE), position.getY());
        Point point3 = new Point(position.getX() + size.getWidth(PERCENTAGE_FINISH_DETAIL_SQUARE), position.getY());
        Point point4 = new Point(position.getX() + size.getWidth(), position.getY());
        Point point5 = new Point(position.getX() + size.getWidth(), position.getY() + size.getHeight());
        Point point6 = new Point(position.getX(), position.getY() + size.getHeight());

        List<Line> lines = new ArrayList<>();
        lines.add(new Line(point1, point2));
        lines.add(new Line(point3, point4));
        lines.add(new Line(point4, point5));
        lines.add(new Line(point5, point6));
        lines.add(new Line(point6, point1));
        return lines;
    }

    private void getPointAbstract(Point position, Size size, List<Line> lines, int widthHalfLittleSquare) throws Exception {
        Point pointAbstract1 = new Point(position.getX() + size.getWidth(PERCENTAGES_START_DETAIL_SQUARE), position.getY() - widthHalfLittleSquare);
        Point pointAbstract2 = new Point(position.getX() + size.getWidth(PERCENTAGE_FINISH_DETAIL_SQUARE), position.getY() - widthHalfLittleSquare);
        Point pointAbstract3 = new Point(position.getX() + size.getWidth(PERCENTAGE_FINISH_DETAIL_SQUARE), position.getY() + widthHalfLittleSquare);
        Point pointAbstract4 = new Point(position.getX() + size.getWidth(PERCENTAGES_START_DETAIL_SQUARE), position.getY() + widthHalfLittleSquare);

        lines.add(new Line(pointAbstract1, pointAbstract2));
        lines.add(new Line(pointAbstract2, pointAbstract3));
        lines.add(new Line(pointAbstract3, pointAbstract4));
        lines.add(new Line(pointAbstract4, pointAbstract1));
    }

}