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

    private boolean movePressed, racePressed, restartPressed;

    private JButton moveButton, raceButton, restartButton;

    private Timer timer;

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
        restartPressed = false;

        //TODO: this should be set by the main class and the user input, and resize all the images
        cars[0] = new Car(new Driver("A"), "./images/yellowCar.png", venue.getLocations());
        cars[1] = new Car(new Driver("B"), "./images/yellowCar.png", venue.getLocations());
        cars[2] = new Car(new Driver("C"), "./images/yellowCar.png", venue.getLocations());
        cars[3] = new Car(new Driver("D"), "./images/yellowCar.png", venue.getLocations());

        timer = new Timer(250, this);
        timer.setActionCommand("timer");
        timer.start();

        setCarStart();

        moveButton = new JButton("Move");
        raceButton = new JButton("Full Race");
        restartButton = new JButton("Restart");

        moveButton.addActionListener(this);
        raceButton.addActionListener(this);
        restartButton.addActionListener(this);

        this.setLayout(new BorderLayout());

        JPanel bottomPanel = new JPanel(new GridLayout(1, 3));
        this.add(bottomPanel, BorderLayout.SOUTH);

        bottomPanel.add(moveButton);
        bottomPanel.add(raceButton);
        bottomPanel.add(restartButton);
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
        venue.addLocation("Cotton Candy Forest", "candyTree.png", 10, height/2 + 75);
        venue.addLocation("Chocolate Lava Cave", "chocolateCave.png", width/2, 100);
        venue.addLocation("Marshmallow Stonehenge", "marshmallowRocks.png", width - 75, height/2 + 75);
        venue.addLocation("Ice Cream Cone Castle", "coneCastle.png", width/2, height);
    }

    /**
     * Sets the players name.
     *
     * @param name player's name
     */
    public void setPlayerName(String name){
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
                            if(raceFinished()){
                                racePressed = false;
                                restartButton.setEnabled(true);
                                this.revalidate();
                                System.out.println("test");
                            }
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
    public void moveOneLeg(){
        for(Car c : cars){

            // if car timer started
            if(c.getTimerStarted()){

                // if car not at location, move to next location
                if(!c.atLocation()){
                    c.move();
                    c.setMoving(true);
                }else{
                    // otherwise reset timer and reset location
                    c.addTime(c.getTime());
                    c.setMoving(false);
                    c.stopTimer();
                    c.setTimerStarted(false);
                    c.resetLocation();
                    // if back at starting position
                    if(c.getCurrentLocation() == c.getStartLocation()){
                        //System.out.println("back at start");
                        c.setFinishedRace(true);
                    }
                    if(allAtNextLoc()){
                        movePressed = false;
                        moveButton.setEnabled(true);
                    }
                }
            }
        }
        // if race finished
        if(raceFinished()){
            movePressed = false;
            moveButton.setEnabled(false);
            restartButton.setEnabled(true);
            this.revalidate();
        }
    }

    /**
     * Returns true if all cars at their next locations. Otherwise returns false. 
     *
     * @return  true if cars at next locations, otherwise false
     */
    public boolean allAtNextLoc(){
        int i = 0;
        for(Car c : cars){
            if(!c.getMoving()){
                i++;
            }
        }
        return i == cars.length;
    }

    /**
     * Returns the name of the winner and the winner's time.
     *
     * @return winner's name and time
     */
    public String checkWinner(){
        int win = 1000;
        String winner = "";
        for(Car c : cars){
            if(c.getTotalTime() <= win){
                win = c.getTotalTime();
                winner = c.getDriver().getName();
            }
        }
        return "Winner is " + winner + ", in " + win + " seconds! If you would like to play again press Restart.";
    }

    /**
     * Returns true if all cars have finsihed the race. Otherwise return false.
     *
     * @return true if all cars finished race, otherwise false
     */
    public boolean raceFinished(){
        int doneCars = 0;
        for(Car c : cars){
            if(c.finishedRace() == true){
                doneCars++;
            }
        }
        return doneCars == cars.length;
    }

    /**
     * Returns an array of all cars.
     *
     * @return array of cars
     */
    public Car[] getCars(){
        return cars;
    }

    public boolean getRestartPressed(){
        return restartPressed;
    }

    public void setRestartPressed(boolean b){
        this.restartPressed = b;
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
            raceButton.setEnabled(false);
            moveButton.setEnabled(false);
            restartButton.setEnabled(false);
            for(Car c : cars){
                c.startTimer();
            }
        }

        if(e.getActionCommand().equals("Full Race")){
            movePressed = false;
            moveButton.setEnabled(false);
            restartButton.setEnabled(false);
            raceButton.setEnabled(false);
            racePressed = true;
        }

        if(e.getActionCommand().equals("Restart")){
            restartPressed = true;
            timer.stop();
        }
    }
}