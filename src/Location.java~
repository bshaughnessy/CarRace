import java.awt.*;
/**
 * Write a description of class Location here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Location
{
    private String name; 
    private int x;
    private int y; 

    /**
     * Constructor for objects of class Location
     */
    public Location(String name, int x, int y) {
        this.name = name; 
        this.x = x;
        this.y = y; 
    }
    
    /**
     * Draws the Location object.
     * 
     * @param  g    the Graphics2D object
     */
    public void draw(Graphics2D g)
    {
        // rectangle
        g.setColor(Color.BLACK);
        g.drawRect(x, y, 100, 100); 
        g.fillRect(x, y, 100, 100);
        
        // string
        g.setColor(Color.WHITE);
        g.drawString(name, x + 5, y + 50);
    }
}