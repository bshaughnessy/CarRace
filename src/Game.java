import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Tom on 2/25/2015.
 */
public class Game {

    public static void main(String[] args){
        Window w = new Window();

        Car[] cars = new Car[4];
        Track[] tracks = new Track[4];

        for(int i = 0; i < 4; i++){
            cars[i] = new Car();
            tracks[i] = new Track();
        }

        for(Car c : cars){
            c.setTracks(tracks);
        }
    }

    public void tallyTime(Car[] c){
        for(Car car : c){
            int x = car.getTime();
            car.setTotalTime(x);
        }
    }

    public void setCarStart(Track[] t, Car[] c){
        ArrayList<Integer> carStart = new ArrayList<Integer>();
        for(int i = 0; i < 4; i++){
            carStart.add(i);
        }
        for(int i = 0; i < 4; i++){
            c[i].setLocation(t[i].getxLocation(), t[i].getyLocation());
        }
    }


}
