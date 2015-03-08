import java.util.*;
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

/**
 * Created by Tom on 2/25/15.
 */
public class Car{
    //vars
    private int time,
                totalTime,
                engineSpeed,
                carX,
                carY,
                moveSpeed,
                diff,
                currentLocation;
    private Driver driver;
    private Venue venue;
    private BufferedImage carImage;
    private boolean moving;
    private Timer timer;
    private Thread thread;

    public Car(Driver driver, String imgFile, Venue venue) {
        //access image file
        try {
            carImage = ImageIO.read(new File("./images/" + imgFile));
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
        moving = false;
        this.driver = driver;
        this.venue = venue;
        timer = new Timer();
        
        makeEngine();
    }

    //no arg constructor
    public Car() {
        driver = null;
        time = 0;
        totalTime = 0;
        this.driver = null;
        carX = 0;
        carY = 0;
        venue = null;
        currentLocation = 0;
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

    public void move(){
        moving = true;
        int clX = venue.getLocations().get((currentLocation + 1) % 4).getX();
        int clY = venue.getLocations().get((currentLocation + 1) % 4).getY();
        switch(currentLocation){
            case 0: diff = clX - carX;
                if(diff > moveSpeed){
                    carX += moveSpeed;
                    diff -= moveSpeed;
                }
                else if(diff <= moveSpeed && diff > 0){
                    carX += diff;
                    moving = false;
                }
                break;
            case 1: diff = clY - carY;
                if(diff > moveSpeed){
                    carY += moveSpeed;
                    diff -= moveSpeed;
                }
                else if(diff <= moveSpeed && diff > 0){
                    carY += diff;
                    moving = false;
                }
                break;
            case 2: diff = carX - clX;
                if(diff > moveSpeed){
                    carX -= moveSpeed;
                    diff -= moveSpeed;
                }
                else if(diff <= moveSpeed && diff > 0){
                    carX -= diff;
                    moving = false;
                }
                break;
            case 3: diff = carY - clY;
                if(diff > moveSpeed){
                    carY -= moveSpeed;
                    diff -= moveSpeed;
                }
                else if(diff <= moveSpeed && diff > 0){
                    carY -= diff;
                    moving = false;
                }
                break;
        }
    }

    public void draw(Graphics2D g2d){

        g2d.drawImage(carImage, null, getCarX(), getCarY());
    }

    public void makeEngine(){
        Random r = new Random();
        this.setEngineSpeed(r.nextInt(10) + 10);
        moveSpeed = engineSpeed;

    }

    public String carDetails(){
        return "x: " + carX + ", diff: " + diff + ", speed: " + moveSpeed;
    }

    public boolean getMoving(){
        return this.moving;
    }

    public void setMoving(boolean b){
        this.moving = b;
    }

    public int getCarX(){
        return carX;
    }

    public int getCarY(){
        return carY;
    }

    public void setLocation(int x, int y){
        this.carX = x;
        this.carY = y;
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


}