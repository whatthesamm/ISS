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



        while (port.shipmentList[shipmentListDest].length() > 0 && vessel.percentFull() < ShippingSim.c && vessel.willItFit((Shipment)port.shipmentList[shipmentListDest].getFront().getData())){
            vessel.addCargo((Shipment)port.shipmentList[shipmentListDest].remove());
            Stat.shipped++;
        }
        //If the next item in the queue is too big to fit, then just leave

        boolean nextItemTooBig = false;
        if (port.shipmentList[shipmentListDest].getFront()!= null && !vessel.willItFit((Shipment)port.shipmentList[shipmentListDest].getFront().getData())){
            nextItemTooBig = true;
        }

        //Check if the ship is ready to depart
        if (timeRemaining == 0 || vessel.percentFull() >= ShippingSim.c || nextItemTooBig){ //If the vessel meets the minimum requirements
            double distance = (Math.sqrt(Math.pow(port.getLocation()[0] - destination.getLocation()[0], 2) + Math.pow(port.getLocation()[1] - destination.getLocation()[1], 2)));
            int elapsedTime = (int) Math.ceil((distance / vessel.getSpeed())*60);
            ShippingSim.agenda.add(new VesselEvent(destination,vessel,null),elapsedTime); //Schedule a new VesselEvent
            vessel.setDistanceTravelled((int)distance);
            Stat.daysWaited += ShippingSim.w - timeRemaining; //How many days the vessel waited
            Stat.shipsDeparted++; //How many ships left
        } else {
            timeRemaining--;
            ShippingSim.agenda.add(this,1440);
        }
    }
}
