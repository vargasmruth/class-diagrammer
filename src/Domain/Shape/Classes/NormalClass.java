package Domain.Shape.Classes;

import Domain.Shape.MainClass;
import Domain.Shape.Models.Line;
import Domain.Shape.Models.Point;
import Domain.Shape.Models.Size;
import Domain.Shape.Models.Text;
import Domain.Shape.ObjectsToDraw;
import Domain.Shape.Shape;

import java.util.ArrayList;
import java.util.List;

public class NormalClass extends MainClass implements Shape {

    public NormalClass(Point positionPoint, Size size, String text) throws Exception {
        super(positionPoint, size, text);
    }

    @Override
    public MainClass createMainClass(Point positionPoint, Size size, String text) throws Exception {
        return new NormalClass(positionPoint, size, text);
    }

    public NormalClass(String text) throws Exception {
        super(new Point(0, 0), new Size(0, 0), text);
    }

    @Override
    public ObjectsToDraw getObjectsToDraw() throws Exception {
        return getObjectsToDraw(getPositionPoint(), getSize());
    }

    @Override
    public ObjectsToDraw getObjectsToDraw(Point position, Size size) throws Exception {
        ArrayList<Line> lines = this.getLines(position, size);

        Point point = new Point(position.getX() + size.getHalfWidth(), position.getY() + size.getHalfHeight());
        List<Text> texts = new ArrayList<>();
        texts.add(new Text(point, getText()));

        return new ObjectsToDraw(lines, null, texts);
    }

    private ArrayList<Line> getLines(Point position, Size size) throws Exception {
        Point point1 = new Point(position.getX(), position.getY());
        Point point2 = new Point(position.getX() + size.getWidth(), position.getY());
        Point point3 = new Point(position.getX() + size.getWidth(), position.getY() + size.getHeight());
        Point point4 = new Point(position.getX(), position.getY() + size.getHeight());

        ArrayList<Line> lines = new ArrayList<>();
        lines.add(new Line(point1, point2));
        lines.add(new Line(point2, point3));
        lines.add(new Line(point3, point4));
        lines.add(new Line(point4, point1));
        return lines;
    }

    @Override
    public boolean isLocated(Point point) {
        return super.isLocated(point);
    }
}