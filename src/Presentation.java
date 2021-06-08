import java.util.ArrayList;


/**
 * <p>Presentations keeps track of the slides in a presentation.</p>
 * <p>Only one instance of this class is available.</p>
 *
 * @author  Atamanchuk, vladataman44@gmail.com
 * @version 1.0 2021/06/03 Vladyslav Atamanchuk
 */

public class Presentation {
    private String showTitle; //The title of the presentation

    private ArrayList<Slide> slidesList = null; //An ArrayList with slides
    private int currentSlideNumber = 0; //The number of the current slide
    private SlideViewerComponent slideViewComponent = null; //The view component of the slides

    public Presentation() {
        slideViewComponent = null;
        clear();
    }

    public Presentation(SlideViewerComponent slideViewerComponent) {
        this.slideViewComponent = slideViewerComponent;
        clear();
    }


    /**
     * Navigate to the previous slide unless we are at the first slide
     */
    public void prevSlide() {
        if (getCurrentSlideNumber() > 0) {
            setSlideNumber(getCurrentSlideNumber() - 1);
        }
    }

    /**
     * Navigate to the next slide unless we are at the last slide
     */
    public void nextSlide() {
        if (getCurrentSlideNumber() < (getSlidesList().size() - 1)) {
            setSlideNumber(getCurrentSlideNumber() + 1);
        }
    }

    /**
     * Remove the presentation
     */
    void clear() {
        slidesList = new ArrayList<>();
        setSlideNumber(-1);
    }

    /**
     * Add a slide to the presentation
     * @param slide slide to be added
     */
    public void append(Slide slide) {
        getSlidesList().add(slide);
    }


    /**
     * Return a slide with a specific number
     * @param number slide number
     * @return slide from slideList
     */
    public Slide getSlide(int number) {
        if (number < 0 || number >= getSize()) {
            return null;
        }
        return getSlidesList().get(number);
    }

    /**
     * Return the current slide
     * @return current slide
     */
    public Slide getCurrentSlide() {
        return getSlide(getCurrentSlideNumber());
    }

    /**
     * Change the current slide number and report it the the window
     * @param number slide number
     */
    public void setSlideNumber(int number) {
        currentSlideNumber = number;
        if (getSlideViewComponent() != null) {
            getSlideViewComponent().update(this, getCurrentSlide());
        }
    }

    /**
     * Exit application
     * @param n
     */
    public void exit(int n) {
        System.exit(n);
    }


    public SlideViewerComponent getSlideViewComponent() {
        return slideViewComponent;
    }

    //Changed name from setShowView to setSlideViewComponent
    public void setSlideViewComponent(SlideViewerComponent slideViewComponent) {
        this.slideViewComponent = slideViewComponent;
    }

    /**
     * Get size of the arrayList inside presentation
     * @return showList size
     */
    public int getSize() {
        return slidesList.size();
    }

    public String getTitle() {
        return showTitle;
    }

    public void setTitle(String nt) {
        showTitle = nt;
    }


    /**
     * Returns the number of the current slide
     * @return
     */
    public int getCurrentSlideNumber() {    //Changed from getSlideNumber to getCurrentSlideNumber
        return currentSlideNumber;
    }

    public void setSlidesList(ArrayList<Slide> showList) {
        this.slidesList = showList;
    }

    public ArrayList<Slide> getSlidesList() {
        return slidesList;
    }
}
