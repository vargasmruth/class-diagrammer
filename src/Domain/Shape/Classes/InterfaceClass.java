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

public class InterfaceClass extends MainClass implements Shape {

    public static final String TEXT_TYPE_CLASS = "I";

    public InterfaceClass(Point positionPoint, Size size, String text) throws Exception {
        super(positionPoint, size, text);
    }

    public InterfaceClass(String text) throws Exception {
        super(new Point(0, 0), new Size(0, 0), text);
    }

    @Override
    public MainClass createMainClass(Point positionPoint, Size size, String text) throws Exception {
        return new InterfaceClass(positionPoint, size, text);
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