package Domain.Shape;

import Domain.Shape.Models.Line;
import Domain.Shape.Models.Polygon;
import Domain.Shape.Models.Text;

import java.util.ArrayList;
import java.util.List;

public class ObjectsToDraw {

    private List<Line> lines;
    private List<Polygon> polygons;
    private List<Text> texts;

    public ObjectsToDraw(List<Line> lines, List<Polygon> polygons, List<Text> texts) {
        if (lines == null) {
            lines = new ArrayList<>();
        }
        this.lines = lines;
        if (polygons == null) {
            polygons = new ArrayList<>();
        }
        this.polygons = polygons;
        if (texts == null) {
            texts = new ArrayList<>();
        }
        this.texts = texts;
    }

    public List<Line> getLines() {
        return lines;
    }

    public List<Polygon> getPolygons() {
        return polygons;
    }

    public List<Text> getTexts() {
        return texts;
    }
}
