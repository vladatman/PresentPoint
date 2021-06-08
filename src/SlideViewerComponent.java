import java.awt.*;
import javax.swing.*;


/** <p>SlideViewerComponent is a graphical component that ca display Slides.</p>
 * @author  Atamanchuk, vladataman44@gmail.com
 * @version 1.0 2021/06/03 Vladyslav Atamanchuk
 */

public class SlideViewerComponent extends JComponent {
		
	private Slide slide; //The current slide
	private Font labelFont = null; //The font for labels
	private Presentation presentation = null; //The presentation
	private JFrame frame = null;
	
	private static final long serialVersionUID = 227L;

	//Changed from Black color name to White color name
	private static final Color WHITECOLOR = Color.white;
	//Changed from color name to Black color name
	private static final Color BLACKCOLOR = Color.black;
	private static final String FONTNAME = "Dialog";
	private static final int FONTSTYLE = Font.BOLD;
	private static final int FONTHEIGHT = 10;
	private static final int XPOS = 1100;
	private static final int YPOS = 20;

	public SlideViewerComponent(Presentation pres, JFrame frame) {
		setBackground(WHITECOLOR);
		presentation = pres;
		labelFont = new Font(FONTNAME, FONTSTYLE, FONTHEIGHT);
		this.frame = frame;
	}

	public Dimension getPreferredSize() {
		return new Dimension(Slide.getWIDTH(), Slide.getHEIGHT());
	}

	public void update(Presentation presentation, Slide data) {
		if (data == null) {
			repaint();
			return;
		}
		this.presentation = presentation;
		this.slide = data;
		repaint();
		getFrame().setTitle(presentation.getTitle());
	}

	/**
	 * Draw the slide
	 * @param g
	 */
	public void paintComponent(Graphics g) {
		g.setColor(getWHITECOLOR());
		g.fillRect(0, 0, getSize().width, getSize().height);
		if (getPresentation().getCurrentSlideNumber() < 0 || getSlide() == null) {
			return;
		}
		g.setFont(getLabelFont());
		g.setColor(getBLACKCOLOR());
		g.drawString("Slide " + (1 + getPresentation().getCurrentSlideNumber()) + " of " +
                 getPresentation().getSize(), XPOS, YPOS);
		Rectangle area = new Rectangle(0, YPOS, getWidth(), (getHeight() - YPOS));
		getSlide().draw(g, area, this);
	}

	public Slide getSlide() {
		return slide;
	}

	public void setSlide(Slide slide) {
		this.slide = slide;
	}

	public Font getLabelFont() {
		return labelFont;
	}

	public void setLabelFont(Font labelFont) {
		this.labelFont = labelFont;
	}

	public Presentation getPresentation() {
		return presentation;
	}

	public void setPresentation(Presentation presentation) {
		this.presentation = presentation;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public static Color getWHITECOLOR() {
		return WHITECOLOR;
	}

	public static Color getBLACKCOLOR() {
		return BLACKCOLOR;
	}

	public static String getFONTNAME() {
		return FONTNAME;
	}

	public static int getFONTSTYLE() {
		return FONTSTYLE;
	}

	public static int getFONTHEIGHT() {
		return FONTHEIGHT;
	}

	public static int getXPOS() {
		return XPOS;
	}

	public static int getYPOS() {
		return YPOS;
	}
}
