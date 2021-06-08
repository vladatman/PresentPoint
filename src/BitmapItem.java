import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;

import javax.imageio.ImageIO;

import java.io.IOException;


/** <p>The class for a Bitmap item</p>
 * <p>Bitmap items are responsible for drawing themselves.</p>
 * @author  Atamanchuk, vladataman44@gmail.com
 * @version 1.0 2021/06/03 Vladyslav Atamanchuk
*/

public class BitmapItem extends SlideItem {
  private BufferedImage bufferedImage;
  private final String imageName;
  
  protected static final String FILE = "File ";
  protected static final String NOTFOUND = " not found";


	/**
	 * level indicates the item-level; name indicates the name of the file with the image
	 * @param level
	 * @param name
	 */
	public BitmapItem(int level, String name) {
		super(level);
		imageName = name;
		try {
			bufferedImage = ImageIO.read(new File(imageName));
		}
		catch (IOException e) {
			System.err.println(FILE + imageName + NOTFOUND) ;
		}
	}

	/**
	 * An empty bitmap item
	 * Changed to private because was never used.
	 */
	private BitmapItem() {
		this(0, null);
	}

	//Returns the filename of the image
	//Changed from getName() to getImageName().
	public String getImageName() {
		return imageName;
	}


	/**
	 * Returns the bounding box of the image
	 * @param g
	 * @param observer
	 * @param scale
	 * @param myStyle
	 * @return
	 */
	public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style myStyle) {
		return new Rectangle((int) (myStyle.indent * scale), 0,
				(int) (getBufferedImage().getWidth(observer) * scale),
				((int) (myStyle.leading * scale)) + 
				(int) (getBufferedImage().getHeight(observer) * scale));
	}

	/**
	 * Draws the image
	 * @param x
	 * @param y
	 * @param scale
	 * @param g
	 * @param myStyle
	 * @param observer
	 */
	public void draw(int x, int y, float scale, Graphics g, Style myStyle, ImageObserver observer) {
		int width = x + (int) (myStyle.indent * scale);
		int height = y + (int) (myStyle.leading * scale);
		g.drawImage(getBufferedImage(), width, height,(int) (getBufferedImage().getWidth(observer)*scale),
                (int) (getBufferedImage().getHeight(observer)*scale), observer);
	}

	public String toString() {
		return "BitmapItem[" + getLevel() + "," + getImageName() + "]";
	}

	//Added getter and setter for BufferedImage field
	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}

	public void setBufferedImage(BufferedImage bufferedImage) {
		this.bufferedImage = bufferedImage;
	}

	public static String getFILE() {
		return FILE;
	}

	public static String getNOTFOUND() {
		return NOTFOUND;
	}
}
