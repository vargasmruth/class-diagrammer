package Domain.Shape.Models;

public class Text {

    private Point startPoint;
    private String text;


    public Text(Point startPoint, String text) throws Exception {
        if (startPoint == null) {
            throw new Exception("Start point can't be null");
        }

        if (text.isEmpty()) {
            throw  new Exception("Text can't be empty");
        }

        this.startPoint = startPoint;
        this.text = text;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public String getText() {
        return text;
    }
}
