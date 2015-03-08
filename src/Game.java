import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

/**
 * Created by Tom on 2/25/2015.
 */
public class Game extends JPanel implements ActionListener
{
    private Car[] cars;
    private Venue venue;

    private JButton moveButton,
                    nextLocButton,
                    raceButton;
    
    private boolean movePressed, 
                    nextPressed,
                    racePressed;
    
    private Timer timer;

    public Game(int xSize, int ySize) {
        
       setSize(xSize, ySize);
      
        // create venue
        createVenue(); 

        //create 4 cars
        cars = new Car[4];

        //TODO: this needs to adjusted and done in the main with the user input
        Driver driver = new Driver("Driver Name");
        
        movePressed = false;
        nextPressed = false; 
        racePressed = false; 

        //TODO: this should be set by the main class and the user input
        cars[0] = new Car(driver, "./images/yellowCar.png", venue.getLocations());
        cars[1] = new Car(driver, "yellowCar.png", venue.getLocations());
        cars[2] = new Car(driver, "yellowCar.png", venue.getLocations());
        cars[3] = new Car(driver, "yellowCar.png", venue.getLocations());
        
        timer = new Timer(250, this);
        timer.setActionCommand("timer");
        timer.start();

        setCarStart();

        moveButton = new JButton("Move");
        nextLocButton = new JButton("Next Location");
        raceButton = new JButton("Full Race");

        moveButton.addActionListener(this);
        nextLocButton.addActionListener(this);
        raceButton.addActionListener(this); 

        this.add(moveButton);
        this.add(nextLocButton);
        this.add(raceButton);

    }

    /**
     * Sets every car to a different starting position.
     */
    public void setCarStart(){
        for(int i = 0; i < cars.length; i++) {
            cars[i].setStartLocation(i);
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
      venue.addLocation("Location 1", "./images/locationPlaceholder.png", 10, 200);
      venue.addLocation("Location 2", "./images/locationPlaceholder.png", width - 50, 10);
      venue.addLocation("Location 3", "./images/locationPlaceholder.png", width - 50, height - 50);
      venue.addLocation("Location 4", "./images/locationPlaceholder.png", 10, height - 50);
      
      // write text in correct spot according to coordinates
      for (Location location : venue.getLocations()) {
        if (location.getY() > height/2) {
          location.setTextTop(true); 
        }
      }
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
        for (Car c : cars) {
            c.draw(artist);
        }
    }
    
    /**
     * Causes all cars to move around the track until they have all reached their starting postions again.
     */
    public void race() {
      int carsDone = 0;

      // while cars still racing
      if (carsDone < cars.length) {
         
          // for every car
          for (Car c : cars) {
            
            // if haven't finished race
            if (c.finishedRace() == false)
            {
              // move towards next
              if (!c.atLocation()) {
                c.move();
              }
              // otherwise reset next location
              else {
                c.resetLocation(); 
                
                // back at starting location
                if (c.getCurrentLocation() == c.getStartLocation()) {
                  c.setFinishedRace(true);
                  carsDone++; 
                }
              }
            }
            repaint(); 
          }
       }
    }

    /**
     * Moves the car to its next location then stops upon arrival
     */
    public void moveOneLeg() {
        for (Car c : cars)
        {
            // if haven't gotten to next location yet
            if (!c.atLocation())
            {
                c.move();
            }
        }
    }

    /**
     * Sets the next locations for the cars
     */
    public void setNextLocations() {
      for (Car c : cars) {
        c.resetLocation(); 
      }
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());

        if(e.getActionCommand().equals("timer") && movePressed) {
            repaint();
            moveOneLeg();
        }
        
        if(e.getActionCommand().equals("timer") && nextPressed) {
            setNextLocations();
            nextPressed = false;
        }
        
        if(e.getActionCommand().equals("timer") && racePressed) {
            race(); 
        }

        if(e.getActionCommand().equals("Move")) {
            movePressed = true;
        }
        
        if(e.getActionCommand().equals("Next Location")) {
            nextPressed = true;
        }
        
        if (e.getActionCommand().equals("Full Race")) {
            racePressed = true; 
        }
    }

    /**
     * Getters and Setters
     */
    public Car[] getCars(){
        return cars;
    }
}