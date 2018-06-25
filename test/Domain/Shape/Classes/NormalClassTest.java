package Domain.Shape.Classes;

import Domain.Shape.Models.*;
import Domain.Shape.ObjectsToDraw;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class NormalClassTest {

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

            assertEquals(4, lines.size(), "NormalClass Class has 4 lines");
            assertEquals(1, texts.size(), "NormalClass Class has 1 texts");
            assertEquals(0, polygons.size(), "NormalClass Class has 0 texts");

        } catch (Exception e) {
            fail(e.getMessage());
        }

    }
}