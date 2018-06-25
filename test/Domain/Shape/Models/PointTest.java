package Domain.Shape.Models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PointTest {


    @Test
    void getDataPoint() {

        Point point = new Point(3, 4);

        assertEquals(3, point.getX());
        assertEquals(4, point.getY());

    }
}