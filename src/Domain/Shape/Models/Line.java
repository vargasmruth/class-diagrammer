package Domain.Shape.Models;


public class Line {

    private Point startPoint;
    private Point finalPoint;

    public Line(Point startPoint, Point finalPoint) throws Exception {

        if (startPoint == null) {
            throw new Exception("Start point can't be null");
        }
        if (finalPoint == null) {
            throw new Exception("Start point can't be null");
        }

        this.startPoint = startPoint;
        this.finalPoint = finalPoint;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getFinalPoint() {
        return finalPoint;
    }

    public double getLength() {
        return  Math.hypot(finalPoint.getX()-startPoint.getX(), finalPoint.getY()-startPoint.getY());
    }

    public double getAngle() {

        double ty = -(startPoint.getY() - finalPoint.getY());
        double tx = (startPoint.getX() - finalPoint.getX());

        double angle = Math.atan(ty / tx);

        if (tx < 0) {
            angle += Math.PI;
        }
        return angle;
    }
}
