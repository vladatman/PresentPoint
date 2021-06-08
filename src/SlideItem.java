import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

/** <p>The abstract class for items on a slide.<p>
 * <p>All SlideItems have drawing capabilities.</p>
 * @author Vladyslav Atamanchuk, vladataman44@gmail.com
 * @version 1.0 2021/06/03 Vladyslav Atamanchuk
*/

public abstract class SlideItem {
	private int level = 0; //The level of the SlideItem

	public SlideItem(int lev) {
		level = lev;
	}

	protected SlideItem() {
		this(0);
	}

	/**
	 * Returns the level
	 * @return
	 */
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * Returns the bounding box
	 * @param g
	 * @param observer
	 * @param scale
	 * @param style
	 * @return
	 */
	public abstract Rectangle getBoundingBox(Graphics g, 
			ImageObserver observer, float scale, Style style);

	/**
	 * Draws the item
	 * @param x
	 * @param y
	 * @param scale
	 * @param g
	 * @param style
	 * @param observer
	 */
	public abstract void draw(int x, int y, float scale, 
			Graphics g, Style style, ImageObserver observer);
}
