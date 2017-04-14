//Written by maten009 and nguy2886
public class VesselEvent implements Event {
    private Port port;
    private Vessel vessel;

    public VesselEvent(Port port, Vessel vessel) {
        this.port = port;
        this.vessel = vessel;
    }

    public void run() {
        int temp = vessel.removeCargo();
        int dest = port.getOldestShipmentDest();
        if (dest == -1) { //If the ship is at the moon
            dest = (int) (Math.random() * 9);

        }
        Port destination = ShippingSim.portList[(dest + port.getPortNum()+ 1)%10];
        if (!port.getName().equals("Moon")) {
            while (vessel.willItFit((Shipment) port.shipmentList[dest].getFirst())) {
                vessel.addCargo((Shipment) port.shipmentList[dest].remove());
            }
        }
        //Calculate time to destination
        int distance = (int)(Math.sqrt(Math.pow(port.getLocation()[0] - destination.getLocation()[0], 2) + Math.pow(port.getLocation()[1] - destination.getLocation()[1], 2)));
        int elapsedTime = (int) Math.ceil(distance / (vessel.getSpeed() * 24));
        int runCost = distance / vessel.getCost();

        //Add the next VesselEvent
        ShippingSim.agenda.add(new VesselEvent(destination,vessel),elapsedTime);

        //Summary of event
        System.out.println("Place of origin: " + port.getName() + "\nDestination: " + destination.getName() + "\nDistance: " + distance + "\nElapsed time: " + elapsedTime + "\n");

    }
}
