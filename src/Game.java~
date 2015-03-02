import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Tom on 2/25/2015.
 */
public class Game {
    private Car[] cars;
    private Track[] tracks;

    public Game(){
        cars = new Car[4];
        tracks = new Track[4];
        tracks[0] = new Track(10, 10);
        tracks[1] = new Track(50, 10);
        tracks[2] = new Track(50, 50);
        tracks[3] = new Track(10, 50);

        for(int i = 0; i < 4; i++){
            cars[i] = new Car(new Driver("Driver" + i), tracks);
        }
        for(Car c : cars){
            c.setTracks(tracks);
        }

        setCarStart(tracks, cars);
    }

    public void tallyTime(Car[] c){
        for(Car car : c){
            int x = car.getTime();
            car.setTotalTime(x);
        }
    }

    //there is definitely a better way to do this
    public void setCarStart(Track[] t, Car[] c){
        ArrayList<Integer> carStart = new ArrayList<Integer>();
        for(int i = 0; i < 4; i++){
            carStart.add(i);
        }
        for(int i = 0; i < 4; i++){
            c[i].setLocation(t[i].getxLocation(), t[i].getyLocation());
        }
    }

    public Car[] getCars(){
        return cars;
    }

    public Track[] getTracks(){
        return tracks;
    }


}
