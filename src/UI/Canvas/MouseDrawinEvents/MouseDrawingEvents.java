package UI.Canvas.MouseDrawinEvents;

public interface MouseDrawingEvents {
    boolean clicked(int x,int y);
    boolean pressed(int x,int y);
    boolean released(int x,int y);
    boolean dragged(int x,int y);
}
