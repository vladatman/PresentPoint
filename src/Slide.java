import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.Vector;

/** <p>A slide. This class has drawing functionality.</p>
 * @author  Atamanchuk, vladataman44@gmail.com
 * @version 1.0 2021/06/03 Vladyslav Atamanchuk
 */

public class Slide {
	public final static int WIDTH = 1200;
	public final static int HEIGHT = 800;
	protected String title;

	//Changed the name from items to slideItems
	protected Vector<SlideItem> slideItems; //The SlideItems are kept in a vector

	public Slide() {
		slideItems = new Vector<SlideItem>();
	}

	/**
	 * Add a SlideItem
	 * @param anItem
	 */
	public void append(SlideItem anItem) {
		getSlideItems().addElement(anItem);
	}


	/**
	 * Create a TextItem out of a String and add the TextItem
	 * @param level
	 * @param message
	 */
	public void append(int level, String message) {
		append(new TextItem(level, message));
	}

	//Returns the SlideItem
	private SlideItem getSlideItem(int number) {
		return getSlideItems().elementAt(number);
	}	//Method is never used so I changed from public to private

	/**
	 * Returns the size of a slide
	 * @return size of a slide
	 */
	public int getSize() {
		return getSlideItems().size();
	}

	/**
	 * Draws the slide
	 * @param g
	 * @param area
	 * @param view
	 */
	public void draw(Graphics g, Rectangle area, ImageObserver view) {
		float scale = getScale(area);
	    int y = area.y;

	    SlideItem slideItem = new TextItem(0, getTitle());
	    Style style = Styles.getStyles(slideItem.getLevel());
	    slideItem.draw(area.x, y, scale, g, style, view);
	    y += slideItem.getBoundingBox(g, view, scale, style).getHeight();
	    for (int number=0; number<getSize(); number++) {
	      slideItem = getSlideItems().elementAt(number);
	      style = Styles.getStyles(slideItem.getLevel());
	      slideItem.draw(area.x, y, scale, g, style, view);
	      y += slideItem.getBoundingBox(g, view, scale, style).getHeight();
	    }
	  }

	/**
	 * Returns the scale to draw a slide
	 * @param area
	 * @return the scale to draw a slide
	 */
	private float getScale(Rectangle area) {
		return Math.min(((float)area.getWidth()) / ((float)getWIDTH()), ((float)area.getHeight()) / ((float)getHEIGHT()));
	}



	/**
	 * Return the title of a slide
	 * @return title of a slide
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Change the title of a slide
	 * @param newTitle
	 */
	public void setTitle(String newTitle) {
		title = newTitle;
	}

	/**
	 * Return all the SlideItems in a vector
	 * @return all the SlideItems in a vector
	 */
	public Vector<SlideItem> getSlideItems() {
		return slideItems;
	}

	public void setSlideItems(Vector<SlideItem> items) {
		this.slideItems = items;
	}

	public static int getWIDTH() {
		return WIDTH;
	}

	public static int getHEIGHT() {
		return HEIGHT;
	}
}
