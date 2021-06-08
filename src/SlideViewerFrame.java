import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.JFrame;

/**
 * <p>The applicatiewindow for a slideviewcomponent</p>
 * @author  Atamanchuk, vladataman44@gmail.com
 * @version 1.0 2021/06/03 Vladyslav Atamanchuk
*/

public class SlideViewerFrame extends JFrame {
	private static final long serialVersionUID = 3227L;

	private static final String TITLE = "PresentPoint 1.0";
	public final static int WIDTH = 1200;
	public final static int HEIGHT = 800;

	public SlideViewerFrame(String title, Presentation presentation) {
		super(title);
		SlideViewerComponent slideViewerComponent = new SlideViewerComponent(presentation, this);
		presentation.setSlideViewComponent(slideViewerComponent);
		setupWindow(slideViewerComponent, presentation);
	}

	/**
	 * Setup the GUI
	 * @param slideViewerComponent
	 * @param presentation
	 */
	public void setupWindow(SlideViewerComponent
			slideViewerComponent, Presentation presentation) {
		setTitle(getTITLE());
		addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
		getContentPane().add(slideViewerComponent);
		addKeyListener(new KeyController(presentation)); //Add a controller
		setMenuBar(new MenuController(this, presentation));	//Add another controller
		setSize(new Dimension(getWIDTH(), getHEIGHT())); //Same sizes a slide has
		setVisible(true);
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public static String getTITLE() {
		return TITLE;
	}

	public static int getWIDTH() {
		return WIDTH;
	}

	public static int getHEIGHT() {
		return HEIGHT;
	}
}
