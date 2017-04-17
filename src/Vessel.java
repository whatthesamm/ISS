//Written by maten009 and nguy2886
public class Vessel {
    private String name;
    private double capacity;
    private int currentWeight = 0;
    private int numCargo = 0;

    public int getSpeed() {
        return speed;
    }

    private int speed;
    private int cost;

    public Vessel(String name, int capacity, int speed, int cost) {
        this.name = name;
        this.capacity = capacity;
        this.speed = speed;
        this.cost = cost;
    }

    public void removeCargo() {
        Stat.weightDelivered += currentWeight;
        currentWeight = 0;
        numCargo = 0;
    }

    //Need to add loading method, make sure it loads the queue with the oldest shipment

    public boolean addCargo(Shipment cargo){
        if (willItFit(cargo)){
            currentWeight += cargo.getWeight();
            numCargo++;
            return true;
        }
        return false;
    }

    public boolean willItFit(Shipment cargo){
        if (cargo == null) return false;
        return currentWeight + cargo.getWeight() < capacity;
    }

    public int getCost(){
        return cost;
    }

    public double percentFull(){
        return currentWeight / capacity;
    }

}
