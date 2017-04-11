/**
 * Created by sam on 4/6/17.
 */
public class VesselEvent implements Event {
    private Port port;
    private int capacity;
    private int speed;
    private int cost;

    public VesselEvent(Port port, int capacity, int speed, int cost){
        this.port = port;
        this.capacity = capacity;
        this.speed = speed;
        this.cost = cost;
    }

    public void run(){
        //Need to unload and load
        Port destination = ShippingSim.portList[port.getOldestShipmentDest()];
        double distance = Math.sqrt(Math.pow(port.getLocation()[0] - destination.getLocation()[0],2) + Math.pow(port.getLocation()[1] - destination.getLocation()[1],2));
        int daySpeed = speed * 24;
        int elapsedTime = (int)Math.ceil(distance/daySpeed);
    }
}
