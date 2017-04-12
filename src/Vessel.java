//Written by maten009 and nguy2886
public class Vessel {
    private String name;
    private int capacity, speed, cost;
    private Port destination;

    public Vessel(String name, int capacity, int speed, int cost) {
        this.name = name;
        this.capacity = capacity;
        this.speed = speed;
        this.cost = cost;
    }

    public boolean removeCargo() {
        return false;
    }

    //Need to add loading method, make sure it loads the queue with the oldest shipment
}
