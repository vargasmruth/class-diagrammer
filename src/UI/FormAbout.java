package UI;

import javax.swing.*;
import javax.swing.border.Border;

/**
 * Created on 8/4/2018.
 */
public class FormAbout extends JFrame {

    private static int width = 300;
    private static int height = 200;

    private JTextArea texto;
    private Border border;

    private javax.swing.JLabel content;

    public FormAbout() {
        super("About ...");
        texto = new JTextArea();
        border = BorderFactory.createBevelBorder(10);
     }

    public void showFrameAbout() {
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public void addContent() {
        texto.setEditable(false);
        texto.setText("Class Diagrammer es una herramienta que nos \n " +
                      "permite dibujar un diagrama de clases UML. \n \n"
                        + " Autores: \n"
                        + " - Fernando Luna Molina \n"
                        + " - Brenda López \n"
                        + " - María Ruth Vargas  \n \n"
                        + " SC - 09.04.2018  \n");

        texto.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(12, 12, 12, 12)));

        this.add(texto);
    }
}