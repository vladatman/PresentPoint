import javax.swing.*;
import java.awt.*;

/**
 * Contains methods that are already predefined and user is not allowed to modify.
 * Those two Subclasses had methods that performed similar work so it made sense to move them into one class.(Pull Up Method)
 */
public class DefaultDisplay {
    /**
     * Get demoPresentation
     * @param presentation Initialized presentation
     */
    public static void getDemoPresentation(Presentation presentation){
        presentation.setTitle("Demo Presentation");
        Slide slide;
        slide = new Slide();
        slide.setTitle("PresentPoint");
        slide.append(1, "The Java prestentation tool");
        slide.append(2, "Copyright (c) 2021: Vladyslav Atamanchuk");
        slide.append(4, "Calling PresentPoint without a filename");
        slide.append(4, "will show this presentation");
        slide.append(1, "Navigate:");
        slide.append(3, "Next slide: PgDn or Enter");
        slide.append(3, "Previous slide: PgUp or up-arrow");
        slide.append(3, "Quit: q or Q");
        presentation.append(slide);

        slide = new Slide();
        slide.setTitle("Demonstration of levels and styles");
        slide.append(1, "Level 1");
        slide.append(2, "Level 2");
        slide.append(1, "Again level 1");
        slide.append(1, "Level 1 has style number 1");
        slide.append(2, "Level 2 has style number 2");
        slide.append(3, "This is how level 3 looks like");
        slide.append(4, "And this is level 4");
        presentation.append(slide);

        slide = new Slide();
        slide.setTitle("The third slide");
        slide.append(1, "To open a new presentation,");
        slide.append(2, "use File->Open from the menu.");
        slide.append(1, " ");
        slide.append(1, "This is the end of the presentation.");
        slide.append(new BitmapItem(1, "PresentPoint.png"));
        presentation.append(slide);
    }
    /**
     * Display about box, that contains dialogMessage about PresentPoint program
     * @param parent Frame used to display message box
     */
    public static void showAboutBox(Frame parent) {
        JOptionPane.showMessageDialog(parent,
                "PresentPoint is a primitive slide-show program in Java(tm).\n" +
                        "Copyright (c) 2021 by Vladyslav Atamanchuk, vladataman44@gmail.com.\n",
                "About PresentPoint",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
