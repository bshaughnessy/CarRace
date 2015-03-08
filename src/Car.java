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
                engineSpeed,
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
    private Thread thread;

/**
 * Constructor for objects of type Car. 
 */
    public Car(Driver driver, String imgFile, ArrayList<Location> locations) {
      
      
        //access image file
        try {
            carImage = ImageIO.read(new File("../images/" + imgFile));
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

//    public void move(){
//        if(this.getCarX() != venue.getLocations().get(currentLocation).getX()){
//            if(this.getCarX() < venue.getLocations().get(currentLocation).getX())
//            {
//                this.setLocation(this.getCarX() + moveSpeed, getCarY());
//            }
//            else{
//                this.setLocation(this.getCarX() - moveSpeed, getCarY());
//            }
//        }
//        if(this.getCarY() != venue.getLocations().get(currentLocation).getY()){
//            if(this.getCarY() < venue.getLocations().get(currentLocation).getY())
//            {
//                this.setLocation(this.getCarX(), this.getCarY() + moveSpeed);
//            }
//            else{
//                this.setLocation(this.getCarX(), this.getCarY() - moveSpeed);
//            }
//        }
//    }

    /*public void move(){
        int clX = locations.get((currentLocation + 1) % 4).getX();
        int clY = locations.get((currentLocation + 1) % 4).getY();
        switch(currentLocation){
            case 0: diff = clX - carX;
                if (diff > moveSpeed) {
                    carX += moveSpeed;
                    diff -= moveSpeed;
                }
                else if (diff <= moveSpeed && diff > 0) {
                    carX += diff;
                    //diff = 0;
                }
                break;
            case 1: diff = clY - carY;
                if(diff > moveSpeed){
                    carY += moveSpeed;
                    diff -= moveSpeed;
                }
                else if (diff <= moveSpeed && diff > 0) {
                    carY += diff;
                    //diff = 0;
                }
                break;
            case 2: diff = carX - clX;
                if (diff > moveSpeed) {
                    carX -= moveSpeed;
                    diff -= moveSpeed;
                }
                else if (diff <= moveSpeed && diff > 0) {
                    carX -= diff;
                    //diff = 0;
                }
                break;
            case 3: diff = carY - clY;
                if (diff > moveSpeed) {
                    carY -= moveSpeed;
                    diff -= moveSpeed;
                }
                else if (diff <= moveSpeed && diff > 0) {
                    carY -= diff;
                    //diff = 0;
                }
                break;
        }
    }*/
    
/**
 * Moves the car to the next location in their route. 
 */
    public void move() {
      // delta x
      //int deltaX = locations.get(getNextLocation()).getX() - locations.get(currentLocation).getX(); 
      
      // delta y
      //int deltaY = locations.get(getNextLocation()).getY() - locations.get(currentLocation).getY(); 
      
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
        this.setEngineSpeed(r.nextInt(10) + 10);
        moveSpeed = engineSpeed;
    }
    
/**
 * 
 */
    public boolean atLocation() {
      System.out.println("distance: " + getDistance());
      return getDistance() < 20;
    }
    
    public void resetLocation() {
      currentLocation = getNextLocation(); 
    }

    public String carDetails(){
        return "x: " + carX + ", y: " + carY + ", diff: " + diff + ", speed: " + moveSpeed;
    }

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

    public void printLocation(){
        System.out.println(driver.getName() + " x: " + this.carX + "y: " + this.carY);
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

    public void setEngineSpeed(int x){
        this.engineSpeed = x;
    }

    public int getCurrentLocation()
    {
        return currentLocation;
    }

    public void setCurrentLocation(int currentLocation)
    {
        this.currentLocation = currentLocation;
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