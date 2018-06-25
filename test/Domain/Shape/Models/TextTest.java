package Domain.Shape.Models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class TextTest {


    @Test
    void createText() {

        Point point = new Point(1,2);

        try {
            Text text = new Text(point, "");
            fail("Text can't be null");
        }catch (Exception e) {}

        try {
            Text text = new Text(null, "text");
            fail("Start point can't be null");
        }catch (Exception e) {}


        try {
            new Text(point, "text");
        }catch (Exception e) {
            fail(e.getMessage());
        }

    }

    @Test
    void getDataText() {

        try {
            Text text = new Text(new Point(1,2), "text data");

            assertEquals(1, text.getStartPoint().getX());
            assertEquals(2, text.getStartPoint().getY());

            assertEquals("text data", text.getText());


        }catch (Exception e) {
            fail(e.getMessage());
        }

    }
}