package UI.MenuBar;

import Domain.Shape.Models.Point;
import Domain.Shape.Models.Size;
import Domain.Shape.ObjectsToDraw;
import Domain.Shape.Shape;
import UI.Canvas.DrawObjects;
import java.awt.*;
import javax.swing.*;

public class ShapeButton extends JButton {

    public static final int DISTANCE_PADDING = 20;

    private Shape shape;
    public Boolean isSelected;

    public ShapeButton(Shape shape) {
        this.shape = shape;
        this.isSelected = false;
    }

    public Shape getShape() {
        return shape;
    }

    @Override
    protected void paintBorder(Graphics g) {
        super.paintBorder(g);
        try {
            g.setFont(new Font("Arial", Font.PLAIN, 10));
            if (isSelected) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.BLACK);
            }

            Dimension dimension = getSize();
            int TotalPadding = DISTANCE_PADDING * 2;
            ObjectsToDraw objectsToDraw = shape.getObjectsToDraw(new Point(DISTANCE_PADDING,
                DISTANCE_PADDING), new Size(dimension.width - TotalPadding, dimension.height - TotalPadding));

            DrawObjects.drawShapes(g, objectsToDraw);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
