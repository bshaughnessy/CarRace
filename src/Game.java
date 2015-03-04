import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import java.awt.*;

/**
 * Created by Tom on 2/25/2015.
 */
public class Game extends JPanel {
    private Car[] cars;
    private Car car; 
    private Venue venue; 

    public Game(int xSize, int ySize) {
        
       setSize(xSize, ySize);
      
        // create venue
        createVenue(); 
        
        //cars = new Car[4];
        
        Driver driver = new Driver("");
        
        /*cars[0] =*/ 
        
        car = new Car(driver, "yellowCar.png");
        car.setLocation(50, 50);
        
        //car = new Car();
        
        

        /*for(int i = 0; i < 4; i++){
            cars[i] = new Car(new Driver("Driver" + i), venue);
        }
        for(Car c : cars){
            c.setTracks(tracks);
        }*/

        //setCarStart(tracks, cars);
    }

    public void tallyTime(Car[] c){
        for(Car car : c){
            int x = car.getTime();
            car.setTotalTime(x);
        }
    }

    //there is definitely a better way to do this
    /*public void setCarStart(Track[] t, Car[] c){
        ArrayList<Integer> carStart = new ArrayList<Integer>();
        for(int i = 0; i < 4; i++){
            carStart.add(i);
        }
        for(int i = 0; i < 4; i++){
            c[i].setLocation(t[i].getxLocation(), t[i].getyLocation());
        }
    }*/
    
  /**
   * Creates the venue and its locations. 
   */
    public void createVenue() {
      venue = new Venue(); 
      
      int width = getWidth();
      int height = getHeight(); 
      
      // add locations
      // these coordinates, names and images should change-- just as placeholders
      venue.addLocation("Location 1", "locationPlaceholder.png", 10, 10); 
      venue.addLocation("Location 2", "locationPlaceholder.png", width - 50, 10); 
      venue.addLocation("Location 3", "locationPlaceholder.png", 10, height - 50); 
      venue.addLocation("Location 4", "locationPlaceholder.png", width - 50, height - 50); 
      
      // write text in correct spot according to coordinates
      for (Location location : venue.getLocations()) {
        if (location.getY() > height/2) {
          location.setTextTop(true); 
        }
      }
    }
    
    public Car[] getCars(){
        return cars;
    }

    
    
    /**
     * Draws all components of the game: venue and cars.   
     * 
     * @param  g    Graphics object
     */
    @Override
    public void paintComponent(Graphics g)
    {    
        super.paintComponent(g);
        Graphics2D artist = (Graphics2D) g;
        
        // draw venue
        venue.draw(artist);
        
        //cars[0].draw(artist); 
        car.draw(artist); 
        
        //Car car = new Car(null, "yellowCar.png");
        
        /*/for () {
        }*/
        
        // draws cars here
        
    }
}