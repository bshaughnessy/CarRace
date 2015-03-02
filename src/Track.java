/**
 * Created by Tom on 2/25/15.
 *
 * These should each have images attached to them.
 */
public class Track
{
    private int xLocation,
                yLocation;

    //no-arg constructor
    public Track(){
        xLocation = 0;
        yLocation = 0;
    }

    //USE ME!
    public Track(int xLocation, int yLocation){
        this.xLocation = xLocation;
        this.yLocation = yLocation;
    }

    //getters and setters1
    public int getxLocation(){
        return xLocation;
    }

    public void setxLocation(int xLocation){
        this.xLocation = xLocation;
    }

    public int getyLocation(){
        return yLocation;
    }

    public void setyLocation(int yLocation){
        this.yLocation = yLocation;
    }
}
