//Written by maten009 and nguy2886
public class Shipment {

    private int weight;
    private double creationTime;

    public Shipment(int weight, double creationTime) {
        this.weight = weight;
        this.creationTime = creationTime;
    }

    public int getWeight() {
        return weight;
    }

    public double getCreationTime() {
        return creationTime;
    }
}
