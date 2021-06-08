import java.awt.*;
import java.util.Vector;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;


/**
 * XMLAccessor, reads and writes XML files
 *
 * @author  Atamanchuk, vladataman44@gmail.com
 * @version 1.0 2021/06/03 Vladyslav Atamanchuk
 */
public class XMLAccessor  {

    /**
     * Names of xml tags of attributes
     */
    protected static final String SHOWTITLE = "showtitle";
    protected static final String SLIDETITLE = "title";
    protected static final String SLIDE = "slide";
    protected static final String ITEM = "item";
    protected static final String LEVEL = "level";
    protected static final String KIND = "kind";
    protected static final String TEXT = "text";
    protected static final String IMAGE = "image";

    /**
     * Text of messages
     */
    protected static final String PCE = "Parser Configuration Exception";
    protected static final String UNKNOWNTYPE = "Unknown Element type";
    protected static final String NFE = "Number Format Exception";



    private static String getTitle(Element element, String tagName) {
        NodeList titles = element.getElementsByTagName(tagName);
        return titles.item(0).getTextContent();

    }


    // Removing IOException may break code, so I decided to keep it

    /**
     * Used to load a file with the presentation inside of it
     * @param presentation Presentation to be used
     * @param filename Name of the file
     * @throws IOException If file does not load properly,throws an exception
     */
    public static void loadFile(Presentation presentation, String filename) throws IOException{
        int slideNumber, itemNumber, max, maxItems;
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(new File(filename)); //Create a JDOM document
            Element doc = document.getDocumentElement();
            presentation.setTitle(getTitle(doc, getSHOWTITLE()));

            NodeList slides = doc.getElementsByTagName(getSLIDE());
            max = slides.getLength();
            for (slideNumber = 0; slideNumber < max; slideNumber++) {
                Element xmlSlide = (Element) slides.item(slideNumber);
                Slide slide = new Slide();
                slide.setTitle(getTitle(xmlSlide, getSLIDETITLE()));
                presentation.append(slide);

                NodeList slideItems = xmlSlide.getElementsByTagName(getITEM());
                maxItems = slideItems.getLength();
                for (itemNumber = 0; itemNumber < maxItems; itemNumber++) {
                    Element item = (Element) slideItems.item(itemNumber);
                    loadSlideItem(slide, item);
                }
            }
        }
        catch (IOException iox) {
            System.err.println(iox.toString());
        }
        catch (SAXException sax) {
            System.err.println(sax.getMessage());
        }
        catch (ParserConfigurationException pcx) {
            System.err.println(getPCE());
        }
    }

    /**
     * Loads in the items of the slide.
     * @param slide slide that we want to load in
     * @param item item is either text or an bitMap image
     */
    protected static void loadSlideItem(Slide slide, Element item) {
        int level = 1; // default
        NamedNodeMap attributes = item.getAttributes();
        String levelText = attributes.getNamedItem(getLEVEL()).getTextContent();
        if (levelText != null) {
            try {
                level = Integer.parseInt(levelText);
            } catch (NumberFormatException x) {
                System.err.println(getNFE());
            }
        }
        String type = attributes.getNamedItem(getKIND()).getTextContent();
        if (getTEXT().equals(type)) {
            slide.append(new TextItem(level, item.getTextContent()));
        } else {
            if (getIMAGE().equals(type)) {
                slide.append(new BitmapItem(level, item.getTextContent()));
            } else {
                System.err.println(getUNKNOWNTYPE());
            }
        }
    }

    /**
     * Used to save presentation that is currently opened, under the name savedPresentation.xml.
     * @param presentation
     * @param filename
     * @throws IOException
     */
    public static void saveFile(Presentation presentation, String filename) throws IOException {
            PrintWriter out = new PrintWriter(new FileWriter(filename));
            out.println("<?xml version=\"1.0\"?>");
            out.println("<!DOCTYPE presentation SYSTEM \"presentPoint.dtd\">");
            out.println("<presentation>");
            out.print("<showtitle>");
            out.print(presentation.getTitle());
            out.println("</showtitle>");
            for (int slideNumber = 0; slideNumber < presentation.getSize(); slideNumber++) {
                Slide slide = presentation.getSlide(slideNumber);
                out.println("<slide>");
                out.println("<title>" + slide.getTitle() + "</title>");
                Vector<SlideItem> slideItems = slide.getSlideItems();
                for (int itemNumber = 0; itemNumber < slideItems.size(); itemNumber++) {
                    SlideItem slideItem = slideItems.elementAt(itemNumber);
                    out.print("<item kind=");
                    if (slideItem instanceof TextItem) {
                        out.print("\"text\" level=\"" + slideItem.getLevel() + "\">");
                        out.print(((TextItem) slideItem).getText());
                    } else {
                        if (slideItem instanceof BitmapItem) {
                            out.print("\"image\" level=\"" + slideItem.getLevel() + "\">");
                            out.print(((BitmapItem) slideItem).getImageName());
                        } else {
                            System.out.println("Ignoring " + slideItem);
                        }
                    }
                    out.println("</item>");
                }
                out.println("</slide>");
            }
            out.println("</presentation>");
            out.close();
    }


    public static String getSHOWTITLE() {
        return SHOWTITLE;
    }

    public static String getSLIDETITLE() {
        return SLIDETITLE;
    }

    public static String getSLIDE() {
        return SLIDE;
    }

    public static String getITEM() {
        return ITEM;
    }

    public static String getLEVEL() {
        return LEVEL;
    }

    public static String getKIND() {
        return KIND;
    }

    public static String getTEXT() {
        return TEXT;
    }

    public static String getIMAGE() {
        return IMAGE;
    }

    public static String getPCE() {
        return PCE;
    }

    public static String getUNKNOWNTYPE() {
        return UNKNOWNTYPE;
    }

    public static String getNFE() {
        return NFE;
    }
}
