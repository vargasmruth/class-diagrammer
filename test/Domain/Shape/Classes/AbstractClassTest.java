package Domain.Shape.Classes;

import Domain.Shape.Models.*;
import Domain.Shape.ObjectsToDraw;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class AbstractClassTest {

    @Test
    void validaClassDrawing() {

        Point startPoint = new Point(0, 0);
        Size size = new Size(100, 100);
        String text = "Text Sample";

        AbstractClass abstractClass = null;
        try {

            abstractClass = new AbstractClass(startPoint, size, text);
            ObjectsToDraw objToDraw = abstractClass.getObjectsToDraw();

            List<Line> lines = objToDraw.getLines();
            List<Text> texts = objToDraw.getTexts();
            List<Polygon> polygons = objToDraw.getPolygons();

            assertEquals(9, lines.size(), "Abstract Class has 9 lines");
            assertEquals(2, texts.size(), "Abstract Class has 2 texts");
            assertEquals(0, polygons.size(), "Abstract Class has 0 polygons");

        } catch (Exception e) {
            fail(e.getMessage());
        }

    }

}