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

/**
 * Created by Tom on 2/25/15.
 */
public class Car {
    private int time,
                totalTime,
                engineSpeed,
                carX,
                carY,
                moveSpeed;
    private Driver driver;
    private ArrayList<Location> locationList; 
    
    private BufferedImage carImage;
    private Rectangle rect; 

    public Car(Driver driver, String carImageName) {
      
      rect = new Rectangle(50, 50, 25, 25);
        
         // access image file
        try { 
          System.out.println("Try successful");
            //image = ImageIO.read(new File("../images/" + imgFile));
          carImage = ImageIO.read(new File("../images/" + carImageName));
        }
        catch (Exception e) {
            System.out.println("Car image file not found");
        } 
      
        
        time = 0;
        totalTime = 0;
        carX = 0;
        carY = 0;
        this.driver = driver;
        //locationList = list; 
        
        makeEngine();
    }

    public Car() {
      driver = null; 
        time = 0;
        totalTime = 0;
        //tracks = new Track[4];
        this.driver = driver;
        carX = 0;
        carY = 0;

        makeEngine();
    }

    public void makeEngine(){
        Random r = new Random();
        this.setEngineSpeed(r.nextInt(50) + 200);
        moveSpeed = engineSpeed/10;

    }

    public void moveCar(){
        carX += moveSpeed;
        carY += moveSpeed;
    }

    public void setLocation(int x, int y){
     rect.setLocation(x, y);
    }

    public void printLocation(){
        System.out.println(driver.getName() + " x: " + this.carX + "y: " + this.carY);
    }
    
    public Driver getDriver(){
        return driver;
    }

    //public void setTracks(Track[] t){
        //this.tracks = t;
    //}

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
    
    public void move()
    {
        Point p = rect.getLocation();
        int t = getCurrentTrack();
        //0 represents top left checkpoint
        switch (t)  {
            case 0: while(p.x < 740)
                    p.x += 4;
            case 1: while(p.y < 550)
                    p.y += 4;
            case 2: while(p.x > 50)
                    p.x -= 4;
            case 3: while(p.y > 50)
                    p.y -= 4;
        }
    }
     public void draw(Graphics2D g2d){

       
      Point p2 = rect.getLocation();
      //don't really know if there is a better way to do this
      AffineTransform a = new AffineTransform();
      //and this
      BufferedImageOp op = new AffineTransformOp(a, AffineTransformOp.TYPE_BILINEAR);
      g2d.drawImage(carImage, op, (int)p2.getX(), (int)p2.getY());
     }
        
}