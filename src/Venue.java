import java.util.ArrayList;
import java.awt.*;

/**
 * Write a description of class Venue here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Venue{
    private ArrayList<Location> locations;

    /**
     * Constructor for objects of class Venue
     */
    public Venue(){
        locations = new ArrayList<Location>();
    }

    /**
     * Add a location to the venue.
     *
     * @param name the name of the location
     * @param x    the x-coordinate of the location
     * @param y    the y-coordinate of the location
     */
    public void addLocation(String name, String imgFile, int x, int y){
        locations.add(new Location(name, imgFile, x, y));
    }

    /**
     * Returns the arraylist of locations.
     *
     * @return arraylist of locations
     */
    public ArrayList<Location> getLocations(){
        return locations;
    }

    /**
     * Draws the Venue object.
     *
     * @param g the Graphics2D object
     */
    public void draw(Graphics2D g){
        for(Location location : locations){
            location.draw(g);
        }
    }
}