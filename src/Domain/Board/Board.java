package Domain.Board;

import Domain.Shape.MainClass;
import Domain.Shape.Models.Point;
import Domain.Shape.Shape;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

public class Board {

    private List<ObserverBoard> observers = new ArrayList<>();

    private List<Shape> shapes;
    private Shape selectShape;
    private Repository repository;
    private ActionCanvas delegateCanvas;
    private Stack<Shape> listShapes = new Stack<>();

    public Board() {
        this.shapes = new ArrayList<>(0);
        this.selectShape = null;
    }

    public void add(ObserverBoard o) {
        observers.add(o);
    }

    public void setDelegateCanvas(ActionCanvas delegateCanvas) {
        this.delegateCanvas = delegateCanvas;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public Repository getRepository() {
        return repository;
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public void saveData() throws Exception {
        Optional.ofNullable(repository)
            .orElseThrow(() -> new Exception("To save data need assign a repository"))
            .save(shapes);
    }

    public void loadData() throws Exception {
        shapes = Optional.ofNullable(repository)
                    .orElseThrow(() -> new Exception("To save data need assign a repository"))
                    .load();
        repaint();
    }

    public Shape getSelectedShape() {
        return selectShape;
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    public void removeShape(Shape shape) throws Exception {
        if (!shapes.contains(shape)) {
            throw new Exception("Board not contain this object.");
        }
        shapes.remove(shape);
    }

    public MainClass getMainClass(Point point) {
        return this.shapes.stream()
            .filter(shape -> shape instanceof MainClass && shape.isLocated(point))
            .map(shape -> (MainClass) shape).findFirst().orElse(null);
    }

    public Shape getShape(Point point) {
        return shapes.stream().filter(shape -> shape.isLocated(point))
            .findFirst().orElse(null);
    }

    public void selectShape(Point point) {
        shapes.stream().filter(shape -> shape.isLocated(point))
            .forEach(shape -> {
                if (selectShape == shape) {
                    selectShape = null;
                } else {
                    selectShape = shape;
                }
                executeUpdateObserver();
            });
    }

    public boolean isSelected(Shape shape) {
        return selectShape == shape;
    }

    public void undo() {
        if (shapes.isEmpty()) {
            return;
        }
        Shape shape = shapes.get(shapes.size() - 1);
        listShapes.push(shapes.get(shapes.size() - 1));
        shapes.remove(shape);
        repaint();
    }

    public void redo(){
        if (listShapes.isEmpty()) {
            return;
        }
        shapes.add(listShapes.pop());
        repaint();
    }

    public void clean(){
        shapes.clear();
        repaint();
    }

    public void moveSelected(int x, int y) {
        if (selectShape != null && selectShape instanceof  MainClass) {
            ((MainClass) selectShape).move(x,y);
            executeUpdateObserver();
        }
    }

    private void repaint(){
        Optional.ofNullable(delegateCanvas).ifPresent(ActionCanvas::repaintCanvas);
    }


    private void executeUpdateObserver() {
        for (ObserverBoard observer : observers) {
            observer.updateShapes();
        }
    }

    public void updateSelectShape(MainClass mainClass) {

        shapes.remove(selectShape);
        shapes.add(mainClass);
        selectShape = mainClass;
        repaint();

    }
}