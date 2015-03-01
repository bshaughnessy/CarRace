import java.util.Random;

/**
 * Created by Tom on 2/25/15.
 */
public class Car {
    private int time, engineSpeed;

    public Car(){
        time = 0;

    }

    public void makeEngine(){
        Random r = new Random();
        this.setEngineSpeed(r.nextInt(100) + 100);
    }

    public void tallyTime(){

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
