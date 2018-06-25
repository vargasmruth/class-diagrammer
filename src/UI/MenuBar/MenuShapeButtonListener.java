package UI.MenuBar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuShapeButtonListener implements ActionListener {

    MenuSelectShapeDelegate delegate;

    public MenuShapeButtonListener(MenuSelectShapeDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof ShapeButton) {
            ShapeButton button = (ShapeButton) e.getSource();
            if (delegate != null) {
                delegate.menuSelect(button);
            }
        }
    }

}
