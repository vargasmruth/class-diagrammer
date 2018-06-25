package UI.Canvas.MouseDrawinEvents;

import Domain.Board.Board;
import Domain.Shape.MainClass;
import Domain.Shape.Models.Point;
import Domain.Shape.Shape;

import java.util.Optional;

public class SelectEvents implements MouseDrawingEvents {

    private Board board;
    private Point startPoint;

    public SelectEvents(Board board) {
        this.board = board;
    }

    @Override
    public boolean clicked(int x, int y) {
        board.selectShape(new Point(x, y));
        return true;
    }

    @Override
    public boolean pressed(int x, int y) {
        Shape shape = board.getShape(new Point(x, y));

        if (shape == null) {
            return false;
        }

        if (!board.isSelected(shape)) {
            return false;
        }

        if (startPoint == null) {
            startPoint = new Point(x, y);
        }

        return true;
    }

    @Override
    public boolean released(int x, int y) {
        if (startPoint == null) {
            return false;
        }
        int newX = x - startPoint.getX();
        int newY = y - startPoint.getY();
        board.moveSelected(newX, newY);
        return true;
    }

    @Override
    public boolean dragged(int x, int y) {
        if (startPoint == null) {
            return false;
        }
        int newX = x - startPoint.getX();
        int newY = y - startPoint.getY();
        board.moveSelected(newX, newY);
        startPoint = new Point(x, y);
        return true;
    }

}
