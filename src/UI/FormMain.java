package UI;

import Domain.Board.Board;
import Domain.Shape.Classes.AbstractClass;
import Domain.Shape.Classes.InterfaceClass;
import Domain.Shape.Classes.NormalClass;
import Domain.Shape.Connectors.Association;
import Domain.Shape.Connectors.Composition;
import Domain.Shape.Connectors.DirectAssociation;
import Domain.Shape.Connectors.Inherit;
import Domain.Shape.Shape;
import UI.Canvas.Canvas;
import UI.MenuBar.DetailShapeBar;
import UI.MenuBar.MenuBar;
import UI.ToolBar.ToolBar;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FormMain extends JFrame {

    private UI.Canvas.Canvas canvas;
    private ToolBar toolBar;
    private MenuBar menuBar;
    private DetailShapeBar detailShapeBar;

    public FormMain() throws Exception {
        super("Class Diagrammer");
        instanceControls();
        addControls();
    }

    private void instanceControls() throws Exception {
        Board board = new Board();
        menuBar = new MenuBar(getShapesMenu());
        canvas = new Canvas(board, menuBar);
        toolBar = new ToolBar(board, menuBar);
        detailShapeBar = new DetailShapeBar(board);
    }

    private void addControls() {
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(canvas, BorderLayout.CENTER);
        getContentPane().add(toolBar, BorderLayout.NORTH);
        getContentPane().add(menuBar, BorderLayout.WEST);
        getContentPane().add(detailShapeBar, BorderLayout.EAST);
    }

    public void showFrame() {
        setSize(700, 600);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public List<Shape> getShapesMenu() throws Exception {
        List<Shape> shapesMenu = new ArrayList<Shape>();
        shapesMenu.add(new NormalClass("Normal Class"));
        shapesMenu.add(new InterfaceClass("Interface Class"));
        shapesMenu.add(new AbstractClass("Abstract Class"));
        shapesMenu.add(new Association());
        shapesMenu.add(new DirectAssociation());
        shapesMenu.add(new Composition());
        shapesMenu.add(new Inherit());
        return shapesMenu;
    }
}