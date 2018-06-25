package UI.MenuBar;

import Domain.Board.*;
import Domain.Shape.MainClass;
import Domain.Shape.Models.Point;
import Domain.Shape.Models.Size;
import Domain.Shape.Shape;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;



public class DetailShapeBar extends JPanel implements ObserverBoard, DocumentListener, ChangeListener {

    private Board board;
    private Shape selectShape;
    private Boolean isLoading;

    private JTextField textField;
    private JSpinner spinnerWidth;
    private JSpinner spinnerHeight;
    private JSpinner spinnerPositionX;
    private JSpinner spinnerPositionY;


    public DetailShapeBar(Board board) {
        super(new FlowLayout(FlowLayout.RIGHT));
        setPreferredSize(new Dimension(310, 500));
        this.board = board;
        setVisible(false);
        board.add(this);
        AddControls();
        isLoading = false;
    }

    public void update() {
        isLoading = true;
        selectShape = board.getSelectedShape();
        if (selectShape != null && selectShape instanceof MainClass) {
            setControl((MainClass)selectShape);
            setVisible(true);
        } else {
            setVisible(false);
        }
        revalidate();
        repaint();
        isLoading = false;
    }

    private void AddControls() {

        JPanel contentPane = new JPanel(new GridLayout(0, 2, 10, 10));
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nameLabel = new JLabel("Name", SwingConstants.TRAILING);
        contentPane.add(nameLabel);

        textField = new JTextField();
        textField.getDocument().addDocumentListener(this);
        contentPane.add(textField);

        JLabel WidthLabel = new JLabel("Width", SwingConstants.TRAILING);
        contentPane.add(WidthLabel);

        SpinnerModel widthModel = new SpinnerNumberModel( MainClass.MIN_SIZE_WIDTH,  MainClass.MIN_SIZE_WIDTH, Integer.MAX_VALUE, 1);
        spinnerWidth = new JSpinner(widthModel);
        spinnerWidth.addChangeListener(this);
        contentPane.add(spinnerWidth);

        JLabel heightLabel = new JLabel("Height", SwingConstants.TRAILING);
        contentPane.add(heightLabel);

        SpinnerModel heighthModel = new SpinnerNumberModel(MainClass.MIN_SIZE_HEIGHT, MainClass.MIN_SIZE_HEIGHT, Integer.MAX_VALUE, 1);
        spinnerHeight = new JSpinner(heighthModel);
        spinnerHeight.addChangeListener(this);
        contentPane.add(spinnerHeight);

        JLabel xLabel = new JLabel("Position X", SwingConstants.TRAILING);
        contentPane.add(xLabel);

        SpinnerModel xModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
        spinnerPositionX = new JSpinner(xModel);
        spinnerPositionX.addChangeListener(this);
        contentPane.add(spinnerPositionX);

        JLabel yLabel = new JLabel("Position Y", SwingConstants.TRAILING);
        contentPane.add(yLabel);

        SpinnerModel yModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
        spinnerPositionY = new JSpinner(yModel);
        spinnerPositionY.addChangeListener(this);
        contentPane.add(spinnerPositionY);

        add(contentPane);

    }

    private void setControl(MainClass mainClass) {
        textField.setText(mainClass.getText());
        spinnerWidth.setValue(mainClass.getSize().getWidth());
        spinnerHeight.setValue(mainClass.getSize().getHeight());
        spinnerPositionX.setValue(mainClass.getPositionPoint().getX());
        spinnerPositionY.setValue(mainClass.getPositionPoint().getY());
    }

    @Override
    public void updateShapes() {
        update();
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        updateShape();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        updateShape();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        updateShape();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        updateShape();
    }

    private void updateShape() {
        if (isLoading) {
            return;
        }

        MainClass mainClass = (MainClass)selectShape;

        mainClass.setText(textField.getText());

        int widht = (int) spinnerWidth.getValue();
        int height = (int) spinnerHeight.getValue();

        int x = (int) spinnerPositionX.getValue();
        int y = (int) spinnerPositionY.getValue();

        mainClass.setSize(new Size(widht, height));
        mainClass.setPositionPoint(new Point(x,y));

        board.updateSelectShape(mainClass);
    }
}