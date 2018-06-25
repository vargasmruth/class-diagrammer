package Domain.Shape.Models;

import java.io.Serializable;

public class Size implements Serializable {

    private int height;
    private int width;

    public Size(int width, int height) {
        this.height = height;
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getHalfHeight() {
        return Math.round((float) (height / 2.0));
    }

    public int getHalfWidth() {
        return Math.round((float) (width / 2.0));
    }

    public int getWidth(int percentage) {
        return (int) (width * (percentage / (double) 100));
    }

    public int getHeight(int percentage) {
        return (int) (height * (percentage / (double) 100));
    }
}