import java.util.ArrayList;
import java.util.Random;

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
    private Track[] tracks;

    public Car(){
        time = 0;
        totalTime = 0;
        engineSpeed = 0;
        carX = 0;
        carY = 0;
        moveSpeed = 0;
        driver = new Driver("");
        tracks = null;
    }

    public Car(Driver driver){
        time = 0;
        totalTime = 0;
        tracks = new Track[4];
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
        this.carX = x;
        this.carY = y;
    }

    public Driver getDriver(){
        return driver;
    }

    public void setTracks(Track[] t){
        this.tracks = t;
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
}
