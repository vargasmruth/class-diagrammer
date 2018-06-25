package Domain.Shape.Models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class LineTest {


    @Test
    void createLine()  {


        Point onePoint = new Point(3,4);

        try {
            Line line = new Line(null, onePoint);
            fail("Start point can't be null");
        } catch (Exception e) {}

        try {
            Line line = new Line(onePoint, null);
            fail("Final point can't be null");
        } catch (Exception e) {}

    }

    @Test
    void getPointsLine() {

        Point starPoint = new Point(1,2);
        Point finalPoint = new Point(3,4);

        try {

            Line line = new Line(starPoint, finalPoint);
            assertEquals(1, line.getStartPoint().getX());
            assertEquals(2, line.getStartPoint().getY());

            assertEquals(3, line.getFinalPoint().getX());
            assertEquals(4, line.getFinalPoint().getY());

        } catch (Exception e) {
            fail(e.getMessage());
        }

    }

}