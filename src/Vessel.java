//Written by maten009 and nguy2886
public class Vessel {
    private double capacity;
    private int currentWeight = 0;
    private int numCargo = 0;
    private int speed;
    private int cost;
    private int distanceTravelled = 0;

    public Vessel(double capacity, int speed, int cost) {
        this.capacity = capacity;
        this.speed = speed;
        this.cost = cost;
    }

    public void setDistanceTravelled(int distanceTravelled) {
        this.distanceTravelled = distanceTravelled;
    }

    public double getCapacity() {
        return capacity;
    }

    public int getSpeed() {
        return speed;
    }

    public void removeCargo(boolean PirateTown) {
        int chance = (int) (Math.random() * 10); //10% chance, hence 1/10
        if (PirateTown && chance == 0) { //If PirateTown is the destination
            //Do nothing
        } else {//Track stats
            Stat.profit += (currentWeight * 10) - (distanceTravelled * cost);
            Stat.weightDelivered += currentWeight;
        }
        currentWeight = 0;
        numCargo = 0;
    }

    public boolean addCargo(Shipment cargo) { //Load cargo onto the ship
        if (willItFit(cargo)) {
            currentWeight += cargo.getWeight();
            numCargo++;
            return true;
        }
        return false;
    }

    public boolean willItFit(Shipment cargo) { //Check if the cargo will fit on the boat
        if (cargo == null) return false;
        return currentWeight + cargo.getWeight() < capacity;
    }

    public int getCost() {
        return cost;
    }

    public double percentFull() {
        return currentWeight / capacity;
    }

}
