package Domain.Shape.Classes;

import Domain.Shape.MainClass;
import Domain.Shape.Models.Point;
import Domain.Shape.Models.Size;
import Domain.Shape.ObjectsToDraw;
import Domain.Shape.Shape;


public class AbstractClass extends MainClass implements Shape {

    private static final String TEXT_TYPE_CLASS = "A";

    public AbstractClass(Point positionPoint, Size size, String text) throws Exception {
        super(positionPoint, size, text);
    }

    public AbstractClass(String text) throws Exception {
        super(new Point(0, 0), new Size(0, 0), text);
    }

    @Override
    public MainClass createMainClass(Point positionPoint, Size size, String text) throws Exception {
        return new AbstractClass(positionPoint, size, text);
    }

    @Override
    public ObjectsToDraw getObjectsToDraw() throws Exception {
        return getObjectsToDraw(getPositionPoint(), getSize());
    }

    @Override
    public ObjectsToDraw getObjectsToDraw(Point position, Size size) throws Exception {
        return super.getObjectsToDraw(position, size, TEXT_TYPE_CLASS, getText());
    }

    @Override
    public boolean isLocated(Point point) {
        return super.isLocated(point);
    }
}