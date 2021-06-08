import java.awt.Color;
import java.awt.Font;

/** <p>Style stands for Indent, Color, Font and Leading.</p>
 * @author  Atamanchuk, vladataman44@gmail.com
 * @version 1.0 2021/06/03 Vladyslav Atamanchuk
 */
public class Style {

	private static final String FONTNAME = "Helvetica";
	public int indent;
	public Color color;
	private final Font font;
	private final int fontSize;
	public  int leading;


	public Style(int indent, Color color, int points, int leading) {
		this.indent = indent;
		this.color = color;
		font = new Font(FONTNAME, Font.BOLD, fontSize=points);
		this.leading = leading;
	}

	public String toString() {
		return "["+ getIndent()+ "," + getColor() + "; " + getFontSize() + " on " + getLeading() +"]";
	}

	public Font getFont(float scale) {
		return font.deriveFont(getFontSize() * scale);
	}

	public int getIndent() {
		return indent;
	}

	public void setIndent(int indent) {
		this.indent = indent;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getLeading() {
		return leading;
	}

	public void setLeading(int leading) {
		this.leading = leading;
	}

	public static String getFONTNAME() {
		return FONTNAME;
	}

	public int getFontSize() {
		return fontSize;
	}
}
