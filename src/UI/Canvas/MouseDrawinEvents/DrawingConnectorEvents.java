package UI.Canvas.MouseDrawinEvents;

import Domain.Board.Board;
import Domain.Shape.Connector;
import Domain.Shape.MainClass;
import Domain.Shape.Models.Point;
import Domain.Shape.Shape;

import java.util.Optional;

public class DrawingConnectorEvents implements MouseDrawingEvents {

    private Connector connector;
    private Board board;
    private Point startPoint;
    private Connector connectorShape;

    public DrawingConnectorEvents(Connector connector, Board board) {
        this.connector = connector;
        this.board = board;
    }

    @Override
    public boolean clicked(int x, int y) {
        return false;
    }

    @Override
    public boolean pressed(int x, int y) {
        Optional<MainClass> firstClass = Optional.ofNullable(board.getMainClass(new Point(x, y)));
        if (!firstClass.isPresent()) {
            return false;
        }

        try {
            if (startPoint == null) {
                startPoint = new Point(x, y);
                connectorShape = connector.createConnector(firstClass.get(), null);
                connectorShape.setTemporalPoint(startPoint);
                board.addShape(connectorShape);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean released(int x, int y) {
        if (startPoint == null) {
            return false;
        }
        try {
            Optional<MainClass> secondClass = Optional.ofNullable(board.getMainClass(new Point(x, y)));
            if (secondClass.isPresent()) {
                connectorShape.setSecondClass(secondClass.get());
            } else {
                board.removeShape(connectorShape);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean dragged(int x, int y) {
        if (startPoint == null) {
            return false;
        }
        connectorShape.setTemporalPoint(new Point(x,y));
        return true;
    }

}
