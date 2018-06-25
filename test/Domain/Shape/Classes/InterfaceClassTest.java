package Domain.Shape.Classes;

import Domain.Shape.Models.*;
import Domain.Shape.ObjectsToDraw;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class InterfaceClassTest {


    @Test
    void validaClassDrawing() {

        Point startPoint = new Point(0, 0);
        Size size = new Size(100, 100);
        String text = "Text Sample";

        InterfaceClass interfaceClass = null;
        try {

            interfaceClass = new InterfaceClass(startPoint, size, text);

            ObjectsToDraw objToDraw = interfaceClass.getObjectsToDraw();

            List<Line> lines = objToDraw.getLines();
            List<Text> texts = objToDraw.getTexts();
            List<Polygon> polygons = objToDraw.getPolygons();

            assertEquals(9, lines.size(), "Interface Class has 9 lines");
            assertEquals(2, texts.size(), "Interface Class has 2 texts");
            assertEquals(0, polygons.size(), "Interface Class has 0 polygons");

        } catch (Exception e) {
            fail(e.getMessage());
        }

    }
}