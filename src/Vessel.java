import java.util.ArrayList;

//Written by maten009 and nguy2886
public class Vessel {
    private String name;
    private int capacity;

    public int getSpeed() {
        return speed;
    }

    private int speed;
    private int cost;
    private Port destination;
    private ArrayList cargoList = new ArrayList();

    public Vessel(String name, int capacity, int speed, int cost) {
        this.name = name;
        this.capacity = capacity;
        this.speed = speed;
        this.cost = cost;
    }

    public int removeCargo() {
        int counter = 0;
        for (int i = 0; i < cargoList.size(); i++){
            if (cargoList.get(i) != null){
                Shipment temp = (Shipment)cargoList.get(i);
                Stat.weightDelivered += temp.getWeight();
                counter++;
            }
        }
        return counter;
    }

    //Need to add loading method, make sure it loads the queue with the oldest shipment

    public boolean addCargo(Shipment cargo){
        if (willItFit(cargo)){
            cargoList.add(cargo);
            return true;
        }
        return false;
    }

    public boolean willItFit(Shipment cargo){
        if (cargo == null) return false;
        int temp = 0;
        for (Object aCargoList : cargoList) {
            Shipment tempShip = (Shipment) aCargoList;
            temp += tempShip.getWeight();
        }
        return temp + cargo.getWeight() < capacity;
    }

    public int getCost(){
        return cost;
    }
}
