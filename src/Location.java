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

    /**
     * Constructor for objects of class Location
     */
    public Location(String name, String imgFile, int x, int y){
        this.name = name;
        this.x = x;
        this.y = y;

        // access image file
        try{
            image = ImageIO.read(new File("../images/" + imgFile));
        }catch(Exception e){
            System.out.println("Image file not found");
        }
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
        g.drawImage(image, null, x, y - 85);
        
        // draw string
        g.setColor(Color.BLACK);
        g.drawString(name, x, y);
    }
}