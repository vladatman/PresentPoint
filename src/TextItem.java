import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.font.TextLayout;
import java.awt.font.TextAttribute;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.text.AttributedString;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

/** <p>A text item.</p>
 * <p>A text item has drawing capabilities.</p>
 * @author  Atamanchuk, vladataman44@gmail.com
 * @version 1.0 2021/06/03 Vladyslav Atamanchuk
 */

public class TextItem extends SlideItem {

	private String text;


	private static final String EMPTYTEXT = "No Text Given";

	/**
	 * A textItem of int level with text string
	 * @param level
	 * @param string
	 */
	public TextItem(int level, String string) {
		super(level);
		text = string;
	}


	/**
	 * An empty textItem
	 * Changed to private, because method was never used
	 */
	private TextItem() {
		this(0, EMPTYTEXT);
	}

	/**
	 * Returns the AttributedString for the Item
	 * @param style
	 * @param scale
	 * @return
	 */
	public AttributedString getAttributedString(Style style, float scale) {
		AttributedString attrStr = new AttributedString(getText());
		//changed text.length() to getText().length
		attrStr.addAttribute(TextAttribute.FONT, style.getFont(scale), 0, getText().length());
		return attrStr;
	}

	/**
	 * Returns the bounding box of an Item
	 * @param g
	 * @param observer
	 * @param scale
	 * @param myStyle
	 * @return
	 */
	public Rectangle getBoundingBox(Graphics g, ImageObserver observer, 
			float scale, Style myStyle) {
		List<TextLayout> layouts = getLayouts(g, myStyle, scale);
		int xsize = 0, ysize = (int) (myStyle.leading * scale);
		Iterator<TextLayout> iterator = layouts.iterator();
		while (iterator.hasNext()) {
			TextLayout layout = iterator.next();
			Rectangle2D bounds = layout.getBounds();
			if (bounds.getWidth() > xsize) {
				xsize = (int) bounds.getWidth();
			}
			if (bounds.getHeight() > 0) {
				ysize += bounds.getHeight();
			}
			ysize += layout.getLeading() + layout.getDescent();
		}
		return new Rectangle((int) (myStyle.indent*scale), 0, xsize, ysize );
	}

	/**
	 * Draws the item
	 * @param x
	 * @param y
	 * @param scale
	 * @param g
	 * @param myStyle
	 * @param o
	 */
	public void draw(int x, int y, float scale, Graphics g, 
			Style myStyle, ImageObserver o) {
		if (text == null || text.length() == 0) {
			return;
		}
		List<TextLayout> layouts = getLayouts(g, myStyle, scale);
		Point pen = new Point(x + (int)(myStyle.indent * scale), 
				y + (int) (myStyle.leading * scale));
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(myStyle.color);
		Iterator<TextLayout> it = layouts.iterator();
		while (it.hasNext()) {
			TextLayout layout = it.next();
			pen.y += layout.getAscent();
			layout.draw(g2d, pen.x, pen.y);
			pen.y += layout.getDescent();
		}
	  }

	/**
	 * Returns List of layouts
	 * @param g
	 * @param s
	 * @param scale
	 * @return
	 */
	private List<TextLayout> getLayouts(Graphics g, Style s, float scale) {
		List<TextLayout> layouts = new ArrayList<TextLayout>();
		AttributedString attrStr = getAttributedString(s, scale);
    	Graphics2D g2d = (Graphics2D) g;
    	FontRenderContext frc = g2d.getFontRenderContext();
    	LineBreakMeasurer measurer = new LineBreakMeasurer(attrStr.getIterator(), frc);
    	float wrappingWidth = (Slide.WIDTH - s.indent) * scale;
    	while (measurer.getPosition() < getText().length()) {
    		TextLayout layout = measurer.nextLayout(wrappingWidth);
    		layouts.add(layout);
    	}
    	return layouts;
	}

	public String toString() {
		return "TextItem[" + getLevel()+","+getText()+"]";
	}

	//Added setter for text field(Self Encapsulate Field)
	public void setText(String text) {
		this.text = text;
	}

	//Returns the text
	public String getText() {
		return text == null ? "" : text;
	}

	//Returns Empty text
	public static String getEMPTYTEXT() {
		return EMPTYTEXT;
	}
}
