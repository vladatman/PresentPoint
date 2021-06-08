import java.io.IOException;

/**
 * PresentPoint Main Program
 *
 * @author  Atamanchuk, vladataman44@gmail.com
 * @version 1.0 2021/06/03 Vladyslav Atamanchuk
 */

public class PresentPoint {
    protected static final String VERSION = "PresentPoint 1.0 - version";

    /**
     * The main program
     */
    public static void main(String[] argv) throws IOException {

        Styles.createStyles();
        Presentation presentation = new Presentation();
        new SlideViewerFrame(getVERSION(), presentation);
        if (argv.length == 0) { //a demo presentation
            DefaultDisplay.getDemoPresentation(presentation);
        } else {
             XMLAccessor.loadFile(presentation, argv[0]);
        }
        presentation.setSlideNumber(0);

    }

    public static String getVERSION() {
        return VERSION;
    }
}
