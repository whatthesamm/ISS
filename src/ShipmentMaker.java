//Written by maten009 and nguy2886
public class ShipmentMaker implements Event {

    private Port port;
    private int genRate;

    public ShipmentMaker(Port portName, int genRate) {
        port = portName;
        this.genRate = genRate;
    }

    public void run() {
        for (int i = 0; i < genRate; i++) { //Creates genRate number of packages per day
            int randNum = (int) (Math.random() * 9); //Randomly number between 0-8
            int weight = (int) (Math.random() * 1000) + 1; //Weight of given package
            double creationTime = ShippingSim.agenda.getCurrentTime(); //Time created
            Port destinationIsland = ShippingSim.portList[(randNum + port.getPortNum() + 1) % 10]; //Get the random island
            port.shipmentList[randNum].add(new Shipment(weight, creationTime, destinationIsland)); //Add the shipment to the designated island queue

        }
        ShippingSim.agenda.add(this, 1);
    }
}
