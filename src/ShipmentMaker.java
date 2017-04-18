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

            Port destination = ShippingSim.portList[(randNum + port.getPortNum() + 1) % 10]; //Selects random destination port

            if (destination.getName().equals("Moon")) { //This will enter a loop to determine if the moon will become the true destination with a 0.1% chance
                int chance = (int) (Math.random() * 1000); //1000 is for 1/1000 = 0.001 = 0.1% chance, will produce that chance to accept shipment to moon
                if (chance != 0) { //If chance fails to become 0, it will find a different destination
                    while (destination == port || destination.getName().equals("Moon")) { //Safeguard to ensure it won't select the new destination to itself or the moon
                        randNum = (int) (Math.random() * 9);
                        destination = ShippingSim.portList[(randNum + port.getPortNum() + 1) % 10]; //Selects new port after Moon's failure to become the destination
                    }
                }
            }
            if (destination.getName().equals("Moon")) Stat.moonCounter++;
            port.shipmentList[randNum].add(new Shipment(weight, creationTime)); //Add the shipment to the designated port queue
        }
        ShippingSim.agenda.add(this, 1440); //ShipmentMaker is added to the queue at a rate of 1 day
    }
}
