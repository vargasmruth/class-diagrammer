package UI.Canvas.MouseDrawinEvents;

import Domain.Board.Board;
import Domain.Shape.MainClass;
import Domain.Shape.Models.Point;
import Domain.Shape.Models.Size;
import Domain.Shape.Shape;

import javax.swing.*;
import java.util.Optional;

public class DrawingClassEvents implements MouseDrawingEvents {

    private MainClass mainClass;
    private Board board;
    private Point startPoint;
    private MainClass mainShape;

    public DrawingClassEvents(MainClass mainClass, Board board) {
        this.mainClass = mainClass;
        this.board = board;
    }

    @Override
    public boolean clicked(int x, int y) {
        return false;
    }

    @Override
    public boolean pressed(int x, int y) {
        try {
            if (startPoint == null) {
                startPoint = new Point(x, y);
                mainShape = mainClass.createMainClass(startPoint, new Size(0, 0), "Class");
                board.addShape(mainShape);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean released(int x, int y) {
        final int width = x - startPoint.getX();
        final int height = y - startPoint.getY();
        mainShape.setSize(new Size(width, height));
        try {
            String response = JOptionPane.showInputDialog("Please enter the class name");
            if (response == null) {
                board.removeShape(mainShape);
            } else {
                mainShape.setText(response);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean dragged(int x, int y) {
        final int width = x - startPoint.getX();
        final int height = y - startPoint.getY();
        mainShape.setSize(new Size(width, height));
        return true;
    }

}
