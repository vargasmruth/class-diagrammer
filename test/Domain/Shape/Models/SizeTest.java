package Domain.Shape.Models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SizeTest {

    @Test
    void getDataSize() {

        Size size = new Size(3, 4);

        assertEquals(4, size.getHeight());
        assertEquals(3, size.getWidth());

    }
}