package Domain.Board;

import Domain.Shape.Models.Point;
import Domain.Shape.Models.Size;
import Domain.Shape.ObjectsToDraw;
import Domain.Shape.Shape;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class BoardTest {


    @Test
    void createdBoard() {

        try {
            new Board();

        } catch (Exception e) {
            fail(e.getMessage());
        }

        try {
            new Board();
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void repositoryUse() {


        Board board = null;
        try {
            board = new Board();
        } catch (Exception e) {
            fail(e.getMessage());
        }

        try {
            board.saveData();
            fail("Board needs a repository to save data.");
        } catch (Exception e) {
        }

        try {
            board.loadData();
            fail("Board needs a repository to load data.");
        } catch (Exception e) {
        }

        Repository respository = new Repository() {
            ArrayList<Shape> shapes;

            @Override
            public void save(List<Shape> shapes) throws Exception {
                this.shapes = new ArrayList(shapes);
            }

            @Override
            public List<Shape> load() throws Exception {
                return this.shapes;
            }

        };

        board.setRepository(respository);

        Shape shape = new Shape() {
            @Override
            public ObjectsToDraw getObjectsToDraw() throws Exception {
                return new ObjectsToDraw(null, null, null);
            }

            @Override
            public ObjectsToDraw getObjectsToDraw(Point position, Size size) throws Exception {
                return new ObjectsToDraw(null, null, null);
            }

            @Override
            public boolean isLocated(Point point) {
                return false;
            }
        };

        board.addShape(shape);

        try {
            board.saveData();
        } catch (Exception e) {
            fail(e.getMessage());
        }

        assertEquals(1, board.getShapes().size());

        try {
            board.removeShape(shape);
        } catch (Exception e) {
            fail(e.getMessage());
        }

        assertEquals(0, board.getShapes().size());


        try {
            board.loadData();
        } catch (Exception e) {
            fail(e.getMessage());
        }

        assertEquals(1, board.getShapes().size());
    }
}
