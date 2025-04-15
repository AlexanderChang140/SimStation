package mvc;

import tools.Command;
import tools.CommandProcessor;
import tools.Utilities;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.util.*;
import javax.swing.*;

public class AppPanel extends JPanel implements PropertyChangeListener, ActionListener {
    private static final long serialVersionUID = 1L;
    protected Model model;
    protected AppFactory factory;
    private final View view;
    private final JFrame frame;
    protected final JPanel panel;
    public static int FRAME_WIDTH = 600;
    public static int FRAME_HEIGHT = 350;

    public AppPanel(AppFactory factory) {
        super();
        this.factory = factory;
        model = factory.makeModel();
        view = factory.getView(model);
        if (model != null) model.addPropertyChangeListener(this);

        this.setLayout(new GridLayout(1, 2));

        panel = new JPanel();
        this.add(panel, "West");

        this.add(view, "East");

        frame = new JFrame();
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(createMenuBar());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle(factory.getTitle());
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    public void display() {
        frame.setVisible(true);
    }


    public void propertyChange(PropertyChangeEvent evt) {
        /* override in extensions if needed */
    }

    public Model getModel() {
        return model;
    }

    // called by file/open and file/new
    public void setModel(Model newModel) {
        this.model.removePropertyChangeListener(this);
        this.model = newModel;
        this.model.initSupport();
        this.model.addPropertyChangeListener(this);
        view.setModel(this.model);
        //alternatively: this.model.copy(model);
    }

    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        // add file, edit, and help menus
        JMenu fileMenu =
                Utilities.makeMenu("File", new String[]{"New", "Save", "Save As", "Open", "Quit"}, this);
        result.add(fileMenu);

        JMenu editMenu =
                Utilities.makeMenu("Edit", factory.getEditCommands(), this);
        result.add(editMenu);

        JMenu helpMenu =
                Utilities.makeMenu("Help", new String[]{"About", "Help"}, this);
        result.add(helpMenu);

        return result;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmmd = e.getActionCommand();
        try {
            switch (cmmd) {
                case "Save" -> Utilities.save(model, false);
                case "Save As" -> Utilities.save(model, true);
                case "Open" -> {
                    Model newModel = Utilities.open(model);
                    if (newModel != null) {
                        setModel(newModel);
                    }
                }
                case "New" -> {
                    Utilities.saveChanges(model);
                    setModel(factory.makeModel());
                    model.setUnsavedChanges(false);
                }
                case "Quit" -> {
                    Utilities.saveChanges(model);
                    System.exit(0);
                }

                case "About" -> Utilities.inform(factory.about());
                case "Help" -> Utilities.inform(factory.getHelp());

                default -> {
                    Command command = factory.makeEditCommand(model, cmmd);
                    CommandProcessor.execute(command);
                }
            }

        } catch (Exception ex) {
            Utilities.error(ex);
        }
    }
}