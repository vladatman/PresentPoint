import java.awt.*;

/**
 * Used to create and return styles
 *  <p>The link between a style number and a item level is hard-linked:
 *  in Slide the style is grabbed for an item
 *  with a style number the same as the item level.</p>
 */
public class Styles {
    private static Style[] styles;

    /**
     *Creates multiple styles
     */
    public static void createStyles() {
        styles = new Style[5];
        // De styles zijn vast ingecodeerd.
        styles[0] = new Style(0, Color.red,   48, 20);	// style voor item-level 0
        styles[1] = new Style(20, Color.blue,  40, 10);	// style voor item-level 1
        styles[2] = new Style(50, Color.black, 36, 10);	// style voor item-level 2
        styles[3] = new Style(70, Color.black, 30, 10);	// style voor item-level 3
        styles[4] = new Style(90, Color.black, 24, 10);	// style voor item-level 4
    }

    /**
     *Used to return style from, styles array
     * @param level Style that will be used
     * @return style from array styls
     */
    public static Style getStyles(int level) {
        if (level >= styles.length) {
            level = styles.length - 1;
        }
        return styles[level];
    }

    public static Style[] getStyles() {
        return styles;
    }

    public static void setStyles(Style[] styles) {
        Styles.styles = styles;
    }
}
