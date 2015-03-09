import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

/**
 * Write a description of class Location here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Location{
    private String name;
    private BufferedImage image;
    private int x;
    private int y;
    private boolean textOnTop;

    /**
     * Constructor for objects of class Location
     */
    public Location(String name, String imgFile, int x, int y){
        this.name = name;
        this.x = x;
        this.y = y;
        textOnTop = false;

        // access image file
        try{
            image = ImageIO.read(new File("./images/locationPlaceholder.png"));
        }catch(Exception e){
            System.out.println("Image file not found");
        }
    }

    /**
     * Sets if the location string is written above or below the image.
     *
     * @param newLocation true if the text should be on top, otherwise false
     */
    public void setTextTop(boolean newLocation){
        textOnTop = newLocation;
    }

    /**
     * Returns the x-coordinate of the Location.
     *
     * @return x-coordinate
     */
    public int getX(){
        return x;
    }

    /**
     * Returns the y-coordinate of the Location.
     *
     * @param y-coordinate
     */
    public int getY(){
        return y;
    }

    /**
     * Draws the Location object.
     *
     * @param g the Graphics2D object
     */
    public void draw(Graphics2D g){
        // draw image
        g.drawImage(image, null, x, y);

        g.setColor(Color.BLACK);

        // draw string
        if(textOnTop){
            g.drawString(name, x, y - 5);
        }else{
            g.drawString(name, x, y + 60);
        }
    }
}