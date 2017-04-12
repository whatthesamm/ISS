//Written by maten009 and nguy2886
public class Shipment {

    private int weight;
    private double creationTime;
    private Port destination;

    public Shipment(int weight, double creationTime, Port destination) {
        this.weight = weight;
        this.creationTime = creationTime;
        this.destination = destination;
    }

    public int getWeight() {
        return weight;
    }

    public double getCreationTime() {
        return creationTime;
    }

    public Port getDestination() {
        return destination;
    }
}
