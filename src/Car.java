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
                carY;
    private Driver driver;
    private Track[] tracks;


    public Car(){
        time = 0;
        totalTime = 0;
        tracks = new Track[4];
        driver = new Driver("test");
        carX = 0;
        carY = 0;

        makeEngine();
    }

    public void makeEngine(){
        Random r = new Random();
        this.setEngineSpeed(r.nextInt(100) + 100);
    }

    public void setLocation(int x, int y){
        this.carX = x;
        this.carY = y;
    }

    public void addTotalTime(int n){
        totalTime += time;
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
