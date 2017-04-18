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
        int shipmentListDest = 0;
        if (destination == null){ //If the ship has just arrived
            if (vessel.percentFull() != 0) {
                vessel.removeCargo(false);
            }
            int dest = port.getOldestShipmentDest();
            if (dest == -1) { //If the ship is at the moon, choose a random port to go to
                dest = (int) (Math.random() * 9);
            }
            shipmentListDest = dest;
            destination = ShippingSim.portList[(dest + port.getPortNum()+ 1)%10];
        }

        //Loading
        /*
        boolean noneFound = false;
        while ((port.shipmentList[shipmentListDest].length() > 0) && !noneFound){
            int counter = 0;
            for (int i = 0; i < port.shipmentList[shipmentListDest].length(); i++){
                if (port.shipmentList[shipmentListDest].getDataAtIndex(i) == null) break;
                Shipment temp = (Shipment) port.shipmentList[shipmentListDest].getDataAtIndex(i);
                if (vessel.willItFit(temp)){
                    Shipment n = (Shipment)port.shipmentList[shipmentListDest].removeAtIndex(i);
                    if (n != null) {
                        vessel.addCargo(n);
                        counter++;
                        break;
                    }
                }
            }
            if (counter == 0) noneFound = true;
            }

        */

        while (port.shipmentList[shipmentListDest].length() > 0 && vessel.percentFull() < ShippingSim.c){
            Queue first = port.shipmentList[shipmentListDest];
            if (vessel.willItFit((Shipment)first.getFront().getData())){
                vessel.addCargo((Shipment)first.getFront().getData());
            } else {
                for (int i = 0; i < )
            }
        }
        //System.out.println("The length: " + port.shipmentList[shipmentListDest].length());

        if (timeRemaining == 0 || vessel.percentFull() >= ShippingSim.c){ //If the vessel meets the minimum requirements
            double distance = (Math.sqrt(Math.pow(port.getLocation()[0] - destination.getLocation()[0], 2) + Math.pow(port.getLocation()[1] - destination.getLocation()[1], 2)));
            int elapsedTime = (int) Math.ceil((distance / vessel.getSpeed())*60);
            int runCost = (int) (distance * vessel.getCost());
            System.out.println("Time: " + ShippingSim.agenda.getCurrentTime() + "\nPercent full: " + vessel.percentFull() + "\nPlace of origin: " + port.getName() + "\nDestination: " + destination.getName() + "\nDistance: " + distance + "\nElapsed time: " + elapsedTime + "\n");
            ShippingSim.agenda.add(new VesselEvent(destination,vessel,null),elapsedTime); //Schedule a new VesselEvent
            vessel.setDistanceTravelled((int)distance);
        } else {
            timeRemaining--;
            //System.out.println("Waiting with " + timeRemaining + " days left.");
            ShippingSim.agenda.add(this,1440);
        }
    }
}
