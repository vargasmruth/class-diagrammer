package UI.ToolBar;

import Domain.Board.Board;
import Domain.Shape.Classes.AbstractClass;
import Domain.Shape.Classes.InterfaceClass;
import Domain.Shape.Classes.NormalClass;
import Domain.Shape.Connectors.Association;
import Domain.Shape.Connectors.Composition;
import Domain.Shape.Connectors.DirectAssociation;
import Domain.Shape.Connectors.Inherit;
import Persistence.File;
import UI.MenuBar.MenuBar;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ToolBar extends JMenuBar {

    JMenu menu;
    JMenuItem menuItem;
    Board board;
    MenuBar menuBar;

    private String lastPath = ".";

    public ToolBar(Board board, MenuBar menuBar) {
        this.board = board;
        this.menuBar = menuBar;
        LoadMenu();
    }

    private void LoadMenu() {

        addMenuFile();
        addMenuEdit();
        addMenuOption();
        addMenuHelp();

    }

    public void addMenuHelp() {
        menu = new JMenu("Help");
        add(menu);
        addMenuItem(menu, e -> menuBar.openAbout(), "About", 'H', true);
    }

    private void addMenuOption() {
        menu = new JMenu("Option");
        add(menu);

        addMenuItem(menu, e -> menuBar.Select(NormalClass.class), "Normal Class", KeyEvent.VK_N, false);
        addMenuItem(menu, e -> menuBar.Select(InterfaceClass.class), "Interface Class", KeyEvent.VK_I, false);
        addMenuItem(menu, e -> menuBar.Select(AbstractClass.class), "Abstract Class", KeyEvent.VK_A, false);
        addMenuItem(menu, e -> menuBar.Select(Association.class), "Association Connector", KeyEvent.VK_S, false);
        addMenuItem(menu, e -> menuBar.Select(Composition.class), "Composition Connector", KeyEvent.VK_C, false);
        addMenuItem(menu, e -> menuBar.Select(DirectAssociation.class), "Direct Association Connector", KeyEvent
                .VK_D, false);
        addMenuItem(menu, e -> menuBar.Select(Inherit.class), "Inherit Connector", KeyEvent.VK_H, false);
    }

    private void addMenuEdit() {
        menu = new JMenu("Edit");
        add(menu);

        addMenuItem(menu, e -> board.undo(), "Undo", 'U', true);
        addMenuItem(menu, e -> board.redo(), "Redo", 'R', true);
    }

    private void addMenuFile() {
        menu = new JMenu("File");
        add(menu);

        addMenuItem(menu, e -> board.clean(), "New", 'N', true);
        addMenuItem(menu, e -> OpenFile(), "Open", 'O', true);
        addMenuItem(menu, e -> SaveFile(), "Save", 'S', true);
        addMenuItem(menu, e -> SaveAsFile(), "SaveAs", 'G', true);

        menu.add(new JSeparator());

        addMenuItem(menu, e -> System.exit(0), "Exit", 'E', true);
    }

    private void addMenuItem(JMenu menu, ToolBarListener listener, String name, int key, boolean type) {
        try {
            menuItem = new JMenuItem(name);
            if (type) {
                menuItem.setAccelerator(KeyStroke.getKeyStroke(key, Toolkit.getDefaultToolkit()
                        .getMenuShortcutKeyMask()));
            } else {
                menuItem.setAccelerator(KeyStroke.getKeyStroke(key, ActionEvent.ALT_MASK));
            }
            menu.add(menuItem);
            menuItem.addActionListener(listener::exec);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void SaveAsFile() {
        try {
            String filename = ChooseFile("Save As");
            if (filename != "") {
                board.setRepository(new File(filename));
                board.saveData();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void SaveFile() {
        try {
            if (board.getRepository() != null) {
                board.saveData();
            } else {
                String filename = ChooseFile("Save");
                if (filename != "") {
                    board.setRepository(new File(filename));
                    board.saveData();
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    private void OpenFile() {
        try {
            String filename = ChooseFile("Open");
            if (filename != "") {
                board.setRepository(new File(filename));
                board.loadData();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }


    private String ChooseFile(String save) {
        JFileChooser chooser = new JFileChooser(lastPath);
        chooser.setFileFilter(new FileNameExtensionFilter("data file", "data"));
        int retval = chooser.showDialog(null, save);
        if (retval == JFileChooser.APPROVE_OPTION) {
            java.io.File theFile = chooser.getSelectedFile();
            if (theFile != null) {
                if (!theFile.isDirectory()) {
                    String filename = chooser.getSelectedFile().getAbsolutePath();
                    if (!filename.endsWith(".data"))
                        filename += ".data";
                    lastPath = chooser.getSelectedFile().getPath();
                    return filename;
                }
            }
        }
        return "";
    }


}