//Written by maten009 and nguy2886
public class ShipmentMaker implements Event {

    private Port port;
    private int genRate;

    public ShipmentMaker(Port portName, int genRate) {
        port = portName;
        this.genRate = genRate;
    }

    public void run() {
        for (int i = 0; i < genRate; i++) { //Creates genRate number of packages per day based on the port
            int randNum = (int) (Math.random() * 9); //Randomly number between 0-8
            int weight = (int) (Math.random() * 1000) + 1; //Random weight of given package
            double creationTime = ShippingSim.agenda.getCurrentTime(); //Time created
            Port destination = ShippingSim.portList[(randNum + port.getPortNum() + 1) % 10]; //Selects random port **This is most likely where we do the moon's 0.1% generation**
            port.shipmentList[randNum].add(new Shipment(weight, creationTime, destination)); //Add the shipment to the designated port queue
            //System.out.println("Added new shipment from " + port.getName() + " (" + weight + "kg) to " + destination + "."); //Debug code

        }
        ShippingSim.agenda.add(this, 1); //ShipmentMaker is added to the queue at a rate of 1 second
    }
}
