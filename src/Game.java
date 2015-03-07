import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import java.awt.*;

/**
 * Created by Tom on 2/25/2015.
 */
public class Game extends JPanel implements ActionListener
{
    private Car[] cars;
    private Venue venue;
    private Timer t;

    private JButton moveButton,
                    nextLocButton;

    public Game(int xSize, int ySize) {
        
       setSize(xSize, ySize);
      
        // create venue
        createVenue(); 

        //create 4 cars
        cars = new Car[4];
        
        Driver driver = new Driver("");
        
//        car = new Car(driver, "yellowCar.png", venue);
//        car.setLocation(venue.getLocations().get(0).getX(), venue.getLocations().get(0).getY());

        cars[0] = new Car(driver, "yellowCar.png", venue);
        cars[1] = new Car(driver, "yellowCar.png", venue);
        cars[2] = new Car(driver, "yellowCar.png", venue);
        cars[3] = new Car(driver, "yellowCar.png", venue);

        t = new Timer(5, null);

        setCarStart();

        moveButton = new JButton("Move");
        nextLocButton = new JButton("Next Location");

        moveButton.addActionListener(this);
        nextLocButton.addActionListener(this);

        this.add(moveButton);
        this.add(nextLocButton);

    }

    public void setCarStart(){
        for(int i = 0; i < 4; i++){
            cars[i].setLocation(venue.getLocations().get(i).getX(), venue.getLocations().get(i).getY());
            cars[i].setCurrentLocation(i);
        }
    }

    public void setNextLoc(){
        for(Car c : cars){
            c.setCurrentLocation((c.getCurrentLocation() + 1) % 4);
        }
    }
    
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
      venue.addLocation("Location 3", "locationPlaceholder.png", width - 50, height - 50);
      venue.addLocation("Location 4", "locationPlaceholder.png", 10, height - 50);
      
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
        for(Car c : cars){
            c.draw(artist);
        }
        //t.start();
        
    }

    public void actionPerformed(ActionEvent e){
        JButton b = (JButton) e.getSource();

        if(b.equals(moveButton)){
            for(Car c : cars){
                c.move();
                System.out.println(c.carDetails());
                repaint();
            }
            System.out.println("==============================");

        }

        if(b.equals(nextLocButton)){

            setNextLoc();
            for(Car c : cars){
                c.makeEngine();
                c.getCurrentLocation();
            }
        }
    }
}