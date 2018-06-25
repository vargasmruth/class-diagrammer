package UI.Canvas;

import Domain.Shape.Models.Line;
import Domain.Shape.Models.Polygon;
import Domain.Shape.Models.Text;
import Domain.Shape.ObjectsToDraw;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Draw Objects.
 *
 * @author David
 * @since 24/04/2018
 */
public interface DrawObjects {
  static void drawShapes(Graphics graphics, ObjectsToDraw objectsToDraw) {
    drawLines(graphics, objectsToDraw.getLines());
    drawTexts(graphics, objectsToDraw.getTexts());
    drawPolygons(graphics, objectsToDraw.getPolygons());
  }

  static void drawLines(Graphics graphics, java.util.List<Line> lines) {
    lines.forEach(line -> graphics.drawLine(line.getStartPoint().getX(),
        line.getStartPoint().getY(),
        line.getFinalPoint().getX(),
        line.getFinalPoint().getY()));
  }

  static void drawTexts(Graphics graphics, java.util.List<Text> texts) {
    texts.forEach(text -> {
      FontMetrics metrics = graphics.getFontMetrics(graphics.getFont());
      Rectangle2D rectangle = metrics.getStringBounds(text.getText(), graphics);

      int textWidth = (int) Math.round(rectangle.getWidth() / 2.0);
      int textHeight = (int) Math.round(rectangle.getHeight() / 2.0);

      graphics.drawString(text.getText(), text.getStartPoint().getX() - textWidth, text.getStartPoint().getY() + textHeight);
    });
  }

  static void drawPolygons(Graphics graphics, java.util.List<Polygon> polygons) {
    polygons.forEach(polygon -> graphics.fillPolygon(polygon.getPointsX(), polygon.getPointsY(), polygon.getNumberOfPoints()));
  }
}
