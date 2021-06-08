import java.awt.MenuBar;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.xml.bind.annotation.XmlAccessOrder;

/**
 * <p>The controller for the menu</p>
 *
 * @author  Atamanchuk, vladataman44@gmail.com
 * @version 1.0 2021/06/03 Vladyslav Atamanchuk
 */
public class MenuController extends MenuBar {

//Changed both field below to final
    private final Frame parent; //The frame, only used as parent for the Dialogs
    private final Presentation presentation; //Commands are given to the presentation

    private static final long serialVersionUID = 227L;

    protected static final String ABOUT = "About";
    protected static final String FILE = "File";
    protected static final String EXIT = "Exit";
    protected static final String GOTO = "Go to";
    protected static final String HELP = "Help";
    protected static final String NEW = "New";
    protected static final String NEXT = "Next";
    protected static final String OPEN = "Open";
    protected static final String PAGENR = "Page number?";
    protected static final String PREV = "Prev";
    protected static final String SAVE = "Save";
    protected static final String SAVENEW = "Save new";
    protected static final String VIEW = "View";

    protected static final String TESTFILE = "testPresentation.xml";
    protected static String SAVEFILE = "savedPresentation.xml";

    protected static final String CLEAR = "Clear Presentation"; //ADDED


    protected static final String IOEX = "IO Exception: ";
    protected static final String LOADERR = "Load Error";
    protected static final String SAVEERR = "Save Error";

    public MenuController(Frame frame, Presentation pres) {
        this.parent = frame;
        this.presentation = pres;
        MenuItem menuItem;

        Menu fileMenu = new Menu(getFILE());

        fileMenu.add(menuItem = mkMenuItem(getOPEN()));
        menuItem.addActionListener(new ActionListener() { //Used to open testPresentation.xml file and presentation inside of it
            public void actionPerformed(ActionEvent actionEvent) {
                getPresentation().clear();
                try {
                    XMLAccessor.loadFile(getPresentation(), getTESTFILE());
                    getPresentation().setSlideNumber(0);
                } catch (IOException exc) {
                    JOptionPane.showMessageDialog(getParent(), getIOEX() + exc,
                            getLOADERR(), JOptionPane.ERROR_MESSAGE);
                }
                getParent().repaint();
            }
        } );
        fileMenu.add(menuItem = mkMenuItem(getCLEAR()));
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                getPresentation().clear();
                getParent().repaint();
            }
        });
        fileMenu.add(menuItem = mkMenuItem(getSAVE()));
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    XMLAccessor.saveFile(getPresentation(), getSAVEFILE());
                } catch (IOException exc) {
                    JOptionPane.showMessageDialog(getParent(), getIOEX() + exc,
                            getSAVEERR(), JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        fileMenu.addSeparator();
        fileMenu.add(menuItem = mkMenuItem(getEXIT()));
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                getPresentation().exit(0);
            }
        });
        add(fileMenu);
        Menu viewMenu = new Menu(getVIEW());
        viewMenu.add(menuItem = mkMenuItem(getNEXT()));
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                getPresentation().nextSlide();
            }
        });
        viewMenu.add(menuItem = mkMenuItem(getPREV()));
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                getPresentation().prevSlide();
            }
        });
        viewMenu.add(menuItem = mkMenuItem(getGOTO()));
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                String pageNumberStr = JOptionPane.showInputDialog(getPAGENR());
                if(!checkIfInputIsEmpty(pageNumberStr)){
                    try {
                        int pageNumber = Integer.parseInt(pageNumberStr);
                        if(getPresentation().getSlide(pageNumber-1)==null){
                            JOptionPane.showMessageDialog(getParent(), "Index out of presentation number of slides boundary");
                        }else{
                            getPresentation().setSlideNumber(pageNumber - 1);
                        }
                    }catch(NumberFormatException e){
                        JOptionPane.showMessageDialog(getParent(), "Index must be an Integer");;
                    }
                }
            }
        });
        add(viewMenu);
        Menu helpMenu = new Menu(getHELP());
        helpMenu.add(menuItem = mkMenuItem(getABOUT()));
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                DefaultDisplay.showAboutBox(getParent());
            }
        });
        setHelpMenu(helpMenu);
    }

    //Creating a menu-item
    public MenuItem mkMenuItem(String name) {
        return new MenuItem(name, new MenuShortcut(name.charAt(0)));
    }

    /**
     * Checks if input is empty
     * @param input Inputted string by user
     * @return True if input was empty. False if input was not empty
     */
    private boolean checkIfInputIsEmpty(String input) {
        if (input.isEmpty()) {
            JOptionPane.showMessageDialog(getParent(), "Input cannot be left empty");
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Frame getParent() {
        return parent;
    }

    public Presentation getPresentation() {
        return presentation;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static String getABOUT() {
        return ABOUT;
    }

    public static String getFILE() {
        return FILE;
    }

    public static String getEXIT() {
        return EXIT;
    }

    public static String getGOTO() {
        return GOTO;
    }

    public static String getHELP() {
        return HELP;
    }

    public static String getNEW() {
        return NEW;
    }

    public static String getNEXT() {
        return NEXT;
    }

    public static String getOPEN() {
        return OPEN;
    }

    public static String getPAGENR() {
        return PAGENR;
    }

    public static String getPREV() {
        return PREV;
    }

    public static String getSAVE() {
        return SAVE;
    }

    public static String getSAVENEW() {
        return SAVENEW;
    }

    public static String getVIEW() {
        return VIEW;
    }

    public static String getTESTFILE() {
        return TESTFILE;
    }

    public static String getSAVEFILE() {
        return SAVEFILE;
    }

    public static String getCLEAR() {
        return CLEAR;
    }

    public static String getIOEX() {
        return IOEX;
    }

    public static String getLOADERR() {
        return LOADERR;
    }

    public static String getSAVEERR() {
        return SAVEERR;
    }
}
