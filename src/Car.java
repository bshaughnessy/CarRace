import java.util.ArrayList;
import java.util.Random;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics2D;
import java.util.*;


/**
 * Created by Tom on 2/25/15.
 */
public class Car{
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
            carImage = ImageIO.read(new File("./images/yellowCar.png"));
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
     * No arg constructor for objects of type car.
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
     * starts the timer for the car
     * changes time to 0 to reset the increment
     */
    public void startTimer(){
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
    public void move(){
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
     * Gets the distance between the car's current postion and the next location's coordinates.
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
     * Gets the next Location that the car will travel to.
     */
    public int getNextLocation(){
        return (currentLocation + 1) % locations.size();
    }

    public void draw(Graphics2D g2d){
        g2d.drawImage(carImage, null, carX, carY);
    }

    /**
     * sets the speed of the car
     */
    public void makeEngine(){
        Random r = new Random();
        moveSpeed = r.nextInt(10) + 10;
    }

    /**
     * returns true if the car is at its next destination
     */
    public boolean atLocation(){
        return getDistance() < 20;
    }

    /**
     * changes to the next destination of the car
     * and alters the car speed
     */
    public void resetLocation(){
        currentLocation = getNextLocation();
        makeEngine();
    }

    /**
     * adds to the cars total time
     */
    public void addTime(int i){
        this.totalTime += i;
    }

    /**
     * Getters and Setters
     */
    public void setLocation(int x, int y){
        carX = x;
        carY = y;
    }

    public Driver getDriver(){
        return driver;
    }

    public int getTotalTime(){
        return totalTime;
    }

    public int getTime(){
        return time;
    }

    public int getCurrentLocation(){
        return currentLocation;
    }

    public void setStartLocation(int startLocation){
        this.startLocation = startLocation;
        setLocation(locations.get(startLocation).getX(), locations.get(startLocation).getY());
        currentLocation = startLocation;
    }

    public int getStartLocation(){
        return startLocation;
    }

    public boolean finishedRace(){
        return finishedRace;
    }

    public void setFinishedRace(boolean hasFinished){
        finishedRace = hasFinished;
    }

    public boolean getTimerStarted(){
        return timerStarted;
    }

    public void setTimerStarted(boolean b){
        timerStarted = b;
    }
}