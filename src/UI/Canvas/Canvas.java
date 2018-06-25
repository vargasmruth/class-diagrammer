package UI.Canvas;

import Domain.Board.ActionCanvas;
import Domain.Board.Board;
import Domain.Shape.Shape;
import UI.Canvas.MouseDrawinEvents.MouseDrawingEvents;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.*;

public class Canvas extends JPanel implements MouseListener, MouseMotionListener, ActionCanvas {

    private Board board;
    private MenuShapesDelegate menuDelegate;

    public Canvas(Board board, MenuShapesDelegate delegate) {
        this.board = board;
        this.board.setDelegateCanvas(this);
        this.menuDelegate = delegate;
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    private MouseDrawingEvents mouseDrawinEvents;

    @Override
    public void mouseClicked(MouseEvent e) {
        if (mouseDrawinEvents == null) {
            mouseDrawinEvents = menuDelegate.getMouseEvent(board);
        }
        if (mouseDrawinEvents.clicked(e.getX(), e.getY())) {
            mouseDrawinEvents = null;
            repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (mouseDrawinEvents == null) {
            mouseDrawinEvents = menuDelegate.getMouseEvent(board);
        }
        if (mouseDrawinEvents.pressed(e.getX(), e.getY())) {
            repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (mouseDrawinEvents.dragged(e.getX(), e.getY())) {
            repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (mouseDrawinEvents.released(e.getX(), e.getY())) {
            mouseDrawinEvents = null;
            this.menuDelegate.deselectAll();
            repaint();
        }
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        board.getShapes().forEach(shape -> {
            try {
              this.selectShape(board, shape, graphics);
              DrawObjects.drawShapes(graphics, shape.getObjectsToDraw());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void repaintCanvas(){
        repaint();
    }

    private void selectShape(Board board, Shape shape, Graphics graphics) {
        if (board.isSelected(shape)) {
            graphics.setColor(Color.BLUE);
        } else {
            graphics.setColor(Color.BLACK);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}
}