package UI.Canvas;

import Domain.Board.Board;
import UI.Canvas.MouseDrawinEvents.MouseDrawingEvents;

public interface MenuShapesDelegate {

    MouseDrawingEvents getMouseEvent(Board board);
    void deselectAll();

}
