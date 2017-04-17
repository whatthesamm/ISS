import java.util.ArrayList;

//Written by maten009 and nguy2886
public class Vessel {
    private String name;
    private double capacity;
    private int currentWeight = 0;

    public int getSpeed() {
        return speed;
    }

    private int speed;
    private int cost;
    private ArrayList cargoList = new ArrayList();

    public Vessel(String name, int capacity, int speed, int cost) {
        this.name = name;
        this.capacity = capacity;
        this.speed = speed;
        this.cost = cost;
    }

    public void removeCargo() {
        for (int i = 0; i < cargoList.size(); i++){
            if (cargoList.get(i) != null) cargoList.remove(i);
        }
        currentWeight = 0;
    }

    //Need to add loading method, make sure it loads the queue with the oldest shipment

    public boolean addCargo(Shipment cargo){
        if (willItFit(cargo)){
            cargoList.add(cargo);
            currentWeight += cargo.getWeight();
            System.out.println("Cargo weight " + cargo.getWeight() + "\nCurrent weight: " + currentWeight);
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
