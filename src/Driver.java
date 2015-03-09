/**
 * Created by Tom on 2/25/15.
 */
/**
 * The Driver class... 
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Driver {
    String name;

    /**
     * No arg constructor for objects of type Driver.
     */
    public Driver(){
        name = " ";
    }

    /**
     * Constructor for objects of type Driver.
     */
    public Driver(String name){
        this.name = name;
    }

    /**
     * Returns the name of the driver.
     * 
     * @return  name of driver
     */
    public String getName(){
        return name;
    }

    public void setName(String s){
        this.name = s;
    }
}