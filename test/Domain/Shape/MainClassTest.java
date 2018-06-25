package Domain.Shape;

import Domain.Shape.Classes.NormalClass;
import Domain.Shape.Models.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class MainClassTest {


    @Test
    void createdClass() {

        Point positionPoint = new Point(100, 100);
        Size size = new Size(100, 100);
        String text = "Text Sample";


        try {
            new NormalClass(null, size, text);
            fail("Start point can't be null");
        } catch (Exception e) {
        }


        try {
            new NormalClass(positionPoint, null, text);
            fail("Size can't be null");
        } catch (Exception e) {
        }


        try {
            new NormalClass(positionPoint, size, null);
            fail("Text can't be empty");
        } catch (Exception e) {
        }


        try {
            new NormalClass(positionPoint, size, text);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }


    @Test
    void validaClassDrawing() {

        Point startPoint = new Point(0, 0);
        Size size = new Size(100, 100);
        String text = "Text Sample";

        NormalClass normalClass = null;
        try {
            normalClass = new NormalClass(startPoint, size, text);
            ObjectsToDraw objToDraw = normalClass.getObjectsToDraw();

            List<Line> lines = objToDraw.getLines();
            List<Text> texts = objToDraw.getTexts();
            List<Polygon> polygons = objToDraw.getPolygons();

            assertEquals(4, lines.size(), "NormalClass Class has 4 line");
            assertEquals(1, texts.size(), "NormalClass Class has 1 text");
            assertEquals(0, polygons.size(), "NormalClass Class has 0 text");

        } catch (Exception e) {
            fail(e.getMessage());
        }

    }
}