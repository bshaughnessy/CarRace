import java.util.ArrayList;
import java.util.Random;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JPanel;

import java.io.File;
import java.io.IOException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Point;
import java.util.*;


/**
 * Created by Tom on 2/25/15.
 */
public class Car {
    private int time,
                totalTime,
                carX,
                carY,
                moveSpeed,
                diff,
                currentLocation,
                startLocation;
    private boolean finishedRace;
    private Driver driver;
    private ArrayList<Location> locations;
    private BufferedImage carImage;
    
    // tom
    private boolean moving;
    private Timer timer;

    /**
     * Constructor for objects of type Car.
     */
    public Car(Driver driver, String imgFile, ArrayList<Location> locations) {
      
      
        //access image file
        try {
            carImage = ImageIO.read(new File("./images/yellowCar.png"));
        }
        catch (Exception e) {
            System.out.println("Car image file not found");
        }

        time = 0;
        totalTime = 0;
        carX = 0;
        carY = 0;
        diff = 0;
        currentLocation = 0;
        startLocation = 0; 
        finishedRace = false; 
        this.driver = driver;
        this.locations = locations;
        
        makeEngine();
    }

    /**
     * No arg constructor for objects of type car.
     */
    public Car() {
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
    
        public void startTimer(){
        timer.scheduleAtFixedRate(new TimerTask()
        {
            @Override
            public void run()
            {
                time++;
                System.out.println(time);
            }
        }, new Date(), 1000);
    }

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
      int velocityX = (int) (moveSpeed * (deltaX/path));
      
      // y-axis speed using ratio for sine
      int velocityY = (int) (moveSpeed * (deltaY/path));
      
      // reset x- and y- coordinates
      carX += velocityX; 
      carY += velocityY; 
    }

    /**
     * Gets the distance between the car's current postion and the next location's coordinates.
     */
    public double getDistance() {
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
    public int getNextLocation() {
      return (currentLocation + 1) % locations.size(); 
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(carImage, null, carX, carY);
    }

    public void makeEngine() {
        Random r = new Random();
        moveSpeed = r.nextInt(10) + 10;
    }

    public boolean atLocation() {
      return getDistance() < 20;
    }
    
    public void resetLocation() {
      System.out.println("before current: " + currentLocation);
      System.out.println("before next: " + getNextLocation());
      currentLocation = getNextLocation(); 
      System.out.println("after current: " + currentLocation);
      System.out.println("after next: " + getNextLocation());
      
    }

    /**
     * Getters and Setters
     */
    public int getCarX(){
        return carX;
    }

    public int getCarY(){
        return carY;
    }

    public int getMoveSpeed(){
        return moveSpeed;
    }

    public void setLocation(int x, int y){
        carX = x;
        carY = y;
    }
    
    public Driver getDriver(){
        return driver;
    }

    public int getCurrentTrack()
    {
        return 0;
    }
    
    public void setTotalTime(int i){
        this.totalTime = i;
    }

    public int getTime(){
        return time;
    }

    public void setTime(int time){
        this.time = time;
    }

    public int getCurrentLocation()
    {
        return currentLocation;
    }
    
    public void setStartLocation(int startLocation) {
        this.startLocation = startLocation; 
        setLocation(locations.get(startLocation).getX(), locations.get(startLocation).getY()); 
        currentLocation = startLocation; 
    }
    
    public int getStartLocation() {
      return startLocation; 
    }
    
    public boolean finishedRace() {
      return finishedRace;
    }
    
    public void setFinishedRace(boolean hasFinished) {
      finishedRace = hasFinished; 
    }
}