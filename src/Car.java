import java.util.ArrayList;
import java.util.Random;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics2D;
import java.util.*;

/**
 * The Car class...
 *
 * @author (your name)
 * @version (a version number or a date)
 */
/**
 * Created by Tom on 2/25/15.
 */
public class Car {
    private int time, totalTime, carX, carY, moveSpeed, currentLocation, startLocation;
    private boolean finishedRace, timerStarted;
    private Driver driver;
    private ArrayList<Location> locations;
    private BufferedImage carImage;
    private Timer timer;

    /**
     * Constructor for objects of type Car.
     */
    public Car(Driver driver, String imgFile, ArrayList<Location> locations){


        //access image file
        try{
            carImage = ImageIO.read(new File("../images/yellowCar.png"));
        }catch(Exception e){
            System.out.println("Car image file not found");
        }

        time = 0;
        totalTime = 0;
        carX = 0;
        carY = 0;
        moveSpeed = 0;
        currentLocation = 0;
        startLocation = 0;
        finishedRace = false;
        timerStarted = false;
        this.driver = driver;
        this.locations = locations;

        makeEngine();
    }

    /**
     * No arg constructor for objects of type Car.
     */
    public Car(){
        driver = null;
        time = 0;
        totalTime = 0;
        this.driver = null;
        carX = 0;
        carY = 0;
        locations = null;
        currentLocation = 0;
        startLocation = 0;
    }

    /**
     * Starts the timer for the car and changes time to 0 to reset the increment. 
     */
    public void startTimer() {
        timer = new Timer();
        time = 0;
        timerStarted = true;
        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                time++;
            }
        }, new Date(), 1000);
    }

    /**
     * stops the timer
     */
    public void stopTimer(){
        timer.cancel();
    }

    /**
     * Moves the car to the next location in their route.
     */
    public void move() {
        // delta x
        int deltaX = locations.get(getNextLocation()).getX() - carX;

        // delta y
        int deltaY = locations.get(getNextLocation()).getY() - carY;

        // get the distance between the current and next location
        double path = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));

        // x-axis speed using ratio for cosine
        int velocityX = (int) (moveSpeed * (deltaX / path));

        // y-axis speed using ratio for sine
        int velocityY = (int) (moveSpeed * (deltaY / path));

        // reset x- and y- coordinates
        carX += velocityX;
        carY += velocityY;
    }

    /**
     * Returns the distance between the car's current postion and the next location's coordinates.
     * 
     * @return  distance between current and next position
     */
    public double getDistance(){
        // delta x
        int deltaX = locations.get(getNextLocation()).getX() - carX;

        // delta y
        int deltaY = locations.get(getNextLocation()).getY() - carY;

        // get the distance between the 2 points
        double distance = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));

        return distance;
    }

    /**
     * Returns the index of the next location in list of locations.
     * 
     * @return  index of next location
     */
    public int getNextLocation(){
        return (currentLocation + 1) % locations.size();
    }

    /**
     * Draws the car. 
     * 
     * @param  g2d    graphics 2d object
     */
    public void draw(Graphics2D g2d){
        g2d.drawImage(carImage, null, carX, carY);
    }

    /**
     * Sets the speed of the car.
     */
    public void makeEngine(){
        Random r = new Random();
        moveSpeed = r.nextInt(10) + 10;
    }

    /**
     * Returns true if the car is within 20 pixels of its next location. 
     */
    public boolean atLocation(){
        return getDistance() < 20;
    }

    /**
     * Changes the current location to the next destination of the car
     * and alters the car speed. 
     */
    public void resetLocation(){
        currentLocation = getNextLocation();
        makeEngine();
    }

    /**
     * Adds to the cars total time. 
     */
    public void addTime(int i){
        this.totalTime += i;
    }

    /**
     * Getters and Setters
     */
    
    /**
     * Sets the location of the car.
     * 
     * @param  x    x-coordinate
     * @param  y    y-coordinate
     */
    public void setLocation(int x, int y){
        carX = x;
        carY = y;
    }

    /**
     * Returns the driver of the car.
     * 
     * @return  the driver object
     */
    public Driver getDriver(){
        return driver;
    }

    /**
     * Returns the total time that the car has traveled during the race. 
     */
    public int getTotalTime(){
        return totalTime;
    }

    /**
     * What is difference between this and total time?
     */
    public int getTime(){
        return time;
    }

    /**
     * Returns the index of the current location in the list.
     * 
     * @return  current location index 
     */
    public int getCurrentLocation(){
        return currentLocation;
    }

    /**
     * Sets the start position of the car. 
     */
    public void setStartLocation(int startLocation){
        this.startLocation = startLocation;
        setLocation(locations.get(startLocation).getX(), locations.get(startLocation).getY());
        currentLocation = startLocation;
    }

    /**
     * Returns the index of the start position of the car. 
     * 
     * @return  start position index
     */
    public int getStartLocation() {
        return startLocation;
    }

    /**
     * Returns true if the car has finished the race, otherwise returns false.
     * 
     * @return  true if finished race, otherwise false
     */
    public boolean finishedRace() {
        return finishedRace;
    }

    /**
     * Sets if the car has finished the race or not. 
     * 
     * @param  hasFinished    whether or not the car has finished the race
     */
    public void setFinishedRace(boolean hasFinished) {
        finishedRace = hasFinished;
    }

    /**
     * Returns true if the timer has started, otherwise returns false.
     * 
     * @return  returns true if timer started, otherwise false
     */
    public boolean getTimerStarted() {
        return timerStarted;
    }

    /**
     * Sets whether or not the timer has started.
     * 
     * @param  b    whether or not timer started
     */
    public void setTimerStarted(boolean b) {
        timerStarted = b;
    }
}