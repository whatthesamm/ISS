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
            if (port.getName().equals("Pirate Town")){ //Pirate town is a special case
                vessel.removeCargo(true);
            } else if (vessel.percentFull() != 0) {
                vessel.removeCargo(false);
            }

            int dest = port.getOldestShipmentDest(); //Get the index of the queue with the oldest shipment, returns -1 if at moon
            if (dest == -1) { //If the ship is at the moon, choose a random port to go to
                dest = (int) (Math.random() * 9); //Just go to any of the other ports
            }
            shipmentListDest = dest; //This will be used later
            destination = ShippingSim.portList[(dest + port.getPortNum()+ 1)%10]; //Pick the next port to go to
        }


        //Load cargo until there is no more, the ship has reached capacity, or the min weight is reached
        while (port.shipmentList[shipmentListDest].length() > 0 && vessel.percentFull() < ShippingSim.c && vessel.willItFit((Shipment)port.shipmentList[shipmentListDest].getFront().getData())){
            vessel.addCargo((Shipment)port.shipmentList[shipmentListDest].remove());
            Stat.shipped++;
        }

        boolean nextItemTooBig = false; //If the next item is too big to fit, then make this true so the ship can leave
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
            timeRemaining--; //Another day has passed
            ShippingSim.agenda.add(this,1440); //Try again in a day
        }
    }
}
