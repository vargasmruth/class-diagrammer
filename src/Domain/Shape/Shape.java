package Domain.Shape;

import Domain.Shape.Models.Point;
import Domain.Shape.Models.Size;

import java.io.Serializable;

public interface Shape extends Serializable {

    ObjectsToDraw getObjectsToDraw() throws Exception;

    ObjectsToDraw getObjectsToDraw(Point position, Size size) throws Exception;

    boolean isLocated(Point point);
}