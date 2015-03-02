/**
 * Created by Tom on 3/1/15.
 */
public class Main
{
    public static void main(String[] args){
        Game game = new Game();

        for(Car c : game.getCars()){
            c.printLocation();
        }
    }
}
