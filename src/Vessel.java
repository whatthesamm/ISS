/**
 * Created by sam on 4/6/17.
 */
public class Vessel {

    private int capacity;
    private int speed;
    private int cost;
    private Port destination;

    public Vessel(int capacity, int speed, int cost){
        this.capacity = capacity;
        this.speed = speed;
        this.cost = cost;
    }

    public boolean removeCargo(){
        return false;
    }

    //Need to add loading method, make sure it loads the queue with the oldest shipment
}
