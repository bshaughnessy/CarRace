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

    private int racesFinished;

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
        cars[1] = new Car(new Driver("B"), "yellowCar.png", venue.getLocations());
        cars[2] = new Car(new Driver("C"), "yellowCar.png", venue.getLocations());
        cars[3] = new Car(new Driver("D"), "yellowCar.png", venue.getLocations());

        Timer timer = new Timer(250, this);
        timer.setActionCommand("timer");
        timer.start();

        setCarStart();

        JButton moveButton = new JButton("Move");
        JButton raceButton = new JButton("Full Race");

        moveButton.addActionListener(this);
        raceButton.addActionListener(this);

        this.setLayout(new BorderLayout());

        JPanel bottomPanel = new JPanel(new GridLayout(1, 3));
        this.add(bottomPanel, BorderLayout.SOUTH);

        bottomPanel.add(moveButton);
        bottomPanel.add(raceButton);

        racesFinished = 0;
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
     * Moves the car to its next location then stops upon arrival
     */
    public void moveOneLeg(){
        for(Car c : cars){
            // if haven't gotten to next location yet
            if(!c.atLocation() && c.getTimerStarted()){
                c.move();
            }
            if(c.atLocation() && c.getTimerStarted()){
                c.addTime(c.getTime());
                c.stopTimer();
                c.setTimerStarted(false);
            }
        }
        if(isAllDone()){
            movePressed = false;
            //for testing TODO: DELETE
            setNextLocations();
            System.out.println(checkWinner());
            racesFinished++;
        }
    }

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
     * checks if all the cars are done
     */
    public boolean isAllDone(){
        int i = 0;
        for(Car c : cars){
            if(c.atLocation()){
                i++;
            }
        }
        return i == 4;
    }

    /**
     * Sets the next locations for the cars
     */
    public void setNextLocations(){
        for(Car c : cars){
            c.resetLocation();
        }
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
            for(Car c : cars){
                c.startTimer();
            }
        }

        if(e.getActionCommand().equals("Full Race")){
            racePressed = true;
        }
    }

    public Car[] getCars(){
        return cars;
    }

    public int getRacesFinished(){
        return racesFinished;
    }

    public Venue getVenue(){
        return venue;
    }
}