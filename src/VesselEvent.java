//Written by maten009 and nguy2886
public class VesselEvent implements Event {
    private Port port;
    private Vessel vessel;
    private int timeRemaining;
    private Port destination;

    public VesselEvent(Port port, Vessel vessel, Port destination) {
        this.port = port;
        this.vessel = vessel;
        timeRemaining = ShippingSim.w;
        this.destination = destination;
    }

    public void run() {

        if (destination == null){ //If the ship has just arrived
            vessel.removeCargo();
            int dest = port.getOldestShipmentDest();
            if (dest == -1) { //If the ship is at the moon, choose a random port to go to
                dest = (int) (Math.random() * 9);
            }
            destination = ShippingSim.portList[(dest + port.getPortNum()+ 1)%9];
        }

        while ((port.shipmentList[destination.getPortNum()].length() > 0) && (vessel.percentFull() < ShippingSim.c)){
            vessel.addCargo((Shipment) port.shipmentList[destination.getPortNum()].remove());
        }

        System.out.println("The length: " + port.shipmentList[destination.getPortNum()].length());

        if (timeRemaining == 0 || vessel.percentFull() >= ShippingSim.c){ //If the vessel meets the minimum requirements
            int distance = (int)(Math.sqrt(Math.pow(port.getLocation()[0] - destination.getLocation()[0], 2) + Math.pow(port.getLocation()[1] - destination.getLocation()[1], 2)));
            int elapsedTime = (int) Math.ceil((distance / vessel.getSpeed())*60);
            int runCost = distance * vessel.getCost();
            System.out.println("Percent full: " + vessel.percentFull() + "\nPlace of origin: " + port.getName() + "\nDestination: " + destination.getName() + "\nDistance: " + distance + "\nElapsed time: " + elapsedTime + "\n");
            ShippingSim.agenda.add(new VesselEvent(destination,vessel,null),elapsedTime); //Schedule a new VesselEvent
        } else {
            timeRemaining--;
            System.out.println("Waiting with " + timeRemaining + " days left.");
            ShippingSim.agenda.add(this,1440);
        }
    }
}
