import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

/**
 * Created by Tom on 2/25/2015.
 */
/**
 * The Game class... 
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Game extends JPanel implements ActionListener{
    private Car[] cars;
    private Venue venue;

    private boolean movePressed,
                    racePressed;

    private JButton moveButton,
                    raceButton;

    public Game(int xSize, int ySize){

        setSize(xSize, ySize);

        // create venue
        createVenue();

        //create 4 cars
        cars = new Car[4];

        //TODO: this needs to adjusted and done in the main with the user input
        //Driver driver = new Driver("Driver Name");

        movePressed = false;
        racePressed = false;

        //TODO: this should be set by the main class and the user input, and resize all the images
        cars[0] = new Car(new Driver("A"), "./images/yellowCar.png", venue.getLocations());
        cars[1] = new Car(new Driver("B"), "./images/yellowCar.png", venue.getLocations());
        cars[2] = new Car(new Driver("C"), "./images/yellowCar.png", venue.getLocations());
        cars[3] = new Car(new Driver("D"), "./images/yellowCar.png", venue.getLocations());

        Timer timer = new Timer(250, this);
        timer.setActionCommand("timer");
        timer.start();

        setCarStart();

        moveButton = new JButton("Move");
        raceButton = new JButton("Full Race");

        moveButton.addActionListener(this);
        raceButton.addActionListener(this);

        this.setLayout(new BorderLayout());

        JPanel bottomPanel = new JPanel(new GridLayout(1, 3));
        this.add(bottomPanel, BorderLayout.SOUTH);

        bottomPanel.add(moveButton);
        bottomPanel.add(raceButton);
    }

    /**
     * Sets every car to a different starting position.
     */
    public void setCarStart(){
        for(int i = 0; i < cars.length; i++){
            cars[i].setStartLocation(i);
        }
    }

    /**
     * Creates the venue and its locations.
     */
    public void createVenue(){
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
        for(Location location : venue.getLocations()){
            if(location.getY() > height / 2){
                location.setTextTop(true);
            }
        }
    }
    
    /**
     * Sets the players name. 
     *
     * @param  name    player's name
     */
    public void setPlayerName(String name) {
      cars[0].getDriver().setName(name);
    }

    /**
     * Draws all components of the game: venue and cars.
     *
     * @param g Graphics object
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D artist = (Graphics2D) g;

        // draw venue
        venue.draw(artist);

        //cars[0].draw(artist); 
        for(Car c : cars){
            c.draw(artist);
        }
    }

    /**
     * Causes all cars to move around the track until they have all reached their starting postions again.
     */
    public void race(){
        int carsDone = 0;

        // while cars still racing
        if(carsDone < cars.length){
            // for every car
            for(Car c : cars){
                if(!c.getTimerStarted()){
                    c.startTimer();
                }
                // if haven't finished race
                if(!c.finishedRace()){
                    // move towards next
                    if(!c.atLocation()){
                        c.move();
                    }
                    // otherwise reset next location
                    else{
                        c.resetLocation();

                        // back at starting location
                        if(c.getCurrentLocation() == c.getStartLocation()){
                            c.setFinishedRace(true);
                            carsDone++;
                            c.stopTimer();
                            c.addTime(c.getTime());
                        }
                    }
                }
                repaint();
            }
        }
    }
    
    /**
     * Moves the car to its next location then stops upon arrival.
     * Upon arrival, resets next location. 
     */
    public void moveOneLeg() {
              for(Car c : cars) {
                
                // if car timer started
                if (c.getTimerStarted()) {
                  
                  // if car not at location, move to next location
                  if (!c.atLocation()) {
                    c.move(); 
                  } else {
                    // otherwise reset timer and reset location
                    c.addTime(c.getTime());
                    c.stopTimer();
                    c.setTimerStarted(false);
                    c.resetLocation(); 
                    
                    // if back at starting position
                    if (c.getCurrentLocation() == c.getStartLocation()) {
                      //System.out.println("back at start"); 
                      c.setFinishedRace(true);
                        if(raceFinished()){
                            disableButtons(true);
                        }
                    }
                  }
                }
              }
              
              // if race finsihed
              if(raceFinished()){
                movePressed = false;
                  disableButtons(true);
              }
    }
    
    /**
     * Returns the name of the winner and the winner's time. 
     * 
     * @return  winner's name and time
     */
    public String checkWinner(){
        int win = 1000;
        String winner = "";
        for(Car c : cars){
            if (c.getTotalTime() <= win){
                win = c.getTotalTime();
                winner = c.getDriver().getName();
            }
        }
        return "Winner is " + winner + " ,in " + win + " seconds";
    }

    /**
     * sets the buttons to enabled or disabled
     */
    public void disableButtons(boolean b){
        moveButton.setEnabled(b);
        raceButton.setEnabled(b);
    }
    
    /**
     * Returns true if all cars have finsihed the race. Otherwise return false.
     * 
     * @return  true if all cars finished race, otherwise false
     */
    public boolean raceFinished() {
      int doneCars = 0; 
      for (Car c : cars) {
        if (c.finishedRace() == true) {
          doneCars++; 
        }
      }
      return doneCars == cars.length; 
    }
    
    /**
     * Returns an array of all cars. 
     * 
     * @return  array of cars
     */
    public Car[] getCars(){
        return cars;
    }

    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals("timer") && movePressed){
            repaint();
            moveOneLeg();
        }

        if(e.getActionCommand().equals("timer") && racePressed){
            race();
        }

        if(e.getActionCommand().equals("Move")){
            movePressed = true;
            disableButtons(false);
            for(Car c : cars){
                c.startTimer();
            }
        }

        if(e.getActionCommand().equals("Full Race")){
            movePressed = false; 
            racePressed = true;
            disableButtons(false);
        }
    }
}